package ast;

public class ASTNot implements ASTNode {

	public final ASTNode left;
	
	public ASTNot(ASTNode left) {
		this.left = left;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}