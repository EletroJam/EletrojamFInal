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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Funcionario;
import negocio.Pessoa;

public class ControleTelaFuncionarioEditar extends Application implements Initializable {

	EletroJam fachada = EletroJam.getInstance();
	public static Stage palcoEditFunc = null;
	public static Pessoa pessoa;
	public static Funcionario funcionario;
	public static int codEditFuncConfir = 0;

	private boolean op = false;
	private boolean re = false;
	private boolean co = false;
	private boolean en = false;

	@FXML
	private TextField lNome;
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
	//@FXML
	//private CheckBox cOperaodr;

	@FXML
	private CheckBox cResponsavel;

	@FXML
	private CheckBox cCobrador;

	@FXML
	private CheckBox cEntregador;
	
	
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

		Funcionario funcionario2 = new Funcionario(funcionario.getId_funcionario());
		int id = funcionario.getId_funcionario();
		boolean controlTel = false;
		boolean controlCel = false;
		
		lValidoEmail.setVisible(false);
		lValidoCpf.setVisible(false);
		lValidoIdentidade.setVisible(false);
		lCadastradoTel.setVisible(false);
		lCadastradoCel.setVisible(false);
		lCadastradoEmail.setVisible(false);
		lCadastradoCpf.setVisible(false);
		lCadastradoIdentidade.setVisible(false);
		
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
		
		

		LocalDate dNascimento = null;
		if(lData.getValue() == null){
			
		}else{
			dNascimento = lData.getValue();
		}
		
		boolean controlEnd = false;

		Date dataNascimento;

		if (dNascimento == null) {
			//java.util.Date dataAtual = new java.util.Date();
			//java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
			// java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime());
			dataNascimento = null;
		} else {

			dataNascimento = Date.valueOf(dNascimento);
			// System.out.println(dataNascimento);
		}

		funcionario2.setNome(lNome.getText().toUpperCase());

		if (dataNascimento == null){
			
		}else{
			funcionario2.setData_nascimento(dataNascimento);
		}
		

		String cpfT;
		if(funcionario.getCpf() != null ){
			 cpfT = funcionario.getCpf().replace(".", "").replace("-", "").trim().trim();
		}else{
			 cpfT = "";
		}
		
		if (lCpf.getText() == null || lCpf.getText().equals("")) {

		} else {
			funcionario2.setCpf(lCpf.getText());
			
			if (funcionario2.getCpf().trim().equals(cpfT)){
				
			}else{
				if(isCPF (funcionario2.getCpf()) == true){
					bolControlUnicoCpf = isUnicoCPF(funcionario2.getCpf());
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
		
		String inden;
		if(funcionario.getIdentidade() != null ){
			inden = funcionario.getIdentidade().trim();
		}else{
			inden = "";
		}

		if (lIdentidade.getText() == null || lIdentidade.getText().equals("")) {

		} else {
			funcionario2.setIdentidade(lIdentidade.getText().replace(".", "").replace("-", "").trim());
			
			if (funcionario2.getIdentidade().trim().equals(inden)){
				
			}else{
				if(isValidIdentidade(funcionario2.getIdentidade()) == true){
					bolControlUnicoIdenti = isUnicoIdentidade(funcionario2.getIdentidade());
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
		if(funcionario.getEmail() != null ){
			emailt = funcionario.getEmail().trim();
		}else{
			emailt = "";
		}
		
		if (lEmail.getText() == null || lEmail.getText().equals("")) {

		} else {
			funcionario2.setEmail(lEmail.getText().toUpperCase());
			
			if (funcionario2.getEmail().trim().equals(emailt)){
				
			}else{
				if(isValidEmail(funcionario2.getEmail()) == true ){
					bolControlUnicoEmail = isUnicoEmail(funcionario2.getEmail());
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
			funcionario2.setCep(lCep.getText().toUpperCase().replace(".", "").replace("-", "").trim());
		}

		if (lEstado.getText() == null) {

		} else {
			controlEnd = true;
			funcionario2.setEstado(lEstado.getText().toUpperCase());
		}

		if (lBairro.getText() == null) {

		} else {
			controlEnd = true;
			funcionario2.setBairro(lBairro.getText().toUpperCase());
		}

		if (lCidade.getText() == null) {

		} else {
			controlEnd = true;
			funcionario2.setCidade(lCidade.getText().toUpperCase());
		}

		/*
		if (lNumero.getText() == null) {

		} else {
			if(lNumero.getText().equals("")){
				
			}else{
				controlEnd = true;
				funcionario2.setNumero(Integer.valueOf(lNumero.getText().replace(".", "").replace("-", "").trim()));
			}
			
		}*/

		if (lLogadouro.getText() == null) {

		} else {
			controlEnd = true;
			funcionario2.setLogadouro(lLogadouro.getText().toUpperCase());
		}

		if (lComplemento.getText() == null) {

		} else {
			controlEnd = true;
			funcionario2.setComplemento(lComplemento.getText().toUpperCase());
		}
		
		if (lCelular.getText().equals("")) {

		} else {
			funcionario2.setNumeroCel(lCelular.getText());
			controlCel = true;
			
			if (funcionario2.getNumeroCel().trim().equals(funcionario.getNumeroCel().trim())){
				
			}else{
				bolControlUnicoCel = isUnicoCelular(funcionario2.getNumeroCel());
				if(bolControlUnicoCel == true){
					
				}else{
					controlCel1 = true;
				}
			}
		}
		
		if (lTelefone.getText().equals("")) {

		} else {
			funcionario2.setNumeroTel(lTelefone.getText());
			controlTel = true;
			
			if (funcionario2.getNumeroTel().trim().equals(funcionario.getNumeroTel().trim())){
				
			}else{
				bolControlUnicoTel = isUnicoTelefone(funcionario2.getNumeroTel());
				if(bolControlUnicoTel == true){
					
				}else{
					controlTel1 = true;
				}
			}

		}
		
		//boolean op = false;
		//op = cOperaodr.isSelected();
		
		boolean co = false;
		co = cCobrador.isSelected();
		
		boolean ve = false;
		ve = cResponsavel.isSelected();
		
		boolean en = false;
		en = cEntregador.isSelected();
	

		//String operador = "";
		String cobrador = "";
		String responsavel = "";
		String entregador = "";

	//	if (op == true) {
	//		operador = "Operador";
	//		funcionario2.setCargo(operador);
	//	}

		if (co == true) {
			cobrador = "Cobrador";
			funcionario2.setCargo2(cobrador);
		}

		if (ve == true) {
			responsavel = "Vendedor";
			funcionario2.setCargo3(responsavel);
		}

		if (en == true) {
			entregador = "Entregador";
			funcionario2.setCargo4(entregador);
		}
		
		
		if (controlTel1 == true || controlCel1 == true || controlEmail == true || controlIdentidade == true || controlCpf == true){
			
		}else{

		fachada.updateFuncionario(funcionario2, id);

		fachada.updatePessoaFunc(id, funcionario2);
		
		
		if (controlCel == true){
			fachada.updateCel(lCelular.getText(), id);
			
		}
		


		if (controlTel == true){
			fachada.updateTelefone(lTelefone.getText(), id);
		}
		
		if (controlEnd == true){
			int idEnd = 0;

			idEnd = fachada.getIdEndereco(id);

			fachada.updateEnderecoFunc(idEnd, funcionario2);
		}
		

		if (codEditFuncConfir == 0) {
			codEditFuncConfir = 1;

			try {

				new ControleTelaConfirmarFuncionarioEditar().start(new Stage());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

	}

	public static int getCodEditFuncConfir() {
		return codEditFuncConfir;
	}

	public static void setCodEditFuncConfir(int codEditFuncConfir) {
		ControleTelaFuncionarioEditar.codEditFuncConfir = codEditFuncConfir;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		
		if (funcionario.getData_nascimento() != null) {
			Date input = funcionario.getData_nascimento();
			LocalDate date = Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			lData.setValue(date);
		}

		lNome.setText(funcionario.getNome());

		lCpf.setText(funcionario.getCpf());
		lIdentidade.setText(funcionario.getIdentidade());
		lEmail.setText(funcionario.getEmail());
		lCep.setText(funcionario.getCep());
		lEstado.setText(funcionario.getEstado());
		lBairro.setText(funcionario.getBairro());
		lCidade.setText(funcionario.getCidade());

		/*
		if (funcionario.getNumero() == 0) {
			lNumero.setText("");
		} else {
			lNumero.setText(String.valueOf(funcionario.getNumero()));
		}
*/
		lLogadouro.setText(funcionario.getLogadouro());
		lComplemento.setText(funcionario.getComplemento());

		if (Integer.valueOf(funcionario.getNumeroTel().replaceAll(" ", "")) == funcionario.getId_funcionario()) {
			lTelefone.setText("");

		} else {
			lTelefone.setText(funcionario.getNumeroTel());

		}

		if (Integer.valueOf(funcionario.getNumeroCel().replaceAll(" ", "")) == funcionario.getId_funcionario()) {
			lCelular.setText("");
		} else {
			lCelular.setText(funcionario.getNumeroCel());

		}

		/*if (funcionario.getCargo() == null) {
			
		} else {
			
			cOperaodr.setSelected(true);
		}*/

		if (funcionario.getCargo2() == null) {
			
		} else {
			
			cCobrador.setSelected(true);
		}

		if (funcionario.getCargo3() == null) {
			
		} else {
			
			cResponsavel.setSelected(true);
		}

		if (funcionario.getCargo4() == null) {
			
		} else {
			
			cEntregador.setSelected(true);
		}

	}

	public void start(Stage palcoEditFunc, Pessoa pessoa, Funcionario funcionario) throws Exception {
		this.palcoEditFunc = palcoEditFunc;
		this.pessoa = pessoa;
		this.funcionario = funcionario;

		try {
			// System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/FuncionarioEditDialog.fxml"));

			// System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// Definir a cena para a janela
			palcoEditFunc.setScene(cena);

			palcoEditFunc.setTitle("Editar Funcionario");

			palcoEditFunc.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			palcoEditFunc.setResizable(false);

			palcoEditFunc.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaFuncionarioEd.setCodEditFunc(0);

				}
			});
			palcoEditFunc.close();

			// Mostrar a janela/tela
			palcoEditFunc.show();

		} catch (Exception e) {
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
					//tfEmail.setText("");
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
