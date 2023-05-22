package com.cryptocurrencyalert.services;

import com.cryptocurrencyalert.dto.ImageDTO;
import com.cryptocurrencyalert.models.Image;
import com.cryptocurrencyalert.repisitories.ImagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@Slf4j
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
            log.info("Save new image " + file.getOriginalFilename());
        }
    }

    @Override
    public ResponseEntity<Object> show(int id) {
        Image image = imagesRepository.findById(id).orElse(null);
        if(image == null){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        ImageDTO imageDTO = convertToImageDTO(image);
        return ResponseEntity.ok()
                .header("fileName",imageDTO.getOriginalFileName())
                .contentType(MediaType.valueOf(imageDTO.getContentType()))
                .contentLength(imageDTO.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageDTO.getBytes())));
    }

    @Override
    public ResponseEntity<Object> delete(int id) {
        Image image = imagesRepository.findById(id).orElse(null);
        if(image != null){
            imagesRepository.delete(image);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
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
    private ImageDTO convertToImageDTO(Image image){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(image,ImageDTO.class);
    }
}
