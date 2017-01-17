package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.OperacaoNaoEncontrada;
import interfaces.RepositorioOperacaoCliente;
import negocio.OperacaoCliente;


public class ControleOperacaoCliente {

	private RepositorioOperacaoCliente operacoesCliente;
	
	public ControleOperacaoCliente(RepositorioOperacaoCliente operacoesCliente) {
		super();
		this.operacoesCliente = operacoesCliente;
	}

	public void insert(OperacaoCliente operacaoCliente) throws RepositoryException {
		operacoesCliente.insert(operacaoCliente);
	}

	public boolean has(int id) throws RepositoryException {
		return operacoesCliente.has(id);
	}

	/*
	public void update(Telefone telefone, String numero_telefone) throws RepositoryException, TelefoneNaoEncontrado {
		// Pega número antigo, pois o passado como parâmetro é o que vai ser
		// atualizado
		String oldNumero = telefone.getNumero_telefone();
		if (has(oldNumero)) {
			telefones.update(telefone, numero_telefone);
		} else {
			throw new TelefoneNaoEncontrado();
		}
	}
	*/
	
	public void update (int id, int atraso) throws RepositoryException{
		operacoesCliente.update(id, atraso);
	}
	
	public void update_estado (int id, String estado) throws RepositoryException{
		operacoesCliente.update_estado(id, estado);
	}
	

	public void delete(int id) throws RepositoryException, OperacaoNaoEncontrada {
		
		operacoesCliente.delete(id);
		

	}

	public OperacaoCliente get(int id) throws RepositoryException {
		
			return operacoesCliente.get(id);
		
	}

	public ArrayList<OperacaoCliente> getOperacaoClientes() throws RepositoryException {
		return operacoesCliente.getOperacaoClientes();
	}
}
