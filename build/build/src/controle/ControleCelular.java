package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.CelularNaoEncontrado;
import interfaces.RepositorioCelular;
import negocio.Celular;

public class ControleCelular {

	private RepositorioCelular celulares;
	
	public ControleCelular(RepositorioCelular celulares) {
		super();
		this.celulares = celulares;
	}
	
	public void insert(Celular celular) throws RepositoryException {
		celulares.insert(celular);
	}

	public boolean has(String numero_celular) throws RepositoryException {
		return celulares.has(numero_celular);
	}
	
	public void updateCel(String numero_celular, int id) throws RepositoryException {
		celulares.update(numero_celular, id);
	}
	
	public void delete (String numero_celular) throws RepositoryException, CelularNaoEncontrado {
		if(has(numero_celular)){
			celulares.delete(numero_celular);
		}else{
			throw new CelularNaoEncontrado();
		}		
		
	}
	
	public Celular get(String numero_celular) throws RepositoryException, CelularNaoEncontrado {
		if(has(numero_celular)){
			return celulares.get(numero_celular);
		}else{
			return null;
		}
	}
	
	public ArrayList<Celular> getCelulares() throws RepositoryException {
		return celulares.getCelulares();
	}
}
