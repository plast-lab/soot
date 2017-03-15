
package jastadd.soot.JastAddJ;

public class IdUse extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public IdUse clone() throws CloneNotSupportedException {
        IdUse node = (IdUse)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IdUse copy() {
      try {
          IdUse node = clone();
          if(children != null) node.children = children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public IdUse fullCopy() {
        IdUse res = copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in java.ast at line 3
    // Declared in java.ast line 95

    public IdUse() {
        super();

        is$Final(true);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 95
    public IdUse(String p0) {
        setID(p0);
        is$Final(true);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 95
    public IdUse(jastadd.beaver.Symbol p0) {
        setID(p0);
        is$Final(true);
    }

    // Declared in java.ast at line 22


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 25

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 95
    protected String tokenString_ID;

    // Declared in java.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 6

    public int IDstart;

    // Declared in java.ast at line 7

    public int IDend;

    // Declared in java.ast at line 8

    public void setID(jastadd.beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
