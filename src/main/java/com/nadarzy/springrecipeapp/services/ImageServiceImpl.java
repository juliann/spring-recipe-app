package com.nadarzy.springrecipeapp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {
  @Override
  public void saveImageFile(Long id, MultipartFile multipartFile) {}
}
