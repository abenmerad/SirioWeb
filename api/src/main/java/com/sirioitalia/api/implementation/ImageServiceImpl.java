package com.sirioitalia.api.implementation;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Image;
import com.sirioitalia.api.repository.ImageRepository;
import com.sirioitalia.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;

    public ImageServiceImpl() {
        super();
    }

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        super();
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getImages() throws ResourceException {
        return (List<Image>) imageRepository.getImages();
    }

    @Override
    public Image getImageById(Long imageId) throws ResourceException {
        Image imageFound = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceException("404", "Image not found", HttpStatus.NOT_FOUND));

        return imageFound;
    }

    @Override
    public Image createImage(Image imageDetails) throws ResourceException {
        try {
            return imageRepository.save(imageDetails);
        } catch(Exception e) {
            throw new ResourceException(e.getMessage(), e.getCause(), HttpStatus.CONFLICT);
        }
    }

    public List<Image> createImages(List<Image> imagesDetails) throws ResourceException {
        try {
            return (List<Image>) imageRepository.saveAll(imagesDetails);
        } catch(Exception e) {
            throw new ResourceException(e.getMessage(), e.getCause(), HttpStatus.CONFLICT);
        }
    }


    @Override
    public void deleteImage(Long imageId) throws ResourceException {
        Image imageToDelete = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceException("404", "Image not found", HttpStatus.NOT_FOUND));

        imageRepository.delete(imageToDelete);
    }
}
