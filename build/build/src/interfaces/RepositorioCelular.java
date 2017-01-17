package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Celular;



public interface RepositorioCelular {

//negocio -> INTERFACE -> repositorio -> controle -> fachada


	public void insert(Celular celular) throws RepositoryException;
	
	public void update(String numero_celular, int id) throws RepositoryException;
	
	public void delete(String numero_celular) throws RepositoryException;
	
	public boolean has (String numero_celular) throws RepositoryException;
	
	public Celular get(String numero_celular) throws RepositoryException;
	
	
	
	public ArrayList<Celular> getCelulares() throws RepositoryException;
}
