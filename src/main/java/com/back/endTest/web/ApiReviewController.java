package com.back.endTest.web;

import com.back.endTest.domain.entity.JobReview;
import com.back.endTest.domain.jobReview.svc.JobReviewSVC;
import com.back.endTest.web.form.review.*;
import com.back.endTest.web.session.LoginPerson;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobBoards")
public class ApiReviewController {

  private final JobReviewSVC jobReviewSVC;


  @GetMapping("/{jobBoardIdPk}")
  public ReviewApiResult<Object> list(@PathVariable String jobBoardIdPk) {
    log.info("list() 호출됨!");

    List<JobReview> list = jobReviewSVC.findAll(Long.valueOf(jobBoardIdPk));
    if (list == null) {
      return new ReviewApiResult<>("01", "fail", "리뷰 리스트 조회에 실패하였습니다.");
    }

    List<ReviewItemForm> items = new ArrayList<>();

    for (JobReview jobReview : list) {
      ReviewItemForm item = new ReviewItemForm();
      BeanUtils.copyProperties(jobReview, item);

      LocalDate reviewDate = jobReview.getRudate().toLocalDate();
      LocalDate today = LocalDate.now();

      if (reviewDate.equals(today)) {
        item.setRudate(jobReview.getRudate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")).toString());
      } else {
        item.setRudate(jobReview.getRudate().toLocalDate().toString());
      }

      items.add(item);
    }

    return new ReviewApiResult<>("00", "success", items);
  }

  //리뷰 단건 조회
  @GetMapping("/{reviewIdPK}/detail")
  public ReviewApiResult<Object> detail(@PathVariable Long reviewIdPK){
    log.info("detail() 호출됨!");

    JobReview jobReview = jobReviewSVC.inquiry(reviewIdPK);

    ReviewDetailForm detailForm = new ReviewDetailForm();
    BeanUtils.copyProperties(jobReview, detailForm);

    LocalDate reviewDate = jobReview.getRudate().toLocalDate();
    LocalDate today = LocalDate.now();

    if (reviewDate.equals(today)) {
      detailForm.setRudate(jobReview.getRudate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")).toString());
    } else {
      detailForm.setRudate(jobReview.getRudate().toLocalDate().toString());
    }

    return new ReviewApiResult<>("00", "success", detailForm);
  }

  //리뷰 등록
  @PostMapping("/{jobBoardIdPk}/inquiry")
  public ReviewApiResult<Object> add(@PathVariable String jobBoardIdPk,
                                     @RequestBody ReviewAddForm reviewAddForm,
                                     HttpSession session){
    log.info("add() 호출됨!");
    log.info("reviewAddForm={}",reviewAddForm);

    JobReview jobReview = new JobReview();
    jobReview.setContentReview(reviewAddForm.getContentReview());
    jobReview.setRstar(reviewAddForm.getRstar());
    jobReview.setJobBoardIdPk(jobBoardIdPk);
    LoginPerson loginPerson = (LoginPerson) session.getAttribute("loginMember");//세션에서 로그인 정보 가져오기
    jobReview.setIdReview(loginPerson.getIdPerson());

    JobReview savedReview = jobReviewSVC.save(jobReview);

    return new ReviewApiResult<>("00", "success", savedReview);
  }

  //리뷰 수정
  @PatchMapping("/{jobBoardIdPk}")
  public ReviewApiResult<Object> edit(@PathVariable String jobBoardIdPk,
                                      @RequestBody ReviewEditForm reviewEditForm,
                                      HttpSession session){
    log.info("edit() 호출됨!");

    LoginPerson loginPerson = (LoginPerson) session.getAttribute("loginMember");
    if (!loginPerson.getIdPerson().equals(reviewEditForm.getIdReview())) {
      log.info("edit() : 아이디 불일치");
      return new ReviewApiResult<>("01", "fail", "리뷰 수정에 실패하였습니다.");
    }

    JobReview jobReview = jobReviewSVC.inquiry(reviewEditForm.getReviewIdPK());
    if (jobReview == null) {
      log.info("edit() : 존재하지 않는 리뷰입니다.");
      return new ReviewApiResult<>("01", "fail", "리뷰 수정에 실패하였습니다.");
    }

    jobReview.setContentReview(reviewEditForm.getContentReview());
    jobReview.setRstar(reviewEditForm.getRstar());
    JobReview modifiedReview = jobReviewSVC.update(jobReview);

    if (modifiedReview == null) {
      log.info("edit() : 리뷰 수정에 실패하였습니다.");
      return new ReviewApiResult<>("01", "fail", "리뷰 수정에 실패하였습니다.");
    }

    return new ReviewApiResult<>("00", "success", modifiedReview);
  }

  //리뷰 삭제
  @DeleteMapping("/{jobBoardIdPk}")
  public ReviewApiResult<Object> delete(@PathVariable Long rnum,
                                        HttpSession session){
    log.info("delete() 호출됨!");

    LoginPerson loginMember = (LoginPerson) session.getAttribute("loginMember");
    int result = jobReviewSVC.delete(rnum, loginMember.getIdPerson());
    ReviewApiResult<Object> reviewApiResult = null;

    if(result == 1){
      reviewApiResult = new ReviewApiResult<>("00", "success", "댓글 삭제를 성공했습니다.");
      log.info("apiResult = {}", reviewApiResult);
    }else{
      reviewApiResult = new ReviewApiResult<>("01", "fail", "댓글 삭제를 실패했습니다.");
      log.info("apiResult = {}", reviewApiResult);
    }

    return reviewApiResult;
  }








}