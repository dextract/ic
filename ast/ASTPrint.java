package ast;

public class ASTPrint implements ASTNode {

	public final ASTNode left;
	
	public ASTPrint(ASTNode left) {
		this.left = left;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}