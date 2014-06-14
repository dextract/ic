package ast;

public class ASTRecordVal implements ASTNode {

	public final ASTNode left;
	public final String label;
	
	public ASTRecordVal(ASTNode left, String label) {
		this.label = label;
		this.left = left;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}