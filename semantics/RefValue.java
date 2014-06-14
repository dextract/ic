package semantics;

public class RefValue implements Value {
	private Value value;
	
	public RefValue(Value value) {
		this.value = value;
	}
	
	public Value get() { return value; }
	
	public void set(Value value) { this.value = value; }
	
	public boolean sameType(Object other) {
		if(other instanceof RefValue)
			return true;
		else
			return false;
	}
	
	public String toString() { return "Ref("+value.toString()+")"; }
}