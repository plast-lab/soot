/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class AStrictfpModifier extends PModifier
{
    private TStrictfp _strictfp_;

    public AStrictfpModifier()
    {
        // Constructor
    }

    public AStrictfpModifier(
        @SuppressWarnings("hiding") TStrictfp _strictfp_)
    {
        // Constructor
        setStrictfp(_strictfp_);

    }

    @Override
    public Object clone()
    {
        return new AStrictfpModifier(
            cloneNode(this._strictfp_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStrictfpModifier(this);
    }

    public TStrictfp getStrictfp()
    {
        return this._strictfp_;
    }

    public void setStrictfp(TStrictfp node)
    {
        if(this._strictfp_ != null)
        {
            this._strictfp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._strictfp_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._strictfp_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._strictfp_ == child)
        {
            this._strictfp_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._strictfp_ == oldChild)
        {
            setStrictfp((TStrictfp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
