package negocio;

import java.io.Serializable;

public class Rota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5872016180479674461L;
	
	private String nome;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Rota (String nome){
		this.nome = nome;
	}
	
	public Rota (int id, String nome){
		this.id = id;
		this.nome = nome;
	}

	
	
	
	
	
}
