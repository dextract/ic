package semantics;

import java.util.HashMap;
import java.util.Map.Entry;

public class EnvImpl<T> implements Env<T> {
	private EnvImpl<T> previous;
	private EnvImpl<T> next;
	HashMap<String, T> denots = new HashMap<String, T>();

	public EnvImpl() {}

	public EnvImpl(EnvImpl<T> previous) {
		this();
		previous.next = this;
		this.previous = previous;
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Env({");
		int count = 0;

		for (Entry<String, T> entry : denots.entrySet()) {
			String key = entry.getKey();
			T value = entry.getValue();
			count++;
			sb.append(key+"="+value+(count==denots.size()?"":";"));
		}
		sb.append("})");

		return sb.toString();	
	}

	@Override
	public Env<T> beginScope() {
		return new EnvImpl<T>(this);
	}

	@Override
	public Env<T> endScope() {
		return previous;
	}

	@Override
	public void assoc(String id, T denot) throws ExistentIdentifierException {
		if(denots.get(id)!=null)
			throw new ExistentIdentifierException(id);
		else {
			denots.put(id, denot);
		}
	}
	
	public void update(String id, T denot) {
		denots.remove(id);
		denots.put(id, denot);
	}

	@Override
	public T find(String id) throws UnkownIdentifierException {

		T denot;

		if(denots.size() == 0)
			if((denot=findForward(id))==null)
				throw new UnkownIdentifierException(id);
				//return null;
			else
				return denot;

		denot = denots.get(id);
		if( denot!=null ) 
			return denot;
		else
			if( previous != null)
				return previous.find(id);
			else
				throw new UnkownIdentifierException(id);
	}

	private T findForward(String id) {

		T denot = denots.get(id);
		if( denot!=null ) 
			return denot;
		else {
			if( next != null )
				return next.findForward(id);
		}
		return denot;
	}

}