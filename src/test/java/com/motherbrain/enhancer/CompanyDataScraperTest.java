package com.motherbrain.enhancer;

import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import com.motherbrain.enhancer.entities.Company;
import java.util.List;
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

  @Test
  void testGetCurrentCompanies_Success() {
    List<Company> companies = currentCompaniesScraper.getCompanies();
  }

  @Test
  void testGetDivestmentCompanies_Success() {
    List<Company> companies = divestmentCompaniesScraper.getCompanies();
  }

  @Test
  void testEnrichCurrentCompaniesWithCompanyPageData() {
    currentCompaniesScraper.getFullCompanyData();
  }

  @Test
  void testEnrichDivestmentCompaniesWithCompanyPageData() {
    divestmentCompaniesScraper.getFullCompanyData();
  }
}
