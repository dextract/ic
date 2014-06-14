package ast;

public interface ASTNode {
	<T,S> T accept(Visitor<T, S> visitor, S other) throws Exception;
}