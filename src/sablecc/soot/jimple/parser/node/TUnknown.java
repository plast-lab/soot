/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TUnknown extends Token
{
    public TUnknown()
    {
        super.setText("unknown");
    }

    public TUnknown(int line, int pos)
    {
        super.setText("unknown");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TUnknown(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTUnknown(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TUnknown text.");
    }
}
