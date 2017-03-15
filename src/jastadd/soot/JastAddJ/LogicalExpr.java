
package jastadd.soot.JastAddJ;

public abstract class LogicalExpr extends Binary implements Cloneable {
    public void flushCache() {
        super.flushCache();
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public LogicalExpr clone() throws CloneNotSupportedException {
        LogicalExpr node = (LogicalExpr)super.clone();
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in TypeCheck.jrag at line 212


  // 15.23, 15.24
  public void typeCheck() {
    if(!getLeftOperand().type().isBoolean())
      error(getLeftOperand().type().typeName() + " is not boolean");
    if(!getRightOperand().type().isBoolean())
      error(getRightOperand().type().typeName() + " is not boolean");
  }

    // Declared in BooleanExpressions.jrag at line 104

  public soot.Value eval(Body b) { return emitBooleanCondition(b); }

    // Declared in java.ast at line 3
    // Declared in java.ast line 174

    public LogicalExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 174
    public LogicalExpr(Expr p0, Expr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in java.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 18

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 153
    public void setLeftOperand(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getLeftOperand() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getLeftOperandNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 153
    public void setRightOperand(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getRightOperand() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getRightOperandNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 347
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        if(type_computed) {
            return type_value;
        }
        ASTNode$State state = state();
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        type_value = type_compute();
        if(isFinal && num == state().boundariesCrossed)
            type_computed = true;
        return type_value;
    }

    private TypeDecl type_compute() {  return typeBoolean();  }

    // Declared in BooleanExpressions.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public boolean definesLabel() {
        ASTNode$State state = state();
        boolean definesLabel_value = definesLabel_compute();
        return definesLabel_value;
    }

    private boolean definesLabel_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
