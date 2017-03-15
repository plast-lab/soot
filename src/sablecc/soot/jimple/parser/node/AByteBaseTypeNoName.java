/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AByteBaseTypeNoName extends PBaseTypeNoName
{
    private TByte _byte_;

    public AByteBaseTypeNoName()
    {
        // Constructor
    }

    public AByteBaseTypeNoName(
        @SuppressWarnings("hiding") TByte _byte_)
    {
        // Constructor
        setByte(_byte_);

    }

    @Override
    public Object clone()
    {
        return new AByteBaseTypeNoName(
            cloneNode(this._byte_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAByteBaseTypeNoName(this);
    }

    public TByte getByte()
    {
        return this._byte_;
    }

    public void setByte(TByte node)
    {
        if(this._byte_ != null)
        {
            this._byte_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._byte_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._byte_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._byte_ == child)
        {
            this._byte_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._byte_ == oldChild)
        {
            setByte((TByte) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
