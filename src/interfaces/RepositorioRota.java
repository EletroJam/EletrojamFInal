package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Rota;


public interface RepositorioRota {
	public void insert(Rota rota) throws RepositoryException;
	
//	public void update(Cliente cliente, String email) throws RepositoryException;
	
	//public void delete(String cpf) throws RepositoryException;
	
	public boolean has (String nome) throws RepositoryException;
	
	//public Rota get(int id) throws RepositoryException;
	
	//public Blob getPicture(String cpf);
	
	public ArrayList<Rota> getRotas() throws RepositoryException;
}
