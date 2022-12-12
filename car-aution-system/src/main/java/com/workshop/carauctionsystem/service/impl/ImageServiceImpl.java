package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Image;
import com.workshop.carauctionsystem.repository.ImageRepository;
import com.workshop.carauctionsystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public List<Image> getAllImageByCarId(Long CarId) {
        return imageRepository.findAllByCarId(CarId);
    }

    @Override
    public void saveImageForCar(Image image) {
        imageRepository.save(image);
    }


}
