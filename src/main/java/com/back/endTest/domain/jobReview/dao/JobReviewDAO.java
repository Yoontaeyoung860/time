package com.back.endTest.domain.jobReview.dao;

import com.back.endTest.domain.entity.JobReview;

import java.util.List;
import java.util.Optional;

public interface JobReviewDAO {
  //등록
  JobReview save(JobReview jobReview);
  //수정
  JobReview update(JobReview jobReview);
  //삭제
  int delete(String reviewIdPK, String idReview);

  //전체 조회
  List<JobReview> findAll(String idReview);

  int delete(Long reviewIdPK, String idReview);

  //전체 조회
  List<JobReview> findAll(Long reviewIdPK);

  //단건 조회
  JobReview inquiry(Long reviewIdPK);
  //구인글 존재 유무
  boolean isExist(Long reviewIdPK);
}
