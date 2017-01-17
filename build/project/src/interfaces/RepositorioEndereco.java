package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Endereco;


public interface RepositorioEndereco {

public void insert(Endereco endereco) throws RepositoryException;
	
	public void update(Endereco endereco, int id_endereco) throws RepositoryException;
	
	public void delete(int id) throws RepositoryException;
	
	public boolean has (int id) throws RepositoryException;
	
	public int getId() throws RepositoryException;
	
	public Endereco get(int id) throws RepositoryException;
	
	
	
	public ArrayList<Endereco> getEnderecos() throws RepositoryException;
}
