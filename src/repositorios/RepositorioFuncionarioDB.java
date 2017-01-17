package repositorios;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.PersistenceMechanismException;
import conexao.PersistenceMechanismRDBMS;
import conexao.RepositoryException;
import interfaces.RepositorioFuncionario;
import negocio.Funcionario;

public class RepositorioFuncionarioDB implements RepositorioFuncionario {

	private static RepositorioFuncionarioDB instance;
	private PersistenceMechanismRDBMS pm;

	private RepositorioFuncionarioDB() {
		try {

			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();

			// c.conect();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	public synchronized static RepositorioFuncionarioDB getInstance() {
		if (instance == null) {
			instance = new RepositorioFuncionarioDB();
		}
		return instance;
	}

	public void close() throws RepositoryException {
		try {
			this.pm.disconnect();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		}
	}

	public void insert(Funcionario funcionario) throws RepositoryException {

		try {

			funcionario.resetStringTypes();
			String sql = "INSERT INTO funcionario (login, cargo, senha, cargo2, cargo3, id_funcionario, cargo4)"
					+ "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// getSexo()+""
			statement.setString(1, funcionario.getLogin());
			statement.setString(2, funcionario.getCargo());
			statement.setString(3, funcionario.getSenha());
			statement.setString(4, funcionario.getCargo2());
			statement.setString(5, funcionario.getCargo3());
			statement.setInt(6, funcionario.getId_funcionario());
			statement.setString(7, funcionario.getCargo4());

			statement.executeUpdate();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}

	}

	public ArrayList<Funcionario> getFuncionarioPessoa() throws RepositoryException {

		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery(
					"SELECT d.numero as cel, z.numero as tel ,p.nome, p.email,  p.cpf, p.identidade, p.data_nascimento, c.cargo, c.cargo2, c.cargo3, c.cargo4, c.id_funcionario, e.logadouro, e.cidade, e.estado, e.cep, e.numero, e.complemento, e.bairro FROM funcionario c, pessoa p, endereco e, telefone z, celular d where c.id_funcionario = p.id and p.id_endereco = e.id and z.id_pessoa = p.id and d.id_pessoa = p.id order by p.nome");

			while (resultset.next()) {
				funcionarios.add(createFuncionarioPessoa(resultset));
			}
			resultset.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return funcionarios;
	}

	private Funcionario createFuncionarioPessoa(ResultSet resultset) throws SQLException {
		
		
		int id_funcionario = resultset.getInt("id_funcionario");
		String cargo = resultset.getString("cargo");
		String cargo2 = resultset.getString("cargo2");
		String cargo3 = resultset.getString("cargo3");
		String cargo4 = resultset.getString("cargo4");
		String nome = resultset.getString("nome");
		String cpf = resultset.getString("cpf");
		String identidade = resultset.getString("identidade");
		String email = resultset.getString("email");
		Date data_nascimento = resultset.getDate("data_nascimento");
		String cep = resultset.getString("cep"); 
		String logadouro = resultset.getString("logadouro");
		int numero = resultset.getInt("numero");
		String cidade = resultset.getString("cidade");
		String estado = resultset.getString("estado");
		String complemento = resultset.getString("complemento");
		String bairro = resultset.getString("bairro");
		String numeroTel = resultset.getString("tel");
		String numeroCel = resultset.getString("cel");
		
		return new Funcionario (id_funcionario, cargo, cargo2, cargo3, cargo4, nome, cpf, identidade, email, data_nascimento, logadouro,  cidade,   estado,  cep,  numero, complemento, bairro, numeroCel, numeroTel );
	}

	public void updateEnderecoFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException {

		try {

			String sql = "UPDATE endereco set logadouro = ?, cidade = ?, estado = ?, numero = ?, complemento = ?, bairro = ?, cep = ?  where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, funcionario.getLogadouro());
			statement.setString(2, funcionario.getCidade());
			statement.setString(3, funcionario.getEstado());
			statement.setInt(4, funcionario.getNumero());
			statement.setString(5, funcionario.getComplemento());
			statement.setString(6, funcionario.getBairro());
			statement.setString(7, funcionario.getCep());
			statement.setInt(8, id_funcionario);

			statement.executeUpdate();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}

	public void updatePessoaFunc(int id_funcionario, Funcionario funcionario) throws RepositoryException {

		try {

			String sql = "UPDATE pessoa set nome = ?, cpf = ?, identidade = ?, email = ?, data_nascimento = ? where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, funcionario.getNome());
			statement.setString(2, funcionario.getCpf());
			statement.setString(3, funcionario.getIdentidade());
			statement.setString(4, funcionario.getEmail());
			statement.setDate(5, funcionario.getData_nascimento());
			statement.setInt(6, id_funcionario);

			statement.executeUpdate();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}

	public void update(Funcionario funcionario, int id_funcionario) throws RepositoryException {

		try {

			String sql = "UPDATE funcionario set cargo = ?, cargo2 = ?, cargo3 = ?, cargo4 = ? where id_funcionario = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, funcionario.getCargo());
			statement.setString(2, funcionario.getCargo2());
			statement.setString(3, funcionario.getCargo3());
			statement.setString(4, funcionario.getCargo4());
			statement.setInt(5, id_funcionario);

			statement.executeUpdate();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}

	}

	public void delete(String cpf) throws RepositoryException {

		try {
			// Pessoa pessoa = get(cpf);
			String sql = "Delete from funcionario where cpf = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, cpf);
			statement.executeUpdate();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}

	}

	public boolean has(String cpf) throws RepositoryException {
		try {
			String sql = "SELECT * FROM funcionario WHERE cpf = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, cpf);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				resultset.close();
				return true;
			}
			resultset.close();
			return false;

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}

	}

	public Funcionario get(String cpf) throws RepositoryException {
		Funcionario funcionario = null;
		try {
			String sql = "SELECT * FROM funcionario where cpf = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, cpf);

			// System.out.println(statement);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				funcionario = createFuncionario(resultset);
			}
			resultset.close();

		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}

		return funcionario;
	}
	
	

	private Funcionario createFuncionario(ResultSet resultset) throws SQLException, RepositoryException {

		String login = resultset.getString("login");
		String cargo = resultset.getString("cargo");
		String senha = resultset.getString("senha");
		String cargo2 = resultset.getString("cargo2");
		String cargo3 = resultset.getString("cargo3");
		int id_funcionario = resultset.getInt("id_funcionario");
		String cargo4 = resultset.getString("cargo4");

		return new Funcionario(login, cargo, senha, cargo2, cargo3, id_funcionario, cargo4);
	}
	
	private Funcionario createFuncionario2(ResultSet resultset) throws SQLException, RepositoryException {

		String login = resultset.getString("login");
		String cargo = resultset.getString("cargo");
		String senha = resultset.getString("senha");
		String cargo2 = resultset.getString("cargo2");
		String cargo3 = resultset.getString("cargo3");
		int id_funcionario = resultset.getInt("id_funcionario");
		String cargo4 = resultset.getString("cargo4");
		String nome = resultset.getString("nome");

		return new Funcionario(login, cargo, senha, cargo2, cargo3, id_funcionario, cargo4, nome);
	}
	

	public ArrayList<Funcionario> getFuncionarios() throws RepositoryException {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM funcionario");

			while (resultset.next()) {
				funcionarios.add(createFuncionario(resultset));
			}
			resultset.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return funcionarios;
	}

	@Override
	public Funcionario getLogin(String login, String senha) throws RepositoryException {
		Funcionario funcionario = null;

		try {
			String sql = "SELECT * FROM funcionario WHERE login = ? and senha = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, login);
			statement.setString(2, senha);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				funcionario = createFuncionario(resultset);
			}
			resultset.close();

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}

		return funcionario;
	}

	@Override
	public boolean hasLogin(String login) throws RepositoryException {
		try {
			String sql = "SELECT * FROM funcionario WHERE login = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, login);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				resultset.close();
				return true;
			}
			resultset.close();
			return false;

		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}

	public ArrayList<Funcionario> getFuncionariosOperadores() throws RepositoryException {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM funcionario where cargo = 'Operador'");

			while (resultset.next()) {
				funcionarios.add(createFuncionario(resultset));
			}
			resultset.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return funcionarios;
	}

	public ArrayList<Funcionario> getFuncionariosVendedores() throws RepositoryException {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT f.login, f.cargo, f.senha, f.cargo2, f.cargo3, f.cargo4, f.id_funcionario, p.nome  FROM funcionario f, pessoa p where p.id = f.id_funcionario and cargo3 = 'Vendedor'");

			while (resultset.next()) {
				funcionarios.add(createFuncionario2(resultset));
			}
			resultset.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return funcionarios;
	}

	public ArrayList<Funcionario> getFuncionariosCobradores() throws RepositoryException {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT f.login, f.cargo, f.senha, f.cargo2, f.cargo3, f.cargo4, f.id_funcionario, p.nome FROM funcionario f, pessoa p  where f.cargo2 = 'Cobrador' and f.id_funcionario = p.id order by p.nome");

			while (resultset.next()) {
				funcionarios.add(createFuncionario2(resultset));
			}
			resultset.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return funcionarios;
	}

	public ArrayList<Funcionario> getFuncionariosEntregadores() throws RepositoryException {
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT f.login, f.cargo, f.senha, f.cargo2, f.cargo3, f.cargo4, f.id_funcionario, p.nome FROM funcionario f, pessoa p  where cargo4 = 'Entregador' and f.id_funcionario = p.id order by p.nome");

			while (resultset.next()) {
				funcionarios.add(createFuncionario2(resultset));
			}
			resultset.close();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return funcionarios;
	}
	
	public String getFuncionarioNome (int id) throws RepositoryException{
		String nome = "";
		try{
			
			String sql = "select p.nome from funcionario f, pessoa p where p.id = f.id_funcionario and id_funcionario = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setInt(1, id);
		//	System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				
				nome = resultset.getString("nome");
			}
			resultset.close();
			
			
		}catch(PersistenceMechanismException e){
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
		return nome;
		
	}
	public int getFuncionarioId (String nomeF) throws RepositoryException{
		int nome = 0;
		try{
			
			String sql = "select p.id from pessoa p where p.nome = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, nomeF);
		//	System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				
				nome = resultset.getInt("id");
			}
			resultset.close();
			
			
		}catch(PersistenceMechanismException e){
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
		return nome;
		
	}
}

