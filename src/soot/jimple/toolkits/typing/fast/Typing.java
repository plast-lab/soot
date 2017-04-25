/* Soot - a J*va Optimization Framework
 * Copyright (C) 2008 Ben Bellamy 
 * 
 * All rights reserved.
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
package soot.jimple.toolkits.typing.fast;

import soot.ErroneousType;
import soot.Local;
import soot.Type;
import soot.UnknownType;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ben Bellamy
 */
public class Typing
{
    private Map<Local, Type> map;

    Typing(Collection<Local> vs)
    {
        this.map = new ConcurrentHashMap<>();
        for (Local v : vs) {
            /*
              Originally, all the values on this map where the BottomType instance. My understanding is that this
              that the information obtained from the debugging in bytecode was completely discarded.
             */
            if (v.getType() == null || v.getType() instanceof UnknownType || v.getType() instanceof ErroneousType)
                this.map.put(v, BottomType.getInstance());
            else
                this.map.put(v, v.getType());
        }
    }

    Typing(Typing tg)
    {
        this.map = new ConcurrentHashMap<>(tg.map);
    }

    public Type get(Local v) { return this.map.get(v); }

    public Type set(Local v, Type t) { return this.map.put(v, t); }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for ( Local v : this.map.keySet() )
        {
            sb.append(v);
            sb.append(':');
            sb.append(this.get(v));
            sb.append(',');
        }
        sb.append('}');
        return sb.toString();
    }

    static void minimize(List<Typing> tgs, IHierarchy h)
    {
        outer: for ( ListIterator<Typing> i = tgs.listIterator(); i.hasNext(); )
        {
            Typing tgi = i.next();

            // Throw out duplicate typings
            for ( Typing tgj : tgs ) {
                // if compare = 1, then tgi is the more general typing
                // We shouldn't pick that one as we would then end up
                // with lots of locals typed to Serializable etc.
                if ( tgi != tgj && compare(tgi, tgj, h) == 1 ) {
                    i.remove();
                    continue outer;
                }
            }
        }

    }

    public static int compare(Typing a, Typing b, IHierarchy h)
    {
        int r = 0;
        for ( Local v : a.map.keySet() )
        {
            Type ta = a.get(v), tb = b.get(v);

            int cmp;
            if (TypeResolver.typesEqual(ta, tb))
                cmp = 0;
            else if ( h.ancestor(ta, tb) )
                cmp = 1;
            else if ( h.ancestor(tb, ta) )
                cmp = -1;
            else
                return -2;

            if ( (cmp == 1 && r == -1) || (cmp == -1 && r == 1) )
                return 2;
            if ( r == 0 )
                r = cmp;
        }
        return r;
    }
}