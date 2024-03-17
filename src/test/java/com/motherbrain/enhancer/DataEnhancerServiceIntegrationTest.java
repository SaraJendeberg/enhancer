package com.motherbrain.enhancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import com.motherbrain.enhancer.datacollectors.JsonFileReader;
import java.io.IOException;

import com.motherbrain.enhancer.export.JsonDataExporter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataEnhancerServiceIntegrationTest {
  @Autowired CompanyDataScraper currentCompaniesScraper;
  @Autowired CompanyDataScraper divestmentCompaniesScraper;
  @Autowired ObjectMapper mapper;
  @Autowired JsonFileReader jsonFileReader;
  @Autowired JsonDataExporter jsonDataExporter;
  @Autowired private DataEnhancerService dataEnhancerService;

  DataEnhancerServiceIntegrationTest() throws IOException {}

  @Test
  void testRunPipeline() throws IOException {
    dataEnhancerService.runPipeline();
  }
}
