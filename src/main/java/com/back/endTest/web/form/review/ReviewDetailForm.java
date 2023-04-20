package com.back.endTest.web.form.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDetailForm {

  private Long reviewIdPK;
  private String id_review;
  private String nickname_person;
  private String contentReview;
  private String rudate;

  private Long rstar;


}
