package negocio;

import java.io.Serializable;
import java.sql.Date;

public class OperacaoCliente implements Serializable{

	private static final long serialVersionUID = -7840817695747912447L;
	private int id;
	private Date data_dia;
	private double valor_emprestimo;
	private int quantidade_atraso;
	private int id_Cliente;
	private int id_operacao;
	private String estado;
	
	public OperacaoCliente (Date data_dia, double valor_emprestimo,
			int quantidade_atraso, int id_Cliente, int id_operacao, String estado){
		
		this.data_dia = data_dia;
		this.valor_emprestimo = valor_emprestimo;
		this.quantidade_atraso = quantidade_atraso;
		this.id_Cliente = id_Cliente;
		this.id_operacao = id_operacao;
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public OperacaoCliente (int id, Date data_dia, double valor_emprestimo,
			int quantidade_atraso, int id_Cliente, int id_operacao, String estado){
		
		this.id = id;
		this.data_dia = data_dia;
		this.valor_emprestimo = valor_emprestimo;
		this.quantidade_atraso = quantidade_atraso;
		this.id_Cliente = id_Cliente;
		this.id_operacao = id_operacao;
		this.estado = estado;
		
	}

	public int getId_operacao() {
		return id_operacao;
	}

	public void setId_operacao(int id_operacao) {
		this.id_operacao = id_operacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_dia() {
		return data_dia;
	}

	public void setData_dia(Date data_dia) {
		this.data_dia = data_dia;
	}

	public double getValor_emprestimo() {
		return valor_emprestimo;
	}

	public void setValor_emprestimo(double valor_emprestimo) {
		this.valor_emprestimo = valor_emprestimo;
	}

	public int getQuantidade_atraso() {
		return quantidade_atraso;
	}

	public void setQuantidade_atraso(int quantidade_atraso) {
		this.quantidade_atraso = quantidade_atraso;
	}

	public int getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(int id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
