package applicaiton;

import java.sql.Connection;

import br.com.db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = DB.getConnection(); // conexao com o banco
		DB.closeConnection();

	}

}
