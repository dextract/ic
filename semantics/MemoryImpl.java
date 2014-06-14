package semantics;

public class MemoryImpl implements Memory {

	@Override
	public RefValue alloc(Value v) {
		 return new RefValue(v);
	}

	@Override
	public void free(RefValue r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(RefValue r, Value v) {
		 r.set(v);
	}

	@Override
	public Value get(RefValue r) {
		 return r.get();
	}

}
