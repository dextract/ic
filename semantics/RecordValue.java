package semantics;

import java.util.Iterator;
import java.util.List;

import ast.ASTNode;
import ast.Pair;

public class RecordValue implements Value {

	public final List<Pair<String,ASTNode>> right;
	public final Env<Value> env;

	public RecordValue(List<Pair<String,ASTNode>> right, Env<Value> env) {
		this.right = right;
		this.env = env;
	}
	
	public boolean sameType(Object other) {
		if(other instanceof RecordValue)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("RecordValue({");
		
		Iterator<Pair<String, ASTNode>> it = right.iterator();
		while(it.hasNext()) {
			Pair<String, ASTNode> tmp = it.next();
			try {
				sb.append(tmp.getElement0()+":"+tmp.getElement1().accept(new EvalVisitor(), env)+((it.hasNext())?";":""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("},"+env+")");
		
		return sb.toString();
		
	}
	
}
