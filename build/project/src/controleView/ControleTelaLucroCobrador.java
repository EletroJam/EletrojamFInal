package controleView;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import fachada.EletroJam;
import javafx.application.Application;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Operacao;
import net.sf.jasperreports.engine.JRException;

public class ControleTelaLucroCobrador extends Application implements Initializable {

	public static Stage palcoTelaLucroCobrador = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Operacao> data = FXCollections.observableArrayList();

	@FXML
	DatePicker dInicial;
	
	@FXML
	DatePicker dFinal;


	@FXML
	TableView<Operacao> cobradorTable;

	@FXML
	TableColumn<Operacao, String> nomeColum;
	@FXML

	TableColumn<Operacao, Double> diarioColum;

	@FXML
	TableColumn<Operacao, Double> multa_diariaColum;
	
	@FXML
	TableColumn<Operacao, Double> totalColum;

	@FXML
	TableColumn<Operacao, Integer> cobrancaColum;
	
	@FXML
	Button btnPesquisar;
	
	@FXML
	Button btnRelatorio;
	
	@FXML
	public void eventoBtnRelatorio(ActionEvent event) {
		LocalDate ini = null;
		ini = dInicial.getValue();
		
		LocalDate fim = null;
		fim = dFinal.getValue();
		
		Date dataIni;
		Date dataFim;
		
		
		if(ini == null ){
			dataIni = null;
		}else{
			
			dataIni = Date.valueOf(ini);
		}

		if(fim == null ){
			dataFim = null;
		}else{
			
			dataFim = Date.valueOf(fim);
		}
		
		try {
			fachada.relatorioComissaoCobradorEntregador(dataIni, dataFim, "relatorios/cobradorLucro.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {

		cobradorTable.getItems().clear();

		LocalDate ini = null;
		ini = dInicial.getValue();
		
		LocalDate fim = null;
		fim = dFinal.getValue();
		
		Date dataIni;
		Date dataFim;
		
		
		if(ini == null ){
			dataIni = null;
		}else{
			
			dataIni = Date.valueOf(ini);
		}

		if(fim == null ){
			dataFim = null;
		}else{
			
			dataFim = Date.valueOf(fim);
		}

		ArrayList<Operacao> listarCobradores = new ArrayList<Operacao>();


		try {
			listarCobradores = fachada.getCobradoresComissao(dataIni, dataFim, false);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarCobradores.size(); i++) {

			data.add(new Operacao(listarCobradores.get(i).getNomecobrador(), listarCobradores.get(i).getDiario(),
			listarCobradores.get(i).getMulta_diaria(), listarCobradores.get(i).getTotalcobrador() ));
		}

		cobradorTable.setItems(data);

		nomeColum.setStyle( "-fx-alignment: CENTER;");
		nomeColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nomecobrador"));

		diarioColum.setStyle( "-fx-alignment: CENTER;");
		diarioColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("diario"));
		
		multa_diariaColum.setStyle( "-fx-alignment: CENTER;");
		multa_diariaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("multa_diaria"));
		
		//totalColum.setStyle( "-fx-alignment: CENTER;");
		//totalColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("totalcobrador"));
		
	}


	public void initialize(URL arg0, ResourceBundle arg1) {


	}


	public void start(Stage palcoTelaLucroCobrador) throws Exception {
		this.palcoTelaLucroCobrador = palcoTelaLucroCobrador;
		
		
		try {	
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaLucroCobrador.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaLucroCobrador.setScene(cena);
			
			palcoTelaLucroCobrador.setTitle("Andamento Cobrador");
			
			palcoTelaLucroCobrador.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaLucroCobrador.setResizable(false);

			palcoTelaLucroCobrador.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		              ControleTelaInicial.setCodLucroCobrador(0);
		          }
		      });        
			palcoTelaLucroCobrador.close();

			palcoTelaLucroCobrador.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}