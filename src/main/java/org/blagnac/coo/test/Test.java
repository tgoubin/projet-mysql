package org.blagnac.coo.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.blagnac.coo.mysql.Database;

public class Test {

	public static void main(String[] args) {
		Database database = new Database();

		database.executeInsertUpdateDelete(
				"INSERT INTO utilisateur (login, mot_de_passe, nom, prenom, email) VALUES ('tgoubin', 'test', 'GOUBIN', 'Thibault', 'thibault.goubin31@gmail.com')");
		database.executeInsertUpdateDelete(
				"INSERT INTO utilisateur (login, mot_de_passe, nom, prenom, email) VALUES ('tgoubin2', 'test2', 'GOUBIN2', 'Thibault2', 'thibault.goubin@inetum.world')");

		try {
			ResultSet resultSet = database.executeSelect("SELECT * FROM utilisateur");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("login"));
				System.out.println(resultSet.getString("mot_de_passe"));
				System.out.println(resultSet.getString("nom"));
				System.out.println(resultSet.getString("prenom"));
				System.out.println(resultSet.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		database.executeInsertUpdateDelete("DELETE FROM utilisateur");

		database.closeConnection();
	}
}
