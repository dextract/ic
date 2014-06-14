package ast;

public class ASTDeref implements ASTNode {

	public final ASTNode exp;
	
	public ASTDeref(ASTNode exp) {
		this.exp = exp;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}