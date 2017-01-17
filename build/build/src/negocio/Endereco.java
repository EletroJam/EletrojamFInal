package negocio;

import java.io.Serializable;

public class Endereco implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3490566230928599140L;
	private int id;
	private String cep; 
	private String logadouro;
	private int numero;
	//private String pais;
	//private String uf;
	private String cidade;
	private String estado;
	private String complemento;
	private String bairro;
	
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco (String logadouro, String cidade,  String estado, String cep, int numero,String complemento, String bairro ){
		
		this.cep = cep;
		this.logadouro = logadouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.bairro = bairro;
		
	}
	
	public Endereco (int id, String logadouro, String cidade,  String estado, String cep, int numero,String complemento, String bairro ){
		
		this.id = id;
		this.cep = cep;
		this.logadouro = logadouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.bairro = bairro;
		
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogadouro() {
		return logadouro;
	}
	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
