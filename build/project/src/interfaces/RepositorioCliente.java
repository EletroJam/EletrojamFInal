package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Cliente;

public interface RepositorioCliente {
	
	public void insert(Cliente cliente) throws RepositoryException;
	
	public void update(String apelido, int id_cliente, String nomeFun) throws RepositoryException;
	
	public void updatePessoa(int id_cliente, Cliente cliente) throws RepositoryException;
	
	public void updateEndereco(int id, Cliente cliente) throws RepositoryException;
	
	public void delete(String cpf) throws RepositoryException;
	
	public boolean has (int id) throws RepositoryException;
	
	public Cliente get(int id) throws RepositoryException;
	
	//public Blob getPicture(String cpf);
	
	public ArrayList<Cliente> getClientes() throws RepositoryException;
	
	public ArrayList<Cliente> getClientesPessoa() throws RepositoryException;
	
	public ArrayList<Cliente> getClientesHistorico(int id) throws RepositoryException;
	
	public ArrayList<Cliente> getClientesPorResponsavel(int id) throws RepositoryException;
	

}
