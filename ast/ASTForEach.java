package ast;

public class ASTForEach implements ASTNode {

	public final String id;
	public final ASTNode left;
	public final ASTNode right;
	
	public ASTForEach(String id, ASTNode left, ASTNode right) {
		this.id = id;
		this.left = left;
		this.right = right;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}