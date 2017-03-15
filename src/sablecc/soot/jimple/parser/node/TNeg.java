/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TNeg extends Token
{
    public TNeg()
    {
        super.setText("neg");
    }

    public TNeg(int line, int pos)
    {
        super.setText("neg");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNeg(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNeg(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TNeg text.");
    }
}
