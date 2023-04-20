package com.back.endTest.domain.jobBoard.svc;

import com.back.endTest.domain.entity.JobBoard;
import com.back.endTest.domain.entity.JobBoardImage;
import com.back.endTest.domain.jobBoard.dao.JobBoardDAO;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class JobBoardSVCImpl implements JobBoardSVC {

  private final JobBoardDAO jobBoardDAO;
  private final Path fileStorageLocation = Paths.get("src/main/resources/static/images").toAbsolutePath().normalize();

  @Autowired

  @PostConstruct
  public void initFileStorageLocation() {
    try {
      Files.createDirectories(fileStorageLocation);
    } catch (IOException e) {
      throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
    }
  }

  @Override
  public Long save(JobBoard jobBoard) {
    return jobBoardDAO.save(jobBoard);
  }

  @Override
  public Optional<JobBoard> inquiry(Long jobBoardIdPk) {
    return jobBoardDAO.inquiry(jobBoardIdPk);
  }

  @Override
  public int update(Long jobBoardIdPk, JobBoard jobBoard) {
    return jobBoardDAO.update(jobBoardIdPk, jobBoard);
  }

  @Override
  public int delete(Long jobBoardIdPk) {
    return jobBoardDAO.delete(jobBoardIdPk);
  }

  @Override
  public List<JobBoard> findAll() {
    return jobBoardDAO.findAll();
  }

  @Override
  public boolean isExist(Long jobBoardIdPk) {
    return jobBoardDAO.isExist(jobBoardIdPk);
  }

  @Override
  public String storeFile(MultipartFile file, Long jobBoardId) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      if (fileName.contains("..")) {
        throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
      }

      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      // 이미지 정보 저장
      JobBoardImage jobBoardImage = new JobBoardImage();
      jobBoardImage.setFileName(fileName);
      jobBoardImage.setFilePath(targetLocation.toString());
      jobBoardImage.setJobBoardId(jobBoardId);
      saveImageInfo(jobBoardImage);

      return fileName;
    } catch (IOException e) {
      throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
    }
  }

  @Override
  public Long saveImageInfo(JobBoardImage jobBoardImage) {
    jobBoardDAO.save(jobBoardImage);
    return null;
  }



}







