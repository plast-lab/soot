/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TSpecialinvoke extends Token {
    public TSpecialinvoke() {
        super.setText("specialinvoke");
    }

    public TSpecialinvoke(int line, int pos) {
        super.setText("specialinvoke");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone() {
        return new TSpecialinvoke(getLine(), getPos());
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseTSpecialinvoke(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text) {
        throw new RuntimeException("Cannot change TSpecialinvoke text.");
    }
}
