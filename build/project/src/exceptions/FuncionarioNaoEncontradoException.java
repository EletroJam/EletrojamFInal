package exceptions;

public class FuncionarioNaoEncontradoException extends Exception {

	private static final long serialVersionUID = -1148248855470998111L;
	
	public FuncionarioNaoEncontradoException() {
		super("Funcionario não encontrado");
	}

}
