package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoEncontradoException;
import interfaces.RepositorioCliente;
import negocio.Cliente;


public class ControleCliente {

private RepositorioCliente clientes;
	
	public ControleCliente (RepositorioCliente clientes) {
		super();
		this.clientes = clientes;
	}
	
	
	
	public void insert(Cliente cliente) throws RepositoryException, ClienteJaCadastradoException {
		clientes.insert(cliente);
		
	}
	/*
	  public void insert(Cliente cliente) throws RepositoryException, ClienteJaCadastradoException {
		if(has(cliente.getCpf())){
			throw new ClienteJaCadastradoException();
		}else{
			clientes.insert(cliente);
		}
	}
	
	 */


	public boolean has(int id) throws RepositoryException{
		return clientes.has(id);
	}

	//checa se o cpf existe, se existe passa o antigo email e o objeto.
/*	public void update(Cliente cliente, String email) throws RepositoryException, ClienteNaoEncontradoException {
		if(has(cliente.getCpf())){
			clientes.update(cliente, email);
		}else{
			throw new ClienteNaoEncontradoException();
		}		
	}
	*/
	
	public void delete (String cpf) throws RepositoryException, ClienteNaoEncontradoException {
	//	if(has(cpf)){
			clientes.delete(cpf);
		//}else{
		//	throw new ClienteNaoEncontradoException();
		//}		
		
	}

	public Cliente getCliente(int id) throws RepositoryException, ClienteNaoEncontradoException {
		if(has(id)){
			return clientes.get(id);
		}else{
			return null;
		}
	}
	
	public ArrayList<Cliente> getClientes() throws RepositoryException {
		return clientes.getClientes();
	}
	
	public ArrayList<Cliente> getClientesPessoa() throws RepositoryException {
		return clientes.getClientesPessoa();
		
	}
	
	public void updateCliente(String apelido, int id_cliente, String nomeFun) throws RepositoryException{
		clientes.update(apelido, id_cliente, nomeFun);
	}
	
	public void updatePessoa(int id_cliente, Cliente cliente) throws RepositoryException{
		clientes.updatePessoa(id_cliente, cliente);
	}
	
	public void updateEndereco(int id, Cliente cliente) throws RepositoryException{
		clientes.updateEndereco(id, cliente);
	}
	
	public ArrayList<Cliente> getClientesHistorico(int id) throws RepositoryException{
		return clientes.getClientesHistorico(id);
	}
	
	public ArrayList<Cliente> getClientesPorResponsavel(int id) throws RepositoryException{
		return clientes.getClientesPorResponsavel(id);
	}
	
}


