/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AClassNameBaseType extends PBaseType
{
    private PClassName _className_;

    public AClassNameBaseType()
    {
        // Constructor
    }

    public AClassNameBaseType(
        @SuppressWarnings("hiding") PClassName _className_)
    {
        // Constructor
        setClassName(_className_);

    }

    @Override
    public Object clone()
    {
        return new AClassNameBaseType(
            cloneNode(this._className_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAClassNameBaseType(this);
    }

    public PClassName getClassName()
    {
        return this._className_;
    }

    public void setClassName(PClassName node)
    {
        if(this._className_ != null)
        {
            this._className_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._className_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._className_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._className_ == child)
        {
            this._className_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._className_ == oldChild)
        {
            setClassName((PClassName) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
