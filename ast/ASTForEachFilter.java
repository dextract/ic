package ast;

public class ASTForEachFilter implements ASTNode {

	public final String id;
	public final ASTNode left;
	public final ASTNode right;
	public final ASTNode filter;
	
	public ASTForEachFilter(String id, ASTNode left, ASTNode right, ASTNode filter) {
		this.id = id;
		this.left = left;
		this.right = right;
		this.filter = filter;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}