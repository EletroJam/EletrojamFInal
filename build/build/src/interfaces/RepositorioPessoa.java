package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Pessoa;

public interface RepositorioPessoa {

	public void insert(Pessoa pessoa) throws RepositoryException;
	
	public void update(Pessoa pessoa, String email) throws RepositoryException;
	
	public void delete(String cpf) throws RepositoryException;
	
	public boolean has (String cpf) throws RepositoryException;
	
	public boolean hasEmail (String email) throws RepositoryException;
	
	public boolean hasIdentidade (String identidade) throws RepositoryException;
	
	public boolean hasId(int id) throws RepositoryException;
	
	public Pessoa get(String cpf) throws RepositoryException;
	
	public Pessoa getIdentidade(String identidade) throws RepositoryException;
	
	public Pessoa getId (int id) throws RepositoryException;
	
	public int getId (String cpf) throws RepositoryException;
	
	public int getIdPessoa() throws RepositoryException;
	
	public int getIdEndereco(int id) throws RepositoryException;
	
	//public Blob getPicture(String cpf);
	
	public ArrayList<Pessoa> getPessoas() throws RepositoryException;
	
	
	
}
