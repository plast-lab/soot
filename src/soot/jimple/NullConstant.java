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





package soot.jimple;

import soot.G;
import soot.NullType;
import soot.Singletons;
import soot.Type;
import soot.util.Switch;

public class NullConstant extends Constant
{
    public NullConstant( Singletons.Global g ) {}
    public static NullConstant v() { return G.v().soot_jimple_NullConstant(); }

    public boolean equals(Object c)
    {
        return c == G.v().soot_jimple_NullConstant();
    }

    public int hashCode() 
    {
        return 982;
    }

    public String toString()
    {
        return Jimple.NULL;
    }

    public Type getType()
    {
        return NullType.v();
    }

    public void apply(Switch sw)
    {
        ((ConstantSwitch) sw).caseNullConstant(this);
    }
}
