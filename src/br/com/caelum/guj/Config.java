package br.com.caelum.guj;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Rafael Steil
 */
public class Config {
	private static Properties properties;

	public static void loadConfigs() {
		properties = new Properties();
		try {
			properties.load(Config.class.getResourceAsStream("/guj.properties"));
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Errro ao carregas as configs. " + e);
		}
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}

	public static int getIntvalue(String key) {
		return Integer.parseInt(getValue(key));
	}
}
