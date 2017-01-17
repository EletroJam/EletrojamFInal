package negocio;

import java.io.Serializable;
import java.sql.Date;

public class Funcionario implements Serializable {

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCargo2() {
		return cargo2;
	}

	public void setCargo2(String cargo2) {
		this.cargo2 = cargo2;
	}

	public String getCargo3() {
		return cargo3;
	}

	public void setCargo3(String cargo3) {
		this.cargo3 = cargo3;
	}

	public String getCargo4() {
		return cargo4;
	}

	public void setCargo4(String cargo4) {
		this.cargo4 = cargo4;
	}

	private String login;
	private String senha;
	private String cargo;
	private String cargo2;
	private String cargo3;
	private int id_funcionario;
	private String cargo4;

	// editar
	private String nome;
	private String cpf;
	private String identidade;
	private String email;
	private Date data_nascimento;

	private String numeroCel;
	private String numeroTel;
	private String cep;
	private String logadouro;
	private int numero;
	private String cidade;
	private String estado;
	private String complemento;
	private String bairro;
	//

	public Funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public Funcionario(int id_funcionario, String cargo, String cargo2, String cargo3, String cargo4, String nome,
			String cpf, String identidade, String email, Date data_nascimento, String logadouro, String cidade,
			String estado, String cep, int numero, String complemento, String bairro, String numeroCel,
			String numeroTel) {

		this.id_funcionario = id_funcionario;
		this.cargo = cargo;
		this.cargo2 = cargo2;
		this.cargo3 = cargo3;
		this.cargo4 = cargo4;
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
	}

	public Funcionario(String login, String cargo, String senha, String cargo2, String cargo3, int id_funcionario,
			String cargo4) {

		this.login = login;
		this.cargo = cargo;
		this.senha = senha;
		this.cargo2 = cargo2;
		this.cargo3 = cargo3;
		this.id_funcionario = id_funcionario;
		this.cargo4 = cargo4;

	}

	public Funcionario(String login, String cargo, String senha, String cargo2, String cargo3, int id_funcionario,
			String cargo4, String nome) {

		this.login = login;
		this.cargo = cargo;
		this.senha = senha;
		this.cargo2 = cargo2;
		this.cargo3 = cargo3;
		this.id_funcionario = id_funcionario;
		this.cargo4 = cargo4;
		this.nome = nome;

	}

	@Override
	public String toString() {
		return nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4906986888643152008L;

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public void resetStringTypes() {
		// TODO Auto-generated method stub

	}

}
