/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TFloat extends Token {
    public TFloat() {
        super.setText("float");
    }

    public TFloat(int line, int pos) {
        super.setText("float");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone() {
        return new TFloat(getLine(), getPos());
    }

    public void apply(Switch sw) {
        ((Analysis) sw).caseTFloat(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text) {
        throw new RuntimeException("Cannot change TFloat text.");
    }
}
