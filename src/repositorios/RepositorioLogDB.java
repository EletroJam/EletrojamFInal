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
import interfaces.RepositorioLog;
import negocio.Log;


public class RepositorioLogDB  implements RepositorioLog{
	
	private static RepositorioLogDB instance;
	private PersistenceMechanismRDBMS pm;
	
	
	private RepositorioLogDB(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioLogDB getInstance (){
		if (instance == null) {
			instance = new RepositorioLogDB();
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

	public void insert(Log log) throws RepositoryException {
		// TODO Auto-generated method stub
		try{
		String sql = "INSERT INTO log (id_status, id_operacao, id_funcionario, data_dia)" + "VALUES (?,?,?,?)";
		PreparedStatement statement = pm.getConnection().prepareStatement(sql);
		
		//getSexo()+""
		
		statement.setInt(1, log.getId_status());
		statement.setInt(2, log.getId_operacao());
		statement.setInt(3, log.getId_funcionario());
		statement.setDate(4, log.getData_dia());
		
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
	
	private Log createLog(ResultSet resultset) throws SQLException, RepositoryException {
		
		int id_status = resultset.getInt("id_status");
		int id_operacao = resultset.getInt("id_operacao");
		int id_funcionario = resultset.getInt("id_funcionario");
		Date data_dia = resultset.getDate("data_dia");
		
		
		return new Log ( id_status, id_operacao, id_funcionario, data_dia);
	}
	
	public Log get(int id_funcionario) throws RepositoryException {
		Log log = null;
		try {
			String sql = "SELECT * FROM realiza where id_funcionario = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id_funcionario);
			
			
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				log = createLog(resultset);
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
		
		
		return log;
	}
	
	public ArrayList<Log> getLogs() throws RepositoryException {
		
		ArrayList<Log> logs = new ArrayList<Log>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT * FROM log");
			 
			 while(resultset.next()){
				 logs.add(createLog(resultset));
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
		 return logs;
	}

}
