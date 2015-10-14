/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class ASingleArgList extends PArgList {
    private PImmediate _immediate_;

    public ASingleArgList() {
        // Constructor
    }

    public ASingleArgList(
            @SuppressWarnings("hiding") PImmediate _immediate_) {
        // Constructor
        setImmediate(_immediate_);

    }

    @Override
    public Object clone() {
        return new ASingleArgList(
                cloneNode(this._immediate_));
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseASingleArgList(this);
    }

    public PImmediate getImmediate() {
        return this._immediate_;
    }

    public void setImmediate(PImmediate node) {
        if (this._immediate_ != null) {
            this._immediate_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._immediate_ = node;
    }

    @Override
    public String toString() {
        return ""
                + toString(this._immediate_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child) {
        // Remove child
        if (this._immediate_ == child) {
            this._immediate_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
        // Replace child
        if (this._immediate_ == oldChild) {
            setImmediate((PImmediate) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
