package interfaces;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import conexao.RepositoryException;
import negocio.Operacao;
import negocio.Status;
import net.sf.jasperreports.engine.JRException;

public interface RepositorioStatus {
	
	public void insert(Status status) throws RepositoryException;
	
	public void update(boolean status, double valor_recebido, int id, int parcelas, int multas, String tipo_pagamento_dia, int numero_parcelas) throws RepositoryException;
	
	public void updateUno(int parc_atual, int id) throws RepositoryException;
	
	public void delete(int id) throws RepositoryException;
	
	public boolean has (int id ) throws RepositoryException;
	
	public Status get(int id) throws RepositoryException;
	
	public int getId(int id ) throws RepositoryException;

	public int getIdStatus(int id ) throws RepositoryException;
	
	public void relatorioCobranca (String func, Date data, String x, String rota, String total, String divida) throws JRException ;
	
	public void relatorioDia (String func, Date data, String x, BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) throws JRException ;
	
	public String getNomeFull(int id ) throws RepositoryException;
	
	public ArrayList<Status> AllStatus() throws RepositoryException;
	public ArrayList<Status> AllStatusCliente() throws RepositoryException;
	
	public ArrayList<Integer> AllParc( int id) throws RepositoryException ;
	
	public Double getTotal(Date data ) throws RepositoryException;
	
	public Double getParc(Date data , String cobradorNome) throws RepositoryException;
	
	public Double getMulta(Date data, String cobradorNome ) throws RepositoryException;
	
	public void relatorioComissaoCobradorEntregador (Date dataIni, Date dataFim,  String x) throws JRException;
	
	public ArrayList<Status> AllStatusCliente(int idOp) throws RepositoryException;
	
	public void updateDataInicial (int id, Status st) throws RepositoryException;
	
	public Double getValorReceber() throws RepositoryException;
	
	public void relatorioComissaoCobradorEntregador2 (Date ini, Date fim,  String x) throws JRException;
	
	public Double getTotalPercas(Date dataIni, Date dataFim ) throws RepositoryException;
	
	public void relatorioHistoricoGeral (int id,  String z,String x) throws JRException;
	
	public void relatorioHistoricoUnic (int id, String nome,  String x) throws JRException;
	
	public void relatorioComissaoVendedorCobrador (Date dataIni, Date dataFim,  String x) throws JRException;
	
	public String getStatusNome (int id) throws RepositoryException;
	
	public ArrayList<Double> getSaldoEDiario (String nomeF, Date data) throws RepositoryException;
	
	public ArrayList<Double> getSaldoEDiarioTodos (String nomeF, Date data) throws RepositoryException;
	
	public ArrayList<Status> AllStatusReceber() throws RepositoryException;

	
	public void relatorioEmprestimoDiario (Date ini, String nomeResp, 
			int id, String total, String valorPedido,
			int parcelas, String valorParcela, String valorMulta,
			String valorPorFora, String x ) throws JRException;
	
	public void relatorioResponsavelCliente (int id, String nome, String x) throws JRException;
	
	public void relatorioResponsavel (Date ini, Date fim, int id, String nome, String x, int parcelas, String valorEmprestado, String comissao, String
			valorPassando, String valorCobrador, String total, String totalEntregador, String vlis) throws JRException;
	
	public void deleteStatusUnico(int id) throws RepositoryException ;
	
	public void getRelatorio(List<Operacao> lista, String x, Date ini, Date fim, int id, String nome, int parcelas, String valorEmprestado, String comissao, String
			valorPassando, String valorCobrador, String total, String totalEntregador, String vlis, String mult, String multaParcela) throws JRException;
	
	
}
