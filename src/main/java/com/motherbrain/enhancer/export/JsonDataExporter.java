package com.motherbrain.enhancer.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonDataExporter<T> {
    @Autowired ObjectMapper mapper;

    public void export(List<T> objectsToExport, String filePath) {
        System.out.println("Exporting to json file...");
        try {
            mapper.writeValue(new File(filePath), objectsToExport);
            System.out.println("Objects converted to JSON and written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
