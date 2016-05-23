/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TNewarray extends Token
{
    public TNewarray()
    {
        super.setText("newarray");
    }

    public TNewarray(int line, int pos)
    {
        super.setText("newarray");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNewarray(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNewarray(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TNewarray text.");
    }
}
