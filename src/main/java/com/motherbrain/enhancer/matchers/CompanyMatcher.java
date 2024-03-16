package com.motherbrain.enhancer.matchers;

import com.motherbrain.enhancer.entities.Company;
import com.motherbrain.enhancer.entities.FileCompanyData;

public class CompanyMatcher {

  public static boolean matchCompanyName(Company company, FileCompanyData fileCompanyData) {
    String title = company.getTitle();
    String companyName = fileCompanyData.getCompanyName();
    return title != null && companyName != null && title.equalsIgnoreCase(companyName);
  }
}
