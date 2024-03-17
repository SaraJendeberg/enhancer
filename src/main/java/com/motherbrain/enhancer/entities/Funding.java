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
public class Funding {
  @JsonProperty("company_name")
  String companyName;

  @JsonProperty("funding_round_uuid")
  String fundingRoundId;

  @JsonProperty("company_uuid")
  String companyUUID;

  @JsonProperty("investment_type")
  String investmentType;

  @JsonProperty("announced_on")
  String announcedOn; //TODO: use Date

  @JsonProperty("raised_amount_usd")
  String raisedAmountUsd;

  @JsonProperty("investor_names")
  String investorNames; // TODO: deserialize to Set
}
