
package jastadd.soot.JastAddJ;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.File;import java.util.*;import jastadd.beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;import java.io.FileNotFoundException;import java.util.Collection;import soot.*;import soot.util.*;import soot.jimple.*;import soot.coffi.ClassFile;import soot.coffi.method_info;import soot.coffi.CONSTANT_Utf8_info;import soot.tagkit.SourceFileTag;import soot.coffi.CoffiMethodSource;

public interface MemberSubstitutor extends Parameterization {
    // Declared in Generics.jrag at line 680

    TypeDecl original();

    // Declared in Generics.jrag at line 681

    void addBodyDecl(BodyDecl b);

    // Declared in Generics.jrag at line 682

    TypeDecl substitute(TypeVariable typeVariable);

    // Declared in Generics.jrag at line 929
 @SuppressWarnings({"unchecked", "cast"})
 HashMap localMethodsSignatureMap();
    // Declared in Generics.jrag at line 944
 @SuppressWarnings({"unchecked", "cast"})
 SimpleSet localFields(String name);
    // Declared in Generics.jrag at line 959
 @SuppressWarnings({"unchecked", "cast"})
 SimpleSet localTypeDecls(String name);
    // Declared in Generics.jrag at line 989
 @SuppressWarnings({"unchecked", "cast"})
 Collection constructors();
}
