package semantics;

public class UnitValue implements Value {
	
	public final String value = "-";
	
	public boolean sameType(Object other) {
		if(other instanceof UnitValue)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return value;
	}

}
