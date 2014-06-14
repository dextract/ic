package ast;

public class ASTIfElse implements ASTNode {

	public final ASTNode cond;
	public final ASTNode then_branch;
	public final ASTNode else_branch;
	
	public ASTIfElse(ASTNode cond, ASTNode then_branch, ASTNode else_branch) {
	    this.cond = cond;
	    this.then_branch = then_branch;
	    this.else_branch = else_branch;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}
