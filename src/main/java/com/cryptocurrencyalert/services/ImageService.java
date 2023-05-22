package com.cryptocurrencyalert.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void saveImage(MultipartFile file) throws IOException;
    ResponseEntity<Object> show(int id);
    ResponseEntity<Object> delete(int id);
}
