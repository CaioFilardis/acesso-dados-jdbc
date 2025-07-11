package applicaiton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.db.DB;

/* atualizar dados da tabela */
public class Program3 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE " // aplicando restrição para não atualizar todo o DB
					+ "(DepartmentId = ?)");
			
			st.setDouble(1, 200.0);
			st.setDouble(2, 2);
			
			int rowsAffected = st.executeUpdate(); // linhas afetadas
			
			System.out.println("Done!\nRows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
	}

}
