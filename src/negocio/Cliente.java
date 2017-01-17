package negocio;

import java.io.Serializable;
import java.sql.Date;

public class Cliente implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6191137639176089055L;
	
	private String apelido;
	private int id_cliente;
	
	//operacao
	private Date data_dia;
	private double valor_emprestimo;
	private int quantidade_atraso;
	private int id_operacao;
	
	private String nome_funcionario;
	private int id_vendedor;
	
	
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


	public int getId_operacao() {
		return id_operacao;
	}


	public void setId_operacao(int id_operacao) {
		this.id_operacao = id_operacao;
	}


	private String estadoo;
	
	public Cliente (Date data_dia, double valor_emprestimo, int quantidade_atraso, int id_operacao, String estadoo, String nome_funcionario){
		this.data_dia = data_dia;
		this.valor_emprestimo = valor_emprestimo;
		this.quantidade_atraso = quantidade_atraso;
		this.id_operacao = id_operacao;
		this.estadoo = estadoo;
		this.nome_funcionario = nome_funcionario;
	}
	
	
	public String getEstadoo() {
		return estadoo;
	}

	public void setEstadoo(String estadoo) {
		this.estadoo = estadoo;
	}


	//especifico para editar
	private String nome;
	private String cpf; 
	private String identidade;
	private String sexo;
	private String email;
	private Date data_nascimento;
	
	private String numeroCel;
	private String numeroTel;
	
	public String getNumeroCel() {
		return numeroCel;
	}

	public void setNumeroCel(String numeroCel) {
		this.numeroCel = numeroCel;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}



	private String cep; 
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	private String logadouro;
	private int numero;
	private String cidade;
	private String estado;
	private String complemento;
	private String bairro;
	//private String pais;
	
	public Cliente (int id_cliente){
		this.id_cliente = id_cliente;
	}
	
	
	public Cliente(int id_cliente, String nome, String apelido, int id_vendedor) {
		
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.apelido = apelido;
		this.id_vendedor = id_vendedor;
	}
	
	public Cliente(String nome, String apelido, String numCel) {
		
		this.nome = nome;
		this.apelido = apelido;
		this.numeroCel = numCel;
	}
	
	
	public Cliente(String apelido, int id_cliente, String nome_funcionario, int id_vendedor) {
		
		
		this.apelido = apelido;
		this.id_cliente = id_cliente;
		this.nome_funcionario = nome_funcionario;
		this.id_vendedor = id_vendedor;
	}
	
	public Cliente(String apelido, int id_cliente, String nome, String cpf, String identidade, String email, Date data_nascimento, String logadouro, String cidade,  String estado, String cep, int numero,String complemento, String bairro, String numeroCel, String numeroTel, String nome_funcionario){
		this.apelido = apelido;
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.cpf = cpf;
		this.identidade = identidade;
		this.email = email;
		this.data_nascimento = data_nascimento;
		this.cep = cep;
		this.logadouro = logadouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.bairro = bairro;
		this.numeroCel = numeroCel;
		this.numeroTel = numeroTel;
		this.nome_funcionario = nome_funcionario;
	}
	



	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}




	public void resetStringTypes() {
		// TODO Auto-generated method stub

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




	public Date getData_nascimento() {
		return data_nascimento;
	}




	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
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




	public String getBairro() {
		return bairro;
	}




	public void setBairro(String bairro) {
		this.bairro = bairro;
	}




	public int getId_cliente() {
		return id_cliente;
	}




	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNome_funcionario() {
		return nome_funcionario;
	}


	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}


	public int getId_vendedor() {
		return id_vendedor;
	}


	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}


	/**
	 * 
	 */
	
	

}
