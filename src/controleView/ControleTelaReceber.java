package controleView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Operacao;
import negocio.Status;

public class ControleTelaReceber  extends Application implements Initializable {
	
	public static Stage palcoTelaReceber = null;
	EletroJam fachada = EletroJam.getInstance();
	
	private final ObservableList<Status> data = FXCollections.observableArrayList();
	
	@FXML
	TableView<Status> cobradorTable;

	@FXML
	TableColumn<Status, String> nomeColum;
	@FXML

	TableColumn<Status, Date> dataColum;

	@FXML
	TableColumn<Status, Double> receberColum;
	
	@FXML
	Label receberVal;
	
	@FXML
	Button btnPesquisa;
	
	@FXML
	Button btnRelatorio;
	
	@FXML
	public void eventoBtnRelatorio(ActionEvent event){
		
	}
	
	@FXML
	public void eventoBtnPesquisa(ActionEvent event){
		cobradorTable.getItems().clear();
				
		
		ArrayList<Status> listarStatus = new ArrayList();

		try {
			listarStatus = fachada.AllStatusReceber();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < listarStatus.size(); i++) {
			data.add(new Status(listarStatus.get(i).getCliente(), listarStatus.get(i).getDataInicialPagamento(), listarStatus.get(i).getReceber() ));
		
		}

		cobradorTable.setItems(data);

		nomeColum.setStyle( "-fx-alignment: CENTER;");
		nomeColum.setCellValueFactory(new PropertyValueFactory<Status, String>("cliente"));

		dataColum.setStyle( "-fx-alignment: CENTER;");
		dataColum.setCellValueFactory(new PropertyValueFactory<Status, Date>("dataInicialPagamento"));
		
		receberColum.setStyle( "-fx-alignment: CENTER;");
		receberColum.setCellValueFactory(new PropertyValueFactory<Status, Double>("receber"));
		
		
		Double receber = 0.0;
		
		try {
			receber = round(fachada.getValorReceber(),2);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		receberVal.setText( String.valueOf(receber) ); 
	}
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		receberVal.setText("");
	}
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public void start(Stage palcoTelaReceber) throws Exception {
		this.palcoTelaReceber = palcoTelaReceber;
		
		
		try {	
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaReceber.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaReceber.setScene(cena);
			
			palcoTelaReceber.setTitle("A Receber");
			
			palcoTelaReceber.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaReceber.setResizable(false);

			palcoTelaReceber.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		        	  ControleTelaInicial.setCodCodReceber(0);
		          }
		      });        
			palcoTelaReceber.close();

			palcoTelaReceber.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
