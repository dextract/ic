package ast;

import java.util.List;

public class ASTApp implements ASTNode {

	public final ASTNode left;
	//public final ASTNode right;
	public final List<ASTNode> right;
	
	//public ASTApp(ASTNode left, ASTNode right) {
	public ASTApp(ASTNode left, List<ASTNode> right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}