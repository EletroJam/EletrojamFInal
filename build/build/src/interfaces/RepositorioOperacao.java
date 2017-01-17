package interfaces;

import java.sql.Date;
import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Operacao;



public interface RepositorioOperacao {
	
	
	public void insert(Operacao operacao) throws RepositoryException;
	
	//public void update(Celular celular, String numero_celular) throws RepositoryException;
	
	public void delete(int id) throws RepositoryException;
	
	public boolean has (int id ) throws RepositoryException;
	
	public Operacao get(int id) throws RepositoryException;
	
	public int getId() throws RepositoryException;
	
	public ArrayList<Operacao> getOperacaos() throws RepositoryException;

	public ArrayList<String> AllRotas() throws RepositoryException;
	
	public ArrayList<Operacao> getValorCobrador(Date data1, Date data2) throws RepositoryException;
	
	public ArrayList<Operacao> getCobradoresComissao(Date data1, Date data2, boolean x)  throws RepositoryException;
	
	public ArrayList<Operacao> getEntregadorComissao(Date data1, Date data2) throws RepositoryException;
	
	public ArrayList<Operacao> getVendedorComissao(Date data1, Date data2) throws RepositoryException;
	
	public ArrayList<Operacao> getlucroMesVendedor(Date data1, Date data2) throws RepositoryException;
	
	public ArrayList<Operacao> getCobradoresGeral(Date data1, Date data2) throws RepositoryException;
	
	public ArrayList<Operacao> getEmprestimoDiario(Date data1, boolean x, int id) throws RepositoryException;
	
	public ArrayList<Operacao> getResponsavelGeral(Date data1, Date data2, int id) throws RepositoryException;
	
	//public Operacao getMultasResp(Date data1, Date data2, int id, int id_op) throws RepositoryException;
	
	public ArrayList<Operacao> getMultasResp(Date data1, Date data2, int id, int id_op) throws RepositoryException;
}
