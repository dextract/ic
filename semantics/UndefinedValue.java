package semantics;

public class UndefinedValue implements Value {
	
	public boolean sameType(Object other) {
		if(other instanceof UndefinedValue)
			return true;
		else
			return false;
	}

}
