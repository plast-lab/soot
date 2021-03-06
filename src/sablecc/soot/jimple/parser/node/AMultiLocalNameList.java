/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class AMultiLocalNameList extends PLocalNameList
{
    private PLocalName _localName_;
    private TComma _comma_;
    private PLocalNameList _localNameList_;

    public AMultiLocalNameList()
    {
        // Constructor
    }

    public AMultiLocalNameList(
        @SuppressWarnings("hiding") PLocalName _localName_,
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PLocalNameList _localNameList_)
    {
        // Constructor
        setLocalName(_localName_);

        setComma(_comma_);

        setLocalNameList(_localNameList_);

    }

    @Override
    public Object clone()
    {
        return new AMultiLocalNameList(
            cloneNode(this._localName_),
            cloneNode(this._comma_),
            cloneNode(this._localNameList_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultiLocalNameList(this);
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

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public PLocalNameList getLocalNameList()
    {
        return this._localNameList_;
    }

    public void setLocalNameList(PLocalNameList node)
    {
        if(this._localNameList_ != null)
        {
            this._localNameList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._localNameList_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._localName_)
            + toString(this._comma_)
            + toString(this._localNameList_);
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

        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._localNameList_ == child)
        {
            this._localNameList_ = null;
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

        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._localNameList_ == oldChild)
        {
            setLocalNameList((PLocalNameList) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
