package applicaiton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.db.DB;

/* inserir dados no banco */
public class Program2 {

	public static void main(String[] args) {

		Connection conn = null; // faz a conexão com o banco
		Statement st = null; // faz a consulta com o banco
		ResultSet rs = null; // recupera os dados após a consulta em forma de tabela
		
		// recuperar dados do banco
		try {
			conn = DB.getConnection(); // conecta com o banco de dados
			
			st = conn.createStatement(); // instanciando objeto statement
			
			rs = st.executeQuery("SELECT * FROM coursejdbc.department"); // recupera os dadoas da tabela 'departament'
			
			// 'next()', funcao que percorre a tabela do banco
			while (rs.next()) { 
				// imprime as linhas da coluna
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace(); // exceção aponta o erro
		}
		// aplicando boas práticas(evitar o vazamento de memória, isso é necessário devido às funções usadas)
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection(conn);
		}
	}

}
