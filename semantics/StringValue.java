package semantics;


public class StringValue implements Value {
	
	public final String value;

	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object other) {
		if( other instanceof StringValue ) 
			return ((StringValue)other).value.equals(value);
		else
			return false;
	}
	
	public boolean sameType(Object other) {
		if(other instanceof StringValue)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
