package controleView;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

import conexao.RepositoryException;
import exceptions.StatusNaoEncontrado;
import fachada.EletroJam;
import javafx.application.Application;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Pessoa;
import negocio.Status;
import net.sf.jasperreports.engine.JRException;

public class ControleTelaHistoricoOperacaoDetalhes extends Application implements Initializable {

	public static Stage palcoTelaHistoricoDetalhes = null;
	public static Pessoa pessoa;
	public static int id_op;
	public static String pess;
	public static String clint;
	public static int codHistoricoClienteOpD = 0;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Status> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Status> personTable;
	@FXML
	private TableColumn<Status, Integer> idColum; 
	@FXML
	private TableColumn<Status, String> funcColum; 
	
	@FXML
	private TableColumn<Status, Double> valorRecColum; 
	
	@FXML
	private TableColumn<Status, String> tipoPagamentoColum; 
	@FXML
	private TableColumn<Status, Date> dataInicialPagamentoColum; 
	
	@FXML
	private TableColumn<Status, Integer> numeroParcColum; 
	
	@FXML
	private TableColumn<Status, String> tipoPagamentoDiaColum; 

	@FXML
	private TableColumn<Status, Integer> multaColum; 
	
	@FXML
	private TableColumn<Status, Integer> multasColum;
	
	@FXML
	private TableColumn<Status, Integer> parcelasColum; 
	
	@FXML
	private TableColumn<Status, Integer> atrasoColum; 
	
	@FXML
	private TableColumn<Status, Integer> parcAtualColum; 
	
	@FXML
	private TableColumn<Status, Integer> parcPagaColum; 
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnAtualizar;
	
	@FXML
	private Button btnStatusDelete;
	
	public static int getCodHistoricoClienteOpD() {
		return codHistoricoClienteOpD;
	}
	
	@FXML
	public void eventoBtnDeletaStatus(ActionEvent event){
		
		ArrayList<Status> listarHistoricoDet = new ArrayList();
		
		
		// Cliente cliente = new Cliente ();
		try {
			listarHistoricoDet = fachada.AllStatusCliente(id_op);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.reverse(listarHistoricoDet);
		
		if(listarHistoricoDet.size() > 0){
			if(listarHistoricoDet.size() != 1){
				try {
					fachada.deleteStatusUnico(listarHistoricoDet.get(0).getId());
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					fachada.updateStatus(false, 0, listarHistoricoDet.get(1).getId(), 0, 0, "", listarHistoricoDet.get(1).getNumero_parcelas());
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			eventoBtnAtualizar(event);
			
		}
		

		
	
		
		
	}
	
	@FXML
	public void eventoBtnRelatorio(ActionEvent event) {
		//idOOp
		
		try {
			fachada.relatorioHistoricoGeral(id_op, clint ,"relatorios/historicoGeral.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void eventoBtnAtualizar(ActionEvent event){
		personTable.getItems().clear();
		
		ArrayList<Status> listarHistoricoDet = new ArrayList();

		// Cliente cliente = new Cliente ();
		try {
			listarHistoricoDet = fachada.AllStatusCliente(id_op);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarHistoricoDet.size(); i++) {
			
			
			//private Date data_dia;
			//dataInicialPagamento

			data.add(new Status( listarHistoricoDet.get(i).getId(),listarHistoricoDet.get(i).getCobrador(), listarHistoricoDet.get(i).getNumero_parcelas(),
					listarHistoricoDet.get(i).getValor_Recebido(), listarHistoricoDet.get(i).getTipo_pagamento(), listarHistoricoDet.get(i).getTipo_pagamento_dia(),
					listarHistoricoDet.get(i).getDataInicialPagamento(), listarHistoricoDet.get(i).getParcelas(), listarHistoricoDet.get(i).getMulta(),
					listarHistoricoDet.get(i).getMultas(), listarHistoricoDet.get(i).getAtraso(), listarHistoricoDet.get(i).getParcela_atual()));
			//System.out.println(listarHistoricoDet.get(i).getDataInicialPagamento() + " espac " +  listarHistoricoDet.get(i).getCobrador());
		}

		personTable.setItems(data);
		
		idColum.setStyle( "-fx-alignment: CENTER;");
		idColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("id")); 

		funcColum.setStyle( "-fx-alignment: CENTER;");
		funcColum.setCellValueFactory(new PropertyValueFactory<Status, String>("cobrador")); 
		
		valorRecColum.setStyle( "-fx-alignment: CENTER;");
		valorRecColum.setCellValueFactory(new PropertyValueFactory<Status, Double>("valor_Recebido")); 
		
		tipoPagamentoColum.setStyle( "-fx-alignment: CENTER;");
		tipoPagamentoColum.setCellValueFactory(new PropertyValueFactory<Status, String>("tipo_pagamento")); 
		
		tipoPagamentoDiaColum.setStyle( "-fx-alignment: CENTER;");
		tipoPagamentoDiaColum.setCellValueFactory(new PropertyValueFactory<Status, String>("tipo_pagamento_dia")); 
		
		dataInicialPagamentoColum.setStyle( "-fx-alignment: CENTER;");
		dataInicialPagamentoColum.setCellValueFactory(new PropertyValueFactory<Status, Date>("dataInicialPagamento")); 
		
		numeroParcColum.setStyle( "-fx-alignment: CENTER;");
		numeroParcColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("numero_parcelas")); 
		
		
		multaColum.setStyle( "-fx-alignment: CENTER;");
		multaColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("multa"));
		
		multasColum.setStyle( "-fx-alignment: CENTER;");
		multasColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("multas")); 
		
		parcelasColum.setStyle( "-fx-alignment: CENTER;");
		parcelasColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("parcelas")); 
		
		atrasoColum.setStyle( "-fx-alignment: CENTER;");
		atrasoColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("atraso"));
		
		parcAtualColum.setStyle( "-fx-alignment: CENTER;");
		parcAtualColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("parcela_atual"));
	}


	public static void setCodHistoricoClienteOpD(int codHistoricoClienteOpD) {
		ControleTelaHistoricoOperacaoDetalhes.codHistoricoClienteOpD = codHistoricoClienteOpD;
	}


	@FXML
	public void eventoBtnEditar(ActionEvent event){
		if(codHistoricoClienteOpD == 0){
			codHistoricoClienteOpD = 1;
			Status selectedCliente = personTable.getSelectionModel().getSelectedItem();
			
			try {

				//System.out.println(selectedCliente.getId());
				new ControleTelaHistoricoOperacaoDetalhesEditar().start(new Stage(), selectedCliente);
						

			} catch (Exception e) {
						// TODO Auto-generated catch block
				e.printStackTrace();
				}

			} else {
					// Nothing selected
				//System.out.println("fail");
			}
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		personTable.getItems().clear();
		
		ArrayList<Status> listarHistoricoDet = new ArrayList();

		// Cliente cliente = new Cliente ();
		try {
			listarHistoricoDet = fachada.AllStatusCliente(id_op);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarHistoricoDet.size(); i++) {
			
			
			//private Date data_dia;
			//dataInicialPagamento

			data.add(new Status( listarHistoricoDet.get(i).getId(),listarHistoricoDet.get(i).getCobrador(), listarHistoricoDet.get(i).getNumero_parcelas(),
					listarHistoricoDet.get(i).getValor_Recebido(), listarHistoricoDet.get(i).getTipo_pagamento(), listarHistoricoDet.get(i).getTipo_pagamento_dia(),
					listarHistoricoDet.get(i).getDataInicialPagamento(), listarHistoricoDet.get(i).getParcelas(), listarHistoricoDet.get(i).getMulta(),
					listarHistoricoDet.get(i).getMultas(), listarHistoricoDet.get(i).getAtraso(), listarHistoricoDet.get(i).getParcela_atual()));
			//System.out.println(listarHistoricoDet.get(i).getDataInicialPagamento() + " espac " +  listarHistoricoDet.get(i).getCobrador());
		}

		personTable.setItems(data);
		
		idColum.setStyle( "-fx-alignment: CENTER;");
		idColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("id")); 

		funcColum.setStyle( "-fx-alignment: CENTER;");
		funcColum.setCellValueFactory(new PropertyValueFactory<Status, String>("cobrador")); 
		
		valorRecColum.setStyle( "-fx-alignment: CENTER;");
		valorRecColum.setCellValueFactory(new PropertyValueFactory<Status, Double>("valor_Recebido")); 
		
		tipoPagamentoColum.setStyle( "-fx-alignment: CENTER;");
		tipoPagamentoColum.setCellValueFactory(new PropertyValueFactory<Status, String>("tipo_pagamento")); 
		
		tipoPagamentoDiaColum.setStyle( "-fx-alignment: CENTER;");
		tipoPagamentoDiaColum.setCellValueFactory(new PropertyValueFactory<Status, String>("tipo_pagamento_dia")); 
		
		dataInicialPagamentoColum.setStyle( "-fx-alignment: CENTER;");
		dataInicialPagamentoColum.setCellValueFactory(new PropertyValueFactory<Status, Date>("dataInicialPagamento")); 
		
		numeroParcColum.setStyle( "-fx-alignment: CENTER;");
		numeroParcColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("numero_parcelas")); 
		
		
		multaColum.setStyle( "-fx-alignment: CENTER;");
		multaColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("multa"));
		
		multasColum.setStyle( "-fx-alignment: CENTER;");
		multasColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("multas")); 
		
		parcelasColum.setStyle( "-fx-alignment: CENTER;");
		parcelasColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("parcelas")); 
		
		atrasoColum.setStyle( "-fx-alignment: CENTER;");
		atrasoColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("atraso"));
		
		parcAtualColum.setStyle( "-fx-alignment: CENTER;");
		parcAtualColum.setCellValueFactory(new PropertyValueFactory<Status, Integer>("parcela_atual"));
		
		
		
	}

	
	public void start(Stage primaryStage, int idOOp, String clienteNomeC ,String nomeC ) throws Exception {
		this.palcoTelaHistoricoDetalhes = primaryStage;
		this.id_op = idOOp;
		this.pess = nomeC;
		this.clint = clienteNomeC;
		//this.pessoa = pessoa;
		
		
		try {		
			
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaHistoricoOperacaoDetalhes.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaHistoricoDetalhes.setScene(cena);
			
			palcoTelaHistoricoDetalhes.setTitle("Exibir Historico Detalhado");
			
			palcoTelaHistoricoDetalhes.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaHistoricoDetalhes.setResizable(false);
			/*
			Image image = new Image("/src/reuniao_corporativo_negocios.gif");
			 
	         // simple displays ImageView the image as is
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
			*/
			
			
	        //imageView = new ImageView(image);
			
			palcoTelaHistoricoDetalhes.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		        	  ControleTelaHistoricoOperacao.setCodHistoricoClienteOp(0);
		          }
		      });        
			palcoTelaHistoricoDetalhes.close();
			
			
		// Mostrar a janela/tela
			palcoTelaHistoricoDetalhes.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/*
	public static Stage palcoTelaHistoricoDetalhes = null;
	public static Pessoa pessoa;
	public static int id_op;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Cliente> data = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Status> personTable;
	@FXML
	private TableColumn<Status, Integer> idColum;
	@FXML
	private TableColumn<Status, String> funcColum;
	@FXML
	private TableColumn<Status, Integer> parcColum;
	@FXML
	private TableColumn<Status, String> tipoPagamentoColum;
	@FXML
	private TableColumn<Status, String> dataInicialPagamentoColum;
	
	@FXML
	private TableColumn<Status, Integer> numeroParcColum;
	
	@FXML
	private TableColumn<Status, String> tipoPagamentoDiaColum;
	
	@FXML
	private TableColumn<Status, Integer> multaColum;
	
	@FXML
	private TableColumn<Status, Integer> atrasoColum;
	
	@FXML
	private TableColumn<Status, Integer> parcAtualColum;
	
	@FXML
	private TableColumn<Status, Integer> parcPagaColum;
	
	

}


		int id = resultset.getInt("id");
		
		int id_operacao = resultset.getInt("id_operacao");
		
		String cliente = resultset.getString("apelido");
		
		String cobrador = resultset.getString("func");
		
		int numero_parcelas = resultset.getInt("numero_parcelas");
		
		String tipo_pagamento = resultset.getString("tipo_pagamento");;
		
		Date dataInicialPagamento = resultset.getDate("data_inicial_pagamento");
		
		String tipo_pagamento_dia = resultset.getString("tipo_pagamento_dia");;
		
		int multa = resultset.getInt("multa");
		
		boolean check_pag = resultset.getBoolean("check_pag");
		
		int atraso = resultset.getInt("atraso");
		int parcela_atual = resultset.getInt("parcela_atual");
		
		String nome_cliente_todos = resultset.getString("nome_cliente_todos");
		
		int parc_paga = resultset.getInt("parc_paga");
		
		int atraso_parc = resultset.getInt("atraso_parc");
	*/
}