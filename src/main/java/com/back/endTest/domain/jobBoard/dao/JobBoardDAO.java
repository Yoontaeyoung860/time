package com.back.endTest.domain.jobBoard.dao;

import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.entity.JobBoardImage;

import java.util.List;
import java.util.Optional;

public interface JobBoardDAO {
  //등록
  Long save(JobBoard jobBoard);

  //조회
  Optional<JobBoard> inquiry(Long jobBoardIdPk);

  //수정
  int update(Long jobBoardIdPk, JobBoard jobBoard);

  //삭제
  int delete(Long jobBoardIdPk);

  //목록
  List<JobBoard> findAll();

  //구인글 존재 유무
  boolean isExist(Long jobBoardIdPk);

  void save(JobBoardImage jobBoardImage);
}
