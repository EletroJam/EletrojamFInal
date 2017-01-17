package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.OperacaoCliente;



public interface RepositorioOperacaoCliente {

	
	public void insert(OperacaoCliente operacaoCliente) throws RepositoryException;
	
	//public void update(Celular celular, String numero_celular) throws RepositoryException;
	
	public void delete(int id) throws RepositoryException;
	
	public boolean has (int id ) throws RepositoryException;
	
	public OperacaoCliente get(int id) throws RepositoryException;
	
	public void update (int id, int atraso) throws RepositoryException;
	
	public ArrayList<OperacaoCliente> getOperacaoClientes() throws RepositoryException;
	
	public void update_estado(int id, String estado) throws RepositoryException;
}
