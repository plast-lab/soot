
package jastadd.soot.JastAddJ;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import jastadd.beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import soot.tagkit.SourceFileTag;import soot.coffi.CoffiMethodSource;

public interface Variable {
    // Declared in VariableDeclaration.jrag at line 12

    String name();

    // Declared in VariableDeclaration.jrag at line 13

    TypeDecl type();

    // Declared in VariableDeclaration.jrag at line 15

    // 4.5.3
    boolean isClassVariable();

    // Declared in VariableDeclaration.jrag at line 16

    boolean isInstanceVariable();

    // Declared in VariableDeclaration.jrag at line 17

    boolean isMethodParameter();

    // Declared in VariableDeclaration.jrag at line 18

    boolean isConstructorParameter();

    // Declared in VariableDeclaration.jrag at line 19

    boolean isExceptionHandlerParameter();

    // Declared in VariableDeclaration.jrag at line 20

    boolean isLocalVariable();

    // Declared in VariableDeclaration.jrag at line 22

    // 4.5.4
    boolean isFinal();

    // Declared in VariableDeclaration.jrag at line 23

    boolean isVolatile();

    // Declared in VariableDeclaration.jrag at line 25


    boolean isBlank();

    // Declared in VariableDeclaration.jrag at line 26

    boolean isStatic();

    // Declared in VariableDeclaration.jrag at line 27

    boolean isSynthetic();

    // Declared in VariableDeclaration.jrag at line 29


    TypeDecl hostType();

    // Declared in VariableDeclaration.jrag at line 31


    Expr getInit();

    // Declared in VariableDeclaration.jrag at line 32

    boolean hasInit();

    // Declared in VariableDeclaration.jrag at line 34


    Constant constant();

    // Declared in VariableDeclaration.jrag at line 36


    Modifiers getModifiers();

    // Declared in Generics.jrag at line 1281
 @SuppressWarnings({"unchecked", "cast"})
 Variable sourceVariableDecl();
}
