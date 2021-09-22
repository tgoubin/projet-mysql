package org.blagnac.coo.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.blagnac.coo.configuration.Configuration;

/**
 * Classe de manipulation de la base de données
 */
public class Database {

	private Connection connection;

	private Statement statement;

	public Database() {
		connection = createConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.err.println("Erreur durant la création du statement");
			e.printStackTrace();
		}
	}

	/**
	 * Exécution d'une requête SELECT
	 * 
	 * @param sqlRequest la requête SQL
	 * @return le résultat de la requête
	 */
	public ResultSet executeSelect(String sqlRequest) {
		ResultSet resultSet = null;

		try {
			System.out.println("Exécution de la requête '" + sqlRequest + "'");
			resultSet = statement.executeQuery(sqlRequest);
		} catch (SQLException e) {
			System.err.println("Erreur durant l'exécution de la requête '" + sqlRequest + "'");
			e.printStackTrace();
		}

		return resultSet;
	}

	public void executeInsertUpdateDelete(String sqlRequest) {
		try {
			System.out.println("Exécution de la requête '" + sqlRequest + "'");
			statement.executeUpdate(sqlRequest);
		} catch (SQLException e) {
			System.err.println("Erreur durant l'exécution de la requête '" + sqlRequest + "'");
			e.printStackTrace();
		}
	}

	/**
	 * Création de la connexion
	 */
	private Connection createConnection() {
		Properties configuration = Configuration.loadConfiguration();
		try {
			Class.forName(configuration.getProperty(Configuration.DB_DRIVER));
			connection = DriverManager.getConnection(
					configuration.getProperty(Configuration.DB_URL) + "/"
							+ configuration.getProperty(Configuration.DB_NAME),
					configuration.getProperty(Configuration.DB_USER),
					configuration.getProperty(Configuration.DB_PASSWORD));
		} catch (ClassNotFoundException e) {
			System.err.println("Le driver de connexion '" + configuration.getProperty(Configuration.DB_DRIVER)
					+ "' n'a pas été trouvé");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Erreur durant la connexion à la base de données '"
					+ configuration.getProperty(Configuration.DB_NAME) + "'");
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * Fermeture de la connexion
	 */
	public void closeConnection() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Erreur durant la fermeture de la connexion");
			e.printStackTrace();
		}
	}
}
