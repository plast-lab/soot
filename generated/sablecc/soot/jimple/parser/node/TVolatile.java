/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TVolatile extends Token
{
    public TVolatile()
    {
        super.setText("volatile");
    }

    public TVolatile(int line, int pos)
    {
        super.setText("volatile");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TVolatile(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTVolatile(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TVolatile text.");
    }
}
