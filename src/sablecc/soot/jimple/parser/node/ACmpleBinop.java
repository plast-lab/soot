/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class ACmpleBinop extends PBinop
{
    private TCmple _cmple_;

    public ACmpleBinop()
    {
        // Constructor
    }

    public ACmpleBinop(
        @SuppressWarnings("hiding") TCmple _cmple_)
    {
        // Constructor
        setCmple(_cmple_);

    }

    @Override
    public Object clone()
    {
        return new ACmpleBinop(
            cloneNode(this._cmple_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACmpleBinop(this);
    }

    public TCmple getCmple()
    {
        return this._cmple_;
    }

    public void setCmple(TCmple node)
    {
        if(this._cmple_ != null)
        {
            this._cmple_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._cmple_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._cmple_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._cmple_ == child)
        {
            this._cmple_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._cmple_ == oldChild)
        {
            setCmple((TCmple) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
