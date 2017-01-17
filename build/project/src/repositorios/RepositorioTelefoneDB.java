package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.PersistenceMechanismException;
import conexao.PersistenceMechanismRDBMS;
import conexao.RepositoryException;
import interfaces.RepositorioTelefone;
import negocio.Telefone;

public class RepositorioTelefoneDB implements RepositorioTelefone {

	private static RepositorioTelefoneDB instance;
	private PersistenceMechanismRDBMS pm;

	private RepositorioTelefoneDB() {
		try {

			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();

			// c.conect();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	public synchronized static RepositorioTelefoneDB getInstance() {
		if (instance == null) {
			instance = new RepositorioTelefoneDB();
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

	public void insert(Telefone telefone) throws RepositoryException {
		try {
			
			//telefone.resetStringTypes();

			String sql = "INSERT INTO telefone (numero, id_pessoa)" + "VALUES (?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			
			statement.setString(1, telefone.getNumero_telefone());
			statement.setInt(2, telefone.getId_pessoa());
			

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

	
	public void update(String numero_telefone, int id) throws RepositoryException {

		try {

			String sql = "UPDATE telefone set numero = ? where id_pessoa = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, numero_telefone);
			statement.setInt(2, id);
			

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

	public void delete(String numero_telefone) throws RepositoryException {

		try {

			String sql = "Delete from telefone where numero = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, numero_telefone);
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

	public boolean has(String numero_telefone) throws RepositoryException {

		try {
			String sql = "SELECT * FROM telefone WHERE numero = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, numero_telefone);

		//	System.out.println(statement);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				resultset.close();
				return true;
			}
			resultset.close();
		//	System.out.println("RETUEN FALSO");
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

	public Telefone get(String numero_telefone) throws RepositoryException {

		Telefone telefone = null;

		try {

			String sql = "SELECT * FROM telefone where numero = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, numero_telefone);

	//		System.out.println(statement);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				telefone = createTelefone(resultset);
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

		return null;
	}

	private Telefone createTelefone(ResultSet resultset) throws SQLException, RepositoryException {

		String numero_telefone = resultset.getString("numero");
		int id_pessoa = resultset.getInt("id_pessoa");

		return new Telefone(numero_telefone, id_pessoa);
	}

	public ArrayList<Telefone> getTelefones() throws RepositoryException {
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM telefone");

			while (resultset.next()) {
				telefones.add(createTelefone(resultset));
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
		return telefones;
	}

}
