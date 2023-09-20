package com.devchallenges.imageuploader.model;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, length = 35)
    private String imageKey;

    @Lob
    private byte[] imageData;

    public Image() {
    }

    public Image(String imageKey, byte[] imageData) {
        this.imageKey = imageKey;
        this.imageData = imageData;
    }

    public String getImageKey() {
        return imageKey;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
