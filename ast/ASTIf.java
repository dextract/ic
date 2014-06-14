package ast;

public class ASTIf implements ASTNode {

	public final ASTNode cond;
	public final ASTNode then_branch;
	
	public ASTIf(ASTNode cond, ASTNode then_branch) {
	    this.cond = cond;
	    this.then_branch = then_branch;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}
