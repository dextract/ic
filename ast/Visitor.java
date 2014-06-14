package ast;

public interface Visitor<T,S> {
	T visit(ASTAdd node, S other) throws Exception;
	T visit(ASTAnd node, S other) throws Exception;
	T visit(ASTTrue node, S other) throws Exception;
	T visit(ASTFalse node, S other) throws Exception;
	T visit(ASTCond node, S other) throws Exception;
	T visit(ASTDivide node, S other) throws Exception;
	T visit(ASTEquals node, S other) throws Exception;
	T visit(ASTGreater node, S other) throws Exception;
	T visit(ASTGreaterEq node, S other) throws Exception;
	T visit(ASTId node, S other) throws Exception;
	T visit(ASTLess node, S other) throws Exception;
	T visit(ASTLessEq node, S other) throws Exception;
	T visit(ASTMul node, S other) throws Exception;
	T visit(ASTNot node, S other) throws Exception;
	T visit(ASTNotEquals node, S other) throws Exception;
	T visit(ASTNum node, S other) throws Exception;
	T visit(ASTOr node, S other) throws Exception;
	T visit(ASTSub node, S other) throws Exception;
	T visit(ASTDecl node, S other) throws Exception;
	T visit(ASTIfElse node, S other) throws Exception;
	T visit(ASTIf node, S other) throws Exception;
	T visit(ASTWhile node, S other) throws Exception;
	T visit(ASTPrint node, S other) throws Exception;
	T visit(ASTSeq node, S other) throws Exception;
	T visit(ASTDeref node, S other) throws Exception;
	T visit(ASTAssign node, S other) throws Exception;
	T visit(ASTVar node, S other) throws Exception;
	T visit(ASTFun node,S other) throws Exception;
	T visit(ASTApp node,S other) throws Exception;
	T visit(ASTBlock node,S other) throws Exception;
	T visit(ASTProgBlock node,S other) throws Exception;
	T visit(ASTFree node, S other) throws Exception;
	T visit(ASTLabel node, S other) throws Exception;
	T visit(ASTRecord node, S other) throws Exception;
	T visit(ASTRecordVal node, S other) throws Exception;
	T visit(ASTString node, S other) throws Exception;
	T visit(ASTList node, S other) throws Exception;
	T visit(ASTListVal node, S other) throws Exception;
	T visit(ASTForEach node, S other) throws Exception;
	T visit(ASTForEachFilter node, S other) throws Exception;
	T visit(ASTThis node, S other) throws Exception;
	T visit(ASTConcat node, S other) throws Exception;
}