package negocio;

import java.io.Serializable;
import java.sql.Date;

import util.AuxiliarMethods;

public class Pessoa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8613463392107904359L;
	private String nome;
	private String cpf; 
	private String identidade;
	private int id_endereco;
	private byte[] foto;
	private String sexo;
	private String email;
	private Date data_nascimento;
	private int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void resetStringTypes(){
		this.email = AuxiliarMethods.resetStringType(email);
		this.nome = AuxiliarMethods.resetStringType(nome);
		
	}
	
	public Pessoa ( int id, String nome, String cpf, String identidade, int id_endereco, String email, Date data_nascimento2, byte[] foto){
		super();
		
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.identidade = identidade;
		this.id_endereco = id_endereco;
		this.foto = foto;
		
		this.email = email;
		this.data_nascimento = data_nascimento2;
		
	}
	
	public Pessoa ( String nome, String cpf, String identidade, int id_endereco,  String email, Date data_nascimento2, byte[] foto){
		super();
		
		this.nome = nome;
		this.cpf = cpf;
		this.identidade = identidade;
		this.id_endereco = id_endereco;
		this.foto = foto;
		this.email = email;
		this.data_nascimento = data_nascimento2;
		
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public int getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	

}
