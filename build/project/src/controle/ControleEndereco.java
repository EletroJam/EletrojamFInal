package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.EnderecoNaoEncontrado;
import interfaces.RepositorioEndereco;
import negocio.Endereco;

public class ControleEndereco {

	private RepositorioEndereco enderecos;
	
	public ControleEndereco(RepositorioEndereco enderecos) {
		super();
		this.enderecos = enderecos;
	}
	
	public void insert(Endereco endereco) throws RepositoryException {
		enderecos.insert(endereco);
	}

	public boolean has(int id) throws RepositoryException {
		return enderecos.has(id);
	}
	
	public void update(Endereco endereco, int id_endereco) throws RepositoryException, EnderecoNaoEncontrado {
		if(has(id_endereco)){
			enderecos.update(endereco, id_endereco);
		}else{
			throw new EnderecoNaoEncontrado();
		}		
	}
	
	public int getId() throws RepositoryException{
		return enderecos.getId();
	}
	
	public void delete (int id) throws RepositoryException, EnderecoNaoEncontrado {
		if(has(id)){
			enderecos.delete(id);
		}else{
			throw new EnderecoNaoEncontrado();
		}		
		
	}
	
	public Endereco get(int id) throws RepositoryException, EnderecoNaoEncontrado {
		if(has(id)){
			return enderecos.get(id);
		}else{
			throw new EnderecoNaoEncontrado();
		}
	}
	
	public ArrayList<Endereco> getEnderecos() throws RepositoryException {
		return enderecos.getEnderecos();
	}
}
