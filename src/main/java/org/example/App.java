package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.example.constants.AppContants;
import org.example.service.CommantValidator;
import org.example.service.FileCreater;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {

        String filePath = AppContants.FILE_PATH;
        Path path = Paths.get(filePath);
        boolean fileExists = Files.exists(path);

        if (!fileExists) {
            FileCreater.makeEmptyFile();
        }

        if (args.length == 0) {
            System.out.println("use lo --help");
        }

        else {
            try {
                CommantValidator.commandDetector(args);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
