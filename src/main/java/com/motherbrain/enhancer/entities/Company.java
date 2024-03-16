package com.motherbrain.enhancer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
  String title;
  String sector;
  String country; // TODO: enums
  List<Fund> fund;
  String entryDate;
  String exitDate;
  String url;
  String path;
  String promotedSdg;
  List<String> sdg;
  String topic;
  String website;
  String heading;
  String preamble;
  List<Person> board;
  String foundedOn; // TODO: Date
  String city; // TODO: enums
  String countryCode;
  String shortDescription;
  String fundingRounds;
  String fundingTotalUSD;
  String employeeCount; // TODO: map to year - employeeCount : {year 2019 : int count}
  List<Funding> fundings;

  public Company updateFund(List<Fund> funds) {
    this.fund = funds;
    return new Company(
        title,
        sector,
        country,
        funds,
        entryDate,
        exitDate,
        url,
        path,
        promotedSdg,
        sdg,
        topic,
        website,
        heading,
        preamble,
        board,
        foundedOn,
        city,
        countryCode,
        shortDescription,
        fundingRounds,
        fundingTotalUSD,
        employeeCount,
        fundings);
  }

  public void setOrganisationData(Organisation organisation) {
    this.foundedOn = organisation.getFoundedOn();
    this.city = organisation.getCity();
    this.country = organisation.getCountryCode();
    this.shortDescription = organisation.getShortDescription();
    this.fundingRounds = organisation.getFundingRounds();
    this.fundingTotalUSD = organisation.getFundingTotalUSD();
    this.employeeCount = organisation.getEmployeeCount();
  }
}
