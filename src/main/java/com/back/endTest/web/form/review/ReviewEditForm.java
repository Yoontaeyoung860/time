package com.back.endTest.web.form.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewEditForm {

  private Long reviewIdPK;
  private String jobBoardIdPk;
  private String idReview;
  private String contentReview;

  private Long rstar;

}
