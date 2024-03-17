package com.motherbrain.enhancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.datacollectors.CompanyDataScraper;
import com.motherbrain.enhancer.datacollectors.JsonFileReader;
import com.motherbrain.enhancer.entities.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.motherbrain.enhancer.matchers.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DataEnhancerService {
  private CompanyDataScraper currentCompaniesScraper;
  private CompanyDataScraper divestmentCompaniesScraper;
  private JsonFileReader jsonFileReader;
  private ObjectMapper mapper;
  private List<Company> companies;
  StringMatcher stringMatcher;

  DataEnhancerService(
      @Autowired CompanyDataScraper currentCompaniesScraper,
      @Autowired CompanyDataScraper divestmentCompaniesScraper,
      @Autowired JsonFileReader jsonFileReader,
      @Autowired ObjectMapper mapper)
      throws IOException {
    this.currentCompaniesScraper = currentCompaniesScraper;
    this.divestmentCompaniesScraper = divestmentCompaniesScraper;
    this.jsonFileReader = jsonFileReader;
    this.mapper = mapper;
    this.companies = new ArrayList<>();
    stringMatcher = new StringMatcher();
  }

  void runPipeline() throws IOException {
    System.out.println("Pipeline started...");
    // Scrape Company Data
    companies.addAll(currentCompaniesScraper.getFullCompanyData());
    companies.addAll(divestmentCompaniesScraper.getFullCompanyData());

    // Read Json data
    List<Funding> fundings = jsonFileReader.parseFundings();
    List<Organisation> organisationDetails = jsonFileReader.parseOrganisationDetails();

    // Enhance Company Data
    enhanceCompanyData(fundings, organisationDetails);

    // Export Company Data
    exportCompanyData();
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
    System.out.println(
        "---------------" + "FUNDING MATCHES : " + countFundingMatches.get());
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
    System.out.println(
        "---------------"
            + "ORGANISATION MATCHES : "
            + countOrganisationMatches.get());
  }

  private void exportCompanyData() {
    System.out.println("Exporting enhanced data to json file...");
    String filePath = "company-data-output.json";
    try {
      mapper.writeValue(new File(filePath), companies);
      System.out.println("Objects converted to JSON and written to " + filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
