package com.back.endTest.domain.entity;

public class RegistrationInfoRequest {
  private String apiKey;
  private String registrationNumber;

  public RegistrationInfoRequest(String apiKey, String registrationNumber) {
    this.apiKey = apiKey;
    this.registrationNumber = registrationNumber;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }
}