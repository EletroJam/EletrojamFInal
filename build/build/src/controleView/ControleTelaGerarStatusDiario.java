package controleView;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import conexao.RepositoryException;
import exceptions.PessoaNaoEncontradaException;
import fachada.EletroJam;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import negocio.Funcionario;
import negocio.Pessoa;
import net.sf.jasperreports.engine.JRException;
import util.AutoCompleteComboBoxListener;

public class ControleTelaGerarStatusDiario extends Application implements Initializable{

	public static Stage palcoTelaStatus = null;
	EletroJam fachada = EletroJam.getInstance();

	@FXML
	DatePicker dData;
	
	@FXML
	ComboBox<Funcionario> cCobrador;
	
	@FXML
	Button bGerar;
	
	
	//@FXML 
	//ComboBox<String> cRota;
	
	@FXML
	public void eventoBtnGerar (ActionEvent event) throws RepositoryException{
		//String cobradorNome = null;
		//cobradorNome = cCobrador.getValue();
		
		LocalDate dNascimento = null;
		dNascimento = dData.getValue();
		/*
		String rotaCob = null;
		rotaCob = cRota.getValue();
		*/
		Date dataNascimento;
		
		SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		
		
		
		if(dNascimento == null ){
			java.util.Date dataAtual = new java.util.Date(); 
			java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime()); 
			//java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime()); 
			dataNascimento =  dataSQL;
		}else{
			
			dataNascimento = Date.valueOf(dNascimento);
			//System.out.println(dataNascimento);
		}
		
		Calendar c = new GregorianCalendar();  
        c.setTime(dataNascimento);  
        
        String diaSemana = "";  
        
        boolean control1 = false; //seg-sex
        boolean control2 = false; // seg-sab
        boolean control3 = false; //seg dom
        
        int dia = c.get(c.DAY_OF_WEEK);  
        switch(dia){  
          case Calendar.SUNDAY: diaSemana = "Domingo";break;  
          case Calendar.MONDAY: diaSemana = "Segunda";break;  
          case Calendar.TUESDAY: diaSemana = "Terça";break;  
          case Calendar.WEDNESDAY: diaSemana = "Quarta";break;  
          case Calendar.THURSDAY: diaSemana = "Quinta";break;  
          case Calendar.FRIDAY: diaSemana = "Sexta";break;  
          case Calendar.SATURDAY: diaSemana = "Sabado";break;  
        }  
        
      
 
		
		
		String diaSemanaMM = diaSemana;
		
		
		if (diaSemana == "Domingo"){
			
			DateTime date = DateTime.parse(sdf2.format(dataNascimento), DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
			
			org.joda.time.DateTime jodal1 = date;
			//System.out.println(jodal1+ "JODALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
			//org.joda.time.LocalDate localaux1 = jodal1.toDateTime().toLocalDate();
			
			org.joda.time.DateTime joda1 = new org.joda.time.DateTime(jodal1.minusDays(1));
			String auxl1 = joda1.toLocalDate().toString("yyyy-MM-dd");
			
		    Date local11 = null;
				
				
				
			if(auxl1 == null ){
				java.util.Date dataAtual = new java.util.Date(); 
				java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime()); 
					//java.sql.Date dataSQ1L = new java.sql.Date(dataAtual.getTime()); 
				local11 =  dataSQL;
			}else{
					
				local11 = Date.valueOf(auxl1);
					//System.out.println(dataNascimento);
			}
		//	System.out.println(local11);
			
			 Double multa = fachada.getMulta(dataNascimento, cCobrador.getValue().getNome());
		       Double parcela = fachada.getParc(dataNascimento, cCobrador.getValue().getNome());
		       //Double total = fachada.getTotal(local11);
		       double total = multa + parcela;
		      // System.out.println("aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+ multa + " " + parcela + " " + total);
		      // System.out.println(multa + "multa" + parcela + "parcela" + total + "ok "  + local11 + "LOCAL" + "data "+ rotaCob + dataNascimento + cobradorNome);
			
			try {
				fachada.relatorioDia(cCobrador.getValue().getNome(), local11, "relatorios/diario.jasper" ,BigDecimal.valueOf(multa), BigDecimal.valueOf(parcela),  BigDecimal.valueOf(total));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			
		       Double multa = fachada.getMulta(dataNascimento, cCobrador.getValue().getNome());
		       Double parcela = fachada.getParc(dataNascimento, cCobrador.getValue().getNome());
		       double total = multa + parcela;
		       
		     //  System.out.println(multa + "multa" + parcela + "parcela" + total + "ok "  + dataNascimento + "data "+ rotaCob + dataNascimento + cobradorNome);
				
			
			try {
				fachada.relatorioDia(cCobrador.getValue().getNome(), dataNascimento, "relatorios/diarioDomingo.jasper",BigDecimal.valueOf(multa), BigDecimal.valueOf(parcela),  BigDecimal.valueOf(total) );
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		new AutoCompleteComboBoxListener(cCobrador);
		ArrayList<Funcionario> funcionariosCobrador = new ArrayList();
	
		
		EletroJam fachada = EletroJam.getInstance();
		
		try {
			funcionariosCobrador = fachada.getFuncionariosCobradores();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cCobrador.getItems().addAll(funcionariosCobrador);
		cCobrador.getSelectionModel().getSelectedIndex();
		cCobrador.setCellFactory(new Callback<ListView<Funcionario>,ListCell<Funcionario>>(){
			 
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

	
	public void start(Stage primaryStage) throws Exception {
		palcoTelaStatus = primaryStage;
		
		try {		
			
			// Origem do arquivo FXML da tela
			Parent origemTela = FXMLLoader.load(getClass().getResource("/view/TelaGerarStatusDiario.fxml"));
		
			// Criar a cena com a origem da tela
			Scene cena = new Scene(origemTela);	
			
			//origemTela.getStylesheets().add("origemTela.css");
			
			
			//cena.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			// Definir a cena para a janela
			palcoTelaStatus.setScene(cena);
			
			palcoTelaStatus.setTitle("Recebimento Diario");
			
			palcoTelaStatus.getIcons().add(new Image("file:resources/images/address_book_32.png"));
			
			palcoTelaStatus.setResizable(false);
			
			palcoTelaStatus.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              //System.out.println("Stage is closing");
		              ControleTelaInicial.setCodGerarStatus(0);
		              System.out.println(ControleTelaInicial.getCodGerarStatus());
		          }
		      });        
			palcoTelaStatus.close();
			
		// Mostrar a janela/tela
			palcoTelaStatus.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

	
}
