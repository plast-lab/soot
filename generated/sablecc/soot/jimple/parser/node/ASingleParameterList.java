/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class ASingleParameterList extends PParameterList
{
    private PParameter _parameter_;

    public ASingleParameterList()
    {
        // Constructor
    }

    public ASingleParameterList(
        @SuppressWarnings("hiding") PParameter _parameter_)
    {
        // Constructor
        setParameter(_parameter_);

    }

    @Override
    public Object clone()
    {
        return new ASingleParameterList(
            cloneNode(this._parameter_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleParameterList(this);
    }

    public PParameter getParameter()
    {
        return this._parameter_;
    }

    public void setParameter(PParameter node)
    {
        if(this._parameter_ != null)
        {
            this._parameter_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._parameter_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._parameter_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._parameter_ == child)
        {
            this._parameter_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._parameter_ == oldChild)
        {
            setParameter((PParameter) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
