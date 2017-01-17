package controleView;


import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import exceptions.FuncionarioJaCadastradoException;
import exceptions.PessoaJaCadastradaException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Celular;
import negocio.Endereco;
import negocio.Funcionario;
import negocio.Pessoa;
import negocio.Telefone;

public class ControleTelaCadastroFuncionario extends Application implements Initializable {

	public static Stage cadastroStageFuncionario = null;
	
	EletroJam fachada = EletroJam.getInstance();
	
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
	TextField tfPais;
	
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
	TextField tfBairro;
	
	@FXML
	TextField tfCep;
	
	@FXML
	TextField tfConfirmar;
	
	@FXML
	ComboBox<String> cUf;
	
	@FXML
	ComboBox<String> cSexo;

	@FXML
	DatePicker dDataNascimento;
	
	@FXML
	Button btCadastrar;
	
	@FXML
	CheckBox cOperador;
	
	@FXML
	CheckBox cVendedor;
	
	@FXML
	CheckBox cCobrador;
	
	@FXML
	Label tLogin;
	
	@FXML
	Label tSenha;
	
	@FXML 
	TextField tfLogin;
	
	@FXML 
	TextField tfSenha;
	
	@FXML
	Label lLoginNecessario;
	
	@FXML
	Label lSenhaNecessaria;
	
	@FXML
	Label num;
	
	@FXML
	Label lCpfInvalido;
	
	@FXML
	Label lEmailInvalido;
	
	@FXML
	Label lIdentidadeInvalida;
	
	@FXML
	Label lTelefoneInvalido;
	
	@FXML 
	Label lCpfJaExistente;
	
	@FXML
	Label lIdentidadeJaExistente;
	
	@FXML
	Label lEmailExistente;
	
	@FXML
	Label lCamposObrigatorios;
	
	@FXML
	Label lLoginExistente;
	
	@FXML
	Label lCepInvalido;
	
	@FXML
	Label lNumeroInvalido;
	
	@FXML
	Label lCelularInvalido;
	
	@FXML
	Label lTelefoneJaExistente;
	
	@FXML
	Label lCelularJaExistente;
	
	
	@FXML
	Label lCamposInvalidos;
	
	@FXML
	Label lOperadorLogin;
	
	@FXML
	Label lOperadorSenha;
	@FXML
	Label lSenhaInv;
	
	@FXML
	Button btLimpar;
	
	@FXML 
	CheckBox cEntregador;
	
	@FXML
	public void eventoBtnCadastrarCFuncionario (ActionEvent event) throws RepositoryException, PessoaJaCadastradaException, FuncionarioJaCadastradoException {
		
		int idEnd = 0;
		int idPessoaAux = 0;
		lCamposObrigatorios.setVisible(false);
		lLoginExistente.setVisible(false);
		lEmailExistente.setVisible(false);
		lIdentidadeJaExistente.setVisible(false);
		lCpfJaExistente.setVisible(false);
		lCamposObrigatorios.setVisible(false);
		lCamposInvalidos.setVisible(false);
		lCelularJaExistente.setVisible(false);
		lTelefoneJaExistente.setVisible(false);
		
		lOperadorLogin.setVisible(false);
		lOperadorSenha.setVisible(false);
		lSenhaInv.setVisible(false);
		
		boolean control = false;
		
		String nome = null;
	    nome = tfNome.getText();
	    

	    String email = null;
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
		dNascimento = dDataNascimento.getValue();
		
		
		
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
		String complemento = null;
		complemento = tfComplemento.getText();
		
		String cep = null;
		cep = tfCep.getText().replace(".", "").replace("-", "").trim();
		
		
		boolean op = false;
		op = cOperador.isSelected();
		
		boolean co = false;
		co = cCobrador.isSelected();
		
		boolean ve = false;
		ve = cVendedor.isSelected();
		
		boolean en = false;
		en = cEntregador.isSelected();
		
		String operador = "";
		String cobrador = "";
		String vendedor = "";
		String entregador = "";
		
		if (op == true){
			operador = "Operador";
			
		}
		
		if(co == true){
			cobrador = "Cobrador";
		}
		
		if (ve == true){
			vendedor = "Vendedor";
		}
		
		if(en == true){
			entregador = "Entregador";
		}
		
		String login = null;
		login = tfLogin.getText();
		
		String senha = null;
		senha = tfSenha.getText();
		
		String confSenha = null;
		confSenha = tfConfirmar.getText();
		
		byte[] foto = null;
		
		
		lEmailInvalido.setVisible(false);
		lIdentidadeInvalida.setVisible(false);
		lCpfInvalido.setVisible(false);
		lNumeroInvalido.setVisible(false);
		
		
		
		
		
		
		
		
		if(nome.equals("") ||
				(operador.equals("")  && cobrador.equals("") && vendedor.equals("") && entregador.equals(""))){
			lCamposObrigatorios.setVisible(true);
		}else{

			if(isValid(email, identidade, cpf, num) == true ){
				
				if(isUnico(email,identidade,cpf,telefone, celular, op, login) == true){
					
					if (!operador.equals("")){
						
						if (!senha.equals(confSenha)){
							System.out.println("aqui");
							lSenhaInv.setVisible(true);
						}else{
							System.out.println("entro");
						
						if (!login.equals("")){
							if(!senha.equals("")){

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

								int id_funcionario = fachada.getIdPessoa() + 1;

								//fachada.insertEndereco(endereco);
								fachada.insertPessoa(pessoa1);

								if (operador.equals("")){
									operador = null;
								}

								if (cobrador.equals("")){
									cobrador = null;
								}

								if (vendedor.equals("")){
									vendedor = null;
								}

								if (entregador.equals("")){
									entregador = null;
								}

								Funcionario funcionario = new Funcionario(login, operador, senha, cobrador, vendedor, id_funcionario, entregador);
								fachada.insertFuncionario(funcionario);



								

								if(!telefone.equals("")){
									if(fachada.hasTelefone(telefone) == false){

										Telefone telefonePrimario = new Telefone(telefone, id_funcionario);
										fachada.insertTelefone(telefonePrimario);
									}

								}else{
									Telefone telefonePrimario = new Telefone(String.valueOf(id_funcionario), id_funcionario);
									fachada.insertTelefone(telefonePrimario);
								}
								
								if(!celular.equals("")){
									if(fachada.hasCelular(celular) == false){

										Celular cel = new Celular(celular, id_funcionario);
										fachada.insertCelular(cel);
									}

								}else{
									Celular cel = new Celular(String.valueOf(id_funcionario), id_funcionario);
									fachada.insertCelular(cel);
								}
								
								
								ControleTelaConfirmarFuncionario confirm = new ControleTelaConfirmarFuncionario();


								eventoBtnLimpa(event);
								try {
									//Abre a tela de Cadastro de Grupos

									//telaInicial.start(new Stage());

									confirm.start(new Stage());


								} catch (Exception e) {
									e.printStackTrace();

								}
								//	 ControleTelaInicial.setCodFuncio(0);

							}else{	
								lOperadorSenha.setVisible(true);
							}
						}else{
							lOperadorLogin.setVisible(true);
						}
						}
						//operador não setado	
					}else{

						
						
						if (logadouro.equals("") && cidade.equals("") && estado.equals("") && cep.equals("") && numero == 0 && complemento.equals("") && bairro.equals("")){
							//endereco null
							
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
						
						Pessoa pessoa1 = new Pessoa (nome.toUpperCase(), cpf, identidade, idEnd, email, dataNascimento, foto);
						
						int id_funcionario = fachada.getIdPessoa() + 1;
						
						//fachada.insertEndereco(endereco);
						fachada.insertPessoa(pessoa1);
						
						
						if (operador.equals("")){
							operador = null;
						}
						
						if (cobrador.equals("")){
							cobrador = null;
						}
						
						if (vendedor.equals("")){
							vendedor = null;
						}
						
						if (entregador.equals("")){
							entregador = null;
						}
						
						if (login.equals("") || senha.equals("")){
							login = null;
							senha = null;
						}
						
						
						Funcionario funcionario = new Funcionario(login, operador, senha, cobrador, vendedor, id_funcionario, entregador);
						System.out.println(idPessoaAux);
						fachada.insertFuncionario(funcionario);
						
						
						
						//int id_funcionario = fachada.getPessoaIdByCpf(cpf);
						
						if(!telefone.equals("")){
							if(fachada.hasTelefone(telefone) == false){

								Telefone telefonePrimario = new Telefone(telefone, id_funcionario);
								fachada.insertTelefone(telefonePrimario);
							}

						}else{
							Telefone telefonePrimario = new Telefone(String.valueOf(id_funcionario), id_funcionario);
							fachada.insertTelefone(telefonePrimario);
						}
						
						if(!celular.equals("")){
							if(fachada.hasCelular(celular) == false){

								Celular cel = new Celular(celular, id_funcionario);
								fachada.insertCelular(cel);
							}

						}else{
							Celular cel = new Celular(String.valueOf(id_funcionario), id_funcionario);
							fachada.insertCelular(cel);
						}
						
						
						
						ControleTelaConfirmarFuncionario confirm = new ControleTelaConfirmarFuncionario();
						
						
						eventoBtnLimpa(event);
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
				
			
			}
			
			
		}
		
	

		
		}

	
	
	private boolean isUnico(String email, String identidade, String cpf, String telefone, String celular, boolean op, String login) throws RepositoryException {
		
		boolean bolIdentidade = false;
		boolean bolCpf = false;
		boolean bolEmail = false;
		boolean bolTel = false;
		boolean bolTel2 = false;
		boolean bolCel = false;
		boolean bolCel2 = false;
		boolean bolLogin = false;
		
		
		
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
			if(bolCel == true){
				lCelularJaExistente.setVisible(true);
			}
		}
		

		
		
		
		if (op == true){
			bolLogin = fachada.hasFuncionarioLogin(login);
			if (bolLogin == true){
				lLoginExistente.setVisible(true);
			}
		}
		
		
		
		if (bolCpf == false && bolIdentidade == false && bolEmail == false && bolIdentidade == false && bolTel == false && bolCel == false && bolLogin == false){
			return true;
		}else{
			return false;
		}

	}



	private boolean isValid(String email, String identidade, String cpf, String num ) {
		  		
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
	

	@FXML
	private void eventoBtnLimpa (ActionEvent event){
		
		tfNome.setText("");
	    tfEmail.setText("");
	    tfIdentidade.setText("");
		tfCpf.setText("");
		tfTelefone.setText("");
		
		tfCelular.setText("");
		
		dDataNascimento.setValue(null);
		//tfEstado.setText("");
		//tfCidade.setText("");
		tfLogadouro.setText("");
		tfNumero.setText("");
		tfComplemento.setText("");
		tfCep.setText("");
		cOperador.setSelected(false);
		cCobrador.setSelected(false);
		cVendedor.setSelected(false);
		cEntregador.setSelected(false);
		tfCidade.setText("JABOATÃO DOS GUARARAPES");
		tfEstado.setText("PE");
		
		
		String operador = "";
		String cobrador = "";
		String vendedor = "";
		tfLogin.setText("");
		tfSenha.setText("");
		tfConfirmar.setText("");
		
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

	 
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		tfTelefone.setPromptText("(__)____-____");
	
		tfCelular.setPromptText("(__)_____-____");
		
		tfCep.setPromptText("________");
		tfLogin.setPromptText("Apenas Operador tem login");
		tfSenha.setPromptText("Apenas Operador tem senha");
		
		btCadastrar.setFocusTraversable(false);
		btLimpar.setFocusTraversable(false);
		
		num.setVisible(false);
		tfNumero.setVisible(false);
		
		tfCidade.setText("JABOATÃO DOS GUARARAPES");
		tfEstado.setText("PE");
	}

	
	public void start(Stage cadastroStageFuncionario) throws Exception {
		
		ControleTelaCadastroFuncionario.cadastroStageFuncionario = cadastroStageFuncionario;
		
		try {		
			
			// Origem do arquivo FXML da tela
			
			Parent origemTela2 = FXMLLoader.load(getClass().getResource("/view/TelaCadastroFuncionario.fxml"));
		
			
			
			
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela2);			 
			
			
			// Definir a cena para a janela
			cadastroStageFuncionario.setScene(cena);
			
			cadastroStageFuncionario.setTitle("Cadastro de Funcionario");
			
			cadastroStageFuncionario.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			cadastroStageFuncionario.setResizable(false);
			
			cadastroStageFuncionario.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		              ControleTelaInicial.setCodFuncio(0);
		          }
		      });        
			cadastroStageFuncionario.close();
			
			// Mostrar a janela/tela
			cadastroStageFuncionario.show();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
}

	


