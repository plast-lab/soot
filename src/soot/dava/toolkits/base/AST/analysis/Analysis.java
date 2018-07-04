/* Soot - a J*va Optimization Framework
 * Copyright (C) 2005 Nomair A. Naeem
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

package soot.dava.toolkits.base.AST.analysis;


import soot.*;
import soot.jimple.*;
import soot.dava.internal.AST.*;
import soot.dava.internal.javaRep.*;

public interface Analysis{

    void caseASTMethodNode(ASTMethodNode node);
    void caseASTSynchronizedBlockNode(ASTSynchronizedBlockNode node);
    void caseASTLabeledBlockNode(ASTLabeledBlockNode node);
    void caseASTUnconditionalLoopNode(ASTUnconditionalLoopNode node);
    void caseASTSwitchNode(ASTSwitchNode node);
    void caseASTIfNode(ASTIfNode node);
    void caseASTIfElseNode(ASTIfElseNode node);
    void caseASTWhileNode(ASTWhileNode node);
    void caseASTForLoopNode(ASTForLoopNode node);
    void caseASTDoWhileNode(ASTDoWhileNode node);
    void caseASTTryNode(ASTTryNode node);
    void caseASTStatementSequenceNode(ASTStatementSequenceNode node);
    
    void caseASTUnaryCondition(ASTUnaryCondition uc);
    void caseASTBinaryCondition(ASTBinaryCondition bc);
    void caseASTAndCondition(ASTAndCondition ac);
    void caseASTOrCondition(ASTOrCondition oc);
    

    void caseType(Type t);
    void caseDefinitionStmt(DefinitionStmt s);
    void caseReturnStmt(ReturnStmt s);
    void caseInvokeStmt(InvokeStmt s);
    void caseThrowStmt(ThrowStmt s);
    void caseDVariableDeclarationStmt(DVariableDeclarationStmt s);
    void caseStmt(Stmt s);
    void caseValue(Value v);
    void caseExpr(Expr e);
    void caseRef(Ref r);
    void caseBinopExpr(BinopExpr be);
    void caseUnopExpr(UnopExpr ue);
    void caseNewArrayExpr(NewArrayExpr nae);
    void caseNewMultiArrayExpr(NewMultiArrayExpr nmae);
    void caseInstanceOfExpr(InstanceOfExpr ioe);
    void caseInvokeExpr(InvokeExpr ie);
    void caseInstanceInvokeExpr(InstanceInvokeExpr iie);
    void caseCastExpr(CastExpr ce);
    void caseArrayRef(ArrayRef ar);
    void caseInstanceFieldRef(InstanceFieldRef ifr);
    void caseStaticFieldRef(StaticFieldRef sfr);
}
