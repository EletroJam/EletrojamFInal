package controleView;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import exceptions.ClienteJaCadastradoException;
import exceptions.PessoaJaCadastradaException;
import exceptions.PessoaNaoEncontradaException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Celular;
import negocio.Cliente;
import negocio.Endereco;
import negocio.Funcionario;
import negocio.Pessoa;
import negocio.Telefone;
import util.AutoCompleteComboBoxListener;

public class ControleTelaCadastroCliente extends Application implements Initializable {

	/*
	private static int caretPosition = 0;
	private static int caretPosition2 = 0;
	private static int caretPosition3 = 0;
	private static int caretPosition4 = 0;
	private static int caretPosition5 = 0;
	*/
	
	private static boolean cond = true;
	private static boolean cond2 = true;
	private static boolean cond3 = true;
	private static boolean cond4 = true;
	private static boolean cond5 = true;
	
	
	public static Stage cadastroStageCliente = null;
	
	@FXML
	TextField tfNome;
	
	@FXML
	TextField tfApelido;
	
	@FXML
	TextField tfEmail;
	
	@FXML
	TextField tfIdentidade;
	
	@FXML
	TextField tfCpf;
	
	@FXML
	TextField tfTelefone;
	
	
	@FXML
	TextField tfCelular;
	
	
	
	@FXML
	TextField tfEstado;
	
	@FXML
	TextField tfNumero;
	
	@FXML
	TextField tfCidade;
	
	@FXML
	TextField tfLogadouro;
	
	@FXML
	TextField tfComplemento;
	
	@FXML
	TextField tfCep;
	
	@FXML
	TextField tfBairro;
	

	@FXML
	DatePicker dataDataNascimento;
	
	@FXML
	Button btCadastrar;
	
	@FXML
	Label lEmailExistente;
	
	@FXML
	Label lIdentidadeJaExistente;
	
	@FXML
	Label lCpfJaExistente;
	
	@FXML
	Label lCamposObrigatorios;
	
	@FXML
	Label lCamposInvalidos;
	
	@FXML
	Label lCpfInvalido;
	
	@FXML
	Label lIdentidadeInvalida;
	
	@FXML
	Label lEmailInvalido;
	
	@FXML
	Label lTelefoneInvalido;
	
	@FXML
	Label lCelularInvalido;
	
	@FXML
	Label lCepInvalido;
	
	@FXML
	Label lNumeroInvalido;
	
	@FXML
	Label lCelularJaExistente;
	
	@FXML
	Label lTelefoneJaExistente;
	
	
	@FXML
	Label num;
	
	@FXML
	Button btLimpar;
	
	@FXML
	ComboBox<Funcionario> cVendedor;
	
	
	EletroJam fachada = EletroJam.getInstance();
	
	
	
	@FXML
	private void eventoBtnCadastrarCliente (ActionEvent event) throws RepositoryException, ClienteJaCadastradoException, PessoaJaCadastradaException, PessoaNaoEncontradaException, InterruptedException {
		int idEnd = 0;
		//int idPessoaAux =0;
		/*
		caretPosition = 0;
		caretPosition2 = 0;
		caretPosition3 = 0;
		caretPosition4 = 0;
		*/
		
		
		//int id_vendedor = 0;
		
		
		lEmailExistente.setVisible(false);
		lIdentidadeJaExistente.setVisible(false);
		lCpfJaExistente.setVisible(false);
		lCamposObrigatorios.setVisible(false);
		lCamposInvalidos.setVisible(false);
		lCelularJaExistente.setVisible(false);
		lTelefoneJaExistente.setVisible(false);
		
		
		boolean control = false;
		
		String nome = null;
	    nome = tfNome.getText();
	    
	    String apelido = null;
	    apelido = tfApelido.getText();
	    
	    
  
	    String email;
	    email = tfEmail.getText();
	    
	    String identidade = null;
	    identidade = tfIdentidade.getText().replace(".", "").replace("-", "").trim();
		
	    String cpf = null;
	    cpf = tfCpf.getText().replace(".", "").replace("-", "").trim();

		String telefone = null;
		telefone = tfTelefone.getText();
		
		
		
		
		
		
		String celular = null;
		celular = tfCelular.getText();
		
		
		
		LocalDate dNascimento = null;
		dNascimento = dataDataNascimento.getValue();
		
		Date dataNascimento;
		
		
		if(dNascimento == null ){
			dataNascimento = null;
		}else{
			
			dataNascimento = Date.valueOf(dNascimento);
			
		}
		
		
		String estado = null;
		estado = tfEstado.getText();
		
		String cidade = null;
		cidade = tfCidade.getText();
		
		String bairro = null;
		bairro = tfBairro.getText();
		
		String logadouro = null;
		logadouro = tfLogadouro.getText();
		
		String num = null;
		num = tfNumero.getText().replace(".", "").replace("-", "").trim();
		
		
		
		int numero = 0;
		
		try{
			 numero = Integer.parseInt(num); 
		}catch (NumberFormatException e){
			
			
		}
		boolean controlId = false;
		boolean controlNome = false;
		
		//String vendedoIdNome = null;
		
		Funcionario fun = cVendedor.getValue();
		
		//vendedoIdNome = cVendedor.getValue();
		
		String vendedoIdNomeConcatenado = "";
		
		//String vendedorSoId = "";
		
		//ArrayList<Operacao> listarResponsavel= new ArrayList();
		
		
		/*
		if(vendedoIdNome != null){
			for (int i = 0; i < vendedoIdNome.length(); i++) {
				if(vendedoIdNome.charAt(i) == ':'){
					//vendedoIdNomeConcatenado = vendedoIdNomeConcatenado + vendedoIdNome.charAt(i);
					controlId = true;
				}else if(vendedoIdNome.charAt(i) == '/'){
					controlNome = true;
				}else if (controlId == false && controlNome == false){
					if(vendedoIdNome.charAt(i+1) != '/'){
						
					}
					vendedoIdNomeConcatenado = vendedoIdNomeConcatenado + vendedoIdNome.charAt(i);
				}else if (vendedoIdNome.charAt(i) != ' ' && controlId == true){
					vendedorSoId = vendedorSoId + vendedoIdNome.charAt(i);
					
				}
			}	
		}
		*/
		
		
		String complemento = null;
		complemento = tfComplemento.getText();
		
		String cep = null;
		cep = tfCep.getText().replace(".", "").replace("-", "").trim();
		
		
		//id_vendedor = Integer.parseInt(vendedorSoId);
		
		
		byte[] foto = null;
		lEmailInvalido.setVisible(false);
		lIdentidadeInvalida.setVisible(false);
		lCpfInvalido.setVisible(false);
		lNumeroInvalido.setVisible(false);
		
		
	//	if(Integer.parseInt(vendedorSoId) != 0 ){
		
		if(nome.equals("") || celular.equals("") || apelido.equals("") || fun.getId_funcionario() == 0 ) {
			lCamposObrigatorios.setVisible(true);
		}else{
			if(isValid(email, identidade, cpf, cep, num) == true ){
				if(isUnico(email,identidade,cpf,telefone, celular) == true){
					
					if (logadouro.equals("") && cidade.equals("") && estado.equals("") && cep.equals("") && numero == 0 && complemento.equals("") && bairro.equals("")){
						//faz nada
						
						Endereco endereco = new Endereco (null,null,null, null, 0,null,null);
						
						fachada.insertEndereco(endereco);
					}else{
						
						
						
						idEnd = fachada.getEnderecoId() + 1;
						
						if(logadouro.equals("")){
							logadouro = "";
						}
						
						if(cidade.equals("")){
							cidade = "";
						}
						
						if(estado.equals("")){
							estado = "";
						}
						
						if(cep.equals("")){
							cep = "";
						}
						
						if(complemento.equals("")){
							complemento = "";
						}
						
						if(bairro.equals("")){
							bairro = "";
						}
						
						Endereco endereco = new Endereco(logadouro.toUpperCase(), cidade.toUpperCase(), estado.toUpperCase(), cep, numero, complemento.toUpperCase(), bairro.toUpperCase());
						
						fachada.insertEndereco(endereco);
					}
					
					if (identidade.equals("")){
						identidade = null;
					}
						
					if (email.equals("")){
						email = null;
					}
					
					if (cpf.equals("")){
						cpf = null;
					}
					
					Pessoa pessoa1 = new Pessoa (nome.toUpperCase(), cpf, identidade, fachada.getEnderecoId(), email, dataNascimento, foto);
					
					//idPessoaAux = fachada.getIdPessoa() +1;
					
			
					fachada.insertPessoa(pessoa1);
					
					try {
					      Thread.sleep(100);
					     } catch (Exception e) {}
						
				
					
					Cliente cliente = new Cliente(apelido.toUpperCase(), fachada.getIdPessoa(), fun.getNome(), fun.getId_funcionario() ) ;
				
					
					fachada.insertCliente(cliente);
					
					
					
					if(!telefone.equals("")){
						if(fachada.hasTelefone(telefone) == false){
							
							Telefone telefonePrimario = new Telefone(telefone, fachada.getIdPessoa());
							fachada.insertTelefone(telefonePrimario);
						}
						
					}else{
						Telefone telefonePrimario = new Telefone(String.valueOf(fachada.getIdPessoa()), fachada.getIdPessoa());
						fachada.insertTelefone(telefonePrimario);
					}
					
					
					if(!celular.equals("")){
						if(fachada.hasCelular(celular) == false){
							
							Celular cel = new Celular(celular, fachada.getIdPessoa());
							fachada.insertCelular(cel);
						}
						
					}else{
						Celular cel = new Celular(String.valueOf(fachada.getIdPessoa()), fachada.getIdPessoa());
						fachada.insertCelular(cel);
					}
					
					
					ControleTelaConfirmarFuncionario confirm = new ControleTelaConfirmarFuncionario();
					
					
					
					eventoBtnLimpar(event);
					 try {
			    			//Abre a tela de Cadastro de Grupos
			    			 
			    			 //telaInicial.start(new Stage());
			    			 
						 	confirm.start(new Stage());
			    			
			    			 
			    	} catch (Exception e) {
			    			e.printStackTrace();
			    				
			    	}
					
					 //ControleTelaInicial.setCodPessoa(0);
				}
			}
			
			
		}
		/*
		}else{
			ControleTelaConfirmarOperacaoNotErro confirm = new ControleTelaConfirmarOperacaoNotErro();
			

			 try {
	    			//Abre a tela de Cadastro de Grupos
	    			 
	    			 //telaInicial.start(new Stage());
	    			 
				 	confirm.start(new Stage());
	    			
	    			 
	    	} catch (Exception e) {
	    			e.printStackTrace();
	    				
	    	}
		}
		*/
		
		
	
	    
	}
	

	

	
	
	
private boolean isUnico(String email, String identidade, String cpf, String telefone, String celular) throws RepositoryException {
		
	boolean bolIdentidade = false;
	boolean bolCpf = false;
	boolean bolEmail = false;
	boolean bolTel = false;

	boolean bolCel = false;
	
	if(cpf.equals("")){
		bolCpf = false;
	}else{
		bolCpf = fachada.hasPessoa(cpf);
		
		if(bolCpf == true){
			lCpfJaExistente.setVisible(true);
		}
	}
	
	if(identidade.equals("")){
		bolIdentidade = false;
	}else{
		bolIdentidade = fachada.hasPessoaIdentidade(identidade);
		if(bolIdentidade == true){
			lIdentidadeJaExistente.setVisible(true);
		}
	}
	
	if(email.equals("")){
		bolEmail = false;
	}else{
		bolEmail = fachada.hasPessoaEmail(email);
		if(bolEmail == true){
			lEmailExistente.setVisible(true);
		}
	}
	
	if (telefone.equals("")){
		bolTel = false;	
	}else{
		bolTel = fachada.hasTelefone(telefone);
		if(bolTel == true){
			lTelefoneJaExistente.setVisible(true);
		}
	}
	
	if(celular.equals("")){
		bolCel = false;
	}else{
		bolCel = fachada.hasCelular(celular);
		{
			lCelularJaExistente.setVisible(true);
		}
	}
	
	

	if (bolIdentidade == false && bolEmail == false && bolIdentidade == false && bolTel == false && bolCel == false 
			 && bolCpf == false){
		return true;
	}else{
		return false;
	}

}



	private boolean isValid(String email, String identidade, String cpf, String cep, String num) {
	  		
			boolean bolIdentidade = false;
			boolean bolCpf = false;
			boolean bolNum = false;
			boolean b = false;
			
			
			
			
	
			String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			
			if (email.equals("")){
				 b = true;
			}else{
				
				 b = email.matches(EMAIL_REGEX);
				
				if(b == false){
					lEmailInvalido.setVisible(true);
					tfEmail.setText("");
				}
			}
		
			
			if (identidade.equals("")){
				bolIdentidade = true;
			}else{
				try{
					int numero = Integer.parseInt(identidade); 
					bolIdentidade = true;
				}catch (NumberFormatException e){
					bolIdentidade = false;
					
				}
			}
			
			
			if(bolIdentidade == false){
				lIdentidadeInvalida.setVisible(true);
				tfIdentidade.setText("");
			}
			
			/*
			try{
				int numero1 = Integer.parseInt(cpf); 
				bolCpf = true;
			}catch (NumberFormatException e){
				bolCpf = false;
			}
			
			*/
			
			if(cpf.equals("")){
				bolCpf = true;
			}else{
				if (isCPF(cpf) == false){
					lCpfInvalido.setVisible(true);
					tfCpf.setText("");
					bolCpf = false;
					
				}else{
					
					bolCpf = true;
				}
			}
			
			if(num.equals("")){
				bolNum = true;
			}else{
				try{
					int numero2 = Integer.parseInt(num); 
					bolNum = true;
				}catch (NumberFormatException e){
					bolNum = false;
				}
			}
			
			if(bolNum == false){
				lNumeroInvalido.setVisible(true);
				tfNumero.setText("");
			}
			
			if (b == true && bolCpf == true && bolIdentidade == true && bolNum == true){
				return true;
			}else{
				return false;
			}
	}





public static boolean isCPF(String CPF) { 
	
	// considera-se erro CPF's formados por uma sequencia de numeros iguais 
	if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)){
		return(false);
	}
	char dig10, dig11; int sm, i, r, num, peso;
	
	
	 try {
		 sm = 0;
	     peso = 10;
	     for (i=0; i<9; i++) { 
	    	 num = (int)(CPF.charAt(i) - 48);
	    	 sm = sm + (num * peso);
	    	 peso = peso - 1;
	     }
		 
	     r = 11 - (sm % 11); if ((r == 10) || (r == 11)) dig10 = '0'; else dig10 = (char)(r + 48);
	     sm = 0; peso = 11; for(i=0; i<10; i++) { num = (int)(CPF.charAt(i) - 48); sm = sm + (num * peso); peso = peso - 1;}
	     r = 11 - (sm % 11); if ((r == 10) || (r == 11)) dig11 = '0'; else dig11 = (char)(r + 48);
	     if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) return(true); else return(false); } catch (InputMismatchException erro) { return(false); }
}


	@FXML
	private void eventoBtnLimpar (ActionEvent event){
		
		tfNome.setText("");
		tfApelido.setText("");
		tfEmail.setText("");
		tfIdentidade.setText("");
		tfCpf.setText("");
	
		tfCelular.setText("");
		
		dataDataNascimento.setValue(null);
		//tfEstado.setText("");
		//tfCidade.setText("");
		tfLogadouro.setText("");
		tfNumero.setText("");
		tfBairro.setText("");
		tfComplemento.setText("");
		tfCep.setText("");
		tfCidade.setText("JABOATÃO DOS GUARARAPES");
		tfEstado.setText("PE");
		//cVendedor.setValue("");
	
}



//public void cSexo 



/*	
	private boolean isValid(String estado) {
		
		if(estado.equals("pe")){
			return true;
		}
		
		tfEstado.setText("");
		
		return false;
	}
*/

	
/*
private void funcCpfInvalido(boolean bolCpf) {
	if(bolCpf = true){
	}else{
		lCpfInvalido.setVisible(true);
		tfCpf.setText("");
	}
	
}



private void funcIdentidadeInvalid(boolean identidade) {
	
	if(identidade == false){
		
		lIdentidadeInvalida.setVisible(true);
		tfIdentidade.setText("");
	}else{
		
	}
}

private void funcEmailInvalido (boolean email){
	if(email = true){
	}else{
		lEmailInvalido.setVisible(true);
		tfEmail.setText("");
	}
}
*/

/*
	
	public static void addMask(final TextField tf, final String mask) {
		
	    tf.setText(mask);
	    addTextLimiter(tf, mask.length());
	    
	    
	    

	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	
	            String value = stripMask(tf.getText(), mask);
	            
	            tf.setText(merge(value, mask));
	            
	        }
	    });
	    

	    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(final KeyEvent e) {
	        	tf.positionCaret(caretPosition + 1);
	        	caretPosition = caretPosition +1;
	           // caretPosition = tf.getCaretPosition();
	        	
	        	if(e.getCode() == KeyCode.BACK_SPACE){
	        		
	        		if(caretPosition == 0) {
		        		
	        		}else if (caretPosition == 1){
	        			caretPosition = caretPosition -1;
	        		}
	        		
	        		else if (tf.getText().length() > 8){
		        		System.out.println("ok");
		            	caretPosition = caretPosition -2;
		            	
		        	}else{
		        		caretPosition = caretPosition -2;
		        		
		        	}
	            	
	            }
	        	
	            if (caretPosition < mask.length()-1 && mask.charAt(caretPosition) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
	                tf.positionCaret(caretPosition + 1);
	               
	            }
	        }
	    });
	}
	
	
	public static void addMask2(final TextField tf, final String mask) {
		
	    tf.setText(mask);
	    addTextLimiter(tf, mask.length());
	    
	    
	    

	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	
	            String value = stripMask(tf.getText(), mask);
	            
	            tf.setText(merge(value, mask));
	            
	        }
	    });
	    

	    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(final KeyEvent e) {
	        	tf.positionCaret(caretPosition2 + 1);
	        	caretPosition2 = caretPosition2 +1;
	           // caretPosition = tf.getCaretPosition();
	        	
	        	
	        		
	        	if(e.getCode() == KeyCode.BACK_SPACE){
	        		
	        		if(caretPosition2 == 0) {
		        		
	        		}else if (caretPosition2 == 1){
	        			caretPosition2 = caretPosition2 -1;
	        		}
	        		
	        		else if (tf.getText().length() > 8){
		            	caretPosition2 = caretPosition2 -2;
		            	
		        	}else{
		        		caretPosition2 = caretPosition2 -2;
		        		
		        	}
	            	
	            }
	        	
	            if (caretPosition2 < mask.length()-1 && mask.charAt(caretPosition2) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
	                tf.positionCaret(caretPosition2 + 1);
	                caretPosition2 = caretPosition2 +1;
	               
	            }
	        }
	    });
	}
	
	public static void addMask3(final TextField tf, final String mask) {
		
	    tf.setText(mask);
	    addTextLimiter(tf, mask.length());
	    
	    
	    

	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	
	            String value = stripMask(tf.getText(), mask);
	            
	            tf.setText(merge(value, mask));
	            
	        }
	    });
	    

	    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(final KeyEvent e) {
	        	tf.positionCaret(caretPosition3 + 1);
	        	caretPosition3 = caretPosition3 +1;
	           // caretPosition = tf.getCaretPosition();
	        	
	        	
	        		
	        	if(e.getCode() == KeyCode.BACK_SPACE){
	        		
	        		if(caretPosition3 == 0) {
		        		
	        		}else if (caretPosition3 == 1){
	        			caretPosition3 = caretPosition3 -1;
	        		}
	        		
	        		else if (tf.getText().length() > 8){
		            	caretPosition3 = caretPosition3 -2;
		            	
		        	}else{
		        		caretPosition3 = caretPosition3 -2;
		        		
		        	}
	            	
	            }
	        	
	            if (caretPosition3 < mask.length()-1 && mask.charAt(caretPosition3) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
	                tf.positionCaret(caretPosition3 + 1);
	                caretPosition3 = caretPosition3 +1;
	               
	            }
	        }
	    });
	}
	
	
	public static void addMask4(final TextField tf, final String mask) {
		
	    tf.setText(mask);
	    addTextLimiter(tf, mask.length());
	    
	    
	    

	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	System.out.println("curent pos "+ caretPosition);
	        	
	            String value = stripMask(tf.getText(), mask);
	            
	            tf.setText(merge(value, mask));
	            
	        }
	    });
	    

	    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(final KeyEvent e) {
	        	tf.positionCaret(caretPosition4 + 1);
	        	caretPosition4 = caretPosition4 +1;
	           // caretPosition = tf.getCaretPosition();
	        	
	        	
	        		
	        	if(e.getCode() == KeyCode.BACK_SPACE){
	        		
	        		if(caretPosition4 == 0) {
		        		
	        		}else if (caretPosition4 == 1){
	        			caretPosition4 = caretPosition4 -1;
	        		}
	        		
	        		else if (tf.getText().length() > 8){
		        		System.out.println("ok");
		            	caretPosition4 = caretPosition4 -2;
		            	
		        	}else{
		        		caretPosition4 = caretPosition4 -2;
		        		
		        	}
	            	
	            }
	        	
	            if (caretPosition4 < mask.length()-1 && mask.charAt(caretPosition4) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
	            	System.out.println("entrando auqi");
	                tf.positionCaret(caretPosition4 + 1);
	                caretPosition4 = caretPosition4 +1;
	               
	            }
	        }
	    });
	}
	
	/*

	public static void addMask5(final TextField tf, final String mask) {
		
	    tf.setText(mask);
	    addTextLimiter(tf, mask.length());
	    
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	        	
	        	System.out.println("curent pos "+ caretPosition5);
	        	
	            String value = stripMask(tf.getText(), mask);
	            
	            tf.setText(merge(value, mask));
	            
	        }
	    });
	    
	    tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(final KeyEvent e) {
	        	tf.positionCaret(caretPosition5 + 1);
	        	caretPosition5 = caretPosition5 +1;
	           // caretPosition = tf.getCaretPosition();
	        	
	        	
	        		
	        	if(e.getCode() == KeyCode.BACK_SPACE){
	        		
	        		if(caretPosition5 == 0) {
		        		
	        		}else if (caretPosition5 == 1){
	        			caretPosition5 = caretPosition5 -1;
	        		}
	        		
	        		else if (tf.getText().length() > 8){
		        		System.out.println("ok");
		            	caretPosition5 = caretPosition5 -2;
		            	
		        	}else{
		        		caretPosition5 = caretPosition5 -2;
		        		
		        	}
	            	
	            }
	        	
	            if (caretPosition5 < mask.length()-1 && mask.charAt(caretPosition5) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
	            	System.out.println("entrando auqi");
	                tf.positionCaret(caretPosition5 + 1);
	                caretPosition5 = caretPosition5 +1;
	               
	            }
	        }
	    });
	}
	*/

/*
	static String merge(final String value, final String mask) {
	    final StringBuilder sb = new StringBuilder(mask);
	    int k = 0;
	    for (int i = 0; i < mask.length(); i++) {
	        if (mask.charAt(i) == ' ' && k < value.length()) {
	            sb.setCharAt(i, value.charAt(k));
	            k++;
	        }
	    }
	    return sb.toString();
	}

	
	static String stripMask(String text, final String mask) {
	    final Set<String> maskChars = new HashSet<>();
	    for (int i = 0; i < mask.length(); i++) {
	        char c = mask.charAt(i);
	        if (c != ' ') {
	            maskChars.add(String.valueOf(c));
	        }
	    }
	    for (String c : maskChars) {
	        text = text.replace(c, "");
	    }
	    return text;
	}

	
	public static void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
*/
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*
		cSexo.getItems().addAll("M","F");     
		cSexo.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		          System.out.println(ov);
		            System.out.println(t);
		            System.out.println(t1);
		        }    
		    });
		
		cUf.setVisibleRowCount(10);
		cUf.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", 
				"GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", 
				"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");   
		
		cUf.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		          System.out.println(ov);
		            System.out.println(t);
		            System.out.println(t1);
		        }    
		    });
		
		*/
		
		//addMask(tfTelefone, "(  )    -    ");
		//addMask2(tfTelefone2, "(  )    -    ");
		//addMask3(tfCelular, "(  )     -    ");
		//addMask4(tfCelular2, "(  )     -    ");
		//addMask5(tfCep, "         ");
		
		new AutoCompleteComboBoxListener(cVendedor);
		
		ArrayList<Funcionario> funcionariosVendedores = new ArrayList();
		//ArrayList<String> funcionarioVendedorNome = new ArrayList();
		//Pessoa funcionarioVendedor = null;
		
		try {
			funcionariosVendedores =  fachada.getFuncionariosVendedores();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		for (int i = 0; i < funcionariosVendedores.size(); i++) {
			
			try {
				funcionarioVendedor = fachada.getPessoaId(funcionariosVendedores.get(i).getId_funcionario());
			} catch (RepositoryException | PessoaNaoEncontradaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			funcionarioVendedorNome.add(funcionarioVendedor.getNome() + " / ID: "+ funcionariosVendedores.get(i).getId_funcionario());
		}
*/
		//System.out.println(funcionariosVendedores.get(1).getNome());
		cVendedor.getItems().addAll(funcionariosVendedores);
		cVendedor.getSelectionModel().getSelectedIndex();
		cVendedor.setCellFactory(new Callback<ListView<Funcionario>,ListCell<Funcionario>>(){
			 
	            @Override
	            public ListCell<Funcionario> call(ListView<Funcionario> p) {
	                 
	                final ListCell<Funcionario> cell = new ListCell<Funcionario>(){
	 
	                    @Override
	                    protected void updateItem(Funcionario t, boolean bln) {
	                        super.updateItem(t, bln);
	                         
	                        if(t != null){
	                            setText(t.getNome() + " ");
	                        }else{
	                            setText(null);
	                        }
	                    }
	  
	                };
	                 
	                return cell;
	            } 
		   });
		
		
		
		
		tfTelefone.setPromptText("(__)____-____");
		
		tfCelular.setPromptText("(__)_____-____");
		
		tfCep.setPromptText("________");
		
		
		
		num.setVisible(false);
		tfNumero.setVisible(false);
		
		tfCidade.setText("JABOATÃO DOS GUARARAPES");
		tfEstado.setText("PE");
		
		
		
		
		
	}
	
	
	
	
	public void start(Stage cadastroStageCliente) throws Exception {
		
		ControleTelaCadastroCliente.cadastroStageCliente = cadastroStageCliente;
		
		try {		
			
			// Origem do arquivo FXML da tela
			
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaCadastroCliente.fxml"));
		
			
			
			
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			cadastroStageCliente.setScene(cena);
			
			cadastroStageCliente.setTitle("Cadastro de Cliente");
			
			cadastroStageCliente.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			cadastroStageCliente.setResizable(false);
			
			cadastroStageCliente.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		        
		              ControleTelaInicial.setCodPessoa(0);
		          }
		      });        
			cadastroStageCliente.close();
			
			// Mostrar a janela/tela
			cadastroStageCliente.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

}
