package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Funcionario;

public interface RepositorioFuncionario {

	public void insert(Funcionario funcionario) throws RepositoryException;
	
	public ArrayList<Funcionario> getFuncionarioPessoa() throws RepositoryException;
	
	public void update(Funcionario funcionario, int id_funcionario) throws RepositoryException;
	
	public void updatePessoaFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException;
	
	public void updateEnderecoFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException;
	
	public void delete(String cpf) throws RepositoryException;
	
	public boolean has (String cpf) throws RepositoryException;
	
	public Funcionario get(String cpf) throws RepositoryException;
	
	public Funcionario getLogin (String login, String senha) throws RepositoryException;
	
	public boolean hasLogin (String login) throws RepositoryException;
	
	//public Blob getPicture(String cpf);
	
	public ArrayList<Funcionario> getFuncionarios() throws RepositoryException;
	
	public ArrayList<Funcionario> getFuncionariosCobradores() throws RepositoryException;

	public ArrayList<Funcionario> getFuncionariosVendedores() throws RepositoryException;

	public ArrayList<Funcionario> getFuncionariosOperadores() throws RepositoryException;
	
	public ArrayList<Funcionario> getFuncionariosEntregadores() throws RepositoryException;	
	
	public String getFuncionarioNome (int id) throws RepositoryException;	
	
	public int getFuncionarioId (String nomeF) throws RepositoryException;
		
}
