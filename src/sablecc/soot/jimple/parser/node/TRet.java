/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TRet extends Token
{
    public TRet()
    {
        super.setText("ret");
    }

    public TRet(int line, int pos)
    {
        super.setText("ret");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TRet(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTRet(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TRet text.");
    }
}
