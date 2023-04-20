package com.back.endTest;

import com.back.endTest.domain.entity.BusinessRegistrationInfo;
import org.springframework.stereotype.Service;


public interface BusinessRegistrationInfoService {
  boolean isValidBNo(String bNo);
  void save(BusinessRegistrationInfo businessRegistrationInfo);
}