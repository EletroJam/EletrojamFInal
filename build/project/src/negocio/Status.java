package negocio;

import java.io.Serializable;
import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import util.Main.Typ;

public class Status implements Serializable {
	
	private String cliente;
	private String cobrador;
	private String nome_cliente_todos;
	private int parc_paga;
	private int atraso_parc;
	
	private SimpleStringProperty multasO;
	private SimpleStringProperty parcelasO;

	
	public String getNome_cliente_todos() {
		return nome_cliente_todos;
	}
	public void setNome_cliente_todos(String nome_cliente_todos) {
		this.nome_cliente_todos = nome_cliente_todos;
	}

	private String func;
	

	private int parcelas;
	private int multas;
	
	private int atraso;
	private int parcela_atual;
	//private Date data_pagamento;



	 public int getAtraso() {
		return atraso;
	}
	public void setAtraso(int atraso) {
		this.atraso = atraso;
	}
	public int getParcela_atual() {
		return parcela_atual;
	}
	public void setParcela_atual(int parcela_atual) {
		this.parcela_atual = parcela_atual;
	}

	public String getMultasO() {
			
	        return multasO.get();
	    }
	public void setMultasO(String s) {
		
	    	multasO.set(s);
	    }
		
	public String getParcelasO() {
		
	    	return parcelasO.get();
	    }
	    public void setParcelasO(String s) {
		
	    	parcelasO.set(s);
	    }
		



	private int id;
	private Date data;
	private int numero_parcelas;
	private double valor_Recebido;
	private int id_operacao;
	private String tipo_pagamento; // tipo de pagamento do cara
	private String tipo_pagamento_dia; // multa, parcela...
	private Date dataInicialPagamento;
	private int multa;
	private boolean check_pag;
	private Double receber;
	
	Date dataAlvoSequencial;
	 
	private SimpleStringProperty firstName;
	private SimpleObjectProperty typ;
	private SimpleObjectProperty birthday;
	
	
	public Date getDataInicialPagamento() {
		return dataInicialPagamento;
	}

	public int getParcelas() {
		return parcelas;
	}
	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	public int getMultas() {
		return multas;
	}
	public void setMultas(int multas) {
		this.multas = multas;
	}
	public void setDataInicialPagamento(Date dataInicialPagamento) {
		this.dataInicialPagamento = dataInicialPagamento;
	}
	
	public Status(String cliente, Date dataInicialPagamento, Double receber){
		this.cliente = cliente;
		this.dataInicialPagamento = dataInicialPagamento;
		this.receber = receber;
	}

	public Status (int id, int numero_parcelas, double valor_Recebido, int id_operacao, String tipo_pagamento, 
			String tipo_pagamento_dia, Date dataInicialPagamento, int multa,boolean check_pag , int parcelas, int multas,
			int atraso, int parcela_atual, String nome_cliente_todos, int parc_paga, int atraso_parc, String cobrador , Date dataAlvoSequencial){
		
		this.id = id;
		this.numero_parcelas = numero_parcelas;
		this.valor_Recebido = valor_Recebido;
		this.valor_Recebido = valor_Recebido;
		this.dataInicialPagamento = dataInicialPagamento;
		this.id_operacao = id_operacao;
		this.tipo_pagamento = tipo_pagamento;
		this.tipo_pagamento_dia = tipo_pagamento_dia;
		this.multa = multa;
		this.check_pag = check_pag;
		this.parcelas = parcelas;
		this.multas = multas;
		this.atraso = atraso;
		this.parcela_atual = parcela_atual;
		this.nome_cliente_todos = nome_cliente_todos;
		this.parc_paga = parc_paga;
		this.atraso_parc = atraso_parc;
		this.cobrador = cobrador;
		//this.data_pagamento = data_pagamento;
		this.dataAlvoSequencial = dataAlvoSequencial;

		
	}
	
	public Status ( int numero_parcelas, double valor_Recebido, int id_operacao, String tipo_pagamento, String tipo_pagamento_dia, 
			Date dataInicialPagamento, int multa, boolean check_pag,int parcelas, int multas,
			int atraso, int parcela_atual, String nome_cliente_todos, int parc_paga, int atraso_parc, String func, Date dataAlvoSequencial) {
		
		 //String nome_cliente_todos
		this.numero_parcelas = numero_parcelas;
		this.valor_Recebido = valor_Recebido;
		this.id_operacao = id_operacao;
		this.tipo_pagamento = tipo_pagamento;
		this.tipo_pagamento_dia = tipo_pagamento_dia;
		this.dataInicialPagamento = dataInicialPagamento;
		this.multa = multa;
		this.check_pag = check_pag;
		this.parcelas = parcelas;
		this.multas = multas;
		this.atraso = atraso;
		this.parcela_atual = parcela_atual;
		this.nome_cliente_todos = nome_cliente_todos;
		this.parc_paga = parc_paga;
		this.atraso_parc = atraso_parc;
		this.func = func;
	//	this.data_pagamento = data_pagamento;
		this.dataAlvoSequencial = dataAlvoSequencial;

		
	}
	
	public int getParc_paga() {
		return parc_paga;
	}
	public void setParc_paga(int parc_paga) {
		this.parc_paga = parc_paga;
	}
	public int getAtraso_parc() {
		return atraso_parc;
	}
	public void setAtraso_parc(int atraso_parc) {
		this.atraso_parc = atraso_parc;
	}
	public boolean isCheck_pag() {
		return check_pag;
	}
	
	

	public void setCheck_pag(boolean check_pag) {
		this.check_pag = check_pag;
	}

	public Status (int id, String cliente, String cobrador, int numero_parcelas, String tipo_pagamento_dia, int multa ) {
		
		this.id = id;
		this.cliente = cliente;
		this.cobrador = cobrador;
		this.numero_parcelas = numero_parcelas;
		this.tipo_pagamento_dia = tipo_pagamento_dia;
		this.multa = multa;
		
	}
	
	public Status (int id, String cliente, String cobrador, int numero_parcelas, Typ typ, int multa, String s, String s2,
			int atraso, int parcela_atual, Date dataInicialPagamento, String func) {
		
		this.id = id;
		this.cliente = cliente;
		this.cobrador = cobrador;
		this.numero_parcelas = numero_parcelas;
		this.typ = new SimpleObjectProperty(typ);
		this.multa = multa;
		this.parcelasO = new SimpleStringProperty(s);
		this.multasO = new SimpleStringProperty(s2);
		this.atraso = atraso;
		this.parcela_atual = parcela_atual;
		this.dataInicialPagamento = dataInicialPagamento;
		this.func = func;
	//	this.data_pagamento = data_pagamento;
	}
	
	public Status(  int id, int id_operacao,  String cliente,  int numero_parcelas, String tipo_pagamento, Date dataInicialPagamento, 
			String tipo_pagamento_dia, int multa, boolean check_pag,
			int atraso, int parcela_atual, String nome_cliente_todos, int parc_paga, int atraso_parc, String func) {
		
		this.id = id;
		this.id_operacao = id_operacao;
		this.cliente = cliente;
		//this.cobrador = cobrador;
		this.numero_parcelas = numero_parcelas;
		this.tipo_pagamento = tipo_pagamento;
		this.dataInicialPagamento = dataInicialPagamento;
		this.tipo_pagamento_dia = tipo_pagamento_dia;
		this.multa = multa;
		this.check_pag = check_pag;
		this.atraso = atraso;
		this.parcela_atual = parcela_atual;
		this.nome_cliente_todos = nome_cliente_todos;
		this.parc_paga = parc_paga;
		this.atraso_parc = atraso_parc;
		this.func = func;
		//this.data_pagamento = data_pagamento;
		 
    }
	
	//last para status
	public Status ( int id, String cobrador, int numero_parcelas, double valor_Recebido, String tipo_pagamento,
			String tipo_pagamento_dia, Date dataInicialPagamento, int parcelas, int multa, int multas, int atraso, int parcela_atual
			){
		
		//this.id_operacao = id_operacao;
		this.id = id;
		this.cobrador = cobrador;
		this.numero_parcelas = numero_parcelas;
		this.valor_Recebido = valor_Recebido;
		this.tipo_pagamento = tipo_pagamento;
		this.tipo_pagamento_dia = tipo_pagamento_dia;
		this.dataInicialPagamento = dataInicialPagamento;
		this.parcelas = parcelas;
		this.multa = multa;
		this.multas = multas;
		this.atraso = atraso;
		this.parcela_atual = parcela_atual;
		
		
		
		
	}

	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public int getMulta() {
		return multa;
	}

	public void setMulta(int multa) {
		this.multa = multa;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCobrador() {
		return cobrador;
	}

	public void setCobrador(String cobrador) {
		this.cobrador = cobrador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getNumero_parcelas() {
		return numero_parcelas;
	}

	public void setNumero_parcelas(int numero_parcelas) {
		this.numero_parcelas = numero_parcelas;
	}

	public double getValor_Recebido() {
		return valor_Recebido;
	}

	public void setValor_Recebido(double valor_Recebido) {
		this.valor_Recebido = valor_Recebido;
	}

	public int getId_operacao() {
		return id_operacao;
	}

	public void setId_operacao(int id_operacao) {
		this.id_operacao = id_operacao;
	}

	public String getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public String getTipo_pagamento_dia() {
		return tipo_pagamento_dia;
	}

	public void setTipo_pagamento_dia(String tipo_pagamento_dia) {
		this.tipo_pagamento_dia = tipo_pagamento_dia;
	}



    public Typ getTypObj() {
        return (Typ) typ.get();
    }

    public ObjectProperty<Typ> typObjProperty() {
        return this.typ;
    }

    public void setTypObj(Typ typ) {
        this.typ.set(typ);
    }
	public Date getDataAlvoSequencial() {
		return dataAlvoSequencial;
	}
	public void setDataAlvoSequencial(Date dataAlvoSequencial) {
		this.dataAlvoSequencial = dataAlvoSequencial;
	}
	public Double getReceber() {
		return receber;
	}
	public void setReceber(Double receber) {
		this.receber = receber;
	}

    

	
	
}
