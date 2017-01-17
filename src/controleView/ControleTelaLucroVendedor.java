package controleView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import conexao.RepositoryException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Operacao;
import net.sf.jasperreports.engine.JRException;

public class ControleTelaLucroVendedor extends Application implements Initializable {

	public static Stage palcoTelaLucroVendedor = null;
	EletroJam fachada = EletroJam.getInstance();
	private final ObservableList<Operacao> data = FXCollections.observableArrayList();

	@FXML
	DatePicker dInicial;

	@FXML
	DatePicker dFinal;

	@FXML
	TableView<Operacao> vendedorTable;

	@FXML
	TableColumn<Operacao, String> nomeColum;
	@FXML

	TableColumn<Operacao, Double> lucroColum;

	@FXML
	TableColumn<Operacao, Double> diarioColum;

	@FXML
	TableColumn<Operacao, Double> multaColum;

	@FXML
	TableColumn<Operacao, Double> porforaColum;

	@FXML
	TableColumn<Operacao, Double> totalColum;

	@FXML
	TableColumn<Operacao, Double> vendidoColum;

	@FXML
	TableColumn<Operacao, Double> comissaoColum;

	@FXML
	TableColumn<Operacao, Double> cobraLucroColum;

	@FXML
	TableColumn<Operacao, Double> totalPassColum;

	@FXML
	TableColumn<Operacao, Double> vendidoRecec;

	@FXML
	TableColumn<Operacao, Double> lucroEntrega;

	@FXML
	Button btnPesquisar;

	@FXML
	Button btnRelatorio;

	@FXML
	public void eventoBtnRelatorio(ActionEvent event) {

		LocalDate ini = null;
		ini = dInicial.getValue();

		LocalDate fim = null;
		fim = dFinal.getValue();

		Date dataIni;
		Date dataFim;

		if (ini == null) {
			dataIni = null;
		} else {

			dataIni = Date.valueOf(ini);
		}

		if (fim == null) {
			dataFim = null;
		} else {

			dataFim = Date.valueOf(fim);
		}

		try {
			fachada.relatorioComissaoVendedorCobrador(dataIni, dataFim, "relatorios/vendedorRelatorioLucro.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void eventoBtnPesquisar(ActionEvent event) {

		vendedorTable.getItems().clear();

		double valorReceber = 0;
		double valorVendido = 0;
		double comissao = 0;
		double valorPassando = 0;
		double lucroVendedor = 0;
		double lucroEntregador = 0;
		double lucroCobrador = 0;
		double totalRecebidoParcelas = 0;
		double totalRecebidoMulta = 0;
		double totalPassando = 0;
		double totalRecebidoGeral = 0;

		String total = "TOTAL";

		LocalDate ini = null;
		ini = dInicial.getValue();

		LocalDate fim = null;
		fim = dFinal.getValue();

		Date dataIni;
		Date dataFim;

		if (ini == null) {
			dataIni = null;
		} else {

			dataIni = Date.valueOf(ini);
		}

		if (fim == null) {
			dataFim = null;
		} else {

			dataFim = Date.valueOf(fim);
		}

		ArrayList<Operacao> listarVendedores = new ArrayList();
		ArrayList<Operacao> listarVendedores2 = new ArrayList();
		ArrayList<Operacao> listarVendedores3 = new ArrayList();
		// getValorCobrador

		try {
			listarVendedores = fachada.getVendedorComissao(dataIni, dataFim);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// os 3 primeiros
			listarVendedores2 = fachada.getlucroMesVendedor(dataIni, dataFim);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			listarVendedores3 = fachada.getValorCobrador(dataIni, dataFim);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> chec = new ArrayList<String>();
		ArrayList<String> chec2 = new ArrayList<String>();
		boolean check = false;

		for (int i = 0; i < listarVendedores2.size(); i++) {
			check = false;
			for (int j = 0; j < listarVendedores.size(); j++) {
				if (listarVendedores2.get(i).getNomeentregador().equals(listarVendedores.get(j).getNomecobrador())) {
					check = true;
				}
			}
			if (check == false) {
				chec.add(listarVendedores2.get(i).getNomeentregador());
			}
		}

		for (int i = 0; i < listarVendedores.size(); i++) {
			check = false;
			for (int j = 0; j < listarVendedores2.size(); j++) {
				if (listarVendedores.get(i).getNomecobrador().equals(listarVendedores2.get(j).getNomeentregador())) {
					check = true;
				}
			}

			if (check == false) {
				chec2.add(listarVendedores.get(i).getNomecobrador());
			}
		}

		if (chec.size() != 0) {
			for (int i = 0; i < chec.size(); i++) {
				listarVendedores.add(new Operacao(chec.get(i), 0.0, 0.0, 0.0));
			}
		}

		if (chec2.size() != 0) {
			for (int i = 0; i < chec2.size(); i++) {
				listarVendedores2.add(new Operacao(chec2.get(i), 0.0, 0.0));
				listarVendedores3.add(new Operacao(chec2.get(i), 0.0, 0.0, 0.0));
			}
		}

		ordenaPorNome(listarVendedores2);
		ordenaPorNome2(listarVendedores3);
		ordenaPorNome2(listarVendedores);
		
		
		
		for (int i = 0; i < listarVendedores3.size(); i++) {
			
		}

		for (int i = 0; i < listarVendedores2.size(); i++) {
			
		}

		for (int i = 0; i < listarVendedores.size(); i++) {
			
		}

		System.out.println(listarVendedores3.size() + " " + listarVendedores2.size() + " " + listarVendedores.size());
		

		for (int i = 0; i < listarVendedores2.size(); i++) {

			for (int j = 0; j < listarVendedores.size(); j++) {

				if (listarVendedores2.get(i).getNomeentregador().equals(listarVendedores.get(i).getNomecobrador())) {
					
				} else {
				 listarVendedores.remove(i);
				
				}
			}
		}

		for (int i = 0; i < listarVendedores.size(); i++) {
			System.out.println(listarVendedores3.get(i).getValorCobrador());
			
			data.add(new Operacao(listarVendedores.get(i).getNomecobrador(),
					listarVendedores2.get(i).getTotal_entregador(),
					(listarVendedores2.get(i).getLucroVendedorRel() + listarVendedores3.get(i).getMulta_diaria()),
					listarVendedores.get(i).getDiario(), listarVendedores.get(i).getMulta_diaria(),
					listarVendedores3.get(i).getMulta_diaria(),
					(listarVendedores.get(i).getDiario() + listarVendedores.get(i).getMulta_diaria()
							+ listarVendedores.get(i).getTotalcobrador()),
					listarVendedores2.get(i).getLucroVendedorRel(), listarVendedores3.get(i).getDiario(),
					listarVendedores.get(i).getTotalcobrador(), listarVendedores3.get(i).getTotalcobrador(),
					listarVendedores3.get(i).getLucroMesFuncionario()));

			valorReceber = listarVendedores3.get(i).getLucroMesFuncionario() + valorReceber;
			valorVendido = listarVendedores2.get(i).getTotal_entregador() + valorVendido;
			comissao = listarVendedores2.get(i).getLucroVendedorRel() + comissao;
			valorPassando = listarVendedores3.get(i).getMulta_diaria() + valorPassando;
			lucroVendedor = listarVendedores2.get(i).getLucroVendedorRel() + listarVendedores3.get(i).getMulta_diaria()
					+ lucroVendedor;
			lucroEntregador = listarVendedores3.get(i).getTotalcobrador() + lucroEntregador;
			lucroCobrador = listarVendedores3.get(i).getDiario() + lucroCobrador;
			totalRecebidoParcelas = listarVendedores.get(i).getDiario() + totalRecebidoParcelas;
			totalRecebidoMulta = listarVendedores.get(i).getMulta_diaria() + totalRecebidoMulta;
			totalPassando = listarVendedores.get(i).getTotalcobrador() + totalPassando;
			totalRecebidoGeral = (listarVendedores.get(i).getDiario() + listarVendedores.get(i).getMulta_diaria()
					+ listarVendedores.get(i).getTotalcobrador()) + totalRecebidoGeral;

		}
		
		data.add(new Operacao(total, round (valorVendido,2), round(lucroVendedor,2), round(totalRecebidoParcelas,2), round(totalRecebidoMulta,2),
				round (valorPassando,2), round (totalRecebidoGeral,2), round (comissao , 2),  round (lucroCobrador,2),  round (totalPassando,2), round (lucroEntregador,2),
				round (valorReceber,2)));
		vendedorTable.setItems(data);

		nomeColum.setCellValueFactory(new PropertyValueFactory<Operacao, String>("nomecobrador"));

		vendidoColum.setStyle("-fx-alignment: CENTER;");
		vendidoColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("total_entregador"));

		comissaoColum.setStyle("-fx-alignment: CENTER;");
		comissaoColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("lucroVendedorRel"));

		lucroColum.setStyle("-fx-alignment: CENTER;");
		lucroColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("valorCobrador"));

		diarioColum.setStyle("-fx-alignment: CENTER;");
		diarioColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("diario"));

		multaColum.setStyle("-fx-alignment: CENTER;");
		multaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("multa_diaria"));

		porforaColum.setStyle("-fx-alignment: CENTER;");
		porforaColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("totalcobrador"));

		cobraLucroColum.setStyle("-fx-alignment: CENTER;");
		cobraLucroColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("cobradorReceb"));

		totalColum.setStyle("-fx-alignment: CENTER;");
		totalColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("total"));

		totalPassColum.setStyle("-fx-alignment: CENTER;");
		totalPassColum.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("totalPass"));

		vendidoRecec.setStyle("-fx-alignment: CENTER;");
		vendidoRecec.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("recerbLuc"));

		lucroEntrega.setStyle("-fx-alignment: CENTER;");
		lucroEntrega.setCellValueFactory(new PropertyValueFactory<Operacao, Double>("entregaLuc"));
	}
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	private static void ordenaPorNome(List<Operacao> lista) {
		Collections.sort(lista, new Comparator<Operacao>() {
			@Override
			public int compare(Operacao o1, Operacao o2) {
				return o1.getNomeentregador().compareTo(o2.getNomeentregador());
			}		
		});
	}
	
	private static void ordenaPorNome2(List<Operacao> lista) {
		Collections.sort(lista, new Comparator<Operacao>() {
			@Override
			public int compare(Operacao o1, Operacao o2) {
				return o1.getNomecobrador().compareTo(o2.getNomecobrador());
			}		
		});
	}

	public void start(Stage palcoTelaLucroVendedor) throws Exception {
		this.palcoTelaLucroVendedor = palcoTelaLucroVendedor;

		try {
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaLucroVendedor.fxml"));

			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);

			// origemTela.getStylesheets().add("origemTela.css");

			// cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaLucroVendedor.setScene(cena);

			palcoTelaLucroVendedor.setTitle("Fechamento Geral");

			palcoTelaLucroVendedor.getIcons().add(new Image("file:resources/images/address_book_32.png"));

			palcoTelaLucroVendedor.setResizable(false);

			palcoTelaLucroVendedor.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					// System.out.println("Stage is closing");
					ControleTelaInicial.setCodLucroVendedor(0);
				}
			});
			palcoTelaLucroVendedor.close();

			palcoTelaLucroVendedor.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}