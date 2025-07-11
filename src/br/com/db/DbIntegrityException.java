package br.com.db;

/* criando classe de exceção personalizada para deletar dados */

public class DbIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
