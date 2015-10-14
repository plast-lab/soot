/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

/**
 * @production VariableArityParameterDeclarationSubstituted : {@link VariableArityParameterDeclaration} ::= <span class="component">&lt;Original:VariableArityParameterDeclaration&gt;</span>;
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.ast:34
 */
public class VariableArityParameterDeclarationSubstituted extends VariableArityParameterDeclaration implements Cloneable {
    /**
     * @apilevel internal
     */
    protected VariableArityParameterDeclaration tokenVariableArityParameterDeclaration_Original;

    /**
     * @ast method
     */
    public VariableArityParameterDeclarationSubstituted() {
        super();


    }

    /**
     * @ast method
     */
    public VariableArityParameterDeclarationSubstituted(Modifiers p0, Access p1, String p2, VariableArityParameterDeclaration p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setOriginal(p3);
    }

    /**
     * @ast method
     */
    public VariableArityParameterDeclarationSubstituted(Modifiers p0, Access p1, beaver.Symbol p2, VariableArityParameterDeclaration p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setOriginal(p3);
    }

    /**
     * @apilevel low-level
     */
    public void flushCache() {
        super.flushCache();
    }

    /**
     * @apilevel internal
     */
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }

    /**
     * @apilevel internal
     */
    @SuppressWarnings({"unchecked", "cast"})
    public VariableArityParameterDeclarationSubstituted clone() throws CloneNotSupportedException {
        VariableArityParameterDeclarationSubstituted node = (VariableArityParameterDeclarationSubstituted) super.clone();
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }

    /**
     * @apilevel internal
     */
    @SuppressWarnings({"unchecked", "cast"})
    public VariableArityParameterDeclarationSubstituted copy() {
        try {
            VariableArityParameterDeclarationSubstituted node = clone();
            node.parent = null;
            if (children != null)
                node.children = children.clone();
            return node;
        } catch (CloneNotSupportedException e) {
            throw new Error("Error: clone not supported for " +
                    getClass().getName());
        }
    }

    /**
     * Create a deep copy of the AST subtree at this node.
     * The copy is dangling, i.e. has no parent.
     *
     * @return dangling copy of the subtree at this node
     * @apilevel low-level
     */
    @SuppressWarnings({"unchecked", "cast"})
    public VariableArityParameterDeclarationSubstituted fullCopy() {
        VariableArityParameterDeclarationSubstituted tree = copy();
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                ASTNode child = children[i];
                if (child != null) {
                    child = child.fullCopy();
                    tree.setChild(child, i);
                }
            }
        }
        return tree;
    }

    /**
     * Initializes the child array to the correct size.
     * Initializes List and Opt nta children.
     *
     * @apilevel internal
     * @ast method
     * @ast method
     */
    public void init$Children() {
        children = new ASTNode[2];
    }

    /**
     * @apilevel low-level
     * @ast method
     */
    protected int numChildren() {
        return 2;
    }

    /**
     * @apilevel internal
     * @ast method
     */
    public boolean mayHaveRewrite() {
        return false;
    }

    /**
     * Retrieves the Modifiers child.
     *
     * @return The current node used as the Modifiers child.
     * @apilevel high-level
     * @ast method
     */
    public Modifiers getModifiers() {
        return (Modifiers) getChild(0);
    }

    /**
     * Replaces the Modifiers child.
     *
     * @param node The new node to replace the Modifiers child.
     * @apilevel high-level
     * @ast method
     */
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    /**
     * Retrieves the Modifiers child.
     * <p><em>This method does not invoke AST transformations.</em></p>
     *
     * @return The current node used as the Modifiers child.
     * @apilevel low-level
     * @ast method
     */
    public Modifiers getModifiersNoTransform() {
        return (Modifiers) getChildNoTransform(0);
    }

    /**
     * Retrieves the TypeAccess child.
     *
     * @return The current node used as the TypeAccess child.
     * @apilevel high-level
     * @ast method
     */
    public Access getTypeAccess() {
        return (Access) getChild(1);
    }

    /**
     * Replaces the TypeAccess child.
     *
     * @param node The new node to replace the TypeAccess child.
     * @apilevel high-level
     * @ast method
     */
    public void setTypeAccess(Access node) {
        setChild(node, 1);
    }

    /**
     * Retrieves the TypeAccess child.
     * <p><em>This method does not invoke AST transformations.</em></p>
     *
     * @return The current node used as the TypeAccess child.
     * @apilevel low-level
     * @ast method
     */
    public Access getTypeAccessNoTransform() {
        return (Access) getChildNoTransform(1);
    }

    /**
     * Replaces the lexeme ID.
     *
     * @param value The new value for the lexeme ID.
     * @apilevel high-level
     * @ast method
     */
    public void setID(String value) {
        tokenString_ID = value;
    }

    /**
     * Retrieves the value for the lexeme ID.
     *
     * @return The value for the lexeme ID.
     * @apilevel high-level
     * @ast method
     */
    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    /**
     * JastAdd-internal setter for lexeme ID using the Beaver parser.
     *
     * @apilevel internal
     * @ast method
     */
    public void setID(beaver.Symbol symbol) {
        if (symbol.value != null && !(symbol.value instanceof String))
            throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String) symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }
    /**
     * @apilevel internal
     * @ast method
     *
     */

    /**
     * Retrieves the value for the lexeme Original.
     *
     * @return The value for the lexeme Original.
     * @apilevel high-level
     * @ast method
     */
    public VariableArityParameterDeclaration getOriginal() {
        return tokenVariableArityParameterDeclaration_Original;
    }

    /**
     * Replaces the lexeme Original.
     *
     * @param value The new value for the lexeme Original.
     * @apilevel high-level
     * @ast method
     */
    public void setOriginal(VariableArityParameterDeclaration value) {
        tokenVariableArityParameterDeclaration_Original = value;
    }

    /**
     * @apilevel internal
     */
    public ASTNode rewriteTo() {
        return super.rewriteTo();
    }
}
