/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam
 * Copyright (C) 2004 Ondrej Lhotak
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

package soot.jimple.internal;

import soot.*;
import soot.jimple.ExprSwitch;
import soot.jimple.InterfaceInvokeExpr;
import soot.jimple.Jimple;
import soot.util.Switch;


public abstract class AbstractInterfaceInvokeExpr extends AbstractInstanceInvokeExpr
		implements InterfaceInvokeExpr {
	AbstractInterfaceInvokeExpr(ValueBox baseBox, SootMethodRef methodRef, ValueBox[] argBoxes) {
		super(methodRef, baseBox, argBoxes);
		if (methodRef.isStatic())
			throw new RuntimeException("wrong static-ness");
	}

	public boolean equivTo(Object o) {
		if (o instanceof AbstractInterfaceInvokeExpr) {
			AbstractInterfaceInvokeExpr ie = (AbstractInterfaceInvokeExpr) o;
			if (!(baseBox.getValue().equivTo(ie.baseBox.getValue()) && getMethod().equals(ie.getMethod())
					&& (argBoxes == null ? 0 : argBoxes.length) == (ie.argBoxes == null ? 0 : ie.argBoxes.length)))
				return false;
			if (argBoxes != null) {
				for (int i = 0; i < argBoxes.length; i++) {
					if (!(argBoxes[i]).getValue().equivTo(ie.argBoxes[i].getValue()))
						return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Returns a hash code for this object, consistent with structural equality.
	 */
	public int equivHashCode() {
		return baseBox.getValue().equivHashCode() * 101 + getMethod().hashCode() * 17;
	}

	public abstract Object clone();

	public String toString() {
		StringBuilder buffer = new StringBuilder();

		buffer.append(Jimple.INTERFACEINVOKE + " ").append(baseBox.getValue().toString()).append(".").append(methodRef.getSignature()).append("(");

		if (argBoxes != null) {
			for (int i = 0; i < argBoxes.length; i++) {
				if (i != 0)
					buffer.append(", ");
	
				buffer.append(argBoxes[i].getValue().toString());
			}
		}

		buffer.append(")");

		return buffer.toString();
	}

	public void toString(UnitPrinter up) {
		up.literal(Jimple.INTERFACEINVOKE);
		up.literal(" ");
		baseBox.toString(up);
		up.literal(".");
		up.methodRef(methodRef);
		up.literal("(");

		if (argBoxes != null) {
			for (int i = 0; i < argBoxes.length; i++) {
				if (i != 0)
					up.literal(", ");
	
				argBoxes[i].toString(up);
			}
		}

		up.literal(")");
	}

	public void apply(Switch sw) {
		((ExprSwitch) sw).caseInterfaceInvokeExpr(this);
	}

}
