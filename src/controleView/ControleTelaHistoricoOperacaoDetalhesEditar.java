package controleView;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Cliente;
import negocio.Status;

public class ControleTelaHistoricoOperacaoDetalhesEditar extends Application implements Initializable{
	
	public static Stage palcoTelaHistoricoInicialEd = null;
	//private static Data dataD;
	public static Status st;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Cliente> data = FXCollections.observableArrayList();
	private static int codHistoricoClienteOpDConfirm =0;
	//public static int codHistoricoClienteOpDEd = 0;
	
	public static int getCodHistoricoClienteOpDConfirm() {
		return codHistoricoClienteOpDConfirm;
	}

	public static void setCodHistoricoClienteOpDConfirm(int codHistoricoClienteOpDConfirm) {
		ControleTelaHistoricoOperacaoDetalhesEditar.codHistoricoClienteOpDConfirm = codHistoricoClienteOpDConfirm;
	}

	public void initialize(URL location, ResourceBundle resources) {
		
		if(st.getDataInicialPagamento() != null ){
			Date input = st.getDataInicialPagamento();
			LocalDate date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			lData.setValue(date);
		}
		
	}
	
	@FXML
	private DatePicker lData;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	public void eventoBtnEditar(ActionEvent event){
		
		if(codHistoricoClienteOpDConfirm == 0){
			codHistoricoClienteOpDConfirm = 1;
			
			LocalDate dNascimento = null;
			
			if(lData.getValue() == null ){
				System.out.println("aqui");
			}else{
				
				dNascimento = lData.getValue();
				System.out.println(dNascimento);
			}
			
			Date dataNascimento;

			if(dNascimento == null ){
				//java.util.Date dataAtual = new java.util.Date(); 
				//java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime()); 
				//java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime()); 
				dataNascimento =  null;
			}else{
				
				dataNascimento = Date.valueOf(dNascimento);
				
			}
			
			st.setDataInicialPagamento(dataNascimento);
			//System.out.println(st.getDataInicialPagamento() + " pagamneto que via banco " + st.getId());
			
			try {
				fachada.updateDataInicial(st.getId(), st);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {

				
				new ControleTelaHistoricoOperacaoDetalhesEditarConfirmar().start(new Stage());
						

			} catch (Exception e) {
						// TODO Auto-generated catch block
				e.printStackTrace();
				}

			} else {
					// Nothing selected
				System.out.println("fail");
			}
		
		
		
	}
	
	public void start(Stage primaryStage, Status sta) throws Exception {
		
		this.palcoTelaHistoricoInicialEd = primaryStage;
		this.st = sta;
		
		//this.pessoa = pessoa;
		
		
		try {		
			
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaHistoricoOperacaoDetalhesEditar.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaHistoricoInicialEd.setScene(cena);
			
			palcoTelaHistoricoInicialEd.setTitle("Editar data");
			
			palcoTelaHistoricoInicialEd.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaHistoricoInicialEd.setResizable(false);
			/*
			Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 
	         // simple displays ImageView the image as is
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
			*/
			
			
	        //imageView = new ImageView(image);
			
			palcoTelaHistoricoInicialEd.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		        	  ControleTelaHistoricoOperacaoDetalhes.setCodHistoricoClienteOpD(0);
		          }
		      });        
			palcoTelaHistoricoInicialEd.close();
			
			
		// Mostrar a janela/tela
			palcoTelaHistoricoInicialEd.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
