package com.motherbrain.enhancer.datacollectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.entities.Company;
import com.motherbrain.enhancer.entities.CompanyPage;
import com.motherbrain.enhancer.entities.Fund;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CompanyDataScraper {
  private RestTemplate restTemplate;
  private ObjectMapper mapper;
  private String url;

  public CompanyDataScraper(RestTemplate restTemplate, ObjectMapper objectMapper, String url) {
    this.restTemplate = restTemplate;
    this.mapper = objectMapper;
    this.url = url;
  }

  public List<Company> getFullCompanyData() {
    System.out.println("Scraping data from " + url);
    List<Company> companies =
        getCompanies().stream().map(c -> merge(c, getCompanyPage(c))).toList();
    return companies.stream()
        .map(
            c ->
                c.updateFund(
                    c.getFund().stream().map(fund -> merge(fund, getFundPageData(fund))).toList()))
        .toList();
  }

  private Fund getFundPageData(Fund fund) {
    try {
      ResponseEntity<String> response =
          restTemplate.getForEntity(
              "https://eqtgroup.com/page-data/" + fund.getPath() + "/page-data.json\n",
              String.class);
      JsonNode root = mapper.readTree(Objects.requireNonNull(response.getBody()));
      var node = root.path("result").path("data").path("sanityFund");
      return mapper.readValue(mapper.treeAsTokens(node), Fund.class);
    } catch (IOException e) {
      throw new RuntimeException("", e);
    } catch (HttpClientErrorException e1) {
      return new Fund("", "", "", null, "", "", "", "", "", List.of());
    }
  }

  private CompanyPage getCompanyPage(Company company) {
    try {
      ResponseEntity<String> response =
          restTemplate.getForEntity(
              "https://eqtgroup.com/page-data/" + company.getPath() + "/page-data.json\n",
              String.class);
      JsonNode root = mapper.readTree(Objects.requireNonNull(response.getBody()));
      var node = root.path("result").path("data").path("sanityCompanyPage");
      return mapper.readValue(mapper.treeAsTokens(node), CompanyPage.class);
    } catch (IOException e) {
      throw new RuntimeException("", e);
    } catch (HttpClientErrorException e1) {
      return new CompanyPage("", "", "", List.of());
    }
  }

  private Fund merge(Fund f1, Fund fundPageData) {
    Fund fund =
        new Fund(
            f1.getPath(),
            f1.getTitle(),
            fundPageData.getStatus(),
            fundPageData.getLaunchDate(),
            fundPageData.getSize(),
            fundPageData.getHeading(),
            fundPageData.getEmail(),
            fundPageData.getCurrency(),
            fundPageData.getClassification(),
            fundPageData.getBoard());
    return fund;
  }

  private Company merge(Company c, CompanyPage companyPage) {
    c.setWebsite(companyPage.getWebsite());
    c.setHeading(companyPage.getHeading());
    c.setPreamble(companyPage.getPreamble());
    c.setBoard(List.copyOf(companyPage.getBoard()));
    return c;
  }

  public List<Company> getCompanies() {
    try {
      JsonNode rootNode = mapper.readTree(getPage().getBody().toString());
      JsonNode nodes =
          mapper
              .valueToTree(rootNode)
              .path("result")
              .path("data")
              .path("allSanityCompanyPage")
              .path("nodes");
      return mapper.readValue(mapper.treeAsTokens(nodes), new TypeReference<>() {});
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Returning empty list of companies");
      return List.of();
    }
  }

  ResponseEntity getPage() {
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    if (!response.getStatusCode().is2xxSuccessful()) {
      System.out.println("Failed to fetch data. Response code: " + response.getStatusCode());
    }
    return response;
  }
}
