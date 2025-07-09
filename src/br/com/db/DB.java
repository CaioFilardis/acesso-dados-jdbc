package br.com.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// obter e fechar uma conexão com o banco
public class DB {

	// criar um objeto de conexao com o banco de dados JDBC
	private static Connection conn = null;
	
	
	// método para conectar com o banco
	public static Connection getConnection() {
		// tratar exceção em caso de erro com a conexao
		try {
			if (conn == null) {
				Properties props = loadProperties(); // pegar as propriedades do banco de dados
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props); // realiza de fato a conexão com o banco de dados
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return conn;
	}
	
	// metodo para fechar a conexao com o banco
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	
	
	
	// criar método auxiliar para carregar as propriedades do banco
	// objeto para guardar os dados do arquivo db.propeties criado
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs); // lê e guarda os dados do arquivo db.properties
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage()); // retorna a mensagem personalizada criada
		}
	}
	
	
}
