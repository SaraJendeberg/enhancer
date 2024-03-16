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
public class Fund {
  String path;
  String title;
  String status;
  String launchDate;
  String size;
  String heading;
  String email;
  String currency;
  String classification;
  List<Person> board;
}
