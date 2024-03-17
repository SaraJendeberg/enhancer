package com.motherbrain.enhancer.datacollectors;

import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CompanyDataScraperTest {
  @Autowired CompanyDataScraper currentCompaniesScraper;
  @Autowired CompanyDataScraper divestmentCompaniesScraper;
  @Autowired ObjectMapper objectMapper;
  @Autowired RestTemplate restTemplate;

  @Test
  void testGetCurrentCompaniesPage_Success() {
    ResponseEntity result = currentCompaniesScraper.getPage();
    assertThat("Is successful", result.getStatusCode().is2xxSuccessful());
  }

  @Test
  void testGetDivestmentCompaniesPage_Success() {
    ResponseEntity result = divestmentCompaniesScraper.getPage();
    assertThat("Is successful", result.getStatusCode().is2xxSuccessful());
  }
}
