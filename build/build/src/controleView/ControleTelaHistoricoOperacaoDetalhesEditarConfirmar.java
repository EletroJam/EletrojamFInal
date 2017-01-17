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

public class ControleTelaHistoricoOperacaoDetalhesEditarConfirmar extends Application implements Initializable {
	public static Stage palcoTelaConfirmarDetalhes = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
	public void start(Stage primaryStage) throws Exception {
		this.palcoTelaConfirmarDetalhes = primaryStage;
		
		
		
		try {		
			//System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaConfimarHistoricoEdit.fxml"));
		
			
			
			//System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			palcoTelaConfirmarDetalhes.setScene(cena);
			
			palcoTelaConfirmarDetalhes.setTitle("JK");
			
			palcoTelaConfirmarDetalhes.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaConfirmarDetalhes.setResizable(false);
			
			palcoTelaConfirmarDetalhes.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaHistoricoOperacaoDetalhesEditar.setCodHistoricoClienteOpDConfirm(0);
				}
			});
			palcoTelaConfirmarDetalhes.close();
			
			
			// Mostrar a janela/tela
			palcoTelaConfirmarDetalhes.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
