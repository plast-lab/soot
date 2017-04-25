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

package soot;

import soot.options.Options;
import soot.tagkit.AbstractHost;
import soot.util.*;
import soot.validation.ClassValidator;
import soot.validation.MethodDeclarationValidator;
import soot.validation.OuterClassValidator;
import soot.validation.ValidationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Incomplete and inefficient implementation.
 *
 * Implementation notes:
 *
 * 1. The getFieldOf() method is slow because it traverses the list of fields, comparing the names,
 * one by one.  If you establish a Dictionary of Name->Field, you will need to add a
 * notifyOfNameChange() method, and register fields which belong to classes, because the hashtable
 * will need to be updated.  I will do this later. - kor  16-Sep-97
 *
 * 2. Note 1 is kept for historical (i.e. amusement) reasons.  In fact, there is no longer a list of fields;
 * these are kept in a Chain now.  But that's ok; there is no longer a getFieldOf() method,
 * either.  There still is no efficient way to get a field by name, although one could establish
 * a Chain of EquivalentValue-like objects and do an O(1) search on that.  - plam 2-24-00
 */

/**
 * Soot representation of a Java class. They are usually created by a Scene, but
 * can also be constructed manually through the given constructors.
 */
public class SootClass extends AbstractHost implements Numberable {
    private String name, shortName, fixedShortName, packageName,
            fixedPackageName;
    protected int modifiers;
    protected Chain<SootField> fields = new HashChain<>();
    private SmallNumberedMap<SootMethod> subSigToMethods = new SmallNumberedMap<>(
            Scene.getInstance().getSubSigNumberer());
    // methodList is just for keeping the methods in a consistent order. It
    // needs to be kept consistent with subSigToMethods.
    private List<SootMethod> methodList = new ArrayList<>();
    private Chain<SootClass> interfaces = new HashChain<>();

    private boolean isInScene;
    private SootClass superClass;
    private SootClass outerClass;

    protected boolean isPhantom;

    public final static String INVOKEDYNAMIC_DUMMY_CLASS_NAME = "soot.dummy.InvokeDynamic";

    /**
     * Constructs an empty SootClass with the given name and modifiers.
     */

    public SootClass(String name, int modifiers) {
        if (name.charAt(0) == '[')
            throw new RuntimeException(
                    "Attempt to make a class whose name starts with [");
        setName(name);
        this.modifiers = modifiers;
        refType = RefType.getInstance(name);
        refType.setSootClass(this);
        if (Options.getInstance().debug_resolver())
            System.out.println("created " + name + " with modifiers "
                    + modifiers);
        setResolvingLevel(BODIES);

        Scene.getInstance().getClassNumberer().add(this);
    }

    /**
     * Constructs an empty SootClass with the given name and no modifiers.
     */

    public SootClass(String name) {
        this(name, 0);
    }

    final static int DANGLING = 0;
    public final static int HIERARCHY = 1;
    public final static int SIGNATURES = 2;
    public final static int BODIES = 3;
    private int resolvingLevel = DANGLING;

    private String levelToString(int level) {
        switch (level) {
            case DANGLING:
                return "DANGLING";
            case HIERARCHY:
                return "HIERARCHY";
            case SIGNATURES:
                return "SIGNATURES";
            case BODIES:
                return "BODIES";
            default:
                throw new RuntimeException("unknown resolving level");
        }
    }

    /**
     * Checks if the class has at lease the resolving level specified. This
     * check does nothing is the class resolution process is not completed.
     *
     * @param level
     *            the resolution level, one of DANGLING, HIERARCHY, SIGNATURES,
     *            and BODIES
     * @throws java.lang.RuntimeException
     *             if the resolution is at an insufficient level
     */
    void checkLevel(int level) {
        if (!Scene.getInstance().doneResolving() || Options.getInstance().ignore_resolving_levels())
            return;
        checkLevelIgnoreResolving(level);
    }

    /**
     * Checks if the class has at lease the resolving level specified. This
     * check ignores the resolution completeness.
     *
     * @param level
     *            the resolution level, one of DANGLING, HIERARCHY, SIGNATURES,
     *            and BODIES
     * @throws java.lang.RuntimeException
     *             if the resolution is at an insufficient level
     */
    public void checkLevelIgnoreResolving(int level) {
        if (resolvingLevel < level) {
            String hint = "\nIf you are extending Soot, try to add the following call before calling soot.Main.main(..):\n"
                    + "Scene.getInstance().addBasicClass("
                    + getName()
                    + ","
                    + levelToString(level)
                    + ");\n"
                    + "Otherwise, try whole-program mode (-w).";
            throw new RuntimeException(
                    "This operation requires resolving level "
                            + levelToString(level) + " but " + name
                            + " is at resolving level "
                            + levelToString(resolvingLevel) + hint);
        }
    }

    public int resolvingLevel() {
        return resolvingLevel;
    }

    public void setResolvingLevel(int newLevel) {
        resolvingLevel = newLevel;
    }

    boolean isInScene() {
        return isInScene;
    }

    /** Tells this class if it is being managed by a Scene. */
    void setInScene(boolean isInScene) {
        this.isInScene = isInScene;
    }

    /**
     * Returns a backed Chain of fields.
     */

    public Chain<SootField> getFields() {
        checkLevel(SIGNATURES);
        return fields;
    }

    /**
     * Adds the given field to this class.
     */

    public void addField(SootField f) {
        checkLevel(SIGNATURES);
        if (f.isDeclared())
            throw new RuntimeException("already declared: " + f.getName());

        if (declaresField(f.getName(), f.getType()))
            throw new RuntimeException("Field already exists : " + f.getName()
                    + " of type " + f.getType());

        fields.add(f);
        f.isDeclared = true;
        f.declaringClass = this;

    }

    /**
     * Returns the field of this class with the given name and type. If the
     * field cannot be found, an exception is thrown.
     */
    public SootField getField(String name, Type type) {
        SootField sf = getFieldUnsafe(name, type);
        if (sf == null)
            throw new RuntimeException("No field " + name + " in class "
                    + getName());
        return sf;
    }

    /**
     * Returns the field of this class with the given name and type. If the
     * field cannot be found, null is returned.
     */
    SootField getFieldUnsafe(String name, Type type) {
        checkLevel(SIGNATURES);
        for (SootField field : fields.getElementsUnsorted()) {
            if (field.getName().equals(name) && field.getType().equals(type))
                return field;
        }
        return null;
    }

    /**
     * Returns the field of this class with the given subsignature. If such a
     * field does not exist, an exception is thrown.
     */
    public SootField getField(String subsignature) {
        SootField sf = getFieldUnsafe(subsignature);
        if (sf == null)
            throw new RuntimeException("No field " + subsignature
                    + " in class " + getName());
        return sf;
    }

    /**
     * Returns the field of this class with the given subsignature. If such a
     * field does not exist, null is returned.
     */
    SootField getFieldUnsafe(String subsignature) {
        checkLevel(SIGNATURES);
        for (SootField field : fields.getElementsUnsorted()) {
            if (field.getSubSignature().equals(subsignature))
                return field;
        }
        return null;
    }

    /**
     * Returns the method of this class with the given subsignature. If no
     * method with the given subsignature can be found, an exception is thrown.
     */
    public SootMethod getMethod(NumberedString subsignature) {
        SootMethod ret = getMethodUnsafe(subsignature);
        if (ret == null)
            throw new RuntimeException("No method " + subsignature
                    + " in class " + getName());
        else
            return ret;
    }

    /**
     * Returns the method of this class with the given subsignature. If no
     * method with the given subsignature can be found, null is returned.
     */
    SootMethod getMethodUnsafe(NumberedString subsignature) {
        checkLevel(SIGNATURES);
        return subSigToMethods.get(subsignature);
    }

    /**
     * Does this class declare a method with the given subsignature?
     */
    boolean declaresMethod(NumberedString subsignature) {
        checkLevel(SIGNATURES);
        SootMethod ret = subSigToMethods.get(subsignature);
        return ret != null;
    }

    /*
     * Returns the method of this class with the given subsignature. If no
     * method with the given subsignature can be found, an exception is thrown.
     */
    public SootMethod getMethod(String subsignature) {
        checkLevel(SIGNATURES);
        return getMethod(Scene.getInstance().getSubSigNumberer().findOrAdd(subsignature));
    }

    /*
     * Returns the method of this class with the given subsignature. If no
     * method with the given subsignature can be found, null is returned.
     */
    public SootMethod getMethodUnsafe(String subsignature) {
        checkLevel(SIGNATURES);
        return getMethodUnsafe(Scene.getInstance().getSubSigNumberer()
                .findOrAdd(subsignature));
    }

    /**
     * Does this class declare a method with the given subsignature?
     */

    boolean declaresMethod(String subsignature) {
        checkLevel(SIGNATURES);
        return declaresMethod(Scene.getInstance().getSubSigNumberer()
                .findOrAdd(subsignature));
    }

    /**
     * Does this class declare a field with the given name and type.
     */
    public boolean declaresField(String name, Type type) {
        checkLevel(SIGNATURES);
        for (SootField field : fields) {
            if (field.getName().equals(name) && field.getType().equals(type))
                return true;
        }

        return false;
    }

    /**
     * Returns the number of methods in this class.
     */

    int getMethodCount() {
        checkLevel(SIGNATURES);
        return subSigToMethods.nonNullSize();
    }

    /**
     * Returns an iterator over the methods in this class.
     */

    Iterator<SootMethod> methodIterator() {
        checkLevel(SIGNATURES);
        return new Iterator<SootMethod>() {
            final Iterator<SootMethod> internalIterator = methodList.iterator();
            private SootMethod currentMethod;

            @Override
            public boolean hasNext() {
                return internalIterator.hasNext();
            }

            @Override
            public SootMethod next() {
                currentMethod = internalIterator.next();
                return currentMethod;
            }

            @Override
            public void remove() {
                internalIterator.remove();

                subSigToMethods.put(currentMethod.getNumberedSubSignature(), null);
                currentMethod.setDeclared(false);
            }
        };
    }

    public List<SootMethod> getMethods() {
        checkLevel(SIGNATURES);
        return methodList;
    }

    /**
     * Attempts to retrieve the method with the given name, parameters and
     * return type. If no matching method can be found, an exception is thrown.
     */
    public SootMethod getMethod(String name, List<Type> parameterTypes,
                                Type returnType) {
        SootMethod sm = getMethodUnsafe(name, parameterTypes, returnType);
        if (sm != null)
            return sm;

        throw new RuntimeException("Class " + getName()
                + " doesn't have method " + name + "(" + parameterTypes + ")"
                + " : " + returnType);
    }

    /**
     * Attempts to retrieve the method with the given name, parameters and
     * return type. If no matching method can be found, null is returned.
     */
    public SootMethod getMethodUnsafe(String name, List<Type> parameterTypes,
                                      Type returnType) {
        checkLevel(SIGNATURES);
        for (SootMethod method : methodList) {
            if (method.getName().equals(name)
                    && parameterTypes.equals(method.getParameterTypes())
                    && returnType.equals(method.getReturnType())) {
                return method;
            }
        }
        return null;
    }

    /**
     * Attempts to retrieve the method with the given name and parameters. This
     * method may throw an AmbiguousMethodException if there is more than one
     * method with the given name and parameter.
     */

    public SootMethod getMethod(String name, List<Type> parameterTypes) {
        checkLevel(SIGNATURES);
        SootMethod foundMethod = null;

        for (SootMethod method : methodList) {
            if (method.getName().equals(name)
                    && parameterTypes.equals(method.getParameterTypes())) {
                if (foundMethod == null)
                    foundMethod = method;
                else
                    throw new RuntimeException("ambiguous method");
            }
        }

        if (foundMethod == null)
            throw new RuntimeException("couldn't find method " + name + "("
                    + parameterTypes + ") in " + this);
        return foundMethod;
    }

    /**
     * Attempts to retrieve the method with the given name. This method may
     * throw an AmbiguousMethodException if there are more than one method with
     * the given name. If no method with the given is found, null is returned.
     */
    public SootMethod getMethodByNameUnsafe(String name) {
        checkLevel(SIGNATURES);
        SootMethod foundMethod = null;

        for (SootMethod method : methodList) {
            if (method.getName().equals(name)) {
                if (foundMethod == null)
                    foundMethod = method;
                else
                    throw new RuntimeException("ambiguous method: " + name
                            + " in class " + this);
            }
        }
        return foundMethod;
    }

    /**
     * Does this class declare a method with the given name, parameter types,
     * and return type?
     */

    public boolean declaresMethod(String name, List<Type> parameterTypes,
                                  Type returnType) {
        checkLevel(SIGNATURES);
        for (SootMethod method : methodList) {
            if (method.getName().equals(name)
                    && method.getParameterTypes().equals(parameterTypes)
                    && method.getReturnType().equals(returnType))

                return true;
        }

        return false;
    }

    /**
     * Adds the given method to this class.
     */
    public void addMethod(SootMethod m) {
        checkLevel(SIGNATURES);
        if (m.isDeclared())
            throw new RuntimeException("already declared: " + m.getName());

        if (subSigToMethods.get(m.getNumberedSubSignature()) != null) {
            throw new RuntimeException(
                    "Attempting to add method "
                            + m.getSubSignature()
                            + " to class "
                            + this
                            + ", but the class already has a method with that signature.");
        }
        subSigToMethods.put(m.getNumberedSubSignature(), m);
        methodList.add(m);
        m.setDeclared(true);
        m.setDeclaringClass(this);
    }

    synchronized SootMethod getOrAddMethod(SootMethod m) {
        checkLevel(SIGNATURES);
        if (m.isDeclared())
            throw new RuntimeException("already declared: " + m.getName());
        SootMethod old = subSigToMethods.get(m.getNumberedSubSignature());
        if (old != null)
            return old;
        subSigToMethods.put(m.getNumberedSubSignature(), m);
        methodList.add(m);
        m.setDeclared(true);
        m.setDeclaringClass(this);
        return m;
    }

    /**
     * Removes the given method from this class.
     */

    void removeMethod(SootMethod m) {
        checkLevel(SIGNATURES);
        if (!m.isDeclared() || m.getDeclaringClass() != this)
            throw new RuntimeException("incorrect declarer for remove: "
                    + m.getName());

        if (subSigToMethods.get(m.getNumberedSubSignature()) == null) {
            throw new RuntimeException("Attempt to remove method "
                    + m.getSubSignature() + " which is not in class " + this);
        }
        subSigToMethods.put(m.getNumberedSubSignature(), null);
        methodList.remove(m);
        m.setDeclared(false);
    }

    /**
     * Returns the modifiers of this class.
     */

    public int getModifiers() {
        return modifiers;
    }

    /**
     * Sets the modifiers for this class.
     */

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    /**
     * Returns the number of interfaces being directly implemented by this
     * class. Note that direct implementation corresponds to an "implements"
     * keyword in the Java class file and that this class may still be
     * implementing additional interfaces in the usual sense by being a subclass
     * of a class which directly implements some interfaces.
     */

    public int getInterfaceCount() {
        checkLevel(HIERARCHY);
        return interfaces.size();
    }

    /**
     * Returns a backed Chain of the interfaces that are directly implemented by
     * this class. (see getInterfaceCount())
     */

    public Chain<SootClass> getInterfaces() {
        checkLevel(HIERARCHY);
        return interfaces;
    }

    /**
     * Does this class directly implement the given interface? (see
     * getInterfaceCount())
     */

    public boolean implementsInterface(String name) {
        checkLevel(HIERARCHY);

        for (soot.SootClass SootClass : getInterfaces()) {
            if (SootClass.getName().equals(name))
                return true;
        }

        return false;
    }

    /**
     * Add the given class to the list of interfaces which are directly
     * implemented by this class.
     */

    public void addInterface(SootClass interfaceClass) {
        checkLevel(HIERARCHY);
        if (implementsInterface(interfaceClass.getName()))
            throw new RuntimeException("duplicate interface: "
                    + interfaceClass.getName());
        interfaces.add(interfaceClass);
    }

    /**
     * WARNING: interfaces are subclasses of the java.lang.Object class! Does
     * this class have a superclass? False implies that this is the
     * java.lang.Object class. Note that interfaces are subclasses of the
     * java.lang.Object class.
     */

    public boolean hasSuperclass() {
        checkLevel(HIERARCHY);
        return superClass != null;
    }

    /**
     * WARNING: interfaces are subclasses of the java.lang.Object class! Returns
     * the superclass of this class. (see hasSuperclass())
     */

    public SootClass getSuperclass() {
        checkLevel(HIERARCHY);
        if (superClass == null && !isPhantom())
            throw new RuntimeException("no superclass for " + getName());
        else
            return superClass;
    }

    /**
     * Sets the superclass of this class. Note that passing a null will cause
     * the class to have no superclass.
     */

    public void setSuperclass(SootClass c) {
        checkLevel(HIERARCHY);
        superClass = c;
    }

    public boolean hasOuterClass() {
        checkLevel(HIERARCHY);
        return outerClass != null;
    }

    public SootClass getOuterClass() {
        checkLevel(HIERARCHY);
        if (outerClass == null)
            throw new RuntimeException("no outer class");
        else
            return outerClass;
    }

    public void setOuterClass(SootClass c) {
        checkLevel(HIERARCHY);
        outerClass = c;
    }

    /**
     * Returns the name of this class.
     */

    public String getName() {
        return name;
    }

    String getShortJavaStyleName() {
        if (PackageNamer.has_FixedNames()) {
            if (fixedShortName == null)
                fixedShortName = PackageNamer.get_FixedClassName(name);

            return fixedShortName;
        }

        return shortName;
    }

    String getJavaPackageName() {
        if (PackageNamer.has_FixedNames()) {
            if (fixedPackageName == null)
                fixedPackageName = PackageNamer.get_FixedPackageName(
                        packageName);

            return fixedPackageName;
        }

        return packageName;
    }

    /**
     * Sets the name of this class.
     */

    public void setName(String name) {
        this.name = name.intern();

        shortName = name;
        packageName = "";

        int index = name.lastIndexOf('.');
        if (index > 0) {
            shortName = name.substring(index + 1);
            packageName = name.substring(0, index);
        }

        fixedShortName = null;
        fixedPackageName = null;
    }

    /** Convenience method; returns true if this class is an interface. */
    public boolean isInterface() {
        checkLevel(HIERARCHY);
        return Modifier.isInterface(this.getModifiers());
    }

    /** Returns true if this class is not an interface and not abstract. */
    public boolean isConcrete() {
        return !isInterface() && !isAbstract();
    }

    /** Convenience method; returns true if this class is public. */
    public boolean isPublic() {
        return Modifier.isPublic(this.getModifiers());
    }

    private RefType refType;

    /** Returns the RefType corresponding to this class. */
    public RefType getType() {
        return refType;
    }

    /** Returns the name of this class. */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Convenience method returning true if this class is an application class.
     *
     * @see Scene#getApplicationClasses()
     */
    public boolean isApplicationClass() {
        return Scene.getInstance().getApplicationClasses().contains(this);
    }

    /** Makes this class an application class. */
    public void setApplicationClass() {
        if (isApplicationClass())
            return;
        Chain<SootClass> c = Scene.getInstance().getContainingChain(this);
        if (c != null)
            c.remove(this);
        Scene.getInstance().getApplicationClasses().add(this);

        isPhantom = false;
    }

    /**
     * Convenience method returning true if this class is a library class.
     *
     * @see Scene#getLibraryClasses()
     */
    boolean isLibraryClass() {
        return Scene.getInstance().getLibraryClasses().contains(this);
    }

    /** Makes this class a library class. */
    void setLibraryClass() {
        if (isLibraryClass())
            return;
        Chain<SootClass> c = Scene.getInstance().getContainingChain(this);
        if (c != null)
            c.remove(this);
        Scene.getInstance().getLibraryClasses().add(this);

        isPhantom = false;
    }

    /**
     * Convenience method returning true if this class is a phantom class.
     *
     * @see Scene#getPhantomClasses()
     */
    public boolean isPhantomClass() {
        return Scene.getInstance().getPhantomClasses().contains(this);
    }

    /** Makes this class a phantom class. */
    void setPhantomClass() {
        Chain<SootClass> c = Scene.getInstance().getContainingChain(this);
        if (c != null)
            c.remove(this);
        Scene.getInstance().getPhantomClasses().add(this);
        isPhantom = true;
    }

    /** Convenience method returning true if this class is phantom. */
    public boolean isPhantom() {
        return isPhantom;
    }

    /**
     * Convenience method returning true if this class is private.
     */
    public boolean isPrivate() {
        return Modifier.isPrivate(this.getModifiers());
    }

    /**
     * Convenience method returning true if this class is protected.
     */
    public boolean isProtected() {
        return Modifier.isProtected(this.getModifiers());
    }

    /**
     * Convenience method returning true if this class is abstract.
     */
    public boolean isAbstract() {
        return Modifier.isAbstract(this.getModifiers());
    }

    /**
     * Convenience method returning true if this class is final.
     */
    public boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }

    /**
     * Convenience method returning true if this class is static.
     */
    public boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }

    @Override
    public final int getNumber() {
        return number;
    }

    @Override
    public final void setNumber(int number) {
        this.number = number;
    }

    private int number = 0;

    private static ClassValidator[] validators;

    /**
     * Returns an array containing some validators in order to validate the
     * SootClass
     *
     * @return the array containing validators
     */
    private synchronized static ClassValidator[] getValidators() {
        if (validators == null) {
            validators = new ClassValidator[] { OuterClassValidator.v(),
                    MethodDeclarationValidator.v() };
        }
        return validators;
    }

    /**
     * Validates this SootClass for logical errors. Note that this does not
     * validate the method bodies, only the class structure.
     */
    public void validate() {
        final List<ValidationException> exceptionList = new ArrayList<>();
        validate(exceptionList);
        if (!exceptionList.isEmpty())
            throw exceptionList.get(0);
    }

    /**
     * Validates this SootClass for logical errors. Note that this does not
     * validate the method bodies, only the class structure. All found errors
     * are saved into the given list.
     */
    public void validate(List<ValidationException> exceptionList) {
        final boolean runAllValidators = Options.getInstance().debug()
                || Options.getInstance().validate();
        for (ClassValidator validator : getValidators()) {
            if (!validator.isBasicValidator() && !runAllValidators)
                continue;
            validator.validate(this, exceptionList);
        }
    }

}
