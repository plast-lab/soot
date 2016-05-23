/* Soot - a J*va Optimization Framework
 * Copyright (C) Feng Qian
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



package soot.tagkit;


import soot.G;
import soot.IdentityUnit;
import soot.Singletons;
import soot.Unit;

/** The aggregator for LineNumberTable attribute. */
public class LineNumberTagAggregator extends FirstTagAggregator
{    
    public LineNumberTagAggregator( Singletons.Global g ) {}
    public static LineNumberTagAggregator v() { return G.v().soot_tagkit_LineNumberTagAggregator(); }

    /** Decide whether this tag should be aggregated by this aggregator. */
    public boolean wantTag(Tag t)
    {
	return (t instanceof LineNumberTag) || (t instanceof SourceLnPosTag);
    }
    
    public String aggregatedName()
    {
        return "LineNumberTable";
    }

    public void considerTag(Tag t, Unit u){
        //System.out.println("consider tag for unit: "+u.getClass());
        if (!(u instanceof IdentityUnit)) super.considerTag(t, u);
    }
}







