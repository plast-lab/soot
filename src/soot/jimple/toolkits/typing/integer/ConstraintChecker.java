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

package soot.jimple.toolkits.typing.integer;

import soot.*;
import soot.jimple.*;
import soot.jimple.toolkits.typing.Util;

import java.io.PrintWriter;
import java.io.StringWriter;

class ConstraintChecker extends AbstractStmtSwitch {
	private final TypeResolver resolver;
	private final boolean fix; // if true, fix constraint violations

	private JimpleBody stmtBody;

	public ConstraintChecker(TypeResolver resolver, boolean fix) {
		this.resolver = resolver;
		this.fix = fix;
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

	static void error(String message) {
		throw new RuntimeTypeException(message);
	}

	private void handleInvokeExpr(InvokeExpr ie, Stmt invokestmt) {
		// Handle the parameters
		SootMethodRef method = ie.getMethodRef();
		for (int i = 0; i < ie.getArgCount(); i++) {
			if (ie.getArg(i) instanceof Local) {
				Local local = (Local) ie.getArg(i);
				if (local.getType() instanceof IntegerType) {
					if (!ClassHierarchy.getInstance().typeNode(local.getType())
							.hasAncestor_1(ClassHierarchy.getInstance().typeNode(method.parameterType(i)))) {
						if (fix) {
							ie.setArg(i, insertCast(local,
									method.parameterType(i), invokestmt));
						} else {
							error("Type Error");
						}
					}
				}
			}
		}

		if (ie instanceof DynamicInvokeExpr) {
			DynamicInvokeExpr die = (DynamicInvokeExpr) ie;
			SootMethodRef bootstrapMethod = die.getBootstrapMethodRef();
			for (int i = 0; i < die.getBootstrapArgCount(); i++) {
				if (die.getBootstrapArg(i) instanceof Local) {
					Local local = (Local) die.getBootstrapArg(i);
					if (local.getType() instanceof IntegerType) {
						if (!ClassHierarchy.getInstance().typeNode(local.getType())
								.hasAncestor_1(ClassHierarchy.getInstance().typeNode(bootstrapMethod.parameterType(i)))) {
							if (fix) {
								die.setArg(i, insertCast(local,
										bootstrapMethod.parameterType(i), invokestmt));
							} else {
								error("Type Error");
							}
						}
					}
				}
			}
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

		TypeNode left = null;
		TypeNode right = null;

		// ******** LEFT ********

		if (l instanceof ArrayRef) {
			ArrayRef ref = (ArrayRef) l;
			Type baset = ref.getBase().getType();
			if (baset instanceof ArrayType) {
				ArrayType base = (ArrayType) baset;
				Value index = ref.getIndex();

				if ((base.numDimensions == 1)
						&& (base.baseType instanceof IntegerType)) {
					left = ClassHierarchy.getInstance().typeNode(base.baseType);
				}

				if (index instanceof Local) {
					if (!ClassHierarchy.getInstance().typeNode(index.getType())
							.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							ref.setIndex(insertCast((Local) index, IntType.getInstance(),
									stmt));
						} else {
							error("Type Error(5)");
						}
					}
				}
			}
		} else if (l instanceof Local) {
			if (l.getType() instanceof IntegerType) {
				left = ClassHierarchy.getInstance().typeNode(l.getType());
			}
		} else if (l instanceof InstanceFieldRef) {
			InstanceFieldRef ref = (InstanceFieldRef) l;

			if (ref.getFieldRef().type() instanceof IntegerType) {
				left = ClassHierarchy.getInstance().typeNode(ref.getFieldRef().type());
			}
		} else if (l instanceof StaticFieldRef) {
			StaticFieldRef ref = (StaticFieldRef) l;

			if (ref.getFieldRef().type() instanceof IntegerType) {
				left = ClassHierarchy.getInstance().typeNode(ref.getFieldRef().type());
			}
		} else {
			throw new RuntimeException(
					"Unhandled assignment left hand side type: " + l.getClass());
		}

		// ******** RIGHT ********

		if (r instanceof ArrayRef) {
			ArrayRef ref = (ArrayRef) r;
			Type baset = ref.getBase().getType();
			if (!(baset instanceof NullType)) {
				ArrayType base = (ArrayType) baset;
				Value index = ref.getIndex();

				if ((base.numDimensions == 1)
						&& (base.baseType instanceof IntegerType)) {
					right = ClassHierarchy.getInstance().typeNode(base.baseType);
				}

				if (index instanceof Local) {
					if (!ClassHierarchy.getInstance().typeNode(index.getType())
							.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							ref.setIndex(insertCast((Local) index, IntType.getInstance(),
									stmt));
						} else {
							error("Type Error(6)");
						}
					}
				}
			}
		} else if (r instanceof DoubleConstant) {
		} else if (r instanceof FloatConstant) {
		} else if (r instanceof IntConstant) {
			int value = ((IntConstant) r).value;

			if (value < -32768) {
				right = ClassHierarchy.getInstance().INT;
			} else if (value < -128) {
				right = ClassHierarchy.getInstance().SHORT;
			} else if (value < 0) {
				right = ClassHierarchy.getInstance().BYTE;
			} else if (value < 2) {
				right = ClassHierarchy.getInstance().R0_1;
			} else if (value < 128) {
				right = ClassHierarchy.getInstance().R0_127;
			} else if (value < 32768) {
				right = ClassHierarchy.getInstance().R0_32767;
			} else if (value < 65536) {
				right = ClassHierarchy.getInstance().CHAR;
			} else {
				right = ClassHierarchy.getInstance().INT;
			}
		} else if (r instanceof LongConstant) {
		} else if (r instanceof NullConstant) {
		} else if (r instanceof StringConstant) {
		} else if (r instanceof ClassConstant) {
		} else if (r instanceof BinopExpr) {
			// ******** BINOP EXPR ********

			BinopExpr be = (BinopExpr) r;

			Value lv = be.getOp1();
			Value rv = be.getOp2();

			TypeNode lop = null;
			TypeNode rop = null;

			// ******** LEFT ********
			if (lv instanceof Local) {
				if (lv.getType() instanceof IntegerType/* || lv.getType() instanceof LongType*/) {
					lop = ClassHierarchy.getInstance().typeNode(lv.getType());
				}
			} else if (lv instanceof DoubleConstant) {
			} else if (lv instanceof FloatConstant) {
			} else if (lv instanceof IntConstant) {
				int value = ((IntConstant) lv).value;

				if (value < -32768) {
					lop = ClassHierarchy.getInstance().INT;
				} else if (value < -128) {
					lop = ClassHierarchy.getInstance().SHORT;
				} else if (value < 0) {
					lop = ClassHierarchy.getInstance().BYTE;
				} else if (value < 2) {
					lop = ClassHierarchy.getInstance().R0_1;
				} else if (value < 128) {
					lop = ClassHierarchy.getInstance().R0_127;
				} else if (value < 32768) {
					lop = ClassHierarchy.getInstance().R0_32767;
				} else if (value < 65536) {
					lop = ClassHierarchy.getInstance().CHAR;
				} else {
					lop = ClassHierarchy.getInstance().INT;
				}
			} else if (lv instanceof LongConstant) {
				lop = ClassHierarchy.getInstance().LONG;
			} else if (lv instanceof NullConstant) {
			} else if (lv instanceof StringConstant) {
			} else if (lv instanceof ClassConstant) {
			} else {
				throw new RuntimeException(
						"Unhandled binary expression left operand type: "
								+ lv.getClass());
			}

			// ******** RIGHT ********
			if (rv instanceof Local) {
				if (rv.getType() instanceof IntegerType/*  || rv.getType() instanceof LongType*/) {
					rop = ClassHierarchy.getInstance().typeNode(rv.getType());
				}
			} else if (rv instanceof DoubleConstant) {
			} else if (rv instanceof FloatConstant) {
			} else if (rv instanceof IntConstant) {
				int value = ((IntConstant) rv).value;

				if (value < -32768) {
					rop = ClassHierarchy.getInstance().INT;
				} else if (value < -128) {
					rop = ClassHierarchy.getInstance().SHORT;
				} else if (value < 0) {
					rop = ClassHierarchy.getInstance().BYTE;
				} else if (value < 2) {
					rop = ClassHierarchy.getInstance().R0_1;
				} else if (value < 128) {
					rop = ClassHierarchy.getInstance().R0_127;
				} else if (value < 32768) {
					rop = ClassHierarchy.getInstance().R0_32767;
				} else if (value < 65536) {
					rop = ClassHierarchy.getInstance().CHAR;
				} else {
					rop = ClassHierarchy.getInstance().INT;
				}
			} else if (rv instanceof LongConstant) {
				rop = ClassHierarchy.getInstance().LONG;
			} else if (rv instanceof NullConstant) {
			} else if (rv instanceof StringConstant) {
			} else if (rv instanceof ClassConstant) {
			} else {
				throw new RuntimeException(
						"Unhandled binary expression right operand type: "
								+ rv.getClass());
			}

			if ((be instanceof AddExpr) || (be instanceof SubExpr)
					|| (be instanceof MulExpr) || (be instanceof DivExpr)
					|| (be instanceof RemExpr)) {
				if (lop != null && rop != null) {
					if (!lop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							be.setOp1(insertCast(be.getOp1(),
									getTypeForCast(lop), IntType.getInstance(), stmt));
						} else {
							error("Type Error(7)");
						}
					}

					if (!rop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							be.setOp2(insertCast(be.getOp2(),
									getTypeForCast(rop), IntType.getInstance(), stmt));
						} else {
							error("Type Error(8)");
						}
					}
				}

				right = ClassHierarchy.getInstance().INT;
			} else if ((be instanceof AndExpr) || (be instanceof OrExpr)
					|| (be instanceof XorExpr)) {
				if (lop != null && rop != null) {
					TypeNode lca = lop.lca_1(rop);

					if (lca == ClassHierarchy.getInstance().TOP) {
						if (fix) {
							if (!lop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
								be.setOp1(insertCast(be.getOp1(),
										getTypeForCast(lop),
										getTypeForCast(rop), stmt));
								lca = rop;
							}

							if (!rop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
								be.setOp2(insertCast(be.getOp2(),
										getTypeForCast(rop),
										getTypeForCast(lop), stmt));
								lca = lop;
							}
						} else {
							error("Type Error(11)");
						}
					}

					right = lca;
				}
			} else if (be instanceof ShlExpr) {
				if (lop != null) {
					if (!lop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							be.setOp1(insertCast(be.getOp1(),
									getTypeForCast(lop), IntType.getInstance(), stmt));
						} else {
							error("Type Error(9)");
						}
					}
				}
				if (rop != null) {
					if (!rop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							be.setOp2(insertCast(be.getOp2(), getTypeForCast(rop),
									IntType.getInstance(), stmt));
						} else {
							error("Type Error(10)");
						}
					}
				}

				right = (lop == null) ? null : ClassHierarchy.getInstance().INT;
			} else if ((be instanceof ShrExpr) || (be instanceof UshrExpr)) {
				if (lop != null) {
					if (!lop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							be.setOp1(insertCast(be.getOp1(),
									getTypeForCast(lop), ByteType.getInstance(), stmt));
							lop = ClassHierarchy.getInstance().BYTE;
						} else {
							error("Type Error(9)");
						}
					}
				}
				if (rop != null) {
					if (!rop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							be.setOp2(insertCast(be.getOp2(), getTypeForCast(rop),
									IntType.getInstance(), stmt));
						} else {
							error("Type Error(10)");
						}
					}
				}

				right = lop;
			} else if ((be instanceof CmpExpr) || (be instanceof CmpgExpr)
					|| (be instanceof CmplExpr)) {
				right = ClassHierarchy.getInstance().BYTE;
			} else if ((be instanceof EqExpr) || (be instanceof GeExpr)
					|| (be instanceof GtExpr) || (be instanceof LeExpr)
					|| (be instanceof LtExpr) || (be instanceof NeExpr)) {
				if (rop != null) {
					TypeNode lca = lop.lca_1(rop);

					if (lca == ClassHierarchy.getInstance().TOP) {
						if (fix) {
							if (!lop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
								be.setOp1(insertCast(be.getOp1(),
										getTypeForCast(lop), getTypeForCast(rop),
										stmt));
							}

							if (!rop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
								be.setOp2(insertCast(be.getOp2(),
										getTypeForCast(rop), getTypeForCast(lop),
										stmt));
							}
						} else {
							error("Type Error(11)");
						}
					}
				}

				right = ClassHierarchy.getInstance().BOOLEAN;
			} else {
				throw new RuntimeException("Unhandled binary expression type: "
						+ be.getClass());
			}
		} else if (r instanceof CastExpr) {
			CastExpr ce = (CastExpr) r;

			if (ce.getCastType() instanceof IntegerType) {
				right = ClassHierarchy.getInstance().typeNode(ce.getCastType());
			}
		} else if (r instanceof InstanceOfExpr) {
			right = ClassHierarchy.getInstance().BOOLEAN;
		} else if (r instanceof InvokeExpr) {
			InvokeExpr ie = (InvokeExpr) r;

			handleInvokeExpr(ie, stmt);

			if (ie.getMethodRef().returnType() instanceof IntegerType) {
				right = ClassHierarchy.getInstance().typeNode(
						ie.getMethodRef().returnType());
			}
		} else if (r instanceof NewArrayExpr) {
			NewArrayExpr nae = (NewArrayExpr) r;
			Value size = nae.getSize();

			if (size instanceof Local) {
				if (!ClassHierarchy.getInstance().typeNode(size.getType())
						.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
					if (fix) {
						nae.setSize(insertCast((Local) size, IntType.getInstance(), stmt));
					} else {
						error("Type Error(12)");
					}
				}
			}
		} else if (r instanceof NewExpr) {
		} else if (r instanceof NewMultiArrayExpr) {
			NewMultiArrayExpr nmae = (NewMultiArrayExpr) r;

			for (int i = 0; i < nmae.getSizeCount(); i++) {
				Value size = nmae.getSize(i);

				if (size instanceof Local) {
					if (!ClassHierarchy.getInstance().typeNode(size.getType())
							.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							nmae.setSize(i,
									insertCast((Local) size, IntType.getInstance(), stmt));
						} else {
							error("Type Error(13)");
						}
					}
				}
			}
		} else if (r instanceof LengthExpr) {
			right = ClassHierarchy.getInstance().INT;
		} else if (r instanceof NegExpr) {
			NegExpr ne = (NegExpr) r;

			if (ne.getOp() instanceof Local) {
				Local local = (Local) ne.getOp();

				if (local.getType() instanceof IntegerType) {
					TypeNode ltype = ClassHierarchy.getInstance().typeNode(
							local.getType());
					if (!ltype.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						if (fix) {
							ne.setOp(insertCast(local, IntType.getInstance(), stmt));
							ltype = ClassHierarchy.getInstance().BYTE;
						} else {
							error("Type Error(14)");
						}
					}

					right = (ltype == ClassHierarchy.getInstance().CHAR) ? ClassHierarchy
							.getInstance().INT : ltype;
				}
			} else if (ne.getOp() instanceof DoubleConstant) {
			} else if (ne.getOp() instanceof FloatConstant) {
			} else if (ne.getOp() instanceof IntConstant) {
				right = ClassHierarchy.getInstance().INT;
			} else if (ne.getOp() instanceof LongConstant) {
			} else {
				throw new RuntimeException(
						"Unhandled neg expression operand type: "
								+ ne.getOp().getClass());
			}
		} else if (r instanceof Local) {
			Local local = (Local) r;

			if (local.getType() instanceof IntegerType) {
				right = ClassHierarchy.getInstance().typeNode(local.getType());
			}
		} else if (r instanceof InstanceFieldRef) {
			InstanceFieldRef ref = (InstanceFieldRef) r;

			if (ref.getFieldRef().type() instanceof IntegerType) {
				right = ClassHierarchy.getInstance().typeNode(ref.getFieldRef().type());
			}
		} else if (r instanceof StaticFieldRef) {
			StaticFieldRef ref = (StaticFieldRef) r;

			if (ref.getFieldRef().type() instanceof IntegerType) {
				right = ClassHierarchy.getInstance().typeNode(ref.getFieldRef().type());
			}
		} else {
			throw new RuntimeException(
					"Unhandled assignment right hand side type: "
							+ r.getClass());
		}

		if (left != null && right != null) {
			if (!right.hasAncestor_1(left)) {
				if (fix) {
					stmt.setRightOp(insertCast(stmt.getRightOp(),
							getTypeForCast(right), getTypeForCast(left), stmt));
				} else {
					error("Type Error(15)");
				}
			}
		}
	}

	private static Type getTypeForCast(TypeNode node)
	// This method is a local kludge, for avoiding NullPointerExceptions
	// when a R0_1, R0_127, or R0_32767 node is used in a type
	// cast. A more elegant solution would work with the TypeNode
	// type definition itself, but that would require a more thorough
	// knowledge of the typing system than the kludger posesses.
	{
		if (node.type() == null) {
			if (node == ClassHierarchy.getInstance().R0_1) {
				return BooleanType.getInstance();
			} else if (node == ClassHierarchy.getInstance().R0_127) {
				return ByteType.getInstance();
			} else if (node == ClassHierarchy.getInstance().R0_32767) {
				return ShortType.getInstance();
			}
			// Perhaps we should throw an exception here, since I don't think
			// there should be any other cases where node.type() is null.
			// In case that supposition is incorrect, though, we'll just
			// go on to return the null, and let the callers worry about it.
		}
		return node.type();
	}

	public void caseIdentityStmt(IdentityStmt stmt) {
		Value l = stmt.getLeftOp();
		Value r = stmt.getRightOp();

		if (l instanceof Local) {
			if (l.getType() instanceof IntegerType) {
				TypeNode left = ClassHierarchy.getInstance().typeNode(
						(l.getType()));
				TypeNode right = ClassHierarchy.getInstance().typeNode(r.getType());

				if (!right.hasAncestor_1(left)) {
					if (fix) {
						((soot.jimple.internal.JIdentityStmt) stmt)
								.setLeftOp(insertCastAfter((Local) l,
										getTypeForCast(left),
										getTypeForCast(right), stmt));
					} else {
						error("Type Error(16)");
					}
				}
			}
		}
	}

	public void caseEnterMonitorStmt(EnterMonitorStmt stmt) {
	}

	public void caseExitMonitorStmt(ExitMonitorStmt stmt) {
	}

	public void caseGotoStmt(GotoStmt stmt) {
	}

	public void caseIfStmt(IfStmt stmt) {
		ConditionExpr cond = (ConditionExpr) stmt.getCondition();

		Value lv = cond.getOp1();
		Value rv = cond.getOp2();

		TypeNode lop = null;
		TypeNode rop = null;

		// ******** LEFT ********
		if (lv instanceof Local) {
			if (lv.getType() instanceof IntegerType) {
				lop = ClassHierarchy.getInstance().typeNode(lv.getType());
			}
		} else if (lv instanceof DoubleConstant) {
		} else if (lv instanceof FloatConstant) {
		} else if (lv instanceof IntConstant) {
			int value = ((IntConstant) lv).value;

			if (value < -32768) {
				lop = ClassHierarchy.getInstance().INT;
			} else if (value < -128) {
				lop = ClassHierarchy.getInstance().SHORT;
			} else if (value < 0) {
				lop = ClassHierarchy.getInstance().BYTE;
			} else if (value < 2) {
				lop = ClassHierarchy.getInstance().R0_1;
			} else if (value < 128) {
				lop = ClassHierarchy.getInstance().R0_127;
			} else if (value < 32768) {
				lop = ClassHierarchy.getInstance().R0_32767;
			} else if (value < 65536) {
				lop = ClassHierarchy.getInstance().CHAR;
			} else {
				lop = ClassHierarchy.getInstance().INT;
			}
		} else if (lv instanceof LongConstant) {
			lop = ClassHierarchy.getInstance().LONG;
		} else if (lv instanceof NullConstant) {
		} else if (lv instanceof StringConstant) {
		} else if (lv instanceof ClassConstant) {
		} else {
			throw new RuntimeException("Unhandled binary expression left operand type: "
							+ lv.getClass());
		}

		// ******** RIGHT ********
		if (rv instanceof Local) {
			if (rv.getType() instanceof IntegerType) {
				rop = ClassHierarchy.getInstance().typeNode(rv.getType());
			}
		} else if (rv instanceof DoubleConstant) {
		} else if (rv instanceof FloatConstant) {
		} else if (rv instanceof IntConstant) {
			int value = ((IntConstant) rv).value;

			if (value < -32768) {
				rop = ClassHierarchy.getInstance().INT;
			} else if (value < -128) {
				rop = ClassHierarchy.getInstance().SHORT;
			} else if (value < 0) {
				rop = ClassHierarchy.getInstance().BYTE;
			} else if (value < 2) {
				rop = ClassHierarchy.getInstance().R0_1;
			} else if (value < 128) {
				rop = ClassHierarchy.getInstance().R0_127;
			} else if (value < 32768) {
				rop = ClassHierarchy.getInstance().R0_32767;
			} else if (value < 65536) {
				rop = ClassHierarchy.getInstance().CHAR;
			} else {
				rop = ClassHierarchy.getInstance().INT;
			}
		} else if (rv instanceof LongConstant) {
			rop = ClassHierarchy.getInstance().LONG;
		} else if (rv instanceof NullConstant) {
		} else if (rv instanceof StringConstant) {
		} else if (rv instanceof ClassConstant) {
		} else {
			throw new RuntimeException(
					"Unhandled binary expression right operand type: "
							+ rv.getClass());
		}

		if (lop != null && rop != null) {
			if (lop.lca_1(rop) == ClassHierarchy.getInstance().TOP) {
				if (fix) {
					if (!lop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						cond.setOp1(insertCast(cond.getOp1(),
								getTypeForCast(lop), getTypeForCast(rop), stmt));
					}

					if (!rop.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
						cond.setOp2(insertCast(cond.getOp2(),
								getTypeForCast(rop), getTypeForCast(lop), stmt));
					}
				} else {
					error("Type Error(17)");
				}
			}
		}
	}

	public void caseLookupSwitchStmt(LookupSwitchStmt stmt) {
		Value key = stmt.getKey();

		if (key instanceof Local) {
			if (!ClassHierarchy.getInstance().typeNode(key.getType())
					.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
				if (fix) {
					stmt.setKey(insertCast((Local) key, IntType.getInstance(), stmt));
				} else {
					error("Type Error(18)");
				}
			}
		}
	}

	public void caseNopStmt(NopStmt stmt) {
	}

	public void caseReturnStmt(ReturnStmt stmt) {
		if (stmt.getOp() instanceof Local) {
			if (stmt.getOp().getType() instanceof IntegerType) {
				if (!ClassHierarchy
						.getInstance()
						.typeNode(stmt.getOp().getType())
						.hasAncestor_1(
								ClassHierarchy.getInstance().typeNode(
										stmtBody.getMethod().getReturnType()))) {
					if (fix) {
						stmt.setOp(insertCast((Local) stmt.getOp(), stmtBody
								.getMethod().getReturnType(), stmt));
					} else {
						error("Type Error(19)");
					}
				}
			}
		}
	}

	public void caseReturnVoidStmt(ReturnVoidStmt stmt) {
	}

	public void caseTableSwitchStmt(TableSwitchStmt stmt) {
		Value key = stmt.getKey();

		if (key instanceof Local) {
			if (!ClassHierarchy.getInstance().typeNode(key.getType())
					.hasAncestor_1(ClassHierarchy.getInstance().INT)) {
				if (fix) {
					stmt.setKey(insertCast((Local) key, IntType.getInstance(), stmt));
				} else {
					error("Type Error(20)");
				}
			}
			resolver.typeVariable((Local) key).addParent(resolver.INT);
		}
	}

	public void caseThrowStmt(ThrowStmt stmt) {
	}

	public void defaultCase(Stmt stmt) {
		throw new RuntimeException("Unhandled statement type: "
				+ stmt.getClass());
	}

	private Local insertCast(Local oldlocal, Type type, Stmt stmt) {
		Local newlocal = Jimple.newLocal("tmp", type, -1, -1);
		stmtBody.getLocals().add(newlocal);

		Unit u = Util.findFirstNonIdentityUnit(this.stmtBody, stmt);
		stmtBody.getUnits().insertBefore(
				Jimple.newAssignStmt(newlocal,
						Jimple.newCastExpr(oldlocal, type)), u);
		return newlocal;
	}

	private Local insertCastAfter(Local leftlocal, Type lefttype,
								  Type righttype, Stmt stmt) {
		Local newlocal = Jimple.newLocal("tmp", righttype, -1, -1);
		stmtBody.getLocals().add(newlocal);

		Unit u = Util.findLastIdentityUnit(this.stmtBody, stmt);
		stmtBody.getUnits().insertAfter(
				Jimple.newAssignStmt(leftlocal,
						Jimple.newCastExpr(newlocal, lefttype)), u);
		return newlocal;
	}

	private Local insertCast(Value oldvalue, Type oldtype, Type type, Stmt stmt) {
		Local newlocal1 = Jimple.newLocal("tmp", oldtype, -1, -1);
		Local newlocal2 = Jimple.newLocal("tmp", type, -1, -1);
		stmtBody.getLocals().add(newlocal1);
		stmtBody.getLocals().add(newlocal2);

		Unit u = Util.findFirstNonIdentityUnit(this.stmtBody, stmt);
		stmtBody.getUnits().insertBefore(Jimple.newAssignStmt(newlocal1, oldvalue), u);
		stmtBody.getUnits().insertBefore(Jimple.newAssignStmt(newlocal2, Jimple.newCastExpr(newlocal1, type)), u);
		return newlocal2;
	}
}
