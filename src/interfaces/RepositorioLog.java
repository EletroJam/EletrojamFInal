package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Log;

public interface RepositorioLog {

	
	public void insert(Log log) throws RepositoryException;
	
	//public void update(Celular celular, String numero_celular) throws RepositoryException;
	
	//public void delete(int id_operacao) throws RepositoryException;
	
	//public boolean has (int id_operacao ) throws RepositoryException;
	
	public Log get(int id_funcionario) throws RepositoryException;
	
	public ArrayList<Log> getLogs() throws RepositoryException;
}
