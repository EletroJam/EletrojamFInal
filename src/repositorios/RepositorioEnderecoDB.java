package repositorios;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.PersistenceMechanismException;
import conexao.PersistenceMechanismRDBMS;
import conexao.RepositoryException;
import interfaces.RepositorioEndereco;
import negocio.Endereco;


public class RepositorioEnderecoDB implements RepositorioEndereco {
	
	
	private static RepositorioEnderecoDB instance;
	private PersistenceMechanismRDBMS pm;
	
	private RepositorioEnderecoDB() {
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioEnderecoDB getInstance (){
		if (instance == null) {
			instance = new RepositorioEnderecoDB();
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
	

	
	public void insert(Endereco endereco) throws RepositoryException {
		try{
			
			String sql = "INSERT INTO endereco (cep, logadouro, numero, cidade, estado, complemento, bairro)" + "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, endereco.getCep());
			statement.setString(2, endereco.getLogadouro());
			statement.setInt(3, endereco.getNumero());
			statement.setString(4, endereco.getCidade());
			statement.setString(5, endereco.getEstado());
			statement.setString(6, endereco.getComplemento());
			statement.setString(7, endereco.getBairro());
		
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

	public void update(Endereco endereco, int id_endereco) throws RepositoryException {
		
		try{
			
			String sql = "UPDATE endereco set cep = ?, logadouro = ?, numero = ?, cidade = ?, estado = ? where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, endereco.getCep());
			statement.setString(2, endereco.getLogadouro());
			statement.setInt(3, endereco.getNumero());
			statement.setString(4, endereco.getCidade());
			statement.setString(5, endereco.getEstado());
			statement.setInt(6, id_endereco);
			
			
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

	
	public void delete(int id) throws RepositoryException {
		
		try{
			//Endereco endereco = get(id);
			String sql = "Delete from endereco where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
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

	
	public boolean has(int id) throws RepositoryException {
		
		try{
			 String sql = "SELECT * FROM endereco WHERE id = ?";
	         PreparedStatement statement = pm.getConnection().prepareStatement(sql);
	           
	         statement.setInt(1, id);
	           
	      //   System.out.println(statement);
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

	
	public Endereco get(int id) throws RepositoryException {
		Endereco endereco = null;
		
		try{
			
			String sql = "SELECT * FROM endereco where id = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
		
		//	System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				endereco = createEndereco(resultset);
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
		
		return endereco;
	}

	
	private Endereco createEndereco(ResultSet resultset)  throws SQLException, RepositoryException {
		
		int id = resultset.getInt("id");
		String cep = resultset.getString("cep"); 
		String logadouro = resultset.getString("logadouro");
		int numero = resultset.getInt("numero");
		String cidade = resultset.getString("cidade");
		String estado = resultset.getString("estado");
		String complemento = resultset.getString("complemento");
		String bairro = resultset.getString("bairro");
		
		return new Endereco ( id, logadouro,  cidade,   estado,  cep,  numero, complemento, bairro );
	}

	public ArrayList<Endereco> getEnderecos() throws RepositoryException {
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		
		try{
			
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM endereco");
			 
			while(resultset.next()){
				enderecos.add(createEndereco(resultset));
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
		return enderecos;
	}
	
	public int getId() throws RepositoryException {
		
		int id = 0;
		
		try{
			
			String sql = "select id from endereco where id = (select max(id) from endereco )";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			
		//	System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				
				id = resultset.getInt("id");
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
		
		return id;
	}

}
