/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class AInvokeExpression extends PExpression
{
    private PInvokeExpr _invokeExpr_;

    public AInvokeExpression()
    {
        // Constructor
    }

    public AInvokeExpression(
        @SuppressWarnings("hiding") PInvokeExpr _invokeExpr_)
    {
        // Constructor
        setInvokeExpr(_invokeExpr_);

    }

    @Override
    public Object clone()
    {
        return new AInvokeExpression(
            cloneNode(this._invokeExpr_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInvokeExpression(this);
    }

    public PInvokeExpr getInvokeExpr()
    {
        return this._invokeExpr_;
    }

    public void setInvokeExpr(PInvokeExpr node)
    {
        if(this._invokeExpr_ != null)
        {
            this._invokeExpr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._invokeExpr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._invokeExpr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._invokeExpr_ == child)
        {
            this._invokeExpr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._invokeExpr_ == oldChild)
        {
            setInvokeExpr((PInvokeExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
