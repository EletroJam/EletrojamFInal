package controle;

import java.util.ArrayList;

import conexao.RepositoryException;
import interfaces.RepositorioLog;
import negocio.Log;


public class ControleLog {

	private RepositorioLog logi;
	
	public ControleLog(RepositorioLog logi) {
		super();
		this.logi = logi;
	}
	
	public void insert(Log log) throws RepositoryException{
		logi.insert(log);
	}
	
	public Log get(int id_funcionario) throws RepositoryException {
		return logi.get(id_funcionario);
	}
	
	public ArrayList<Log> getLogs() throws RepositoryException {
		return logi.getLogs();
	}

}
