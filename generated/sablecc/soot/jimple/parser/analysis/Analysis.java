/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.analysis;

import soot.jimple.parser.node.*;

public interface Analysis extends Switch {
    Object getIn(Node node);

    void setIn(Node node, Object o);

    Object getOut(Node node);

    void setOut(Node node, Object o);

    void caseStart(Start node);

    void caseAFile(AFile node);

    void caseAAbstractModifier(AAbstractModifier node);

    void caseAFinalModifier(AFinalModifier node);

    void caseANativeModifier(ANativeModifier node);

    void caseAPublicModifier(APublicModifier node);

    void caseAProtectedModifier(AProtectedModifier node);

    void caseAPrivateModifier(APrivateModifier node);

    void caseAStaticModifier(AStaticModifier node);

    void caseASynchronizedModifier(ASynchronizedModifier node);

    void caseATransientModifier(ATransientModifier node);

    void caseAVolatileModifier(AVolatileModifier node);

    void caseAStrictfpModifier(AStrictfpModifier node);

    void caseAEnumModifier(AEnumModifier node);

    void caseAAnnotationModifier(AAnnotationModifier node);

    void caseAClassFileType(AClassFileType node);

    void caseAInterfaceFileType(AInterfaceFileType node);

    void caseAExtendsClause(AExtendsClause node);

    void caseAImplementsClause(AImplementsClause node);

    void caseAFileBody(AFileBody node);

    void caseASingleNameList(ASingleNameList node);

    void caseAMultiNameList(AMultiNameList node);

    void caseAClassNameSingleClassNameList(AClassNameSingleClassNameList node);

    void caseAClassNameMultiClassNameList(AClassNameMultiClassNameList node);

    void caseAFieldMember(AFieldMember node);

    void caseAMethodMember(AMethodMember node);

    void caseAVoidType(AVoidType node);

    void caseANovoidType(ANovoidType node);

    void caseASingleParameterList(ASingleParameterList node);

    void caseAMultiParameterList(AMultiParameterList node);

    void caseAParameter(AParameter node);

    void caseAThrowsClause(AThrowsClause node);

    void caseABooleanBaseTypeNoName(ABooleanBaseTypeNoName node);

    void caseAByteBaseTypeNoName(AByteBaseTypeNoName node);

    void caseACharBaseTypeNoName(ACharBaseTypeNoName node);

    void caseAShortBaseTypeNoName(AShortBaseTypeNoName node);

    void caseAIntBaseTypeNoName(AIntBaseTypeNoName node);

    void caseALongBaseTypeNoName(ALongBaseTypeNoName node);

    void caseAFloatBaseTypeNoName(AFloatBaseTypeNoName node);

    void caseADoubleBaseTypeNoName(ADoubleBaseTypeNoName node);

    void caseANullBaseTypeNoName(ANullBaseTypeNoName node);

    void caseABooleanBaseType(ABooleanBaseType node);

    void caseAByteBaseType(AByteBaseType node);

    void caseACharBaseType(ACharBaseType node);

    void caseAShortBaseType(AShortBaseType node);

    void caseAIntBaseType(AIntBaseType node);

    void caseALongBaseType(ALongBaseType node);

    void caseAFloatBaseType(AFloatBaseType node);

    void caseADoubleBaseType(ADoubleBaseType node);

    void caseANullBaseType(ANullBaseType node);

    void caseAClassNameBaseType(AClassNameBaseType node);

    void caseABaseNonvoidType(ABaseNonvoidType node);

    void caseAQuotedNonvoidType(AQuotedNonvoidType node);

    void caseAIdentNonvoidType(AIdentNonvoidType node);

    void caseAFullIdentNonvoidType(AFullIdentNonvoidType node);

    void caseAArrayBrackets(AArrayBrackets node);

    void caseAEmptyMethodBody(AEmptyMethodBody node);

    void caseAFullMethodBody(AFullMethodBody node);

    void caseADeclaration(ADeclaration node);

    void caseAUnknownJimpleType(AUnknownJimpleType node);

    void caseANonvoidJimpleType(ANonvoidJimpleType node);

    void caseALocalName(ALocalName node);

    void caseASingleLocalNameList(ASingleLocalNameList node);

    void caseAMultiLocalNameList(AMultiLocalNameList node);

    void caseALabelStatement(ALabelStatement node);

    void caseABreakpointStatement(ABreakpointStatement node);

    void caseAEntermonitorStatement(AEntermonitorStatement node);

    void caseAExitmonitorStatement(AExitmonitorStatement node);

    void caseATableswitchStatement(ATableswitchStatement node);

    void caseALookupswitchStatement(ALookupswitchStatement node);

    void caseAIdentityStatement(AIdentityStatement node);

    void caseAIdentityNoTypeStatement(AIdentityNoTypeStatement node);

    void caseAAssignStatement(AAssignStatement node);

    void caseAIfStatement(AIfStatement node);

    void caseAGotoStatement(AGotoStatement node);

    void caseANopStatement(ANopStatement node);

    void caseARetStatement(ARetStatement node);

    void caseAReturnStatement(AReturnStatement node);

    void caseAThrowStatement(AThrowStatement node);

    void caseAInvokeStatement(AInvokeStatement node);

    void caseALabelName(ALabelName node);

    void caseACaseStmt(ACaseStmt node);

    void caseAConstantCaseLabel(AConstantCaseLabel node);

    void caseADefaultCaseLabel(ADefaultCaseLabel node);

    void caseAGotoStmt(AGotoStmt node);

    void caseACatchClause(ACatchClause node);

    void caseANewExpression(ANewExpression node);

    void caseACastExpression(ACastExpression node);

    void caseAInstanceofExpression(AInstanceofExpression node);

    void caseAInvokeExpression(AInvokeExpression node);

    void caseAReferenceExpression(AReferenceExpression node);

    void caseABinopExpression(ABinopExpression node);

    void caseAUnopExpression(AUnopExpression node);

    void caseAImmediateExpression(AImmediateExpression node);

    void caseASimpleNewExpr(ASimpleNewExpr node);

    void caseAArrayNewExpr(AArrayNewExpr node);

    void caseAMultiNewExpr(AMultiNewExpr node);

    void caseAArrayDescriptor(AArrayDescriptor node);

    void caseAReferenceVariable(AReferenceVariable node);

    void caseALocalVariable(ALocalVariable node);

    void caseABinopBoolExpr(ABinopBoolExpr node);

    void caseAUnopBoolExpr(AUnopBoolExpr node);

    void caseANonstaticInvokeExpr(ANonstaticInvokeExpr node);

    void caseAStaticInvokeExpr(AStaticInvokeExpr node);

    void caseADynamicInvokeExpr(ADynamicInvokeExpr node);

    void caseABinopExpr(ABinopExpr node);

    void caseAUnopExpr(AUnopExpr node);

    void caseASpecialNonstaticInvoke(ASpecialNonstaticInvoke node);

    void caseAVirtualNonstaticInvoke(AVirtualNonstaticInvoke node);

    void caseAInterfaceNonstaticInvoke(AInterfaceNonstaticInvoke node);

    void caseAUnnamedMethodSignature(AUnnamedMethodSignature node);

    void caseAMethodSignature(AMethodSignature node);

    void caseAArrayReference(AArrayReference node);

    void caseAFieldReference(AFieldReference node);

    void caseAIdentArrayRef(AIdentArrayRef node);

    void caseAQuotedArrayRef(AQuotedArrayRef node);

    void caseALocalFieldRef(ALocalFieldRef node);

    void caseASigFieldRef(ASigFieldRef node);

    void caseAFieldSignature(AFieldSignature node);

    void caseAFixedArrayDescriptor(AFixedArrayDescriptor node);

    void caseASingleArgList(ASingleArgList node);

    void caseAMultiArgList(AMultiArgList node);

    void caseALocalImmediate(ALocalImmediate node);

    void caseAConstantImmediate(AConstantImmediate node);

    void caseAIntegerConstant(AIntegerConstant node);

    void caseAFloatConstant(AFloatConstant node);

    void caseAStringConstant(AStringConstant node);

    void caseAClzzConstant(AClzzConstant node);

    void caseANullConstant(ANullConstant node);

    void caseAAndBinop(AAndBinop node);

    void caseAOrBinop(AOrBinop node);

    void caseAXorBinop(AXorBinop node);

    void caseAModBinop(AModBinop node);

    void caseACmpBinop(ACmpBinop node);

    void caseACmpgBinop(ACmpgBinop node);

    void caseACmplBinop(ACmplBinop node);

    void caseACmpeqBinop(ACmpeqBinop node);

    void caseACmpneBinop(ACmpneBinop node);

    void caseACmpgtBinop(ACmpgtBinop node);

    void caseACmpgeBinop(ACmpgeBinop node);

    void caseACmpltBinop(ACmpltBinop node);

    void caseACmpleBinop(ACmpleBinop node);

    void caseAShlBinop(AShlBinop node);

    void caseAShrBinop(AShrBinop node);

    void caseAUshrBinop(AUshrBinop node);

    void caseAPlusBinop(APlusBinop node);

    void caseAMinusBinop(AMinusBinop node);

    void caseAMultBinop(AMultBinop node);

    void caseADivBinop(ADivBinop node);

    void caseALengthofUnop(ALengthofUnop node);

    void caseANegUnop(ANegUnop node);

    void caseAQuotedClassName(AQuotedClassName node);

    void caseAIdentClassName(AIdentClassName node);

    void caseAFullIdentClassName(AFullIdentClassName node);

    void caseAQuotedName(AQuotedName node);

    void caseAIdentName(AIdentName node);

    void caseTIgnored(TIgnored node);

    void caseTAbstract(TAbstract node);

    void caseTFinal(TFinal node);

    void caseTNative(TNative node);

    void caseTPublic(TPublic node);

    void caseTProtected(TProtected node);

    void caseTPrivate(TPrivate node);

    void caseTStatic(TStatic node);

    void caseTSynchronized(TSynchronized node);

    void caseTTransient(TTransient node);

    void caseTVolatile(TVolatile node);

    void caseTStrictfp(TStrictfp node);

    void caseTEnum(TEnum node);

    void caseTAnnotation(TAnnotation node);

    void caseTClass(TClass node);

    void caseTInterface(TInterface node);

    void caseTVoid(TVoid node);

    void caseTBoolean(TBoolean node);

    void caseTByte(TByte node);

    void caseTShort(TShort node);

    void caseTChar(TChar node);

    void caseTInt(TInt node);

    void caseTLong(TLong node);

    void caseTFloat(TFloat node);

    void caseTDouble(TDouble node);

    void caseTNullType(TNullType node);

    void caseTUnknown(TUnknown node);

    void caseTExtends(TExtends node);

    void caseTImplements(TImplements node);

    void caseTBreakpoint(TBreakpoint node);

    void caseTCase(TCase node);

    void caseTCatch(TCatch node);

    void caseTCmp(TCmp node);

    void caseTCmpg(TCmpg node);

    void caseTCmpl(TCmpl node);

    void caseTDefault(TDefault node);

    void caseTEntermonitor(TEntermonitor node);

    void caseTExitmonitor(TExitmonitor node);

    void caseTGoto(TGoto node);

    void caseTIf(TIf node);

    void caseTInstanceof(TInstanceof node);

    void caseTInterfaceinvoke(TInterfaceinvoke node);

    void caseTLengthof(TLengthof node);

    void caseTLookupswitch(TLookupswitch node);

    void caseTNeg(TNeg node);

    void caseTNew(TNew node);

    void caseTNewarray(TNewarray node);

    void caseTNewmultiarray(TNewmultiarray node);

    void caseTNop(TNop node);

    void caseTRet(TRet node);

    void caseTReturn(TReturn node);

    void caseTSpecialinvoke(TSpecialinvoke node);

    void caseTStaticinvoke(TStaticinvoke node);

    void caseTDynamicinvoke(TDynamicinvoke node);

    void caseTTableswitch(TTableswitch node);

    void caseTThrow(TThrow node);

    void caseTThrows(TThrows node);

    void caseTVirtualinvoke(TVirtualinvoke node);

    void caseTNull(TNull node);

    void caseTFrom(TFrom node);

    void caseTTo(TTo node);

    void caseTWith(TWith node);

    void caseTCls(TCls node);

    void caseTComma(TComma node);

    void caseTLBrace(TLBrace node);

    void caseTRBrace(TRBrace node);

    void caseTSemicolon(TSemicolon node);

    void caseTLBracket(TLBracket node);

    void caseTRBracket(TRBracket node);

    void caseTLParen(TLParen node);

    void caseTRParen(TRParen node);

    void caseTColon(TColon node);

    void caseTDot(TDot node);

    void caseTQuote(TQuote node);

    void caseTColonEquals(TColonEquals node);

    void caseTEquals(TEquals node);

    void caseTAnd(TAnd node);

    void caseTOr(TOr node);

    void caseTXor(TXor node);

    void caseTMod(TMod node);

    void caseTCmpeq(TCmpeq node);

    void caseTCmpne(TCmpne node);

    void caseTCmpgt(TCmpgt node);

    void caseTCmpge(TCmpge node);

    void caseTCmplt(TCmplt node);

    void caseTCmple(TCmple node);

    void caseTShl(TShl node);

    void caseTShr(TShr node);

    void caseTUshr(TUshr node);

    void caseTPlus(TPlus node);

    void caseTMinus(TMinus node);

    void caseTMult(TMult node);

    void caseTDiv(TDiv node);

    void caseTFullIdentifier(TFullIdentifier node);

    void caseTQuotedName(TQuotedName node);

    void caseTIdentifier(TIdentifier node);

    void caseTAtIdentifier(TAtIdentifier node);

    void caseTBoolConstant(TBoolConstant node);

    void caseTIntegerConstant(TIntegerConstant node);

    void caseTFloatConstant(TFloatConstant node);

    void caseTStringConstant(TStringConstant node);

    void caseEOF(EOF node);
}
