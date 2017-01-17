package controleView;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import negocio.Funcionario;
import negocio.Operacao;
import negocio.Pessoa;
import net.sf.jasperreports.engine.JRException;
import util.AutoCompleteComboBoxListener;

public class ControleTelaRelatorioEmprestimo extends Application implements Initializable {
	
	public static Stage palcoTelaRelatorioEmprestimo = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Operacao> data = FXCollections.observableArrayList();
	
	

	
	@FXML
	DatePicker dFinal;
	
	@FXML
	TableView<Operacao> entregadorTable;

	@FXML
	TableColumn<Operacao, String> nomeColum;
	
	@FXML
	TableColumn<Operacao, String> nomeVendColum;
	
	
	@FXML
	TableColumn<Operacao, Double> diarioColum;
	
	@FXML
	TableColumn<Operacao, Integer> parcelaColum;
	
	@FXML
	TableColumn<Operacao, Double> valorParcelasColum;
	
	@FXML
	TableColumn<Operacao, Double> valorMultaColum;
	
	@FXML
	TableColumn<Operacao, Double> ValorPorForaColum;
	
	@FXML
	Button btnPesquisar;
	
	@FXML
	Button btnRelatorio;
	
	@FXML
	ComboBox<Funcionario> cVendedor;
	
	@FXML
	public void eventoBtnRelatorio(ActionEvent event) {
		
		String total = "TOTAL";
		double valorPedido = 0;
		int parcelas = 0;
		double valorParcela = 0;
		double valorMulta = 0;
		double valorPorFora = 0;
		
		boolean controlVendedorIdNome = false;
		
		LocalDate fim = null;
		fim = dFinal.getValue();
		
		Date dataFim;
		
		if(fim == null ){
			dataFim = null;
		}else{
			
			dataFim = Date.valueOf(fim);
		}
		
		//String vendedoIdNome = null;
		//vendedoIdNome = cVendedor.getValue();
		//String vendedoIdNomeConcatenado = "";
	//	String vendedoIdNomeConcatenadoVerdadeiro = "";
	//	int idVendedor = 0;
		
	//	boolean logic = false;
		
		ArrayList<Operacao> listarEntregadores = new ArrayList();
		
		if (cVendedor.getValue().getNome().equals("TODOS")){
			
			
			
			
			try {
				listarEntregadores = fachada.getEmprestimoDiario(dataFim, false, 0);

				
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{

			
		
			
			try {
				listarEntregadores = fachada.getEmprestimoDiario(dataFim, true, cVendedor.getValue().getId_funcionario());
				
				
				
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		for (int i = 0; i < listarEntregadores.size(); i++) {

			//data.add(new Operacao(listarEntregadores.get(i).getNomecobrador(), listarEntregadores.get(i).getValor_pedido(),listarEntregadores.get(i).getParcelas(),listarEntregadores.get(i).getValorDiario(), listarEntregadores.get(i).getValor_juros(), listarEntregadores.get(i).getPorfora() ));
			
			valorPedido  = valorPedido + listarEntregadores.get(i).getValor_pedido();
			parcelas = parcelas + listarEntregadores.get(i).getParcelas();
			valorParcela = valorParcela + listarEntregadores.get(i).getValorDiario();
			valorMulta = valorMulta + listarEntregadores.get(i).getValor_juros();
			valorPorFora = valorPorFora + listarEntregadores.get(i).getPorfora();
		}
		//data.add(new Operacao(total, valorPedido, parcelas, valorParcela, valorMulta, valorPorFora));
		
		if(cVendedor.getValue().getNome().equals("TODOS")){
			try {
				fachada.relatorioEmprestimoDiario(dataFim, "TODOS", cVendedor.getValue().getId_funcionario(), "TOTAL", String.format("%.2f", valorPedido), parcelas, String.format("%.2f",valorParcela), String.format("%.2f",valorMulta), String.format("%.2f",valorPorFora), "relatorios/relatorioEmpresitmoDiarioTodos.jasper");
			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println("cath");
				e.printStackTrace();
			}
		}else{
			try {
				fachada.relatorioEmprestimoDiario(dataFim, cVendedor.getValue().getNome(), cVendedor.getValue().getId_funcionario(), "TOTAL", String.format("%.2f", valorPedido), parcelas, String.format("%.2f",valorParcela), String.format("%.2f",valorMulta), String.format("%.2f",valorPorFora), "relatorios/relatorioEmpresitmoDiarioSolo.jasper");
			} catch (JRException e) {
				System.out.println("cath");
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {
		
		entregadorTable.getItems().clear();
		
		String total = "TOTAL";
		double valorPedido = 0;
		int parcelas = 0;
		double valorParcela = 0;
		double valorMulta = 0;
		double valorPorFora = 0;
		
		//boolean controlVendedorIdNome = false;
		
		LocalDate fim = null;
		fim = dFinal.getValue();
		
		Date dataFim;
		
		if(fim == null ){
			dataFim = null;
		}else{
			
			dataFim = Date.valueOf(fim);
		}
		
		boolean cond1 = false;
		
		//String vendedoIdNome = null;
		//vendedoIdNome = cVendedor.getValue();
		//String vendedoIdNomeConcatenado = "";
		
		ArrayList<Operacao> listarEntregadores = new ArrayList();
		
		if (cVendedor.getValue().getNome().equals("TODOS")){
			
			
			
			
			try {
				listarEntregadores = fachada.getEmprestimoDiario(dataFim, false, 0);

				
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		
			cond1 = true;
			try {
				listarEntregadores = fachada.getEmprestimoDiario(dataFim, true, cVendedor.getValue().getId_funcionario());
				
				
				
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		for (int i = 0; i < listarEntregadores.size(); i++) {

			if(cond1 == true){
				data.add(new Operacao(listarEntregadores.get(i).getNomecobrador(), listarEntregadores.get(i).getValor_pedido(),listarEntregadores.get(i).getParcelas(),listarEntregadores.get(i).getValorDiario(), listarEntregadores.get(i).getValor_juros(), listarEntregadores.get(i).getPorfora(), cVendedor.getValue().getNome() ));
			}else{
				data.add(new Operacao(listarEntregadores.get(i).getNomecobrador(), listarEntregadores.get(i).getValor_pedido(),listarEntregadores.get(i).getParcelas(),listarEntregadores.get(i).getValorDiario(), listarEntregadores.get(i).getValor_juros(), listarEntregadores.get(i).getPorfora(), listarEntregadores.get(i).getNomeVendedorResponsavel() ));

			}
			
			valorPedido  = valorPedido + listarEntregadores.get(i).getValor_pedido();
			parcelas = parcelas + listarEntregadores.get(i).getParcelas();
			valorParcela = valorParcela + listarEntregadores.get(i).getValorDiario();
			valorMulta = valorMulta + listarEntregadores.get(i).getValor_juros();
			valorPorFora = valorPorFora + listarEntregadores.get(i).getPorfora();
			System.out.println(listarEntregadores.get(i).getNomeVendedorResponsavel()+"ddd");
		}
		data.add(new Operacao(total, valorPedido, parcelas, valorParcela, valorMulta, valorPorFora, ""));
		
		entregadorTable.setItems(data);

		
		nomeColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nomecobrador"));
		
		nomeVendColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nomeVendedorResponsavel"));

		diarioColum.setStyle( "-fx-alignment: CENTER;");
		diarioColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valor_pedido"));
		
		parcelaColum.setStyle( "-fx-alignment: CENTER;");
		parcelaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Integer>("parcelas"));
		
		valorParcelasColum.setStyle( "-fx-alignment: CENTER;");
		valorParcelasColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorDiario"));
		
		valorMultaColum.setStyle( "-fx-alignment: CENTER;");
		valorMultaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valor_juros"));
		
		ValorPorForaColum.setStyle( "-fx-alignment: CENTER;");
		ValorPorForaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("porfora"));
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		
		//funcionariosVendedores.add(new Funcionario("", "", "", "", "", 0, "", "TODOS"));
		
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
		
		cVendedor.getItems().add((new Funcionario("", "", "", "", "", 0, "", "TODOS")));
		
	}

	@Override
	public void start(Stage palcoTelaRelatorioEmprestimo) throws Exception {
		this.palcoTelaRelatorioEmprestimo = palcoTelaRelatorioEmprestimo;
		
		
		try {	
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaEmprestimoDiario.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaRelatorioEmprestimo.setScene(cena);
			
			palcoTelaRelatorioEmprestimo.setTitle("Emprestimo Diario");
			
			palcoTelaRelatorioEmprestimo.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaRelatorioEmprestimo.setResizable(false);

			palcoTelaRelatorioEmprestimo.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              System.out.println("Stage is closing");
		              ControleTelaInicial.setCodRelatorioEmpDiario(0);
		          }
		      });        
			palcoTelaRelatorioEmprestimo.close();

			palcoTelaRelatorioEmprestimo.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
