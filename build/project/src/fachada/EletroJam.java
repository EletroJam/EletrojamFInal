package fachada;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import conexao.RepositoryException;
import controle.ControleCliente;
import controle.ControleEndereco;
import controle.ControleFuncionario;
import controle.ControleLog;
import controle.ControleOperacao;
import controle.ControleOperacaoCliente;
import controle.ControlePessoa;
import controle.ControleRealiza;
import controle.ControleRota;
import controle.ControleStatus;
import controle.ControleCelular;
import controle.ControleTelefone;
import exceptions.*;
import negocio.Cliente;
import negocio.Endereco;
import negocio.Funcionario;
import negocio.Log;
import negocio.Operacao;
import negocio.OperacaoCliente;
import negocio.Pessoa;
import negocio.Realiza;
import negocio.Rota;
import negocio.Status;
import negocio.Celular;
import negocio.Telefone;
import net.sf.jasperreports.engine.JRException;
import repositorios.RepositorioPessoaDB;
import repositorios.RepositorioRealizaDB;
import repositorios.RepositorioRotaDB;
import repositorios.RepositorioStatusDB;
import repositorios.RepositorioClienteDB;
import repositorios.RepositorioEnderecoDB;
import repositorios.RepositorioFuncionarioDB;
import repositorios.RepositorioLogDB;
import repositorios.RepositorioOperacaoClienteDB;
import repositorios.RepositorioOperacaoDB;
import repositorios.RepositorioCelularDB;
import repositorios.RepositorioTelefoneDB;

public class EletroJam {

	private ControlePessoa controlePessoa;
	private ControleEndereco controleEndereco;
	private ControleCliente controleCliente;
	private ControleFuncionario controleFuncionario;
	private ControleCelular controleCelular;
	private ControleTelefone controleTelefone;
	private ControleOperacao controleOperacao;
	private ControleOperacaoCliente controleOperacaoCliente;
	private ControleStatus controleStatus;
	private ControleRealiza controleRealiza;
	private ControleLog controleLog;
	private ControleRota controleRota;

	private static EletroJam instance;

	public static EletroJam getInstance() {
		if (EletroJam.instance == null) {
			EletroJam.instance = new EletroJam();
		}
		return EletroJam.instance;
	}

	private EletroJam() {
		initCadastro();
	}

	private void initCadastro() {
		
		controlePessoa = new ControlePessoa(RepositorioPessoaDB.getInstance());
		controleEndereco = new ControleEndereco(RepositorioEnderecoDB.getInstance());
		controleCliente = new ControleCliente(RepositorioClienteDB.getInstance());
		controleFuncionario = new ControleFuncionario(RepositorioFuncionarioDB.getInstance());
		controleCelular = new ControleCelular(RepositorioCelularDB.getInstance());
		controleTelefone = new ControleTelefone(RepositorioTelefoneDB.getInstance());
		controleOperacao = new ControleOperacao(RepositorioOperacaoDB.getInstance());
		controleOperacaoCliente = new ControleOperacaoCliente(RepositorioOperacaoClienteDB.getInstance());
		controleStatus = new ControleStatus(RepositorioStatusDB.getInstance());
		controleRealiza = new ControleRealiza(RepositorioRealizaDB.getInstance());
		controleLog = new ControleLog(RepositorioLogDB.getInstance());
		controleRota = new ControleRota(RepositorioRotaDB.getInstance());
	}
	
	public void insertRota(Rota rota) throws RepositoryException{
		controleRota.insert(rota);
	}

	public boolean hasRota (String nome) throws RepositoryException{
		return controleRota.has(nome);
	}
	
	public ArrayList<Rota> getRotas() throws RepositoryException{
		return controleRota.getRotas();
	}
	
	
	public void insertPessoa(Pessoa pessoa) throws RepositoryException, PessoaJaCadastradaException {
		controlePessoa.insert(pessoa);

	}

	public void updatePessoa(Pessoa pessoa, String email) throws RepositoryException, PessoaNaoEncontradaException {
		controlePessoa.update(pessoa, email);
	}

	public boolean hasPessoa(String cpf) throws RepositoryException {
		return controlePessoa.has(cpf);
	}
	
	public boolean hasPessoaIdentidade(String identidade) throws RepositoryException {
		return controlePessoa.hasIdentidade(identidade);
	}
	
	public boolean hasPessoaEmail(String email) throws RepositoryException {
		return controlePessoa.hasEmail(email);
	}
	
	public boolean hasPessoaId(int id) throws RepositoryException {
		return controlePessoa.hasId(id);
	}


	public void deletePessoa(String cpf) throws RepositoryException, PessoaNaoEncontradaException {
		controlePessoa.delete(cpf);
	}

	public Pessoa getPessoa(String cpf) throws RepositoryException, PessoaNaoEncontradaException {
		return controlePessoa.get(cpf);
	}
	
	public Pessoa getPessoaId(int id) throws RepositoryException, PessoaNaoEncontradaException {
		return controlePessoa.getId(id);
	}
	
	public Pessoa getPessoaIdentidade(String identidade) throws RepositoryException, PessoaNaoEncontradaException {
		return controlePessoa.getIdentidade(identidade);
	}
	
	public int getPessoaIdByCpf (String cpf) throws RepositoryException{
		return controlePessoa.getId(cpf);
	}

	public ArrayList<Pessoa> getPessoas() throws RepositoryException {
		return controlePessoa.getPessoas();
	}
	
	public int getIdPessoa () throws RepositoryException{
		return controlePessoa.getIdPessoa();
	}
	
	public int getIdEndereco(int id) throws RepositoryException{
		return controlePessoa.getIdEndereco(id);
	}

	public void insertEndereco(Endereco endereco) throws RepositoryException {
		controleEndereco.insert(endereco);

	}

	public void updateEndereco(Endereco endereco, int id_endereco) throws RepositoryException, EnderecoNaoEncontrado {
		controleEndereco.update(endereco, id_endereco);
	}

	public boolean hasEndereco(int id) throws RepositoryException {
		return controleEndereco.has(id);
	}

	public void deleteEndereco(int id) throws RepositoryException, EnderecoNaoEncontrado {
		controleEndereco.delete(id);
	}

	public Endereco getEndereco(int id) throws RepositoryException, EnderecoNaoEncontrado {
		return controleEndereco.get(id);
	}
	
	public int getEnderecoId() throws RepositoryException{
		return controleEndereco.getId();
	}

	public ArrayList<Endereco> getEnderecos() throws RepositoryException {
		return controleEndereco.getEnderecos();
	}

	public void insertCliente(Cliente cliente) throws RepositoryException, ClienteJaCadastradoException {
		controleCliente.insert(cliente);

	}

	/*
	public void updateCliente(Cliente cliente, String email) throws RepositoryException, ClienteNaoEncontradoException {
		controleCliente.update(cliente, email);
	}
	*/

	public boolean hasCliente(int id) throws RepositoryException {
		return controleCliente.has(id);
	}

	public void deleteCliente(String cpf) throws RepositoryException, ClienteNaoEncontradoException {
		controleCliente.delete(cpf);
	}
	
	public void updateCliente(String apelido, int id_cliente, String nomeFun) throws RepositoryException{
		controleCliente.updateCliente(apelido, id_cliente, nomeFun);
	}
	
	public void updatePessoa(int id_cliente, Cliente cliente) throws RepositoryException{
		controleCliente.updatePessoa(id_cliente, cliente);
	}
	
	public void updateEndereco(int id, Cliente cliente) throws RepositoryException{
		controleCliente.updateEndereco(id, cliente);
	}

	public ArrayList<Cliente> getClientesHistorico(int id) throws RepositoryException{
		return controleCliente.getClientesHistorico(id);
	}
	/*
	public Pessoa getCliente(String cpf) throws RepositoryException, ClienteNaoEncontradoException {
		return controleCliente.get(cpf);
	}
	
	publi
	*/
	
	public Cliente getCliente(int id)throws RepositoryException, ClienteNaoEncontradoException {
		return controleCliente.getCliente(id);
	}

	public ArrayList<Cliente> getClientes() throws RepositoryException {
		return controleCliente.getClientes();
	}
	
	public ArrayList<Cliente> getClientesPessoa() throws RepositoryException {
		return controleCliente.getClientesPessoa();
		
	}
	
	public ArrayList<Cliente> getClientesPorResponsavel(int id) throws RepositoryException{
		return controleCliente.getClientesPorResponsavel(id);
	}

	public void insertFuncionario(Funcionario funcionario)
			throws RepositoryException, FuncionarioJaCadastradoException {
		controleFuncionario.insert(funcionario);

	}

	/*
	public void updateFuncionario(Funcionario funcionario, String email)
			throws RepositoryException, FuncionarioNaoEncontradoException {
		controleFuncionario.update(funcionario, email);
	}

*/
	public boolean hasFuncionario(String cpf) throws RepositoryException {
		return controleFuncionario.has(cpf);
	}

	public void deleteFuncionario(String cpf) throws RepositoryException, FuncionarioNaoEncontradoException {
		controleFuncionario.delete(cpf);
	}
/*
	public Pessoa getFuncionario(String cpf) throws RepositoryException, FuncionarioNaoEncontradoException {
		return controleFuncionario.get(cpf);
	}
*/
	public ArrayList<Funcionario> getFuncionarios() throws RepositoryException {
		return controleFuncionario.getFuncionarios();
	}

	public Funcionario getFuncionarioLogin(String login, String senha) throws RepositoryException, FuncionarioNaoEncontradoException {
		return controleFuncionario.getLogin(login, senha);
	}
	
	public int getFuncionarioId (String nomeF) throws RepositoryException{
		return controleFuncionario.getFuncionarioId(nomeF);
	}

	public boolean hasFuncionarioLogin(String login) throws RepositoryException {
		return controleFuncionario.hasLogin(login);
	}
	
	public ArrayList<Funcionario> getFuncionariosOperadores() throws RepositoryException {
		return controleFuncionario.getFuncionariosOperadores();
	}	
	
	public String getFuncionarioNome (int id) throws RepositoryException{
		return controleFuncionario.getFuncionarioNome(id);
	}

	public ArrayList<Funcionario> getFuncionariosVendedores() throws RepositoryException {
		return controleFuncionario.getFuncionariosVendedores();
	}	
	
	public ArrayList<Funcionario> getFuncionarioPessoa() throws RepositoryException {
		return controleFuncionario.getFuncionarioPessoa();
	}


	public ArrayList<Funcionario> getFuncionariosCobradores() throws RepositoryException {
	return controleFuncionario.getFuncionariosCobradores();
	}	

	public ArrayList<Funcionario> getFuncionariosEntregadores() throws RepositoryException {
	return controleFuncionario.getFuncionariosEntregadores();
	}	
	
	
	public void updateFuncionario(Funcionario funcionario, int id_funcionario) throws RepositoryException{
		controleFuncionario.update(funcionario, id_funcionario);
	}
	
	public void updatePessoaFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException{
		controleFuncionario.updatePessoaFunc(id_funcionario, funcionario);
	}
	
	public void updateEnderecoFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException{
		controleFuncionario.updateEnderecoFunc(id_funcionario, funcionario);
	}


	public void insertCelular(Celular celular) throws RepositoryException {
		controleCelular.insert(celular);
	}
	public void updateCel(String numero_celular, int id) throws RepositoryException{
		controleCelular.updateCel(numero_celular, id);
	}
	public boolean hasCelular(String numero_celular) throws RepositoryException {
		return controleCelular.has(numero_celular);
	}

	public void deleteCelular(String numero_celular) throws RepositoryException, CelularNaoEncontrado {
		controleCelular.delete(numero_celular);
	}

	public Celular getCelular(String numero_celular) throws RepositoryException, CelularNaoEncontrado {
		return controleCelular.get(numero_celular);
	}

	public ArrayList<Celular> getCelulares() throws RepositoryException {
		return controleCelular.getCelulares();
	}

	public void insertTelefone(Telefone telefone) throws RepositoryException {
		controleTelefone.insert(telefone);
	}

	public void updateTelefone(String numero_telefone, int id) throws RepositoryException{
		controleTelefone.updateTelefone(numero_telefone, id);
	}
	
	public boolean hasTelefone(String numero_telefone) throws RepositoryException {
		return controleTelefone.has(numero_telefone);
	}

	public void deleteTelefone(String numero_telefone) throws RepositoryException, TelefoneNaoEncontrado {
		controleTelefone.delete(numero_telefone);
	}

	public Telefone getTelefone(String numero_telefone) throws RepositoryException, TelefoneNaoEncontrado {
		return controleTelefone.get(numero_telefone);
	}

	public ArrayList<Telefone> getTelefones() throws RepositoryException {
		return controleTelefone.getTelefones();
	}
	
	public void inserteOperacao(Operacao operacao) throws RepositoryException {
		controleOperacao.insert(operacao);
	}
	
	public void deleteOperacao(int id) throws RepositoryException, OperacaoNaoEncontrada{
		controleOperacao.delete(id);
	}
	
	
	public ArrayList<Operacao> getVendedorComissao(Date data1, Date data2) throws RepositoryException{
		return controleOperacao.getVendedorComissao(data1, data2);
	}
	
	public ArrayList<Operacao> getlucroMesVendedor(Date data1, Date data2) throws RepositoryException{
		return controleOperacao.getlucroMesVendedor(data1, data2);
	}
	
	public ArrayList<Operacao> getCobradoresComissao(Date data1, Date data2, boolean x) throws RepositoryException{
		return controleOperacao.getCobradoresComissao(data1, data2,x);
	}
	
	public ArrayList<Operacao> getValorCobrador(Date data1, Date data2) throws RepositoryException {
		return controleOperacao.getValorCobrador(data1, data2);
	}
	
	public boolean hasOperacao (int id ) throws RepositoryException {
		return controleOperacao.has(id);
	}
	
	public ArrayList<Operacao> getMultasResp(Date data1, Date data2, int id, int id_op) throws RepositoryException{
		return controleOperacao.getMultasResp(data1, data2, id, id_op);
	}
	
	public int OperacaoId () throws RepositoryException{
		return controleOperacao.getOperacaoId();
	}
	
	public ArrayList<Operacao> getEntregadorComissao(Date data1, Date data2) throws RepositoryException {
		return controleOperacao.getEntregadorComissao(data1, data2);
	}
	public Operacao getOperacao(int id) throws RepositoryException{
		return controleOperacao.get(id);
	}
	
	public ArrayList<String> AllRotas() throws RepositoryException{
		return controleOperacao.AllRotas();
	}
	
	public ArrayList<Operacao> getEmprestimoDiario(Date data1, boolean x, int id) throws RepositoryException{
		return controleOperacao.getEmprestimoDiario(data1, x, id);
	}
	
	public ArrayList<Operacao> getOperacaos() throws RepositoryException{
		return controleOperacao.getOperacaos();
	}
	
	public ArrayList<Operacao> getResponsavelGeral(Date data1, Date data2, int id) throws RepositoryException {
		return controleOperacao.getResponsavelGeral(data1, data2, id);
	}
	
	public ArrayList<Operacao> getCobradoresGeral(Date data1, Date data2) throws RepositoryException{
		return controleOperacao.getCobradoresGeral(data1, data2);
	}
	
	public void insertOperacaoCliente(OperacaoCliente operacaoCliente) throws RepositoryException{
		controleOperacaoCliente.insert(operacaoCliente);
	}
	
	public void deleteOperacaoCliente (int id) throws RepositoryException, OperacaoNaoEncontrada{
		controleOperacaoCliente.delete(id);
	}
	
	public void updateOperacaoCliente (int id, int atraso) throws RepositoryException{
		controleOperacaoCliente.update(id, atraso);
	}
	
	public void updateOperacaoClienteEstado (int id, String estado) throws RepositoryException{
		controleOperacaoCliente.update_estado(id, estado);
	}
	
	public boolean hasOperacaoCliente  (int id ) throws RepositoryException{
		return controleOperacaoCliente.has(id);
	}
	
	public OperacaoCliente getOperacaoCliente(int id) throws RepositoryException{
		return controleOperacaoCliente.get(id);
	}
	
	
	public ArrayList<OperacaoCliente> getOperacaoClientes() throws RepositoryException{
		return controleOperacaoCliente.getOperacaoClientes();
	}
	
	public void insertStatus(Status status) throws RepositoryException{
		controleStatus.insert(status);
	}
	
	public ArrayList<Status> AllStatusCliente(int idOp) throws RepositoryException{
		return controleStatus.AllStatusCliente(idOp);
	}
	
	public ArrayList<Status> AllStatusReceber() throws RepositoryException {
		return controleStatus.AllStatusReceber();
	}
	
	public Double getValorReceber() throws RepositoryException {
		return controleStatus.getValorReceber();
	}
	
	public void relatorioHistoricoGeral (int id, String z, String x) throws JRException{
		controleStatus.relatorioHistoricoGeral(id, z, x);
	}
	
	
	public void updateStatus(boolean status, double valor_recebido, int id, int parcelas, int multas, String tipo_pagamento_dia, int numero_parcelas) throws RepositoryException{
		controleStatus.update(status, valor_recebido, id, parcelas, multas, tipo_pagamento_dia, numero_parcelas);
	}
	
	public void deleteStatus(int id) throws RepositoryException, StatusNaoEncontrado{
		controleStatus.delete(id);
	}
	
	public void updateDataInicial (int id, Status st) throws RepositoryException{
		controleStatus.updateDataInicial(id, st);
	}
	
	public int getIdStatus (int id )throws RepositoryException{
		return controleStatus.getIdStatus(id);
	}
	
	public void deleteStatusUnico(int id) throws RepositoryException {
		controleStatus.deleteStatusUnico(id);
	}
	
	public void getRelatorio(List<Operacao> lista, String x, Date ini, Date fim, int id, String nome, int parcelas, String valorEmprestado, String comissao, String
			valorPassando, String valorCobrador, String total, String totalEntregador, String vlis, String mult, String multaParcela) throws JRException{
		controleStatus.getRelatorio(lista, x, ini, fim, id, nome, parcelas, valorEmprestado, comissao, valorPassando, valorCobrador, total, totalEntregador, vlis, mult, multaParcela);
	}
	
	public boolean hasStatus (int id ) throws RepositoryException{
		return controleStatus.has(id);
	}
	
	public ArrayList<Integer> getAllParc (int id) throws RepositoryException {
		return controleStatus.getAllParc(id);
	}
	
	public void updateUno(int parc_atual , int id) throws RepositoryException{
		controleStatus.updateUno(parc_atual, id);
	}
	
	public Status getStatus(int id) throws RepositoryException{
		return controleStatus.get(id);
	}
	
	public int getIdOperacao(int id) throws RepositoryException{
		return controleStatus.getId(id);
	}
	
	public ArrayList<Double> getSaldoEDiarioTodos (String nomeF, Date data) throws RepositoryException{
		return controleStatus.getSaldoEDiarioTodos(nomeF, data);
	}
	
	
	public ArrayList<Status> AllStatus() throws RepositoryException{
		return controleStatus.getStatus();
	}
	
	public ArrayList<Status> AllStatusCliente() throws RepositoryException{
		return controleStatus.getStatusCliente();
	}
	
	public void relatorioHistoricoUnic (int id, String nome,  String x) throws JRException{
		controleStatus.relatorioHistoricoUnic(id, nome, x);
	}
	
	public String getIdNome (int id) throws RepositoryException{
		return controleStatus.getIdNome(id);
	}
	
	public void relatorioComissaoCobradorEntregador (Date dataIni, Date dataFim,  String x) throws JRException{
		controleStatus.relatorioComissaoCobradorEntregador(dataIni, dataFim, x);
	}
	
	public void relatorioComissaoVendedorCobrador (Date dataIni, Date dataFim,  String x) throws JRException{
		controleStatus.relatorioComissaoVendedorCobrador(dataIni, dataFim, x);
	}
	
	public Double getMulta(Date data, String cobradorNome ) throws RepositoryException {
		return controleStatus.getMulta(data, cobradorNome);
	}
	
	public String getStatusNome (int id) throws RepositoryException {
		return controleStatus.getStatusNome(id);
	}
	
	public Double getParc(Date data, String cobradorNome ) throws RepositoryException {
		return controleStatus.getParc(data, cobradorNome);
	}
	
	public Double getTotal(Date data ) throws RepositoryException {
		return controleStatus.getTotal(data);
	}
	
	public void relatorioComissaoCobradorEntregador2 (Date ini, Date fim,  String x) throws JRException{
		controleStatus.relatorioComissaoCobradorEntregador2(ini, fim, x);
	}
	
	public ArrayList<Double> getSaldoEDiario (String nomeF, Date data) throws RepositoryException{
		return controleStatus.getSaldoEDiario(nomeF, data);
	}
	
	public void relatorioEmprestimoDiario (Date ini, String nomeResp, 
			int id, String total, String valorPedido,
			int parcelas, String valorParcela, String valorMulta,
			String valorPorFora, String x ) throws JRException{
		controleStatus.relatorioEmprestimoDiario(ini, nomeResp, id, total, valorPedido, parcelas, valorParcela, valorMulta, valorPorFora, x);
	}
	
	public void geraRelatorioCobranca (String func, Date data, String x, String rota, String total, String divida) throws JRException{
		controleStatus.geraRelatorioCobranca(func, data, x, rota, total, divida);
	}
	
	public void relatorioResponsavelCliente (int id, String nome, String x) throws JRException{
		controleStatus.relatorioResponsavelCliente(id, nome, x);
	}
	
	
	public void relatorioResponsavel (Date ini, Date fim, int id, String nome, String x, int parcelas, String valorEmprestado, String comissao, String
			valorPassando, String valorCobrador, String total, String totalEntregador, String vlis) throws JRException{
		controleStatus.relatorioResponsavel(ini, fim, id, nome, x, parcelas, valorEmprestado, comissao, valorPassando, valorCobrador, total, totalEntregador, vlis);
	}
	
	public Double getTotalPercas(Date dataIni, Date dataFim ) throws RepositoryException {
		return controleStatus.getTotalPercas(dataIni, dataFim);
	}
	
	public void relatorioDia (String func, Date data, String x, BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) throws RepositoryException, JRException{
		controleStatus.relatorioDia(func, data, x, bigDecimal, bigDecimal2, bigDecimal3);
	}
	
	public void insertRealiza(Realiza realiza) throws RepositoryException{
		controleRealiza.insert(realiza);
	}
	
	public void deleteRealiza(int id_operacao) throws RepositoryException, RealizaNaoEncontrado{
		controleRealiza.delete(id_operacao);
	}
	
	public boolean hasRealiza (int id_operacao ) throws RepositoryException{
		return controleRealiza.has(id_operacao);
	}
	
	public Realiza getRealiza(int id_operacao) throws RepositoryException{
		return controleRealiza.get(id_operacao);
	}
	
	public ArrayList<Realiza> getRealizacao() throws RepositoryException{
		return controleRealiza.getRealizacoes();
	}
	
	public void insert(Log log) throws RepositoryException{
		controleLog.insert(log);
	}
	
	public Log get(int id_funcionario) throws RepositoryException {
		return controleLog.get(id_funcionario);
	}
	
	public ArrayList<Log> getLogs() throws RepositoryException {
		return controleLog.getLogs();
	}
	
	

}
