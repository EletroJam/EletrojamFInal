package exceptions;

public class TelefoneNaoEncontrado extends Exception {

	private static final long serialVersionUID = 4864790316983221507L;

	public TelefoneNaoEncontrado() {
		super("Telefone não encontrado");
	}

}
