
package jastadd.soot.JastAddJ;

public class CONSTANT_Class_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 12

    public int name_index;

    // Declared in BytecodeCONSTANT.jrag at line 14


    public CONSTANT_Class_Info(BytecodeParser parser) {
      super(parser);
      name_index = p.u2();
    }

    // Declared in BytecodeCONSTANT.jrag at line 19


    public String toString() {
      return "ClassInfo: " + name();
    }

    // Declared in BytecodeCONSTANT.jrag at line 23


    public String name() {
      String name = ((CONSTANT_Utf8_Info) this.p.constantPool[name_index]).string();
      //name = name.replaceAll("\\/", ".");
      name = name.replace('/', '.');
      return name;
    }

    // Declared in BytecodeCONSTANT.jrag at line 30


    public String simpleName() {
      String name = name();
      //name = name.replace('$', '.');
      int pos = name.lastIndexOf('.');
      return name.substring(pos + 1, name.length());
    }

    // Declared in BytecodeCONSTANT.jrag at line 37


    public String packageDecl() {
      String name = name();
      //name = name.replace('$', '.');
      int pos = name.lastIndexOf('.');
      if(pos == -1)
        return "";
      return name.substring(0, pos);
    }

    // Declared in BytecodeCONSTANT.jrag at line 46


    public Access access() {
      String name = name();
      int pos = name.lastIndexOf('.');
      String typeName = name.substring(pos + 1, name.length());
      String packageName = pos == -1 ? "" : name.substring(0, pos);
      if(typeName.indexOf('$') != -1)
        return new BytecodeTypeAccess(packageName, typeName);
      else
        return new TypeAccess(packageName, typeName);
    }


}
