package controleView;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import conexao.RepositoryException;
import exceptions.ClienteNaoEncontradoException;
import exceptions.PessoaNaoEncontradaException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import negocio.Cliente;
import negocio.Funcionario;
import negocio.Log;
import negocio.Operacao;
import negocio.OperacaoCliente;
import negocio.Pessoa;
import negocio.Realiza;
import negocio.Rota;
import negocio.Status;
import util.AutoCompleteComboBoxListener;

public class ControleTelaCadastroOperacao extends Application implements Initializable {

	public static Stage palcoTelaOperacao = null;
	public static Pessoa pessoa;
	EletroJam fachada = EletroJam.getInstance();

	@FXML
	TextField tfValorPedido;

	@FXML
	TextField tfPorcentagemValorPedido;

	@FXML
	TextField tfValorExtraCobrador;// valor percas futuras

	@FXML
	TextField tfPorcentagemLucroFuncionario;

	@FXML
	TextField tfLucro;

	@FXML
	TextField tfLucroMesFuncionario;

	@FXML
	TextField tfValorDiario;

	@FXML
	TextField tfValorJuros;

	@FXML
	TextField tfParcelas;

	@FXML
	TextField tfPorfora;

	@FXML
	ComboBox<String> cTipoDePagamento;

	@FXML
	ComboBox<Integer> cParcelaMulta;

	@FXML
	ComboBox<Double> cValorCobradorDiario;

	@FXML
	ComboBox<Double> cValorCobradorMulta;

	@FXML
	ComboBox<Funcionario> cCobrador;

	@FXML
	ComboBox<String> cVendedor;

	@FXML
	Button btCadastrar;

	@FXML
	Button btLimpar;

	@FXML
	Button btChecar;

	@FXML
	Label lLucro;

	@FXML
	Label lLucroMesFuncionario;

	@FXML
	Label lValorDiario;

	@FXML
	Label lValorJuros;

	@FXML
	Label lObrigatorio;

	@FXML
	Label lValorPedidp;

	@FXML
	Label lPorcentagemPedido;

	@FXML
	Label lPorcentagemFuncionario;

	@FXML
	Label lPorfora;

	@FXML
	ComboBox<String> cCliente;

	@FXML
	ComboBox<Funcionario> cEntregador;

	@FXML
	DatePicker dCobranca;

	@FXML
	DatePicker dDataOperacao;

	@FXML
	TextField tfPorcentagemEntregador;

	@FXML
	TextField tfPagamentoEs;

	@FXML
	ComboBox<String> cRota;

	public void eventoBtnChecaValores(ActionEvent event) {

		/*
		 * entregador adicionado logica multa cobrador operacao finalizada
		 * inserir funcionario adaptado -checa
		 * 
		 */

		lObrigatorio.setVisible(false);
		// lLucro.setVisible(false);
		lValorJuros.setVisible(false);
		lValorDiario.setVisible(false);
		// lLucroMesFuncionario.setVisible(false);

		// tfLucro.setVisible(false);
		tfValorJuros.setVisible(false);
		tfValorDiario.setVisible(false);
		// tfLucroMesFuncionario.setVisible(false);

		// tfRota.setVisible(false);

		boolean controlClienteIdNome = false;
		boolean controlVendedorIdNome = false;
		boolean controlCobradorIdNome = false;

		double valorDiario = 0;
		double valorJuros = 0;
		double lucro = 0;
		double lucroMesFuncionario = 0;
		double LucroMesCobrador = 0;
		double LucroMesVendedor = 0;
		String rota = "";

		double valor_pedido = 0;
		String valor = "";
		char aux;
		if (!tfValorPedido.getText().equals("")) {

			String testeReplace = tfValorPedido.getText();
			/*
			 * for (int i = 0; i < testeReplace.length(); i++) { if
			 * (testeReplace.charAt(i) == '0' || testeReplace.charAt(i) == '1'
			 * || testeReplace.charAt(i) == '2' || testeReplace.charAt(i) == '3'
			 * || testeReplace.charAt(i) == '4' || testeReplace.charAt(i) == '5'
			 * || testeReplace.charAt(i) == '6' || testeReplace.charAt(i) == '7'
			 * || testeReplace.charAt(i) == '8' || testeReplace.charAt(i) ==
			 * '9'){
			 * 
			 * valor = valor + testeReplace.charAt(i); }else{ valor = valor +
			 * ""; } }
			 */
			valor_pedido = Double.parseDouble(testeReplace.replace(",", ".").trim());
		}
		System.out.println(valor_pedido + "aqui");

		double porForaCobr = 0;
		if (!tfPorfora.getText().equals("")) {
			porForaCobr = Double.parseDouble(tfPorfora.getText().replace(",", ".").trim());
		}

		LocalDate dlocal = null;
		dlocal = dCobranca.getValue();

		// rota = cRota.getValue();

		LocalDate dDataOpera = null;
		dDataOpera = dDataOperacao.getValue();

		Date localTime;
		Date LocalOpera;

		if (dlocal == null) {
			localTime = null;
		} else {

			localTime = Date.valueOf(dlocal);
		}

		if (dDataOpera == null) {
			LocalOpera = null;
		} else {

			LocalOpera = Date.valueOf(dDataOpera);
		}

		double porcentagemLucro = 0;
		if (!tfPorcentagemValorPedido.getText().equals("")) {
			porcentagemLucro = Double
					.parseDouble(tfPorcentagemValorPedido.getText().replace("%", " ").replace(",", ".").trim());

		}

		double extra = 0;
		if (!tfValorExtraCobrador.getText().equals("")) {
			extra = Double.parseDouble(tfValorExtraCobrador.getText().replace(",", ".").trim());
		}

		int porcentagemLucroFuncionario = 0;
		if (!tfPorcentagemLucroFuncionario.getText().equals("")) {
			porcentagemLucroFuncionario = Integer
					.parseInt(tfPorcentagemLucroFuncionario.getText().replace("%", " ").trim());
		}

		int porcentagemEntregador = 0;
		if (!tfPorcentagemEntregador.getText().equals("")) {
			porcentagemEntregador = Integer.parseInt(tfPorcentagemEntregador.getText().replace("%", " ").trim());
		}

		int parcelas = 0;
		if (!tfParcelas.getText().equals("")) {
			parcelas = Integer.parseInt(tfParcelas.getText().trim());
		}

		String tipoPagamento = null;
		tipoPagamento = cTipoDePagamento.getValue();

		Integer parcelaMulta = 0;
		parcelaMulta = cParcelaMulta.getValue();

		double ValorCobradorDiario = 0;
		if (cValorCobradorDiario.getValue() != null) {
			ValorCobradorDiario = cValorCobradorDiario.getValue();
		}

		double ValorCobradorMulta = 0;
		if (cValorCobradorMulta.getValue() != null) {
			ValorCobradorMulta = cValorCobradorMulta.getValue();
		}

		/*
		 * String vendedoIdNome = null; vendedoIdNome = cVendedor.getValue();
		 * String vendedoIdNomeConcatenado = "";
		 * 
		 * 
		 * if(vendedoIdNome != null){ for (int i = 0; i <
		 * vendedoIdNome.length(); i++) { if(vendedoIdNome.charAt(i) == ':'){
		 * controlVendedorIdNome = true; }else if(controlVendedorIdNome == true
		 * && vendedoIdNome.charAt(i) != ':' && vendedoIdNome.charAt(i) != ' ')
		 * { vendedoIdNomeConcatenado = vendedoIdNomeConcatenado +
		 * vendedoIdNome.charAt(i); } } }
		 */

		///String cobradorIdNome = null;
		//cobradorIdNome = cCobrador.getValue();
		//String cobradorIdNomeConcatenado = "";

		/*
		if (cobradorIdNome != null) {
			for (int i = 0; i < cobradorIdNome.length(); i++) {
				if (cobradorIdNome.charAt(i) == ':') {
					controlCobradorIdNome = true;
				} else if (controlCobradorIdNome == true && cobradorIdNome.charAt(i) != ':'
						&& cobradorIdNome.charAt(i) != ' ') {
					cobradorIdNomeConcatenado = cobradorIdNomeConcatenado + cobradorIdNome.charAt(i);
				}
			}

		}*/

		String clienteIdNome = null;
		clienteIdNome = cCliente.getValue();
		String clienteNomeConcatenado = "";

		if (clienteIdNome != null) {
			for (int i = 0; i < clienteIdNome.length(); i++) {
				if (clienteIdNome.charAt(i) == ':') {
					controlClienteIdNome = true;
				} else if (controlClienteIdNome == true && clienteIdNome.charAt(i) != ':'
						&& clienteIdNome.charAt(i) != ' ') {
					clienteNomeConcatenado = clienteNomeConcatenado + clienteIdNome.charAt(i);
				}
			}

		}

		boolean check1 = isValidObrigatorios(cCobrador.getValue().getNome(), valor_pedido, parcelas, tipoPagamento, parcelaMulta,
				 clienteIdNome, localTime);

		// Math.round(10.5)

		if (check1 == true) {
			// campo valor extra multa'
			lucro = Math.ceil((valor_pedido * porcentagemLucro) / 100) + valor_pedido;
			valorDiario = (Math.ceil((((valor_pedido * porcentagemLucro) / 100) + valor_pedido) / parcelas)) + extra
					+ ValorCobradorDiario + porForaCobr;
			// System.out.println((Math.ceil ((((valor_pedido *
			// porcentagemLucro) / 100) +valor_pedido )/ parcelas) +"ta aqui"));
			// valorJuros = (Math.ceil (valorDiario/parcelaMulta)) +1 ;
			valorJuros = (Math.ceil(((((((valor_pedido * porcentagemLucro) / 100) + valor_pedido) / parcelas))
					+ porForaCobr + extra + ValorCobradorDiario) / parcelaMulta)) + ValorCobradorMulta;
			lucroMesFuncionario = Math.ceil(ValorCobradorDiario + ((valor_pedido * porcentagemLucroFuncionario) / 100));
			LucroMesCobrador = ValorCobradorDiario;
			LucroMesVendedor = (Math.ceil((valor_pedido * porcentagemLucroFuncionario) / 100));

			// lLucro.setVisible(true);
			lValorJuros.setVisible(true);
			lValorDiario.setVisible(true);
			// lLucroMesFuncionario.setVisible(true);

			tfValorJuros.setVisible(true);
			tfValorDiario.setVisible(true);

			// tfLucro.setText(String.valueOf(lucro));
			tfValorJuros.setText(String.valueOf(valorJuros));
			tfValorDiario.setText(String.valueOf(valorDiario));
			// tfLucroMesFuncionario.setText(String.valueOf(LucroMesVendedor));

		}

		// System.out.println(extra + " " + ValorCobradorDiario + " " +
		// porForaCobr + " " + valor_pedido * porcentagemLucro + " " +
		// " "+ valor_pedido);

	}

	private boolean isValidObrigatorios(String cobradorIdNome, double valor_pedido, int parcelas, String tipoPagamento,
			Integer parcelaMulta, String clienteIdNome, Date localTime) {

		if (cobradorIdNome != null && valor_pedido != 0 && parcelas != 0 && tipoPagamento != "" && parcelaMulta != 0
				 && clienteIdNome != null && localTime != null) {

			return true;
		}

		lObrigatorio.setVisible(true);
		return false;
	}

	public void eventoBtnLimpar(ActionEvent event) {

		lObrigatorio.setVisible(false);
		lLucro.setVisible(false);
		lValorJuros.setVisible(false);
		lValorDiario.setVisible(false);
		lLucroMesFuncionario.setVisible(false);

		tfLucro.setVisible(false);
		tfValorJuros.setVisible(false);
		tfValorDiario.setVisible(false);
		tfLucroMesFuncionario.setVisible(false);

		tfValorPedido.setText("");
		dCobranca.setValue(null);
		tfPorcentagemValorPedido.setText("");

		tfPorcentagemLucroFuncionario.setText("4");
		tfPorcentagemEntregador.setText("");
		tfValorExtraCobrador.setText("");
		// tfPorcentagemEntregador.setText("1");
		// tfValorExtraCobrador.setText("1.0");
		tfParcelas.setText("");
		cTipoDePagamento.setValue(null);
		// cParcelaMulta.setValue(null);
		// cValorCobradorDiario.setValue(null);
		// cValorCobradorMulta.setValue(null);
		cVendedor.setValue(null);
		cCobrador.setValue(null);
		cCliente.setValue(null);
		dDataOperacao.setValue(null);
		cEntregador.setValue(null);

		/// cRota.setValue(null);

		cTipoDePagamento.setValue("");
		cParcelaMulta.setValue(4);
		cValorCobradorDiario.setValue(0.0);
		// cTipoDePagamento.setValue("Segunda a Sexta");
		// cParcelaMulta.setValue(3);
		// cValorCobradorDiario.setValue(1.0);
		cValorCobradorMulta.setValue(0.0);
		tfPorfora.setText("");
	}

	public void eventoBtnCadastrar(ActionEvent event)
			throws RepositoryException, ParseException, ClienteNaoEncontradoException {

		lObrigatorio.setVisible(false);

		boolean controlVendedorIdNome = false;
		boolean controlCobradorIdNome = false;
		boolean controlClienteIdNome = false;
		boolean controlEntregadorIdNome = false;

		// String rota = "";

		double valorDiario = 0;
		double valorJuros = 0;
		double lucro = 0;
		double lucroMesFuncionario = 0;

		double valor_pedido = 0;
		String valor = "";
		char aux;
		if (!tfValorPedido.getText().equals("")) {

			String testeReplace = tfValorPedido.getText();
			/*
			 * for (int i = 0; i < testeReplace.length(); i++) { if
			 * (testeReplace.charAt(i) == '0' || testeReplace.charAt(i) == '1'
			 * || testeReplace.charAt(i) == '2' || testeReplace.charAt(i) == '3'
			 * || testeReplace.charAt(i) == '4' || testeReplace.charAt(i) == '5'
			 * || testeReplace.charAt(i) == '6' || testeReplace.charAt(i) == '7'
			 * || testeReplace.charAt(i) == '8' || testeReplace.charAt(i) ==
			 * '9'){
			 * 
			 * valor = valor + testeReplace.charAt(i); }else{ valor = valor +
			 * ""; } }
			 */
			valor_pedido = Double.parseDouble(testeReplace.replace(",", ".").trim());
		}

		LocalDate dlocal = null;
		dlocal = dCobranca.getValue();

		LocalDate dDataOpera = null;
		dDataOpera = dDataOperacao.getValue();

		Date localTime;
		Date LocalOpera;

		// rota = cRota.getValue();

		if (dlocal == null) {
			localTime = null;
		} else {

			localTime = Date.valueOf(dlocal);
		}

		if (dDataOpera == null) {
			LocalOpera = null;
		} else {

			LocalOpera = Date.valueOf(dDataOpera);
		}

		double porcentagemLucro = 0;
		if (!tfPorcentagemValorPedido.getText().equals("")) {
			porcentagemLucro = Double
					.parseDouble(tfPorcentagemValorPedido.getText().replace("%", " ").replace(",", ".").trim());

		}

		double porForaCobr = 0;
		if (!tfPorfora.getText().equals("")) {
			porForaCobr = Double.parseDouble(tfPorfora.getText().replace(",", ".").trim());
		}

		double extra = 0;
		if (!tfValorExtraCobrador.getText().equals("")) {
			extra = Double.parseDouble(tfValorExtraCobrador.getText().replace(",", ".").trim());
		}

		int porcentagemLucroFuncionario = 0;
		if (!tfPorcentagemLucroFuncionario.getText().equals("")) {
			porcentagemLucroFuncionario = Integer
					.parseInt(tfPorcentagemLucroFuncionario.getText().replace("%", " ").replace(",", ".").trim());
		}

		int parcelas = 0;
		if (!tfParcelas.getText().equals("")) {
			parcelas = Integer.parseInt(tfParcelas.getText().trim());
		}

		int porcentagemEntregador = 0;
		if (!tfPorcentagemEntregador.getText().equals("")) {
			porcentagemEntregador = Integer
					.parseInt(tfPorcentagemEntregador.getText().replace("%", " ").replace(",", ".").trim());
		}

		String tipoPagamento = null;
		tipoPagamento = cTipoDePagamento.getValue();

		Integer parcelaMulta = 0;
		parcelaMulta = cParcelaMulta.getValue();

		double ValorCobradorDiario = 0;
		if (cValorCobradorDiario.getValue() != null) {
			ValorCobradorDiario = cValorCobradorDiario.getValue();
		}

		double ValorCobradorMulta = 0;
		if (cValorCobradorMulta.getValue() != null) {
			ValorCobradorMulta = cValorCobradorMulta.getValue();
		}

		/*
		 * String vendedoIdNome = null; vendedoIdNome = cVendedor.getValue();
		 * String vendedoIdNomeConcatenado = "";
		 */

		String intervalo = "";
		if (tfPagamentoEs.getText() != null) {
			intervalo = tfPagamentoEs.getText();
		}

		int intervaloReal = 0;
		int flag = 0;

		if (intervalo.equals("")) {

		} else if (tipoPagamento.equals("Sequencial")) {
			if (!intervalo.equals("")) {
				intervaloReal = Integer.parseInt(intervalo);
				flag = 2;
			} else {

			}
			// tipoPagamento = "Sequencial";
		}

		if (tipoPagamento.equals("Mensal")) {
			flag = 1;
			if (!intervalo.equals("")) {
				intervaloReal = Integer.parseInt(intervalo);
			} else {

			}

		}

		/*
		 * if(vendedoIdNome != null){ for (int i = 0; i <
		 * vendedoIdNome.length(); i++) { if(vendedoIdNome.charAt(i) == ':'){
		 * controlVendedorIdNome = true; }else if(controlVendedorIdNome == true
		 * && vendedoIdNome.charAt(i) != ':' && vendedoIdNome.charAt(i) != ' ')
		 * { vendedoIdNomeConcatenado = vendedoIdNomeConcatenado +
		 * vendedoIdNome.charAt(i); } } }
		 */

		//String cobradorIdNome = null;
		// = cCobrador.getValue();
		//String cobradorIdNomeConcatenado = "";

		/*
		if (cobradorIdNome != null) {
			for (int i = 0; i < cobradorIdNome.length(); i++) {
				if (cobradorIdNome.charAt(i) == ':') {
					controlCobradorIdNome = true;
				} else if (controlCobradorIdNome == true && cobradorIdNome.charAt(i) != ':'
						&& cobradorIdNome.charAt(i) != ' ') {
					cobradorIdNomeConcatenado = cobradorIdNomeConcatenado + cobradorIdNome.charAt(i);
				}
			}

		}*/

		String clienteIdNome = null;
		clienteIdNome = cCliente.getValue();
		String clienteNomeConcatenado = "";

		if (clienteIdNome != null) {
			for (int i = 0; i < clienteIdNome.length(); i++) {
				if (clienteIdNome.charAt(i) == ':') {
					controlClienteIdNome = true;
				} else if (controlClienteIdNome == true && clienteIdNome.charAt(i) != ':'
						&& clienteIdNome.charAt(i) != ' ') {
					clienteNomeConcatenado = clienteNomeConcatenado + clienteIdNome.charAt(i);
				}
			}

		}

		//String entregadorId = null;
		//entregadorId = cEntregador.getValue();
		//String EntregadorNomeConcatenado = "";
/*
		if (entregadorId != null) {
			for (int i = 0; i < entregadorId.length(); i++) {
				if (entregadorId.charAt(i) == ':') {
					controlEntregadorIdNome = true;
				} else if (controlEntregadorIdNome == true && entregadorId.charAt(i) != ':'
						&& entregadorId.charAt(i) != ' ') {
					EntregadorNomeConcatenado = EntregadorNomeConcatenado + entregadorId.charAt(i);
				}
			}
		}
*/
		boolean check1 = isValidObrigatorios(cCobrador.getValue().getNome(), valor_pedido, parcelas, tipoPagamento, parcelaMulta
		, clienteIdNome, localTime);

		if (check1 == true) {
			ArrayList<Cliente> clientes = new ArrayList<>();

			//int idCobrador = 0;
			int idVendedor = 0;
			int idCliente = 0;
			//int idEntregador = 0;
			boolean condOperacoaUnicaDia = false;

			//idCobrador = Integer.parseInt(cobradorIdNomeConcatenado);
			// idVendedor = Integer.parseInt(vendedoIdNomeConcatenado);
			idCliente = Integer.parseInt(clienteNomeConcatenado);
			//idEntregador = Integer.parseInt(EntregadorNomeConcatenado);

			clientes = fachada.getClientesHistorico(idCliente);

			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			if (clientes.size() != 0) {
				for (int i = 0; i < clientes.size(); i++) {
					System.out.println(sdf2.format(LocalOpera) + " e a outra data "
							+ (sdf2.format(clientes.get(i).getData_dia())));
					if (sdf2.format(LocalOpera).equals(sdf2.format(clientes.get(i).getData_dia()))) {
						condOperacoaUnicaDia = true;
					}
				}
			}

			if (condOperacoaUnicaDia == false) {

				Cliente c = fachada.getCliente(idCliente);
				
				
				
				idVendedor = c.getId_vendedor();
				System.out.println(idVendedor);
				// System.out.println("primeiro vend"+ idVendedor);
				if (idVendedor != 0) {

					// lucro = Math.ceil ((valor_pedido * porcentagemLucro) /
					// 100) + valor_pedido ;
					// valorDiario = (Math.ceil ((((valor_pedido *
					// porcentagemLucro) / 100) +valor_pedido )/ parcelas)) +
					// extra + ValorCobradorDiario + porForaCobr;
					valorDiario = (Math.ceil((((valor_pedido * porcentagemLucro) / 100) + valor_pedido) / parcelas))
							+ extra + ValorCobradorDiario + porForaCobr;
					lucro = Math.ceil(parcelas * valorDiario);
					valorJuros = (Math.ceil(((((((valor_pedido * porcentagemLucro) / 100) + valor_pedido) / parcelas))
							+ porForaCobr + extra + ValorCobradorDiario) / parcelaMulta)) + ValorCobradorMulta;
					// System.out.println((Math.ceil ((((valor_pedido *
					// porcentagemLucro) / 100) +valor_pedido )/ parcelas) +"ta
					// aqui"));
					// valorJuros = (Math.ceil (valorDiario/parcelaMulta)) +1 ;
					// valorJuros = (Math.ceil((((((valor_pedido *
					// porcentagemLucro) / 100) +valor_pedido )/
					// parcelas))/parcelaMulta)) + ValorCobradorMulta;
					// lucroMesFuncionario = Math.ceil (ValorCobradorDiario +
					// ((valor_pedido * porcentagemLucroFuncionario)/100));
					// LucroMesCobrador = ValorCobradorDiario;
					lucroMesFuncionario = ((valor_pedido * porcentagemLucroFuncionario) / 100);

					System.out.println(extra + " " + ValorCobradorDiario + " " + porForaCobr + " "
							+ valor_pedido * porcentagemLucro + " " + " " + valor_pedido);

					if (tfValorJuros.getText().equals("") || tfValorJuros.getText() == null) {
						;
					} else {
						valorJuros = Double.parseDouble(tfValorJuros.getText().replace(",", ".").trim());
					}
					SimpleDateFormat sdf12 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					DateTime date = DateTime.parse(sdf12.format(localTime),
							DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
					org.joda.time.DateTime jodal1 = date;

					Date date2 = localTime;
					org.joda.time.DateTime joda2 = new org.joda.time.DateTime(jodal1.plusDays(2));

					String auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");

					Date local22 = Date.valueOf(auxl2);

					if (flag != 0) {

						if (flag == 1) {

							if (intervaloReal != 0) {
								joda2 = new org.joda.time.DateTime(jodal1.plusMonths(intervaloReal));
							} else {
								joda2 = new org.joda.time.DateTime(jodal1.plusMonths(1));
							}
							// System.out.println(joda2 + "mensal pos joda");

							auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");

							if (auxl2 == null) {
								java.util.Date dataAtual = new java.util.Date();
								java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
								// java.sql.Date dataSQ1L = new
								// java.sql.Date(dataAtual.getTime());
								local22 = dataSQL;
							} else {

								local22 = Date.valueOf(auxl2);
								// System.out.println(dataNascimento);
							}

							date2 = local22;
							// System.out.println(local2 + " mensal final");

						} else {

							if (intervaloReal != 0) {
								joda2 = new org.joda.time.DateTime(jodal1.plusDays(intervaloReal));
							} else {
								joda2 = new org.joda.time.DateTime(jodal1.plusDays(1));
							}
							// System.out.println(joda2 + "sq pos joda");
							auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");

							if (auxl2 == null) {
								java.util.Date dataAtual = new java.util.Date();
								java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
								// java.sql.Date dataSQ1L = new
								// java.sql.Date(dataAtual.getTime());
								local22 = dataSQL;
							} else {

								local22 = Date.valueOf(auxl2);
								// System.out.println(dataNascimento);
							}

							date2 = local22;
							// System.out.println(local2 + " sq final");
						}

					} else {
						date2 = null;
					}

					// System.out.println("id vend "+ idVendedor);
					Operacao operacao = new Operacao(parcelas, valorJuros, valor_pedido, valorDiario, parcelaMulta,
							tipoPagamento, porcentagemLucroFuncionario, ValorCobradorDiario, extra, cCobrador.getValue().getId_funcionario(),
							idVendedor, lucro, lucroMesFuncionario, porcentagemLucro, cEntregador.getValue().getId_funcionario(), LocalOpera,
							porcentagemEntregador, porForaCobr, ValorCobradorMulta, flag, intervaloReal);
					fachada.inserteOperacao(operacao);

					// System.out.println("Operacao cadastro" +
					// operacao.getParcelas());

					int idOperacao = fachada.OperacaoId();

					//String func = fachada.getFuncionarioNome(cCobrador.getValue().getId_funcionario());

					// System.out.println(idOperacao);
					// java.util.Date dataAtual = new java.util.Date();
					// usando o Date de sql
					// passando como parametro a data atual
					// java.sql.Date dataSQL = new
					// java.sql.Date(dataAtual.getTime());

					SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date dataAtual = new java.util.Date();
					String dt = sdf1.format(dataAtual);

					java.sql.Date dataIni = new java.sql.Date(sdf1.parse(dt).getTime());

					// System.out.println(dataIni);

					Realiza realiza = new Realiza(idOperacao, idCliente, pessoa.getId(), dataIni);

					fachada.insertRealiza(realiza);

					OperacaoCliente operacaoCliente = new OperacaoCliente(LocalOpera, valor_pedido, 0, idCliente,
							idOperacao, "Em andamento");

					fachada.insertOperacaoCliente(operacaoCliente);

					int valor_pedido_int = (int) valor_pedido;

					Cliente cli = fachada.getCliente(idCliente);
					// System.out.println("aquiiiiiiiiii");
					// System.out.println(cli.getApelido()+"
					// "+sdf1.format(localTime)+" "+ parcelas+ "x"+ valorDiario+
					// " "+ "M." + valorJuros);
					String nomeFull = cli.getApelido() + " " + sdf1.format(LocalOpera) + " " + valor_pedido_int + " "
							+ parcelas + "x" + (int) valorDiario + " " + "M." + (int) valorJuros;

					Status status = new Status(parcelas, 0, idOperacao, tipoPagamento, null, localTime, 0, false, 0, 0,
							0, 1, nomeFull, 0, 1, cCobrador.getValue().getNome(), date2);

					fachada.insertStatus(status);

					int idRow = fachada.getIdStatus(idOperacao);

					Log log = new Log(idRow, idOperacao, pessoa.getId(), dataIni);

					fachada.insert(log);

					ControleTelaConfirmarOperacao confirm = new ControleTelaConfirmarOperacao();
					
					eventoBtnLimpar(event);

					// eventoBtnLimpar(event);
					try {
						// Abre a tela de Cadastro de Grupos

						// telaInicial.start(new Stage());

						confirm.start(new Stage());

					} catch (Exception e) {
						e.printStackTrace();

					}
				} else {
					System.out.println("error");
					ControleTelaConfirmarOperacaoNotErro error = new ControleTelaConfirmarOperacaoNotErro();
					try {
						error.start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();

					}
				}

			} else {
				System.out.println("error2");
				ControleTelaConfirmarOperacaoNot confirm = new ControleTelaConfirmarOperacaoNot();

				// eventoBtnLimpar(event);
				try {
					// Abre a tela de Cadastro de Grupos

					// telaInicial.start(new Stage());

					confirm.start(new Stage());

				} catch (Exception e) {
					e.printStackTrace();

				}
			}

		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		Map<String, Integer> example = new HashMap<String, Integer>();

		new AutoCompleteComboBoxListener(cCobrador);
		new AutoCompleteComboBoxListener(cCliente);
		new AutoCompleteComboBoxListener(cEntregador);
		

		tfValorExtraCobrador.setText("");
		// tfValorExtraCobrador.setValue("1.0");

		cTipoDePagamento.getItems().addAll("Segunda a Sexta", "Segunda a Sabado", "Segunda a Domingo", "Mensal",
				"Sequencial");
		cTipoDePagamento.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				// System.out.println(ov);
				// System.out.println(t);
				// System.out.println(t1);
			}
		});

		cTipoDePagamento.setValue("");
		// cTipoDePagamento.setValue("Segunda a Sexta");

		cParcelaMulta.getItems().addAll(0, 1, 2, 3, 4, 5);
		cParcelaMulta.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue ov, Integer t, Integer t1) {
				// System.out.println(ov);
				// System.out.println(t);
				// System.out.println(t1);
			}
		});

		cParcelaMulta.setValue(4);
		// cParcelaMulta.setValue(3);

		cValorCobradorDiario.getItems().addAll(0.0, 1.0, 2.0, 3.0);
		cValorCobradorDiario.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue ov, Double t, Double t1) {
				// System.out.println(ov);
				// System.out.println(t);
				// System.out.println(t1);
			}
		});

		cValorCobradorDiario.setValue(0.0);
		// cValorCobradorDiario.setValue(1.0);

		cValorCobradorMulta.getItems().addAll(0.0, 1.0, 2.0, 3.0);
		cValorCobradorMulta.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue ov, Double t, Double t1) {
				// System.out.println(ov);
				// System.out.println(t);
				// System.out.println(t1);
			}
		});

		cValorCobradorMulta.setValue(0.0);

		/*
		 * ArrayList<Funcionario> funcionariosVendedores = new ArrayList();
		 * ArrayList<String> funcionarioVendedorNome = new ArrayList(); Pessoa
		 * funcionarioVendedor = null;
		 */

		ArrayList<Funcionario> funcionariosCobrador = new ArrayList();
		//ArrayList<String> funcionarioCobradorNome = new ArrayList();
		//Pessoa funcionarioCobrador = null;

		ArrayList<Funcionario> funcionariosEntregador = new ArrayList();
		//ArrayList<String> funcionarioEntregadorNome = new ArrayList();
		//Pessoa funcionarioEntregador = null;

		ArrayList<Cliente> clientes = new ArrayList();
		ArrayList<String> ClienteNomeId = new ArrayList();
		Pessoa ClienteNome = null;

		// ArrayList<Rota> rotas = new ArrayList();
		// ArrayList<String> RotaNome = new ArrayList();

		EletroJam fachada = EletroJam.getInstance();

		/*
		 * try { rotas = fachada.getRotas(); } catch (RepositoryException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * for (int i = 0; i < rotas.size(); i++) {
		 * RotaNome.add(rotas.get(i).getNome()); }
		 */

		try {
			clientes = fachada.getClientes();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < clientes.size(); i++) {
			/*
			 * try { ClienteNome =
			 * fachada.getPessoaId(clientes.get(i).getId_cliente()); } catch
			 * (RepositoryException | PessoaNaoEncontradaException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

			ClienteNomeId.add(clientes.get(i).getApelido() + " / ID: " + clientes.get(i).getId_cliente());

		}
		
		try {
			funcionariosCobrador = fachada.getFuncionariosCobradores();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			funcionariosEntregador = fachada.getFuncionariosEntregadores();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		cCobrador.getItems().addAll(funcionariosCobrador);
		cCobrador.getSelectionModel().getSelectedIndex();
		cCobrador.setCellFactory(new Callback<ListView<Funcionario>,ListCell<Funcionario>>(){
			 
	            @Override
	            public ListCell<Funcionario> call(ListView<Funcionario> p) {
	                 
	                final ListCell<Funcionario> cell = new ListCell<Funcionario>(){
	 
	                    @Override
	                    protected void updateItem(Funcionario t, boolean bln) {
	                        super.updateItem(t, bln);
	                         
	                        if(t != null){
	                            setText(t.getNome() + " ");
	                        }else{
	                            setText(null);
	                        }
	                    }
	  
	                };
	                 
	                return cell;
	            } 
		   });
		
		
		System.out.println(funcionariosEntregador.size());
		
		cEntregador.getItems().addAll(funcionariosEntregador);
		cEntregador.getSelectionModel().getSelectedIndex();
		cEntregador.setCellFactory(new Callback<ListView<Funcionario>,ListCell<Funcionario>>(){
			 
	            @Override
	            public ListCell<Funcionario> call(ListView<Funcionario> p) {
	                 
	                final ListCell<Funcionario> cell = new ListCell<Funcionario>(){
	 
	                    @Override
	                    protected void updateItem(Funcionario t, boolean bln) {
	                        super.updateItem(t, bln);
	                         
	                        if(t != null){
	                            setText(t.getNome() + " ");
	                        }else{
	                            setText(null);
	                        }
	                    }
	  
	                };
	                 
	                return cell;
	            } 
		   });

		
		cVendedor.setVisible(false);

		cCliente.getItems().addAll(ClienteNomeId);
		cCliente.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				System.out.println(ov);
				System.out.println(t);
				System.out.println(t1);
			}
		});

		/*
		 * cRota.getItems().addAll(RotaNome);
		 * cRota.valueProperty().addListener(new ChangeListener<String>() {
		 * 
		 * @Override public void changed(ObservableValue ov, String t, String
		 * t1) { System.out.println(ov); System.out.println(t);
		 * System.out.println(t1); } });
		 */

		cRota.setVisible(false);
	}

	public void start(Stage palcoTelaOperacaoInicial, Pessoa pessoa) throws Exception {
		palcoTelaOperacao = palcoTelaOperacaoInicial;
		this.pessoa = pessoa;

		try {

			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaCadastroOperacao.fxml"));

			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// origemTela.getStylesheets().add("origemTela.css");

			// cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaOperacaoInicial.setScene(cena);

			palcoTelaOperacaoInicial.setTitle("Cadastro de Operação");

			palcoTelaOperacaoInicial.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			palcoTelaOperacaoInicial.setResizable(false);
			/*
			 * Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 * 
			 * // simple displays ImageView the image as is ImageView iv1 = new
			 * ImageView(); iv1.setImage(image);
			 */

			// imageView = new ImageView(image);

			palcoTelaOperacaoInicial.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaInicial.setCodOperacao(0);
				}
			});
			palcoTelaOperacaoInicial.close();

			// Mostrar a janela/tela
			palcoTelaOperacaoInicial.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
