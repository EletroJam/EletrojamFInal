package interfaces;

import java.util.ArrayList;

import conexao.RepositoryException;
import negocio.Telefone;

public interface RepositorioTelefone {

	public void insert(Telefone telefone) throws RepositoryException;

	public void update(String numero_telefone, int id) throws RepositoryException;

	public void delete(String numero_telefone) throws RepositoryException;

	public boolean has(String numero_telefone) throws RepositoryException;

	public Telefone get(String numero_telefone) throws RepositoryException;

	public ArrayList<Telefone> getTelefones() throws RepositoryException;

}
