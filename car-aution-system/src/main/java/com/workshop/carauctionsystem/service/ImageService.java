package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Image;

import java.util.List;

public interface ImageService {
    public List<Image> getAllImageByCarId(Long CarId);

    public void saveImageForCar (Image image);
}
