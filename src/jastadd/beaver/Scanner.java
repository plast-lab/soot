/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of jastadd.beaver Parser Generator.                       *
 * Copyright (C) 2003,2004 Alexander Demenchuk <alder@softanvil.com>.  *
 * All rights reserved.                                                *
 * See the file "LICENSE" for the terms and conditions for copying,    *
 * distribution and modification of jastadd.beaver.                            *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package jastadd.beaver;

import java.io.IOException;



/**
 * Defines an interface expected by a generated parser.
 */
public abstract class Scanner
{
	public static class Exception extends java.lang.Exception
	{
		public final int line;
		public final int column;
		
		public Exception(String msg)
		{
			this(0, 0, msg);
		}
		
		public Exception(int line, int column, String msg)
		{
			super(msg);
			this.line = line;
			this.column = column;
		}
	}

	public abstract Symbol nextToken() throws IOException, Scanner.Exception;
}
