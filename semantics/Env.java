package semantics;

public interface Env<T> {

	Env<T> beginScope();
	Env<T> endScope();
	T find(String id) throws UnkownIdentifierException;
	void assoc(String id, T denot) throws ExistentIdentifierException;
	void update(String id, T denot);
	
}