/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class ANullBaseTypeNoName extends PBaseTypeNoName {
    private TNullType _nullType_;

    public ANullBaseTypeNoName() {
        // Constructor
    }

    public ANullBaseTypeNoName(
            @SuppressWarnings("hiding") TNullType _nullType_) {
        // Constructor
        setNullType(_nullType_);

    }

    @Override
    public Object clone() {
        return new ANullBaseTypeNoName(
                cloneNode(this._nullType_));
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseANullBaseTypeNoName(this);
    }

    public TNullType getNullType() {
        return this._nullType_;
    }

    public void setNullType(TNullType node) {
        if (this._nullType_ != null) {
            this._nullType_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._nullType_ = node;
    }

    @Override
    public String toString() {
        return ""
                + toString(this._nullType_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child) {
        // Remove child
        if (this._nullType_ == child) {
            this._nullType_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
        // Replace child
        if (this._nullType_ == oldChild) {
            setNullType((TNullType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
