package controleView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import exceptions.PessoaNaoEncontradaException;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import negocio.Cliente;
import negocio.Funcionario;
import negocio.Operacao;
import negocio.Pessoa;
import net.sf.jasperreports.engine.JRException;
import util.AutoCompleteComboBoxListener;

public class ControleTelaRelatorioResponsavelCliente extends Application implements Initializable{
	
	public static Stage palcoTelaRelatorioResponsavelClienteBasic = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Cliente> data = FXCollections.observableArrayList();
	
	@FXML
	TableView<Cliente> responsavelTable;

	@FXML
	TableColumn<Operacao, String> nomeColum;
	
	@FXML
	TableColumn<Operacao, String> apelidoColum;
	
	@FXML
	TableColumn<Operacao, String> celularColumn;
	
	@FXML
	Button btnPesquisar;
	
	@FXML
	Button btnRelatorio;
	
	@FXML
	ComboBox<Funcionario> cVendedor;
	
	@FXML
	public void eventoBtnRelatorio(ActionEvent event) {
		
boolean controlVendedorIdNome = false;
		
		//String vendedoIdNome = null;
		//vendedoIdNome = cVendedor.getValue();
		// vendedoIdNomeConcatenado = "";
	//	String vendedoIdNomeConcatenadoVerdadeiro = "";
		boolean logic = false;
		
		ArrayList<Cliente> listarResponsavel= new ArrayList();
		
		/*
		if(vendedoIdNome != null){
			for (int i = 0; i < vendedoIdNome.length(); i++) {
				if(vendedoIdNome.charAt(i) == ':' && controlVendedorIdNome == false){
					controlVendedorIdNome = true;
				}else if(vendedoIdNome.charAt(i) != ':' && controlVendedorIdNome == false   ){
					if (vendedoIdNome.charAt(i) == '/'){
						logic = true;
						System.out.println("aqui o"+ " "+ vendedoIdNome.charAt(i) );
					}else if(logic == false){
						
						vendedoIdNomeConcatenadoVerdadeiro = vendedoIdNomeConcatenadoVerdadeiro + vendedoIdNome.charAt(i);
					}
					
				}
				else if(controlVendedorIdNome == true && vendedoIdNome.charAt(i) != ':' && vendedoIdNome.charAt(i) != ' ') {
					vendedoIdNomeConcatenado = vendedoIdNomeConcatenado + vendedoIdNome.charAt(i);
				}
			}	
		}
		*/
		
		//int idVendedor = 0;
		//idVendedor = Integer.parseInt(vendedoIdNomeConcatenado);
		
		try {
			fachada.relatorioResponsavelCliente(cVendedor.getValue().getId_funcionario(), cVendedor.getValue().getNome(), "relatorios/relatorioClienteResponsavel.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {
		
		responsavelTable.getItems().clear();
		
		//boolean controlVendedorIdNome = false;
		
		//String vendedoIdNome = null;
		//vendedoIdNome = cVendedor.getValue();
		//String vendedoIdNomeConcatenado = "";
		
		ArrayList<Cliente> listarResponsavel= new ArrayList();
		
		/*
		if(vendedoIdNome != null){
			for (int i = 0; i < vendedoIdNome.length(); i++) {
				if(vendedoIdNome.charAt(i) == ':'){
					controlVendedorIdNome = true;
				}else if(controlVendedorIdNome == true && vendedoIdNome.charAt(i) != ':' && vendedoIdNome.charAt(i) != ' ') {
					vendedoIdNomeConcatenado = vendedoIdNomeConcatenado + vendedoIdNome.charAt(i);
				}
			}	
		}
		
		int idVendedor = 0;
		idVendedor = Integer.parseInt(vendedoIdNomeConcatenado);
		*/
		
		try {
			listarResponsavel = fachada.getClientesPorResponsavel(cVendedor.getValue().getId_funcionario());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < listarResponsavel.size(); i++) {
			data.add(new Cliente( listarResponsavel.get(i).getNome(),listarResponsavel.get(i).getApelido(), listarResponsavel.get(i).getNumeroCel()));

		}

		responsavelTable.setItems(data);

		
		nomeColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nome"));

		
		apelidoColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("apelido"));
		
		
		celularColumn.setCellValueFactory(new PropertyValueFactory<Operacao, String>("numeroCel"));
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		new AutoCompleteComboBoxListener(cVendedor);
		
		ArrayList<Funcionario> funcionariosVendedores = new ArrayList();
		ArrayList<String> funcionarioVendedorNome = new ArrayList();
		Pessoa funcionarioVendedor = null;
		
		
		try {
			funcionariosVendedores =  fachada.getFuncionariosVendedores();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		

		
		
		
	}
	
	@Override
	public void start(Stage palcoTelaRelatorioResponsavelClienteBasic) throws Exception {
		this.palcoTelaRelatorioResponsavelClienteBasic = palcoTelaRelatorioResponsavelClienteBasic;
		
		
		try {	
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaRelatorioClienteVendedor.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaRelatorioResponsavelClienteBasic.setScene(cena);
			
			palcoTelaRelatorioResponsavelClienteBasic.setTitle("Clientes por Responsavel");
			
			palcoTelaRelatorioResponsavelClienteBasic.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaRelatorioResponsavelClienteBasic.setResizable(false);

			palcoTelaRelatorioResponsavelClienteBasic.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              System.out.println("Stage is closing");
		              ControleTelaInicial.setCodRelatorioClienteResponsavelGeral(0);
		          }
		      });        
			palcoTelaRelatorioResponsavelClienteBasic.close();

			palcoTelaRelatorioResponsavelClienteBasic.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}