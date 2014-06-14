package ast;

public class ASTListVal implements ASTNode {

	public final ASTNode left;
	public final ASTNode right;
	//public final int index;
	
//	public ASTListVal(ASTNode left, int index) {
	public ASTListVal(ASTNode left, ASTNode right) {
		//this.index = index;
		this.left = left;
		this.right = right;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}