package com.blapiter;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

public class Utilities {

    public static String getJsonStringWithoutSpacesFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes((new ClassPathResource(filePath).getFile()).toPath()))
                .replaceAll("\\s+", "");
    }

}
