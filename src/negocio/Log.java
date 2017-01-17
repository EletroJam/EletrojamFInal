package negocio;

import java.io.Serializable;
import java.sql.Date;

public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1612753283386324927L;
	
	private int id;
	private int id_status;
	private int id_operacao;
	private int id_funcionario;
	private Date data_dia;
	
	public Log (int id, int id_status, int id_operacao, int id_funcionario, Date data_dia) {
		this.id = id;
		this.id_status = id_status;
		this.id_operacao = id_operacao;
		this.id_funcionario = id_funcionario;
		this.data_dia = data_dia;
	}
	
	public Log ( int id_status, int id_operacao, int id_funcionario, Date data_dia) {
		
		this.id_status = id_status;
		this.id_operacao = id_operacao;
		this.id_funcionario = id_funcionario;
		this.data_dia = data_dia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public int getId_operacao() {
		return id_operacao;
	}

	public void setId_operacao(int id_operacao) {
		this.id_operacao = id_operacao;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public Date getData_dia() {
		return data_dia;
	}

	public void setData_dia(Date data_dia) {
		this.data_dia = data_dia;
	}
	
	
	
	
}
