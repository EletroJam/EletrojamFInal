package controle;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import conexao.RepositoryException;
import exceptions.StatusNaoEncontrado;
import interfaces.RepositorioStatus;
import negocio.Operacao;
import negocio.Status;
import net.sf.jasperreports.engine.JRException;

public class ControleStatus {
	
	private RepositorioStatus statusControle;
	

	public ControleStatus(RepositorioStatus statusControle) {
		super();
		this.statusControle = statusControle;
	}

	public void insert(Status status) throws RepositoryException {
		statusControle.insert(status);
	}

	public boolean has(int id) throws RepositoryException {
		return statusControle.has(id);
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

	public void delete(int id) throws RepositoryException, StatusNaoEncontrado {
		
		statusControle.delete(id);
		

	}

	public Status get(int id) throws RepositoryException {
		if (has(id)) {
			return statusControle.get(id);
		} else {
			return null;
		}
	}

	public ArrayList<Status> getStatus() throws RepositoryException {
		return statusControle.AllStatus();
	}
	
	public void relatorioEmprestimoDiario (Date ini, String nomeResp, 
			int id, String total, String valorPedido,
			int parcelas, String valorParcela, String valorMulta,
			String valorPorFora, String x ) throws JRException{
		statusControle.relatorioEmprestimoDiario(ini, nomeResp, id, total, valorPedido, parcelas, valorParcela, valorMulta, valorPorFora, x);
	}
	
	public ArrayList<Double> getSaldoEDiario (String nomeF, Date data) throws RepositoryException{
		return statusControle.getSaldoEDiario(nomeF, data);
	}
	
	public ArrayList<Status> getStatusCliente() throws RepositoryException {
		return statusControle.AllStatusCliente();
	}
	
	public void updateDataInicial (int id, Status st) throws RepositoryException{
		statusControle.updateDataInicial(id, st);
	}
	
	public void update (boolean status, double valor_recebido, int id, int parcelas, int multas, String tipo_pagamento_dia, int numero_parcelas) throws RepositoryException{
		statusControle.update(status,valor_recebido, id, parcelas, multas, tipo_pagamento_dia, numero_parcelas);
	}
	
	public void deleteStatusUnico(int id) throws RepositoryException {
		statusControle.deleteStatusUnico(id);
	}
	
	public ArrayList<Status> AllStatusReceber() throws RepositoryException {
		return statusControle.AllStatusReceber();
	}
	
	public Double getValorReceber() throws RepositoryException {
		return statusControle.getValorReceber();
	}
	
	public void relatorioResponsavelCliente (int id, String nome, String x) throws JRException{
		statusControle.relatorioResponsavelCliente(id, nome, x);
	}
	
	public ArrayList<Double> getSaldoEDiarioTodos (String nomeF, Date data) throws RepositoryException{
		return statusControle.getSaldoEDiarioTodos(nomeF, data);
	}
	
	public void relatorioResponsavel (Date ini, Date fim, int id, String nome, String x, int parcelas, String valorEmprestado, String comissao, String
			valorPassando, String valorCobrador, String total, String totalEntregador, String vlis) throws JRException{
		statusControle.relatorioResponsavel(ini, fim, id, nome, x, parcelas, valorEmprestado, comissao, valorPassando, valorCobrador, total, totalEntregador, vlis);
	}
	
	public int getId (int id) throws RepositoryException{
		return statusControle.getId(id);
		
	}
	
	public void geraRelatorioCobranca (String func, Date data, String x, String rota, String total, String divida) throws JRException{
		statusControle.relatorioCobranca(func, data,  x, rota, total, divida);
	}
	
	public void relatorioComissaoCobradorEntregador (Date dataIni, Date dataFim,  String x) throws JRException{
		statusControle.relatorioComissaoCobradorEntregador(dataIni, dataFim, x);
	}
	
	
	public int getIdStatus (int id) throws RepositoryException{
		return statusControle.getIdStatus(id);
	}
	
	public String getIdNome (int id) throws RepositoryException{
		return statusControle.getNomeFull(id);
	}
	
	public ArrayList<Integer> getAllParc (int id) throws RepositoryException {
		return statusControle.AllParc(id);
	}
	
	public ArrayList<Status> AllStatusCliente(int idOp) throws RepositoryException{
		return statusControle.AllStatusCliente(idOp);
	}
	
	public void updateUno(int parc_atual , int id) throws RepositoryException{
		statusControle.updateUno(parc_atual, id);
	}
	
	public void relatorioDia (String func, Date data, String x , BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) throws RepositoryException, JRException{
		statusControle.relatorioDia(func, data, x, bigDecimal, bigDecimal2, bigDecimal3);
	}
	
	public Double getMulta(Date data, String cobradorNome ) throws RepositoryException {
		return statusControle.getMulta(data, cobradorNome);
	}
	
	public Double getParc(Date data, String cobradorNome ) throws RepositoryException {
		return statusControle.getParc(data, cobradorNome);
	}
	
	public Double getTotal(Date data ) throws RepositoryException {
		return statusControle.getTotal(data);
	}
	
	public void relatorioHistoricoGeral (int id, String z ,String x) throws JRException{
		statusControle.relatorioHistoricoGeral(id, z, x);
	}
	
	public void relatorioComissaoCobradorEntregador2 (Date ini, Date fim,  String x) throws JRException{
		statusControle.relatorioComissaoCobradorEntregador2(ini, fim, x);
	}
	
	public Double getTotalPercas(Date dataIni, Date dataFim ) throws RepositoryException {
		return statusControle.getTotalPercas(dataIni, dataFim);
	}
	
	public void relatorioHistoricoUnic (int id, String nome,  String x) throws JRException{
		statusControle.relatorioHistoricoUnic(id, nome, x);
	}
	
	public void relatorioComissaoVendedorCobrador (Date dataIni, Date dataFim,  String x) throws JRException{
		statusControle.relatorioComissaoVendedorCobrador(dataIni, dataFim, x);
	}
	
	public String getStatusNome (int id) throws RepositoryException {
		return statusControle.getStatusNome(id);
	}
	
	public void getRelatorio(List<Operacao> lista, String x, Date ini, Date fim, int id, String nome, int parcelas, String valorEmprestado, String comissao, String
			valorPassando, String valorCobrador, String total, String totalEntregador, String vlis, String mult, String multaParcela) throws JRException{
		statusControle.getRelatorio(lista, x, ini, fim, id, nome, parcelas, valorEmprestado, comissao, valorPassando, valorCobrador, total, totalEntregador, vlis, mult, multaParcela);
	}
}
