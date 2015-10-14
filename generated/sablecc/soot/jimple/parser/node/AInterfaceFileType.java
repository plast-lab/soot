/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AInterfaceFileType extends PFileType {
    private TInterface _interface_;

    public AInterfaceFileType() {
        // Constructor
    }

    public AInterfaceFileType(
            @SuppressWarnings("hiding") TInterface _interface_) {
        // Constructor
        setInterface(_interface_);

    }

    @Override
    public Object clone() {
        return new AInterfaceFileType(
                cloneNode(this._interface_));
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseAInterfaceFileType(this);
    }

    public TInterface getInterface() {
        return this._interface_;
    }

    public void setInterface(TInterface node) {
        if (this._interface_ != null) {
            this._interface_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._interface_ = node;
    }

    @Override
    public String toString() {
        return ""
                + toString(this._interface_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child) {
        // Remove child
        if (this._interface_ == child) {
            this._interface_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
        // Replace child
        if (this._interface_ == oldChild) {
            setInterface((TInterface) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
