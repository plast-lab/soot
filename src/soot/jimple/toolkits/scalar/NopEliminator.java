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
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */


package soot.jimple.toolkits.scalar;

import soot.*;
import soot.jimple.JimpleBody;
import soot.jimple.NopStmt;
import soot.options.Options;
import soot.util.Chain;

import java.util.Iterator;

public class NopEliminator extends BodyTransformer
{

    /** Removes {@link NopStmt}s from the passed body (which must be
	a {@link JimpleBody}).  Complexity is linear 
        with respect to the statements.
    */
    
    protected void internalTransform(Body b)
    {
        JimpleBody body = (JimpleBody)b;
        
        if(Options.getInstance().verbose())
            System.out.println("[" + body.getMethod().getName() +
                "] Removing nops...");
                
        Chain<Unit> units = body.getUnits();
        
        // Just do one trivial pass.
        {
            Iterator<Unit> stmtIt = units.snapshotIterator();
            
            while(stmtIt.hasNext()) 
            {
                Unit u = stmtIt.next();
				if (u instanceof NopStmt) {
					// Hack: do not remove nop, if is is used for a Trap which
					// is at the very end of the code.
					boolean keepNop = false;
					if (b.getUnits().getLast() == u) {
						for (Trap t : b.getTraps()) {
							if (t.getEndUnit() == u) {
								keepNop = true;
							}
						}
					}
					if (!keepNop) {
						units.remove(u);
					}
				}
            }
        }
    }
}
