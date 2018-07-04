/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class ALocalFieldRef extends PFieldRef
{
    private PLocalName _localName_;
    private TDot _dot_;
    private PFieldSignature _fieldSignature_;

    public ALocalFieldRef()
    {
        // Constructor
    }

    public ALocalFieldRef(
        @SuppressWarnings("hiding") PLocalName _localName_,
        @SuppressWarnings("hiding") TDot _dot_,
        @SuppressWarnings("hiding") PFieldSignature _fieldSignature_)
    {
        // Constructor
        setLocalName(_localName_);

        setDot(_dot_);

        setFieldSignature(_fieldSignature_);

    }

    @Override
    public Object clone()
    {
        return new ALocalFieldRef(
            cloneNode(this._localName_),
            cloneNode(this._dot_),
            cloneNode(this._fieldSignature_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALocalFieldRef(this);
    }

    public PLocalName getLocalName()
    {
        return this._localName_;
    }

    public void setLocalName(PLocalName node)
    {
        if(this._localName_ != null)
        {
            this._localName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._localName_ = node;
    }

    public TDot getDot()
    {
        return this._dot_;
    }

    public void setDot(TDot node)
    {
        if(this._dot_ != null)
        {
            this._dot_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._dot_ = node;
    }

    public PFieldSignature getFieldSignature()
    {
        return this._fieldSignature_;
    }

    public void setFieldSignature(PFieldSignature node)
    {
        if(this._fieldSignature_ != null)
        {
            this._fieldSignature_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._fieldSignature_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._localName_)
            + toString(this._dot_)
            + toString(this._fieldSignature_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._localName_ == child)
        {
            this._localName_ = null;
            return;
        }

        if(this._dot_ == child)
        {
            this._dot_ = null;
            return;
        }

        if(this._fieldSignature_ == child)
        {
            this._fieldSignature_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._localName_ == oldChild)
        {
            setLocalName((PLocalName) newChild);
            return;
        }

        if(this._dot_ == oldChild)
        {
            setDot((TDot) newChild);
            return;
        }

        if(this._fieldSignature_ == oldChild)
        {
            setFieldSignature((PFieldSignature) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
