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
import interfaces.RepositorioPessoa;
import negocio.Pessoa;
import util.AuxiliarMethods;

public class RepositorioPessoaDB implements RepositorioPessoa{
	private static RepositorioPessoaDB instance;
	private PersistenceMechanismRDBMS pm;
	
	//private Conect c;
	
	
	private RepositorioPessoaDB() {
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioPessoaDB getInstance (){
		if (instance == null) {
			instance = new RepositorioPessoaDB();
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

	
	public void insert(Pessoa pessoa) throws RepositoryException {
		
		
		try{
			
			//pessoa.resetStringTypes();
			String sql = "INSERT INTO pessoa (nome,cpf,identidade,id_endereco,foto,sexo,email,data_nascimento)" + "VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			//getSexo()+""
		
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getCpf());
			statement.setString(3, pessoa.getIdentidade());
			//o que fazer com o endere�o
			statement.setInt(4, pessoa.getId_endereco());
			statement.setBytes(5, pessoa.getFoto());
			statement.setString(6, pessoa.getSexo());
			statement.setString(7, pessoa.getEmail());
			statement.setDate(8, pessoa.getData_nascimento());
			
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

	
	public void update(Pessoa pessoa, String oldEmail) throws RepositoryException {
		
	
		try {
			pessoa.resetStringTypes();
			oldEmail  =  AuxiliarMethods.resetStringType(oldEmail);
			String sql = "UPDATE pessoa set nome = ?, cpf = ?, identidade = ?, sexo = ?, email = ?, data_nascimento = ? where email = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getCpf());
			statement.setString(3, pessoa.getIdentidade());
			statement.setString(4, pessoa.getSexo());
			statement.setString(5, pessoa.getEmail());
			statement.setDate(6, pessoa.getData_nascimento());
			statement.setString(7, oldEmail);
			
			
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

	
	public void delete(String cpf) throws RepositoryException {
		
		try{
			//Pessoa pessoa = get(cpf);
			String sql = "Delete from pessoa where cpf = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, cpf);
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

	
	public boolean has(String cpf) throws RepositoryException {
		try {
            String sql = "SELECT * FROM pessoa WHERE cpf = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setString(1, cpf);
           
            
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
	
	public boolean hasIdentidade(String identidade) throws RepositoryException {
		try {
            String sql = "SELECT * FROM pessoa WHERE identidade = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setString(1, identidade);
           
            
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
	
	public boolean hasEmail(String email) throws RepositoryException {
		try {
            String sql = "SELECT * FROM pessoa WHERE email = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setString(1, email);
           
            
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

	
	public Pessoa get(String cpf) throws RepositoryException {
		Pessoa pessoa = null;
		try {
			String sql = "SELECT * FROM pessoa where cpf = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, cpf);
			
			//System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				pessoa = createPessoa(resultset);
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
		
		
		return pessoa;
	}
	

	public Pessoa getIdentidade(String identidade) throws RepositoryException {
		Pessoa pessoa = null;
		try {
			String sql = "SELECT * FROM pessoa where identidade = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setString(1, identidade);
			
			//System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				pessoa = createPessoa(resultset);
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
		
		
		return pessoa;
	}

	private Pessoa createPessoa(ResultSet resultset) throws SQLException, RepositoryException {
		
		int id = resultset.getInt("id");
		String nome = resultset.getString("nome");
		String cpf = resultset.getString("cpf");
		String identidade = resultset.getString("identidade");
		int id_endereco = resultset.getInt("id_endereco");
		byte[] foto = resultset.getBytes("foto");
		
		String email = resultset.getString("email");
		Date data_nascimento = resultset.getDate("data_nascimento");
		
		return new Pessoa ( id, nome, cpf, identidade, id_endereco, email, data_nascimento, foto);
	}

	
	public ArrayList<Pessoa> getPessoas() throws RepositoryException {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT * FROM pessoa");
			 
			 while(resultset.next()){
				 pessoas.add(createPessoa(resultset));
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
		 return pessoas;
	}

	@Override
	public Pessoa getId(int id) throws RepositoryException {
		Pessoa pessoa = null;
		try {
			String sql = "SELECT * FROM pessoa where id = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			
			
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				pessoa = createPessoa(resultset);
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
		
		
		return pessoa;
	}
	
	
	public boolean hasId(int id) throws RepositoryException {
		try {
            String sql = "SELECT * FROM pessoa WHERE id = ?";
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

	public int getIdEndereco(int id1) throws RepositoryException {

		int id = 0;
		try {
            String sql = "SELECT id_endereco FROM pessoa WHERE id = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setInt(1, id1);
           
            
            ResultSet resultset = statement.executeQuery();
           
            if(resultset.next()){
            	id = resultset.getInt("id_endereco");
            }
            resultset.close();
            
            
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
		
		return id;
	}
	
	public int getId(String cpf) throws RepositoryException {
		
		int id = 0;
		try {
            String sql = "SELECT id FROM pessoa WHERE CPF = ?";
            PreparedStatement statement = pm.getConnection().prepareStatement(sql);
           
            statement.setString(1, cpf);
           
            
            ResultSet resultset = statement.executeQuery();
           
            if(resultset.next()){
            	id = resultset.getInt("id");
            }
            resultset.close();
            
            
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
		
		return id;
	}
	
	
	public int getIdPessoa() throws RepositoryException {
		
		int id = 0;
		
		try{
			
			String sql = "select id from pessoa where id = (select max(id) from pessoa )";
			
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
