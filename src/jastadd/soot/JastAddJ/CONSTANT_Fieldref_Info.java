
package jastadd.soot.JastAddJ;

public class CONSTANT_Fieldref_Info extends CONSTANT_Info {
    // Declared in BytecodeCONSTANT.jrag at line 76

    public int class_index;

    // Declared in BytecodeCONSTANT.jrag at line 77

    public int name_and_type_index;

    // Declared in BytecodeCONSTANT.jrag at line 79


    public CONSTANT_Fieldref_Info(BytecodeParser parser) {
      super(parser);
      class_index = p.u2();
      name_and_type_index = p.u2();
    }

    // Declared in BytecodeCONSTANT.jrag at line 85


    public String toString() {
      return "FieldRefInfo: " + p.constantPool[class_index] + " "
        + p.constantPool[name_and_type_index];
    }


}
