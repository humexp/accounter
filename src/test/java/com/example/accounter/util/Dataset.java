package com.example.accounter.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class Dataset {
    private static String DATASET_LOCATION = "dataset";

    public static String getAsString(String type, String name) {
        return getFileContent(DATASET_LOCATION + "/" + type + "/" + name + ".json");
    }

    public static Map<String, Object> getAsMap(String type, String name) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(getAsString(type, name), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileContent(String fileName) {
        ClassLoader classLoader = Dataset.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (FileInputStream inputStream = new FileInputStream(file)) {
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
