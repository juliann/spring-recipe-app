package com.nadarzy.springrecipeapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
  public void saveImageFile(Long id, MultipartFile multipartFile);
}
