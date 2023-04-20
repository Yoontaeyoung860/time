package com.back.endTest.domain.jobReview.svc;

import com.back.endTest.domain.entity.JobReview;

import java.util.List;
import java.util.Optional;

public interface JobReviewSVC {
  //등록
  JobReview save(JobReview jobReview);

  //수정
  JobReview update(JobReview jobReview);

  //삭제
  int delete(Long reviewIdPk, String idReview);

  //전체 조회
  List<JobReview> findAll(Long idReview);
  //단건 조회
  JobReview inquiry(Long reviewIdPK);
  //구인글 존재 유무
  boolean isExist(Long reviewIdPk);
}
