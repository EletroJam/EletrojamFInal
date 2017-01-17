package controleView;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Operacao;
import net.sf.jasperreports.engine.JRException;

public class ControleTelaPercasFutura extends Application implements Initializable {
	
	public static Stage palcoTelaPercas = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Operacao> data = FXCollections.observableArrayList();
	
	@FXML
	DatePicker dInicial;
	
	@FXML
	DatePicker dFinal;
	
	@FXML
	Label lValor;
	
	@FXML
	TextField tRec;
	
	@FXML
	Button btnRelatorio;
	
	@FXML
	Button btnPesquisar;
	
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
			fachada.relatorioComissaoCobradorEntregador2(dataIni, dataFim, "relatorios/percasFutura.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {
		
		lValor.setVisible(false);
		tRec.setVisible(false);
		double valor = 0;
		
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
			valor = fachada.getTotalPercas(dataIni, dataFim);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lValor.setVisible(true);
		tRec.setVisible(true);
		
		tRec.setText(String.valueOf(valor));
		
		
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

//lembrar muda op de tela fechamento
	public void start(Stage palcoTelaPercas) throws Exception {
		this.palcoTelaPercas = palcoTelaPercas;
		
		
		try {	
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaPercasFutura.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaPercas.setScene(cena);
			
			palcoTelaPercas.setTitle("Percas Futura");
			
			palcoTelaPercas.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaPercas.setResizable(false);

			palcoTelaPercas.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		        	  ControleTelaInicial.setCodPercaFutura(0);;
		          }
		      });        
			palcoTelaPercas.close();

			palcoTelaPercas.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
