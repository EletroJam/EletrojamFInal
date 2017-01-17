package exceptions;

public class CelularNaoEncontrado extends Exception {

	private static final long serialVersionUID = -3418830022980635332L;

	public CelularNaoEncontrado() {
		super("Celular não encontrado");
	}

}
