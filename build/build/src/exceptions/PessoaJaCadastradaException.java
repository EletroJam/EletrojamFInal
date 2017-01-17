package exceptions;

public class PessoaJaCadastradaException extends Exception{
	private static final long serialVersionUID = -462455878809050854L;

	public PessoaJaCadastradaException() {
		super("Pessoa já cadastrada");
	}

}
