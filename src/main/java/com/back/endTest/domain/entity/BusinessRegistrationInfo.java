package com.back.endTest.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRegistrationInfo {
  private String bNo;
  private String bStt;
  private String bSttCd;
  private String taxType;
  private String taxTypeCd;
  private String endDt;
  private String utccYn;
  private String taxTypeChangeDt;
  private String invoiceApplyDt;

  // getters and setters
}