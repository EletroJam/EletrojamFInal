package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import interfaces.RepositorioRota;
import negocio.Rota;


public class ControleRota {
	
private RepositorioRota rotass;
	
	public ControleRota(RepositorioRota rota) {
		super();
		this.rotass = rota;
	}
	
	public void insert(Rota rota) throws RepositoryException {
		rotass.insert(rota);
	}

	public boolean has(String nome) throws RepositoryException {
		return rotass.has(nome);
	}
	
	public ArrayList<Rota> getRotas() throws RepositoryException{
		return rotass.getRotas();
	}

}
