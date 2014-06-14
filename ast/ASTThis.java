package ast;

public class ASTThis implements ASTNode {

	
	public ASTThis() {}
	
	@Override
	public <T, S> T accept(Visitor<T, S> visitor, S other) throws Exception {
		return visitor.visit(this,other);
	}

}
