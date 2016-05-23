/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AArrayReference extends PReference
{
    private PArrayRef _arrayRef_;

    public AArrayReference()
    {
        // Constructor
    }

    public AArrayReference(
        @SuppressWarnings("hiding") PArrayRef _arrayRef_)
    {
        // Constructor
        setArrayRef(_arrayRef_);

    }

    @Override
    public Object clone()
    {
        return new AArrayReference(
            cloneNode(this._arrayRef_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAArrayReference(this);
    }

    public PArrayRef getArrayRef()
    {
        return this._arrayRef_;
    }

    public void setArrayRef(PArrayRef node)
    {
        if(this._arrayRef_ != null)
        {
            this._arrayRef_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._arrayRef_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._arrayRef_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._arrayRef_ == child)
        {
            this._arrayRef_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._arrayRef_ == oldChild)
        {
            setArrayRef((PArrayRef) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
