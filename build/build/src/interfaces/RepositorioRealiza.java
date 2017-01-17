package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Realiza;


public interface RepositorioRealiza {

	public void insert(Realiza realiza) throws RepositoryException;
	
	//public void update(Celular celular, String numero_celular) throws RepositoryException;
	
	public void delete(int id_operacao) throws RepositoryException;
	
	public boolean has (int id_operacao ) throws RepositoryException;
	
	public Realiza get(int id_operacao) throws RepositoryException;
	
	public ArrayList<Realiza> getRealizacao() throws RepositoryException;
}
