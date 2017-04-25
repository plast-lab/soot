/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-2000 Etienne Gagnon.  All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */

package soot.jimple.toolkits.typing;

import soot.*;
import soot.jimple.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

class ConstraintChecker extends AbstractStmtSwitch {
	private final ClassHierarchy hierarchy;
	private final boolean fix; // if true, fix constraint violations

	private JimpleBody stmtBody;

	ConstraintChecker(TypeResolver resolver, boolean fix) {
		this.fix = fix;

		hierarchy = resolver.hierarchy();
	}

	public void check(Stmt stmt, JimpleBody stmtBody) throws TypeException {
		try {
			this.stmtBody = stmtBody;
			stmt.apply(this);
		} catch (RuntimeTypeException e) {
			StringWriter st = new StringWriter();
			PrintWriter pw = new PrintWriter(st);
			e.printStackTrace(pw);
			pw.close();
			throw new TypeException(st.toString());
		}
	}


	private static class RuntimeTypeException extends RuntimeException {
		RuntimeTypeException(String message) {
			super(message);
		}
	}

	private static void error(String message) {
		throw new RuntimeTypeException(message);
	}

	private void handleInvokeExpr(InvokeExpr ie, Stmt invokestmt) {
		// Handle the parameters
		SootMethodRef method = ie.getMethodRef();
		for (int i = 0; i < ie.getArgCount(); i++) {
			if (ie.getArg(i) instanceof Local) {
				Local local = (Local) ie.getArg(i);
				if (!hierarchy.typeNode(local.getType()).hasAncestorOrSelf(
						hierarchy.typeNode(method.parameterType(i)))) {
					if (fix) {
						ie.setArg(i, insertCast(local, method.parameterType(i), invokestmt));
					} else {
						error("Type Error");
					}
				}
			}
		}

		if (ie instanceof InterfaceInvokeExpr) {
			InterfaceInvokeExpr invoke = (InterfaceInvokeExpr) ie;
			Value base = invoke.getBase();
			if (base instanceof Local) {
				Local local = (Local) base;
				if (!hierarchy.typeNode(local.getType()).hasAncestorOrSelf(
						hierarchy.typeNode(method.declaringClass().getType()))) {
					if (fix) {
						invoke.setBase(insertCast(local, method.declaringClass().getType(), invokestmt));
					} else {
						error("Type Error(7): local " + local + " is of incompatible type " + local.getType());
					}
				}
			}
		} else if (ie instanceof SpecialInvokeExpr) {
			SpecialInvokeExpr invoke = (SpecialInvokeExpr) ie;
			Value base = invoke.getBase();
			if (base instanceof Local) {
				Local local = (Local) base;
				if (!hierarchy.typeNode(local.getType()).hasAncestorOrSelf(
						hierarchy.typeNode(method.declaringClass().getType()))) {
					if (fix) {
						invoke.setBase(insertCast(local, method.declaringClass().getType(), invokestmt));
					} else {
						error("Type Error(9)");
					}
				}
			}
		} else if (ie instanceof VirtualInvokeExpr) {
			VirtualInvokeExpr invoke = (VirtualInvokeExpr) ie;
			Value base = invoke.getBase();
			if (base instanceof Local) {
				Local local = (Local) base;
				if (!hierarchy.typeNode(local.getType()).hasAncestorOrSelf(
						hierarchy.typeNode(method.declaringClass().getType()))) {
					if (fix) {
						invoke.setBase(insertCast(local, method.declaringClass().getType(), invokestmt));
					} else {
						error("Type Error(13)");
					}
				}
			}
		} else if (ie instanceof StaticInvokeExpr) {
			// No base to handle
		} else if (ie instanceof DynamicInvokeExpr) {
			DynamicInvokeExpr die = (DynamicInvokeExpr) ie;
			SootMethodRef bootstrapMethod = die.getMethodRef();
			for (int i = 0; i < die.getBootstrapArgCount(); i++) {
				if (die.getBootstrapArg(i) instanceof Local) {
					Local local = (Local) die.getBootstrapArg(i);
					if (!hierarchy.typeNode(local.getType()).hasAncestorOrSelf(
							hierarchy.typeNode(bootstrapMethod.parameterType(i)))) {
						if (fix) {
							ie.setArg(i, insertCast(local, bootstrapMethod.parameterType(i), invokestmt));
						} else {
							error("Type Error");
						}
					}
				}
			}
		} else {
			throw new RuntimeException("Unhandled invoke expression type: " + ie.getClass());
		}
	}

	public void caseBreakpointStmt(BreakpointStmt stmt) {
		// Do nothing
	}

	public void caseInvokeStmt(InvokeStmt stmt) {
		handleInvokeExpr(stmt.getInvokeExpr(), stmt);
	}

	public void caseAssignStmt(AssignStmt stmt) {
		Value l = stmt.getLeftOp();
		Value r = stmt.getRightOp();

		TypeNode left;

		// ******** LEFT ********

		if (l instanceof ArrayRef) {
			ArrayRef ref = (ArrayRef) l;
			TypeNode base = hierarchy.typeNode(ref.getBase().getType());

			if (!base.isArray()) {
				error("Type Error(16)");
			}

			left = base.element();

			Value index = ref.getIndex();

			if (index instanceof Local) {
				if (!hierarchy.typeNode(index.getType()).hasAncestorOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
					error("Type Error(17)");
				}
			}
		} else if (l instanceof Local) {
			try {
				left = hierarchy.typeNode(l.getType());
			} catch (InternalTypingException e) {
				System.out.println("untyped local: " + l);
				throw e;
			}
		} else if (l instanceof InstanceFieldRef) {
			InstanceFieldRef ref = (InstanceFieldRef) l;

			TypeNode base = hierarchy.typeNode(ref.getBase().getType());

			if (!base.hasAncestorOrSelf(hierarchy.typeNode(ref.getField().getDeclaringClass().getType()))) {
				if (fix) {
					ref.setBase(insertCast((Local) ref.getBase(), ref.getField().getDeclaringClass().getType(), stmt));
				} else {
					error("Type Error(18)");
				}
			}

			left = hierarchy.typeNode(ref.getField().getType());
		} else if (l instanceof StaticFieldRef) {
			StaticFieldRef ref = (StaticFieldRef) l;
			left = hierarchy.typeNode(ref.getField().getType());
		} else {
			throw new RuntimeException("Unhandled assignment left hand side type: " + l.getClass());
		}

		// ******** RIGHT ********

		if (r instanceof ArrayRef) {
			ArrayRef ref = (ArrayRef) r;
			TypeNode base = hierarchy.typeNode(ref.getBase().getType());

			if (!base.isArray()) {
				error("Type Error(19): " + base + " is not an array type");
			}

			if (base == hierarchy.NULL) {
				return;
			}

			if (!left.hasDescendantOrSelf(base.element())) {
				if (fix) {
					Type lefttype = left.type();
					if (lefttype instanceof ArrayType) {
						ArrayType atype = (ArrayType) lefttype;
						ref.setBase(insertCast((Local) ref.getBase(),
								ArrayType.getInstance(atype.baseType, atype.numDimensions + 1), stmt));
					} else {
						ref.setBase(insertCast((Local) ref.getBase(), ArrayType.getInstance(lefttype, 1), stmt));
					}
				} else {
					error("Type Error(20)");
				}
			}

			Value index = ref.getIndex();

			if (index instanceof Local) {
				if (!hierarchy.typeNode(index.getType()).hasAncestorOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
					error("Type Error(21)");
				}
			}
		} else if (r instanceof DoubleConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(DoubleType.getInstance()))) {
				error("Type Error(22)");
			}
		} else if (r instanceof FloatConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(FloatType.getInstance()))) {
				error("Type Error(45)");
			}
		} else if (r instanceof IntConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
				error("Type Error(23)");
			}
		} else if (r instanceof LongConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(LongType.getInstance()))) {
				error("Type Error(24)");
			}
		} else if (r instanceof NullConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(NullType.getInstance()))) {
				error("Type Error(25)");
			}
		} else if (r instanceof StringConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(RefType.getInstance("java.lang.String")))) {
				error("Type Error(26)");
			}
		} else if (r instanceof ClassConstant) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(RefType.getInstance("java.lang.Class")))) {
				error("Type Error(27)");
			}
		} else if (r instanceof BinopExpr) {
			// ******** BINOP EXPR ********

			BinopExpr be = (BinopExpr) r;

			Value lv = be.getOp1();
			Value rv = be.getOp2();

			TypeNode lop;
			TypeNode rop;

			// ******** LEFT ********
			if (lv instanceof Local) {
				lop = hierarchy.typeNode(lv.getType());
			} else if (lv instanceof DoubleConstant) {
				lop = hierarchy.typeNode(DoubleType.getInstance());
			} else if (lv instanceof FloatConstant) {
				lop = hierarchy.typeNode(FloatType.getInstance());
			} else if (lv instanceof IntConstant) {
				lop = hierarchy.typeNode(IntType.getInstance());
			} else if (lv instanceof LongConstant) {
				lop = hierarchy.typeNode(LongType.getInstance());
			} else if (lv instanceof NullConstant) {
				lop = hierarchy.typeNode(NullType.getInstance());
			} else if (lv instanceof StringConstant) {
				lop = hierarchy.typeNode(RefType.getInstance("java.lang.String"));
			} else if (lv instanceof ClassConstant) {
				lop = hierarchy.typeNode(RefType.getInstance("java.lang.Class"));
			} else {
				throw new RuntimeException("Unhandled binary expression left operand type: " + lv.getClass());
			}

			// ******** RIGHT ********
			if (rv instanceof Local) {
				rop = hierarchy.typeNode(rv.getType());
			} else if (rv instanceof DoubleConstant) {
				rop = hierarchy.typeNode(DoubleType.getInstance());
			} else if (rv instanceof FloatConstant) {
				rop = hierarchy.typeNode(FloatType.getInstance());
			} else if (rv instanceof IntConstant) {
				rop = hierarchy.typeNode(IntType.getInstance());
			} else if (rv instanceof LongConstant) {
				rop = hierarchy.typeNode(LongType.getInstance());
			} else if (rv instanceof NullConstant) {
				rop = hierarchy.typeNode(NullType.getInstance());
			} else if (rv instanceof StringConstant) {
				rop = hierarchy.typeNode(RefType.getInstance("java.lang.String"));
			} else if (rv instanceof ClassConstant) {
				rop = hierarchy.typeNode(RefType.getInstance("java.lang.Class"));
			} else {
				throw new RuntimeException("Unhandled binary expression right operand type: " + rv.getClass());
			}

			if ((be instanceof AddExpr) || (be instanceof SubExpr) || (be instanceof MulExpr)
					|| (be instanceof DivExpr) || (be instanceof RemExpr) || (be instanceof AndExpr)
					|| (be instanceof OrExpr) || (be instanceof XorExpr)) {
				if (!(left.hasDescendantOrSelf(lop) && left.hasDescendantOrSelf(rop))) {
					error("Type Error(27)");
				}
			} else if ((be instanceof ShlExpr) || (be instanceof ShrExpr) || (be instanceof UshrExpr)) {
				if (!(left.hasDescendantOrSelf(lop) && hierarchy.typeNode(IntType.getInstance()).hasAncestorOrSelf(rop))) {
					error("Type Error(28)");
				}
			} else if ((be instanceof CmpExpr) || (be instanceof CmpgExpr) || (be instanceof CmplExpr)
					|| (be instanceof EqExpr) || (be instanceof GeExpr) || (be instanceof GtExpr)
					|| (be instanceof LeExpr) || (be instanceof LtExpr) || (be instanceof NeExpr)) {
				try {
					lop.lca(rop);
				} catch (TypeException e) {
					error(e.getMessage());
				}

				if (!left.hasDescendantOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
					error("Type Error(29)");
				}
			} else {
				throw new RuntimeException("Unhandled binary expression type: " + be.getClass());
			}
		} else if (r instanceof CastExpr) {
			CastExpr ce = (CastExpr) r;
			TypeNode cast = hierarchy.typeNode(ce.getCastType());
			if (ce.getOp() instanceof Local) {
				TypeNode op = hierarchy.typeNode(ce.getOp().getType());

				try {
					// we must be careful not to reject primitive type casts
					// (e.g. int to long)
					if (cast.isClassOrInterface() || op.isClassOrInterface()) {
						cast.lca(op);
					}
				} catch (TypeException e) {
					System.out.println(r + "[" + op + "<->" + cast + "]");
					error(e.getMessage());
				}
			}

			if (!left.hasDescendantOrSelf(cast)) {
				error("Type Error(30)");
			}
		} else if (r instanceof InstanceOfExpr) {
			InstanceOfExpr ioe = (InstanceOfExpr) r;
			TypeNode type = hierarchy.typeNode(ioe.getCheckType());
			TypeNode op = hierarchy.typeNode(ioe.getOp().getType());

			try {
				op.lca(type);
			} catch (TypeException e) {
				System.out.println(r + "[" + op + "<->" + type + "]");
				error(e.getMessage());
			}

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
				error("Type Error(31)");
			}
		} else if (r instanceof InvokeExpr) {
			InvokeExpr ie = (InvokeExpr) r;

			handleInvokeExpr(ie, stmt);

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(ie.getMethodRef().returnType()))) {
				error("Type Error(32)");
			}
		} else if (r instanceof NewArrayExpr) {
			NewArrayExpr nae = (NewArrayExpr) r;

			Type baseType = nae.getBaseType();
			TypeNode right;

			if (baseType instanceof ArrayType) {
				right = hierarchy.typeNode(ArrayType.getInstance(((ArrayType) baseType).baseType,
						((ArrayType) baseType).numDimensions + 1));
			} else {
				right = hierarchy.typeNode(ArrayType.getInstance(baseType, 1));
			}

			if (!left.hasDescendantOrSelf(right)) {
				error("Type Error(33)");
			}

			Value size = nae.getSize();
			if (size instanceof Local) {
				TypeNode var = hierarchy.typeNode(size.getType());

				if (!var.hasAncestorOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
					error("Type Error(34)");
				}
			}
		} else if (r instanceof NewExpr) {
			NewExpr ne = (NewExpr) r;

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(ne.getBaseType()))) {
				error("Type Error(35)");
			}
		} else if (r instanceof NewMultiArrayExpr) {
			NewMultiArrayExpr nmae = (NewMultiArrayExpr) r;

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(nmae.getBaseType()))) {
				error("Type Error(36)");
			}

			for (int i = 0; i < nmae.getSizeCount(); i++) {
				Value size = nmae.getSize(i);
				if (size instanceof Local) {
					TypeNode var = hierarchy.typeNode(size.getType());

					if (!var.hasAncestorOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
						error("Type Error(37)");
					}
				}
			}
		} else if (r instanceof LengthExpr) {
			LengthExpr le = (LengthExpr) r;

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
				error("Type Error(38)");
			}

			if (le.getOp() instanceof Local) {
				if (!hierarchy.typeNode(le.getOp().getType()).isArray()) {
					error("Type Error(39)");
				}
			}
		} else if (r instanceof NegExpr) {
			NegExpr ne = (NegExpr) r;
			TypeNode right;

			if (ne.getOp() instanceof Local) {
				right = hierarchy.typeNode(ne.getOp().getType());
			} else if (ne.getOp() instanceof DoubleConstant) {
				right = hierarchy.typeNode(DoubleType.getInstance());
			} else if (ne.getOp() instanceof FloatConstant) {
				right = hierarchy.typeNode(FloatType.getInstance());
			} else if (ne.getOp() instanceof IntConstant) {
				right = hierarchy.typeNode(IntType.getInstance());
			} else if (ne.getOp() instanceof LongConstant) {
				right = hierarchy.typeNode(LongType.getInstance());
			} else {
				throw new RuntimeException("Unhandled neg expression operand type: " + ne.getOp().getClass());
			}

			if (!left.hasDescendantOrSelf(right)) {
				error("Type Error(40)");
			}
		} else if (r instanceof Local) {
			if (!left.hasDescendantOrSelf(hierarchy.typeNode(r.getType()))) {
				if (fix) {
					stmt.setRightOp(insertCast((Local) r, left.type(), stmt));
				} else {
					error("Type Error(41)");
				}
			}
		} else if (r instanceof InstanceFieldRef) {
			InstanceFieldRef ref = (InstanceFieldRef) r;

			TypeNode baseType = hierarchy.typeNode(ref.getBase().getType());
			if (!baseType.hasAncestorOrSelf(hierarchy.typeNode(ref.getField().getDeclaringClass().getType()))) {
				if (fix) {
					ref.setBase(insertCast((Local) ref.getBase(), ref.getField().getDeclaringClass().getType(), stmt));
				} else {
					error("Type Error(42)");
				}
			}

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(ref.getField().getType()))) {
				error("Type Error(43)");
			}
		} else if (r instanceof StaticFieldRef) {
			StaticFieldRef ref = (StaticFieldRef) r;

			if (!left.hasDescendantOrSelf(hierarchy.typeNode(ref.getField().getType()))) {
				error("Type Error(44)");
			}
		} else {
			throw new RuntimeException("Unhandled assignment right hand side type: " + r.getClass());
		}
	}

	public void caseIdentityStmt(IdentityStmt stmt) {
		TypeNode left = hierarchy.typeNode(stmt.getLeftOp().getType());

		Value r = stmt.getRightOp();

		if (!(r instanceof CaughtExceptionRef)) {
			TypeNode right = hierarchy.typeNode(r.getType());
			if (!left.hasDescendantOrSelf(right)) {
				error("Type Error(46) [" + left + " <- " + right + "]");
			}
		} else {
			List<RefType> exceptionTypes = TrapManager.getExceptionTypesOf(stmt, stmtBody);

			for (RefType t : exceptionTypes) {
				if (!left.hasDescendantOrSelf(hierarchy.typeNode(t))) {
					error("Type Error(47)");
				}
			}

			if (!left.hasAncestorOrSelf(hierarchy.typeNode(RefType.getInstance("java.lang.Throwable")))) {
				error("Type Error(48)");
			}
		}
	}

	public void caseEnterMonitorStmt(EnterMonitorStmt stmt) {
		if (stmt.getOp() instanceof Local) {
			TypeNode op = hierarchy.typeNode(stmt.getOp().getType());

			if (!op.hasAncestorOrSelf(hierarchy.typeNode(RefType.getInstance("java.lang.Object")))) {
				error("Type Error(49)");
			}
		}
	}

	public void caseExitMonitorStmt(ExitMonitorStmt stmt) {
		if (stmt.getOp() instanceof Local) {
			TypeNode op = hierarchy.typeNode(stmt.getOp().getType());

			if (!op.hasAncestorOrSelf(hierarchy.typeNode(RefType.getInstance("java.lang.Object")))) {
				error("Type Error(49)");
			}
		}
	}

	public void caseGotoStmt(GotoStmt stmt) {
	}

	public void caseIfStmt(IfStmt stmt) {

		BinopExpr expr = (ConditionExpr) stmt.getCondition();
		Value lv = expr.getOp1();
		Value rv = expr.getOp2();

		TypeNode lop;
		TypeNode rop;

		// ******** LEFT ********
		if (lv instanceof Local) {
			lop = hierarchy.typeNode(lv.getType());
		} else if (lv instanceof DoubleConstant) {
			lop = hierarchy.typeNode(DoubleType.getInstance());
		} else if (lv instanceof FloatConstant) {
			lop = hierarchy.typeNode(FloatType.getInstance());
		} else if (lv instanceof IntConstant) {
			lop = hierarchy.typeNode(IntType.getInstance());
		} else if (lv instanceof LongConstant) {
			lop = hierarchy.typeNode(LongType.getInstance());
		} else if (lv instanceof NullConstant) {
			lop = hierarchy.typeNode(NullType.getInstance());
		} else if (lv instanceof StringConstant) {
			lop = hierarchy.typeNode(RefType.getInstance("java.lang.String"));
		} else if (lv instanceof ClassConstant) {
			lop = hierarchy.typeNode(RefType.getInstance("java.lang.Class"));
		} else {
			throw new RuntimeException("Unhandled binary expression left operand type: " + lv.getClass());
		}

		// ******** RIGHT ********
		if (rv instanceof Local) {
			rop = hierarchy.typeNode(rv.getType());
		} else if (rv instanceof DoubleConstant) {
			rop = hierarchy.typeNode(DoubleType.getInstance());
		} else if (rv instanceof FloatConstant) {
			rop = hierarchy.typeNode(FloatType.getInstance());
		} else if (rv instanceof IntConstant) {
			rop = hierarchy.typeNode(IntType.getInstance());
		} else if (rv instanceof LongConstant) {
			rop = hierarchy.typeNode(LongType.getInstance());
		} else if (rv instanceof NullConstant) {
			rop = hierarchy.typeNode(NullType.getInstance());
		} else if (rv instanceof StringConstant) {
			rop = hierarchy.typeNode(RefType.getInstance("java.lang.String"));
		} else if (rv instanceof ClassConstant) {
			rop = hierarchy.typeNode(RefType.getInstance("java.lang.Class"));
		} else {
			throw new RuntimeException("Unhandled binary expression right operand type: " + rv.getClass());
		}

		try {
			lop.lca(rop);
		} catch (TypeException e) {
			error(e.getMessage());
		}
	}

	public void caseLookupSwitchStmt(LookupSwitchStmt stmt) {
		Value key = stmt.getKey();

		if (key instanceof Local) {
			if (!hierarchy.typeNode(key.getType()).hasAncestorOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
				error("Type Error(50)");
			}
		}
	}

	public void caseNopStmt(NopStmt stmt) {
	}

	public void caseReturnStmt(ReturnStmt stmt) {
		if (stmt.getOp() instanceof Local) {
			if (!hierarchy.typeNode(stmt.getOp().getType()).hasAncestorOrSelf(
					hierarchy.typeNode(stmtBody.getMethod().getReturnType()))) {
				if (fix) {
					stmt.setOp(insertCast((Local) stmt.getOp(), stmtBody.getMethod().getReturnType(), stmt));
				} else {
					error("Type Error(51)");
				}
			}
		}
	}

	public void caseReturnVoidStmt(ReturnVoidStmt stmt) {
	}

	public void caseTableSwitchStmt(TableSwitchStmt stmt) {
		Value key = stmt.getKey();

		if (key instanceof Local) {
			if (!hierarchy.typeNode(key.getType()).hasAncestorOrSelf(hierarchy.typeNode(IntType.getInstance()))) {
				error("Type Error(52)");
			}
		}
	}

	public void caseThrowStmt(ThrowStmt stmt) {
		if (stmt.getOp() instanceof Local) {
			TypeNode op = hierarchy.typeNode(stmt.getOp().getType());

			if (!op.hasAncestorOrSelf(hierarchy.typeNode(RefType.getInstance("java.lang.Throwable")))) {
				if (fix) {
					stmt.setOp(insertCast((Local) stmt.getOp(), RefType.getInstance("java.lang.Throwable"), stmt));
				} else {
					error("Type Error(53)");
				}
			}
		}
	}

	public void defaultCase(Stmt stmt) {
		throw new RuntimeException("Unhandled statement type: " + stmt.getClass());
	}

	private Local insertCast(Local oldlocal, Type type, Stmt stmt) {
		Local newlocal = Jimple.newLocal("tmp", type);
		stmtBody.getLocals().add(newlocal);

		Unit u = Util.findFirstNonIdentityUnit(stmtBody, stmt);
		stmtBody.getUnits().insertBefore(Jimple.newAssignStmt(newlocal, Jimple.newCastExpr(oldlocal, type)), u);
		return newlocal;
	}
}
