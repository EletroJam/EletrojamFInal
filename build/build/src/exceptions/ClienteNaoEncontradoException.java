package exceptions;

public class ClienteNaoEncontradoException extends Exception{
	
	private static final long serialVersionUID = -1148248855470998111L;
	
	public ClienteNaoEncontradoException() {
		super("Cliente não encontrado");
	}
	

}
