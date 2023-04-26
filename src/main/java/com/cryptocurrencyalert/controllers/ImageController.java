package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.models.Image;
import com.cryptocurrencyalert.repisitories.ImagesRepository;
import com.cryptocurrencyalert.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    private final ImagesRepository imagesRepository;

    @Autowired
    public ImageController(ImageService imageService, ImagesRepository imagesRepository) {
        this.imageService = imageService;
        this.imagesRepository = imagesRepository;
    }
    @PostMapping ("/save")
    public String saveImage(@RequestParam("file")MultipartFile file) throws Exception{
        imageService.saveImage(file);
        return "redirect:/images/save";
    }
    @GetMapping("/save")
    public String newImage(){
        return "newImage2";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable("id") int id) {
        Image image = imagesRepository.findById(id).orElse(null);
        if(image == null){
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok()
                .header("fileName",image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Image image = imagesRepository.findById(id).orElse(null);
        if(image != null){
            imagesRepository.delete(image);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
