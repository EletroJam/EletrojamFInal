package controleView;


import java.net.URL;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import exceptions.FuncionarioNaoEncontradoException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import negocio.Funcionario;
import negocio.Pessoa;

public class ControleTelaLogin extends Application implements Initializable{

	// Palco da tela (atribuída no método start: criação da tela)
	public static Stage palcoTela = null;
	
	//textfiel login e senha
	@FXML
	TextField tfLogin;
	
	@FXML
	TextField tfSenha;
	
	//butão
	@FXML
	Button btnEntrar;
	
	@FXML 
	Label  lNaoExistente;
	
	@FXML
	Label lCampoBranco;
	
	
	
	EletroJam fachada = EletroJam.getInstance();
	
	
	
	@FXML
	 private void eventoBtnEntrar(ActionEvent event) throws RepositoryException, FuncionarioNaoEncontradoException{
			
			//resetando o controle de erro
			lNaoExistente.setVisible(false);
			lCampoBranco.setVisible(false);
			
			//variavel de controle para verificar se o campo ta em branco e mudar a label de erro.
			boolean control = false;
			
		    String login = null;
		    login = tfLogin.getText();
		    
		    String senha = null;
		    senha = tfSenha.getText();
		    
		    Funcionario funcionario = null ;
		    Pessoa pessoa = null;
		    
		    //se campo em branco gera a msg de erro e retorna, se passar atribui um valor ao objeto funcionario
		    if(login.equals("") || senha.equals("")){
		    	lCampoBranco.setVisible(true);
		    }else{
		    	funcionario = fachada.getFuncionarioLogin(login, senha);
		    	control = true;
		    	
		    }
		    
		    //se controle for verdadeiro ou seja os cmapos não estão em branco, checa se o objeto é operador, se n for limpa o cmapo e manda erro, se for 
		    //passa a tela, se o objeto for null seta uma nova mesagem de erro e limpa a tela. 
		    if(control == true){
		    	 if(funcionario == null){
		    		 lNaoExistente.setVisible(true);
		    		 tfLogin.setText("");
		    		 tfSenha.setText("");
		    	 }else if (funcionario.getCargo().equals("Operador")){
		    		// ControleTelaInicial telaInicial = new ControleTelaInicial();
		    		 try {
		    			//Abre a tela de Cadastro de Grupos
		    			 
		    			 //telaInicial.start(new Stage());
		    			 
		    			 pessoa = fachada.getPessoaId(funcionario.getId_funcionario());
		    			 
		    			 new ControleTelaInicial().start(new Stage(), pessoa);
		    			
		    			 palcoTela.close();
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    				
		    			}
		    		
				}else{
		    		 lNaoExistente.setVisible(true);
		    		 tfLogin.setText("");
		    		 tfSenha.setText("");
				}
		    }
		    
		    
 }
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//addMask(tfLogin, "  /  /    ");
		
			

		
	}

	
	public void start(Stage palcoInicialTela) throws Exception {
		
		palcoTela = palcoInicialTela;
		
		try {		
			
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoInicialTela.setScene(cena);
			
			palcoInicialTela.setTitle("JK");
			
			palcoInicialTela.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoInicialTela.setResizable(false);
			/*
			Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 
	         // simple displays ImageView the image as is
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
			*/
			
			
	        //imageView = new ImageView(image);
				
		// Mostrar a janela/tela
			palcoInicialTela.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

}
