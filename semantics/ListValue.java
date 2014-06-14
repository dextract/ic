package semantics;

import java.util.Iterator;
import java.util.List;

public class ListValue implements Value {
	
	public final List<Value> values;
	public final Env<Value> env;

	public ListValue(List<Value> values, Env<Value> env) {
		this.env = env;
		this.values = values;
	}

	@Override
	public boolean equals(Object other) {
		if( other instanceof ListValue ) 
			return ((ListValue)other).values.equals(values);
		else
			return false;
	}
	
	public boolean sameType(Object other) {
		if(other instanceof ListValue)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("ListValue(");
		
		Iterator<Value> it = values.iterator();
		while(it.hasNext()) {
			Value tmp = it.next();
			try {
				sb.append(tmp+((it.hasNext())?";":""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sb.append(")");
		
		return sb.toString();
		
		
	}

}
