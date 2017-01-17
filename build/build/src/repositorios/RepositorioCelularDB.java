package repositorios;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.PersistenceMechanismException;
import conexao.PersistenceMechanismRDBMS;
import conexao.RepositoryException;
import interfaces.RepositorioCelular;
import negocio.Celular;


public class RepositorioCelularDB implements RepositorioCelular {
	
	//negocio -> interface -> REPOSITORIO -> controle -> fachada

	
	private static RepositorioCelularDB instance;
	private PersistenceMechanismRDBMS pm;
	
	private RepositorioCelularDB() {
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioCelularDB getInstance (){
		if (instance == null) {
			instance = new RepositorioCelularDB();
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
	

	
	public void insert(Celular celular) throws RepositoryException {
		try{
			
			String sql = "INSERT INTO celular (numero, id_pessoa)" + "VALUES (?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, celular.getNumero_celular());
			statement.setInt(2, celular.getId_pessoa());
			

		
			statement.executeUpdate();
			
			
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
		
	}

	
	public void update(String numero_celular, int id) throws RepositoryException {
		
		try{
			
			String sql = "UPDATE celular set numero = ? where id_pessoa = ? ";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, numero_celular);
			statement.setInt(2, id);
			

			
			
			statement.executeUpdate();
			
			
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
		
		
	}

	
	public void delete(String numero_celular) throws RepositoryException {
		
		try{
			
			String sql = "Delete from celular where numero = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, numero_celular);
			statement.executeUpdate();
			
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
		
	}

	
	public boolean has(String numero_celular) throws RepositoryException {
		
		try{
			 String sql = "SELECT * FROM celular WHERE numero = ?";
	         PreparedStatement statement = pm.getConnection().prepareStatement(sql);
	           
	         statement.setString(1, numero_celular);
	           
	 //        System.out.println(statement);
	         ResultSet resultset = statement.executeQuery();
	           
	         if(resultset.next()){
	        	 resultset.close();
	        	 return true;
	         }
	         resultset.close();
	         return false;
			
			
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
		
		
	}

	
	public Celular get(String numero_celular) throws RepositoryException {
		
		Celular celular = null;
		
		try{
			
			String sql = "SELECT * FROM celular where numero = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, numero_celular);
			
		//	System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				celular = createCelular(resultset);
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
		
		return null;
	}

	
	private Celular createCelular(ResultSet resultset)  throws SQLException, RepositoryException {
		
		int id_pessoa = resultset.getInt("id_pessoa");
		String numero_celular = resultset.getString("numero"); 

		
		return new Celular (numero_celular, id_pessoa);
	}

	public ArrayList<Celular> getCelulares() throws RepositoryException {
		ArrayList<Celular> celulares = new ArrayList<Celular>();
		
		try{
			
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM celular");
			 
			while(resultset.next()){
				celulares.add(createCelular(resultset));
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
		return celulares;
	}

}
