package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conect {
	//admin454
	private String driver = "org.postgresql.Driver";  
	private String user   = "eletroja_postgres2";  
	private String senha = "admin454";  
	private String url      = "https://linux04.nuvemidc.com:2083/cpsess3629667046/3rdparty/phpPgAdmin/index.php";  
	
	public void conect (){
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
    
    
}
