package negocio;

import java.io.Serializable;
import java.sql.Date;

public class Realiza implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id_operacao;
	private int id_cliente;
	private int id_funcionario;
	private Date dataOperacao;

	public Realiza (int id_operacao, int id_cliente, int id_funcionario,Date dataOperacao) {
		this.id_operacao = id_operacao;
		this.id_cliente = id_cliente;
		this.id_funcionario = id_funcionario;
		this.dataOperacao = dataOperacao;
	}
	
	

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public int getId_operacao() {
		return id_operacao;
	}

	public void setId_operacao(int id_operacao) {
		this.id_operacao = id_operacao;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
