package com.back.endTest.domain.jobReview.svc;

import com.back.endTest.domain.entity.JobReview;
import com.back.endTest.domain.jobReview.dao.JobReviewDAO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class JobReviewSVCImpl implements JobReviewSVC {

  private final JobReviewDAO jobReviewDAO;

  @Override
  public JobReview save(JobReview jobReview) {
    return jobReviewDAO.save(jobReview);
  }

  @Override
  public JobReview update(JobReview jobReview) {
    return jobReviewDAO.update(jobReview);
  }

  @Override
  public int delete(Long reviewIdPk, String idReview) {
    return jobReviewDAO.delete(reviewIdPk, idReview);
  }

  @Override
  public List<JobReview> findAll(Long idReview) {
    return jobReviewDAO.findAll(idReview);
  }

  @Override
  public JobReview inquiry(Long reviewIdPK) {
    return jobReviewDAO.inquiry(reviewIdPK);
  }

  @Override
  public boolean isExist(Long reviewIdPk) {
    return false;
  }
}
