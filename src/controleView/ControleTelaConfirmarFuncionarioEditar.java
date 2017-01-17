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

public class ControleTelaConfirmarFuncionarioEditar extends Application implements Initializable {
	public static Stage palcoTelaConfirmarFuncionarioEdit = null;
	
	public void start(Stage palcoTelaConfirmarFuncionarioEdit) throws Exception {
		
		this.palcoTelaConfirmarFuncionarioEdit = palcoTelaConfirmarFuncionarioEdit;
		
		
		
		try {		
			//System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaConfimarFuncionarioEdit.fxml"));
		
			
			
			//System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			palcoTelaConfirmarFuncionarioEdit.setScene(cena);
			
			palcoTelaConfirmarFuncionarioEdit.setTitle("JK");
			
			palcoTelaConfirmarFuncionarioEdit.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaConfirmarFuncionarioEdit.setResizable(false);
			
			palcoTelaConfirmarFuncionarioEdit.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaFuncionarioEditar.setCodEditFuncConfir(0);
				}
			});
			palcoTelaConfirmarFuncionarioEdit.close();
			
			// Mostrar a janela/tela
			palcoTelaConfirmarFuncionarioEdit.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
