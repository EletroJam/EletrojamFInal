package exceptions;

public class FuncionarioJaCadastradoException extends Exception {
	
	private static final long serialVersionUID = -102455878809050854L;

	public FuncionarioJaCadastradoException() {
		super("Funcionario já cadastrado");
	}

}
