package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import exceptions.ExceptionMessages;
import util.Constantes;

public class PersistenceMechanismRDBMS implements IPersistenceMechanism {
	
    private static PersistenceMechanismRDBMS singleton;
    private static int numConexoes = 1;
        
    private Connection conexoesCriadas[];
    private Connection conexoesLivres[];
    private HashMap conexoesAlocadas;
    private String classeDoDriver;
    private String url;
    private String login = "postgres";
    //private String senha;
    private boolean indisponivel;
	private Properties properties;

	
	PersistenceMechanismRDBMS(String url, String login, String senha, String classeDoDriver) throws PersistenceMechanismException {
	     this.classeDoDriver = classeDoDriver;
	    // this.url = url;

	    this.url = "jdbc:postgresql://pgsql03-farm68.kinghost.net:5432/eletrojam";
	     //this.conexoesAlocadas = new HashMap();
	     //this.login = login;
	     //this.senha = senha;
	     indisponivel = false;
	     	//url, senha, postgres
	     this.properties = new Properties();
	     properties.put("user", "eletrojam");
	     properties.put("password", "eletrojamadmin123");
	     //properties.put("user", "postgres");
	     //properties.put("password", "admin123");
	     properties.put("autoReconnect", "true");
	     //eletrojamadmin123
	     //eletrojam
	    	
	     try {
	    	 Class.forName(classeDoDriver);
	     	} catch (ClassNotFoundException e) {
	     		throw new PersistenceMechanismException("EXC_CLASSE_NAO_ENCONTRADA");
	        }
	    
	   	}
	   
	   
	public static synchronized PersistenceMechanismRDBMS getInstance() throws PersistenceMechanismException {
		
		if (singleton==null) {
			singleton = new PersistenceMechanismRDBMS(
						Constantes.URL,
					    Constantes.USER, Constantes.PASSWORD,
						Constantes.DRIVER);
		}
		return singleton;
		}
	   
	@Override
	public synchronized void connect() throws PersistenceMechanismException {
		if (conexoesCriadas == null) {
        	//System.out.println("Connecting to database");
        	 Statement stmt = null;
            try {
            	//System.out.println("chego aqui");
                this.conexoesLivres = new Connection[PersistenceMechanismRDBMS.numConexoes];
              //  System.out.println("chego aqui 1");
                this.conexoesCriadas = new Connection[PersistenceMechanismRDBMS.numConexoes];
              //  System.out.println("chego aqui 2");
                this.conexoesAlocadas = new HashMap();
              //  System.out.println("chego aqui3 ");
                for (int i = 0; i < PersistenceMechanismRDBMS.numConexoes; i++) {
                //	System.out.println(i);
                	conexoesCriadas[i] = DriverManager.getConnection(url, properties);
                //	System.out.println(properties);
                	
                	
                	//conexoesCriadas[i] = DriverManager.getConnection(url, login, senha);
                    conexoesLivres[i] = conexoesCriadas[i];
                //    System.out.println("passou");
                    
                    
                    
                    /*
                    stmt = conexoesCriadas[i].createStatement();
                    String sql = "INSERT INTO endereco (logadouro,cidade,estado,uf,pais,cep) "
                          + "VALUES ('de', 'Paul', 'ddf', 'California', 'ddd','dfw' );";
                    stmt.executeUpdate(sql);
                 */
                    
                    
                    
                }
                //SendEmail.getInstance().sendEmailConexaoBanco(Constant.SERVER);
            } catch (Exception e) {     
            	//System.out.println("not here please");
            	e.printStackTrace();
                throw new PersistenceMechanismException(ExceptionMessages.EXC_CONECTAR);
            }
        }
		// TODO Auto-generated method stub
		
	}
	
    private boolean testar(Connection resposta) throws SQLException, PersistenceMechanismException {
 		if (!resposta.isValid(0) ){
 			try {
 				this.disconnect();
 			} catch (Exception e) {
 				e.printStackTrace();
 				try {
					//SendEmail.getInstance().sendEmailError(e, null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}
 			this.connect();
 			return false;
 		}	
 		return true;
 	}
    
	
	public synchronized Object getCommunicationChannel() throws PersistenceMechanismException {
	        try {
	            return getCommunicationChannel(false).createStatement();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_GET_CANAL_COMUNICACAO);
	        }            
	    }
	
	public synchronized Connection getConnection() throws PersistenceMechanismException {
        try {
            return getCommunicationChannel(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_GET_CONNECTION);
        }            
    }

	@Override
	public void disconnect() throws PersistenceMechanismException {
		// TODO Auto-generated method stub
	//	System.out.println("Disconnecting to database");
        if (conexoesCriadas != null) {
            int fechadas = 0;
            for (int i = 0; i < PersistenceMechanismRDBMS.numConexoes; i++) {
                if (conexoesCriadas[i] != null) {
                	try {
                		conexoesCriadas[i].close();
                	} catch(SQLException e) {e.printStackTrace();}
                    fechadas++;
                }
            }
            this.conexoesCriadas = null;
            this.conexoesAlocadas = null;
            this.conexoesLivres = null;
        }
		
	}
	
	public synchronized void releaseCommunicationChannel() throws PersistenceMechanismException {
		releaseCommunicationChannel(false);
	}
   
	public synchronized void beginTransaction() throws TransactionException {
		try {
			while (indisponivel)  {
				wait();
			}
			Connection con = (Connection)getCommunicationChannel(true);
			con.setAutoCommit(false);            
		} catch (Exception e) {           
			e.printStackTrace();
			throw new TransactionException("EXC_INICIAR_TRANSACAO");
		}
	}

	

	public synchronized void commitTransaction() throws TransactionException {
		try {
			Connection con = (Connection)getCommunicationChannel(true);
			con.commit();
			con.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {            
			e.printStackTrace();
			throw new TransactionException("EXC_CONFIRMAR_TRANSACAO");
		} finally {
			notifyAll();
		}
	}
	
	public synchronized void rollbackTransaction() throws TransactionException {
		try {
			Connection con = (Connection)getCommunicationChannel(true);
			con.rollback();
			con.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {           
			e.printStackTrace();
			throw new TransactionException("EXC_CANCELAR_TRANSACAO");
		} finally {
			try {
				notifyAll();
			} catch (Exception e) {
                
			}
		}
	}
	
	
	  private synchronized Connection getCommunicationChannel(boolean porTransacao) 
	            throws PersistenceMechanismException {
	        Connection resposta = null;
	        try {
	            Thread currentThread = Thread.currentThread();
	            int threadId = currentThread.hashCode();
	            if (conexoesAlocadas.containsKey("" + threadId)) {
	                resposta = (Connection) conexoesAlocadas.get("" + threadId);
	            } else {
	                boolean achou = false;
	                do {
	                    if (achou) {
	                        break;
	                    }
	                    for (int i = 0; !achou && i < PersistenceMechanismRDBMS.numConexoes; i++) {
	                        if (conexoesLivres[i] == null) {
	                            continue;
	                        }
	                        achou = true;
	                        resposta = conexoesLivres[i];
	                        conexoesLivres[i] = null;
	                        conexoesAlocadas.put("" + threadId, resposta);
	                        if (porTransacao) {
	                            conexoesAlocadas.put("T" + threadId, null);
	                        }
	                    }

	                    if (!achou) {
	                        indisponivel = true;
	                        wait();
	                    }
	                } while (true);
	            }
	          //TESTA A CONEX�O E RECONECTA 
	            if (! this.testar(resposta)) {
	            	resposta = this.getCommunicationChannel(porTransacao);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        	throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_GET_CANAL_COMUNICACAO_CONCORRENCIA);
	        }
	        return resposta;
	    }
	  
	  
		private synchronized void releaseCommunicationChannel(boolean porTransacao)  throws PersistenceMechanismException {
	        try {
	            Thread currentThread = Thread.currentThread();
	            int threadId = currentThread.hashCode();
	            if (porTransacao || !porTransacao && !conexoesAlocadas.containsKey("T" + threadId)) {
	                Object canal = conexoesAlocadas.get("" + threadId);
	                boolean achou = false;
	                for (int i = 0; !achou && i < PersistenceMechanismRDBMS.numConexoes; i++) {
	                    if (conexoesLivres[i] != null) {
	                        continue;
	                    }
	                    achou = true;
	                    conexoesAlocadas.remove("" + threadId);
	                    if (conexoesAlocadas.containsKey("T" + threadId)) {
	                        conexoesAlocadas.remove("T" + threadId);
	                    }
	                    conexoesLivres[i] = (java.sql.Connection)canal;
	                }

	                indisponivel = false;
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_LIBERAR_CANAL_COMUNICACAO);
	        } finally {
	            notifyAll();
	        }
	    }
		
		
		 public void finalize() throws PersistenceMechanismException {
				this.disconnect();
		    }
	
	
	
	

}
