/* Soot - a J*va Optimization Framework
 * Copyright (C) 2002 Ondrej Lhotak
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

/**
 * A generic interface to any type of pointer analysis.
 *
 * @author Ondrej Lhotak
 */

public interface PointsToAnalysis {
    String THIS_NODE = "THIS_NODE";
    int RETURN_NODE = -2;
    String ARRAY_ELEMENTS_NODE = "ARRAY_ELEMENTS_NODE";
    String CAST_NODE = "CAST_NODE";
    String STRING_ARRAY_NODE = "STRING_ARRAY_NODE";
    String STRING_NODE = "STRING_NODE";
    String STRING_NODE_LOCAL = "STRING_NODE_LOCAL";
    String EXCEPTION_NODE = "EXCEPTION_NODE";
    String STRING_ARRAY_NODE_LOCAL = "STRING_ARRAY_NODE_LOCAL";
    String MAIN_THREAD_NODE = "MAIN_THREAD_NODE";
    String MAIN_THREAD_NODE_LOCAL = "MAIN_THREAD_NODE_LOCAL";
    String MAIN_THREAD_GROUP_NODE = "MAIN_THREAD_GROUP_NODE";
    String MAIN_THREAD_GROUP_NODE_LOCAL = "MAIN_THREAD_GROUP_NODE_LOCAL";
    String MAIN_CLASS_NAME_STRING = "MAIN_CLASS_NAME_STRING";
    String MAIN_CLASS_NAME_STRING_LOCAL = "MAIN_CLASS_NAME_STRING_LOCAL";
    String DEFAULT_CLASS_LOADER = "DEFAULT_CLASS_LOADER";
    String DEFAULT_CLASS_LOADER_LOCAL = "DEFAULT_CLASS_LOADER_LOCAL";
    String FINALIZE_QUEUE = "FINALIZE_QUEUE";
    String CANONICAL_PATH = "CANONICAL_PATH";
    String CANONICAL_PATH_LOCAL = "CANONICAL_PATH_LOCAL";
    String PRIVILEGED_ACTION_EXCEPTION = "PRIVILEGED_ACTION_EXCEPTION";
    String PRIVILEGED_ACTION_EXCEPTION_LOCAL = "PRIVILEGED_ACTION_EXCEPTION_LOCAL";
    String PHI_NODE = "PHI_NODE";

    /**
     * Returns the set of objects pointed to by variable l.
     */
    PointsToSet reachingObjects(Local l);

    /**
     * Returns the set of objects pointed to by variable l in context c.
     */
    PointsToSet reachingObjects(Context c, Local l);

    /**
     * Returns the set of objects pointed to by static field f.
     */
    PointsToSet reachingObjects(SootField f);

    /**
     * Returns the set of objects pointed to by instance field f
     * of the objects in the PointsToSet s.
     */
    PointsToSet reachingObjects(PointsToSet s, SootField f);

    /**
     * Returns the set of objects pointed to by instance field f
     * of the objects pointed to by l.
     */
    PointsToSet reachingObjects(Local l, SootField f);

    /**
     * Returns the set of objects pointed to by instance field f
     * of the objects pointed to by l in context c.
     */
    PointsToSet reachingObjects(Context c, Local l, SootField f);

    /**
     * Returns the set of objects pointed to by elements of the arrays
     * in the PointsToSet s.
     */
    PointsToSet reachingObjectsOfArrayElement(PointsToSet s);
}

