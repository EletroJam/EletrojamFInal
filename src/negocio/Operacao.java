package negocio;

import java.io.Serializable;
import java.sql.Date;

public class Operacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2456368572260999671L;
	private int id;
	private int parcelas;
	private double valor_juros;
	private double valor_pedido;
	private double valorDiario;
	private int multa;
	private String rota;
	private double porfora;
	private double valor_cobrador_multa;
	
	private double valorEmprestado;
	private double comissao;
	private double valorPassando;
	//private double valorMulta;
	private double total;
	
	private String tipo;
	private int porcentagemLucroFuncionario;
	private double valorCobrador;
	String nomevendedor;

	private double lucroVendedorRel;
	private double cobradorReceb;
	
	private double valorCobradorNovo;
	

	public String getNomevendedor() {
		return nomevendedor;
	}

	public void setNomevendedor(String nomevendedor) {
		this.nomevendedor = nomevendedor;
	}

	private double extra;
	private int id_funcionario_cobrador;
	private int id_funcionario_venda;
	private double valorLucro;
	private double lucroMesFuncionario;
	private double porcentagemLucro;
	
	private String nomeVendedorResponsavel;
	
	private int id_funcionario_entregador;
	private Date data_operacao_realizada; 
	
	private double diario;
	private double multa_diaria;
	private String nomecobrador;
	private double totalcobrador;
	private double totalPass;
	
	private int id_entregador;
	private double valorEntregador;
	
	private double entregaLuc;
	private double recerbLuc;
	
	//novo para clientes datas especificas
	private int flag;
	private int intervalo;
	private double valorLis;
	
	private double numMulta;
	private double multaPassando;
	
	private String apelido;
	
	private double lucroEntregador;
	
	public Operacao(String nomeentregador, Date data_operacao_realizada, int parcelas, double valorEmprestado, 
			double comissao, double valorPassando, double valorCobrador, double total,int id_entregador, 
			double valorEntregador,double valorLis, double numMulta, int id, double multaPassando){
		this.nomeentregador= nomeentregador;
		this.data_operacao_realizada = data_operacao_realizada;
		this.parcelas = parcelas;
		this.valorEmprestado = valorEmprestado;
		this.comissao = comissao;
		this.valorPassando = valorPassando;
		this.valorCobrador = valorCobrador;
		this.total = total;
		this.id_entregador = id_entregador;
		this.valorEntregador = valorEntregador;
		this.valorLis =  valorLis;
		this.numMulta = numMulta;
		this.id = id;
		this.multaPassando = multaPassando;
	}
	
	
	  public String toString() {
	        return nomeentregador;
	    }

	
	public int getId_entregador() {
		return id_entregador;
	}

	public void setId_entregador(int id_entregador) {
		this.id_entregador = id_entregador;
	}

	public double getValorEntregador() {
		return valorEntregador;
	}

	public void setValorEntregador(double valorEntregador) {
		this.valorEntregador = valorEntregador;
	}

	public double getLucroEntregador() {
		return lucroEntregador;
	}

	public void setLucroEntregador(double lucroEntregador) {
		this.lucroEntregador = lucroEntregador;
	}

	public String getNomeentregador() {
		return nomeentregador;
	}

	public void setNomeentregador(String nomeentregador) {
		this.nomeentregador = nomeentregador;
	}

	public double getTotal_entregador() {
		return total_entregador;
	}

	public void setTotal_entregador(double total_entregador) {
		this.total_entregador = total_entregador;
	}

	private String nomeentregador;
	private double total_entregador;
	
	
	private int porcentagem_lucro_entregador;
	
	public Operacao(String nomeentregador, double total_entregador, double lucroVendedorRel ){
		this.nomeentregador= nomeentregador;
		this.total_entregador = total_entregador;
		this.lucroVendedorRel = lucroVendedorRel;
	}
	
	public Operacao(String nomeentregador, double total_entregador  ){
		this.nomeentregador= nomeentregador;
		this.total_entregador = total_entregador;
		
	}
	
	//pega os valores do cobrador a receber
	public Operacao(double valorCobradorNovo, double valorCobrador, double total_entregador, double lucroVendedorRel ){
		this.valorCobradorNovo = valorCobradorNovo;
		this.valorCobrador = valorCobrador;
		this.total_entregador = total_entregador;
		this.lucroVendedorRel = lucroVendedorRel;
	}
	
	
	
	//contrutor para vendedor pegando de valores existentes, atributos com nomes sem sentido
	public Operacao(String nomecobrador, double total_entregador, double valorCobrador ,double diario, double  multa_diaria, double totalcobrador ,double total,
			double lucroVendedorRel, double cobradorReceb, double totalPass, double entregaLuc, double recerbLuc){
		this.nomecobrador = nomecobrador;
		this.total_entregador = total_entregador;
		this.valorCobrador = valorCobrador;
		this.diario = diario;
		this.multa_diaria= multa_diaria;
		this.totalcobrador = totalcobrador;
		this.total = total;
		this.cobradorReceb = cobradorReceb;
		this.lucroVendedorRel = lucroVendedorRel;
		this.totalPass = totalPass;
		this.entregaLuc = entregaLuc ;
		this.recerbLuc = recerbLuc;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Operacao(String nomecobrador,  double diario, double multa_diaria, Double totalcobrador){
		this.nomecobrador = nomecobrador;
		this.diario = diario;
		this.multa_diaria = multa_diaria;
		this.totalcobrador = totalcobrador;
	}
	/*
	public Operacao(String nomecobrador,  double diario, double multa_diaria, Double totalcobrador, double lucroMesFuncionario){
		this.nomecobrador = nomecobrador;
		this.diario = diario;
		this.multa_diaria = multa_diaria;
		this.totalcobrador = totalcobrador;
		this.lucroMesFuncionario = lucroMesFuncionario;
	}*/
	
	
	public Operacao(  double diario, double multa_diaria,String nomecobrador, Double totalcobrador, double lucroMesFuncionario){
		this.nomecobrador = nomecobrador;
		this.diario = diario;
		this.multa_diaria = multa_diaria;
		this.totalcobrador = totalcobrador;
		this.lucroMesFuncionario = lucroMesFuncionario;
	}
	
	public Operacao(String nomecobrador,  double diario, double multa_diaria, Double totalcobrador, int intervalo){
		this.nomecobrador = nomecobrador;
		this.diario = diario;
		this.multa_diaria = multa_diaria;
		this.totalcobrador = totalcobrador;
		this.intervalo = intervalo;
		
	}
	
	
	public Operacao (int parcelas, double valor_juros, double valor_pedido, double valorDiario, int multa, String tipo, int porcentagemLucroFuncionario, double valorCobrador, 
			double extra, int id_funcionario_cobrador, int id_funcionario_venda, double valorLucro,  double lucroMesFuncionario, double porcentagemLucro, int id_funcionario_entregador
			, Date data_operacao_realizada, int porcentagem_lucro_entregador, double porfora,double valor_cobrador_multa, int flag, int intervalo){
		 
		this.parcelas = parcelas;
		this.valor_juros = valor_juros;
		this.valor_pedido = valor_pedido;
		this.valorDiario = valorDiario;
		this.multa = multa;
		this.tipo = tipo;
		this.porcentagemLucroFuncionario = porcentagemLucroFuncionario;
		this.valorCobrador = valorCobrador;
		this.extra = extra;
		this.id_funcionario_cobrador = id_funcionario_cobrador;
		this.id_funcionario_venda = id_funcionario_venda;
		this.valorLucro =valorLucro;
		this.lucroMesFuncionario = lucroMesFuncionario;
		this.porcentagemLucro = porcentagemLucro;
		this.id_funcionario_entregador = id_funcionario_entregador;
		this.data_operacao_realizada = data_operacao_realizada;
		this.porcentagem_lucro_entregador = porcentagem_lucro_entregador;
		
		this.porfora = porfora;
		this.valor_cobrador_multa =  valor_cobrador_multa;
		this.flag = flag;
		this.intervalo = intervalo;
		
	}
	
	public double getValor_cobrador_multa() {
		return valor_cobrador_multa;
	}

	public void setValor_cobrador_multa(double valor_cobrador_multa) {
		this.valor_cobrador_multa = valor_cobrador_multa;
	}

	public double getPorfora() {
		return porfora;
	}

	public void setPorfora(double porfora) {
		this.porfora = porfora;
	}

	public double getDiario() {
		return diario;
	}

	public void setDiario(double diario) {
		this.diario = diario;
	}

	public double getMulta_diaria() {
		return multa_diaria;
	}

	public void setMulta_diaria(double multa_diaria) {
		this.multa_diaria = multa_diaria;
	}

	public String getNomecobrador() {
		return nomecobrador;
	}

	public void setNomecobrador(String nomecobrador) {
		this.nomecobrador = nomecobrador;
	}

	public int getId_funcionario_entregador() {
		return id_funcionario_entregador;
	}

	public void setId_funcionario_entregador(int id_funcionario_entregador) {
		this.id_funcionario_entregador = id_funcionario_entregador;
	}

	public Date getData_operacao_realizada() {
		return data_operacao_realizada;
	}

	public void setData_operacao_realizada(Date data_operacao_realizada) {
		this.data_operacao_realizada = data_operacao_realizada;
	}


	
	public Double getTotalcobrador() {
		return totalcobrador;
	}

	public void setTotalcobrador(Double totalcobrador) {
		this.totalcobrador = totalcobrador;
	}

	public Operacao (int id, int parcelas, double valor_juros, double valor_pedido, double valorDiario, int multa, String tipo, int porcentagemLucroFuncionario, double valorCobrador, 
			double extra, int id_funcionario_cobrador, int id_funcionario_venda, double valorLucro,  double lucroMesFuncionario, double porcentagemLucro, int id_funcionario_entregador
			, Date data_operacao_realizada, int porcentagem_lucro_entregador ,double porfora,double valor_cobrador_multa, int flag, int intervalo ){
		 
		this.id = id;
		this.parcelas = parcelas;
		this.valor_juros = valor_juros;
		this.valor_pedido = valor_pedido;
		this.valorDiario = valorDiario;
		this.multa = multa;
		this.tipo = tipo;
		this.porcentagemLucroFuncionario = porcentagemLucroFuncionario;
		this.valorCobrador = valorCobrador;
		this.extra = extra;
		this.id_funcionario_cobrador = id_funcionario_cobrador;
		this.id_funcionario_venda = id_funcionario_venda;
		this.valorLucro =valorLucro;
		this.lucroMesFuncionario = lucroMesFuncionario;
		this.porcentagemLucro = porcentagemLucro;
		this.id_funcionario_entregador = id_funcionario_entregador;
		this.data_operacao_realizada = data_operacao_realizada;
		this.porcentagem_lucro_entregador = porcentagem_lucro_entregador;

		this.porfora = porfora;
		this.valor_cobrador_multa =  valor_cobrador_multa;
		this.flag = flag;
		this.intervalo = intervalo;
	}
	
	public Operacao(String nomecobrador, double valorpedido, int parcelas, double diario, double multa, double profora, String nomeVendedorResponsavel) {
		this.nomecobrador = nomecobrador;
		this.valor_pedido = valorpedido;
		this.parcelas = parcelas;
		this.valorDiario=diario;
		this.valor_juros=multa;
		this.porfora = profora;
		this.nomeVendedorResponsavel = nomeVendedorResponsavel;
	}
	
	public Operacao(double numMulta, int id, String apelido, Date data_operacao_realizada) {
		this.numMulta = numMulta;
		this.id = id;
		this.apelido = apelido;
		this.data_operacao_realizada = data_operacao_realizada;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public int getPorcentagem_lucro_entregador() {
		return porcentagem_lucro_entregador;
	}

	public void setPorcentagem_lucro_entregador(int porcentagem_lucro_entregador) {
		this.porcentagem_lucro_entregador = porcentagem_lucro_entregador;
	}

	public double getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(double valorDiario) {
		this.valorDiario = valorDiario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public double getValor_juros() {
		return valor_juros;
	}

	public void setValor_juros(double valor_juros) {
		this.valor_juros = valor_juros;
	}

	public double getValor_pedido() {
		return valor_pedido;
	}

	public void setValor_pedido(double valor_pedido) {
		this.valor_pedido = valor_pedido;
	}

	public int getMulta() {
		return multa;
	}

	public void setMulta(int multa) {
		this.multa = multa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getPorcentagemLucroFuncionario() {
		return porcentagemLucroFuncionario;
	}

	public void setPorcentagemLucroFuncionario(int porcentagemLucroFuncionario) {
		this.porcentagemLucroFuncionario = porcentagemLucroFuncionario;
	}

	public double getValorCobrador() {
		return valorCobrador;
	}

	public void setValorCobrador(double valorCobrador) {
		this.valorCobrador = valorCobrador;
	}

	public double getExtra() {
		return extra;
	}

	public void setExtra(double extra) {
		this.extra = extra;
	}

	public int getId_funcionario_cobrador() {
		return id_funcionario_cobrador;
	}

	public void setId_funcionario_cobrador(int id_funcionario_cobrador) {
		this.id_funcionario_cobrador = id_funcionario_cobrador;
	}

	public int getId_funcionario_venda() {
		return id_funcionario_venda;
	}

	public void setId_funcionario_venda(int id_funcionario_venda) {
		this.id_funcionario_venda = id_funcionario_venda;
	}

	public double getValorLucro() {
		return valorLucro;
	}

	public void setValorLucro(double valorLucro) {
		this.valorLucro = valorLucro;
	}

	public double getLucroMesFuncionario() {
		return lucroMesFuncionario;
	}

	public void setLucroMesFuncionario(double lucroMesFuncionario) {
		this.lucroMesFuncionario = lucroMesFuncionario;
	}

	public double getPorcentagemLucro() {
		return porcentagemLucro;
	}

	public void setPorcentagemLucro(double porcentagemLucro) {
		this.porcentagemLucro = porcentagemLucro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getLucroVendedorRel() {
		return lucroVendedorRel;
	}

	public void setLucroVendedorRel(double lucroVendedorRel) {
		this.lucroVendedorRel = lucroVendedorRel;
	}

	public double getCobradorReceb() {
		return cobradorReceb;
	}

	public void setCobradorReceb(double cobradorReceb) {
		this.cobradorReceb = cobradorReceb;
	}

	public double getValorCobradorNovo() {
		return valorCobradorNovo;
	}

	public void setValorCobradorNovo(double valorCobradorNovo) {
		this.valorCobradorNovo = valorCobradorNovo;
	}

	public Double getTotalPass() {
		return totalPass;
	}

	public void setTotalPass(Double totalPass) {
		this.totalPass = totalPass;
	}

	public double getEntregaLuc() {
		return entregaLuc;
	}

	public void setEntregaLuc(double entregaLuc) {
		this.entregaLuc = entregaLuc;
	}

	public double getRecerbLuc() {
		return recerbLuc;
	}

	public void setRecerbLuc(double recerbLuc) {
		this.recerbLuc = recerbLuc;
	}

	public void setTotalcobrador(double totalcobrador) {
		this.totalcobrador = totalcobrador;
	}

	public void setTotalPass(double totalPass) {
		this.totalPass = totalPass;
	}

	public double getValorEmprestado() {
		return valorEmprestado;
	}

	public void setValorEmprestado(double valorEmprestado) {
		this.valorEmprestado = valorEmprestado;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public double getValorPassando() {
		return valorPassando;
	}

	public void setValorPassando(double valorPassando) {
		this.valorPassando = valorPassando;
	}

	public double getValorLis() {
		return valorLis;
	}

	public void setValorLis(double valorLis) {
		this.valorLis = valorLis;
	}

	public double getNumMulta() {
		return numMulta;
	}

	public void setNumMulta(double numMulta) {
		this.numMulta = numMulta;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public double getMultaPassando() {
		return multaPassando;
	}

	public void setMultaPassando(double multaPassando) {
		this.multaPassando = multaPassando;
	}

	public String getNomeVendedorResponsavel() {
		return nomeVendedorResponsavel;
	}

	public void setNomeVendedorResponsavel(String nomeVendedorResponsavel) {
		this.nomeVendedorResponsavel = nomeVendedorResponsavel;
	}

	


	
	
}
