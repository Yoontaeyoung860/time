package com.back.endTest;

import com.back.endTest.domain.entity.BusinessRegistrationInfo;

import java.util.Optional;

public interface BusinessRegistrationInfoDao {
  Optional<BusinessRegistrationInfo> findByBNo(String bNo);
  void save(BusinessRegistrationInfo businessRegistrationInfo);
}