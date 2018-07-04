/* This file was generated by SableCC (http://www.sablecc.org/). */

package sablecc.soot.jimple.parser.node;

import sablecc.soot.jimple.parser.analysis.*;

@SuppressWarnings("nls")
public final class TExitmonitor extends Token
{
    public TExitmonitor()
    {
        super.setText("exitmonitor");
    }

    public TExitmonitor(int line, int pos)
    {
        super.setText("exitmonitor");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TExitmonitor(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTExitmonitor(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TExitmonitor text.");
    }
}
