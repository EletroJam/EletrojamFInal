package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.FuncionarioJaCadastradoException;
import exceptions.FuncionarioNaoEncontradoException;
import interfaces.RepositorioFuncionario;
import negocio.Funcionario;

public class ControleFuncionario {
	
private RepositorioFuncionario funcionarios;
	
	public ControleFuncionario (RepositorioFuncionario funcionarios) {
		super();
		this.funcionarios = funcionarios;
	}
	
	
	
	public void insert(Funcionario funcionario) throws RepositoryException, FuncionarioJaCadastradoException {
		funcionarios.insert(funcionario);
		
	}
	
	/*
	 * 	public void insert(Funcionario funcionario) throws RepositoryException, FuncionarioJaCadastradoException {
		if(has(funcionario.getCpf())){
			throw new FuncionarioJaCadastradoException();
		}else{
			funcionarios.insert(funcionario);
		}
	}
	
	 */


	public boolean has(String cpf) throws RepositoryException{
		return funcionarios.has(cpf);
	}

	public void update(Funcionario funcionario, int id_funcionario) throws RepositoryException{
		funcionarios.update(funcionario, id_funcionario);
	}
	
	public void updatePessoaFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException{
		funcionarios.updatePessoaFunc(id_funcionario, funcionario);
	}
	
	public void updateEnderecoFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException{
		funcionarios.updateEnderecoFunc(id_funcionario, funcionario);
	}
	
	public void delete (String cpf) throws RepositoryException, FuncionarioNaoEncontradoException {
		if(has(cpf)){
			funcionarios.delete(cpf);
		}else{
			throw new FuncionarioNaoEncontradoException();
		}		
		
	}


	public Funcionario get(String cpf) throws RepositoryException, FuncionarioNaoEncontradoException {
		if(has(cpf)){
			return funcionarios.get(cpf);
		}else{
			return null;
		}
	}
	
	public ArrayList<Funcionario> getFuncionarios() throws RepositoryException {
		return funcionarios.getFuncionarios();
	}
	
	public Funcionario getLogin (String login, String senha) throws RepositoryException, FuncionarioNaoEncontradoException{
		if(hasLogin(login)){
			return funcionarios.getLogin(login, senha);
		}else{
			return null;
		}		
	}
	
	public String getFuncionarioNome (int id) throws RepositoryException{
		return funcionarios.getFuncionarioNome(id);
	}
	
	
	public boolean hasLogin(String login) throws RepositoryException{
		return funcionarios.hasLogin(login);
	}
	
	public ArrayList<Funcionario> getFuncionariosCobradores() throws RepositoryException {
		return funcionarios.getFuncionariosCobradores();
	}

	public ArrayList<Funcionario> getFuncionariosOperadores() throws RepositoryException {
		return funcionarios.getFuncionariosOperadores();
	}

	public ArrayList<Funcionario> getFuncionariosVendedores() throws RepositoryException {
		return funcionarios.getFuncionariosVendedores();
	}

	public ArrayList<Funcionario> getFuncionariosEntregadores() throws RepositoryException {
		return funcionarios.getFuncionariosEntregadores();
	}

	public ArrayList<Funcionario> getFuncionarioPessoa() throws RepositoryException {
		return funcionarios.getFuncionarioPessoa();
	}
	
	public int getFuncionarioId (String nomeF) throws RepositoryException{
		return funcionarios.getFuncionarioId(nomeF);
	}
}

