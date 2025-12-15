package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SeleniumGridManager {

    private static final String COMPOSE_FILE_PATH = "docker-compose.yml";

    public static void startGrid() throws Exception {
        if (!isGridRunning()) {
            System.out.println("Starting Selenium Grid...");
            runCommand("docker-compose", "-f", COMPOSE_FILE_PATH, "up", "-d");
            Thread.sleep(15000);
        } else {
            System.out.println("Selenium Grid is already running.");
        }
    }

    public static void stopGrid() throws Exception {
        System.out.println("Stopping Selenium Grid (containers will remain)... ");
        runCommand("docker-compose", "-f", COMPOSE_FILE_PATH, "stop");
    }

    private static boolean isGridRunning() throws Exception {
        Process process = new ProcessBuilder("docker", "ps", "-q", "-f", "name=selenium-hub")
                .redirectErrorStream(true)
                .start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine();
            return (line != null && !line.isEmpty());
        }
    }

    private static void runCommand(String... command) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code " + exitCode);
        }
    }
}
