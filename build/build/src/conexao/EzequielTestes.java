package conexao;

import java.text.ParseException;

import exceptions.EnderecoNaoEncontrado;
import exceptions.FuncionarioJaCadastradoException;
import exceptions.FuncionarioNaoEncontradoException;
import exceptions.PessoaJaCadastradaException;
import exceptions.PessoaNaoEncontradaException;
import fachada.EletroJam;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class EzequielTestes {
	


	
	

	public static void main(String[] args) throws PessoaNaoEncontradaException, PessoaJaCadastradaException, FuncionarioJaCadastradoException, EnderecoNaoEncontrado, FuncionarioNaoEncontradoException, RepositoryException, ParseException, JRException {
		// TODO Auto-generated method stub
		EletroJam fachada = EletroJam.getInstance();
		//Date data = new java.sql.Date(new java.util.Date().getDay()); 
		//System.out.println(data);
		//fachada.getIdNome(id);
		
		//Log log = new Log(1, 1, 1, null);
		
		//fachada.insert(log);
		
		/*
		SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy"); 
		Date z=new Date(); 
		System.out.println(sdf2.format(z));

		java.sql.Date o = new java.sql.Date(sdf2.parse(sdf2.format(z)).getTime()); 
		java.sql.Date p = new java.sql.Date(sdf2.parse(sdf2.format(z)).getTime()); 
		
		 
		
		//String r = "29/01/2016";
		//java.sql.Date  p = java.sql.Date.valueOf(z);
		
		System.out.println(o + " "+ p);
		
		ArrayList<Operacao> y = fachada.getCobradoresComissao(o,p);
		
		
		for (int i = 0; i < y.size(); i++) {
			System.out.println("oi");
			System.out.println(y.get(i).getNomecobrador());
		}
		*/
		//System.out.println(fachada.getFuncionarioNome(6));
		
		/*
		ArrayList<Status> listarHistoricoDet = new ArrayList();
		
		try {
			listarHistoricoDet = fachada.AllStatusCliente(27);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < listarHistoricoDet.size(); i++) {
			System.out.println(listarHistoricoDet.size());
			//private Date data_dia;
			//dataInicialPagamento

			System.out.println(listarHistoricoDet.get(i).getDataInicialPagamento() + " espac " +  listarHistoricoDet.get(i).getCobrador()
					+ " " + listarHistoricoDet.get(i).getFunc() + " "+ listarHistoricoDet.get(i).getData() + " "
					);
			System.out.println(listarHistoricoDet.get(i).getId());
		}
		*/
		
		/*
		SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 

		Date hoje = new Date();
		DateTime date = DateTime.parse(sdf2.format(hoje), DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
		org.joda.time.DateTime jodal1 = date;
		
		org.joda.time.DateTime joda1 = new org.joda.time.DateTime(jodal1.plusDays(60) );
		org.joda.time.DateTime joda2 = new org.joda.time.DateTime(jodal1.plusMonths(2) );
		System.out.println(joda2);
		//System.out.println(jodal1);
		jodal1.getDayOfMonth();
		//org.joda.time.DateTime joda6 = new org.joda.time.DateTime(jodal1.dayOfMonth());
		jodal1.dayOfMonth();
		jodal1.monthOfYear();
		jodal1.year();
		
		
		Date d = new Date();

		Calendar c = Calendar.getInstance();
		c.setTime(d);

		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 10);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
*/
		//System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(c.getTime()));
		
		/*
		ArrayList<Cliente> x = fachada.getClientesHistorico(4);
		
		for (int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i).getId_operacao() + " " + x.get(i).getEstadoo());
		}*/
		/*
		
		SimpleDateFormat sdf2= new SimpleDateFormat("dd/MM/yyyy"); 
		Date z=new Date(); 
		System.out.println(sdf2.format(z));

		java.sql.Date data = new java.sql.Date(sdf2.parse(sdf2.format(z)).getTime()); 
		
		System.out.println(fachada.getMulta(data) + "okkkkk");
		
		//fachada.updateOperacaoClienteEstado(id, estado);
		
		Double multa = fachada.getMulta(data);
	       Double parcela = fachada.getParc(data);
	       Double total = fachada.getTotal(data);
	       
	       System.out.println(multa + "multa" + parcela + "parcela" + total + "ok "  + data);
	       */
		
	      
		// String sourcePath = "relatorios/diario.jasper";
	    // String outputPath = "C:/Users/Ezequiel/Desktop/diarioooo.jrxml";   
		
		//////////////////////////////////////////////////////////////////
		String sourcePath = "relatorios/cobranca.jasper";
	    String outputPath = "C:/Users/Zeke/Desktop/test3.jrxml"; 
	    JasperDesign  jd  = JRXmlLoader.load(sourcePath);
     
	    JRXmlWriter.writeReport(jd, outputPath, "UTF-8");
		
	    ////////////////////////////////////////////////////////////////
	       /*
	       String sourcePath = "relatorios/diario.jasper";
		    String outputPath = "C:/Users/Ezequiel/Desktop/diii.jrxml"; 
	     JasperDesign  jd  = JRXmlLoader.load(sourcePath);
	     
	     JRXmlWriter.writeReport(jd, outputPath, "UTF-8");
	     
		ArrayList<String> f = new ArrayList<>();
		f = fachada.AllRotas();
		
		for (int i = 0; i < f.size(); i++) {
			System.out.println(f.get(i) + "ta aqui o teste");
		}
		Map<String, Integer> example = new HashMap<String, Integer>();
		
		
		ArrayList <Integer> arrayParc = new ArrayList() ;
		
		arrayParc = fachada.getAllParc(20);
		
		for (int i = 0; i < arrayParc.size(); i++) {
			
			System.out.println(arrayParc.get(i) + " hhuhuhuh");
		}
		
		System.out.println(arrayParc.size() + "hhuhuhuh");
		*/
		
		/*
		Date dataAtual = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		String data = sdf.format(dataAtual);  
		
		System.out.println(dataAtual);
		*/
		
		//java.util.Date dataAtual = new java.util.Date();   
		//usando o Date de sql  
		// passando como parametro a data atual  
		//java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());  
		
		/*
		Date x=new Date();
		String x=request.getParameter("dataUsuario");
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
		Date dataUsuario=sdf1.parse(stx);

		*/
		
		//OperacaoCliente opCliente = fachada.getOperacaoCliente(13);
		
		//System.out.println(opCliente.getQuantidade_atraso() + "aqui o");
		
		
		/*
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
		Date y=new Date(); 
		System.out.println(sdf1.format(y));

		//java.sql.Date data = new java.sql.Date(sdf1.parse(sdf1.format(y)).getTime()); 
		System.out.println(data);
		//String x = sdf1.format(y);
		
		//Log log = new Log (1,1,1, null);
		//fachada.insert(log);
		
		Date d = new Date();  
        Calendar c = new GregorianCalendar();  
        c.setTime(d);  
        String nome = "";  
        int dia = c.get(c.DAY_OF_WEEK);  
        switch(dia){  
          case Calendar.SUNDAY: nome = "Domingo";break;  
          case Calendar.MONDAY: nome = "Segunda";break;  
          case Calendar.TUESDAY: nome = "Terça";break;  
          case Calendar.WEDNESDAY: nome = "Quarta";break;  
          case Calendar.THURSDAY: nome = "Quinta";break;  
          case Calendar.FRIDAY: nome = "Sexta";break;  
          case Calendar.SATURDAY: nome = "sábado";break;  
        }  
        System.out.println(nome);  
        */
		
		/*
		String data = "15/08/2006";  
		String sql = "Insert into tabela (campo_date) values to_date("'+data+'",'dd/mm/yyyy')";  
		stmt.execute(sql);  
		*/
		
		
		
		/*
		
		ArrayList<Funcionario> funcionariosVendedores = new ArrayList();
		ArrayList<String> funcionarioVendedorNome = new ArrayList();
		Pessoa funcionarioVendedor;
		
		ArrayList<Status> status = new ArrayList();
		
		
		funcionariosVendedores =  fachada.getFuncionariosVendedores();
		status = fachada.AllStatusCliente();
		
		for (int i = 0; i < status.size(); i++) {
			System.out.println(status.get(i).getCliente() + " ok");
			
			//example.put(funcionarioVendedor.getNome(), funcionariosVendedores.get(i).getId_funcionario());
			//System.out.println(example.keySet());
		}
		*/
		
		/*
		for (int i = 0; i < funcionariosVendedores.size(); i++) {
			System.out.println(funcionariosVendedores.get(i).getId_funcionario() + " ok");
			funcionarioVendedor = fachada.getPessoaId(funcionariosVendedores.get(i).getId_funcionario());
			funcionarioVendedorNome.add(funcionarioVendedor.getNome());
			System.out.println(funcionarioVendedor.getNome());
			//example.put(funcionarioVendedor.getNome(), funcionariosVendedores.get(i).getId_funcionario());
			//System.out.println(example.keySet());
		}
		*/
		/*
		for(String key : example.keySet()){
			
			Integer value = example.get(key); 
			System.out.println(key + " = " + value);

			
		}
		*/
		
		/*
			String driver = "org.postgresql.Driver";  
	        String user   = "Ezequiel";  
	        String senha = "admin123";  
	        String url      = "jdbc:postgresql://localhost:5432/EletroJam";  
	        Statement stmt = null;
	  
	        try  
	        {  
	            Class.forName(driver);  
	            Connection con = null;  
	  
	            con = (Connection) DriverManager.getConnection(url, user, senha);  
	  
	            System.out.println("Conexão realizada com sucesso.");  
	          
	        }  
	        catch (ClassNotFoundException ex)  
	        {  
	            System.err.print(ex.getMessage());  
	        }   
	        catch (SQLException e)  
	        {  
	            System.err.print(e.getMessage());  
	        }  
	    }  

	  /* insert
    stmt = con.createStatement();
    String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
          + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
    stmt.executeUpdate(sql);
		*/
			
	        
	  	 /*
	    	 String nome2 = "Ezequiel";
	    	// String cpf = "123"; 
	    	 String cpf = "123"; 
	    	 String identidade = "7988694" ;
	    	 int id_endereco = 1;
	    	 byte[] foto = null;
	    	 String sexo = "M";
	    	 String email = "Ezequiel123@gmail.com";
	    	 String email1 = "Ezequiel45454@gmail.com";
	    	 
	    	*/ 
	    	 
	    //	 Operacao operacao = new Operacao (10, 20, 200, 20, 12, "A", 10, 1, 0, 6, 6, 220, 50, 10);
	    	// fachada.inserteOperacao(operacao);
	    	 
	    	 
	    	// Date data = null;
	
		//Cliente cliente = new Cliente (nome, cpf, identidade, id_endereco, foto, sexo, email, data);
		
		
		
		
	/*
			String nome = "lala";
	    	// String cpf = "11122233344"; 
	    	String cpf = "123456977712"; 
	    	String identidade = "7988695" ;
	    	int id_endereco = 1;
	    	byte[] foto = null;
	    	String sexo = "M";
	    	String email = "lucas123@gmail.com";
	    	String email1 = "lucas45454@gmail.com";
	    	Date data = null;
	    	String login = "lop";
	    	String senha = "123";
	    	String cargo = "cobrador"; 
	    	*/
	    	
	    //	Funcionario funcionario = new Funcionario (nome, cpf, identidade, id_endereco,  sexo, email1, data, foto, login, cargo, senha);
	    	
	   // Funcionario funcionario = null;
		
		/*
		String cep = "54430250"; 
		String logadouro ="rua das negas";
		int numero = 1471 ;
		String pais = "Brasil";
		String uf = "bh";
		String cidade = "das novinhas";
		String estado = "pernambuco";
		int id_endereco = 4;
		Endereco endereco = new Endereco (cep, logadouro, numero, pais, uf, cidade, estado);
		*/
		
		
		//RepositorioPessoaDB r = new RepositorioPessoaDB ();
		
		
		
	}
	

}
