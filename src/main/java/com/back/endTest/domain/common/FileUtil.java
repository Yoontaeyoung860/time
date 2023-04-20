package com.back.endTest.domain.common;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtil {
  public static Path generateUniqueFilePath(String baseDirectory, String originalFilename) {
    String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
    return Paths.get(baseDirectory, uniqueFilename);
  }
}
