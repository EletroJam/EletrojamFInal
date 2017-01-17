package controle;


import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.PessoaJaCadastradaException;
import exceptions.PessoaNaoEncontradaException;
import interfaces.RepositorioPessoa;
import negocio.Pessoa;


public class ControlePessoa {
	
	private RepositorioPessoa pessoas;
	
	public ControlePessoa (RepositorioPessoa pessoas) {
		super();
		this.pessoas = pessoas;
	}
	
	
	public void insert(Pessoa pessoa) throws RepositoryException, PessoaJaCadastradaException {
		if(has(pessoa.getCpf())){
			throw new PessoaJaCadastradaException();
		}else{
			pessoas.insert(pessoa);
		}
	}


	public boolean has(String cpf) throws RepositoryException{
		return pessoas.has(cpf);
	}
	
	public boolean hasIdentidade(String identidade) throws RepositoryException{
		return pessoas.hasIdentidade(identidade);
	}
	
	public boolean hasEmail(String email) throws RepositoryException{
		return pessoas.hasEmail(email);
	}

	//checa se o cpf existe, se existe passa o antigo email e o objeto.
	public void update(Pessoa pessoa, String email) throws RepositoryException, PessoaNaoEncontradaException {
		if(has(pessoa.getCpf())){
			pessoas.update(pessoa, email);
		}else{
			throw new PessoaNaoEncontradaException();
		}		
	}
	
	public void delete (String cpf) throws RepositoryException, PessoaNaoEncontradaException {
		if(has(cpf)){
			pessoas.delete(cpf);
		}else{
			throw new PessoaNaoEncontradaException();
		}		
		
	}


	public Pessoa get(String cpf) throws RepositoryException, PessoaNaoEncontradaException {
		if(has(cpf)){
			return pessoas.get(cpf);
		}else{
			return null;
		}
	}
	
	public Pessoa getIdentidade(String identidade) throws RepositoryException, PessoaNaoEncontradaException {
		if(hasIdentidade(identidade)){
			return pessoas.getIdentidade(identidade);
		}else{
			return null;
		}
	}
	
	public ArrayList<Pessoa> getPessoas() throws RepositoryException {
		return pessoas.getPessoas();
	}
	
	public Pessoa getId(int id) throws RepositoryException, PessoaNaoEncontradaException {
		if(hasId(id)){
		//	System.out.println("ACHOUUUUUUUUUUUUUU O KRL DO ID");
			return pessoas.getId(id);
		}else{
			return null;
		}
	}
	
	public int getIdEndereco(int id) throws RepositoryException{
		return pessoas.getIdEndereco(id);
	}
	
	public int getId (String cpf) throws RepositoryException{
		return pessoas.getId(cpf);
	}
	
	public boolean hasId(int id) throws RepositoryException{
		return pessoas.hasId(id);
	}
	
	public int getIdPessoa () throws RepositoryException {
		return pessoas.getIdPessoa();
	}
}
