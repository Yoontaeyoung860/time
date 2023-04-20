package com.back.endTest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPerson {
  private Long personIdPk;
  private String idPerson;
  private String pwPerson;
  private String pwChkPerson;
  private String namePerson;
  private String birthPerson;
  private String genderPerson;
  private String addressPerson;
  private String detailAddressPerson;
  private String emailPerson;
  private String phonePerson;
}
