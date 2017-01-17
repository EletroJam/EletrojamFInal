package exceptions;

public class PessoaNaoEncontradaException extends Exception {
	
	private static final long serialVersionUID = -4548248855470998111L;
	
	public PessoaNaoEncontradaException() {
		super("Pessoa não encontrada");
	}

}
