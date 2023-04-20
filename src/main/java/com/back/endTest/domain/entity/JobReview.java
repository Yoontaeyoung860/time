package com.back.endTest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobReview {
  private Long reviewIdPK;             //  review_ID_PK      NUMBER(10),
  private String  jobBoardIdPk;     //Job_board_ID  NUMBER(20),
  private String titleReview;            //  title_review      VARCHAR2(100),
  private String idReview;             //  id_review     VARCHAR2(40),
  private String contentReview;             //  content_review      CLOB,
  private Long rstar;     //  별점 rstar NUMBER(1) default 0,
  private LocalDateTime rcdate;    // 리뷰작성일 rcdate
  private LocalDateTime rudate;    // 리뷰수정일 rudate
}
