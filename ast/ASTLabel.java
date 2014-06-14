package ast;

public class ASTLabel implements ASTNode {

	public final String label;
	
	public ASTLabel(String label) {
	    this.label = label;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}
