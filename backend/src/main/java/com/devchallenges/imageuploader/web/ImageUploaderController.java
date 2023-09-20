package com.devchallenges.imageuploader.web;

import com.devchallenges.imageuploader.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/image")
public class ImageUploaderController {

    private final ImageService imageService;

    public ImageUploaderController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<String> saveImage(@RequestParam("image") MultipartFile imageData) throws IOException {
        final String key = imageService.saveImage(imageData.getBytes());
        return ResponseEntity.ok(key);
    }

    @GetMapping("/{key}")
    public ResponseEntity<byte[]> findImage(@PathVariable String key) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.getImageByKey(key));
    }
}
