package controleView;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Cliente;
import negocio.Pessoa;


public class ControleTelaClienteEditar extends Application implements Initializable{
	
	EletroJam fachada = EletroJam.getInstance();
	public static Stage palcoEdit = null;
	public static Pessoa pessoa;
	public static Cliente cliente;
	public static int codEditClientConfir = 0;
	
	
	
	public static int getCodEditClientConfir() {
		return codEditClientConfir;
	}



	public static void setCodEditClientConfir(int codEditClientConfir) {
		ControleTelaClienteEditar.codEditClientConfir = codEditClientConfir;
	}

	@FXML
	private TextField lNome;
	@FXML
	private TextField lApelido;
	@FXML
	private DatePicker lData;
	@FXML
	private TextField lCpf;
	@FXML
	private TextField lIdentidade;
	@FXML
	private TextField lEmail;
	@FXML
	private TextField lCep;
	@FXML
	private TextField lEstado;
	@FXML
	private TextField lBairro;
	@FXML
	private TextField lCidade;
	
	@FXML
	private TextField lLogadouro;
	@FXML
	private TextField lComplemento;
	@FXML
	private TextField lTelefone;
	@FXML
	private TextField lCelular;
	
	@FXML
	private TextField lResponsavel;
	
	
	//

	
	@FXML
	private Label lValidoEmail;
	@FXML
	private Label lValidoCpf;
	@FXML
	private Label lValidoIdentidade;
	@FXML
	private Label lCadastradoTel;
	@FXML
	private Label lCadastradoCel;
	@FXML
	private Label lCadastradoEmail;
	@FXML
	private Label lCadastradoCpf;
	@FXML
	private Label lCadastradoIdentidade;
	
	
	
	@FXML
	private void handleOk() throws RepositoryException {
		
		
		lValidoEmail.setVisible(false);
		lValidoCpf.setVisible(false);
		lValidoIdentidade.setVisible(false);
		lCadastradoTel.setVisible(false);
		lCadastradoCel.setVisible(false);
		lCadastradoEmail.setVisible(false);
		lCadastradoCpf.setVisible(false);
		lCadastradoIdentidade.setVisible(false);
		
		boolean controlTel = false;
		boolean controlCel = true;
		Cliente cliente2 = new Cliente(cliente.getId_cliente());
		boolean controlEnd = false;
		LocalDate dNascimento = null;
		
		boolean bolControlValidEmail = false;
		boolean bolControlValidIdenti = false;
		boolean bolControlValidCpf = false;
		boolean bolControlValidNum = false;
		
		
		boolean bolControlUnicoEmail = false;
		boolean bolControlUnicoIdenti = false;
		boolean bolControlUnicoCpf = false;
		boolean bolControlUnicoCel = false;
		boolean bolControlUnicoTel = false;
		
		boolean controlCpf = false;
		boolean controlIdentidade = false;
		boolean controlEmail = false;
		boolean controlCel1 = false;
		boolean controlTel1 = false;
		
		
		if(lData.getValue() == null ){
			//System.out.println("aqui");
		}else{
			
			dNascimento = lData.getValue();
			//System.out.println(dNascimento);
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
		
		cliente2.setNome(lNome.getText().toUpperCase());
		cliente2.setApelido(lApelido.getText().toUpperCase());
		cliente2.setNome_funcionario(lResponsavel.getText().toUpperCase());

		if (dataNascimento == null || dataNascimento.equals("")){
			
		}else{
			cliente2.setData_nascimento(dataNascimento);
		}
	
		//auqi
		
		String cpfT;
		if(cliente.getCpf() != null ){
			 cpfT = cliente.getCpf().trim();
		}else{
			 cpfT = "";
		}
		
		if (lCpf.getText() == null || lCpf.getText().equals("")) {
			//System.out.println("falso"+ controlCpf);
		} else {
			cliente2.setCpf(lCpf.getText().replace(".", "").replace("-", "").trim());
			if (cliente2.getCpf().trim().equals(cpfT)){
				
			}else{
				if(isCPF (cliente2.getCpf()) == true) {
					bolControlUnicoCpf = isUnicoCPF(cliente2.getCpf());
					if(bolControlUnicoCpf == true){
						
					}else{
						controlCpf = true;
					}
				}else{
					lValidoCpf.setVisible(true);
					controlCpf = true;
				}
			}
			
			
		}
		
		//aqui
		String inden;
		if(cliente.getIdentidade() != null ){
			inden = cliente.getIdentidade().trim();
		}else{
			inden = "";
		}
		

		if (lIdentidade.getText() == null) {

		} else {
			cliente2.setIdentidade(lIdentidade.getText().replace(".", "").replace("-", "").trim());
			
			//mSystem.out.println(cliente2.getIdentidade().trim());
			//System.out.println(cliente.getIdentidade().trim());
			if (cliente2.getIdentidade().trim().equals(inden)){
				
			}else{
				if(isValidIdentidade(cliente2.getIdentidade()) == true){
					bolControlUnicoIdenti = isUnicoIdentidade(cliente2.getIdentidade());
					if(bolControlUnicoIdenti == true){
						
					}else{
						controlIdentidade = true;
					}
				}else{
					lValidoIdentidade.setVisible(true);
					controlIdentidade = true;
				}
			}
		}
		
		String emailt;
		if(cliente.getEmail() != null ){
			emailt = cliente.getEmail().trim();
		}else{
			emailt = "";
		}
		
		if (lEmail.getText() == null) {

		} else {
			cliente2.setEmail(lEmail.getText().toUpperCase());
			
			if (cliente2.getEmail().trim().equals(emailt)){
				
			}else{
				if(isValidEmail(cliente2.getEmail()) == true){
					bolControlUnicoEmail = isUnicoEmail(cliente2.getEmail());
					if(bolControlUnicoEmail == true){
						
					}else{
						controlEmail = true;
					}
				}else{
					lValidoEmail.setVisible(true);
					controlEmail = true;
				}
			}
		}
		if (lCep.getText() == null) {

		} else {
			controlEnd = true;
			cliente2.setCep(lCep.getText().toUpperCase().replace(".", "").replace("-", "").trim());
		}

		if (lEstado.getText() == null) {

		} else {
			controlEnd = true;
			cliente2.setEstado(lEstado.getText().toUpperCase());
		}

		if (lBairro.getText() == null) {

		} else {
			controlEnd = true;
			cliente2.setBairro(lBairro.getText().toUpperCase());
		}

		if (lCidade.getText() == null) {

		} else {
			controlEnd = true;
			cliente2.setCidade(lCidade.getText().toUpperCase());
		}

		/*
		if (lNumero.getText().equals("")) {

		} else {
			controlEnd = true;
			cliente2.setNumero(Integer.valueOf(lNumero.getText().replace(".", "").replace("-", "").trim()));
			
		}
		*/

		if (lLogadouro.getText() == null) {

		} else {
			controlEnd = true;
			cliente2.setLogadouro(lLogadouro.getText().toUpperCase());
		}

		if (lComplemento.getText() == null) {

		} else {
			controlEnd = true;
			cliente2.setComplemento(lComplemento.getText().toUpperCase());
		}
		
		if (lCelular.getText().equals("")) {

		} else {
			controlCel = true;
			cliente2.setNumeroCel(lCelular.getText());
			
			if (cliente2.getNumeroCel().trim().equals(cliente.getNumeroCel().trim())){
				
			}else{
				bolControlUnicoCel = isUnicoCelular(cliente2.getNumeroCel());
				if(bolControlUnicoCel == true){
					
				}else{
					controlCel1 = true;
				}
			}
		}
		
		if (lTelefone.getText().equals("")) {

		} else {
			controlTel = true;
			cliente2.setNumeroTel(lTelefone.getText());
			
			if (cliente2.getNumeroTel().trim().equals(cliente.getNumeroTel().trim())){
				
			}else{
				bolControlUnicoTel = isUnicoTelefone(cliente2.getNumeroTel());
				if(bolControlUnicoTel == true){
					
				}else{
					controlTel1 = true;
				}
			}

		}
		
		//String email, String identidade, String cpf, String cep, String num
		//cpf false
		
		if (controlTel1 == true || controlCel1 == true || controlEmail == true || controlIdentidade == true || controlCpf == true){
			//System.out.println("ddd");
		}else{
			
		
		//int id_fun = fachada.getFuncionarioId(cliente2.getNome_funcionario());
		fachada.updateCliente(cliente2.getApelido(), cliente.getId_cliente(), cliente.getNome_funcionario());
		

		fachada.updatePessoa(cliente.getId_cliente(), cliente2);
		
		if (controlTel == true){
			
			fachada.updateTelefone(lTelefone.getText(), cliente.getId_cliente());
			
		}
		
		if (controlCel == true){
			fachada.updateCel(lCelular.getText(), cliente.getId_cliente());
		}
		
		
		if (controlEnd == true){		
			int idEnd = 0;
			
			idEnd = fachada.getIdEndereco(cliente.getId_cliente());
			
			fachada.updateEndereco(idEnd, cliente2);
		}
		
		
		
		
		if (codEditClientConfir == 0) {
			codEditClientConfir = 1;
			
			try {
				
				new ControleTelaConfirmarClienteEditar().start(new Stage());
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		}
		
		
		
		
	}
	

	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(cliente.getData_nascimento() != null){
			Date input = cliente.getData_nascimento();
			LocalDate date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			lData.setValue(date);
		}
		
		//lNumero.setVisible(false);
		//num.setVisible(false);
		
		
		lNome.setText(cliente.getNome());
		lApelido.setText(cliente.getApelido());
		
		lCpf.setText(cliente.getCpf());
		lIdentidade.setText(cliente.getIdentidade());
		lEmail.setText(cliente.getEmail());
		lCep.setText(cliente.getCep());
		lEstado.setText(cliente.getEstado());
		lBairro.setText(cliente.getBairro());
		lCidade.setText(cliente.getCidade());
		lResponsavel.setText(cliente.getNome_funcionario());
		lResponsavel.setEditable(false);
		/*
		if(cliente.getNumero() == 0){
			lNumero.setText("");
		}else{
			lNumero.setText(String.valueOf(cliente.getNumero()));
		}
		*/
		
		lLogadouro.setText(cliente.getLogadouro());
		lComplemento.setText(cliente.getComplemento());
		lCelular.setText(cliente.getNumeroCel());
		
		if (Integer.valueOf(cliente.getNumeroTel().replaceAll(" ", "")) == cliente.getId_cliente()) {
			lTelefone.setText("");
			
		} else {
			lTelefone.setText(cliente.getNumeroTel());
			
		}
		
		
	}
	
	public void start(Stage palcoEdit, Pessoa pessoa, Cliente cliente) throws Exception {
		this.palcoEdit = palcoEdit;
		this.pessoa = pessoa;
		this.cliente = cliente;
		
		
		try {		
			//System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/PersonEditDialog.fxml"));
		
			
			
			//System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			palcoEdit.setScene(cena);
			
			palcoEdit.setTitle("Editar Cliente");
			
			palcoEdit.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoEdit.setResizable(false);
			
			palcoEdit.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		           //   System.out.println("Stage is closing");
		        	  ControleTelaClienteEd.setCodEditCli(0);
		        	  
		        	  
		          }
		      });        
			palcoEdit.close();
			
			// Mostrar a janela/tela
			palcoEdit.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private boolean isUnicoTelefone (String telefone) throws RepositoryException {
		boolean bolTel = false;
		
		if (telefone.equals("")){
		bolTel = false;	
		}else{
		bolTel = fachada.hasTelefone(telefone);
			if(bolTel == true){
			//lTelefoneJaExistente.setVisible(true);
			//	System.out.println("tel exis");
			}
		}
		
		if (bolTel == false ){
			return true;
		}else{
			lCadastradoTel.setVisible(true);
			return false;
		}
		
	}
	
	
	private boolean isUnicoCelular (String celular) throws RepositoryException {
		boolean bolCel = false;
		
		if(celular.equals("")){
		bolCel = false;
		}else{
		bolCel = fachada.hasCelular(celular);
			if(bolCel == true){
			//lCelularJaExistente.setVisible(true);
				//System.out.println("cel existente");
			}
		}
		
		if (bolCel == false ){
			return true;
		}else{
			lCadastradoCel.setVisible(true);
			return false;
		}
		
	}
	
	private boolean isUnicoCPF (String cpf) throws RepositoryException {
		boolean bolCpf = false;
		if(cpf.equals("")){
		bolCpf = false;
		
		}else{
			
		bolCpf = fachada.hasPessoa(cpf);
		if(bolCpf == true){
			//lCpfJaExistente.setVisible(true);
			//System.out.println("existente cpf");
		}
		}
	
	if (bolCpf == false ){
			return true;
		}else{
			lCadastradoCpf.setVisible(true);
			return false;
		}
	
	}
	
	private boolean isUnicoIdentidade (String identidade) throws RepositoryException {
		boolean bolIdentidade = false;
		
		if(identidade.equals("")){
		bolIdentidade = false;
	}else{
		bolIdentidade = fachada.hasPessoaIdentidade(identidade);
		if(bolIdentidade == true){
			//lIdentidadeJaExistente.setVisible(true);
			//System.out.println("iden ja ex");
		}
	}
		
		if (bolIdentidade == false ){
			return true;
		}else{
			lCadastradoIdentidade.setVisible(true);
			return false;
		}
		
	}
	
	
	private boolean isUnicoEmail (String email) throws RepositoryException {
		boolean bolEmail = false;
		
		if(email.equals("")){
		bolEmail = false;
	}else{
		bolEmail = fachada.hasPessoaEmail(email);
		if(bolEmail == true){
			//lEmailExistente.setVisible(true);
			//System.out.println("email existente");
		}
	}
		
		if (bolEmail == false ){
			return true;
		}else{
			lCadastradoEmail.setVisible(true);
			return false;
		}
		
	}
	
	
	
private boolean isValidEmail(String email){
	
	boolean b = false;
	String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	
	if (email.equals("")){
				 b = true;
			}else{
				
				 b = email.matches(EMAIL_REGEX);
				
				if(b == false){
					//lEmailInvalido.setVisible(true);
					//.setText("");
				}
			}
			
			if (b == true){
				return true;
			}else{
				return false;
			}
}
			
private boolean isValidIdentidade(String identidade){
	
	boolean bolIdentidade = false;
	
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
			
			if (bolIdentidade == true){
				return true;
			}else{
				return false;
			}
}


private boolean isValidNum(String num){
	
	boolean bolNum = false;
	
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
			
			if (bolNum == true){
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

	
	
}
