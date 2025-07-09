package br.com.db;


// classe para tratamento de exceção personalizado
// em caso de erros de conexão

public class DbException extends RuntimeException {

	// definindo o número de versão padrão
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}
	

}
