package exceptions;

public class EnderecoNaoEncontrado extends Exception {
	
private static final long serialVersionUID = -2148248855470998111L;
	
	public EnderecoNaoEncontrado() {
		super("Endereco não encontrado");
	}

}
