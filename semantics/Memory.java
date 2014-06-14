package semantics;

public interface Memory {
	RefValue alloc(Value v);
	void free(RefValue r);
	void set(RefValue r, Value v);
	Value get(RefValue r);
}