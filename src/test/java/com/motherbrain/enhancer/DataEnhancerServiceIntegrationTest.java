package com.motherbrain.enhancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.DataEnhancerService;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import com.motherbrain.enhancer.datacollectors.JsonFileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataEnhancerServiceIntegrationTest {
  @Autowired CompanyDataScraper currentCompaniesScraper;
  @Autowired CompanyDataScraper divestmentCompaniesScraper;
  @Autowired ObjectMapper mapper;
  @Autowired JsonFileReader jsonFileReader;
  @Autowired
  private DataEnhancerService dataEnhancerService;

  DataEnhancerServiceIntegrationTest() throws IOException {}

  @Test
  void testRunPipeline() throws IOException {
    dataEnhancerService.runPipeline();
  }
}
