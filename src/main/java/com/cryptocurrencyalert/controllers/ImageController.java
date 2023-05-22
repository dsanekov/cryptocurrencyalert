package com.cryptocurrencyalert.controllers;

import com.cryptocurrencyalert.models.Image;
import com.cryptocurrencyalert.repisitories.ImagesRepository;
import com.cryptocurrencyalert.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Images", description = "Operations with images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    @PostMapping ("/save")
    @Operation(summary = "Save new image")
    public String saveImage(@RequestParam("file")MultipartFile file) throws Exception{
        imageService.saveImage(file);
        return "redirect:/images/save";
    }
    @GetMapping("/save")
    @Operation(summary = "Page for new image")
    public String newImage(){
        return "newImage2";
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Get image by id")
    public ResponseEntity<Object> show(@PathVariable("id") int id) {
        return imageService.show(id);
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete image by id")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        return imageService.delete(id);
    }
}
