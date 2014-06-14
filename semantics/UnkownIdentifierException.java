package semantics;

public class UnkownIdentifierException extends Exception {

	private String id;
	
	public UnkownIdentifierException(String id) {
		this.id=id;
	}

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

}