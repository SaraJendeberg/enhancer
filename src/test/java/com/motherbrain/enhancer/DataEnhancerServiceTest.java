package com.motherbrain.enhancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import com.motherbrain.enhancer.datacollectors.JsonFileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataEnhancerServiceTest {
  @Autowired CompanyDataScraper currentCompaniesScraper;
  @Autowired CompanyDataScraper divestmentCompaniesScraper;
  @Autowired ObjectMapper mapper;
  @Autowired JsonFileReader jsonFileReader;
  @Autowired DataEnhancerService dataEnhancerService;

  DataEnhancerServiceTest() throws IOException {}

  @Test
  void testRunPipeline() throws IOException {
    dataEnhancerService.runPipeline();
  }
}
