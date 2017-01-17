package controleView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ControleTelaConfirmarFuncionario extends Application implements Initializable{

	public static Stage palcoTelaConfirmarFuncionario = null;
	
	//@FXML
//	Button bConfirmarOp;
	
	
	/* public void eventoBtnConfirmarOp () throws JRException{
		try{
			palcoTelaConfirmarOperacao.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}*/
	
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//bConfirmarOp.setFocusTraversable(false);
	}
	
	

	public void start(Stage palcoTelaConfirmarFuncionario) throws Exception {
		
		palcoTelaConfirmarFuncionario = palcoTelaConfirmarFuncionario;
		
		
		
		try {		
			//System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaConfirmarFuncionario.fxml"));
		
			
			
			//System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			palcoTelaConfirmarFuncionario.setScene(cena);
			
			palcoTelaConfirmarFuncionario.setTitle("JK");
			
			palcoTelaConfirmarFuncionario.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			//palcoTelaInicial.setResizable(false);
			
			// Mostrar a janela/tela
			palcoTelaConfirmarFuncionario.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
