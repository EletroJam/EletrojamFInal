package negocio;

import java.io.Serializable;

public class Telefone implements Serializable {

	private static final long serialVersionUID = 2758038366044807962L;

	private int id_pessoa;
	private String numero_telefone;

	// NEGOCIO -> interface -> repositorio -> controle -> fachada

	public Telefone(String numero_telefone, int id_pessoa) {
		this.numero_telefone = numero_telefone;
		this.id_pessoa = id_pessoa;

	}

	

	public String getNumero_telefone() {
		return numero_telefone;
	}

	public void setNumero_telefone(String numero_telefone) {
		this.numero_telefone = numero_telefone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getId_pessoa() {
		return id_pessoa;
	}



	public void setId_pessoa(int id_pessoa) {
		this.id_pessoa = id_pessoa;
	}



}
