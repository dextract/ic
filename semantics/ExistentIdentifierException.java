package semantics;

public class ExistentIdentifierException extends Exception {

	private String id;
	
	public ExistentIdentifierException(String id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	
	public String getId() {
		return id;
	}

}
