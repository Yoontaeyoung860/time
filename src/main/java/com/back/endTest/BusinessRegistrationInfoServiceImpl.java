package com.back.endTest;

import com.back.endTest.domain.entity.BusinessRegistrationInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
public class BusinessRegistrationInfoServiceImpl implements BusinessRegistrationInfoService {
  private BusinessRegistrationInfoDao businessRegistrationInfoDao;
  public BusinessRegistrationInfoServiceImpl(BusinessRegistrationInfoDao businessRegistrationInfoDao) {
    this.businessRegistrationInfoDao = businessRegistrationInfoDao;
  }
  @Override
  public boolean isValidBNo(String bNo) {
    Optional<BusinessRegistrationInfo> businessRegistrationInfoOpt = businessRegistrationInfoDao.findByBNo(bNo);
    // 진위여부 확인
    if (businessRegistrationInfoOpt.isPresent()) {
      BusinessRegistrationInfo businessRegistrationInfo = businessRegistrationInfoOpt.get();
      return "Y".equals(businessRegistrationInfo.getBSttCd());
    }
    return false;
  }
  @Override
  public void save(BusinessRegistrationInfo businessRegistrationInfo) {
    businessRegistrationInfoDao.save(businessRegistrationInfo);
  }
}