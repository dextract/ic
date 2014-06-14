package semantics;

import java.util.Iterator;
import java.util.List;

import ast.ASTNode;
import ast.Pair;

public class ClosureValue implements Value {

	public final List<Pair<String, String>> param;
	public final ASTNode body;
	public final Env<Value> env;

	public ClosureValue(List<Pair<String, String>> param, ASTNode body, Env<Value> env) {
		this.param = param;
		this.body = body;
		this.env = env;
	}
	
	public boolean sameType(Object other) {
		if(other instanceof ClosureValue)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
	StringBuilder sb = new StringBuilder("Closure({");
		
		Iterator<Pair<String, String>> it = param.iterator();
		while(it.hasNext()) {
			String tmp = it.next().getElement0();
			try {
				sb.append(tmp+((it.hasNext())?";":""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append("})");

		
		return sb.toString();
	}

}