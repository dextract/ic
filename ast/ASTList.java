package ast;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ASTList implements ASTNode {
	public List list;
	
	public ASTList(List list) {
		this.list = list;
	}
	
	@Override
	public <T,S> T accept(Visitor<T,S> visitor,S other) throws Exception {
		return visitor.visit(this,other);
	}

}