/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class TCmpl extends Token
{
    public TCmpl()
    {
        super.setText("cmpl");
    }

    public TCmpl(int line, int pos)
    {
        super.setText("cmpl");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TCmpl(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTCmpl(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TCmpl text.");
    }
}
