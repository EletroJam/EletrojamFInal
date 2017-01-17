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
import interfaces.RepositorioOperacao;
import negocio.Operacao;

public class RepositorioOperacaoDB implements RepositorioOperacao {

	private static RepositorioOperacaoDB instance;
	private PersistenceMechanismRDBMS pm;

	private RepositorioOperacaoDB() {

		try {

			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();

			// c.conect();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	public synchronized static RepositorioOperacaoDB getInstance() {
		if (instance == null) {
			instance = new RepositorioOperacaoDB();
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

	public void insert(Operacao operacao) throws RepositoryException {

		try {

			// operacao.resetStringTypes();
			String sql = "INSERT INTO operacao (parcelas,valor_juros,valor_pedido,valor_diario, multa,tipo,porcentagem_lucro_funcionario,valor_cobrador,extra, id_funcionario_cobrador, id_funcionario_venda, valor_lucro, lucro_mes_funcionario, porcentagem_lucro, id_funcionario_entregador, data_operacao_realizada, porcentagem_lucro_entregador, valor_cobrador_multa, porfora, flag, intervalo)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// getSexo()+""

			statement.setInt(1, operacao.getParcelas());
			statement.setDouble(2, operacao.getValor_juros());
			statement.setDouble(3, operacao.getValor_pedido());
			statement.setDouble(4, operacao.getValorDiario());
			statement.setInt(5, operacao.getMulta());
			statement.setString(6, operacao.getTipo());
			statement.setInt(7, operacao.getPorcentagemLucroFuncionario());
			statement.setDouble(8, operacao.getValorCobrador());
			statement.setDouble(9, operacao.getExtra());
			statement.setInt(10, operacao.getId_funcionario_cobrador());
			statement.setInt(11, operacao.getId_funcionario_venda());
			statement.setDouble(12, operacao.getValorLucro());
			statement.setDouble(13, operacao.getLucroMesFuncionario());
			statement.setDouble(14, operacao.getPorcentagemLucro());
			statement.setInt(15, operacao.getId_funcionario_entregador());
			statement.setDate(16, operacao.getData_operacao_realizada());
			statement.setInt(17, operacao.getPorcentagem_lucro_entregador());
			statement.setDouble(18, operacao.getValor_cobrador_multa());
			statement.setDouble(19, operacao.getPorfora());
			statement.setInt(20, operacao.getFlag());
			statement.setInt(21, operacao.getIntervalo());

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
			String sql = "Delete from operacao where id = ?";
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
			String sql = "SELECT * FROM operacao WHERE id = ?";
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

	private Operacao createOperacao(ResultSet resultset) throws SQLException, RepositoryException {

		// int id = resultset.getInt("id");
		int parcelas = resultset.getInt("parcelas");
		double valor_juros = resultset.getDouble("valor_juros");
		double valor_pedido = resultset.getDouble("valor_pedido");
		double valorDiario = resultset.getDouble("valor_diario");
		int multa = resultset.getInt("multa");
		String tipo = resultset.getString("tipo");
		int porcentagemLucroFuncionario = resultset.getInt("porcentagem_lucro_funcionario");
		double valorCobrador = resultset.getDouble("valor_cobrador");
		double extra = resultset.getDouble("extra");
		int id_funcionario_cobrador = resultset.getInt("id_funcionario_cobrador");
		int id_funcionario_venda = resultset.getInt("id_funcionario_venda");
		double valorLucro = resultset.getDouble("valor_lucro");
		double lucroMesFuncionario = resultset.getDouble("lucro_mes_funcionario");
		double porcentagemLucro = resultset.getDouble("porcentagem_lucro");
		int id_funcionario_entregador = resultset.getInt("id_funcionario_entregador");
		Date data_operacao_realizada = resultset.getDate("data_operacao_realizada");
		int porcentagem_lucro_entregador = resultset.getInt("porcentagem_lucro_entregador");
		double porfora = resultset.getDouble("porfora");
		double valor_cobrador_multa = resultset.getDouble("valor_cobrador_multa");
		int flag = resultset.getInt("flag");
		int intervalo = resultset.getInt("intervalo");

		return new Operacao(parcelas, valor_juros, valor_pedido, valorDiario, multa, tipo, porcentagemLucroFuncionario,
				valorCobrador, extra, id_funcionario_cobrador, id_funcionario_venda, valorLucro, lucroMesFuncionario,
				porcentagemLucro, id_funcionario_entregador, data_operacao_realizada, porcentagem_lucro_entregador,
				porfora, valor_cobrador_multa, flag, intervalo);
	}

	public Operacao get(int id) throws RepositoryException {

		Operacao operacao = null;
		try {
			String sql = "SELECT * FROM operacao where id = ?";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
				operacao = createOperacao(resultset);
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

		return operacao;

	}

	public ArrayList<Operacao> getOperacaos() throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("SELECT * FROM operacao");

			while (resultset.next()) {
				operacoes.add(createOperacao(resultset));
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
		return operacoes;
	}

	public int getId() throws RepositoryException {

		int id = 0;

		try {

			String sql = "select id from operacao where id = (select max(id) from operacao )";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				id = resultset.getInt("id");
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

		return id;
	}

	public ArrayList<String> AllRotas() throws RepositoryException {

		ArrayList<String> operacaoRotas = new ArrayList<String>();

		try {

			String sql = "select distinct rota from operacao";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			// System.out.println(statement);

			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				operacaoRotas.add(resultset.getString("rota"));

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

		return operacaoRotas;
	}

	public ArrayList<Operacao> getCobradoresComissao(Date data1, Date data2, boolean x) throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		if (x == false) {
			try {

				String sql = "select s.func , sum(s.parcelas) as diario, count(s.id_operacao) as cobranca, sum( s.multas) as multa_diaria from status s where s.data_inicial_pagamento BETWEEN ? and ? and check_pag = 'TRUE' GROUP BY s.func";
				PreparedStatement statement = pm.getConnection().prepareStatement(sql);
				statement.setDate(1, data1);
				statement.setDate(2, data2);

				ResultSet resultset = statement.executeQuery();

				while (resultset.next()) {
					operacoes.add(createCobradorComissao(resultset));
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
			return operacoes;
		} else {
			return operacoes;
		}
	}

	private Operacao createCobradorComissao(ResultSet resultset) throws SQLException {

		String nomecobrador = resultset.getString("func");
		double diario = resultset.getDouble("diario");
		double multa_diaria = resultset.getDouble("multa_diaria");
		Double intervalo = resultset.getDouble("cobranca"); //intervaloi ta como totalcobrador na classe

		return new Operacao(nomecobrador, diario, multa_diaria, intervalo);
	}

	public ArrayList<Operacao> getEntregadorComissao(Date data1, Date data2) throws RepositoryException {
		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			String sql = "select distinct p.nome, Round(sum(o.valor_pedido * o.porcentagem_lucro_entregador /100),2) as total_entregador from pessoa p, funcionario f, operacao o where p.id = f.id_funcionario and o.id_funcionario_entregador = f.id_funcionario and o.data_operacao_realizada BETWEEN ? and ? group by p.nome order by p.nome";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setDate(1, data1);
			statement.setDate(2, data2);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createEntregadorComissao(resultset));
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
		return operacoes;

	}

	private Operacao createEntregadorComissao(ResultSet resultset) throws SQLException {

		String nomeentregador = resultset.getString("nome");
		double total_entregador = resultset.getDouble("total_entregador");

		return new Operacao(nomeentregador, total_entregador);
	}

	public ArrayList<Operacao> getlucroMesVendedor(Date data1, Date data2) throws RepositoryException {
		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			String sql = "select p.nome, sum(o.valor_pedido) as valorpedido, sum(lucro_mes_funcionario) as lucrovendedor from operacao o, pessoa p where o.id_funcionario_venda = p.id and o.data_operacao_realizada BETWEEN ? and ? group by p.nome order by p.nome";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setDate(1, data1);
			statement.setDate(2, data2);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createVendedorComissao1(resultset));
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
		return operacoes;

	}

	private Operacao createVendedorComissao1(ResultSet resultset) throws SQLException {

		String nomeentregador = resultset.getString("nome");
		double total_entregador = resultset.getDouble("valorpedido");
		double lucroVendedorRel = resultset.getDouble("lucrovendedor");

		return new Operacao(nomeentregador, total_entregador, lucroVendedorRel);
	}

	private Operacao createVendedorComissao(ResultSet resultset) throws SQLException {

		String nomecobrador = resultset.getString("nome");
		double diario = resultset.getDouble("parcelas");
		double multa_diaria = resultset.getDouble("multa");
		double totalcobrador = resultset.getDouble("passando");
		// double lucroMesFuncionario = resultset.getDouble("total");

		return new Operacao(nomecobrador, diario, multa_diaria, totalcobrador);
	}

	public ArrayList<Operacao> getVendedorComissao(Date data1, Date data2) throws RepositoryException {
		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			String sql = "SELECT distinct  p.nome, sum (s.multas * o.valor_juros) as multa, sum (s.parcelas * o.valor_diario)  as parcelas , sum (s.multas * o.valor_juros + s.parcelas * o.valor_diario) as total, sum(s.parcelas * o.porfora) as passando FROM status s, operacao o, pessoa p where s.id_operacao = o.id  and  p.id= o.id_funcionario_venda and s.data_inicial_pagamento BETWEEN  ? and ? group by  p.nome order by p.nome";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setDate(1, data1);
			statement.setDate(2, data2);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createVendedorComissao(resultset));
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
		return operacoes;

	}

	public ArrayList<Operacao> getCobradoresGeral(Date data1, Date data2) throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			String sql = "SELECT distinct s.func, sum (s.multas * o.valor_juros) as multa, sum (s.parcelas * o.valor_diario)  as parcelas , sum (s.multas * o.valor_juros + s.parcelas * o.valor_diario) as total FROM status s, operacao o where s.id_operacao = o.id and s.data_inicial_pagamento BETWEEN ? AND ? group by s.func order by s.func";

			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setDate(1, data1);
			statement.setDate(2, data2);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createCobradorComissaoGeral(resultset));
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
		return operacoes;
	}

	private Operacao createCobradorComissaoGeral(ResultSet resultset) throws SQLException {

		String nomecobrador = resultset.getString("func");
		double diario = resultset.getDouble("parcelas");
		double multa_diaria = resultset.getDouble("multa");
		double totalcobrador = resultset.getDouble("total");

		return new Operacao(nomecobrador, diario, multa_diaria, totalcobrador);
	}

	public ArrayList<Operacao> getValorCobrador(Date data1, Date data2) throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			String sql = "SELECT distinct  p.nome, sum(o.parcelas * o.valor_cobrador) as totalcobrador , sum(o.parcelas * o.porfora) as passando, Round(sum (o.porcentagem_lucro_entregador * o.valor_pedido /100 ),2) as entregador, sum(o.parcelas * o.valor_diario) as receber FROM operacao o, pessoa p where  p.id = o.id_funcionario_venda and o.data_operacao_realizada BETWEEN ? and ? group by  p.nome order by p.nome";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);
			statement.setDate(1, data1);
			statement.setDate(2, data2);

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createCobradorValor(resultset));
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
		return operacoes;
	}

	private Operacao createCobradorValor(ResultSet resultset) throws SQLException {

		String nomecobrador = resultset.getString("nome");
		double valorCobradorNovo = resultset.getDouble("totalcobrador");
		double valorCobradorNovo2 = resultset.getDouble("passando");
		double entregador = resultset.getDouble("entregador");
		double receber = resultset.getDouble("receber");

		return new Operacao(valorCobradorNovo, valorCobradorNovo2, nomecobrador, entregador, receber);
	}

	public ArrayList<Operacao> getEmprestimoDiario(Date data1, boolean x, int id) throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		if (x == false) {

			try {

				String sql = "select p.apelido, o.valor_pedido, o.parcelas, o.valor_diario, o.valor_juros, o.porfora, f.nome as vendedorResponsavel  from operacao o, realiza r, cliente p, pessoa f where o.id_funcionario_venda = f.id and o.id = r.id_operacao and r.id_cliente = p.id_cliente and o.data_operacao_realizada = ? order by p.apelido";
				PreparedStatement statement = pm.getConnection().prepareStatement(sql);
				statement.setDate(1, data1);

				ResultSet resultset = statement.executeQuery();

				while (resultset.next()) {
					operacoes.add(createEmprestimoDia(resultset, x));
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
			return operacoes;
		} else {
			try {

				String sql = "select p.apelido, o.valor_pedido, o.parcelas, o.valor_diario, o.valor_juros, o.porfora from operacao o, realiza r, cliente p where o.id = r.id_operacao and r.id_cliente = p.id_cliente and o.data_operacao_realizada = ? and o.id_funcionario_venda = ? order by p.apelido";
				PreparedStatement statement = pm.getConnection().prepareStatement(sql);
				statement.setDate(1, data1);
				statement.setInt(2, id);

				ResultSet resultset = statement.executeQuery();

				while (resultset.next()) {
					operacoes.add(createEmprestimoDia(resultset, x));
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
			return operacoes;
		}
	}

	private Operacao createEmprestimoDia(ResultSet resultset, boolean x) throws SQLException {

		if (x == false){
			String nomecobrador = resultset.getString("apelido");
			double valorpedido = resultset.getDouble("valor_pedido");
			int parcelas = resultset.getInt("parcelas");
			double diario = resultset.getDouble("valor_diario");
			double multa = resultset.getDouble("valor_juros");
			double profora = resultset.getDouble("porfora");
			String nomeVendedor = resultset.getString("vendedorResponsavel");
			return new Operacao(nomecobrador, valorpedido, parcelas, diario, multa, profora, nomeVendedor);
	
		}else{
			
			String nomecobrador = resultset.getString("apelido");
			double valorpedido = resultset.getDouble("valor_pedido");
			int parcelas = resultset.getInt("parcelas");
			double diario = resultset.getDouble("valor_diario");
			double multa = resultset.getDouble("valor_juros");
			double profora = resultset.getDouble("porfora");
			return new Operacao(nomecobrador, valorpedido, parcelas, diario, multa, profora, "");
		}
		

		
	}

	public ArrayList<Operacao> getResponsavelGeral(Date data1, Date data2, int id) throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			String sql = "select o.id, o.data_operacao_realizada, o.valor_pedido , (o.parcelas * o.valor_diario ) as receber , c.apelido, o.parcelas, o.lucro_mes_funcionario, (o.porfora * o.parcelas) as porfora, (o.parcelas * o.valor_cobrador) as diario, ((o.parcelas * o.valor_cobrador) + (o.porfora * o.parcelas ) +  o.lucro_mes_funcionario) as total, o.id_funcionario_entregador,"
					+ " Round((o.porcentagem_lucro_entregador * o.valor_pedido /100 ),2) as entregador, Round((o.porfora * o.parcelas),2) as vLis  from operacao o, realiza r, cliente c where o.id = r.id_operacao and r.id_cliente = c.id_cliente and o.id_funcionario_venda = ? and o.data_operacao_realizada between ? and ?  order by o.id";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			statement.setInt(1, id);
			statement.setDate(2, data1);
			statement.setDate(3, data2);
			

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createResponsavelGeral(resultset));
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
		return operacoes;
	}

	private Operacao createResponsavelGeral(ResultSet resultset) throws SQLException {

		String nomecliente = resultset.getString("apelido");
		Date dataInicial = resultset.getDate("data_operacao_realizada");
		int parcelas = resultset.getInt("parcelas");
		double valorEmprestado = resultset.getDouble("valor_pedido");
		double comissao = resultset.getDouble("lucro_mes_funcionario");
		double valorPassando = resultset.getDouble("porfora");
		double cobradorReceber = resultset.getDouble("diario");
		double totalRecebido = resultset.getDouble("total");
		int id_entregador = resultset.getInt("id_funcionario_entregador");
		double valorEntregador = resultset.getDouble("entregador");
		double valorLis = resultset.getDouble("vLis");
		
		int id = resultset.getInt("id");

		return new Operacao(nomecliente, dataInicial, parcelas, valorEmprestado, comissao, valorPassando,
				cobradorReceber, totalRecebido, id_entregador, valorEntregador, valorLis, 0, id, 0);
	}

	public ArrayList<Operacao>  getMultasResp(Date data1, Date data2, int id, int id_op) throws RepositoryException {

		ArrayList<Operacao> operacoes = new ArrayList<Operacao>();

		try {

			//String sql = "select count(s.multas) as mul from status s, operacao o where o.id = s.id_operacao and o.id_funcionario_venda = ? and s.data_inicial_pagamento between ?  and  ? and s.multas > 0 and o.id = ?";
			String sql ="select o.id, c.apelido, sum(s.multas) as mul, o.data_operacao_realizada from status s, operacao o, realiza r, cliente c where o.id = s.id_operacao and r.id_operacao = o.id and r.id_cliente = c.id_cliente and s.data_inicial_pagamento between ? and ? and s.multas > 0 and o.id_funcionario_venda = ? group by o.id, c.apelido, o.data_operacao_realizada order by id";
			PreparedStatement statement = pm.getConnection().prepareStatement(sql);

			
			statement.setDate(1, data1);
			statement.setDate(2, data2);
			statement.setInt(3, id);
			//statement.setInt(4, id_op);
		

			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				operacoes.add(createResponsavelGeralMultas(resultset));
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
		return operacoes;
	}

	private Operacao createResponsavelGeralMultas(ResultSet resultset) throws SQLException {
		
		double numMulta = resultset.getDouble("mul");
		int id = resultset.getInt("id");
		String apelido = resultset.getString("apelido"); 
		Date dataOp = resultset.getDate("data_operacao_realizada");
		

		return new Operacao(numMulta, id, apelido, dataOp);
	}

}
