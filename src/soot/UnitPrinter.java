/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 Ondrej Lhotak
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

package soot;
import soot.jimple.*;

/**
* Interface for different methods of printing out a Unit.
*/
public interface UnitPrinter {
    void startUnit(Unit u);
    void endUnit(Unit u);
    void startUnitBox(UnitBox u);
    void endUnitBox(UnitBox u);
    void startValueBox(ValueBox u);
    void endValueBox(ValueBox u);

    void incIndent();
    void decIndent();
    void noIndent();
    void setIndent(String newIndent);
    String getIndent();
    
    void literal(String s);
    void newline();
    void local(Local l);
    void type(Type t);
    void methodRef(SootMethodRef m);
    void constant(Constant c);
    void fieldRef(SootFieldRef f);
    void unitRef(Unit u, boolean branchTarget);
    void identityRef(IdentityRef r);

    void setPositionTagger(AttributesUnitPrinter pt);
    AttributesUnitPrinter getPositionTagger();
    StringBuffer output();
}


