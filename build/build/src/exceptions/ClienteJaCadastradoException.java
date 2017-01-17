package exceptions;

public class ClienteJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -122455878809050854L;

	public ClienteJaCadastradoException() {
		super("Cliente já cadastrado");
	}

}
