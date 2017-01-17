package aplicacao;

import controleView.ControleTelaLogin;
import javafx.application.Application;
import javafx.stage.Stage;

public class ExecutarEletroJamSistema extends Application{
	
	public void start(Stage palcoInicialTela){
		
		try{
			
			//Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));
		//	Scene cena = new Scene(origemTela);
		//	primaryStage.setScene(cena);
		//	primaryStage.show();
			
			
			ControleTelaLogin controleTelaLogin = new ControleTelaLogin();
			controleTelaLogin.start(palcoInicialTela);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		launch(args);	

	}

	

}
