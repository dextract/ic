package ast;

import java.util.ArrayList;
import java.util.List;

public class ASTDecl implements ASTNode {

	public ASTNode body;
	public List<Pair<String, ASTNode>> l;

	public ASTDecl() {
		l = new ArrayList<Pair<String, ASTNode>>();
	}

	public void addToList(String id, ASTNode expr) {
		l.add(new Pair<String, ASTNode>(id, expr));
	}

	public void setBody(ASTNode b) {
		body = b;
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}