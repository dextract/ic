package ast;

public class ASTString implements ASTNode {
	public final String str;
	
	public ASTString(String str) {
		this.str = str;
	}
	
	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}