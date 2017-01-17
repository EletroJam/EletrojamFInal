package negocio;

import java.io.Serializable;


public class Celular implements Serializable{

	
	private static final long serialVersionUID = 8023519447601902873L;
	
	private int id_pessoa; 
	private String numero_celular;

	//NEGOCIO -> interface -> repositorio -> controle -> fachada
	
	public Celular (String numero_celular, int id_pessoa){
		this.numero_celular = numero_celular;
		this.id_pessoa = id_pessoa;
		

	}
	

	
	public int getId_pessoa() {
		return id_pessoa;
	}



	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}



	public String getNumero_celular() {
		return numero_celular;
	}

	public void setNumero_celular(String numero_celular){
		this.numero_celular = numero_celular;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
