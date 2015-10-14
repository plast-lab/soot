/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 Jennifer Lhotak
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package ca.mcgill.sable.soot.launching;

import java.util.EventObject;

/**
 * An event to contain output from Soot to be sent to Soot Output
 * View.
 */
public class SootOutputEvent extends EventObject {

    private int event_type;
    private String textToAppend;

    public SootOutputEvent(Object eventSource, int type) {
        super(eventSource);
        setEventType(type);
    }

    public String getTextToAppend() {
        return textToAppend;
    }

    public void setTextToAppend(String text) {
        textToAppend = text;
    }

    public int getEventType() {
        return event_type;
    }

    public void setEventType(int type) {
        event_type = type;
    }
}
