package com.back.endTest;

import com.back.endTest.domain.entity.BusinessRegistrationInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository
public class BusinessRegistrationInfoDaoImpl implements BusinessRegistrationInfoDao {
  // 구현할 내용
  private static Map<String, BusinessRegistrationInfo> fakeDatabase = new HashMap<>();

  @Override
  public Optional<BusinessRegistrationInfo> findByBNo(String bNo) {
    return Optional.ofNullable(fakeDatabase.get(bNo));
  }

  @Override
  public void save(BusinessRegistrationInfo businessRegistrationInfo) {
    fakeDatabase.put(businessRegistrationInfo.getBNo(), businessRegistrationInfo);
  }
}