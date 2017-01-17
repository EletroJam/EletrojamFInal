package repositorios;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conexao.PersistenceMechanismException;
import conexao.PersistenceMechanismRDBMS;
import conexao.RepositoryException;
import interfaces.RepositorioStatus;
import negocio.Operacao;
import negocio.Status;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RepositorioStatusDB implements RepositorioStatus {

	private static RepositorioStatusDB instance;
	private PersistenceMechanismRDBMS pm;

	private RepositorioStatusDB() {

		try {

			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();

			// c.conect();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	public synchronized static RepositorioStatusDB getInstance() {
		if (instance == null) {
			instance = new RepositorioStatusDB();
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

	public void insert(Status status) throws RepositoryException {

		try {

			// operacao.resetStringTypes();
			String sql = "INSERT INTO status (numero_parcelas,valor_Recebido,id_operacao,tipo_pagamento, tipo_pagamento_dia, "
					+ "data_inicial_pagamento, multa, check_pag, parcelas, multas, atraso, parcela_atual, nome_cliente_todos,"
					+ " parc_paga, atraso_parc, func, data_alvo_sequencial )"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// getSexo()+""

			statement.setInt(1, status.getNumero_parcelas());
			statement.setDouble(2, status.getValor_Recebido());
			statement.setInt(3, status.getId_operacao());
			statement.setString(4, status.getTipo_pagamento());
			statement.setString(5, status.getTipo_pagamento_dia());
			statement.setDate(6, status.getDataInicialPagamento());
			statement.setInt(7, status.getMulta());
			statement.setBoolean(8, status.isCheck_pag());
			statement.setInt(9, status.getParcelas());
			statement.setInt(10, status.getMultas());
			statement.setInt(11, status.getAtraso());
			statement.setInt(12, status.getParcela_atual());
			statement.setString(13, status.getNome_cliente_todos());
			statement.setInt(14, status.getParc_paga());
			statement.setInt(15, status.getAtraso_parc());
			statement.setString(16, status.getFunc());
			statement.setDate(17, status.getDataAlvoSequencial());

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

	public void update(boolean status, double valor_recebido, int id, int parcelas, int multas,
			String tipo_pagamento_dia, int numero_parcelas) throws RepositoryException {
		try {

			String sql = "UPDATE status set check_pag = ?, valor_recebido = ?, parcelas = ?, tipo_pagamento_dia = ?, multas = ?, numero_parcelas = ?  where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setBoolean(1, status);
			statement.setDouble(2, valor_recebido);
			statement.setInt(3, parcelas);
			statement.setString(4, tipo_pagamento_dia);
			statement.setInt(5, multas);
			statement.setInt(6, numero_parcelas);
			statement.setInt(7, id);

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

	public void updateDataInicial(int id, Status st) throws RepositoryException {
		try {
			String sql = "UPDATE status set data_inicial_pagamento =? where id =?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			System.out.println(st.getDataInicialPagamento() + " to aqui");

			statement.setDate(1, st.getDataInicialPagamento());
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

	public void updateUno(int parc_atual, int id) throws RepositoryException {

		try {

			String sql = "UPDATE status set parc_paga = ?  where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setInt(1, parc_atual);
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

	public void updateLivre(int parc_atual, int id) throws RepositoryException {

		try {

			String sql = "UPDATE status set ata_inicial_pagamento =?  where id =?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setInt(1, parc_atual);
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

	public void delete(int id) throws RepositoryException {
		try {
			// Pessoa pessoa = get(cpf);
			String sql = "Delete from status where id_operacao = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
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

	public void deleteStatusUnico(int id) throws RepositoryException {
		try {
			// Pessoa pessoa = get(cpf);
			String sql = "Delete from status where id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);
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

	public boolean has(int id) throws RepositoryException {
		try {
			String sql = "SELECT * FROM status WHERE id = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setInt(1, id);

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

	private Status createStatus(ResultSet resultset) throws SQLException, RepositoryException {

		int id = resultset.getInt("id");
		;
		// Date data = resultset.getDate("data");
		int numero_parcelas = resultset.getInt("numero_parcelas");
		;
		double valor_Recebido = resultset.getDouble("valor_Recebido");
		int id_operacao = resultset.getInt("id_operacao");
		String tipo_pagamento = resultset.getString("tipo_pagamento");
		;
		String tipo_pagamento_dia = resultset.getString("tipo_pagamento_dia");
		;
		Date dataInicialPagamento = resultset.getDate("data_inicial_pagamento");
		System.out.println(dataInicialPagamento);
		int multa = resultset.getInt("multa");
		boolean check_pag = resultset.getBoolean("check_pag");
		int parcelas = resultset.getInt("parcelas");
		int multas = resultset.getInt("multas");
		int atraso = resultset.getInt("atraso");
		int parcela_atual = resultset.getInt("parcela_atual");
		String nome_cliente_todos = resultset.getString("nome_cliente_todos");
		// Date data_pagamento = resultset.getDate ("data_pagamento");
		int parc_paga = resultset.getInt("parc_paga");
		int atraso_parc = resultset.getInt("atraso_parc");
		String cobrador = resultset.getString("func");
		Date dataAlvoSequencial = resultset.getDate("data_alvo_sequencial");

		return new Status(id, numero_parcelas, valor_Recebido, id_operacao, tipo_pagamento, tipo_pagamento_dia,
				dataInicialPagamento, multa, check_pag, parcelas, multas, atraso, parcela_atual, nome_cliente_todos,
				parc_paga, atraso_parc, cobrador, dataAlvoSequencial);
	}

	public Status get(int id) throws RepositoryException {

		Status status = null;
		try {
			String sql = "SELECT * FROM status where id = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				status = createStatus(resultset);
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

		return status;
	}

	public ArrayList<Status> AllStatus() throws RepositoryException {

		ArrayList<Status> statusTodos = new ArrayList<Status>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM status");

			while (resultset.next()) {
				statusTodos.add(createStatus(resultset));
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
		return statusTodos;
	}

	public ArrayList<Status> AllStatusCliente(int idOp) throws RepositoryException {

		ArrayList<Status> statusTodos = new ArrayList<Status>();

		try {

			String sql = ("SELECT * FROM status where id_operacao = ? order by id");

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, idOp);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				statusTodos.add(createStatus(resultset));
			}
			resultset.close();

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
		return statusTodos;
	}
	
	

	public ArrayList<Status> AllStatusReceber() throws RepositoryException {

		ArrayList<Status> statusTodos = new ArrayList<Status>();

		try {

			String sql = ("select s.nome_cliente_todos ,sum ((s.numero_parcelas - s.parc_paga) * o.valor_diario) as receber, "
					+ "s.data_inicial_pagamento from status s, operacao o where o.id = s.id_operacao and s.check_pag = 'FALSE' "
					+ "group by s.nome_cliente_todos, s.data_inicial_pagamento order by data_inicial_pagamento");

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				statusTodos.add(createStatusReceber(resultset));
			}
			resultset.close();

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
		return statusTodos;
	}
	
	public Double getValorReceber() throws RepositoryException {

		Double receber = 0.0;

		try {

			String sql = "select sum ((s.numero_parcelas - s.parc_paga) * o.valor_diario) as receber from status s, operacao o "
					+ "where o.id = s.id_operacao and s.check_pag = 'FALSE'";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				receber = resultset.getDouble("receber");
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

		return receber;
	}
	
	private Status createStatusReceber(ResultSet resultset) throws SQLException, RepositoryException {

		
		String cliente = resultset.getString("nome_cliente_todos");
		Double receber = resultset.getDouble("receber");
		Date dataInicialPagamento = resultset.getDate("data_inicial_pagamento");
		


		return new Status(cliente, dataInicialPagamento, receber);
	}

	public ArrayList<Status> AllStatusCliente() throws RepositoryException {

		ArrayList<Status> statusTodos = new ArrayList<Status>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery(
					"select distinct s.id, s.id_operacao, s.func, s.numero_parcelas, s.tipo_pagamento, s.data_inicial_pagamento, s.tipo_pagamento_dia, s.nome_cliente_todos,  s.multa, s.check_pag, s.atraso, s.parcela_atual, s.parc_paga, s.atraso_parc from  status s order by s.nome_cliente_todos");
			while (resultset.next()) {
				statusTodos.add(createStatus2(resultset));
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
		return statusTodos;
	}

	private Status createStatus2(ResultSet resultset) throws SQLException {

		int id = resultset.getInt("id");

		int id_operacao = resultset.getInt("id_operacao");

		// String cliente = resultset.getString("apelido");

		// String cobrador = resultset.getString("func");

		int numero_parcelas = resultset.getInt("numero_parcelas");

		String tipo_pagamento = resultset.getString("tipo_pagamento");
		;

		Date dataInicialPagamento = resultset.getDate("data_inicial_pagamento");

		String tipo_pagamento_dia = resultset.getString("tipo_pagamento_dia");
		;

		int multa = resultset.getInt("multa");

		boolean check_pag = resultset.getBoolean("check_pag");

		int atraso = resultset.getInt("atraso");
		int parcela_atual = resultset.getInt("parcela_atual");

		String nome_cliente_todos = resultset.getString("nome_cliente_todos");

		int parc_paga = resultset.getInt("parc_paga");

		int atraso_parc = resultset.getInt("atraso_parc");

		String func = resultset.getString("func");

		// Date data_pagamento = resultset.getDate ("data_pagamento");

		return new Status(id, id_operacao, "", numero_parcelas, tipo_pagamento, dataInicialPagamento,
				tipo_pagamento_dia, multa, check_pag, atraso, parcela_atual, nome_cliente_todos, parc_paga, atraso_parc,
				func);

	}

	public int getId(int id) throws RepositoryException {

		int id2 = 0;

		try {

			String sql = "select id_operacao from status where id = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getInt("id_operacao");
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

		return id2;
	}


	public int getIdStatus(int id) throws RepositoryException {

		int id2 = 0;

		try {

			String sql = "select id from status where id_operacao = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getInt("id");
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

		return id2;
	}

	public String getNomeFull(int id) throws RepositoryException {

		String id2 = "";

		try {

			String sql = "select nome_cliente_todos from status where id_operacao = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getString("nome_cliente_todos");
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

		return id2;
	}

	public ArrayList<Integer> AllParc(int id) throws RepositoryException {

		ArrayList<Integer> statusTodos = new ArrayList<Integer>();

		try {

			String sql = "select parc_paga, atraso_parc from status where id = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				statusTodos.add(resultset.getInt("parc_paga"));
				statusTodos.add(resultset.getInt("atraso_parc"));
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
		return statusTodos;
	}

	public void relatorioCobranca(String func, Date data, String x, String rota, String total, String divida)
			throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("func", func);
		parametros.put("data", data);
		parametros.put("rotas", rota);
		parametros.put("total", total);
		parametros.put("divida", divida);

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);

	}

	public void relatorioDia(String func, Date data, String x, BigDecimal vMulta, BigDecimal vParc, BigDecimal vTotal)
			throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("func", func);
		parametros.put("data", data);
		// parametros.put("rotas", rota);
		parametros.put("totalMult", vMulta);
		parametros.put("totalParc", vParc);
		parametros.put("totalTo", vTotal);

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	public Double getMulta(Date data, String cobradorNome) throws RepositoryException {

		double id2 = 0.0;

		try {

			String sql = "SELECT sum (s.multas * o.valor_juros) as x FROM status s, operacao o where s.id_operacao = o.id and s.data_inicial_pagamento = ? and s.func = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setDate(1, data);
			statement.setString(2, cobradorNome);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getDouble("x");
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

		return id2;
	}

	public Double getParc(Date data, String cobradorNome) throws RepositoryException {

		double id2 = 0.0;

		try {

			String sql = "SELECT sum (s.parcelas * o.valor_diario) as x FROM status s, operacao o where s.id_operacao = o.id and s.data_inicial_pagamento = ? and s.func = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setDate(1, data);
			statement.setString(2, cobradorNome);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getDouble("x");
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

		return id2;
	}

	public Double getTotal(Date data) throws RepositoryException {

		double id2 = 0.0;

		try {

			String sql = "SELECT sum (s.valor_recebido) as x FROM status s where s.data_inicial_pagamento = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setDate(1, data);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getDouble("x");
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

		return id2;
	}

	public ArrayList<Status> AllStatusHistorico(int id) throws RepositoryException {

		ArrayList<Status> statusTodos = new ArrayList<Status>();

		try {

			String sql = "select distinct s.id, s.id_operacao, c.apelido, t.nome as func, s.numero_parcelas, s.tipo_pagamento, s.data_inicial_pagamento, s.tipo_pagamento_dia, s.nome_cliente_todos, s.multa, s.check_pag, s.atraso, s.parcela_atual, s.parc_paga, s.atraso_parc from (select p.nome from status s, operacao o, funcionario f, pessoa p where s.id_operacao = o.id and o.id_funcionario_cobrador = p.id) t, status s, pessoa p, cliente c, realiza r, operacao o, funcionario f where s.id_operacao = r.id_operacao and r.id_cliente = c.id_cliente and c.id_cliente = p.id and id_operacao = ?";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				statusTodos.add(createStatus2(resultset));
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
		return statusTodos;
	}

	// Total Recebido
	public Double getTotalRel(Date dataIni, Date dataFim) throws RepositoryException {

		double id2 = 0.0;

		try {

			String sql = "SELECT sum(s.multas * o.valor_juros + s.parcelas * o.valor_diario) as total FROM status  s, operacao o where s.id_operacao = o.id and s.data_inicial_pagamento BETWEEN ? AND ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setDate(1, dataIni);
			statement.setDate(2, dataFim);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getDouble("total");
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

		return id2;
	}

	public void relatorioComissaoCobradorEntregador(Date ini, Date fim, String x) throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("dataIni", ini);
		parametros.put("dataFim", fim);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	public void relatorioComissaoVendedorCobrador(Date dataIni, Date dataFim, String x) throws JRException {
		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("dataIni", dataIni);
		parametros.put("dataFim", dataFim);

		parametros.put("caminho", "relatorios/cobradorRelatorioVendedor.jasper");

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);

	}

	public void relatorioComissaoCobradorEntregador2(Date ini, Date fim, String x) throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("dataIni", ini);
		parametros.put("dataFim", fim);

		// parametros.put("caminho",
		// "relatorios/cobradorRelatorioVendedor.jasper");

		// parametros.put("caminho",
		// "C:/Users/Zeke/JaspersoftWorkspace/MyReports/cobradorRelatorioVendedor.jasper");

		// JasperCompileManager.compileReportToFile("C:/Users/Zeke/JaspersoftWorkspace/MyReports/cobradorRelatorioVendedor.jrxml",
		// "C:/Users/Zeke/JaspersoftWorkspace/MyReports/cobradorRelatorioVendedor.jasper");

		double z = 0;
		try {
			z = getTotalRel(ini, fim);
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		parametros.put("totalRec", z);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	private Connection Conexao() {
		String driver = "org.postgresql.Driver";
		String user = "postgres";
		String senha = "admin123";
		String url = "jdbc:postgresql://localhost:5432/EletroJam";
		Connection con = null;

		try {
			Class.forName(driver);

			con = (Connection) DriverManager.getConnection(url, user, senha);

			// System.out.println("Conexão realizada com sucesso.");

		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}

		return con;
	}

	public Double getTotalPercas(Date dataIni, Date dataFim) throws RepositoryException {

		double id2 = 0.0;

		try {

			String sql = "select sum(o.extra) as num  from status s, operacao o where s.id_operacao = o.id and  s.check_pag = 'TRUE' and data_inicial_pagamento between ? and ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setDate(1, dataIni);
			statement.setDate(2, dataFim);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id2 = resultset.getDouble("num");
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

		return id2;
	}

	// historico resumido
	public void relatorioHistoricoGeral(int id, String z, String x) throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("idOp", id);
		// parametros.put("localizar", "relatorios/teste22.jasper");
		parametros.put("clienteNome", z);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	// nome do status
	public String getStatusNome(int id) throws RepositoryException {

		String nome = "";

		try {

			String sql = "select tipo_pagamento_dia from status where data_inicial_pagamento < (select max (data_inicial_pagamento) from status where id_operacao = ?) and id_operacao = ?  order by data_inicial_pagamento desc  limit 1";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			statement.setInt(1, id);
			statement.setInt(2, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				nome = resultset.getString("tipo_pagamento_dia");
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

		return nome;
	}

	// operacao un
	public void relatorioHistoricoUnic(int id, String nome, String x) throws JRException {
		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("idClientee", id);
		parametros.put("clienteNome", nome);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	public ArrayList<Double> getSaldoEDiario(String nomeF, Date data) throws RepositoryException {
		ArrayList<Double> valor = new ArrayList<Double>();

		try {

			String sql = "select distinct sum(s.numero_parcelas * o.valor_diario - s.parc_paga * o.valor_diario) as total, sum (s.atraso_parc * o.valor_diario + s.multa * o.valor_juros) as divida from status s, pessoa p, cliente c, realiza r, operacao o where s.id_operacao = r.id_operacao and r.id_cliente = c.id_cliente and c.id_cliente = p.id and r.id_operacao = o.id and s.func = ? and s.data_inicial_pagamento = ? ";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setString(1, nomeF);
			statement.setDate(2, data);
			// System.out.println(statement);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				valor.add(resultset.getDouble("total"));
				valor.add(resultset.getDouble("divida"));
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

		return valor;

	}

	public ArrayList<Double> getSaldoEDiarioTodos(String nomeF, Date data) throws RepositoryException {
		ArrayList<Double> valor = new ArrayList<Double>();

		try {

			String sql = "select distinct sum(s.numero_parcelas * o.valor_diario - s.parc_paga * o.valor_diario) as total, sum (s.atraso_parc * o.valor_diario + s.multa * o.valor_juros) as divida from status s, pessoa p, cliente c, realiza r, operacao o where s.id_operacao = r.id_operacao and r.id_cliente = c.id_cliente and c.id_cliente = p.id and r.id_operacao = o.id and s.data_inicial_pagamento = ? ";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// statement.setString(1, nomeF);
			statement.setDate(1, data);
			// System.out.println(statement);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				valor.add(resultset.getDouble("total"));
				valor.add(resultset.getDouble("divida"));
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

		return valor;

	}

	public void relatorioEmprestimoDiario(Date ini, String nomeResp, int id, String total, String valorPedido,
			int parcelas, String valorParcela, String valorMulta, String valorPorFora, String x) throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("data", ini);
		parametros.put("responsavel", nomeResp);
		parametros.put("idResponsavel", id);
		parametros.put("total", total);
		parametros.put("valorPedido", valorPedido);
		parametros.put("parcelas", parcelas);
		parametros.put("valorParcela", valorParcela);
		parametros.put("valorMulta", valorMulta);
		parametros.put("valorPorFora", valorPorFora);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	public void relatorioResponsavelCliente(int id, String nome, String x) throws JRException {
		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);
		// relatorioClienteResponsavel.jrxml
		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("idResp", id);
		parametros.put("nomeResp", nome);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	public void relatorioResponsavel(Date ini, Date fim, int id, String nome, String x, int parcelas,
			String valorEmprestado, String comissao, String valorPassando, String valorCobrador, String total,
			String totalEntregador, String vlis) throws JRException {
		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		// executa o relatório
		Map parametros = new HashMap();

		parametros.put("idResp", id);
		parametros.put("nomeResp", nome);
		parametros.put("ini", ini);
		parametros.put("fim", fim);
		parametros.put("nome", "Total");
		parametros.put("parcelas", parcelas);
		parametros.put("valorEmprestado", valorEmprestado);
		parametros.put("comissao", comissao);
		parametros.put("valorPassando", valorPassando);
		parametros.put("valorCobrador", valorCobrador);
		parametros.put("total", total);
		parametros.put("totalEntregador", totalEntregador);
		parametros.put("vlis", vlis);

		// parametros.put("localizacaoPedidosSubreport",
		// "relatorios/total3.jasper");
		/*
		 * try { parametros.put("totalRec", getTotalRel(ini,fim));
		 * parametros.put("rec", getTotalRel(ini,fim)); } catch
		 * (RepositoryException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		// parametros.put("SUBREPORT_DIR","relatorios/total3.jasper");
		// parametros.put("conexaoo",Conexao());

		JasperPrint impressao = null;

		try {
			impressao = JasperFillManager.fillReport(relatorio, parametros, pm.getConnection());
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void getRelatorio(List<Operacao> lista, String x, Date ini, Date fim, int id, String nome, int parcelas,
			String valorEmprestado, String comissao, String valorPassando, String valorCobrador, String total,
			String totalEntregador, String vlis, String mult, String multaParcela) throws JRException {

		JasperDesign desenho = JRXmlLoader.load(x);
		// "src/cobranca.jasper"

		JasperReport relatorio = JasperCompileManager.compileReport(desenho);

		@SuppressWarnings("rawtypes")
		Map parametros = new HashMap();
		parametros.put("idResp", id);
		parametros.put("nomeResp", nome);
		parametros.put("ini", ini);
		parametros.put("fim", fim);
		parametros.put("nome", "Total");
		parametros.put("parcelas", parcelas);
		parametros.put("valorEmprestado", valorEmprestado);
		parametros.put("comissao", comissao);
		parametros.put("valorPassando", valorPassando);
		parametros.put("valorCobrador", valorCobrador);
		parametros.put("total", total);
		parametros.put("totalEntregador", totalEntregador);
		parametros.put("vlis", vlis);
		parametros.put("mult", mult);
		parametros.put("multaParcela", multaParcela);

		JRBeanCollectionDataSource datasrc = new JRBeanCollectionDataSource(lista);

		// parametros.put("lista", datasrc);

		// para usar JavaBeanDataSource define 'datasrc' como datasource
		JasperPrint impressao = null;

		impressao = JasperFillManager.fillReport(relatorio, parametros, datasrc);

		try {
			pm.releaseCommunicationChannel();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibe o resultado
		JasperViewer viewer = new JasperViewer(impressao, false);
		// JasperPrintManager.printPage(impressao, 0, false);
		viewer.setVisible(true);

	}

}
