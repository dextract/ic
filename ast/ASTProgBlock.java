package ast;

public class ASTProgBlock implements ASTNode {

	public final ASTNode left;
	public final ASTNode right;
	
	public ASTProgBlock(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}