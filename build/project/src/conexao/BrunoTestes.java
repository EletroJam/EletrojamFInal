package conexao;

import java.io.InputStream;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fachada.EletroJam;
import negocio.Status;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class BrunoTestes {
	
	private static String getDateTime() { DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	return dateFormat.format(date); }
	
	public void gerar( String layout ) throws JRException , SQLException, ClassNotFoundException {
		//gerando o jasper design
		JasperDesign desenho = JRXmlLoader.load( layout );
   
		//compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport( desenho );
   
		//estabelece conexão
		 String driver = "org.postgresql.Driver";  
		 String user   = "postgres";  
		 String senha = "admin123";  
		 String url      = "jdbc:postgresql://localhost:5432/EletroJam"; 
		 
		 double fg = 34.67; 
		
		 Class.forName(driver);  
         Connection con = null;  

         con = (Connection) DriverManager.getConnection(url, user, senha);  
         
         Statement stm = con.createStatement( );

		
		String query = "select numero_parcelas, valor_recebido from status";
		ResultSet rs = stm.executeQuery( query );

		//implementação da interface JRDataSource para DataSource ResultSet
		JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
   
		//executa o relatório
		Map parametros = new HashMap();
		
		
		java.util.Date dataAtual = new java.util.Date(); 
		java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());

		
		
		parametros.put("funcionario", "Vendedor junior");
		parametros.put("data_cobranca", dataSQL);
		JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros,    con );
   
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( impressao , true );
		viewer.setVisible(true);
		
		//JasperExportManager.exportReportToPdfFile(impressao, "C:/Users/ezequiel/Desktop/status.pdf");
		//JasperExportManager.exportReportToWord
		
		//Parameter1
	}
	

	public BrunoTestes(){
		
	}



	public static void main(String[] args) throws ClassNotFoundException, JRException, SQLException, RepositoryException{
		
		java.util.Date dataAtual = new java.util.Date(); 
		java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());
		
		//EletroJam fachada = EletroJam.getInstance();
		//fachada.geraRelatorioCobranca("Vendedor junior", dataSQL);
		//new BrunoTestes().gerarRelatorio();
		
		
		try {
			new BrunoTestes().gerar("src/status.jasper");
		} catch (Exception e) {
			e.printStackTrace();
		}


		// RepositorioPessoaDB r = new RepositorioPessoaDB ();

	}
	
	
	public  void gerarRelatorio () throws JRException, ClassNotFoundException, SQLException, RepositoryException{
		
 		 
		
		 String driver = "org.postgresql.Driver";  
		 String user   = "Ezequiel";  
		 String senha = "admin123";  
		 String url      = "jdbc:postgresql://localhost:5432/EletroJam"; 
		
		 Class.forName(driver);  
         Connection con = null;  

         con = (Connection) DriverManager.getConnection(url, user, senha);  
         
         Statement stm = con.createStatement( );
         String query = "select * from status";
         ResultSet rs = stm.executeQuery( query );
         /* implementação da interface JRDataSource para DataSource ResultSet */
         JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
		
      
         EletroJam fachada = EletroJam.getInstance();
         
         Status status = fachada.getStatus(1);
         

		  InputStream inputStream = getClass().getResourceAsStream( "C:/Users/ezequiel/Desktop/teste.jrxml" );

		//Connection con = (Connection) new Conect(); 
         Map<String, Object> parametros = new HashMap<String, Object>();
         parametros.put("numero_parcelas", status.getNumero_parcelas());
         parametros.put("valor_recebido", status.getValor_Recebido());
        
		
       
		JasperPrint jp = JasperFillManager.fillReport("C:/Users/ezequiel/Desktop/teste.jrxml", parametros, con);
		JasperViewer jw = new JasperViewer(jp);
		jw.setVisible(true);
		
		//JasperExportManager jo = new 
		
		
	}
	
	public void gerar2( String jasperFile ) throws JRException , SQLException, ClassNotFoundException {

		 String driver = "org.postgresql.Driver";  
		 String user   = "Ezequiel";  
		 String senha = "admin123";  
		 String url      = "jdbc:postgresql://localhost:5432/EletroJam"; 
		
		 Class.forName(driver);  
         Connection con = null;  

         con = (Connection) DriverManager.getConnection(url, user, senha);  
         
		String query = "select numero_parcelas, valor_recebido from status";
		Statement stm = con.createStatement( );
		ResultSet rs = stm.executeQuery( query );

		//implementação da interface JRDataSource para DataSource ResultSet
		JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
   
		//executa o relatório
		Map parametros = new HashMap();
		parametros.put("nota", new Double(10));

		/* Preenche o relatório com os dados. Gera o arquivo BibliotecaPessoal.jrprint    */
		JasperFillManager.fillReportToFile( jasperFile, parametros, jrRS );
  
		/* Exporta para o formato PDF */
		JasperExportManager.exportReportToPdfFile( "untitled_report_1.jrprint" );
	}
	


}



/*
 * 
 * if (diaSemana.equals("Sabado") && (listarStatus.get(i).getTipo_pagamento().equals("Segunda a Sabado") || listarStatus.get(i).getTipo_pagamento().equals("Segunda a Domingo"))
	       					&& listarStatus.get(i).isCheck_pag() == false){
	       				
	       				
	       				data.add(new Status(listarStatus.get(i).getId(),listarStatus.get(i).getCliente(),listarStatus.get(i).getCobrador(), listarStatus.get(i).getNumero_parcelas(),
	    	        			typData.get(0),listarStatus.get(i).getMulta(), "0", "0", listarStatus.get(i).getAtraso(), listarStatus.get(i).getParcela_atual()
	    	        			 )); 
	       				System.out.println("ta AQUI 22222222");
	       			}else if (diaSemana.equals("Domingo") && listarStatus.get(i).getTipo_pagamento().equals("Segunda a Domingo") && listarStatus.get(i).isCheck_pag() == false){
	       				data.add(new Status(listarStatus.get(i).getId(),listarStatus.get(i).getCliente(),listarStatus.get(i).getCobrador(), listarStatus.get(i).getNumero_parcelas(),
	       						typData.get(0),listarStatus.get(i).getMulta(), "0", "0", listarStatus.get(i).getAtraso(), listarStatus.get(i).getParcela_atual()
	    	        			  ));
	       				System.out.println("ta AQUI 11111111");
	       				
	       			}else if (listarStatus.get(i).isCheck_pag() == false){
	       				
	       				data.add(new Status(listarStatus.get(i).getId(),listarStatus.get(i).getCliente(),listarStatus.get(i).getCobrador(), listarStatus.get(i).getNumero_parcelas(),
	       						typData.get(0),listarStatus.get(i).getMulta(), "0", "0", listarStatus.get(i).getAtraso(), listarStatus.get(i).getParcela_atual()
	    	        			 ));
	       				System.out.println("ta AQUI3333333333");
	       			}else{
	       				
	       			}
	       			*/









/*
if (pagamentoRow.getTyp().equals("Parcela")){
	                    	
	                    	double valor_recebido = op.getValorDiario();
	                    	
	                    	try {
								fachada.updateStatus(true, valor_recebido,  idRow, 1, 0 , pagamentoRow.getTyp(), parcelasRow);
							} catch (Exception e) {
								
								e.printStackTrace();
							}           	
	                    	//tudo ok pegando text field
	                    	//String aux = (String)  numeroParcelasRow ;
	                    	//System.out.println(aux + " auxxx    " + aux);
	                    	
	                    	//String aux = (String) numeroParcelasRow ;
	                    	//System.out.println (aux + "auxxxx AQUIII");
	                    	
	                 /*   	String aux = numeroParcelasRow.getTyp();
	                    	
	                    	System.out.println(aux + " auxxx    " + numeroParcelasRow.getTyp());
	                    	
	                    	int idnew = Integer.parseInt(aux.trim());
	                    	
	                    	if(idnew != 0){
	                    		idnew = idnew -1;
	                    	}else{
	                    		//	todo
	                    	}
	                   
	                    	System.out.println("parcelasRow " + parcelasRow + " parcelaAtual " + parcelaAtual);
	                   if(parcelaAtual != 0){

	                    	if (parcelasRow != parcelaAtual ){
	                    		
	                    		int parc = parcelaAtual + 1;
	                    		
	                    		System.out.println("entrou aqui");
		                    	
	                    		if (old.getTipo_pagamento().equals("Segunda a Sexta")  && diaSemanaM.equals("Sexta")){
	                    			
	                    			Status newStatus = new Status(parcelasRow , 0, old.getId_operacao(), old.getTipo_pagamento(), pagamentoRow.getTyp(), local3 , multaRow, false, 0 ,0, atraso, parc);
	                    			System.out.println (local3 + " deve ser 2 superior");
	                    			try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
	                    		} else if (old.getTipo_pagamento().equals("Segunda a Sabado") && diaSemanaM.equals("Sabado") ){
	                    			
	                    			Status newStatus = new Status(parcelasRow , 0, old.getId_operacao(), old.getTipo_pagamento(), pagamentoRow.getTyp(), local2 , multaRow, false, 0 ,0, atraso, parc);
	                    			System.out.println (local2 + " ta aqui vendo a data data +1");
	                    			try {
										fachada.insertStatus(newStatus);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
	                    		}else{
	                    			
	                    			if (multaRow != 0){
	                    				Status newStatus = new Status(parcelasRow , 0, old.getId_operacao(), old.getTipo_pagamento(), pagamentoRow.getTyp(), local1 , multaRow, false, 0 ,0, atraso, 0);
		                    			System.out.println (local1 + " ta aqui vendo a data data +1");
		                    			try {
											fachada.insertStatus(newStatus);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
	                    			}else{
	                    				try {
											fachada.updateStatus(true, valor_recebido,  idRow, 1, 0 , pagamentoRow.getTyp(), parcelasRow);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
	                    			}
	                    			
	                    		
	                    		}
	
	                    	}
	                   }else{
	                	   	
	                	   if (multaRow != 0){
               				Status newStatus = new Status(parcelasRow , 0, old.getId_operacao(), old.getTipo_pagamento(), pagamentoRow.getTyp(), local1 , multaRow, false, 0 ,0, atraso, 0);
                   			System.out.println (local1 + " ta aqui vendo a data data +1");
                   			try {
									fachada.insertStatus(newStatus);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
               			}else{
               				try {
									fachada.updateStatus(true, valor_recebido,  idRow, 1, 0 , pagamentoRow.getTyp(), parcelasRow);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
               			}
	                   }
	                   
	                    	
	                    }
	                    
	                   */
