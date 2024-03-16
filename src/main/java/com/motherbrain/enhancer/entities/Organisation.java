package com.motherbrain.enhancer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organisation {
  @JsonProperty("company_name")
  String companyName;

  @JsonProperty("founded_on")
  String foundedOn;

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
}
