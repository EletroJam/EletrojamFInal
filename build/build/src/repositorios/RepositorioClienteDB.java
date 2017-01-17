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
import interfaces.RepositorioCliente;
import negocio.Cliente;

public class RepositorioClienteDB implements RepositorioCliente{
	private static RepositorioClienteDB instance;
	private PersistenceMechanismRDBMS pm;
	
	//private Conect c;
	
	
	private RepositorioClienteDB() {
		try{
			
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
			
			//c.conect();
			
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static RepositorioClienteDB getInstance (){
		if (instance == null) {
			instance = new RepositorioClienteDB();
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

	
	public void insert(Cliente cliente) throws RepositoryException {
		
		
		try{
			
			cliente.resetStringTypes();
			String sql = "INSERT INTO cliente (apelido, id_cliente, nome_funcionario, id_funcionario)" + "VALUES (?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			//getSexo()+""
		
			statement.setString(1, cliente.getApelido());
			statement.setInt(2, cliente.getId_cliente());
			statement.setString(3, cliente.getNome_funcionario());
			statement.setInt(4, cliente.getId_vendedor());
			
			
			statement.executeUpdate();
			statement.close();
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
	
	public void updateEndereco(int id, Cliente cliente) throws RepositoryException{
		
		try {
			
			String sql = "UPDATE endereco set logadouro = ?, cidade = ?, estado = ?, numero = ?, complemento = ?, bairro = ?, cep = ?  where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, cliente.getLogadouro());
			statement.setString(2, cliente.getCidade());
			statement.setString(3, cliente.getEstado());
			statement.setInt(4, cliente.getNumero());
			statement.setString(5, cliente.getComplemento());
			statement.setString(6, cliente.getBairro());
			statement.setString(7, cliente.getCep());
			statement.setInt(8, id);
			
			
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

	public void updatePessoa(int id_cliente, Cliente cliente) throws RepositoryException{

		
		try {
			
			String sql = "UPDATE pessoa set nome = ?, cpf = ?, identidade = ?, email = ?, data_nascimento = ? where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCpf());
			statement.setString(3, cliente.getIdentidade());
			statement.setString(4, cliente.getEmail());
			statement.setDate(5, cliente.getData_nascimento());
			statement.setInt(6, id_cliente);
			
			
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
	
	
	public void update(String apelido, int id_cliente, String nomeFun) throws RepositoryException {
		
	
		try {
			
			String sql = "UPDATE cliente set apelido = ?, nome_funcionario = ? where id_cliente = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			
			statement.setString(1, apelido);
			statement.setString(2, nomeFun);
			//statement.setInt(3, idFunc);
			statement.setInt(3, id_cliente);
			
			
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
			String sql = "Delete from cliente where cpf = ?";
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

	
	public boolean has(int id) throws RepositoryException {
		try {
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
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

	
	public Cliente get(int id) throws RepositoryException {
		Cliente cliente = null;
		try {
			String sql = "SELECT * FROM cliente where id_cliente = ?";
			
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			
		//	System.out.println(statement);
			ResultSet resultset =  statement.executeQuery();
			
			if(resultset.next()){
				cliente = createCliente(resultset);
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
		
		
		return cliente;
	}

	private Cliente createCliente(ResultSet resultset) throws SQLException, RepositoryException {
		
		
		String apelido = resultset.getString("apelido");
		int id_cliente = resultset.getInt("id_cliente");
		String nome_funcionario = resultset.getString("nome_funcionario");
		int id_vendedor = resultset.getInt("id_funcionario");
		
		return new Cliente (apelido, id_cliente, nome_funcionario, id_vendedor);
	}

	
	public ArrayList<Cliente> getClientes() throws RepositoryException {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT * FROM cliente order by apelido");
			 
			 while(resultset.next()){
				 clientes.add(createCliente(resultset));
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
		 return clientes;
	}
	
	public ArrayList<Cliente> getClientesPessoa() throws RepositoryException {
		
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try{
			
			 Statement statement = (Statement) pm.getCommunicationChannel();
			 ResultSet resultset = statement.executeQuery("SELECT d.numero as cel, z.numero as tel ,p.nome, p.email,  p.cpf, p.identidade, p.data_nascimento, c.apelido, c.id_cliente, e.logadouro, e.cidade, e.estado, e.cep, e.numero, e.complemento, e.bairro, c.nome_funcionario FROM cliente c, pessoa p, endereco e, telefone z, celular d where c.id_cliente = p.id and p.id_endereco = e.id and z.id_pessoa = p.id and d.id_pessoa = p.id order by c.apelido");
			 
			 while(resultset.next()){
				 clientes.add(createClientePessoa(resultset));
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
		 return clientes;
	}
	
	
	private Cliente createClientePessoa(ResultSet resultset) throws SQLException, RepositoryException {
		
		
		String apelido = resultset.getString("apelido");
		int id_cliente = resultset.getInt("id_cliente");
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
		String nome_funcionario = resultset.getString("nome_funcionario");
		
		return new Cliente (apelido, id_cliente, nome, cpf, identidade, email, data_nascimento, logadouro,  cidade,   estado,  cep,  numero, complemento, bairro, numeroCel, numeroTel,nome_funcionario );
	}
	
	
		public ArrayList<Cliente> getClientesHistorico(int id) throws RepositoryException {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try{
			
			
			 
			String sql  = "Select p.nome as func, o.data, o.valor_emprestimo, o.quantidade_atraso, o.id_operacao, o.estado as estadoo from operacoes_cliente o, cliente c, operacao z, pessoa p where z.id = o.id_operacao and p.id = z.id_funcionario_venda and c.id_cliente = o.id_cliente and  c.id_cliente = ? order by o.data";
			 
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultset =  statement.executeQuery();
			
			 while(resultset.next()){
				 clientes.add(createClienteHistorico(resultset));
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
		 return clientes;
	}
	
	
		private Cliente createClienteHistorico(ResultSet resultset) throws SQLException, RepositoryException {
		
		
		String nome_funcionario = resultset.getString("func");
		int id_operacao = resultset.getInt("id_operacao");
		Double valor_emprestimo = resultset.getDouble("valor_emprestimo");
		int quantidade_atraso = resultset.getInt("quantidade_atraso");
		String estadoo = resultset.getString("estadoo");
		Date data = resultset.getDate("data");
		
		
		return new Cliente (data, valor_emprestimo, quantidade_atraso, id_operacao, estadoo, nome_funcionario);
	}
		
		public ArrayList<Cliente> getClientesPorResponsavel(int id) throws RepositoryException {
			
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			
			try{
				
				
				 
				String sql  = "Select c.apelido, t.numero, p.nome from cliente c, celular t, pessoa p where c.id_cliente = p.id and c.id_cliente = t.id_pessoa and c.id_funcionario = ? order by c.apelido";
				 
				PreparedStatement statement = pm.getConnection().prepareStatement(sql);
				statement.setInt(1, id);
				
				ResultSet resultset =  statement.executeQuery();
				
				 while(resultset.next()){
					 clientes.add(createClienteResponsavel(resultset));
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
			 return clientes;
		}

		private Cliente createClienteResponsavel(ResultSet resultset) throws SQLException {
			
			String apelido = resultset.getString("apelido");
			String nome = resultset.getString("nome");
			String numeroCel = resultset.getString("numero");
			
			
			return new Cliente (nome, apelido, numeroCel  );
		}
		
		
}