package semantics;

public class Values {
	public static boolean toBool(Value value) throws EvaluationException {
		if( value instanceof BooleanValue ) {
			return ((BooleanValue)value).value;
		}
		else throw new EvaluationException();
	}	

	public static int toInt(Value value) throws EvaluationException {
		if( value instanceof IntegerValue ) {
			return ((IntegerValue)value).value;
		}
		else throw new EvaluationException();
	}	

	public static Value newInt(int value) {
		return new IntegerValue(value);
	}

	public static Value newBool(boolean value) {
		if( value )
			return BooleanValue.true_value;
		else
			return BooleanValue.false_value;
	}
	
	public static Value newRef(Value value) {
		return new RefValue(value);
	}
	
	public static Value newUndefined() {
		return new UndefinedValue();
	}
	
	public static Value newUnit() {
		return new UnitValue();
	}

	public static RefValue toRef(Value value) throws EvaluationException {
		if( value instanceof RefValue ) {
			return (RefValue) value;
		}
		else throw new EvaluationException();
	}

	public static ClosureValue toClosure(Value value) throws EvaluationException {
		if( value instanceof ClosureValue ) {
			return (ClosureValue) value;
		}
		else throw new EvaluationException();
	}

	public static RecordValue toRecord(Value value) throws EvaluationException {
		if( value instanceof RecordValue ) {
			return (RecordValue) value;
		}
		else throw new EvaluationException();
	}
	
	public static ListValue toList(Value value) throws EvaluationException {
		if( value instanceof ListValue ) {
			return (ListValue) value;
		}
		else throw new EvaluationException();
	}

	public static Value newString(String value) {
		return new StringValue(value);
	}

	public static Value fromStringToVal(String p) {
		
		Value tmp = null;
		
		if(p.equals("int"))
			tmp = Values.newInt(0);
		else if(p.equals("bool"))
			tmp = Values.newBool(false);
		else if(p.equals("string"))
			tmp = Values.newString("");
		else if(p.startsWith("fun"))
			tmp = new ClosureValue(null, null, null);
		
		return tmp;
		
	}
}