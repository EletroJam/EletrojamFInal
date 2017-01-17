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
import interfaces.RepositorioRealiza;
import negocio.Realiza;

public class RepositorioRealizaDB implements RepositorioRealiza{
	
	
	private static RepositorioRealizaDB instance;
	private PersistenceMechanismRDBMS pm;
	
	private RepositorioRealizaDB() {
		
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioRealizaDB getInstance (){
		if (instance == null) {
			instance = new RepositorioRealizaDB();
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

	
	public void insert(Realiza realiza) throws RepositoryException {
		
			try{
			
			//operacao.resetStringTypes();
			String sql = "INSERT INTO realiza (id_operacao, id_cliente, id_funcionario, data_operacao)" + "VALUES (?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			//getSexo()+""
			
			statement.setInt(1, realiza.getId_operacao());
			statement.setInt(2, realiza.getId_cliente());
			statement.setInt(3, realiza.getId_funcionario());
			statement.setDate(4, realiza.getDataOperacao());
			
			
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

	public void delete(int id_operacao) throws RepositoryException {
		
		try{
			//Pessoa pessoa = get(cpf);
			String sql = "Delete from realiza where id_operacao = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id_operacao);
			
			statement.executeUpdate();
			
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

	
	public boolean has(int id_operacao) throws RepositoryException {
		
		try {
            String sql = "SELECT * FROM realiza WHERE id_operacao = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setInt(1, id_operacao);
           
            
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
	
	
	private Realiza createRealiza(ResultSet resultset) throws SQLException, RepositoryException {
		
		int id_operacao = resultset.getInt("id_operacao");
		int id_cliente = resultset.getInt("id_cliente");
		int id_funcionario = resultset.getInt("id_funcionario");
		Date dataOperacao = resultset.getDate("dataOperacao");
		
		
		return new Realiza ( id_operacao, id_cliente, id_funcionario, dataOperacao);
	}

	
	public Realiza get(int id_operacao) throws RepositoryException {

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

	
	public ArrayList<Realiza> getRealizacao() throws RepositoryException {
		
		ArrayList<Realiza> realizacao = new ArrayList<Realiza>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT * FROM realiza");
			 
			 while(resultset.next()){
				 realizacao.add(createRealiza(resultset));
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
		 return realizacao;
	}
	
	

}
