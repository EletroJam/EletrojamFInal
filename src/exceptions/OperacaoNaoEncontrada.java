package exceptions;

public class OperacaoNaoEncontrada extends Exception{


	private static final long serialVersionUID = 7709531048145070192L;

	public OperacaoNaoEncontrada() {
		super("Operacao não encontrada");
	}
}
