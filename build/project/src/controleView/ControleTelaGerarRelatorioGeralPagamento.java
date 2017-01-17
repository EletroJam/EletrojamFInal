package controleView;

import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import negocio.Funcionario;
import negocio.Pessoa;
import negocio.Rota;
import net.sf.jasperreports.engine.JRException;
import repositorios.RepositorioStatusDB;
import util.AutoCompleteComboBoxListener;

public class ControleTelaGerarRelatorioGeralPagamento extends Application implements Initializable {

	public static Stage palcoTelaCobranca = null;
	EletroJam fachada = EletroJam.getInstance();

	@FXML
	DatePicker dData;

	@FXML
	ComboBox<Funcionario> cCobrador;

	@FXML
	Button bGerar;

	@FXML
	ComboBox<String> cRota;

	public void eventoBtnGerar(ActionEvent event) throws JRException {

		 boolean controlCobradorIdNome = false;
		//String cobradorNome = null;
		//cobradorNome = cCobrador.getValue();
		
		String cNome = "";
		
		if(cCobrador.getValue().getNome().equals("TODOS")){
			cNome = "Todos";
			controlCobradorIdNome = true;
		}

		LocalDate dNascimento = null;
		dNascimento = dData.getValue();

		// String rotaCob = null;
		// rotaCob = cRota.getValue();

		Date dataNascimento;

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (dNascimento == null) {
			java.util.Date dataAtual = new java.util.Date();
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			dataNascimento = dataSQL;
		} else {

			dataNascimento = Date.valueOf(dNascimento);
			// System.out.println(dataNascimento);
		}

		Calendar c = new GregorianCalendar();
		c.setTime(dataNascimento);

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

		if (diaSemana == "Domingo") {

			DateTime date = DateTime.parse(sdf2.format(dataNascimento),
					DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));

			org.joda.time.DateTime jodal1 = date;
			// System.out.println(jodal1+
			// "JODALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			// org.joda.time.LocalDate localaux1 =
			// jodal1.toDateTime().toLocalDate();

			org.joda.time.DateTime joda1 = new org.joda.time.DateTime(jodal1.minusDays(1));
			String auxl1 = joda1.toLocalDate().toString("yyyy-MM-dd");

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
			// System.out.println(local11);

			// File f = new File("relatorios/CobrancaDomingo.jasper");

			ArrayList<Double> a = new ArrayList<>();

			if (cCobrador.getValue().getNome().equals("TODOS")) {

				try {
					a = fachada.getSaldoEDiarioTodos(cNome, dataNascimento);
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {
					a = fachada.getSaldoEDiario(cCobrador.getValue().getNome(), dataNascimento);
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// 0 é tootal e 1 é multa
			String resultado = String.format("%.2f", a.get(0));
			String resultado2 = String.format("%.2f", a.get(1));
			
			if(controlCobradorIdNome == true){
				fachada.geraRelatorioCobranca(cNome, local11, "relatorios/cobradorLucroFinalDomingo.jasper",
						"n tem mais", resultado, resultado2);
			}else{
				fachada.geraRelatorioCobranca(cCobrador.getValue().getNome(), local11, "relatorios/cobradorLucroFinalDomingo.jasper",
						"n tem mais", resultado, resultado2);
			}

			

		} else {

			ArrayList<Double> a = new ArrayList<>();

			if (cCobrador.getValue().getNome().equals("TODOS")) {
				
				try {
					a = fachada.getSaldoEDiarioTodos(cNome, dataNascimento);
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

				try {
					a = fachada.getSaldoEDiario(cCobrador.getValue().getNome(), dataNascimento);
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			String resultado = String.format("%.2f", a.get(0));
			String resultado2 = String.format("%.2f", a.get(1));
			
			if(controlCobradorIdNome == true){
				fachada.geraRelatorioCobranca(cNome, dataNascimento, "relatorios/cobradorLucroFinal.jasper",
						"n tem mais", resultado, resultado2);
			}else{
				fachada.geraRelatorioCobranca(cCobrador.getValue().getNome(), dataNascimento, "relatorios/cobradorLucroFinal.jasper",
						"n tem mais", resultado, resultado2);
			}

			

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		new AutoCompleteComboBoxListener(cCobrador);
		ArrayList<Funcionario> funcionariosCobrador = new ArrayList();
		//ArrayList<String> funcionarioCobradorNome = new ArrayList();
		//Pessoa funcionarioCobrador = null;

		cRota.setVisible(false);

		EletroJam fachada = EletroJam.getInstance();

		try {
			funcionariosCobrador = fachada.getFuncionariosCobradores();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		cCobrador.getItems().add((new Funcionario("", "", "", "", "", 0, "", "TODOS")));
		

		
				

	}

	@Override
	public void start(Stage palcoTelaCobranca1) throws Exception {
		// TODO Auto-generated method stub
		palcoTelaCobranca = palcoTelaCobranca1;

		try {

			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaGerarCobrancaFinal.fxml"));

			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// origemTela.getStylesheets().add("origemTela.css");

			// cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaCobranca.setScene(cena);

			palcoTelaCobranca.setTitle("Gerar Relatorio de Cobrança");

			palcoTelaCobranca.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			palcoTelaCobranca.setResizable(false);
			/*
			 * Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 * 
			 * // simple displays ImageView the image as is ImageView iv1 = new
			 * ImageView(); iv1.setImage(image);
			 */

			// imageView = new ImageView(image);

			// Mostrar a janela/tela

			palcoTelaCobranca.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaInicial.setCodRelatorioClienteResponsavelGeralFinal(0);
				}
			});
			palcoTelaCobranca.close();

			// Mostrar a janela/tela
			palcoTelaCobranca.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
