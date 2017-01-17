package controle;

import java.sql.Date;
import java.util.ArrayList;

import conexao.RepositoryException;
import exceptions.OperacaoNaoEncontrada;
import interfaces.RepositorioOperacao;
import negocio.Operacao;


public class ControleOperacao {
	
	private RepositorioOperacao operacoes;

	public ControleOperacao(RepositorioOperacao operacoes) {
		super();
		this.operacoes = operacoes;
	}

	public void insert(Operacao operacao) throws RepositoryException {
		operacoes.insert(operacao);
	}

	public boolean has(int id) throws RepositoryException {
		return operacoes.has(id);
	}

	/*
	public void update(Telefone telefone, String numero_telefone) throws RepositoryException, TelefoneNaoEncontrado {
		// Pega número antigo, pois o passado como parâmetro é o que vai ser
		// atualizado
		String oldNumero = telefone.getNumero_telefone();
		if (has(oldNumero)) {
			telefones.update(telefone, numero_telefone);
		} else {
			throw new TelefoneNaoEncontrado();
		}
	}
	*/

	public void delete(int id) throws RepositoryException, OperacaoNaoEncontrada {
		
		operacoes.delete(id);
		

	}

	public Operacao get(int id) throws RepositoryException {
		if (has(id)) {
			return operacoes.get(id);
		} else {
			return null;
		}
	}

	public ArrayList<Operacao> getOperacaos() throws RepositoryException {
		return operacoes.getOperacaos();
	}
	
	public int getOperacaoId () throws RepositoryException{
		return operacoes.getId();
	}
	
	public ArrayList<String> AllRotas() throws RepositoryException{
		return operacoes.AllRotas();
	}
	
	public ArrayList<Operacao> getCobradoresComissao(Date data1, Date data2, boolean x) throws RepositoryException{
		return operacoes.getCobradoresComissao(data1, data2, x);
	}
	
	public ArrayList<Operacao> getEntregadorComissao(Date data1, Date data2) throws RepositoryException {
		return operacoes.getEntregadorComissao(data1, data2);
	}
	
	public ArrayList<Operacao> getValorCobrador(Date data1, Date data2) throws RepositoryException {
		return operacoes.getValorCobrador(data1, data2);
	}
	
	
	public ArrayList<Operacao> getVendedorComissao(Date data1, Date data2) throws RepositoryException{
		return operacoes.getVendedorComissao(data1, data2);
	}
	
	public ArrayList<Operacao> getlucroMesVendedor(Date data1, Date data2) throws RepositoryException{
		return operacoes.getlucroMesVendedor(data1, data2);
	}
	
	public ArrayList<Operacao> getCobradoresGeral(Date data1, Date data2) throws RepositoryException{
		return operacoes.getCobradoresGeral(data1, data2);
	}

	public ArrayList<Operacao> getEmprestimoDiario(Date data1, boolean x, int id) throws RepositoryException{
		return operacoes.getEmprestimoDiario(data1, x, id);
	}
	
	public ArrayList<Operacao> getResponsavelGeral(Date data1, Date data2, int id) throws RepositoryException {
		return operacoes.getResponsavelGeral(data1, data2, id);
	}
	
	public ArrayList<Operacao> getMultasResp(Date data1, Date data2, int id, int id_op) throws RepositoryException{
		return operacoes.getMultasResp(data1, data2, id, id_op);
	}

	
}
