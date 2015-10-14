/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class AReturnStatement extends PStatement {
    private TReturn _return_;
    private PImmediate _immediate_;
    private TSemicolon _semicolon_;

    public AReturnStatement() {
        // Constructor
    }

    public AReturnStatement(
            @SuppressWarnings("hiding") TReturn _return_,
            @SuppressWarnings("hiding") PImmediate _immediate_,
            @SuppressWarnings("hiding") TSemicolon _semicolon_) {
        // Constructor
        setReturn(_return_);

        setImmediate(_immediate_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone() {
        return new AReturnStatement(
                cloneNode(this._return_),
                cloneNode(this._immediate_),
                cloneNode(this._semicolon_));
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseAReturnStatement(this);
    }

    public TReturn getReturn() {
        return this._return_;
    }

    public void setReturn(TReturn node) {
        if (this._return_ != null) {
            this._return_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._return_ = node;
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

    public TSemicolon getSemicolon() {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node) {
        if (this._semicolon_ != null) {
            this._semicolon_.parent(null);
        }

        if (node != null) {
            if (node.parent() != null) {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString() {
        return ""
                + toString(this._return_)
                + toString(this._immediate_)
                + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child) {
        // Remove child
        if (this._return_ == child) {
            this._return_ = null;
            return;
        }

        if (this._immediate_ == child) {
            this._immediate_ = null;
            return;
        }

        if (this._semicolon_ == child) {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
        // Replace child
        if (this._return_ == oldChild) {
            setReturn((TReturn) newChild);
            return;
        }

        if (this._immediate_ == oldChild) {
            setImmediate((PImmediate) newChild);
            return;
        }

        if (this._semicolon_ == oldChild) {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
