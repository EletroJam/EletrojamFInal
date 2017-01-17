
package controleView;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import conexao.RepositoryException;
import exceptions.PessoaNaoEncontradaException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import negocio.Funcionario;
import negocio.Log;
import negocio.Operacao;
import negocio.OperacaoCliente;
import negocio.Pessoa;
import negocio.Status;
import net.sf.jasperreports.engine.JRException;
import util.AutoCompleteComboBoxListener;
import util.Main.Typ;

public class ControleTelaInicial extends Application implements Initializable {

	EletroJam fachada = EletroJam.getInstance();

	public static Stage palcoTelaInicial = null;

	private ObservableList<Funcionario> listaTudo = FXCollections.observableArrayList();

	public static Pessoa pessoa;

	public static int getCodPessoa() {
		return codPessoa;
	}

	public static void setCodPessoa(int codPessoa) {
		ControleTelaInicial.codPessoa = codPessoa;

	}

	public static int getCodEditFuncionario() {
		return codEditFuncionario;
	}

	public static void setCodEditFuncionario(int codEditFuncionario) {
		ControleTelaInicial.codEditFuncionario = codEditFuncionario;
	}

	public static int codPessoa = 0;
	public static int codFuncio = 0;
	public static int codOperacao = 0;
	public static int codRota = 0;
	public static int codGerar = 0;
	public static int codGerarStatus = 0;
	public static int codEditCliente = 0;
	public static int codEditFuncionario = 0;
	public static int codHistoricoCliente = 0;
	public static int codLucroCobrador = 0;
	public static int codLucroEntregador = 0;
	public static int codLucroVendedor = 0;
	public static int codPercaFutura = 0;
	public static int codRelatorioGeral = 0;
	public static int codRelatorioClienteResponsavelGeral = 0;
	public static int codRelatorioClienteResponsavelGeralFinal = 0;
	
	public static int codCodReceber = 0;

	public static int codRelatorioEmpDiario = 0;
	public static int codRelatorioClienteResp = 0;
	public static int codRelatorioResponsavelEspec = 0;

	public static int getCodLucroVendedor() {
		return codLucroVendedor;
	}

	public static void setCodLucroVendedor(int codLucroVendedor) {
		ControleTelaInicial.codLucroVendedor = codLucroVendedor;
	}

	public static int getCodLucroEntregador() {
		return codLucroEntregador;
	}

	public static void setCodLucroEntregador(int conLucroEntregador) {
		ControleTelaInicial.codLucroEntregador = conLucroEntregador;
	}

	public static int getCodLucroCobrador() {
		return codLucroCobrador;
	}

	public static void setCodLucroCobrador(int codLucroCobrador) {
		ControleTelaInicial.codLucroCobrador = codLucroCobrador;
	}

	public static int getCodHistoricoCliente() {
		return codHistoricoCliente;
	}

	public static void setCodHistoricoCliente(int codHistoricoCliente) {
		ControleTelaInicial.codHistoricoCliente = codHistoricoCliente;
	}

	public static int getCodGerarStatus() {
		return codGerarStatus;
	}

	public static void setCodGerarStatus(int codGerarStatus) {
		ControleTelaInicial.codGerarStatus = codGerarStatus;
	}

	public static int getCodRota() {
		return codRota;
	}

	public static void setCodRota(int codRota) {
		ControleTelaInicial.codRota = codRota;
	}

	public static int getCodFuncio() {
		return codFuncio;
	}

	public static void setCodFuncio(int codFuncio) {
		ControleTelaInicial.codFuncio = codFuncio;
	}

	public static int getCodOperacao() {
		return codOperacao;
	}

	public static void setCodOperacao(int codOperacao) {
		ControleTelaInicial.codOperacao = codOperacao;
	}

	private final ObservableList<Typ> typData = FXCollections.observableArrayList(new Typ(null), new Typ("PARCELA"),
			new Typ("MULTA"),
			// new Typ("Livre"),
			new Typ("FINALIZAR"), new Typ("NÃO PAGOU"), new Typ("PARCELA + MULTA"), new Typ("PARCELAS"),
			new Typ("MULTAS"), new Typ("PARCELAS e MULTAS"), new Typ(""), new Typ("FECHAR Op. D."),
			new Typ("PERDOAR DIVIDA"));

	private final ObservableList<Status> data = FXCollections.observableArrayList();
	/*
	 * = FXCollections.observableArrayList( new Status("", typData.get(0), null)
	 * )
	 */

	ArrayList<Status> status = new ArrayList();

	@FXML
	DatePicker dData;

	@FXML
	ComboBox<String> cCobrador;

	@FXML
	ComboBox<String> cRota;

	@FXML
	Button bGerar;

	/*
	 * @FXML Button btnCadastrarCliente;
	 * 
	 * @FXML Button btnCadastrarFuncionario;
	 * 
	 * @FXML Button btnCadastrarOperação;
	 * 
	 * @FXML Button btnListagem;
	 * 
	 * @FXML Button btnGerarRelatorio;
	 * 
	 * @FXML Button btnStatusDiario;
	 */
	@FXML
	DatePicker datePick;

	@FXML
	Label lFuncionarioNome;

	@FXML
	TableView<Status> tbStatus;

	@FXML
	DatePicker dtData;

	@FXML
	Button btConsultar;

	@FXML
	MenuBar barMenu;

	@FXML
	Button btFerias;

	@FXML
	MenuBar MMenu;

	@FXML
	Menu MCadastrar;

	@FXML
	Menu MRelatorio;

	@FXML
	Menu MListar;

	@FXML
	Menu MGerar;

	@FXML
	MenuItem MCliente;

	@FXML
	MenuItem MFuncionario;

	@FXML
	MenuItem MOperacao;

	@FXML
	MenuItem MRota;

	@FXML
	MenuItem MStatus;

	@FXML
	MenuItem MCobranca;

	@FXML
	Menu MEdita;

	@FXML
	MenuItem MECliente;

	@FXML
	MenuItem MVendedor;

	@FXML
	MenuItem MGeral;

	@FXML
	MenuItem MLucroPercaFutura;
	
	@FXML
	MenuItem MReceber;

	@FXML
	MenuItem MHistoricoCliente;

	@FXML
	MenuItem MLucroCobrador;

	@FXML
	MenuItem MLucroEntregador;

	@FXML
	MenuItem MEFuncionario;

	@FXML
	MenuItem MEmprestimoDiario;

	@FXML
	MenuItem MClienteResponsavel;

	@FXML
	MenuItem MRelatorioResponsavelEspec;

	@FXML
	MenuItem MRelatorioResponsavelGeralRua;

	@FXML
	ComboBox<Funcionario> ccCobrador;

	/*
	 * public void gerarRelatorio () throws ClassNotFoundException, SQLException
	 * {
	 * 
	 * String driver = "org.postgresql.Driver"; String user = "postgres"; String
	 * senha = "admin123"; String url =
	 * "jdbc:postgresql://localhost:5432/EletroJam";
	 * 
	 * Class.forName(driver); Connection con = null;
	 * 
	 * con = (Connection) DriverManager.getConnection(url, user, senha);
	 * 
	 * 
	 * 
	 * 
	 * //Connection con = (Connection) new Conect(); HashMap param = new
	 * HashMap(); JasperPrint jp; try { jp =
	 * JasperFillManager.fillReport("src/relatorios/teste.jasper", param, con);
	 * JasperViewer jw = new JasperViewer(jp); jw.setVisible(true); } catch
	 * (JRException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * 
	 * //JasperExportManager jo = new
	 * 
	 * }
	 */

	@FXML
	public void eventoMRelatorioEmprestimoDiario(ActionEvent event) {
		if (codRelatorioEmpDiario == 0) {
			codRelatorioEmpDiario = 1;

			try {
				new ControleTelaRelatorioEmprestimo().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioGeralCobrancaTodos(ActionEvent event) {
		if (codRelatorioClienteResponsavelGeralFinal == 0) {
			codRelatorioClienteResponsavelGeralFinal = 1;

			try {
				new ControleTelaGerarRelatorioGeralPagamento().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}
	
	@FXML
	public void eventoMReceber(ActionEvent event) {
		if (codCodReceber == 0) {
			codCodReceber = 1;

			try {
				new ControleTelaReceber().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioClienteResponsavel(ActionEvent event) {
		if (codRelatorioClienteResponsavelGeral == 0) {
			codRelatorioClienteResponsavelGeral = 1;

			try {
				new ControleTelaRelatorioResponsavelCliente().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioResponsavelEspec(ActionEvent event) {
		if (codRelatorioResponsavelEspec == 0) {
			codRelatorioResponsavelEspec = 1;

			try {
				new ControleTelaRelatorioVendedorBasico().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioPercaFutura(ActionEvent event) {
		if (codPercaFutura == 0) {
			codPercaFutura = 1;
			try {
				new ControleTelaPercasFutura().start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMClienteEditar(ActionEvent event) {
		if (codEditCliente == 0) {
			codEditCliente = 1;
			try {
				new ControleTelaClienteEd().start(new Stage(), pessoa);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioCobrancaCobrador(ActionEvent event) {
		if (codLucroCobrador == 0) {
			codLucroCobrador = 1;
			try {
				new ControleTelaLucroCobrador().start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioCobrancaEntregador(ActionEvent event) {
		if (codLucroEntregador == 0) {
			codLucroEntregador = 1;

			try {
				new ControleTelaLucroEntregador().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}

	}

	@FXML
	public void eventoMRelatorioVendedor(ActionEvent event) {
		if (codLucroVendedor == 0) {
			codLucroVendedor = 1;

			try {
				new ControleTelaLucroVendedor().start(new Stage());
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

		}
	}

	@FXML
	public void eventoMRelatorioGeral(ActionEvent event) {
		if (codRelatorioGeral == 0) {
			codRelatorioGeral = 1;
			try {
				new ControleTelaLucroCobradorGeral().start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}

	}

	@FXML
	public void eventoFuncionarioEditar(ActionEvent event) {
		if (codEditFuncionario == 0) {
			codEditFuncionario = 1;
			try {
				new ControleTelaFuncionarioEd().start(new Stage(), pessoa);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}
	}

	public static int getCodEditCliente() {
		return codEditCliente;
	}

	public static void setCodEditCliente(int codEditCliente) {
		ControleTelaInicial.codEditCliente = codEditCliente;
	}

	@FXML
	public void eventoMCadastrarCliente(ActionEvent event) {
		if (codPessoa == 0) {
			codPessoa = 1;
			// ControleTelaCadastroCliente telacadastroCliente = new
			// ControleTelaCadastroCliente();
			try {
				new ControleTelaCadastroCliente().start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}
	}

	@FXML
	public void eventoMCadastrarFuncionario(ActionEvent event) {
		if (codFuncio == 0) {
			codFuncio = 1;
			// ControleTelaCadastroFuncionario telacadastroFuncionario = new
			// ControleTelaCadastroFuncionario();
			try {
				new ControleTelaCadastroFuncionario().start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}
	}

	@FXML
	public void eventoMHistoricoCliente(ActionEvent event) {
		if (codHistoricoCliente == 0) {
			codHistoricoCliente = 1;
			ControleTelaHistoricoOperacao telaHistoricoCliente = new ControleTelaHistoricoOperacao();
			try {
				telaHistoricoCliente.start(new Stage(), pessoa);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
	}

	@FXML
	public void eventoMCadastrarOperacao(ActionEvent event) {
		if (codOperacao == 0) {
			codOperacao = 1;
			ControleTelaCadastroOperacao telacadastroOperacao = new ControleTelaCadastroOperacao();
			try {
				telacadastroOperacao.start(new Stage(), pessoa);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}
	}

	@FXML
	public void eventoMCadastrarRota(ActionEvent event) {
		if (codRota == 0) {
			codRota = 1;
			ControleTelaCadastroRota telacadastroRota = new ControleTelaCadastroRota();
			try {
				telacadastroRota.start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}
	}

	@FXML
	public void eventoMRelatorioStatus(ActionEvent event) {
		if (codGerarStatus == 0) {
			codGerarStatus = 1;
			ControleTelaGerarStatusDiario telaGerarCobranca = new ControleTelaGerarStatusDiario();
			try {
				telaGerarCobranca.start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}
	}

	@FXML
	public void eventoMGerarCobranca(ActionEvent event) {
		// System.out.println("try" + codGerar);
		if (codGerar == 0) {
			codGerar = 1;
			ControleTelaGerarCobranca teleStatusDiario = new ControleTelaGerarCobranca();
			try {
				teleStatusDiario.start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}

	}

	/*
	 * @FXML public void eventoBtnCadastrarCliente (ActionEvent event) {
	 * 
	 * //System.out.println(codPessoa); if (codPessoa == 0){ codPessoa = 1;
	 * //ControleTelaCadastroCliente telacadastroCliente = new
	 * ControleTelaCadastroCliente(); try { new
	 * ControleTelaCadastroCliente().start(new Stage());
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }else{
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @FXML public void eventoBtnCadastrarFuncionario (ActionEvent event){
	 * 
	 * if (codFuncio == 0){ codFuncio = 1; //ControleTelaCadastroFuncionario
	 * telacadastroFuncionario = new ControleTelaCadastroFuncionario(); try {
	 * new ControleTelaCadastroFuncionario().start(new Stage());
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }else{
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @FXML public void eventoBtnCadastrarOperacao (ActionEvent event) {
	 * 
	 * if(codOperacao == 0){ codOperacao =1; ControleTelaCadastroOperacao
	 * telacadastroOperacao = new ControleTelaCadastroOperacao (); try {
	 * telacadastroOperacao.start(new Stage(), pessoa);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }else{
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @FXML public void eventoBtnListagem (ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML public void eventoBtnGerarRelatorio (ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML public void eventoBtnGerarCobranca (ActionEvent event) {
	 * ControleTelaGerarCobranca telaGerarCobranca = new
	 * ControleTelaGerarCobranca(); try { telaGerarCobranca.start(new Stage());
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 */

	public static int getCodGerar() {
		return codGerar;
	}

	public static void setCodGerar(int codGerar) {
		ControleTelaInicial.codGerar = codGerar;
	}

	/*
	 * @FXML public void eventoBtnGerar (ActionEvent event) throws JRException{
	 * 
	 * //boolean controlCobradorIdNome = false; String cobradorNome = null;
	 * cobradorNome = cCobrador.getValue();
	 * 
	 * LocalDate dNascimento = null; dNascimento = dData.getValue();
	 * 
	 * String rotaCob = null; rotaCob = cRota.getValue();
	 * 
	 * 
	 * Date dataNascimento;
	 * 
	 * SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	 * 
	 * 
	 * 
	 * if(dNascimento == null ){ java.util.Date dataAtual = new
	 * java.util.Date(); java.sql.Date dataSQL = new
	 * java.sql.Date(dataAtual.getTime()); //java.sql.Date dataSQ1L = new
	 * java.sql.Date(dataAtual.getTime()); dataNascimento = dataSQL; }else{
	 * 
	 * dataNascimento = Date.valueOf(dNascimento);
	 * //System.out.println(dataNascimento); }
	 * 
	 * Calendar c = new GregorianCalendar(); c.setTime(dataNascimento);
	 * 
	 * String diaSemana = "";
	 * 
	 * boolean control1 = false; //seg-sex boolean control2 = false; // seg-sab
	 * boolean control3 = false; //seg dom
	 * 
	 * int dia = c.get(c.DAY_OF_WEEK); switch(dia){ case Calendar.SUNDAY:
	 * diaSemana = "Domingo";break; case Calendar.MONDAY: diaSemana =
	 * "Segunda";break; case Calendar.TUESDAY: diaSemana = "Terça";break; case
	 * Calendar.WEDNESDAY: diaSemana = "Quarta";break; case Calendar.THURSDAY:
	 * diaSemana = "Quinta";break; case Calendar.FRIDAY: diaSemana =
	 * "Sexta";break; case Calendar.SATURDAY: diaSemana = "Sabado";break; }
	 * 
	 * 
	 * 
	 * 
	 * String diaSemanaMM = diaSemana;
	 * 
	 * 
	 * if (diaSemana == "Domingo"){
	 * 
	 * DateTime date = DateTime.parse(sdf2.format(dataNascimento),
	 * DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
	 * 
	 * org.joda.time.DateTime jodal1 = date; //System.out.println(jodal1+
	 * "JODALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"); //org.joda.time.LocalDate
	 * localaux1 = jodal1.toDateTime().toLocalDate();
	 * 
	 * org.joda.time.DateTime joda1 = new
	 * org.joda.time.DateTime(jodal1.minusDays(1)); String auxl1 =
	 * joda1.toLocalDate().toString("yyyy-MM-dd");
	 * 
	 * Date local11 = null;
	 * 
	 * 
	 * 
	 * if(auxl1 == null ){ java.util.Date dataAtual = new java.util.Date();
	 * java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
	 * //java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
	 * local11 = dataSQL; }else{
	 * 
	 * local11 = Date.valueOf(auxl1); //System.out.println(dataNascimento); }
	 * //System.out.println(local11);
	 * fachada.geraRelatorioCobranca(cobradorNome, local11,
	 * "relatorios/CobrancaDomingo.jasper", rotaCob);
	 * 
	 * }else{
	 * 
	 * 
	 * fachada.geraRelatorioCobranca(cobradorNome, dataNascimento,
	 * "relatorios/cobranca.jasper", rotaCob);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	@FXML
	public void eventoBtnFerias(ActionEvent event)
			throws ParseException, RepositoryException, ClassNotFoundException, JRException, SQLException {

		LocalDate dNascimento = null;
		dNascimento = dtData.getValue();

		Date dataNascimento;
		SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");

		if (dNascimento == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			dataNascimento = dataSQL;
		} else {

			dataNascimento = Date.valueOf(dNascimento);
			// System.out.println(dataNascimento);
		}

		// System.out.println(jodal1.toString(DateTimeFormat.forPattern("yyyy-mm-dd")));

		// System.out.println(jodal1.toString("yyyy-mm-dd"));

		// System.out.println(jodal1 + " esp "+ jodal1.plusDays(1) + " so mais 1
		// "+ jodal1.plusDays(2) + " o ultimo "+ jodal1.plusDays(3));

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// Date y=new Date();
		// System.out.println(sdf1.format(dataNascimento));

		// String dataJoda = sdf1.format(joda3);
		// System.out.println(dataJoda);

		// DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		// Date diaSeguinte = (Date)formatter.parse(dataJoda);
		// java.sql.Date diaSeguinte = new
		// java.sql.Date(sdf1.parse(dataJoda).getTime());

		// System.out.println(diaSeguinte + " noo");

		// Date diaSeguinte = Date.valueOf(sdf1.format(joda3));
		// System.out.println(diaSeguinte);

		java.util.Date dataAtual1 = new java.util.Date();
		java.sql.Date dataSQL1 = new java.sql.Date(dataAtual1.getTime());

		Date dataDia;

		if (dNascimento == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			dataDia = dataSQL;
		} else {
			// System.out.println(dNascimento + "dia daqui");
			dataDia = Date.valueOf(dNascimento);
			// System.out.println(dataNascimento);
		}

		Calendar c = new GregorianCalendar();
		c.setTime(dataDia);
		// System.out.println("data da semana "+dataDia );
		String diaSemana = "";

		boolean control1 = false; // seg-sex
		boolean control2 = false; // seg-sab
		boolean control3 = false; // seg dom

		int dia = c.get(c.DAY_OF_WEEK);
		switch (dia) {
		case Calendar.SUNDAY:
			diaSemana = "Domingo";
			break;
		case Calendar.MONDAY:
			diaSemana = "Segunda";
			break;
		case Calendar.TUESDAY:
			diaSemana = "Terça";
			break;
		case Calendar.WEDNESDAY:
			diaSemana = "Quarta";
			break;
		case Calendar.THURSDAY:
			diaSemana = "Quinta";
			break;
		case Calendar.FRIDAY:
			diaSemana = "Sexta";
			break;
		case Calendar.SATURDAY:
			diaSemana = "Sabado";
			break;
		}

		String diaSemanaMM = diaSemana;

		DateTime date = DateTime.parse(sdf2.format(dataNascimento), DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));

		org.joda.time.DateTime jodal1 = date;
		// System.out.println(jodal1+
		// "JODALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		// org.joda.time.LocalDate localaux1 =
		// jodal1.toDateTime().toLocalDate();

		org.joda.time.DateTime joda1 = new org.joda.time.DateTime(jodal1.plusDays(1));
		org.joda.time.DateTime joda2 = new org.joda.time.DateTime(jodal1.plusDays(2));
		org.joda.time.DateTime joda3 = new org.joda.time.DateTime(jodal1.plusDays(3));

		// System.out.println(jodal1.toLocalDate().toString("yyyy-MM-dd") +
		// "aqui o");

		String auxl1 = joda1.toLocalDate().toString("yyyy-MM-dd");
		String auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");
		String auxl3 = joda3.toLocalDate().toString("yyyy-MM-dd");

		Date local11 = null;

		if (auxl1 == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			local11 = dataSQL;
		} else {

			local11 = Date.valueOf(auxl1);
			// System.out.println(dataNascimento);
		}

		Date local22 = null;

		if (auxl2 == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			local22 = dataSQL;
		} else {

			local22 = Date.valueOf(auxl2);
			// System.out.println(dataNascimento);
		}

		Date local33 = null;

		if (auxl3 == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			local33 = dataSQL;
		} else {

			local33 = Date.valueOf(auxl3);
			// System.out.println(dataNascimento);
		}

		Date local1 = local11;
		Date local2 = local22;
		Date local3 = local33;

		ArrayList<Status> listarStatus = new ArrayList();

		try {
			listarStatus = fachada.AllStatusCliente();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarStatus.size(); i++) {

			if (sdf2.format(listarStatus.get(i).getDataInicialPagamento()).equals(sdf2.format(dataNascimento))) {

				if (diaSemana.equals("Sabado") && (listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta"))
						&& listarStatus.get(i).isCheck_pag() == false) {

				} else if (((diaSemana.equals("Domingo")
						&& listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta"))
						|| (diaSemana.equals("Domingo")
								&& listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sabado")))
						&& listarStatus.get(i).isCheck_pag() == false) {

				} else if (listarStatus.get(i).isCheck_pag() == false) {

					try {
						fachada.updateStatus(true, 0, listarStatus.get(i).getId(), 0, 0, "Livre",
								listarStatus.get(i).getNumero_parcelas());
					} catch (Exception e) {

						e.printStackTrace();
					}

					if (listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta")
							&& diaSemana.equals("Sexta")) {

						Status newStatus = new Status(listarStatus.get(i).getNumero_parcelas(), 0,
								listarStatus.get(i).getId_operacao(), listarStatus.get(i).getTipo_pagamento(), null,
								local3, listarStatus.get(i).getMulta(), false, 0, 0, listarStatus.get(i).getAtraso(),
								listarStatus.get(i).getParcela_atual(), listarStatus.get(i).getNome_cliente_todos(),
								listarStatus.get(i).getParc_paga(), listarStatus.get(i).getAtraso_parc(),
								listarStatus.get(i).getFunc(), listarStatus.get(i).getDataAlvoSequencial());
						try {
							fachada.insertStatus(newStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sabado")
							&& diaSemana.equals("Sabado")) {

						Status newStatus = new Status(listarStatus.get(i).getNumero_parcelas(), 0,
								listarStatus.get(i).getId_operacao(), listarStatus.get(i).getTipo_pagamento(), null,
								local2, listarStatus.get(i).getMulta(), false, 0, 0, listarStatus.get(i).getAtraso(),
								listarStatus.get(i).getParcela_atual(), listarStatus.get(i).getNome_cliente_todos(),
								listarStatus.get(i).getParc_paga(), listarStatus.get(i).getAtraso_parc(),
								listarStatus.get(i).getFunc(), listarStatus.get(i).getDataAlvoSequencial());
						try {
							fachada.insertStatus(newStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (listarStatus.get(i).getTipo_pagamento().equals("Sequencial")) {

						Status status = fachada.getStatus(listarStatus.get(i).getId());

						Operacao op = fachada.getOperacao(listarStatus.get(i).getId_operacao());

						System.out.println(status.getDataAlvoSequencial() + "  sssss");

						Date data = status.getDataAlvoSequencial();

						System.out.println(data + "  d  sdsdsd");

						DateTime date2 = DateTime.parse(sdf2.format(data),
								DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));

						org.joda.time.DateTime jodal7 = date2;
						org.joda.time.DateTime joda4 = new org.joda.time.DateTime(jodal7);
						org.joda.time.DateTime joda5 = new org.joda.time.DateTime(jodal7);

						String auxl5;
						String auxl6;

						Date local44 = local11;
						Date local55 = local22;

						if (op.getFlag() == 1) {

							if (op.getIntervalo() != 0) {
								joda4 = new org.joda.time.DateTime(jodal7.plusMonths(op.getIntervalo()));
								joda5 = new org.joda.time.DateTime(
										jodal7.plusMonths(op.getIntervalo() + op.getIntervalo()));
							} else {
								joda4 = new org.joda.time.DateTime(jodal7.plusMonths(1));
								joda5 = new org.joda.time.DateTime(jodal7.plusMonths(2));

							}
							// System.out.println(joda2 + "mensal pos joda");

							auxl5 = joda4.toLocalDate().toString("yyyy-MM-dd");
							auxl6 = joda5.toLocalDate().toString("yyyy-MM-dd");

							if (auxl5 == null) {
								java.util.Date dataAtual = new java.util.Date();
								java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
								// java.sql.Date dataSQ1L = new
								// java.sql.Date(dataAtual.getTime());
								local44 = dataSQL;
							} else {

								local44 = Date.valueOf(auxl5);
								local55 = Date.valueOf(auxl6);
								// System.out.println(dataNascimento);
							}

							// System.out.println(local2 + " mensal final");

						} else {

							if (op.getIntervalo() != 0) {
								joda4 = new org.joda.time.DateTime(jodal7.plusDays(op.getIntervalo()));
								joda5 = new org.joda.time.DateTime(
										jodal7.plusDays(op.getIntervalo() + op.getIntervalo()));
							} else {
								joda4 = new org.joda.time.DateTime(jodal7.plusDays(1));
								joda5 = new org.joda.time.DateTime(jodal7.plusDays(2));
							}
							// System.out.println(joda2 + "sq pos joda");
							auxl5 = joda4.toLocalDate().toString("yyyy-MM-dd");
							auxl6 = joda5.toLocalDate().toString("yyyy-MM-dd");

							if (auxl5 == null) {
								java.util.Date dataAtual = new java.util.Date();
								java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
								// java.sql.Date dataSQ1L = new
								// java.sql.Date(dataAtual.getTime());
								local44 = dataSQL;
							} else {

								local44 = Date.valueOf(auxl5);
								local55 = Date.valueOf(auxl6);
								// System.out.println(dataNascimento);
							}

							// System.out.println(local2 + " sq final");
						}

						if (sdf4.format(status.getDataInicialPagamento())
								.equals(sdf4.format(status.getDataAlvoSequencial()))) {

							Status newStatus = new Status(listarStatus.get(i).getNumero_parcelas(), 0,
									listarStatus.get(i).getId_operacao(), listarStatus.get(i).getTipo_pagamento(), null,
									local44, listarStatus.get(i).getMulta(), false, 0, 0,
									listarStatus.get(i).getAtraso(), listarStatus.get(i).getParcela_atual(),
									listarStatus.get(i).getNome_cliente_todos(), listarStatus.get(i).getParc_paga(),
									listarStatus.get(i).getAtraso_parc(), listarStatus.get(i).getFunc(), local55);

							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else {
							Status newStatus = new Status(listarStatus.get(i).getNumero_parcelas(), 0,
									listarStatus.get(i).getId_operacao(), listarStatus.get(i).getTipo_pagamento(), null,
									local1, listarStatus.get(i).getMulta(), false, 0, 0,
									listarStatus.get(i).getAtraso(), listarStatus.get(i).getParcela_atual(),
									listarStatus.get(i).getNome_cliente_todos(), listarStatus.get(i).getParc_paga(),
									listarStatus.get(i).getAtraso_parc(), listarStatus.get(i).getFunc(),
									status.getDataAlvoSequencial());

							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} else {

						Status newStatus = new Status(listarStatus.get(i).getNumero_parcelas(), 0,
								listarStatus.get(i).getId_operacao(), listarStatus.get(i).getTipo_pagamento(), null,
								local1, listarStatus.get(i).getMulta(), false, 0, 0, listarStatus.get(i).getAtraso(),
								listarStatus.get(i).getParcela_atual(), listarStatus.get(i).getNome_cliente_todos(),
								listarStatus.get(i).getParc_paga(), listarStatus.get(i).getAtraso_parc(),
								listarStatus.get(i).getFunc(), listarStatus.get(i).getDataAlvoSequencial());
						try {
							fachada.insertStatus(newStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					Log log = new Log(listarStatus.get(i).getId(), listarStatus.get(i).getId_operacao(), pessoa.getId(),
							dataNascimento);
					fachada.insert(log);

				} else {

				}

			}
		}

		tbStatus.getItems().clear();
	}

	@FXML
	public void eventoBtnConsultar(ActionEvent event)
			throws ParseException, RepositoryException, ClassNotFoundException, JRException, SQLException {

		// gerarRelatorio ();
		tbStatus.getItems().clear();

		// org.joda.time.DateTime jodal1 = new
		// org.joda.time.DateTime();//inicializa com data/hora atuais
		// System.out.println(joda1); //imprime 2014-04-10T11:35:09.000-03:00

		// cria um novo objeto com 5 horas a menos que o joda1
		// org.joda.time.DateTime joda2 = new
		// org.joda.time.DateTime(joda1.minusHours(5));

		// System.out.println(joda3.toLocalDate() + " aqui");

		LocalDate dNascimento = null;
		dNascimento = dtData.getValue();

		Date dataNascimento;

		if (dNascimento == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			dataNascimento = dataSQL;
		} else {

			dataNascimento = Date.valueOf(dNascimento);
			// System.out.println(dataNascimento);
		}

		// System.out.println(jodal1.toString(DateTimeFormat.forPattern("yyyy-mm-dd")));

		// System.out.println(jodal1.toString("yyyy-mm-dd"));

		// System.out.println(jodal1 + " esp "+ jodal1.plusDays(1) + " so mais 1
		// "+ jodal1.plusDays(2) + " o ultimo "+ jodal1.plusDays(3));

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		// Date y=new Date();
		// System.out.println(sdf1.format(dataNascimento));

		// String dataJoda = sdf1.format(joda3);
		// System.out.println(dataJoda);

		// DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		// Date diaSeguinte = (Date)formatter.parse(dataJoda);
		// java.sql.Date diaSeguinte = new
		// java.sql.Date(sdf1.parse(dataJoda).getTime());

		// System.out.println(diaSeguinte + " noo");

		// Date diaSeguinte = Date.valueOf(sdf1.format(joda3));
		// System.out.println(diaSeguinte);

		java.util.Date dataAtual1 = new java.util.Date();
		java.sql.Date dataSQL1 = new java.sql.Date(dataAtual1.getTime());

		Date dataDia;

		if (dNascimento == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			dataDia = dataSQL;
		} else {
			// System.out.println(dNascimento + "dia daqui");
			dataDia = Date.valueOf(dNascimento);
			// System.out.println(dataNascimento);
		}

		Calendar c = new GregorianCalendar();
		c.setTime(dataDia);
		// System.out.println("data da semana "+dataDia );
		String diaSemana = "";

		boolean control1 = false; // seg-sex
		boolean control2 = false; // seg-sab
		boolean control3 = false; // seg dom

		int dia = c.get(c.DAY_OF_WEEK);
		switch (dia) {
		case Calendar.SUNDAY:
			diaSemana = "Domingo";
			break;
		case Calendar.MONDAY:
			diaSemana = "Segunda";
			break;
		case Calendar.TUESDAY:
			diaSemana = "Terça";
			break;
		case Calendar.WEDNESDAY:
			diaSemana = "Quarta";
			break;
		case Calendar.THURSDAY:
			diaSemana = "Quinta";
			break;
		case Calendar.FRIDAY:
			diaSemana = "Sexta";
			break;
		case Calendar.SATURDAY:
			diaSemana = "Sabado";
			break;
		}

		// String cobradorIdNome = null;
		// cobradorIdNome = ccCobrador.getValue().getNome();
		// String cobradorIdNomeConcatenado = "";

		String diaSemanaMM = diaSemana;

		ArrayList<Status> listarStatus = new ArrayList();

		try {
			listarStatus = fachada.AllStatusCliente();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (ccCobrador.getValue().getNome().equals("TODOS")) {
			for (int i = 0; i < listarStatus.size(); i++) {
				if (sdf2.format(listarStatus.get(i).getDataInicialPagamento()).equals(sdf2.format(dataNascimento))) {

					if (diaSemana.equals("Sabado")
							&& (listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta"))
							&& listarStatus.get(i).isCheck_pag() == false) {

					} else if (((diaSemana.equals("Domingo")
							&& listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta"))
							|| (diaSemana.equals("Domingo")
									&& listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sabado")))
							&& listarStatus.get(i).isCheck_pag() == false) {

					} else if (listarStatus.get(i).isCheck_pag() == false) {

						data.add(new Status(listarStatus.get(i).getId(), listarStatus.get(i).getNome_cliente_todos(),
								listarStatus.get(i).getFunc(), listarStatus.get(i).getNumero_parcelas(), typData.get(0),
								listarStatus.get(i).getMulta(), "", "", listarStatus.get(i).getAtraso(),
								listarStatus.get(i).getParcela_atual(), listarStatus.get(i).getDataInicialPagamento(),
								listarStatus.get(i).getFunc()));

					} else {

					}

				}
			}

		} /*
			 * else {
			 * 
			 * if (cobradorIdNome != null) { for (int i = 0; i <
			 * cobradorIdNome.length(); i++) { if (cobradorIdNome.charAt(i + 1)
			 * != '/') { cobradorIdNomeConcatenado = cobradorIdNomeConcatenado +
			 * cobradorIdNome.charAt(i); } else { break; } }
			 * 
			 * }
			 */

		// System.out.println(cobradorIdNomeConcatenado);

		for (int i = 0; i < listarStatus.size(); i++) {

			if (listarStatus.get(i).getFunc().equals(ccCobrador.getValue().getNome())) {

				if (sdf2.format(listarStatus.get(i).getDataInicialPagamento()).equals(sdf2.format(dataNascimento))) {

					if (diaSemana.equals("Sabado")
							&& (listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta"))
							&& listarStatus.get(i).isCheck_pag() == false) {

					} else if (((diaSemana.equals("Domingo")
							&& listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sexta"))
							|| (diaSemana.equals("Domingo")
									&& listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sabado")))
							&& listarStatus.get(i).isCheck_pag() == false) {

					} else if (listarStatus.get(i).isCheck_pag() == false) {

						data.add(new Status(listarStatus.get(i).getId(), listarStatus.get(i).getNome_cliente_todos(),
								listarStatus.get(i).getFunc(), listarStatus.get(i).getNumero_parcelas(), typData.get(0),
								listarStatus.get(i).getMulta(), "", "", listarStatus.get(i).getAtraso(),
								listarStatus.get(i).getParcela_atual(), listarStatus.get(i).getDataInicialPagamento(),
								listarStatus.get(i).getFunc()));

					} else {

					}

				}
			}

		}
		// }

		tbStatus.setItems(data);
		tbStatus.setEditable(true);

		TextField headerTextField = new TextField();
		TextField headerTextField2 = new TextField();
		// Label label = new Label("First Name");
		// VBox headerGraphic = new VBox();
		// headerGraphic.setAlignment(Pos.CENTER);
		// headerGraphic.getChildren().addAll(label, headerTextField);

		TableColumn<Status, Integer> tcID = new TableColumn<Status, Integer>("ID");
		tcID.setMinWidth(30.0);
		tcID.setMaxWidth(50.0);
		tcID.setStyle("-fx-alignment: CENTER;");

		tcID.setCellValueFactory(new PropertyValueFactory<Status, Integer>("id"));

		/*
		 * TableColumn<Status, Date> tcDataPagamento = new TableColumn<Status,
		 * Date>("Data"); tcDataPagamento.setMinWidth(60.0);
		 * tcDataPagamento.setMaxWidth(80.0); tcDataPagamento.setStyle(
		 * "-fx-alignment: CENTER;");
		 * 
		 * tcDataPagamento.setCellValueFactory( new PropertyValueFactory<Status,
		 * Date>("data_inicial_pagamento"));
		 */

		TableColumn<Status, String> tcCliente = new TableColumn("Cliente");
		tcCliente.setMinWidth(240);
		tcCliente.setMaxWidth(500);
		// tcCliente.setStyle( "-fx-alignment: LEFT;");
		tcCliente.setCellValueFactory(new PropertyValueFactory<Status, String>("cliente"));

		TableColumn<Status, String> tcCobrador = new TableColumn("Cobrador");
		tcCobrador.setMinWidth(200);
		tcCobrador.setMaxWidth(240);
		tcCobrador.setStyle("-fx-alignment: CENTER;");
		tcCobrador.setCellValueFactory(new PropertyValueFactory<Status, String>("func"));

		TableColumn<Status, Integer> tcParcelas = new TableColumn("N.Parcelas");
		tcParcelas.setMinWidth(70);
		tcParcelas.setMaxWidth(70);
		tcParcelas.setStyle("-fx-alignment: CENTER;");
		tcParcelas.setCellValueFactory(new PropertyValueFactory<Status, Integer>("numero_parcelas"));

		TableColumn<Status, Integer> tcParcelasAtual = new TableColumn<Status, Integer>("Parcela atual");
		tcParcelasAtual.setMinWidth(90);
		tcParcelasAtual.setMaxWidth(90);
		tcParcelasAtual.setStyle("-fx-alignment: CENTER;");
		tcParcelasAtual.setCellValueFactory(new PropertyValueFactory<Status, Integer>("parcela_atual"));

		TableColumn<Status, Integer> tcAtraso = new TableColumn<Status, Integer>("Atrasos");
		tcAtraso.setMinWidth(50);
		tcAtraso.setMaxWidth(50);
		tcAtraso.setStyle("-fx-alignment: CENTER;");
		tcAtraso.setCellValueFactory(new PropertyValueFactory<Status, Integer>("atraso"));

		Callback<TableColumn<Status, Typ>, TableCell<Status, Typ>> comboBoxCellFactory = (
				TableColumn<Status, Typ> param) -> new ComboBoxEditingCell();

		/*
		 * TableColumn<Status, Typ> tcPagamento = new TableColumn(
		 * "Tipo de Pagamento"); tcPagamento.setMinWidth(210.0);
		 * tcPagamento.setStyle( "-fx-alignment: CENTER;");
		 * 
		 */

		TableColumn<Status, Integer> tcMulta = new TableColumn("Multa Atrasada");
		tcMulta.setMinWidth(110.0);
		tcMulta.setStyle("-fx-alignment: CENTER;");
		tcMulta.setCellValueFactory(new PropertyValueFactory<Status, Integer>("multa"));

		TableColumn tcNumeroMulta = new TableColumn("Nº de Multas");
		tcNumeroMulta.setMinWidth(120.0);
		tcNumeroMulta.setStyle("-fx-alignment: CENTER;");
		tcNumeroMulta.setCellValueFactory(new PropertyValueFactory<Status, String>("multasO"));
		tcNumeroMulta.setCellFactory(TextFieldTableCell.forTableColumn());
		tcNumeroMulta.setOnEditCommit(new EventHandler<CellEditEvent<Status, String>>() {

			public void handle(CellEditEvent<Status, String> t) {

				((Status) t.getTableView().getItems().get(

						t.getTablePosition().getRow())

				).setMultasO(t.getNewValue());

			}

		});

		/*
		 * tcNumeroMulta.setCellValueFactory(cellData ->
		 * cellData.getValue().typObjProperty());
		 * tcNumeroMulta.setCellFactory(comboBoxCellFactory2);
		 * tcNumeroMulta.setOnEditCommit( (TableColumn.CellEditEvent<Status,
		 * Typ> t) -> { ((Status) t.getTableView().getItems()
		 * .get(t.getTablePosition().getRow())) .setTypObj(t.getNewValue()); });
		 * 
		 */
		// TableColumn<Status, Typ> tcNumeroParcelas = new TableColumn("Nº de
		// parcelas");
		// tcNumeroParcelas.setMinWidth(120.0);
		// tcNumeroParcelas.setStyle( "-fx-alignment: CENTER;");

		TableColumn tcNumeroParcelas = new TableColumn("Nº de parcelas");
		tcNumeroParcelas.setMinWidth(120.0);
		tcNumeroParcelas.setStyle("-fx-alignment: CENTER;");
		tcNumeroParcelas.setCellValueFactory(new PropertyValueFactory<Status, String>("parcelasO"));
		tcNumeroParcelas.setCellFactory(TextFieldTableCell.forTableColumn());
		tcNumeroParcelas.setOnEditCommit(new EventHandler<CellEditEvent<Status, String>>() {

			public void handle(CellEditEvent<Status, String> t) {

				((Status) t.getTableView().getItems().get(

						t.getTablePosition().getRow())

				).setParcelasO(t.getNewValue());

			}

		});

		/*
		 * tcNumeroParcelas.setCellValueFactory(cellData ->
		 * cellData.getValue().typObjProperty());
		 * tcNumeroParcelas.setCellFactory(comboBoxCellFactory3);
		 * tcNumeroParcelas.setOnEditCommit( (TableColumn.CellEditEvent<Status,
		 * Typ> t) -> { ((Status) t.getTableView().getItems()
		 * .get(t.getTablePosition().getRow())) .setTypObj(t.getNewValue()); });
		 */

		TableColumn<Status, Typ> tcPagamento = new TableColumn("Tipo de Pagamento");
		tcPagamento.setMinWidth(268.0);
		tcPagamento.setStyle("-fx-alignment: CENTER;");

		tcPagamento.setCellValueFactory(cellData -> cellData.getValue().typObjProperty());
		tcPagamento.setCellFactory(comboBoxCellFactory);
		tcPagamento.setOnEditCommit((TableColumn.CellEditEvent<Status, Typ> t) -> {
			((Status) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTypObj(t.getNewValue());

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			LocalDate dNascimento1 = null;
			dNascimento1 = dtData.getValue();

			Date dataNascimento1;

			if (dNascimento1 == null) {
				java.util.Date dataAtual = new java.util.Date();
				java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
				// java.sql.Date dataSQ1L = new
				// java.sql.Date(dataAtual.getTime());
				dataNascimento1 = dataSQL;
			} else {

				dataNascimento1 = Date.valueOf(dNascimento1);
				// System.out.println(dataNascimento);
			}

			Calendar c1 = new GregorianCalendar();
			c1.setTime(dataNascimento1);
			// System.out.println("data da semana "+dataNascimento1 );
			String diaSemana1 = "";

			int dia2 = c1.get(c1.DAY_OF_WEEK);
			switch (dia2) {
			case Calendar.SUNDAY:
				diaSemana1 = "Domingo";
				break;
			case Calendar.MONDAY:
				diaSemana1 = "Segunda";
				break;
			case Calendar.TUESDAY:
				diaSemana1 = "Terça";
				break;
			case Calendar.WEDNESDAY:
				diaSemana1 = "Quarta";
				break;
			case Calendar.THURSDAY:
				diaSemana1 = "Quinta";
				break;
			case Calendar.FRIDAY:
				diaSemana1 = "Sexta";
				break;
			case Calendar.SATURDAY:
				diaSemana1 = "Sabado";
				break;
			}

			String diaSemanaM = diaSemana1;

			DateTime date = DateTime.parse(sdf1.format(dataNascimento1),
					DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));

			org.joda.time.DateTime jodal1 = date;
			// System.out.println(jodal1+
			// "JODALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			// org.joda.time.LocalDate localaux1 =
			// jodal1.toDateTime().toLocalDate();

			org.joda.time.DateTime joda1 = new org.joda.time.DateTime(jodal1.plusDays(1));
			org.joda.time.DateTime joda2 = new org.joda.time.DateTime(jodal1.plusDays(2));
			org.joda.time.DateTime joda3 = new org.joda.time.DateTime(jodal1.plusDays(3));
			org.joda.time.DateTime joda4 = new org.joda.time.DateTime(jodal1.plusDays(1));

			// System.out.println(jodal1.toLocalDate().toString("yyyy-MM-dd") +
			// "aqui o");

			String auxl1 = joda1.toLocalDate().toString("yyyy-MM-dd");
			String auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");
			String auxl3 = joda3.toLocalDate().toString("yyyy-MM-dd");
			String auxl4 = joda4.toLocalDate().toString("yyyy-MM-dd");

			Date local44 = null;

			if (auxl4 == null) {
				java.util.Date dataAtual = new java.util.Date();
				java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
				// java.sql.Date dataSQ1L = new
				// java.sql.Date(dataAtual.getTime());
				local44 = dataSQL;
			} else {

				local44 = Date.valueOf(auxl4);
				// System.out.println(dataNascimento);
			}
			Date local4 = local44;

			Date local11 = null;

			if (auxl1 == null) {
				java.util.Date dataAtual = new java.util.Date();
				java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
				// java.sql.Date dataSQ1L = new
				// java.sql.Date(dataAtual.getTime());
				local11 = dataSQL;
			} else {

				local11 = Date.valueOf(auxl1);
				// System.out.println(dataNascimento);
			}

			Date local22 = null;

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

			Date local33 = null;

			if (auxl3 == null) {
				java.util.Date dataAtual = new java.util.Date();
				java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
				// java.sql.Date dataSQ1L = new
				// java.sql.Date(dataAtual.getTime());
				local33 = dataSQL;
			} else {

				local33 = Date.valueOf(auxl3);
				// System.out.println(dataNascimento);
			}

			Date local1 = local11;
			Date local2 = local22;
			Date local3 = local33;

			// System.out.println(local1 + " " + local2 + " " + local3 + "pq ta
			// zuado ??");

			Typ pagamentoRow = tcPagamento.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			Object numeroParcelasRow = tcNumeroParcelas
					.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			Object NumeroMultasRow = tcNumeroMulta
					.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));

			int idRow = tcID.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			String clienteRow = tcCliente.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			String cobradorRow = tcCobrador.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			int parcelasRow = tcParcelas.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			int multaRow = tcMulta.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));

			int parcelaAtual = tcParcelasAtual
					.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			int atraso = tcAtraso.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));
			// Date dataPagamento =
			// tcDataPagamento.getCellData(t.getTableView().getItems().get(t.getTablePosition().getRow()));

			Status old = null;
			Operacao op = null;

			int idOperacao = 0;
			try {
				idOperacao = fachada.getIdOperacao(idRow);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				op = fachada.getOperacao(idOperacao);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// System.out.println(idRow);

			try {
				old = fachada.getStatus(idRow);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-mm-dd");

			// novo modulo para datas diferenciais

			String aux4;

			if (op.getFlag() == 0) {

			} else if (op.getFlag() == 1) {

				if (op.getIntervalo() != 0) {
					joda2 = new org.joda.time.DateTime(jodal1.plusMonths(op.getIntervalo()));
					joda3 = new org.joda.time.DateTime(jodal1.plusMonths(op.getIntervalo() + op.getIntervalo()));
				} else {
					joda2 = new org.joda.time.DateTime(jodal1.plusMonths(1));
					joda3 = new org.joda.time.DateTime(jodal1.plusMonths(2));

				}
				// System.out.println(joda2 + "mensal pos joda");

				auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");
				auxl4 = joda3.toLocalDate().toString("yyyy-MM-dd");

				if (auxl2 == null) {
					java.util.Date dataAtual = new java.util.Date();
					java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
					// java.sql.Date dataSQ1L = new
					// java.sql.Date(dataAtual.getTime());
					local22 = dataSQL;
				} else {

					local22 = Date.valueOf(auxl2);
					local33 = Date.valueOf(auxl4);
					// System.out.println(dataNascimento);
				}

				local1 = local22;
				local2 = local33;
				// System.out.println(local2 + " mensal final");

			} else {

				if (op.getIntervalo() != 0) {
					joda2 = new org.joda.time.DateTime(jodal1.plusDays(op.getIntervalo()));
					joda3 = new org.joda.time.DateTime(jodal1.plusDays(op.getIntervalo() + op.getIntervalo()));
				} else {
					joda2 = new org.joda.time.DateTime(jodal1.plusDays(1));
					joda3 = new org.joda.time.DateTime(jodal1.plusDays(2));
				}
				// System.out.println(joda2 + "sq pos joda");
				auxl2 = joda2.toLocalDate().toString("yyyy-MM-dd");
				auxl3 = joda3.toLocalDate().toString("yyyy-MM-dd");

				if (auxl2 == null) {
					java.util.Date dataAtual = new java.util.Date();
					java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
					// java.sql.Date dataSQ1L = new
					// java.sql.Date(dataAtual.getTime());
					local22 = dataSQL;
				} else {

					local22 = Date.valueOf(auxl2);
					local33 = Date.valueOf(auxl3);
					// System.out.println(dataNascimento);
				}

				local1 = local22;
				local2 = local33;
				// System.out.println(local2 + " sq final");
			}

			// org.joda.time.LocalDate localaux1 =
			// joda1.toDateTime().toLocalDate();

			// Date local1;

			/*
			 * if(localaux1 == null ){ java.util.Date dataAtual = new
			 * java.util.Date(); java.sql.Date dataSQL = new
			 * java.sql.Date(dataAtual.getTime()); //java.sql.Date dataSQ1L =
			 * new java.sql.Date(dataAtual.getTime()); local1 = dataSQL; }else{
			 * 
			 * local1 = Date.valueOf(dNascimento);
			 * //System.out.println(dataNascimento); }
			 * 
			 */

			/*
			 * java.sql.Date local1 = null; try { local1 = new
			 * java.sql.Date(sdf2.parse(joda1.toLocalDate().toString(
			 * "yyyy-MM-dd")).getTime());
			 * 
			 * } catch (Exception e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); }
			 * 
			 * java.sql.Date local2 = null; try { local2 = new
			 * java.sql.Date(sdf2.parse(joda2.toLocalDate().toString(
			 * "yyyy-MM-dd")).getTime()); } catch (Exception e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 * 
			 * 
			 * java.sql.Date local3 = null; try { local3 = new
			 * java.sql.Date(sdf2.parse(joda3.toLocalDate().toString(
			 * "yyyy-MM-dd")).getTime()); } catch (Exception e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 * System.out.println(local1 + " " + local2+ " "+ local3);
			 */
			// java.sql.Timestamp local1 =
			// java.sql.Timestamp.valueOf(joda1.toString("YYYY/MM/dd"));
			// java.sql.Timestamp local2 =
			// java.sql.Timestamp.valueOf(joda1.toString("YYYY/MM/dd"));
			// java.sql.Timestamp local3 =
			// java.sql.Timestamp.valueOf(joda1.toString("YYYY/MM/dd"));

			/*
			 * java.util.Date d; java.sql.Date local1 = null;
			 * 
			 * String x = joda1.toString("YYYY/MM/dd");
			 * 
			 * try { d = sdf2.parse(x); local1 = new java.sql.Date(d.getTime());
			 * } catch (Exception e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); }
			 * 
			 * java.util.Date d2; java.sql.Date local2 = null; try { d2 =
			 * sdf2.parse(joda2.toString("YYYY/MM/dd")); local2 = new
			 * java.sql.Date(d2.getTime()); } catch (Exception e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 * 
			 * java.util.Date d3; java.sql.Date local3 = null; try { d3 =
			 * sdf2.parse(joda3.toString("YYYY/MM/dd")); local3 = new
			 * java.sql.Date(d3.getTime()); } catch (Exception e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 * 
			 * System.out.println(local1 + " " + local2+ " "+ local3);
			 */
			// Date local1 ;
			// local1 = Date.valueOf(sdf2.format(joda1.toString()));

			// Date local2 ;
			// local2 = Date.valueOf(sdf2.format(joda2.toString()));

			// Date local3 ;
			// local3 = Date.valueOf(sdf2.format(joda3.toString()));
			//

			// System.out.println(valor_recebido + " dadad auxxx");

			String fullName = "";
			try {
				fullName = fachada.getIdNome(old.getId_operacao());
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			ArrayList<Integer> arrayParc = new ArrayList();

			try {
				arrayParc = fachada.getAllParc(idRow);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			int parcPagas = arrayParc.get(0);
			int atraso2 = arrayParc.get(1);

			if (numeroParcelasRow.equals("")) {
				numeroParcelasRow = "0";
			}
			if (NumeroMultasRow.equals("")) {
				NumeroMultasRow = "0";
			}

			if (pagamentoRow.getTyp().equals("PARCELA")) {

				double valor_recebido = op.getValorDiario();

				String aux = (String) numeroParcelasRow;

				int parcelasRowAux = Integer.parseInt(aux.trim());

				if (parcelasRowAux != 0) {

				} else {

					// tudo ok pegando text field
					// String aux = (String) numeroParcelasRow ;
					// System.out.println(aux + " auxxx " + aux);

					// String aux = (String) numeroParcelasRow ;
					// System.out.println (aux + "auxxxx AQUIII");

					/*
					 * String aux = numeroParcelasRow.getTyp();
					 * 
					 * System.out.println(aux + " auxxx    " +
					 * numeroParcelasRow.getTyp());
					 * 
					 * int idnew = Integer.parseInt(aux.trim());
					 * 
					 * if(idnew != 0){ idnew = idnew -1; }else{ // todo }
					 */
					// System.out.println("parcelasRow " + parcelasRow + "
					// parcelaAtual " + parcelaAtual);

					if (parcelaAtual != 0) {
						if (parcelasRow != parcelaAtual) {

							int parc = parcelaAtual + 1;
							parcPagas = parcPagas + 1;
							
							if(parc == parcelasRow){
								atraso2 = 1;
							}

							// System.out.println("entrou aqui");

							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {
								
								

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
								// System.out.println (local3 + " deve ser 2
								// superior");
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else if (op.getFlag() == 1 || op.getFlag() == 2) {
								// System.out.println("ta certo" + local1);

								if (old.getDataAlvoSequencial() == null) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, null);
									// System.out.println (local2 + " ta aqui
									// vendo
									// a data data +1");
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								} else {
									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
												parc, fullName, parcPagas, atraso2, cobradorRow, local2);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, old.getDataAlvoSequencial(), multaRow,
												false, 0, 0, atraso, parc, fullName, parcPagas, atraso2, cobradorRow,
												local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
								// System.out.println (local2 + " ta aqui vendo
								// a data data +1");
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
								// System.out.println (local1 + " ta aqui vendo
								// a data data +1");
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, 1, 0, pagamentoRow.getTyp(),
										parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t.getTableView().getItems().remove(t.getTablePosition().getRow());

						} else {
							if (multaRow == 0) {
								/*
								 * try { fachada.updateStatus(true,
								 * valor_recebido, idRow, 1, 0 ,
								 * pagamentoRow.getTyp(), parcelasRow); } catch
								 * (Exception e) { // TODO Auto-generated catch
								 * block e.printStackTrace(); }
								 */
								// System.out.print("finalizado");
								try {
									fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, 1, 0, pagamentoRow.getTyp(),
											parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								try {
									fachada.updateUno(parcelasRow, idRow);
								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());

							} else {

								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									// System.out.println (local3 + " deve ser 2
									// superior");
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
												fullName, parcelasRow, 0, cobradorRow, null);
										// System.out.println (local2 + " ta
										// aqui
										// vendo a data data +1");
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso, 0,
												fullName, parcelasRow, 0, cobradorRow, local4);
										// System.out.println (local2 + " ta
										// aqui
										// vendo a data data +1");
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									// System.out.println (local2 + " ta aqui
									// vendo a data data +1");
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									// System.out.println (local1 + " ta aqui
									// vendo a data data +1");
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, 1, 0, pagamentoRow.getTyp(),
											parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());

							}

						}

					} else {
						// faça nada
					}

				}

			} else if (pagamentoRow.getTyp().equals("MULTA")) {

				double valor_recebido = op.getValor_juros();

				String aux2 = (String) NumeroMultasRow;

				int multasRowAux = Integer.parseInt(aux2.trim());

				if (atraso2 == 1) {
					atraso2 = 1;
				} else {
					atraso2 = 2;
				}

				if (multasRowAux != 0) {

				} else {

					if (multaRow == 0) {
						System.out.print("1");
						if (parcelasRow != parcelaAtual) {
							System.out.print("2");
							multaRow = multaRow - 1;

							if (multaRow < 0) {
								multaRow = 0;
							}

							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (op.getFlag() == 1 || op.getFlag() == 2) {
								System.out.print("3");
								if (old.getDataAlvoSequencial() == null) {
									System.out.print("4");
									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									System.out.print("6");
									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {
										System.out.print("5");
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
										parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							t.getTableView().getItems().remove(t.getTablePosition().getRow());

							// REVER COM O TEMPO
						} else {
							multaRow = multaRow - 1;

							if (multaRow < 0) {
								multaRow = 0;
							}

							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (op.getFlag() == 1 || op.getFlag() == 2) {
								System.out.print("3");
								if (old.getDataAlvoSequencial() == null) {
									System.out.print("4");
									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									System.out.print("6");
									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {
										System.out.print("5");
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
										parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							t.getTableView().getItems().remove(t.getTablePosition().getRow());
						}
						// msg de erro
					} else {
						System.out.print("8");
						if (parcelaAtual != 0) { // se vier como 0
							System.out.print("9");
							if (parcelasRow != parcelaAtual) { // se ainda ta
																// pagando
								System.out.print("10");
								if (multaRow != 0) {
									System.out.print("11");
									if (multaRow < 0) {
										multaRow = 0;
									}
									// System.out.print("hello word multa row !=
									// 0");

									// multaRow = multaRow -1;
									// atraso = atraso -1;

									if (old.getTipo_pagamento().equals("Segunda a Sexta")
											&& diaSemanaM.equals("Sexta")) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else if (op.getFlag() == 1 || op.getFlag() == 2) {

										if (old.getDataAlvoSequencial() == null) {

											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0,
													atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
													null);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {

											if (sdf1.format(old.getDataInicialPagamento())
													.equals(sdf1.format(old.getDataAlvoSequencial()))) {

												Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
														old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0,
														atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
														null);
												try {
													fachada.insertStatus(newStatus);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
												try {
													fachada.insertStatus(newStatus);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}

											} else {
												Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
														old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0,
														atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
														old.getDataAlvoSequencial());
												try {
													fachada.insertStatus(newStatus);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
											}
										}
									} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
											&& diaSemanaM.equals("Sabado")) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
									try {
										fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
												parcelasRow);
									} catch (Exception e) {

										e.printStackTrace();
									}

									Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
									try {
										fachada.insert(log);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									t.getTableView().getItems().remove(t.getTablePosition().getRow());

								} else {
									// N TIRA D ALISTA

								}
							} else {
								if ((multaRow - 1) != 0) {

									multaRow = multaRow - 1;
									atraso = atraso - 1;

									if (multaRow < 0) {
										multaRow = 0;
									}
									if (old.getTipo_pagamento().equals("Segunda a Sexta")
											&& diaSemanaM.equals("Sexta")) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else if (op.getFlag() == 1 || op.getFlag() == 2) {

										if (old.getDataAlvoSequencial() == null) {
											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0,
													atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
													null);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {

											if (sdf1.format(old.getDataInicialPagamento())
													.equals(sdf1.format(old.getDataAlvoSequencial()))) {

												Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
														old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0,
														atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
														local1);
												try {
													fachada.insertStatus(newStatus);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}

											} else {
												Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
														old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0,
														atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
														old.getDataAlvoSequencial());
												try {
													fachada.insertStatus(newStatus);
												} catch (Exception e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
											}
										}
									} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
											&& diaSemanaM.equals("Sabado")) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
												old.getDataAlvoSequencial());
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
									try {
										fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
												parcelasRow);
									} catch (Exception e) {

										e.printStackTrace();
									}

									Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
									try {
										fachada.insert(log);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									t.getTableView().getItems().remove(t.getTablePosition().getRow());

								} else { // fechou as dividas e pagamento

									try {
										fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									try {
										fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
												parcelasRow);
									} catch (Exception e) {

										e.printStackTrace();
									}
									Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
									try {
										fachada.insert(log);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									t.getTableView().getItems().remove(t.getTablePosition().getRow());
								}

							}

						} else {

							if ((multaRow - 1) != 0) {

								multaRow = multaRow - 1;
								atraso = atraso - 1;

								if (multaRow < 0) {
									multaRow = 0;
								}
								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										if (sdf1.format(old.getDataInicialPagamento())
												.equals(sdf1.format(old.getDataAlvoSequencial()))) {

											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0,
													atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
													local1);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}

										} else {
											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0,
													atraso, parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
													old.getDataAlvoSequencial());
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									}
								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
								try {
									fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
											parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());

							} else { // fehco as dividas e pagamento

								try {
									fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, 0, 1, pagamentoRow.getTyp(),
											parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							}

						}
					}

				}

			}

			else if (pagamentoRow.getTyp().equals("FINALIZAR")) {

				double valorDia = op.getValorDiario();

				double valorJuros = op.getValor_juros();

				String aux = (String) numeroParcelasRow;

				int parcelasRowAux = Integer.parseInt(aux.trim());

				String aux2 = (String) NumeroMultasRow;

				int multasRowAux = Integer.parseInt(aux2.trim());

				double valor_recebido = (valorDia * parcelasRowAux) + (valorJuros * multasRowAux);

				int parcial = parcelasRowAux + parcelaAtual - 1;

				System.out.println(parcelasRowAux + " " + parcelaAtual + " " + parcial);

				if (parcial == parcelasRow) {

					try {
						fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, multasRowAux,
								pagamentoRow.getTyp(), parcelasRow);
					} catch (Exception e) {

						e.printStackTrace();
					}

					try {
						fachada.updateUno(parcelasRow, idRow);
					} catch (Exception e) {

						e.printStackTrace();
					}

					Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
					try {
						fachada.insert(log);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					t.getTableView().getItems().remove(t.getTablePosition().getRow());

				} else {
					// msg show
				}

			} else if (pagamentoRow.getTyp().equals("PARCELA + MULTA")) {

				// boolean cond = true;
				double valorPar = op.getValorDiario();
				double valorMulta = op.getValor_juros();

				double valor_recebido = valorPar + valorMulta;

				if (multaRow == 0) {
					// cond = false;
					atraso2 = 1;
				}

				if (parcelaAtual != 0) {
					if (parcelasRow != parcelaAtual) {
						// if (multaRow != 0) {
						int parc = parcelaAtual + 1;

						int parcMulta;
						int parcAtraso;
						parcMulta = multaRow - 1;
						parcAtraso = atraso - 1;
						parcPagas = parcPagas + 1;

						if (parcMulta < 0) {
							parcMulta = 0;
						}

						if (atraso < 0) {
							atraso = 0;
						}
						
						if(parc == parcelasRow){
							atraso2 = 1;
						}

						if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local3, parcMulta, false, 0, 0, parcAtraso, parc, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (op.getFlag() == 1 || op.getFlag() == 2) {
							System.out.print("aqui");
							if (old.getDataAlvoSequencial() == null) {
								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, parcAtraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, null);
								System.out.print("aqui2");
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								if (sdf1.format(old.getDataInicialPagamento())
										.equals(sdf1.format(old.getDataAlvoSequencial()))) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, parcAtraso,
											parc, fullName, parcPagas, atraso2, cobradorRow, local4);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}

								} else {
									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, old.getDataAlvoSequencial(), parcMulta,
											false, 0, 0, parcAtraso, parc, fullName, parcPagas, atraso2, cobradorRow,
											local1);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch
										// block
										e.printStackTrace();
									}
								}
							}

						} else if (old.getTipo_pagamento().equals("Segunda a Sabado") && diaSemanaM.equals("Sabado")) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local2, parcMulta, false, 0, 0, parcAtraso, parc, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local1, parcMulta, false, 0, 0, parcAtraso, parc, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						try {
							fachada.updateStatus(true, valor_recebido, idRow, 1, 1, pagamentoRow.getTyp(), parcelasRow);
						} catch (Exception e) {

							e.printStackTrace();
						}
						Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
						try {
							fachada.insert(log);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						t.getTableView().getItems().remove(t.getTablePosition().getRow());
						// } else {

						// faça nada
						// }
					} else {
						if ((multaRow - 1) != 0) {
							// parcPagas = parcPagas + 1;
							multaRow = multaRow - 1;
							atraso = atraso - 1;

							if (multaRow < 0) {
								multaRow = 0;
							}

							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso, 0,
										fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (op.getFlag() == 1 || op.getFlag() == 2) {

								if (old.getDataAlvoSequencial() == null) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, null);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
												fullName, parcelasRow, 0, cobradorRow, local2);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch
											// block
											e.printStackTrace();
										}
									} else {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, old.getDataAlvoSequencial(), multaRow,
												false, 0, 0, atraso, 0, fullName, parcelasRow, 0, cobradorRow, local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch
											// block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso, 0,
										fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
										fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
							try {
								fachada.updateStatus(true, valor_recebido, idRow, 1, 1, pagamentoRow.getTyp(),
										parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t.getTableView().getItems().remove(t.getTablePosition().getRow());

						} else { // fehco as dividas e pagamento

							try {
								fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, 1, 1, pagamentoRow.getTyp(),
										parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							try {
								fachada.updateUno(parcelasRow, idRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t.getTableView().getItems().remove(t.getTablePosition().getRow());
						}
					}

				} else {
					// faça nada
				}

			} else if (pagamentoRow.getTyp().equals("PARCELAS")) {

				double parcValor = op.getValorDiario();

				String aux = (String) numeroParcelasRow;

				int parcelasRowAux = Integer.parseInt(aux.trim());
				double valor_recebido = parcValor * parcelasRowAux;

				if (multaRow == 0) {
					// System.out.print("Parcelass");

					if (atraso2 == 2) {
						atraso2 = 1;
					}

				}

				if (parcelasRowAux == 0) {

				} else {
					if (parcelaAtual != 0) {
						if (parcelasRow != (parcelaAtual + parcelasRowAux - 1)) {
							if (multaRow == 0) {
								parcPagas = parcPagas + parcelasRowAux;

								int parc = parcelaAtual + parcelasRowAux;

								atraso = atraso - (parcelasRowAux - 1);

								if (atraso < 0) {
									atraso = 0;
								}
								
								if(parc == parcelasRow){
									atraso2 = 1;
								}

								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
												parc, fullName, parcPagas, atraso2, cobradorRow, null);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										if (sdf1.format(old.getDataInicialPagamento())
												.equals(sdf1.format(old.getDataAlvoSequencial()))) {

											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0,
													atraso, parc, fullName, parcPagas, atraso2, cobradorRow, local2);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {
											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, old.getDataAlvoSequencial(),
													multaRow, false, 0, 0, atraso, parc, fullName, parcPagas, atraso2,
													cobradorRow, local1);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									}

								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, 0,
											pagamentoRow.getTyp(), parcelasRow);

								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							} else {
								int parc = parcelaAtual + parcelasRowAux;
								parcPagas = parcPagas + parcelasRowAux;

								atraso = atraso - (parcelasRowAux - 1);

								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso,
												parc, fullName, parcPagas, atraso2, cobradorRow, null);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {

										if (sdf1.format(old.getDataInicialPagamento())
												.equals(sdf1.format(old.getDataAlvoSequencial()))) {

											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0,
													atraso, parc, fullName, parcPagas, atraso2, cobradorRow, local2);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {
											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, old.getDataAlvoSequencial(),
													multaRow, false, 0, 0, atraso, parc, fullName, parcPagas, atraso2,
													cobradorRow, local1);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									}

								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, 0,
											pagamentoRow.getTyp(), parcelasRow);

								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							}
						} else {
							if (multaRow == 0) {

								try {
									fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, 0,
											pagamentoRow.getTyp(), parcelasRow);

								} catch (Exception e) {

									e.printStackTrace();
								}

								try {
									fachada.updateUno(parcelasRow, idRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							} else {

								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
												fullName, parcelasRow, 0, cobradorRow, null);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {

										if (sdf1.format(old.getDataInicialPagamento())
												.equals(sdf1.format(old.getDataAlvoSequencial()))) {

											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0,
													atraso, 0, fullName, parcelasRow, 0, cobradorRow, local2);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {
											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, old.getDataAlvoSequencial(),
													multaRow, false, 0, 0, atraso, 0, fullName, parcelasRow, 0,
													cobradorRow, local1);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									}

								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, multaRow, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, 0,
											pagamentoRow.getTyp(), parcelasRow);

								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							}
						}
					} else {
						// faça nada
					}
				}

			} else if (pagamentoRow.getTyp().equals("MULTAS")) {

				String aux2 = (String) NumeroMultasRow;

				int multasRowAux = Integer.parseInt(aux2.trim());

				double valor_recebido = op.getValor_juros() * multasRowAux;

				// if (multaRow == 0) {
				// System.out.print("Parcelass");

				// atraso = 0;

				// }

				if (atraso2 == 2) {
					if (multasRowAux == multaRow + 1) {
						atraso2 = 1;
					}else if (multaRow == 0){
						atraso2 = 1;
					}else{
						atraso2 = 2;
					}

				} else {
					if (multasRowAux == 2) {
						atraso2 = 2;
					} else if (multasRowAux == 1) {
						atraso2 = 1;
					}
				}

				int parcMulta;
				atraso = atraso - multasRowAux;

				if (atraso < 0) {
					atraso = 0;
				}
				//LOGICA DE PAGOU OU N A DO DIA
				if (multaRow != 0) {
					if (multasRowAux == multaRow + 1) {
						parcMulta = 0;
						// atraso = 0;
					} else {
						parcMulta = multaRow - multasRowAux + 1;
						// atraso = atraso - multasRowAux + 1;
						if(parcMulta < 0){
							parcMulta = 0;
						}
					}

				} else {
					parcMulta = 0;

				}

				if (multasRowAux == 0) {
					// msg pro usuario que fez merda
				} else {
					// System.out.print ("parcela atual" + parcelaAtual);
					if (parcelaAtual != 0) {
						// System.out.print ("parcela atual deveria está aqui" +
						// parcelaAtual);
						if (parcelasRow != parcelaAtual) { // se ainda ta
															// pagando
							// System.out.print ("aqui na mulkta");
							// System.out.print("atraso"+ atraso);

							if (parcMulta < 0) {
								parcMulta = 0;
							}

							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

								Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, parcMulta, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (op.getFlag() == 1 || op.getFlag() == 2) {

								if (old.getDataAlvoSequencial() == null) {

									Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								} else {

									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {

										Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local2);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
												old.getTipo_pagamento(), null, old.getDataAlvoSequencial(), parcMulta,
												false, 0, 0, atraso, parcelaAtual, fullName, parcPagas, atraso2,
												cobradorRow, local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, parcMulta, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, 0, multasRowAux,
										pagamentoRow.getTyp(), parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t.getTableView().getItems().remove(t.getTablePosition().getRow());
						} else {
							// System.out.print ("aqui na mulkta 2");
							if ((multaRow - multasRowAux) != 0) {

								if (parcMulta < 0) {
									parcMulta = 0;
								}
								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, parcMulta, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {
										Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										if (sdf1.format(old.getDataInicialPagamento())
												.equals(sdf1.format(old.getDataAlvoSequencial()))) {

											Status newStatus = new Status(parcelasRow, valor_recebido,
													old.getId_operacao(), old.getTipo_pagamento(), null, local1,
													parcMulta, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
													atraso2, cobradorRow, local2);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}

										} else {

											Status newStatus = new Status(parcelasRow, valor_recebido,
													old.getId_operacao(), old.getTipo_pagamento(), null,
													old.getDataAlvoSequencial(), parcMulta, false, 0, 0, atraso,
													parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local1);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									}

								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, parcMulta, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

								try {
									fachada.updateStatus(true, valor_recebido, idRow, 0, multasRowAux,
											pagamentoRow.getTyp(), parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								t.getTableView().getItems().remove(t.getTablePosition().getRow());

							} else {

								try {
									fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									fachada.updateStatus(true, valor_recebido, idRow, 0, multasRowAux,
											pagamentoRow.getTyp(), parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							}

						}
					} else {
						if ((multaRow - multasRowAux) != 0) {

							parcMulta = multaRow - multasRowAux;

							if (parcMulta < 0) {
								parcMulta = 0;
							}
							// atraso = atraso - multasRowAux;
							// System.out.print ("aqui na mulkta");
							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

								Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, parcMulta, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (op.getFlag() == 1 || op.getFlag() == 2) {

								if (old.getDataAlvoSequencial() == null) {

									Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								} else {

									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {
										Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
												parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local2);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
												old.getTipo_pagamento(), null, old.getDataAlvoSequencial(), parcMulta,
												false, 0, 0, atraso, parcelaAtual, fullName, parcPagas, atraso2,
												cobradorRow, local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, parcMulta, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, valor_recebido, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, 0, multasRowAux,
										pagamentoRow.getTyp(), parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}
							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							t.getTableView().getItems().remove(t.getTablePosition().getRow());

						} else {

							try {
								fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							// System.out.print ("aqui na mulkta 3");
							try {
								fachada.updateStatus(true, valor_recebido, idRow, 0, multasRowAux,
										pagamentoRow.getTyp(), parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}

							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t.getTableView().getItems().remove(t.getTablePosition().getRow());
						}
					}
				}

			} else if (pagamentoRow.getTyp().equals("PARCELAS e MULTAS")) {

				double valorDia = op.getValorDiario();
				double valorJuros = op.getValor_juros();

				String aux = (String) numeroParcelasRow;

				int parcelasRowAux = Integer.parseInt(aux.trim());

				String aux2 = (String) NumeroMultasRow;

				int multasRowAux = Integer.parseInt(aux2.trim());

				double valor_recebido = (valorDia * parcelasRowAux) + (valorJuros * multasRowAux);

				int parc = parcelasRowAux + parcelaAtual;

				atraso = (atraso - multasRowAux) - (parcelasRowAux - 1);

				if (atraso2 == 2) {
					if (parcelasRowAux == 1) {
						atraso2 = 2;
					}
				}

				if (atraso < 0) {
					atraso = 0;
				}

				int parcMulta;
				// int parcAtraso;

				if (multaRow != 0) {

					parcMulta = multaRow - multasRowAux;
					// parcAtraso = atraso - multasRowAux;
				} else {
					parcMulta = 0;
					// parcAtraso = 0;
				}

				if (parcelasRowAux == 0 || multasRowAux == 0) {
					// inserir alguma msg aqui futuramente pro usuario burro

				} else {
					if (parcelaAtual != 0) {
						if (parcelasRow != (parcelaAtual + parcelasRowAux - 1)) {
							// if (multaRow != 0) {

							if (parcMulta < 0) {
								parcMulta = 0;
							}

							System.out.println("entro");

							parcPagas = parcPagas + parcelasRowAux;
							
							int parc2 = parcelaAtual + parcelasRowAux;
							
							if(parc2 == parcelasRow){
								atraso2 = 1;
							}
							
							if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, parcMulta, false, 0, 0, atraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (op.getFlag() == 1 || op.getFlag() == 2) {

								if (old.getDataAlvoSequencial() == null) {
									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso, parc,
											fullName, parcPagas, atraso2, cobradorRow, null);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									if (sdf1.format(old.getDataInicialPagamento())
											.equals(sdf1.format(old.getDataAlvoSequencial()))) {

										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
												parc, fullName, parcPagas, atraso2, cobradorRow, local2);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									} else {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, old.getDataAlvoSequencial(), parcMulta,
												false, 0, 0, atraso, parc, fullName, parcPagas, atraso2, cobradorRow,
												local1);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
									&& diaSemanaM.equals("Sabado")) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local2, parcMulta, false, 0, 0, atraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso, parc,
										fullName, parcPagas, atraso2, cobradorRow, old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							try {
								fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, multasRowAux,
										pagamentoRow.getTyp(), parcelasRow);
							} catch (Exception e) {

								e.printStackTrace();
							}
							Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
							try {
								fachada.insert(log);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							t.getTableView().getItems().remove(t.getTablePosition().getRow());
							// } else {
							// faz nada
							// }
						} else {
							if ((multaRow - multasRowAux) != 0 && (multaRow - multasRowAux) > 0) {
								if (parcMulta < 0) {
									parcMulta = 0;
								}
								if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local3, parcMulta, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else if (op.getFlag() == 1 || op.getFlag() == 2) {

									if (old.getDataAlvoSequencial() == null) {
										Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
												old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso,
												0, fullName, parcelasRow, 0, cobradorRow, null);
										try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} else {

										if (sdf1.format(old.getDataInicialPagamento())
												.equals(sdf1.format(old.getDataAlvoSequencial()))) {

											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0,
													atraso, 0, fullName, parcelasRow, 0, cobradorRow, local2);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}

										} else {
											Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
													old.getTipo_pagamento(), null, old.getDataAlvoSequencial(),
													parcMulta, false, 0, 0, atraso, 0, fullName, parcelasRow, 0,
													cobradorRow, local1);
											try {
												fachada.insertStatus(newStatus);
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}

										}
									}

								} else if (old.getTipo_pagamento().equals("Segunda a Sabado")
										&& diaSemanaM.equals("Sabado")) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local2, parcMulta, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local1, parcMulta, false, 0, 0, atraso, 0,
											fullName, parcelasRow, 0, cobradorRow, old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								try {
									fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, multasRowAux,
											pagamentoRow.getTyp(), parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}
								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());

							} else {

								try {
									fachada.updateOperacaoClienteEstado(old.getId_operacao(), "Finalizado");
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									fachada.updateStatus(true, valor_recebido, idRow, parcelasRowAux, multasRowAux,
											pagamentoRow.getTyp(), parcelasRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								try {
									fachada.updateUno(parcelasRow, idRow);
								} catch (Exception e) {

									e.printStackTrace();
								}

								Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
								try {
									fachada.insert(log);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t.getTableView().getItems().remove(t.getTablePosition().getRow());
							}
						}
					} else {

						// faça nada
					}
				}

			} else if (pagamentoRow.getTyp().equals("NÃO PAGOU")) {

				try {
					fachada.updateStatus(true, 0, idRow, 0, 0, pagamentoRow.getTyp(), parcelasRow);
				} catch (Exception e) {

					e.printStackTrace();
				}

				int opAtraso = 0;
				try {
					OperacaoCliente opCliente = fachada.getOperacaoCliente(old.getId_operacao());
					opAtraso = opCliente.getQuantidade_atraso();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				opAtraso = opAtraso + 1;
				
				if (parcelaAtual == 0) {
					try {
						fachada.updateOperacaoCliente(old.getId_operacao(), opAtraso - 1);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					try {
						fachada.updateOperacaoCliente(old.getId_operacao(), opAtraso);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				

				int multaResu = multaRow + 1;
				atraso = atraso + 1;
				// atraso2 = 1;
				String nome = "";
				try {
					nome = fachada.getStatusNome(old.getId_operacao());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (parcelaAtual == parcelasRow){
					atraso2 = 1;
				}

				if (parcelaAtual == 0) {
					
					if (parcelaAtual == parcelasRow){
						atraso2 = 1;
					}
					
					System.out.println("entrou no multa");
					if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

						Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
								null, local3, multaRow, false, 0, 0, atraso - 1, parcelaAtual, fullName, parcPagas,
								atraso2, cobradorRow, old.getDataAlvoSequencial());
						

						/*
						 * opAtraso);
						 * fachada.updateOperacaoCliente(old.getId_operacao(
						 * ), opAtraso); } catch (Exception e1) { // TODO
						 * Auto-generated catch block e1.printStackTrace();
						 * }
						 */
						try {
							fachada.insertStatus(newStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (op.getFlag() == 1 || op.getFlag() == 2) {

						if (old.getDataAlvoSequencial() == null) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
									old.getTipo_pagamento(), null, local1, multaRow  , false, 0, 0, atraso - 1, 
									parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);
							
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							
							if (sdf1.format(old.getDataInicialPagamento())
									.equals(sdf1.format(old.getDataAlvoSequencial()))) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local4, multaRow , false, 0, 0, atraso -1,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local1);
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else {
								
								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local4, multaRow , false, 0, 0, atraso - 1,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
										old.getDataAlvoSequencial());
								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					} else if (old.getTipo_pagamento().equals("Segunda a Sabado") && diaSemanaM.equals("Sabado")) {
						
						Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
								null, local2, multaRow , false, 0, 0, atraso-1, parcelaAtual, fullName, parcPagas,
								atraso2, cobradorRow, old.getDataAlvoSequencial());
						/*
						 * try { // System.out.println("ATRASO AQUI O" +
						 * opAtraso);
						 * fachada.updateOperacaoCliente(old.getId_operacao(
						 * ), opAtraso); } catch (Exception e1) { // TODO
						 * Auto-generated catch block e1.printStackTrace();
						 * }
						 */
						try {
							fachada.insertStatus(newStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						
						Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
								null, local1, multaRow , false, 0, 0, atraso-1, parcelaAtual, fullName, parcPagas,
								atraso2, cobradorRow, old.getDataAlvoSequencial());
						/*
						 * try { // System.out.println("ATRASO AQUI O" +
						 * opAtraso);
						 * fachada.updateOperacaoCliente(old.getId_operacao(
						 * ), opAtraso); } catch (Exception e1) { // TODO
						 * Auto-generated catch block e1.printStackTrace();
						 * }
						 */
						try {
							fachada.insertStatus(newStatus);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					
					Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
					try {
						fachada.insert(log);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					t.getTableView().getItems().remove(t.getTablePosition().getRow());

				} else {

					if (nome.equals("NÃO PAGOU")) {
						if (atraso2 == 2) {
							multaResu = multaResu + 1;
						}

						atraso2 = 1;
						
						if (parcelaAtual == parcelasRow){
							atraso2 = 1;
						}

						if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local3, multaResu, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());

							/*
							 * opAtraso);
							 * fachada.updateOperacaoCliente(old.getId_operacao(
							 * ), opAtraso); } catch (Exception e1) { // TODO
							 * Auto-generated catch block e1.printStackTrace();
							 * }
							 */
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (op.getFlag() == 1 || op.getFlag() == 2) {
							
							

							if (old.getDataAlvoSequencial() == null) {

								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local1, multaResu, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);

								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								if (sdf1.format(old.getDataInicialPagamento())
										.equals(sdf1.format(old.getDataAlvoSequencial()))) {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local4, multaResu, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local1);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								} else {

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local4, multaResu, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}

						} else if (old.getTipo_pagamento().equals("Segunda a Sabado") && diaSemanaM.equals("Sabado")) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local2, multaResu, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							/*
							 * try { // System.out.println("ATRASO AQUI O" +
							 * opAtraso);
							 * fachada.updateOperacaoCliente(old.getId_operacao(
							 * ), opAtraso); } catch (Exception e1) { // TODO
							 * Auto-generated catch block e1.printStackTrace();
							 * }
							 */
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local1, multaResu, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							/*
							 * try { // System.out.println("ATRASO AQUI O" +
							 * opAtraso);
							 * fachada.updateOperacaoCliente(old.getId_operacao(
							 * ), opAtraso); } catch (Exception e1) { // TODO
							 * Auto-generated catch block e1.printStackTrace();
							 * }
							 */
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					} else {
						atraso2 = 2;
						
						if (parcelaAtual == parcelasRow){
							atraso2 = 1;
						}
						if (old.getTipo_pagamento().equals("Segunda a Sexta") && diaSemanaM.equals("Sexta")) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local3, multaRow, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());

							/*
							 * try { // System.out.println("ATRASO AQUI O" +
							 * opAtraso);
							 * fachada.updateOperacaoCliente(old.getId_operacao(
							 * ), opAtraso); } catch (Exception e1) { // TODO
							 * Auto-generated catch block e1.printStackTrace();
							 * }
							 */
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (op.getFlag() == 1 || op.getFlag() == 2) {

							atraso2 = 1;
							
							if (parcelaAtual == parcelasRow){
								atraso2 = 1;
							}

							if (old.getDataAlvoSequencial() == null) {
								Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
										old.getTipo_pagamento(), null, local3, multaRow, false, 0, 0, atraso,
										parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, null);

								try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {

								if (sdf1.format(old.getDataInicialPagamento())
										.equals(sdf1.format(old.getDataAlvoSequencial()))) {
									atraso2 = 2;

									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow, local1);
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								} else {
									Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(),
											old.getTipo_pagamento(), null, local4, multaRow, false, 0, 0, atraso,
											parcelaAtual, fullName, parcPagas, atraso2, cobradorRow,
											old.getDataAlvoSequencial());
									try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}

						} else if (old.getTipo_pagamento().equals("Segunda a Sabado") && diaSemanaM.equals("Sabado")) {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local2, multaRow, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							/*
							 * try { // System.out.println("ATRASO AQUI O" +
							 * opAtraso);
							 * fachada.updateOperacaoCliente(old.getId_operacao(
							 * ), opAtraso); } catch (Exception e1) { // TODO
							 * Auto-generated catch block e1.printStackTrace();
							 * }
							 */
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {

							Status newStatus = new Status(parcelasRow, 0, old.getId_operacao(), old.getTipo_pagamento(),
									null, local1, multaRow, false, 0, 0, atraso, parcelaAtual, fullName, parcPagas,
									atraso2, cobradorRow, old.getDataAlvoSequencial());
							/*
							 * try { // System.out.println("ATRASO AQUI O" +
							 * opAtraso);
							 * fachada.updateOperacaoCliente(old.getId_operacao(
							 * ), opAtraso); } catch (Exception e1) { // TODO
							 * Auto-generated catch block e1.printStackTrace();
							 * }
							 */
							try {
								fachada.insertStatus(newStatus);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}

					Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
					try {
						fachada.insert(log);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					t.getTableView().getItems().remove(t.getTablePosition().getRow());

				}
			}

			else if (pagamentoRow.getTyp().equals("FECHAR Op. D.")) {

				try {
					fachada.updateStatus(true, 0, idRow, 0, 0, pagamentoRow.getTyp(), parcelasRow);
				} catch (Exception e) {

					e.printStackTrace();
				}

				try {

					fachada.updateOperacaoClienteEstado(old.getId_operacao(), "EM DIVIDA");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
				try {
					fachada.insert(log);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				t.getTableView().getItems().remove(t.getTablePosition().getRow());

			} else if (pagamentoRow.getTyp().equals("PERDOAR DIVIDA")) {

				try {
					fachada.updateStatus(true, 0, idRow, 0, 0, pagamentoRow.getTyp(), parcelasRow);
				} catch (Exception e) {

					e.printStackTrace();
				}

				try {

					fachada.updateOperacaoClienteEstado(old.getId_operacao(), "DIVIDA PERDOADA");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Log log = new Log(idRow, old.getId_operacao(), pessoa.getId(), dataNascimento1);
				try {
					fachada.insert(log);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				t.getTableView().getItems().remove(t.getTablePosition().getRow());

			} else {

			}

		}

		);

		// tcPagamento.setEditable(false);
		// tbStatus.setEditable(false);

		tbStatus.getColumns().addAll(tcID, tcCliente, tcCobrador, tcParcelas, tcParcelasAtual, tcAtraso, tcMulta,
				tcNumeroMulta, tcNumeroParcelas, tcPagamento);

		// tbStatus.getItems().clear();

	}
	/*
	 * @FXML public void btnEventoCadastrarClienteMoved (ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML public void btnEventoCadastrarFuncionarioMoved (ActionEvent event)
	 * {
	 * 
	 * }
	 * 
	 * @FXML public void btnEventoCadastrarOperacaoMoved (ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML public void btnEventoListagemMoved (ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML public void btnEventoGerarRelatorioMoved (ActionEvent event) {
	 * 
	 * }
	 * 
	 * @FXML public void btnEventoStatusDiarioMoved (ActionEvent event) {
	 * 
	 * }
	 */

	public void initialize(URL arg0, ResourceBundle arg1) {

		new AutoCompleteComboBoxListener(ccCobrador);

		btFerias.setVisible(false);

		ArrayList<Funcionario> funcionariosCobrador = new ArrayList();
		// ArrayList<String> funcionarioCobradorNome = new ArrayList();
		// Pessoa funcionarioCobrador = null;

		try {
			funcionariosCobrador = fachada.getFuncionariosCobradores();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		funcionariosCobrador.add(new Funcionario("", "", "", "", "", 0, "", "TODOS"));
		// funcionarioCobradorNome.add("TODOS");

		// System.out.println(funcionariosVendedores.get(1).getNome());
		ccCobrador.getItems().addAll(funcionariosCobrador);
		ccCobrador.getSelectionModel().getSelectedIndex();
		ccCobrador.setCellFactory(new Callback<ListView<Funcionario>, ListCell<Funcionario>>() {

			@Override
			public ListCell<Funcionario> call(ListView<Funcionario> p) {

				final ListCell<Funcionario> cell = new ListCell<Funcionario>() {

					@Override
					protected void updateItem(Funcionario t, boolean bln) {
						super.updateItem(t, bln);

						if (t != null) {
							setText(t.getNome() + " ");
						} else {
							setText(null);
						}
					}

				};

				return cell;
			}
		});

		/*
		 * for (int i = 0; i < funcionariosCobrador.size(); i++) {
		 * 
		 * try { funcionarioCobrador =
		 * fachada.getPessoaId(funcionariosCobrador.get(i).getId_funcionario());
		 * } catch (RepositoryException | PessoaNaoEncontradaException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * funcionarioCobradorNome .add(funcionarioCobrador.getNome() +
		 * " / ID: " + funcionariosCobrador.get(i).getId_funcionario()); }
		 * 
		 * // System.out.println(funcionarioCobradorNome.size());
		 * 
		 * ccCobrador.getItems().addAll(funcionarioCobradorNome);
		 * ccCobrador.valueProperty().addListener(new ChangeListener<String>() {
		 * 
		 * @Override public void changed(ObservableValue ov, String t, String
		 * t1) {
		 * 
		 * } });
		 */

	}

	public void start(Stage palcoTelaInicial, Pessoa pessoa) throws Exception {

		this.palcoTelaInicial = palcoTelaInicial;
		this.pessoa = pessoa;

		try {
			// System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaInicial.fxml"));

			// System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// Definir a cena para a janela
			palcoTelaInicial.setScene(cena);

			palcoTelaInicial.setTitle("JK");

			palcoTelaInicial.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			// palcoTelaInicial.setResizable(false);

			// Mostrar a janela/tela
			palcoTelaInicial.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	class ComboBoxEditingCell extends TableCell<Status, Typ> {

		private ComboBox<Typ> comboBox;

		private ComboBoxEditingCell() {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createComboBox();
				setText(null);
				setGraphic(comboBox);
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText(getTyp().getTyp());
			setGraphic(null);
		}

		@Override
		public void updateItem(Typ item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (comboBox != null) {
						comboBox.setValue(getTyp());
					}
					setText(getTyp().getTyp());
					setGraphic(comboBox);
				} else {
					setText(getTyp().getTyp());
					setGraphic(null);
				}
			}
		}

		private void createComboBox() {
			comboBox = new ComboBox<>(typData);
			comboBoxConverter(comboBox);
			comboBox.valueProperty().set(getTyp());
			comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			comboBox.setOnAction((e) -> {
				// System.out.println("Committed: " +
				// comboBox.getSelectionModel().getSelectedItem());
				commitEdit(comboBox.getSelectionModel().getSelectedItem());
			});

			// comboBox.focusedProperty().addListener((ObservableValue<? extends
			// Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			// if (!newValue) {
			// commitEdit(comboBox.getSelectionModel().getSelectedItem());
			// }
			// });
		}

		private void comboBoxConverter(ComboBox<Typ> comboBox) {
			// Define rendering of the list of values in ComboBox drop down.
			comboBox.setCellFactory((c) -> {
				return new ListCell<Typ>() {
					@Override
					protected void updateItem(Typ item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setText(null);
						} else {
							setText(item.getTyp());
						}
					}
				};
			});
		}

		private Typ getTyp() {
			return getItem() == null ? new Typ("") : getItem();
		}
	}

	public static int getCodPercaFutura() {
		return codPercaFutura;
	}

	public static void setCodPercaFutura(int codPercaFutura) {
		ControleTelaInicial.codPercaFutura = codPercaFutura;
	}

	public static int getCodRelatorioGeral() {
		return codRelatorioGeral;
	}

	public static void setCodRelatorioGeral(int codRelatorioGeral) {
		ControleTelaInicial.codRelatorioGeral = codRelatorioGeral;
	}

	public static int getCodRelatorioEmpDiario() {
		return codRelatorioEmpDiario;
	}

	public static void setCodRelatorioEmpDiario(int codRelatorioEmpDiario) {
		ControleTelaInicial.codRelatorioEmpDiario = codRelatorioEmpDiario;
	}

	public static int getCodRelatorioClienteResp() {
		return codRelatorioClienteResp;
	}

	public static void setCodRelatorioClienteResp(int codRelatorioClienteResp) {
		ControleTelaInicial.codRelatorioClienteResp = codRelatorioClienteResp;
	}

	public static int getCodRelatorioResponsavelEspec() {
		return codRelatorioResponsavelEspec;
	}

	public static void setCodRelatorioResponsavelEspec(int codRelatorioResponsavelEspec) {
		ControleTelaInicial.codRelatorioResponsavelEspec = codRelatorioResponsavelEspec;
	}

	public static int getCodRelatorioClienteResponsavelGeral() {
		return codRelatorioClienteResponsavelGeral;
	}

	public static void setCodRelatorioClienteResponsavelGeral(int codRelatorioClienteResponsavelGeral) {
		ControleTelaInicial.codRelatorioClienteResponsavelGeral = codRelatorioClienteResponsavelGeral;
	}

	public static int getCodRelatorioClienteResponsavelGeralFinal() {
		return codRelatorioClienteResponsavelGeralFinal;
	}

	public static void setCodRelatorioClienteResponsavelGeralFinal(int codRelatorioClienteResponsavelGeralFinal) {
		ControleTelaInicial.codRelatorioClienteResponsavelGeralFinal = codRelatorioClienteResponsavelGeralFinal;
	}

	public static int getCodCodReceber() {
		return codCodReceber;
	}

	public static void setCodCodReceber(int codCodReceber) {
		ControleTelaInicial.codCodReceber = codCodReceber;
	}
}
