package controleView;

import java.net.URL;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Rota;

public class ControleTelaCadastroRota extends Application implements Initializable{
	
	public static Stage palcoTelaRota = null;
	EletroJam fachada = EletroJam.getInstance();
	
	
	@FXML
	Button btCadastrar;
	
	@FXML
	Label lRotaJaExistente;
	
	@FXML
	TextField tfRota;
	
	@FXML
	public void eventoBtnEntrar (ActionEvent event) throws RepositoryException{
		lRotaJaExistente.setVisible(false);
		
		String Tfrota = tfRota.getText().replace(".", " ").replace("-", " ");
		
		boolean b = fachada.hasRota(Tfrota);
		
		if (b == true){
			lRotaJaExistente.setVisible(true);
		}else{
			Rota rota = new Rota(Tfrota.toUpperCase());
			fachada.insertRota(rota);
			
			ControleTelaConfirmarFuncionario confirm = new ControleTelaConfirmarFuncionario();
			
			tfRota.setText("");
			
			 try {
	    			//Abre a tela de Cadastro de Grupos
	    			 
	    			 //telaInicial.start(new Stage());
	    			 
				 	confirm.start(new Stage());
	    			
	    			 
	    	} catch (Exception e) {
	    			e.printStackTrace();
	    				
	    	}
			// ControleTelaInicial.setCodFuncio(0);
		}
		
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage palcoTelaRo) throws Exception {
		palcoTelaRota = palcoTelaRo;
		
		try {		
			
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaCadastroRota.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaRota.setScene(cena);
			
			palcoTelaRota.setTitle("Cadastro de Rota");
			
			palcoTelaRota.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaRota.setResizable(false);
			/*
			Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 
	         // simple displays ImageView the image as is
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
			*/
			
			
	        //imageView = new ImageView(image);
			
			palcoTelaRota.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		              ControleTelaInicial.setCodRota(0);
		          }
		      });        
			palcoTelaRota.close();
			
		// Mostrar a janela/tela
			palcoTelaRota.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}



	

}
