package com.devchallenges.imageuploader.service;

import com.devchallenges.imageuploader.model.Image;
import com.devchallenges.imageuploader.repository.ImageRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ImageService {

    private final static int KEY_LENGTH = 35;

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public String saveImage(byte[] imageData) {
        final Image image = new Image(getRandomKey(), imageData);
        imageRepository.save(image);
        return image.getImageKey();
    }

    public byte[] getImageByKey(String key) {
        final Optional<Image> image = imageRepository.findByImageKey(key);
        return image.map(Image::getImageData).orElse(null);
    }

    private String getRandomKey() {
        String key = null;
        while (key == null) {
            key = RandomStringUtils.randomAlphabetic(KEY_LENGTH);
            if (imageRepository.existsByImageKey(key)) {
                key = null;
            }
        }
        return key;
    }
}
