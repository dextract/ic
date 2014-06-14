package ast;

import java.util.List;

public class ASTFun implements ASTNode {

	public final List<Pair<String, String>> param;
	public final ASTNode body;
	
	public ASTFun(List<Pair<String, String>> param, ASTNode body) {
		this.param = param;
		this.body= body;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}