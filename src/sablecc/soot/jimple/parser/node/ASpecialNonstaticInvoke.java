/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class ASpecialNonstaticInvoke extends PNonstaticInvoke
{
    private TSpecialinvoke _specialinvoke_;

    public ASpecialNonstaticInvoke()
    {
        // Constructor
    }

    public ASpecialNonstaticInvoke(
        @SuppressWarnings("hiding") TSpecialinvoke _specialinvoke_)
    {
        // Constructor
        setSpecialinvoke(_specialinvoke_);

    }

    @Override
    public Object clone()
    {
        return new ASpecialNonstaticInvoke(
            cloneNode(this._specialinvoke_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASpecialNonstaticInvoke(this);
    }

    public TSpecialinvoke getSpecialinvoke()
    {
        return this._specialinvoke_;
    }

    public void setSpecialinvoke(TSpecialinvoke node)
    {
        if(this._specialinvoke_ != null)
        {
            this._specialinvoke_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._specialinvoke_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._specialinvoke_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._specialinvoke_ == child)
        {
            this._specialinvoke_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._specialinvoke_ == oldChild)
        {
            setSpecialinvoke((TSpecialinvoke) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
