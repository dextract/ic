package ast;

public class ASTVar implements ASTNode {

	public final ASTNode exp;
	
	public ASTVar(ASTNode exp) {
		this.exp = exp;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}
}