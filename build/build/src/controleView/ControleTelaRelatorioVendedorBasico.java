package controleView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class ControleTelaRelatorioVendedorBasico extends Application implements Initializable {

	public static Stage palcoTelaRelatorioResponsavelBasic = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Operacao> data = FXCollections.observableArrayList();

	@FXML
	DatePicker dFinal;

	@FXML
	DatePicker dInicial;

	@FXML
	TableView<Operacao> responsavelTable;

	@FXML
	TableColumn<Operacao, String> nomeColum;

	@FXML
	TableColumn<Operacao, Date> dataRec;

	@FXML
	TableColumn<Operacao, Integer> parcelaColum;

	@FXML
	TableColumn<Operacao, Double> valorEmpColum;

	@FXML
	TableColumn<Operacao, Double> comissaoColum;

	@FXML
	TableColumn<Operacao, Double> valorPassandoColum;

	@FXML
	TableColumn<Operacao, Double> lucroCobradorColum;

	@FXML
	TableColumn<Operacao, Double> totalPagarColum;

	@FXML
	TableColumn<Operacao, Double> lucroEntregadorColum;

	@FXML
	TableColumn<Operacao, Double> vLisrColum;

	@FXML
	TableColumn<Operacao, Double> multasColum;
	
	@FXML
	TableColumn<Operacao, Double> pMColum;


	@FXML
	Button btnPesquisar;

	@FXML
	Button btnRelatorio;

	@FXML
	ComboBox<Funcionario> cVendedor;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void eventoBtnRelatorio(ActionEvent event) {

		LocalDate fim = null;
		fim = dFinal.getValue();

		LocalDate ini = null;
		ini = dInicial.getValue();

		Date dataFim;
		Date dataIni;

		int parcelas = 0;
		double valorEmprestado = 0;
		double comissao = 0;
		double valorPassando = 0;
		double valorCobrador = 0;
		double total = 0;
		double totalEntregador = 0;
		
		double multaPassando = 0;
		double numMulta = 0;
		double totalV = 0;

		boolean controlVendedorIdNome = false;

		if (fim == null) {
			dataFim = null;
		} else {

			dataFim = Date.valueOf(fim);
		}

		if (ini == null) {
			dataIni = null;
		} else {

			dataIni = Date.valueOf(ini);
		}

	//	String vendedoIdNome = null;
	//	vendedoIdNome = cVendedor.getValue();
	// vendedoIdNomeConcatenado = "";
		boolean logic = false;
	// vendedoIdNomeConcatenadoVerdadeiro = "";

		ArrayList<Operacao> listarResponsavel = new ArrayList();
		ArrayList<Operacao> listarResponsavel2 = new ArrayList();
		
		ArrayList<Operacao> operacao = new ArrayList();

		/*
		if (vendedoIdNome != null) {
			for (int i = 0; i < vendedoIdNome.length(); i++) {
				if (vendedoIdNome.charAt(i) == ':' && controlVendedorIdNome == false) {
					controlVendedorIdNome = true;
				} else if (vendedoIdNome.charAt(i) != ':' && controlVendedorIdNome == false) {
					if (vendedoIdNome.charAt(i) == '/') {
						logic = true;
						System.out.println("aqui o" + " " + vendedoIdNome.charAt(i));
					} else if (logic == false) {

						vendedoIdNomeConcatenadoVerdadeiro = vendedoIdNomeConcatenadoVerdadeiro
								+ vendedoIdNome.charAt(i);
					}

				} else if (controlVendedorIdNome == true && vendedoIdNome.charAt(i) != ':'
						&& vendedoIdNome.charAt(i) != ' ') {
					vendedoIdNomeConcatenado = vendedoIdNomeConcatenado + vendedoIdNome.charAt(i);
				}
			}
		}*/

		//int idVendedor = 0;
		//idVendedor = Integer.parseInt(vendedoIdNomeConcatenado);

		
		

		try {
			listarResponsavel = fachada.getResponsavelGeral(dataIni, dataFim, cVendedor.getValue().getId_funcionario());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			operacao = fachada.getMultasResp(dataIni, dataFim, cVendedor.getValue().getId_funcionario(), 0);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double valor = 0;

		ArrayList<Operacao> idListIgual = new ArrayList<Operacao>();
		

		for (int i = 0; i < listarResponsavel.size(); i++) {
			for (int j = 0; j < operacao.size(); j++) {
				if (listarResponsavel.get(i).getId() == 0) {

				} else if (operacao.get(j).getId() == 0) {
					break;
				} else if (listarResponsavel.get(i).getId() == operacao.get(j).getId()) {
					idListIgual.add(new Operacao(operacao.get(j).getNumMulta(), operacao.get(j).getId(),
							operacao.get(j).getApelido(), operacao.get(j).getData_operacao_realizada()));
					operacao.remove(j);
				}
			}
		}

		// System.out.println(listarResponsavel.size() + " " + operacao.size() +
		// " " + operacao.get(0).getApelido());

		if (listarResponsavel.size() > operacao.size()) {

			int len = listarResponsavel.size() - operacao.size();

			for (int i = 0; i < len; i++) {
				operacao.add(new Operacao(0, 0, "x", null));
				// numMulta, id, apelido, dataOp
			}
		} else if (listarResponsavel.size() < operacao.size()) {
			int len = operacao.size() - listarResponsavel.size();
			for (int i = 0; i < len; i++) {
				operacao.add(new Operacao(0, 0, "x", null));
				// numMulta, id, apelido, dataOp
			}
		}

		if (listarResponsavel.size() > idListIgual.size()) {

			int len = listarResponsavel.size() - idListIgual.size();

			for (int i = 0; i < len; i++) {
				idListIgual.add(new Operacao(0, 0, "x", null));
				// numMulta, id, apelido, dataOp
			}
		}

		double valorTemp = 0;
		

		for (int i = 0; i < listarResponsavel.size(); i++) {
			// vLis = 0;
			valor = 0;
			valorTemp = 0;

			if (listarResponsavel.get(i).getId_entregador() == cVendedor.getValue().getId_funcionario()) {
				valor = listarResponsavel.get(i).getValorEntregador();

			}

			if (listarResponsavel.get(i).getId() != 0) {

				double multaLista = 0;
				

				for (int j = 0; j < idListIgual.size(); j++) {

					if (listarResponsavel.get(i).getId() == idListIgual.get(j).getId()) {
						multaLista = idListIgual.get(j).getNumMulta();
						idListIgual.remove(j);
					} else if (idListIgual.get(j).getId() == 0) {
						break;
					}
				}
				
				valorTemp = listarResponsavel.get(i).getValorPassando() + multaLista;
				
				listarResponsavel2.add(new Operacao(listarResponsavel.get(i).getNomeentregador(),
						listarResponsavel.get(i).getData_operacao_realizada(), listarResponsavel.get(i).getParcelas(),
						listarResponsavel.get(i).getValorEmprestado(), listarResponsavel.get(i).getComissao(),
						listarResponsavel.get(i).getValorPassando(), listarResponsavel.get(i).getValorCobrador(),
						round((listarResponsavel.get(i).getTotal() + valor +multaLista) - ((listarResponsavel.get(i).getValorLis()
								+ multaLista) * 0.2), 2),
						listarResponsavel.get(i).getId_entregador(), valor, round ((listarResponsavel.get(i).getValorLis() + multaLista) * 0.2,2),
						round(multaLista, 2), listarResponsavel.get(i).getId(), valorTemp));

				parcelas = parcelas + listarResponsavel.get(i).getParcelas();
				valorEmprestado = valorEmprestado + listarResponsavel.get(i).getValorEmprestado();
				comissao = comissao + listarResponsavel.get(i).getComissao();
				valorPassando = valorPassando + listarResponsavel.get(i).getValorPassando();
				numMulta = numMulta + multaLista;
				totalV = totalV + ((listarResponsavel.get(i).getValorLis() + multaLista) * 0.2);
				valorCobrador = valorCobrador + listarResponsavel.get(i).getValorCobrador();
				total = total + (listarResponsavel.get(i).getTotal() + valor + multaLista - ((listarResponsavel.get(i).getValorLis() + multaLista) * 0.2));
				totalEntregador = totalEntregador + valor;
				multaPassando = multaPassando + valorTemp;
			}

		}

		for (int i = 0; i < operacao.size(); i++) {
			if (operacao.get(i).getId() != 0) {

				double lis = operacao.get(i).getNumMulta() * 0.2;
				
				listarResponsavel2.add(new Operacao(operacao.get(i).getApelido(), operacao.get(i).getData_operacao_realizada(), 0, 0,
						0, 0, 0,  round(operacao.get(i).getNumMulta() - lis,2), 0, 0, lis, operacao.get(i).getNumMulta(), operacao.get(i).getId(),0));
				

				numMulta = numMulta + operacao.get(i).getNumMulta();
				totalV = totalV + lis;
				total = total + operacao.get(i).getNumMulta() - lis;
				
			} else {
				break;
			}
		}

		
		
		
		
        Collections.sort (listarResponsavel2, new Comparator() {
            public int compare(Object o1, Object o2) {
                Operacao p1 = (Operacao) o1;
                Operacao p2 = (Operacao) o2;
                return p1.getNomeentregador().compareToIgnoreCase(p2.getNomeentregador());
            }
        });
        
		
        
	
		try {
			fachada.getRelatorio(listarResponsavel2, "relatorios/responsavelTeste.jasper", dataIni, dataFim, cVendedor.getValue().getId_funcionario(),
					cVendedor.getValue().getNome(), parcelas, String.format("%.2f", valorEmprestado),
					String.format("%.2f", comissao), String.format("%.2f", valorPassando),
					String.format("%.2f", valorCobrador), String.format("%.2f", total),
					String.format("%.2f", totalEntregador), String.format("%.2f", totalV),
					String.format("%.2f", numMulta), String.format("%.2f", multaPassando));
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		


	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {

		responsavelTable.getItems().clear();

		LocalDate fim = null;
		fim = dFinal.getValue();

		LocalDate ini = null;
		ini = dInicial.getValue();

		Date dataFim;
		Date dataIni;

		int parcelas = 0;
		double valorEmprestado = 0;
		double comissao = 0;
		double valorPassando = 0;
		double valorCobrador = 0;
		double total = 0;
		double totalEntregador = 0;
		
		double totalV = 0;
		double multaPassando = 0;

		boolean controlVendedorIdNome = false;

		if (fim == null) {
			dataFim = null;
		} else {

			dataFim = Date.valueOf(fim);
		}

		if (ini == null) {
			dataIni = null;
		} else {

			dataIni = Date.valueOf(ini);
		}

		//String vendedoIdNome = null;
		//vendedoIdNome = cVendedor.getValue();
	//	String vendedoIdNomeConcatenado = "";
		double numMulta = 0;

		ArrayList<Operacao> listarResponsavel = new ArrayList();
		ArrayList<Operacao> operacao = new ArrayList();

		/*
		if (vendedoIdNome != null) {
			for (int i = 0; i < vendedoIdNome.length(); i++) {
				if (vendedoIdNome.charAt(i) == ':') {
					controlVendedorIdNome = true;
				} else if (controlVendedorIdNome == true && vendedoIdNome.charAt(i) != ':'
						&& vendedoIdNome.charAt(i) != ' ') {
					vendedoIdNomeConcatenado = vendedoIdNomeConcatenado + vendedoIdNome.charAt(i);
				}
			}
		}

		int idVendedor = 0;
		idVendedor = Integer.parseInt(vendedoIdNomeConcatenado);
*/
		try {
			listarResponsavel = fachada.getResponsavelGeral(dataIni, dataFim, cVendedor.getValue().getId_funcionario());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			operacao = fachada.getMultasResp(dataIni, dataFim, cVendedor.getValue().getId_funcionario(), 0);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double valor = 0;

		ArrayList<Operacao> idListIgual = new ArrayList<Operacao>();
		

		for (int i = 0; i < listarResponsavel.size(); i++) {
			for (int j = 0; j < operacao.size(); j++) {
				if (listarResponsavel.get(i).getId() == 0) {

				} else if (operacao.get(j).getId() == 0) {
					break;
				} else if (listarResponsavel.get(i).getId() == operacao.get(j).getId()) {
					idListIgual.add(new Operacao(operacao.get(j).getNumMulta(), operacao.get(j).getId(),
							operacao.get(j).getApelido(), operacao.get(j).getData_operacao_realizada()));
					operacao.remove(j);
				}
			}
		}

		// System.out.println(listarResponsavel.size() + " " + operacao.size() +
		// " " + operacao.get(0).getApelido());

		if (listarResponsavel.size() > operacao.size()) {

			int len = listarResponsavel.size() - operacao.size();

			for (int i = 0; i < len; i++) {
				operacao.add(new Operacao(0, 0, "x", null));
				// numMulta, id, apelido, dataOp
			}
		} else if (listarResponsavel.size() < operacao.size()) {
			int len = operacao.size() - listarResponsavel.size();
			for (int i = 0; i < len; i++) {
				operacao.add(new Operacao(0, 0, "x", null));
				// numMulta, id, apelido, dataOp
			}
		}

		if (listarResponsavel.size() > idListIgual.size()) {

			int len = listarResponsavel.size() - idListIgual.size();

			for (int i = 0; i < len; i++) {
				idListIgual.add(new Operacao(0, 0, "x", null));
				// numMulta, id, apelido, dataOp
			}
		}

		// System.out.println(listarResponsavel.size() + " " + operacao.size() +
		// " " + operacao.get(0).getApelido());

		double valorTemp = 0;
		for (int i = 0; i < listarResponsavel.size(); i++) {
			// vLis = 0;
			valor = 0;
			valorTemp = 0;

			if (listarResponsavel.get(i).getId_entregador() == cVendedor.getValue().getId_funcionario()) {
				valor = listarResponsavel.get(i).getValorEntregador();

			}

			if (listarResponsavel.get(i).getId() != 0) {

				double multaLista = 0;
				

				for (int j = 0; j < idListIgual.size(); j++) {
					// System.out.println("laço: "+ j + " " +
					// idListIgual.size());
					if (listarResponsavel.get(i).getId() == idListIgual.get(j).getId()) {
						multaLista = idListIgual.get(j).getNumMulta();
						idListIgual.remove(j);
					} else if (idListIgual.get(j).getId() == 0) {
						break;
					}
					// System.out.println("continua");
				}
				
				valorTemp = listarResponsavel.get(i).getValorPassando() + multaLista;

				data.add(new Operacao(listarResponsavel.get(i).getNomeentregador(),
						listarResponsavel.get(i).getData_operacao_realizada(), listarResponsavel.get(i).getParcelas(),
						listarResponsavel.get(i).getValorEmprestado(), listarResponsavel.get(i).getComissao(),
						listarResponsavel.get(i).getValorPassando(), listarResponsavel.get(i).getValorCobrador(),
						round((listarResponsavel.get(i).getTotal() + valor +multaLista) - ((listarResponsavel.get(i).getValorLis()
								+ multaLista) * 0.2), 2),
						listarResponsavel.get(i).getId_entregador(), valor, round ((listarResponsavel.get(i).getValorLis() + multaLista) * 0.2,2),
						round(multaLista, 2), listarResponsavel.get(i).getId(), valorTemp));

				parcelas = parcelas + listarResponsavel.get(i).getParcelas();
				valorEmprestado = valorEmprestado + listarResponsavel.get(i).getValorEmprestado();
				comissao = comissao + listarResponsavel.get(i).getComissao();
				valorPassando = valorPassando + listarResponsavel.get(i).getValorPassando();
				numMulta = numMulta + multaLista;
				totalV = totalV + ((listarResponsavel.get(i).getValorLis() + multaLista) * 0.2);
				valorCobrador = valorCobrador + listarResponsavel.get(i).getValorCobrador();
				total = total + (listarResponsavel.get(i).getTotal() + valor + multaLista - ((listarResponsavel.get(i).getValorLis() + multaLista) * 0.2));
				totalEntregador = totalEntregador + valor;
				multaPassando = multaPassando + valorTemp;
			}

		}

		for (int i = 0; i < operacao.size(); i++) {
			if (operacao.get(i).getId() != 0) {

				double lis = operacao.get(i).getNumMulta() * 0.2;

				data.add(new Operacao(operacao.get(i).getApelido(), operacao.get(i).getData_operacao_realizada(), 0, 0,
						0, 0, 0, round(operacao.get(i).getNumMulta() - lis,2), 0, 0, round(lis,2), operacao.get(i).getNumMulta(), operacao.get(i).getId(),0));

				numMulta = numMulta + operacao.get(i).getNumMulta();
				totalV = totalV + lis;
				total = total + operacao.get(i).getNumMulta() - lis;
			} else {
				break;
			}
		}

		// DecimalFormat formatador = new DecimalFormat("0.##");
		// double aDouble = Double.parseDouble(aString);
		// String z = formatador.format(total);

		// total = Double.parseDouble(formatador.format(z.replaceAll(",",
		// ".")));
		// comissao = Double.parseDouble(formatador.format(comissao));
		
		//data.sort(c);
		
		
        Collections.sort (data, new Comparator() {
            public int compare(Object o1, Object o2) {
                Operacao p1 = (Operacao) o1;
                Operacao p2 = (Operacao) o2;
                return p1.getNomeentregador().compareToIgnoreCase(p2.getNomeentregador());
            }
        });
		
        
		

		data.add(new Operacao("TOTAL", dataFim, parcelas, round(valorEmprestado, 2), round(comissao, 2),
				round(valorPassando, 2), round(valorCobrador, 2), round(total, 2), 0, round(totalEntregador, 2),
				round(totalV, 2), round(numMulta, 2), 0, round(multaPassando, 2)  ));

		listarResponsavel.clear();

		responsavelTable.setItems(data);

		nomeColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nomeentregador"));

		dataRec.setStyle("-fx-alignment: CENTER;");
		dataRec.setCellValueFactory(new PropertyValueFactory<Operacao, Date>("data_operacao_realizada"));

		parcelaColum.setStyle("-fx-alignment: CENTER;");
		parcelaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Integer>("parcelas"));

		valorEmpColum.setStyle("-fx-alignment: CENTER;");
		valorEmpColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorEmprestado"));

		comissaoColum.setStyle("-fx-alignment: CENTER;");
		comissaoColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("comissao"));

		valorPassandoColum.setStyle("-fx-alignment: CENTER;");
		valorPassandoColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorPassando"));

		lucroCobradorColum.setStyle("-fx-alignment: CENTER;");
		lucroCobradorColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorCobrador"));

		totalPagarColum.setStyle("-fx-alignment: CENTER;");
		totalPagarColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("total"));

		lucroEntregadorColum.setStyle("-fx-alignment: CENTER;");
		lucroEntregadorColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorEntregador"));

		vLisrColum.setStyle("-fx-alignment: CENTER;");
		vLisrColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorLis"));

		multasColum.setStyle("-fx-alignment: CENTER;");
		multasColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("numMulta"));

		pMColum.setStyle("-fx-alignment: CENTER;");
		pMColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("multaPassando"));
		
		listarResponsavel.clear();
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		new AutoCompleteComboBoxListener(cVendedor);

		ArrayList<Funcionario> funcionariosVendedores = new ArrayList();
		//ArrayList<String> funcionarioVendedorNome = new ArrayList();
		//Pessoa funcionarioVendedor = null;

		try {
			funcionariosVendedores = fachada.getFuncionariosVendedores();
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

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage palcoTelaRelatorioResponsavelBasic) throws Exception {
		this.palcoTelaRelatorioResponsavelBasic = palcoTelaRelatorioResponsavelBasic;

		try {
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaVendedorComissaoBasico.fxml"));

			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// origemTela.getStylesheets().add("origemTela.css");

			// cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaRelatorioResponsavelBasic.setScene(cena);

			palcoTelaRelatorioResponsavelBasic.setTitle("Responsavel Fechamento");

			palcoTelaRelatorioResponsavelBasic.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			palcoTelaRelatorioResponsavelBasic.setResizable(false);

			palcoTelaRelatorioResponsavelBasic.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					//System.out.println("Stage is closing");
					ControleTelaInicial.setCodRelatorioResponsavelEspec(0);
				}
			});
			palcoTelaRelatorioResponsavelBasic.close();

			palcoTelaRelatorioResponsavelBasic.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
