package com.back.endTest.domain.schedule.svc;

import com.back.endTest.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleSVC {
  //등록
  Long save(Schedule schedule);

  //목록
  List<Schedule> findAll(String myId);

  //조회
  Optional<Schedule> inquiry(Long scheduleIdPk);

  //수정
  int update(Long scheduleIdPk, Schedule schedule);

  //삭제
  int delete(Long scheduleIdPk);
}
