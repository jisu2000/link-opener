package org.example.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinkOpener {

    public static void openLink(String url) {

        String command = "nohup xdg-open \"" + url + "\" > /dev/null 2>&1 &";

        // ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Read output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete and get the exit value
            int exitCode = process.waitFor();
            System.out.println("\nExited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
