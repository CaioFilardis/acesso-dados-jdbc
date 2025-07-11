package applicaiton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.db.DB;
import br.com.db.DbIntegrityException;

/* atualizar dados da tabela */
public class DeleteProgram4 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE " // também aplicar restrição
					+ "Id = ?");
			
			st.setInt(1, 6);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done!\nRows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			// lançar a exceção personalizada
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
	}
}
