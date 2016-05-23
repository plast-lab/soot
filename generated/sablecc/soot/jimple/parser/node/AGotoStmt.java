/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AGotoStmt extends PGotoStmt
{
    private TGoto _goto_;
    private PLabelName _labelName_;
    private TSemicolon _semicolon_;

    public AGotoStmt()
    {
        // Constructor
    }

    public AGotoStmt(
        @SuppressWarnings("hiding") TGoto _goto_,
        @SuppressWarnings("hiding") PLabelName _labelName_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setGoto(_goto_);

        setLabelName(_labelName_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AGotoStmt(
            cloneNode(this._goto_),
            cloneNode(this._labelName_),
            cloneNode(this._semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGotoStmt(this);
    }

    public TGoto getGoto()
    {
        return this._goto_;
    }

    public void setGoto(TGoto node)
    {
        if(this._goto_ != null)
        {
            this._goto_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._goto_ = node;
    }

    public PLabelName getLabelName()
    {
        return this._labelName_;
    }

    public void setLabelName(PLabelName node)
    {
        if(this._labelName_ != null)
        {
            this._labelName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._labelName_ = node;
    }

    public TSemicolon getSemicolon()
    {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(this._semicolon_ != null)
        {
            this._semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._goto_)
            + toString(this._labelName_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._goto_ == child)
        {
            this._goto_ = null;
            return;
        }

        if(this._labelName_ == child)
        {
            this._labelName_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._goto_ == oldChild)
        {
            setGoto((TGoto) newChild);
            return;
        }

        if(this._labelName_ == oldChild)
        {
            setLabelName((PLabelName) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
