package ast;

import java.util.ArrayList;
import java.util.List;

public class ASTRecord implements ASTNode {
	
	public List<Pair<String, ASTNode>> l;

	public ASTRecord() {
		l = new ArrayList<Pair<String, ASTNode>>();
	}

	public void addToList(String id, ASTNode expr) {
		l.add(new Pair<String, ASTNode>(id, expr));
	}

	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}


}