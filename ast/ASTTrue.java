package ast;

public class ASTTrue implements ASTNode {
	
	public ASTTrue() {}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}