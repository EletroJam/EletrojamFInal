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
import negocio.Funcionario;
import negocio.Pessoa;

public class ControleTelaFuncionarioEd extends Application implements Initializable {

	public static int getCodEditFunc() {
		return codEditFunc;
	}

	public static void setCodEditFunc(int codEditFunc) {
		ControleTelaFuncionarioEd.codEditFunc = codEditFunc;
	}

	EletroJam fachada = EletroJam.getInstance();
	public static Stage palcoFunEd = null;
	public static Pessoa pessoa;
	public static int aux1 = 0;
	private final ObservableList<Funcionario> data = FXCollections.observableArrayList();
	public static int codEditFunc = 0;

	@FXML
	private TableView<Funcionario> personTable;
	@FXML
	private TableColumn<Funcionario, Integer> idColum;
	@FXML
	private TableColumn<Funcionario, String> nomeColum;

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
	private Label lOperador;

	@FXML
	private Label lResponsavel;

	@FXML
	private Label lCobrador;

	@FXML
	private Label lEntregador;

	@FXML
	private Button bAtt;

	public void initialize(URL arg0, ResourceBundle arg1) {
		personTable.getItems().clear();

		ArrayList<Funcionario> listarFuncionario = new ArrayList();

		// Cliente cliente = new Cliente ();
		try {
			listarFuncionario = fachada.getFuncionarioPessoa();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarFuncionario.size(); i++) {

			data.add(new Funcionario(listarFuncionario.get(i).getId_funcionario(), listarFuncionario.get(i).getCargo(),
					listarFuncionario.get(i).getCargo2(), listarFuncionario.get(i).getCargo3(),
					listarFuncionario.get(i).getCargo4(), listarFuncionario.get(i).getNome(),
					listarFuncionario.get(i).getCpf(), listarFuncionario.get(i).getIdentidade(),
					listarFuncionario.get(i).getEmail(), listarFuncionario.get(i).getData_nascimento(),
					listarFuncionario.get(i).getLogadouro(), listarFuncionario.get(i).getCidade(),
					listarFuncionario.get(i).getEstado(), listarFuncionario.get(i).getCep(),
					listarFuncionario.get(i).getNumero(), listarFuncionario.get(i).getComplemento(),
					listarFuncionario.get(i).getBairro(), listarFuncionario.get(i).getNumeroCel(),
					listarFuncionario.get(i).getNumeroTel()));

		}

		personTable.setItems(data);

		idColum.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("id_funcionario"));

		nomeColum.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));

		// clear person
		showPersonDetails(null);

		// Listen for selection changes
		personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Funcionario>() {

			@Override
			public void changed(ObservableValue<? extends Funcionario> observable, Funcionario oldValue,
					Funcionario newValue) {
				showPersonDetails(newValue);
			}
		});

	}

	public static int getAux1() {
		return aux1;
	}

	public static void setAux1(int aux1) {
		ControleTelaFuncionarioEd.aux1 = aux1;
	}

	@FXML
	private void AtualizarAction (){
		
		refreshPersonTable();
	}

	@FXML
	private void btnEditAction() {
		if (codEditFunc == 0) {
			codEditFunc = 1;
			Funcionario selectedFuncionario = personTable.getSelectionModel().getSelectedItem();
			if (selectedFuncionario != null) {
				try {

					new ControleTelaFuncionarioEditar().start(new Stage(), pessoa, selectedFuncionario);
					showPersonDetails(selectedFuncionario);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				// Nothing selected

			}
		} else {

		}
	}

	private void showPersonDetails(Funcionario funcionario) {

		if (funcionario != null) {

			lNome.setText(funcionario.getNome());
			
			if (funcionario.getData_nascimento() == null){
				lData.setText("");
			}else{
				lData.setText(String.valueOf(funcionario.getData_nascimento()));
			}
			
			
			lCpf.setText(funcionario.getCpf());
			lIdentidade.setText(funcionario.getIdentidade());
			lEmail.setText(funcionario.getEmail());
			lCep.setText(funcionario.getCep());
			lEstado.setText(funcionario.getEstado());
			lBairro.setText(funcionario.getBairro());
			lCidade.setText(funcionario.getCidade());
			
			/*
			if(funcionario.getNumero() == 0){
				lNumero.setText("");
			}else{
				lNumero.setText(String.valueOf(funcionario.getNumero()));
			}
			*/
			
			
			lLogadouro.setText(funcionario.getLogadouro());
			lComplemento.setText(funcionario.getComplemento());
			//lCelular.setText(funcionario.getNumeroCel());
			

			if (funcionario.getNumeroTel().replaceAll(" ", "").equals(String.valueOf(funcionario.getId_funcionario()))) {
				lTelefone.setText("");
				
			} else {
				lTelefone.setText(funcionario.getNumeroTel());
				
			}
			
			
			if (funcionario.getNumeroCel().replaceAll(" ", "").equals(String.valueOf(funcionario.getId_funcionario()))) {
				lCelular.setText("");
			} else {
				lCelular.setText(funcionario.getNumeroCel());
				
			}


			if (funcionario.getCargo() == null) {
				lOperador.setText("");
			} else {
				lOperador.setText("sim");
			}

			if (funcionario.getCargo2() == null) {
				lCobrador.setText("");
			} else {
				lCobrador.setText("sim");
			}

			if (funcionario.getCargo3() == null) {
				lResponsavel.setText("");
			} else {
				lResponsavel.setText("sim");
			}

			if (funcionario.getCargo4() == null) {
				lEntregador.setText("");
			} else {
				lEntregador.setText("sim");
			}

		} else {
			lNome.setText("");
			// lApelido.setText("");
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
			lOperador.setText("");
			lCobrador.setText("");
			lResponsavel.setText("");
			lEntregador.setText("");
		}

	}

	public void start(Stage palcoFunEd, Pessoa pessoa) throws Exception {
		this.palcoFunEd = palcoFunEd;
		this.pessoa = pessoa;

		try {
			// System.out.println("chego tela principal");
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/FuncionarioOverview.fxml"));

			// System.out.println("achou caminho");
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// Definir a cena para a janela
			palcoFunEd.setScene(cena);

			palcoFunEd.setTitle("Editar Funcionario");

			palcoFunEd.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			palcoFunEd.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaInicial.setCodEditFuncionario(0);
				}
			});
			palcoFunEd.close();

			palcoFunEd.setResizable(false);

			// Mostrar a janela/tela
			palcoFunEd.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void refreshPersonTable() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		personTable.setItems(null);
		personTable.layout();
		// reniciarTable();
		// Must set the selected index again (see
		// http://javafx-jira.kenai.com/browse/RT-26291)
		personTable.setItems(reniciarTable());
		personTable.getSelectionModel().select(selectedIndex);
	}

	public ObservableList<Funcionario> reniciarTable() {

		// personTable.getItems().clear();

		ObservableList<Funcionario> data2 = FXCollections.observableArrayList();

		ArrayList<Funcionario> listarFuncionario = new ArrayList();

		// Cliente cliente = new Cliente ();
		try {
			listarFuncionario = fachada.getFuncionarioPessoa();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < listarFuncionario.size(); i++) {

			data2.add(new Funcionario(listarFuncionario.get(i).getId_funcionario(), listarFuncionario.get(i).getCargo(),
					listarFuncionario.get(i).getCargo2(), listarFuncionario.get(i).getCargo3(),
					listarFuncionario.get(i).getCargo4(), listarFuncionario.get(i).getNome(),
					listarFuncionario.get(i).getCpf(), listarFuncionario.get(i).getIdentidade(),
					listarFuncionario.get(i).getEmail(), listarFuncionario.get(i).getData_nascimento(),
					listarFuncionario.get(i).getLogadouro(), listarFuncionario.get(i).getCidade(),
					listarFuncionario.get(i).getEstado(), listarFuncionario.get(i).getCep(),
					listarFuncionario.get(i).getNumero(), listarFuncionario.get(i).getComplemento(),
					listarFuncionario.get(i).getBairro(), listarFuncionario.get(i).getNumeroCel(),
					listarFuncionario.get(i).getNumeroTel()));

		}
		return data2;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
