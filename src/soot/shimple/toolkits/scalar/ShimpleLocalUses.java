/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 Navindra Umanee <navindra@cs.mcgill.ca>
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

package soot.shimple.toolkits.scalar;

import soot.Local;
import soot.Unit;
import soot.Value;
import soot.ValueBox;
import soot.shimple.ShimpleBody;
import soot.toolkits.scalar.LocalUses;
import soot.toolkits.scalar.UnitValueBoxPair;

import java.util.*;

/**
 * This class implements the LocalUses interface for Shimple.
 * ShimpleLocalUses can be used in conjunction with SimpleLocalDefs to
 * provide Definition/Use and Use/Definition chains in SSA.
 *
 * <p> In addition to the interface required by LocalUses,
 * ShimpleLocalUses also provides a method for obtaining the list of
 * uses given only the Local.  Furthermore, unlike SimpleLocalUses, a
 * LocalDefs object is not required when constructing
 * ShimpleLocalUses.
 *
 * @author Navindra Umanee
 * @see ShimpleLocalDefs
 * @see soot.toolkits.scalar.SimpleLocalDefs
 * @see soot.toolkits.scalar.SimpleLocalUses
 **/
public class ShimpleLocalUses implements LocalUses
{
    private Map<Local, ArrayList<UnitValueBoxPair>> localToUses;

    /**
     * Build a LocalUses interface from a ShimpleBody.  Proper SSA
     * form is required, otherwise correct behaviour is not
     * guaranteed.
     **/
    public ShimpleLocalUses(ShimpleBody sb)
    {
        // Instead of rebuilding the ShimpleBody without the
        // programmer's knowledge, throw a RuntimeException
        if(!sb.isSSA())
            throw new RuntimeException("ShimpleBody is not in proper SSA form as required by ShimpleLocalUses.  You may need to rebuild it or use SimpleLocalUses instead.");

        // initialise the map
        localToUses = new HashMap<>();
        for (Local local : sb.getLocals()) {
            localToUses.put(local, new ArrayList<>());
        }

        // iterate through the units and save each Local use in the
        // appropriate list -- due to SSA form, each Local has a
        // unique def, and therefore one appropriate list.
        for (Unit unit : sb.getUnits()) {

            for (ValueBox box : unit.getUseBoxes()) {
                Value value = box.getValue();

                if (!(value instanceof Local))
                    continue;

                ArrayList<UnitValueBoxPair> useList = localToUses.get(value);
                useList.add(new UnitValueBoxPair(unit, box));
            }
        }
    }

    /**
     * Returns all the uses of the given Local as a list of
     * UnitValueBoxPairs, each containing a Unit that uses the local
     * and the corresponding ValueBox containing the Local.  
     *
     * <p> This method is currently not required by the LocalUses
     * interface.
     **/
    public List getUsesOf(Local local)
    {
        List<UnitValueBoxPair> uses = localToUses.get(local);
        if(uses == null)
            return Collections.EMPTY_LIST;
        return uses;
    }

    /**
     * If a Local is defined in the Unit, returns all the uses of that
     * Local as a list of UnitValueBoxPairs, each containing a Unit
     * that uses the local and the corresponding ValueBox containing
     * the Local.
     **/
    public List getUsesOf(Unit unit)
    {
        List<ValueBox> defBoxes = unit.getDefBoxes();
        
        switch(defBoxes.size()){
        case 0:
            return Collections.EMPTY_LIST;
        case 1:
            Value local = (defBoxes.get(0)).getValue();
            if(!(local instanceof Local))
                return Collections.EMPTY_LIST;
            return getUsesOf((Local) local);
        default:
            System.out.println("Warning: Unit has multiple definition boxes?");
            List<Object> usesList = new ArrayList<>();
            for (ValueBox defBoxe : defBoxes) {
                Value def = (defBoxe).getValue();
                if (def instanceof Local)
                    usesList.addAll(getUsesOf((Local) def));
            }
            return usesList;
        }
    }
}
