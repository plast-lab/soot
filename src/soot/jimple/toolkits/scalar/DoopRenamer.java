/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-1999 Raja Vallee-Rai
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
 * Modified by the Plast Research Group (U. Athens) and others.
 */






package soot.jimple.toolkits.scalar;

import soot.*;
import soot.jimple.DefinitionStmt;
import soot.singletons.Singletons;

import java.util.*;

public class DoopRenamer extends BodyTransformer
{
    public DoopRenamer(Singletons.Global g ) {}
    public static DoopRenamer v() { return G.v().soot_jimple_toolkits_scalar_DoopRenamer(); }

    protected void internalTransform(Body body, String phaseName, Map<String,String> options)
    {
        Set<Local> transformedLocals = new HashSet<Local>();

        // For all statements, see whether they def a var.
        for (Unit u : body.getUnits()) {
            if (u instanceof DefinitionStmt) {
                DefinitionStmt def = (DefinitionStmt) u;
                Value assignee = def.getLeftOp();
                if (assignee instanceof Local) {
                    Local var = (Local) assignee;
                    if(!(var.getName().startsWith("$")) && !(transformedLocals.contains(var))) {
                        transformedLocals.add(var);
                        int lineNumber = u.getJavaSourceStartLineNumber();
                        if (lineNumber > 0)
                            var.setName(var.getName()+"#_"+u.getJavaSourceStartLineNumber());
                    }
                }
            }
        }
    }
}



