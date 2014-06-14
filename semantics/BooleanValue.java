package semantics;

public class BooleanValue implements Value {
	
	public final boolean value;
	public static final BooleanValue true_value = new BooleanValue(true);
	public static final BooleanValue false_value = new BooleanValue(false);

	public BooleanValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object other) {
		if( other instanceof BooleanValue ) 
			return ((BooleanValue)other).value == value;
		else
			return false;
	}
	
	public boolean sameType(Object other) {
		if(other instanceof BooleanValue)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return Boolean.toString(value);
	}
	
}