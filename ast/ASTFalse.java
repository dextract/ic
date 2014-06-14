package ast;

public class ASTFalse implements ASTNode {
	
	public ASTFalse() {}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}