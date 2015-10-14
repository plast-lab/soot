/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AClzzConstant extends PConstant {
    private TClass _id_;
    private TStringConstant _stringConstant_;

    public AClzzConstant() {
        // Constructor
    }

    public AClzzConstant(
            @SuppressWarnings("hiding") TClass _id_,
            @SuppressWarnings("hiding") TStringConstant _stringConstant_) {
        // Constructor
        setId(_id_);

        setStringConstant(_stringConstant_);

    }

    @Override
    public Object clone() {
        return new AClzzConstant(
                cloneNode(this._id_),
                cloneNode(this._stringConstant_));
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseAClzzConstant(this);
    }

    public TClass getId() {
        return this._id_;
    }

    public void setId(TClass node) {
        if (this._id_ != null) {
            this._id_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public TStringConstant getStringConstant() {
        return this._stringConstant_;
    }

    public void setStringConstant(TStringConstant node) {
        if (this._stringConstant_ != null) {
            this._stringConstant_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._stringConstant_ = node;
    }

    @Override
    public String toString() {
        return ""
                + toString(this._id_)
                + toString(this._stringConstant_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child) {
        // Remove child
        if (this._id_ == child) {
            this._id_ = null;
            return;
        }

        if (this._stringConstant_ == child) {
            this._stringConstant_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
        // Replace child
        if (this._id_ == oldChild) {
            setId((TClass) newChild);
            return;
        }

        if (this._stringConstant_ == oldChild) {
            setStringConstant((TStringConstant) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
