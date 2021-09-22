package org.blagnac.coo.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe de gestion de la configuration de l'application
 */
public class Configuration {

	private static final String CONFIGURATION_FILENAME = "configuration.properties";

	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_NAME = "db.name";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";

	/**
	 * Chargement de la configuration de l'application
	 * 
	 * @return la configuration de l'application
	 */
	public static Properties loadConfiguration() {
		Properties configuration = null;

		try {
			configuration = new Properties();
			InputStream is = Configuration.class.getClassLoader().getResourceAsStream(CONFIGURATION_FILENAME);

			if (is != null) {
				configuration.load(is);
			} else {
				throw new FileNotFoundException(
						"Le fichier de configuration '" + CONFIGURATION_FILENAME + "' n'existe pas");
			}
		} catch (IOException ioe) {
			System.err.println("Erreur durant le chargement du fichier de configuration");
			ioe.printStackTrace();
		}

		return configuration;
	}
}
