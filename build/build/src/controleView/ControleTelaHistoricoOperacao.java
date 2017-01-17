package controleView;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import exceptions.OperacaoNaoEncontrada;
import exceptions.RealizaNaoEncontrado;
import exceptions.StatusNaoEncontrado;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Cliente;
import negocio.Pessoa;
import net.sf.jasperreports.engine.JRException;
import util.AutoCompleteComboBoxListener;

public class ControleTelaHistoricoOperacao extends Application implements Initializable {

	public static Stage palcoTelaHistoricoInicial = null;
	public static Pessoa pessoa;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Cliente> data = FXCollections.observableArrayList();
	public static int codHistoricoClienteOp = 0;

	public static int getCodHistoricoClienteOp() {
		return codHistoricoClienteOp;
	}

	public static void setCodHistoricoClienteOp(int codHistoricoCliente) {
		ControleTelaHistoricoOperacao.codHistoricoClienteOp = codHistoricoCliente;
	}


	@FXML
	private ComboBox<String> cCliente;

	@FXML
	private Button btnPesquisar;
	
	@FXML
	private Button btnDetalhes;
	
	@FXML
	private TableView<Cliente> personTable;
	
	@FXML
	private TableColumn<Cliente, String> funcColum;
	
	@FXML
	private TableColumn<Cliente, Integer> idColum;
	@FXML
	private TableColumn<Cliente, String> valor_emprestimoColum;
	@FXML
	private TableColumn<Cliente, String> atarsoColum;
	@FXML
	private TableColumn<Cliente, Date> dataColum;
	@FXML
	private TableColumn<Cliente, String> estadoColum;
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	public void eventoBtnEditar(ActionEvent event){
		
	}
	
	
	public void eventoBtnRelatorio(ActionEvent event) {
		//idOOp
		
		boolean controlClienteIdNome = false;
		int idCliente = 0;
		
		String clienteIdNome = null;
		clienteIdNome = cCliente.getValue();
		String clienteNomeConcatenado = "";
		
		if(clienteIdNome != null){
			for (int i = 0; i < clienteIdNome.length(); i++) {
				if(clienteIdNome.charAt(i) == ':'){
					controlClienteIdNome = true;
				}else if(controlClienteIdNome == true && clienteIdNome.charAt(i) != ':' && clienteIdNome.charAt(i) != ' ') {
					clienteNomeConcatenado = clienteNomeConcatenado + clienteIdNome.charAt(i);
				}
			}
			
		}
		
		idCliente = Integer.parseInt(clienteNomeConcatenado);
		
		String clienteNomeC = "";
		
		if(clienteIdNome != null){
			for (int i = 0; i < clienteIdNome.length(); i++) {
				if(clienteIdNome.charAt(i+1) != '/'){
					clienteNomeC = clienteNomeC + clienteIdNome.charAt(i);
				}else{
					break;
				}
			}	
		}
		
		System.out.println(idCliente + "cliente");
		
		try {
			fachada.relatorioHistoricoUnic(idCliente, clienteNomeC, "relatorios/historicoOperacoes.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void eventoBtnDelete(ActionEvent event) throws RepositoryException, OperacaoNaoEncontrada, RealizaNaoEncontrado, StatusNaoEncontrado{
		
		Cliente selectedCliente = personTable.getSelectionModel().getSelectedItem();
		int id = selectedCliente.getId_operacao();
		
		fachada.deleteOperacao(id);
		fachada.deleteOperacaoCliente(id);
		fachada.deleteRealiza(id);
		fachada.deleteStatus(id);
		System.out.println("aqui");
		
		eventoBtnPesquisar(event);
	}
	
	@FXML
	public void eventoBtnDetalhes(ActionEvent event){
		
		if(codHistoricoClienteOp == 0){
			codHistoricoClienteOp = 1;
			
			Cliente selectedCliente = personTable.getSelectionModel().getSelectedItem();
			
			
			
			String clienteIdNome = null;
			clienteIdNome = cCliente.getValue();
			String clienteNomeConcatenado = "";
			
			if(clienteIdNome != null){
				for (int i = 0; i < clienteIdNome.length(); i++) {
					if(clienteIdNome.charAt(i) == ':'){
						break;
					}else {
						clienteNomeConcatenado = clienteNomeConcatenado + clienteIdNome.charAt(i);
					}
				}
				
			}
			
			String clienteNomeC = "";
			
			if(clienteIdNome != null){
				for (int i = 0; i < clienteIdNome.length(); i++) {
					if(clienteIdNome.charAt(i+1) != '/'){
						clienteNomeC = clienteNomeC + clienteIdNome.charAt(i);
					}else{
						break;
					}
				}	
			}
			
				if (selectedCliente != null) {
					try {

						new ControleTelaHistoricoOperacaoDetalhes().start(new Stage(), selectedCliente.getId_operacao(), clienteNomeC, clienteNomeConcatenado);
						

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					// Nothing selected
					//System.out.println("fail");
				}
		}else{
			
		}
		
	}
	
	public void eventoBtnPesquisar(ActionEvent event) {
		
		personTable.getItems().clear();
		
		boolean controlClienteIdNome = false;
		int idCliente = 0;
		
		String clienteIdNome = null;
		clienteIdNome = cCliente.getValue();
		String clienteNomeConcatenado = "";
		
		if(clienteIdNome != null){
			for (int i = 0; i < clienteIdNome.length(); i++) {
				if(clienteIdNome.charAt(i) == ':'){
					controlClienteIdNome = true;
				}else if(controlClienteIdNome == true && clienteIdNome.charAt(i) != ':' && clienteIdNome.charAt(i) != ' ') {
					clienteNomeConcatenado = clienteNomeConcatenado + clienteIdNome.charAt(i);
				}
			}
			
		}
		
		idCliente = Integer.parseInt(clienteNomeConcatenado);
		
		
		
		

		ArrayList<Cliente> listarHistorico = new ArrayList();

		// Cliente cliente = new Cliente ();
		try {
			listarHistorico = fachada.getClientesHistorico(idCliente);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarHistorico.size(); i++) {
			
			//private Date data_dia;
	

			data.add(new Cliente( listarHistorico.get(i).getData_dia(),listarHistorico.get(i).getValor_emprestimo(), 
listarHistorico.get(i).getQuantidade_atraso(), listarHistorico.get(i).getId_operacao(), listarHistorico.get(i).getEstadoo(), listarHistorico.get(i).getNome_funcionario()));

		}

		personTable.setItems(data);
		
		
		funcColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome_funcionario"));
		
		idColum.setStyle( "-fx-alignment: CENTER;");
		idColum.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id_operacao"));

		valor_emprestimoColum.setStyle( "-fx-alignment: CENTER;");
		valor_emprestimoColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("valor_emprestimo"));
		
		atarsoColum.setStyle( "-fx-alignment: CENTER;");
		atarsoColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("quantidade_atraso"));
		
		dataColum.setStyle( "-fx-alignment: CENTER;");
		dataColum.setCellValueFactory(new PropertyValueFactory<Cliente, Date>("data_dia"));
		
		estadoColum.setStyle( "-fx-alignment: CENTER;");
		estadoColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("estadoo"));
		
	
	}
	
	

public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Map<String, Integer> example = new HashMap<String, Integer>();
		
		
		new AutoCompleteComboBoxListener(cCliente);
		
		
		
		ArrayList<Cliente> clientes = new ArrayList();
		ArrayList<String> ClienteNomeId = new ArrayList();
		Pessoa ClienteNome = null;
		
	
		
		EletroJam fachada = EletroJam.getInstance();
		
		try {
			clientes =  fachada.getClientes();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < clientes.size(); i++) {
			
			/*
			try {
				ClienteNome = fachada.getPessoaId(clientes.get(i).getId_cliente());
			} catch (RepositoryException | PessoaNaoEncontradaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			ClienteNomeId.add(clientes.get(i).getApelido() + " / ID: "+ clientes.get(i).getId_cliente());
		}
		
		
		cCliente.getItems().addAll(ClienteNomeId);
		cCliente.valueProperty().addListener(new ChangeListener<String>() {
		       @Override public void changed(ObservableValue ov, String t, String t1) {
		         System.out.println(ov);
		           System.out.println(t);
		           System.out.println(t1);
		       }    
		   });
		
		
	}

	
	public void start(Stage palcoTelaHistoricoInicial, Pessoa pessoa) throws Exception {
		this.palcoTelaHistoricoInicial = palcoTelaHistoricoInicial;
		this.pessoa = pessoa;
		
		try {		
			
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaHistoricoOperacao.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaHistoricoInicial.setScene(cena);
			
			palcoTelaHistoricoInicial.setTitle("Exibir Historico");
			
			palcoTelaHistoricoInicial.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaHistoricoInicial.setResizable(false);
			/*
			Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 
	         // simple displays ImageView the image as is
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
			*/
			
			
	        //imageView = new ImageView(image);
			
			palcoTelaHistoricoInicial.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		              ControleTelaInicial.setCodHistoricoCliente(0);
		          }
		      });        
			palcoTelaHistoricoInicial.close();
			
			
		// Mostrar a janela/tela
			palcoTelaHistoricoInicial.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}









}