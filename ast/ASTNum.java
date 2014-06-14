package ast;

public class ASTNum implements ASTNode {
	public final int num;
	
	public ASTNum(int num) {
		this.num = num;
	}
	
	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}