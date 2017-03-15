
package jastadd.soot.JastAddJ;

public class PostIncExpr extends PostfixExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PostIncExpr clone() throws CloneNotSupportedException {
        PostIncExpr node = (PostIncExpr)super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PostIncExpr copy() {
      try {
          PostIncExpr node = clone();
          if(children != null) node.children = children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PostIncExpr fullCopy() {
        PostIncExpr res = copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Expressions.jrag at line 751


  public soot.Value eval(Body b) { return emitPostfix(b, 1); }

    // Declared in java.ast at line 3
    // Declared in java.ast line 150

    public PostIncExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 150
    public PostIncExpr(Expr p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 17

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 139
    public void setOperand(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getOperand() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getOperandNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in PrettyPrint.jadd at line 372
 @SuppressWarnings({"unchecked", "cast"})     public String printPostOp() {
        ASTNode$State state = state();
        String printPostOp_value = printPostOp_compute();
        return printPostOp_value;
    }

    private String printPostOp_compute() {  return "++";  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
