/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-1999 Raja Vallee-Rai
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

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */





package soot.jimple;

import soot.util.*;

public interface StmtSwitch extends Switch
{
    void caseBreakpointStmt(BreakpointStmt stmt);
    void caseInvokeStmt(InvokeStmt stmt);
    void caseAssignStmt(AssignStmt stmt);
    void caseIdentityStmt(IdentityStmt stmt);
    void caseEnterMonitorStmt(EnterMonitorStmt stmt);
    void caseExitMonitorStmt(ExitMonitorStmt stmt);
    void caseGotoStmt(GotoStmt stmt);
    void caseIfStmt(IfStmt stmt);
    void caseLookupSwitchStmt(LookupSwitchStmt stmt);
    void caseNopStmt(NopStmt stmt);
    void caseRetStmt(RetStmt stmt);
    void caseReturnStmt(ReturnStmt stmt);
    void caseReturnVoidStmt(ReturnVoidStmt stmt);
    void caseTableSwitchStmt(TableSwitchStmt stmt);
    void caseThrowStmt(ThrowStmt stmt);
    void defaultCase(Object obj);
}
