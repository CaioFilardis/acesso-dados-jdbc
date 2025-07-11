package applicaiton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.db.DB;

public class Program {

	public static void main(String[] args) {

		// formatando uma data especificamente
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// inserir um novo vendedor
		Connection conn = null; 
		PreparedStatement st = null;
		try {
			// realizar conexão
			conn= DB.getConnection(); 
			
			// inserindo dados utilizando scripts SQL, instanciando o 'PreparedStatement'
			st = conn.prepareStatement(
					"INSERT INTO seller " // inserir dados
					+ "(name, Email, BirthDate, BaseSalary, DepartmentId)" // selecionando os parâmetros
					+ "VALUES " // inserir os valores abaixo
					+ "(?, ?, ?, ?, ?)",
					+ Statement.RETURN_GENERATED_KEYS); // recuperação de Id 
					
			// definindo os valores dos campos, com base np seu tipo de dado definido na tabela
			st.setString(1, "Carl Purple"); 
			st.setString(2, "carl@gmail.com"); // ?, valor-selecionado
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // instanciar uma data no JDBC
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			// executar o comando, definindo às linhas alteradas
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				// pegar o código do novo registro inserido
				ResultSet rs =st.getGeneratedKeys();
				
				// pode gerar mais de um valor
				while(rs.next()) {
					int id = rs.getInt(1); // quero o valor da primeira coluna
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rown affected"); // nenhuma linha alterada
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) { // vai tratar os erros de data em formato inválido
			e.printStackTrace();
		}
		finally {
			// fechar os recurso que foram abertos
			DB.closeStatement(st); // fechar a função de inserção dos scripts SQL
			DB.closeConnection(conn); // fechar a função de conexão com p banco
		}
	}
}
