package com.back.endTest.domain.jobReview.dao;

import com.back.endTest.domain.entity.JobReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class JobReviewDAOImpl implements JobReviewDAO {

  private final JdbcTemplate jdbcTemplate;


  //후기글 등록
  @Override
  public JobReview save(JobReview jobReview) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into job_review( ");
    sql.append(" reviewIdPK, ");
    sql.append(" Job_board_ID, ");
    sql.append(" title_review, ");
    sql.append(" id_review, ");
    sql.append(" content_review, ");
    sql.append(" rstar ");
    sql.append(" ) ");
    sql.append(" VALUES ");
    sql.append(" (job_review_reviewIdPK_sql.nextval, ?, ?, ?, ?, ?) ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {

      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"reviewIdPK"}
        );

        pstmt.setString(1, jobReview.getJobBoardIdPk());
        pstmt.setString(2, jobReview.getTitleReview());
        pstmt.setString(3, jobReview.getIdReview());
        pstmt.setString(3, jobReview.getContentReview());
        pstmt.setString(4, String.valueOf(jobReview.getRstar()));

        return pstmt;
      }
    }, keyHolder);

    Long reviewIdPK = Long.valueOf(keyHolder.getKeys().get("reviewIdPK").toString());
    return inquiry(reviewIdPK);
  }


  /**
   * 리뷰 수정
   * @param jobReview
   * @return
   */
  @Override
  public JobReview update(JobReview jobReview) {
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE ");
    sql.append("   Job_review ");
    sql.append(" SET ");
    sql.append("   content_review = ? , ");
    sql.append("   rstar = ? , ");
    sql.append("   rudate = systimestamp ");
    sql.append(" WHERE ");
    sql.append("   reviewIdPK = ? ");
    sql.append(" AND ");
    sql.append("   id_review = ? ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"rnum"}
        );

        pstmt.setString(1, jobReview.getContentReview());
        pstmt.setLong(2, jobReview.getReviewIdPK());
        pstmt.setString(3, jobReview.getIdReview());

        return pstmt;
      }
    }, keyHolder);

    Long reviewIdPK = Long.valueOf(keyHolder.getKeys().get("reviewIdPK").toString());
    return inquiry(reviewIdPK);
  }

  @Override
  public int delete(String reviewIdPK, String idReview) {
    StringBuffer sql = new StringBuffer();
    sql.append(" DELETE FROM ");
    sql.append("   Job_review ");
    sql.append("  WHERE ");
    sql.append("    reviewIdPK =? ");
    sql.append("  AND ");
    sql.append("    id_review = ? ");

    int result = jdbcTemplate.update(sql.toString(), reviewIdPK, idReview);

    return result;
  }

  @Override
  public List<JobReview> findAll(String reviewIdPK) {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append("   row_number() over (order by rcdate) as num, ");
    sql.append("   reviewIdPK, ");
    sql.append("   Job_board_ID, ");
    sql.append("   title_review, ");
    sql.append("   id_review,");
    sql.append("   content_review,");
    sql.append("   rstar, ");
    sql.append("   rcdate, ");
    sql.append("   rudate, ");
    sql.append(" FROM ");
    sql.append("   Job_review, ");
    sql.append("   member_person ");
    sql.append(" WHERE ");
    sql.append("   Job_review.id_review = member_person.id_person ");
    sql.append(" ORDER BY rcdate ASC ");

    //sql 실행
    List<JobReview> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(JobReview.class),reviewIdPK);

    return list;
  }

  @Override
  public int delete(Long reviewIdPK, String idReview) {
    return 0;
  }

  @Override
  public List<JobReview> findAll(Long reviewIdPK) {
    return null;
  }


  //단건 조회
  @Override
  public JobReview inquiry(Long reviewIdPK) {
    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append("   reviewIdPK, ");
    sql.append("   Job_board_ID, ");
    sql.append("   title_review, ");
    sql.append("   id_review, ");
    sql.append("   content_review, ");
    sql.append("   rstar ");
    sql.append("   FROM ");
    sql.append("   Job_review, ");
    sql.append("   member_person ");
    sql.append(" FROM ");
    sql.append("   review, ");
    sql.append("   member ");
    sql.append(" WHERE ");
    sql.append("   Job_review.id_review = member_person.id_person ");
    sql.append(" AND ");
    sql.append("   reviewIdPK = ? ");

    //sql 실행
    List<JobReview> query = jdbcTemplate.query(sql.toString(),
        new BeanPropertyRowMapper<>(JobReview.class), reviewIdPK);
    return (query.size() == 1) ? query.get(0) : null;
  }


  @Override
  public boolean isExist(Long reviewIdPk) {
    return false;
  }


}