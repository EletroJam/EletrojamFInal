package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.RealizaNaoEncontrado;
import interfaces.RepositorioRealiza;
import negocio.Realiza;


public class ControleRealiza {

	private RepositorioRealiza realizacao;
	
	public ControleRealiza(RepositorioRealiza realizacao) {
		super();
		this.realizacao = realizacao;
	}

	public void insert(Realiza realiza) throws RepositoryException {
		realizacao.insert(realiza);
	}

	public boolean has(int id) throws RepositoryException {
		return realizacao.has(id);
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

	public void delete(int id) throws RepositoryException, RealizaNaoEncontrado {
		
		realizacao.delete(id);
		

	}

	public Realiza get(int id) throws RepositoryException {
		if (has(id)) {
			return realizacao.get(id);
		} else {
			return null;
		}
	}

	public ArrayList<Realiza> getRealizacoes() throws RepositoryException {
		return realizacao.getRealizacao();
	}
}
