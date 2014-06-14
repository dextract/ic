package ast;

public class ASTFree implements ASTNode {

	public final ASTNode left;
	
	public ASTFree(ASTNode left) {
		this.left = left;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}