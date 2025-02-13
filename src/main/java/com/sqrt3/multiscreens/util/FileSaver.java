package com.sqrt3.multiscreens.util;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class FileSaver {

  private FileSaver() {
    // 인스턴스화 방지를 위한 private 생성자
  }

  public static void saveFile(String name, MultipartFile file) {
    try {
      File uploadDir = new File("C:/Files/");
      if (!uploadDir.exists()) {
        uploadDir.mkdirs();
      }

      File uploadedFile = new File(uploadDir, name + ".png");
      file.transferTo(uploadedFile);
    } catch (IOException e) {
      log.warn("Error while saving file");
    }
  }
}
