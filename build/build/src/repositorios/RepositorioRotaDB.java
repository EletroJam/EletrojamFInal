package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexao.PersistenceMechanismException;
import conexao.PersistenceMechanismRDBMS;
import conexao.RepositoryException;
import interfaces.RepositorioRota;
import negocio.Rota;

public class RepositorioRotaDB implements RepositorioRota {
	
	
	private static RepositorioRotaDB instance;
	private PersistenceMechanismRDBMS pm;
	
	private RepositorioRotaDB() {
		
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioRotaDB getInstance (){
		if (instance == null) {
			instance = new RepositorioRotaDB();
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

	@Override
	public void insert(Rota rota) throws RepositoryException {
		
		try{
			
			//operacao.resetStringTypes();
			String sql = "INSERT INTO rota ( nome)" + "VALUES (?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			//getSexo()+""
			//statement.setInt(1, rota.getId());
			statement.setString(1, rota.getNome());
			
			
			
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
	
	
	private Rota createRota(ResultSet resultset) throws SQLException, RepositoryException {
		
		int id = resultset.getInt("id");
		String nome = resultset.getString("nome");
		
		
		
		return new Rota ( id , nome);
	}


	@Override
	
	public boolean has(String nome) throws RepositoryException {


		try {
            String sql = "SELECT * FROM rota WHERE nome = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setString(1, nome);
           
            
            ResultSet resultset = statement.executeQuery();
           
            if(resultset.next()){
            	resultset.close();
            	return true;
            }
            resultset.close();
            return false;
            
		} catch(PersistenceMechanismException e){
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
	

	/* a fazer
	public Rota get(int id) throws RepositoryException {
		Realiza realiza = null;
		try {
			String sql = "SELECT * FROM realiza where id = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id_operacao);
			
			
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				realiza = createRealiza(resultset);
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
		
		
		return realiza;
	}
	*/
	
	public ArrayList<Rota> getRotas() throws RepositoryException {
		ArrayList<Rota> rotas = new ArrayList<Rota>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT * FROM rota");
			 
			 while(resultset.next()){
				 rotas.add(createRota(resultset));
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
		 return rotas;
	}

	

}
