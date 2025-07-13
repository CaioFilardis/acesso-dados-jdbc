package applicaiton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.db.DB;
import br.com.db.DbException;

/* realizar transações e lidando com erros de transferência */
public class TransitionProgram {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			// não é para confirmar a transação de forma automática
			// será apenas confirmada pelo programador
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rowsOne = st.executeUpdate(
					"UPDATE seller "
					+ "SET BaseSalary = 2090 "
					+ "WHERE "
					+ "DepartmentId = 1");
			
			/*int x = 1;
			if (x < 2) {
				// lançando uma execeção personalizada
				throw new SQLException("Fake error");
			}*/
			
			int rowsTwo = st.executeUpdate(
					"UPDATE seller "
							+ "SET BaseSalary = 3090 "
							+ "WHERE "
							+ "DepartmentId = 2");
			
			conn.commit(); // agora confirma que a transação seja feita
			
			System.out.println("rowsOne: " + rowsOne);
			System.out.println("rowsTwo: " + rowsTwo);
					
		}
		catch(SQLException e) {
			try {
				conn.rollback(); // desfaz a transção realizada e volta para o estado anterior
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			}
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Cause by: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
	}

}
