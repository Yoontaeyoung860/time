package com.back.endTest.web.form.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewItemForm {//리뷰 목록용

  private Long reviewIdPK;
  private String idReview;
  private String nickname_person;
  private String contentReview;
  private String rudate;

  private Long rstar;

}
