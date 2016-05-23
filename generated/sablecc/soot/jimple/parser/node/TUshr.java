/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TUshr extends Token
{
    public TUshr()
    {
        super.setText(">>>");
    }

    public TUshr(int line, int pos)
    {
        super.setText(">>>");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TUshr(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTUshr(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TUshr text.");
    }
}
