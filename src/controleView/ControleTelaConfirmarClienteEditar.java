package controleView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControleTelaConfirmarClienteEditar extends Application implements Initializable {
	public static Stage palcoTelaConfirmarClienteEdit = null;
	
	
	public void start(Stage palcoTelaConfirmarClienteEdit) throws Exception {
		
		this.palcoTelaConfirmarClienteEdit = palcoTelaConfirmarClienteEdit;
		
		
		
		try {		
			//System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaConfimarClienteEdit.fxml"));
		
			
			
			//System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			palcoTelaConfirmarClienteEdit.setScene(cena);
			
			palcoTelaConfirmarClienteEdit.setTitle("JK");
			
			palcoTelaConfirmarClienteEdit.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaConfirmarClienteEdit.setResizable(false);
			
			palcoTelaConfirmarClienteEdit.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaClienteEditar.setCodEditClientConfir(0);
				}
			});
			palcoTelaConfirmarClienteEdit.close();
			
			
			// Mostrar a janela/tela
			palcoTelaConfirmarClienteEdit.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
