package com.back.endTest.domain.schedule.dao;

import com.back.endTest.domain.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleDAOImpl implements ScheduleDAO {

  private final NamedParameterJdbcTemplate template;

  //등록
  @Override
  public Long save(Schedule schedule) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into schedule( ");
    sql.append(" schedule_ID_PK, ");
    sql.append(" my_id, ");
    sql.append(" NAME_schedule, ");
    sql.append(" ID_schedule, ");
    sql.append(" work_schedule, ");
    sql.append(" day_schedule, ");
    sql.append(" come_in_schedule, ");
    sql.append(" come_out_schedule, ");
    sql.append(" period_start, ");
    sql.append(" period_end ) ");
    sql.append(" values( schedule_schedule_ID_PK_seq.nextval, ");
    sql.append(" :myId, ");
    sql.append(" :nameSchedule, ");
    sql.append(" :idSchedule, ");
    sql.append(" :workSchedule, ");
    sql.append(" :daySchedule, ");
    sql.append(" :comeInSchedule, ");
    sql.append(" :comeOutSchedule, ");
    sql.append(" :periodStart, ");
    sql.append(" :periodEnd ) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(schedule);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[]{"schedule_ID_PK"});

    long scheduleIdPk = keyHolder.getKey().longValue();

    return scheduleIdPk;
  }

  //목록
  @Override
  public List<Schedule> findAll(String myId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select schedule_ID_PK, ");
    sql.append(" my_id, ");
    sql.append(" NAME_schedule, ");
    sql.append(" ID_schedule, ");
    sql.append(" work_schedule, ");
    sql.append(" day_schedule, ");
    sql.append(" come_in_schedule, ");
    sql.append(" come_out_schedule, ");
    sql.append(" period_start, ");
    sql.append(" period_end ");
    sql.append(" from schedule ");
    sql.append(" where my_id = :id ");

    try {
      Map<String, String> param = Map.of("id", myId);
      List<Schedule> list = template.query(
        sql.toString(), param,
        BeanPropertyRowMapper.newInstance(Schedule.class)
      );

      return list;
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  //조회
  @Override
  public Optional<Schedule> inquiry(Long scheduleIdPk) {
    StringBuffer sql = new StringBuffer();
    sql.append(" select schedule_ID_PK, ");
    sql.append(" my_id, ");
    sql.append(" ID_schedule, ");
    sql.append(" NAME_schedule, ");
    sql.append(" work_schedule, ");
    sql.append(" day_schedule, ");
    sql.append(" come_in_schedule, ");
    sql.append(" come_out_schedule, ");
    sql.append(" period_start, ");
    sql.append(" period_end ");
    sql.append(" from schedule ");
    sql.append(" where schedule_ID_PK = :id ");

    try {
      Map<String, Long> param = Map.of("id", scheduleIdPk);
      Schedule schedule = template.queryForObject(
        sql.toString(), param, BeanPropertyRowMapper.newInstance(Schedule.class)
      );
      return Optional.of(schedule);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  //수정
  @Override
  public int update(Long scheduleIdPk, Schedule schedule) {
    StringBuffer sql = new StringBuffer();
    sql.append(" update schedule ");
    sql.append(" set work_schedule = :workSchedule, ");
    sql.append(" day_schedule = :daySchedule, ");
    sql.append(" come_in_schedule = :comeInSchedule, ");
    sql.append(" come_out_schedule = :comeOutSchedule, ");
    sql.append(" period_start = :periodStart, ");
    sql.append(" period_end = :periodEnd ");
    sql.append(" where schedule_ID_PK = :id ");

    SqlParameterSource param = new MapSqlParameterSource()
      .addValue("workSchedule", schedule.getWorkSchedule())
      .addValue("daySchedule", schedule.getDaySchedule())
      .addValue("comeInSchedule", schedule.getComeInSchedule())
      .addValue("comeOutSchedule", schedule.getComeOutSchedule())
      .addValue("periodStart", schedule.getPeriodStart())
      .addValue("periodEnd", schedule.getPeriodEnd())
      .addValue("id", scheduleIdPk);

    return template.update(sql.toString(), param);
  }

  //추가 등록


  //삭제
  @Override
  public int delete(Long scheduleIdPk) {
    String sql = " delete from schedule where schedule_ID_PK = :id ";

    return template.update(sql, Map.of("id", scheduleIdPk));
  }
}
