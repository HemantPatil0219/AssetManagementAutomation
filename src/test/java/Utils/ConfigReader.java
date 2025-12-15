package Utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static Dotenv dotenv;

    static {
        try (FileInputStream file =
                     new FileInputStream("src/test/resources/config.properties")) {

            properties.load(file);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config.properties file", e
            );
        }

        dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }

    public static String get(String key) {

        // 1️⃣ First priority: Environment variable (.env / system)
        String value = dotenv.get(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        // 2️⃣ Fallback: config.properties
        value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(
                    "Property '" + key + "' not found in config.properties or .env"
            );
        }

        value = value.trim();

        // 3️⃣ Resolve relative paths safely
        if (value.startsWith("src")) {
            return System.getProperty("user.dir") + "/" + value;
        }

        return value;
    }
}
