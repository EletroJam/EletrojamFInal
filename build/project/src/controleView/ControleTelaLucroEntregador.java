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


public class ControleTelaLucroEntregador extends Application implements Initializable {

	public static Stage palcoTelaLucroEntregador = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Operacao> data = FXCollections.observableArrayList();

	@FXML
	DatePicker dInicial;
	
	@FXML
	DatePicker dFinal;


	@FXML
	TableView<Operacao> entregadorTable;

	@FXML
	TableColumn<Operacao, String> nomeColum;
	@FXML

	TableColumn<Operacao, Double> valorColum;

	

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
			fachada.relatorioComissaoCobradorEntregador(dataIni, dataFim, "relatorios/entregadorLucro.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {

		entregadorTable.getItems().clear();

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

		ArrayList<Operacao> listarEntregadores = new ArrayList();


		try {
			listarEntregadores = fachada.getEntregadorComissao(dataIni, dataFim);
			
			
			
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarEntregadores.size(); i++) {

			data.add(new Operacao(listarEntregadores.get(i).getNomeentregador(), listarEntregadores.get(i).getTotal_entregador() ));
		}

		entregadorTable.setItems(data);

		nomeColum.setStyle( "-fx-alignment: CENTER;");
		nomeColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nomeentregador"));

		valorColum.setStyle( "-fx-alignment: CENTER;");
		valorColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("total_entregador"));
		
		
	}


	public void initialize(URL arg0, ResourceBundle arg1) {


	}


	public void start(Stage palcoTelaLucroEntregador) throws Exception {
		this.palcoTelaLucroEntregador = palcoTelaLucroEntregador;
		
		
		try {	
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaLucroEntregador.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaLucroEntregador.setScene(cena);
			
			palcoTelaLucroEntregador.setTitle("Comissão Entregador");
			
			palcoTelaLucroEntregador.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaLucroEntregador.setResizable(false);

			palcoTelaLucroEntregador.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		              ControleTelaInicial.setCodLucroEntregador(0);
		          }
		      });        
			palcoTelaLucroEntregador.close();

			palcoTelaLucroEntregador.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}