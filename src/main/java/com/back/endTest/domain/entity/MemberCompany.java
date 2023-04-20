package com.back.endTest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCompany {
  private Long companyIdPk;
  private String idCompany;
  private String pwCompany;
  private String pwChkCompany;
  private String nameCompany;
  private String businessCompany;
  private String addressCompany;
  private String detailAddressCompany;
  private String emailCompany;
  private String phoneCompany;
}
