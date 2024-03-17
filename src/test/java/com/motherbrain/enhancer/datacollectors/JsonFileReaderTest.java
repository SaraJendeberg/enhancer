package com.motherbrain.enhancer.datacollectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motherbrain.enhancer.entities.Funding;
import com.motherbrain.enhancer.entities.Organisation;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class JsonFileReaderTest {

  @Autowired private ObjectMapper objectMapper;
  @Autowired JsonFileReader jsonFileReader;

  @Test
  void testParseFundings() throws IOException {
    List<Funding> fundings = jsonFileReader.parseFundings();

    // Verify the size of the list
    assertEquals(10, fundings.size());

    // Verify the content of the first item
    Funding funding = fundings.get(0);
    assertEquals("50 Cubes", funding.getCompanyName());
    assertEquals("secondary_market", funding.getInvestmentType());
    assertEquals("2012-01-01", funding.getAnnouncedOn());
  }

  @Test
  void testParseOrganisationDetails() throws IOException {
    List<Organisation> organisations = jsonFileReader.parseOrganisationDetails();

    // Verify the size of the list
    assertEquals(10, organisations.size());

    // Verify the content of the first item
    Organisation organisation = organisations.get(0);
    assertEquals("Connected Auto", organisation.getCompanyName());
    assertEquals("unknown", organisation.getEmployeeCount());
    assertEquals("0", organisation.getFundingRounds());
  }
}
