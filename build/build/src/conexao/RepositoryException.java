package conexao;

public class RepositoryException extends Exception{
	
	 private Exception exception;
	 
	 public RepositoryException(Exception exception) {
	        super("Excecao  encapsulada");
	        this.exception = exception;
	    }

	    public String getMessage() {
	        return exception.getMessage();
	    }

	    public void printStackTrace() {
	        exception.printStackTrace();
	    }


}
