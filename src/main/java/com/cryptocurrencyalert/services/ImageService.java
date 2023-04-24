package com.cryptocurrencyalert.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void saveImage(MultipartFile file) throws IOException;
}
