/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TWith extends Token
{
    public TWith()
    {
        super.setText("with");
    }

    public TWith(int line, int pos)
    {
        super.setText("with");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TWith(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTWith(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TWith text.");
    }
}
