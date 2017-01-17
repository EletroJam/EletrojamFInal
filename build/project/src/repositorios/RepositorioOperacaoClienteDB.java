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
import interfaces.RepositorioOperacaoCliente;
import negocio.OperacaoCliente;

public class RepositorioOperacaoClienteDB implements RepositorioOperacaoCliente{

	
	private static RepositorioOperacaoClienteDB instance;
	private PersistenceMechanismRDBMS pm;
	
	private RepositorioOperacaoClienteDB() {
		
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioOperacaoClienteDB getInstance (){
		if (instance == null) {
			instance = new RepositorioOperacaoClienteDB();
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
	
	
	
	public void insert(OperacaoCliente operacaoCliente) throws RepositoryException {
		
	try{
		
			//operacao.resetStringTypes();
			String sql = "INSERT INTO operacoes_cliente (data, valor_emprestimo, quantidade_atraso, id_Cliente, id_operacao,estado)" + "VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			//getSexo()+""
			
		
			
			statement.setDate(1, operacaoCliente.getData_dia());
			statement.setDouble(2, operacaoCliente.getValor_emprestimo());
			statement.setInt(3, operacaoCliente.getQuantidade_atraso());
			statement.setInt(4, operacaoCliente.getId_Cliente());
			statement.setInt(5, operacaoCliente.getId_operacao());
			statement.setString(6, operacaoCliente.getEstado());
			
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
	
	public void update(int id, int atraso) throws RepositoryException{
		try{
			
		String sql = "UPDATE operacoes_cliente set quantidade_atraso = ? where id_operacao = ?";
		PreparedStatement statement = pm.getConnection().prepareStatement(sql);
		
		statement.setInt(1, atraso);
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
	
	
	public void update_estado(int id, String estado) throws RepositoryException{
		try{
			
		String sql = "UPDATE operacoes_cliente set estado = ? where id_operacao = ?";
		PreparedStatement statement = pm.getConnection().prepareStatement(sql);
		
		statement.setString(1, estado);
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

	
	public void delete(int id) throws RepositoryException {
		
		try{
			//Pessoa pessoa = get(cpf);
			String sql = "Delete from operacoes_cliente where id_operacao = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			
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

	
	public boolean has(int id) throws RepositoryException {
		try {
            String sql = "SELECT * FROM operacoes_cliente WHERE id = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setInt(1, id);
           
            
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
	
	
	private OperacaoCliente createOperacaoCliente(ResultSet resultset) throws SQLException, RepositoryException {
	
		int id = resultset.getInt("id");
		Date data_dia = resultset.getDate("data");
		double valor_emprestimo = resultset.getDouble("valor_emprestimo");
		int quantidade_atraso = resultset.getInt("quantidade_atraso");
		int id_Cliente = resultset.getInt("id_Cliente");
		int id_operacao = resultset.getInt("id_operacao");
		String estado = resultset.getString("estado");
		
		
		return new OperacaoCliente ( id, data_dia, valor_emprestimo, quantidade_atraso,id_Cliente,id_operacao,estado);
	}

	//por hora o get é apenas pela operação
	public OperacaoCliente get(int idOperacao) throws RepositoryException {
		
		OperacaoCliente operacaoCliente = null;
		try {
			String sql = "SELECT * FROM operacoes_cliente where id_operacao = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, idOperacao);
			
			
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				operacaoCliente = createOperacaoCliente(resultset);
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
		
		
		return operacaoCliente;
	}

	
	public ArrayList<OperacaoCliente> getOperacaoClientes() throws RepositoryException {
		
		ArrayList<OperacaoCliente> operacoesClientes = new ArrayList<OperacaoCliente>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT * FROM operacoes_cliente");
			 
			 while(resultset.next()){
				 operacoesClientes.add(createOperacaoCliente(resultset));
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
		 return operacoesClientes;
	}

	
}
