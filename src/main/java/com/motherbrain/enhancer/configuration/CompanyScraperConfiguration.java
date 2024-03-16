package com.motherbrain.enhancer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class CompanyScraperConfiguration {
  @Autowired private RestTemplate restTemplate;
  @Autowired private ObjectMapper mapper;

  @Bean
  public CompanyDataScraper currentCompaniesScraper(
      @Autowired RestTemplate restTemplate,
      @Autowired ObjectMapper objectMapper,
      @Value("${eqt.current.portfolio.url}") String currentPortfolioUrl) {
    return new CompanyDataScraper(restTemplate, objectMapper, currentPortfolioUrl);
  }

  @Bean
  public CompanyDataScraper divestmentCompaniesScraper(
      @Autowired RestTemplate restTemplate,
      @Autowired ObjectMapper objectMapper,
      @Value("${eqt.current.portfolio.divestments.url}") String divestmentsUrl) {
    return new CompanyDataScraper(restTemplate, objectMapper, divestmentsUrl);
  }
}
