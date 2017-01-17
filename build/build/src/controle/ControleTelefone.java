package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.TelefoneNaoEncontrado;
import interfaces.RepositorioTelefone;
import negocio.Telefone;

public class ControleTelefone {

	private RepositorioTelefone telefones;

	public ControleTelefone(RepositorioTelefone telefones) {
		super();
		this.telefones = telefones;
	}

	public void insert(Telefone telefone) throws RepositoryException {
		telefones.insert(telefone);
	}

	public boolean has(String numero_telefone) throws RepositoryException {
		return telefones.has(numero_telefone);
	}

	public void updateTelefone(String numero_telefone, int id) throws RepositoryException{
		telefones.update(numero_telefone, id);
	}

	public void delete(String numero_telefone) throws RepositoryException, TelefoneNaoEncontrado {
		if (has(numero_telefone)) {
			telefones.delete(numero_telefone);
		} else {
			throw new TelefoneNaoEncontrado();
		}

	}

	public Telefone get(String numero_telefone) throws RepositoryException, TelefoneNaoEncontrado {
		if (has(numero_telefone)) {
			return telefones.get(numero_telefone);
		} else {
			return null;
		}
	}

	public ArrayList<Telefone> getTelefones() throws RepositoryException {
		return telefones.getTelefones();
	}

}
