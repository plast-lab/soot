/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class TLookupswitch extends Token
{
    public TLookupswitch()
    {
        super.setText("lookupswitch");
    }

    public TLookupswitch(int line, int pos)
    {
        super.setText("lookupswitch");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLookupswitch(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLookupswitch(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLookupswitch text.");
    }
}
