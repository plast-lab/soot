/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class AImplementsClause extends PImplementsClause
{
    private TImplements _implements_;
    private PClassNameList _classNameList_;

    public AImplementsClause()
    {
        // Constructor
    }

    public AImplementsClause(
        @SuppressWarnings("hiding") TImplements _implements_,
        @SuppressWarnings("hiding") PClassNameList _classNameList_)
    {
        // Constructor
        setImplements(_implements_);

        setClassNameList(_classNameList_);

    }

    @Override
    public Object clone()
    {
        return new AImplementsClause(
            cloneNode(this._implements_),
            cloneNode(this._classNameList_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAImplementsClause(this);
    }

    public TImplements getImplements()
    {
        return this._implements_;
    }

    public void setImplements(TImplements node)
    {
        if(this._implements_ != null)
        {
            this._implements_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._implements_ = node;
    }

    public PClassNameList getClassNameList()
    {
        return this._classNameList_;
    }

    public void setClassNameList(PClassNameList node)
    {
        if(this._classNameList_ != null)
        {
            this._classNameList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._classNameList_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._implements_)
            + toString(this._classNameList_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._implements_ == child)
        {
            this._implements_ = null;
            return;
        }

        if(this._classNameList_ == child)
        {
            this._classNameList_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._implements_ == oldChild)
        {
            setImplements((TImplements) newChild);
            return;
        }

        if(this._classNameList_ == oldChild)
        {
            setClassNameList((PClassNameList) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
