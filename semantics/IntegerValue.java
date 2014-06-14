package semantics;


public class IntegerValue implements Value {
	
	public final int value;

	public IntegerValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object other) {
		if( other instanceof IntegerValue )
			return ((IntegerValue)other).value == value;
		else
			return false;
	}
	
	public boolean sameType(Object other) {
		if(other instanceof IntegerValue)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
}
