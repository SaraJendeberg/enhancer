package com.motherbrain.enhancer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileCompanyData {
  @JsonProperty("company_name")
  String companyName;

  // FUNDING INFORMATION
  @JsonProperty("funding_round_uuid")
  String fundingRoundId;

  @JsonProperty("company_uuid")
  String companyUUID;

  @JsonProperty("investment_type")
  String investmentType;

  @JsonProperty("announced_on")
  Date announcedOn;

  @JsonProperty("raised_amount_usd")
  String raisedAmountUsd;

  @JsonProperty("investor_names")
  String investorNames; // TODO: this should be a set

  // FUNDING INFO END

  // ORG INFO START
  @JsonProperty("founded_on")
  Date foundedOn;

  String city; // TODO: enums

  @JsonProperty("country_code")
  String countryCode;

  @JsonProperty("short_description")
  String shortDescription;

  @JsonProperty("funding_rounds")
  String fundingRounds;

  @JsonProperty("funding_total_usd")
  String fundingTotalUSD;

  @JsonProperty("employee_count") // TODO: map to year - employeeCount : {year 2019 : int count}
  String employeeCount;
  // ORG INFO END
}
