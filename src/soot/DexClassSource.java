package soot;
/* Soot - a J*va Optimization Framework
 * Copyright (C) 2011, 2012 Michael Markert
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


import soot.dexpler.DexResolver;
import soot.javaToJimple.IInitialResolver.Dependencies;
import soot.options.Options;

import java.io.File;

/**
 * Responsible for resolving a single class from a dex source format. 
 */
public class DexClassSource extends ClassSource {
    protected File path;
    /**
     * @param className the class which dependencies are to be resolved.
     * @param path to the file that defines the class.
     */
    DexClassSource(String className, File path) {
        super(className);
        this.path = path;
    }

    /**
     * Resolve dependencies of class.
     *
     * @param sc The SootClass to resolve into.
     * @return Dependencies of class (Strings or Types referenced).
     */
    public Dependencies resolve(SootClass sc) {
        if(Options.getInstance().verbose())
            System.out.println("resolving " + className + " from file " + path.getPath());
        return new DexResolver().resolveFromFile(path, className, sc);
    }
}
