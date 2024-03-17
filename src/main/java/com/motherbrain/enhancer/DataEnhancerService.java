package com.motherbrain.enhancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import com.motherbrain.enhancer.datacollectors.JsonFileReader;
import com.motherbrain.enhancer.entities.*;
import com.motherbrain.enhancer.export.JsonDataExporter;
import com.motherbrain.enhancer.matchers.StringMatcher;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DataEnhancerService {
  private CompanyDataScraper currentCompaniesScraper;
  private CompanyDataScraper divestmentCompaniesScraper;
  private JsonFileReader jsonFileReader;
  private JsonDataExporter jsonDataExporter;
  private ObjectMapper mapper;
  private List<Company> companies;
  StringMatcher stringMatcher;

  DataEnhancerService(
      @Autowired CompanyDataScraper currentCompaniesScraper,
      @Autowired CompanyDataScraper divestmentCompaniesScraper,
      @Autowired JsonFileReader jsonFileReader,
      @Autowired JsonDataExporter jsonDataExporter,
      @Autowired ObjectMapper mapper)
      throws IOException {
    this.currentCompaniesScraper = currentCompaniesScraper;
    this.divestmentCompaniesScraper = divestmentCompaniesScraper;
    this.jsonFileReader = jsonFileReader;
    this.jsonDataExporter = jsonDataExporter;
    this.mapper = mapper;
    this.companies = new ArrayList<>();
    this.stringMatcher = new StringMatcher();
  }

  void runPipeline() throws IOException {
    System.out.println("Pipeline started...");
    // Scrape Company Data
    companies.addAll(currentCompaniesScraper.getFullCompanyData());
    companies.addAll(divestmentCompaniesScraper.getFullCompanyData());

    // Read Json data
    List<Funding> fundings = jsonFileReader.parseFundings();
    List<Organisation> organisationDetails = jsonFileReader.parseOrganisationDetails();

    // Enhance
    enhanceCompanyData(fundings, organisationDetails);

    // Export
    jsonDataExporter.export(companies, "company-data-output.json");

    System.out.println("Pipeline ended.");
  }

  private void enhanceCompanyData(List<Funding> fundings, List<Organisation> organisationDetails) {
    System.out.println("Enhancing company data...");
    enhanceWithFundings(fundings);
    enhanceWithOrganisation(organisationDetails);
  }

  private void enhanceWithFundings(List<Funding> fundings) {
    System.out.println("Enhancing with funding data...");
    AtomicInteger countFundingMatches = new AtomicInteger();
    companies.forEach(
        company -> {
          List<Funding> matchedFundings =
              fundings.stream()
                  .filter(c -> stringMatcher.matches(c.getCompanyName(), company.getTitle()))
                  .toList();
          if (!matchedFundings.isEmpty()) {
            countFundingMatches.getAndIncrement();
            company.setFundings(matchedFundings);
          }
        });
    System.out.println("FUNDING MATCHES : " + countFundingMatches.get());
  }

  private void enhanceWithOrganisation(List<Organisation> organisations) {
    System.out.println("Enhancing with organisation data...");
    AtomicInteger countOrganisationMatches = new AtomicInteger();
    companies.forEach(
        company -> {
          Organisation organisation =
              organisations.stream()
                  .filter(c -> stringMatcher.matches(c.getCompanyName(), company.getTitle()))
                  .findAny()
                  .orElse(null);
          if (organisation != null) {
            countOrganisationMatches.getAndIncrement();
            company.setOrganisationData(organisation);
          }
        });
    System.out.println("ORGANISATION MATCHES : " + countOrganisationMatches.get());
  }
}
