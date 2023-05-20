package com.serwis.serwissamochodowy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileIOUtils {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static List<Car> loadCarsFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return List.of();
        }

        if (filePath.toLowerCase().endsWith(".json")) {
            return JSON_MAPPER.readValue(file, new TypeReference<>() {});
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }

    public static void saveCarsToFile(List<Car> cars, String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        if (filePath.toLowerCase().endsWith(".json")) {
            JSON_MAPPER.writeValue(file, cars);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}
