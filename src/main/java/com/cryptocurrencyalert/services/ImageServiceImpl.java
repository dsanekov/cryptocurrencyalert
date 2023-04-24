package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.models.Image;
import com.cryptocurrencyalert.repisitories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImagesRepository imagesRepository;
    @Autowired
    public ImageServiceImpl(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public void saveImage(MultipartFile file) throws IOException {
        if (file.getSize() != 0) {
            Image image = toImageEntity(file);
            imagesRepository.save(image);
            System.out.println("Save new image");
        }
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image newImage = new Image();
        newImage.setName(file.getName());
        newImage.setSize(file.getSize());
        newImage.setContentType(file.getContentType());
        newImage.setOriginalFileName(file.getOriginalFilename());
        newImage.setBytes(file.getBytes());
        return newImage;
    }
}
