package ast;

public class ASTId implements ASTNode {

	public final String id;
	
	public ASTId(String id) {
	    this.id = id;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}
