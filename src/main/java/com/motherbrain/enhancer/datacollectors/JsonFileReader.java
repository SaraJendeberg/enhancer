package com.motherbrain.enhancer.datacollectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.entities.Funding;
import com.motherbrain.enhancer.entities.Organisation;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonFileReader {
  private ObjectMapper mapper;
  private BufferedReader bufferedReader;
  private static String RESOURCES_FOLDER;
  private static final List<String> fileNames =
      List.of("interview-test-funding-2019.json", "interview-test-org-2019.json");

  public JsonFileReader(
      @Autowired ObjectMapper mapper, @Value("${resource.path}") String resourcePath)
      throws FileNotFoundException {
    this.mapper = mapper;
    this.RESOURCES_FOLDER = resourcePath;
  }

  public List<Funding> parseFundings() throws IOException {
    System.out.println("Parse funding reference data...");
    return parseData(Funding.class, "interview-test-funding-2019.json");
  }

  public List<Organisation> parseOrganisationDetails() throws IOException {
    System.out.println("Parsing organisation reference data...");
    return parseData(Organisation.class, "interview-test-org-2019.json");
  }

  private <T> List<T> parseData(Class<T> tClass, String fileName) throws IOException {
    this.bufferedReader = new BufferedReader(new FileReader(RESOURCES_FOLDER + fileName));
    String line = bufferedReader.readLine();

    List<T> data = new ArrayList<>();
    while (line != null) {
      JsonNode node = mapper.readTree(line);
      T fileCompanyData = mapper.readValue(mapper.treeAsTokens(node), tClass);
      data.add(fileCompanyData);
      line = bufferedReader.readLine();
    }
    return data;
  }
}
