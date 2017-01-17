package controleView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Cliente;
import negocio.Pessoa;


public class ControleTelaClienteEd extends Application implements Initializable{

	EletroJam fachada = EletroJam.getInstance();
	public static Stage palcoEd = null;
	public static Pessoa pessoa;
	

	public static int codEditCli = 0;
	
	private static ControleTelaClienteEd instance;
	
	public static ControleTelaClienteEd getInstance() {
		if (ControleTelaClienteEd.instance == null) {
			ControleTelaClienteEd.instance = new ControleTelaClienteEd();
		}
		return ControleTelaClienteEd.instance;
	}
	

	

	public static int getCodEditCli() {
		return codEditCli;
	}


	public static void setCodEditCli(int codEditCli) {
		ControleTelaClienteEd.codEditCli = codEditCli;
	}

	@FXML
	private TableView<Cliente> personTable;
	@FXML
	private TableColumn<Cliente, Integer> idColum;
	@FXML
	private TableColumn<Cliente, String> apelidoColum;
	@FXML
	private TableColumn<Cliente, String> nomeColum;
	@FXML
	private TableColumn<Cliente, String> responsavelColum;

	@FXML
	private Label lNome;
	@FXML
	private Label lApelido;
	@FXML
	private Label lData;
	@FXML
	private Label lCpf;
	@FXML
	private Label lIdentidade;
	@FXML
	private Label lEmail;
	@FXML
	private Label lCep;
	@FXML
	private Label lEstado;
	@FXML
	private Label lBairro;
	@FXML
	private Label lCidade;
	
	@FXML
	private Label lLogadouro;
	@FXML
	private Label lComplemento;
	@FXML
	private Button btnEdit;
	@FXML
	private Label lCelular;
	@FXML
	private Label lTelefone;
	@FXML
	private Label lResponsavel;
	
	@FXML
	private Button bAtt;
	
	private final ObservableList<Cliente> data = FXCollections.observableArrayList();
	
	
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//lNumero.setVisible(false);
		
		personTable.getItems().clear();
		
		ArrayList<Cliente> listarClientes = new ArrayList();
		
		//Cliente cliente = new Cliente ();
		try {
			listarClientes = fachada.getClientesPessoa();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < listarClientes.size(); i++) {
			
			
			data.add(new Cliente (listarClientes.get(i).getApelido(), listarClientes.get(i).getId_cliente(), listarClientes.get(i).getNome(),listarClientes.get(i).getCpf(), listarClientes.get(i).getIdentidade(),listarClientes.get(i).getEmail(),
					listarClientes.get(i).getData_nascimento(), listarClientes.get(i).getLogadouro(), listarClientes.get(i).getCidade(), listarClientes.get(i).getEstado(), listarClientes.get(i).getCep(),
					listarClientes.get(i).getNumero(),listarClientes.get(i).getComplemento(), listarClientes.get(i).getBairro(),listarClientes.get(i).getNumeroCel(), listarClientes.get(i).getNumeroTel(), listarClientes.get(i).getNome_funcionario()));
			
		}
		
		
		personTable.setItems(data);
		
		//idColum.setStyle("-fx-alignment: CENTER-LEFT;");
		idColum.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("id_cliente"));
		apelidoColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apelido"));
		nomeColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		responsavelColum.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome_funcionario"));
		
		// clear person
		showPersonDetails(null);
		
		// Listen for selection changes
		personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {

			@Override
			public void changed(ObservableValue<? extends Cliente> observable,
					Cliente oldValue, Cliente newValue) {
				showPersonDetails(newValue);
			}
		});
		
	}
	
	
	
	@FXML
	private void btnEditAction (){
		if(codEditCli == 0){
			codEditCli = 1;
		Cliente selectedCliente = personTable.getSelectionModel().getSelectedItem();
			if (selectedCliente != null) {
				try {
					
					
					new ControleTelaClienteEditar().start(new Stage(), pessoa, selectedCliente);
					showPersonDetails(selectedCliente);
				
					
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else {
			// Nothing selected
			
			}
		}else{
			
		}
	}

	private void showPersonDetails(Cliente cliente) {
		
		
		if (cliente != null) {
			lNome.setText(cliente.getNome());
			lApelido.setText(cliente.getApelido());
			
			if(cliente.getData_nascimento() == null){
				lData.setText("");
			}else{
				lData.setText(String.valueOf(cliente.getData_nascimento()));
			}
			
			
			lCpf.setText(cliente.getCpf());
			lIdentidade.setText(cliente.getIdentidade());
			lEmail.setText(cliente.getEmail());
			lCep.setText(cliente.getCep());
			lEstado.setText(cliente.getEstado());
			
			/*
			if(cliente.getNumero() == 0){
				lNumero.setText("");
			}else{
				lNumero.setText(String.valueOf(cliente.getNumero()));
			}
			*/
			
			lBairro.setText(cliente.getBairro());
			lCidade.setText(cliente.getCidade());
			//lNumero.setText(String.valueOf(cliente.getNumero()));
			lLogadouro.setText(cliente.getLogadouro());
			lComplemento.setText(cliente.getComplemento());
			lCelular.setText(cliente.getNumeroCel());
			lResponsavel.setText(cliente.getNome_funcionario());
			
			
			if (cliente.getNumeroTel().replaceAll(" ", "").equals(String.valueOf(cliente.getId_cliente()))) {
				lTelefone.setText("");
				
			} else {
				lTelefone.setText(cliente.getNumeroTel());
				
			}
			
			
			
			
		
			
		} else {
			lNome.setText("");
			lApelido.setText("");
			lData.setText("");
			lCpf.setText("");
			lIdentidade.setText("");
			lEmail.setText("");
			lCep.setText("");
			lEstado.setText("");
			lBairro.setText("");
			lCidade.setText("");
			//lNumero.setText("");
			lLogadouro.setText("");
			lComplemento.setText("");
			lCelular.setText("");
			lTelefone.setText("");
			lResponsavel.setText("");
		}
		
	}




	public void start(Stage palcoEd, Pessoa pessoa) throws Exception {
		this.palcoEd = palcoEd;
		this.pessoa = pessoa;
		
		
		try {		
			//System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/PersonOverview.fxml"));
		
			
			
			//System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);			 
			
			
			// Definir a cena para a janela
			palcoEd.setScene(cena);
			
			palcoEd.setTitle("Editar Cliente");
			
			palcoEd.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoEd.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		           //   System.out.println("Stage is closing");
		              ControleTelaInicial.setCodEditCliente(0);
		          }
		      });        
			palcoEd.close();
			
			palcoEd.setResizable(false);
			
			// Mostrar a janela/tela
			palcoEd.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void AtualizarAction (){
		refreshPersonTable();
	}
	
	private void refreshPersonTable( ) {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		
		personTable.setItems(null);
		personTable.layout();
		//reniciarTable();
		// Must set the selected index again (see http://javafx-jira.kenai.com/browse/RT-26291)
		personTable.setItems(reniciarTable());
		personTable.getSelectionModel().select(selectedIndex);
	}
	
	public ObservableList<Cliente> reniciarTable (){
		
		//personTable.getItems().clear();
		
		ObservableList<Cliente> data2 = FXCollections.observableArrayList();
		
		ArrayList<Cliente> listarClientes = new ArrayList();
		
		//Cliente cliente = new Cliente ();
		try {
			listarClientes = fachada.getClientesPessoa();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < listarClientes.size(); i++) {
			
			data2.add(new Cliente (listarClientes.get(i).getApelido(), listarClientes.get(i).getId_cliente(), listarClientes.get(i).getNome(),listarClientes.get(i).getCpf(), listarClientes.get(i).getIdentidade(),listarClientes.get(i).getEmail(),
					listarClientes.get(i).getData_nascimento(), listarClientes.get(i).getLogadouro(), listarClientes.get(i).getCidade(), listarClientes.get(i).getEstado(), listarClientes.get(i).getCep(),
					listarClientes.get(i).getNumero(),listarClientes.get(i).getComplemento(), listarClientes.get(i).getBairro(), listarClientes.get(i).getNumeroCel(), listarClientes.get(i).getNumeroTel(),listarClientes.get(i).getNome_funcionario()
					));
			
		}
		return data2;

		
	}
	


}
