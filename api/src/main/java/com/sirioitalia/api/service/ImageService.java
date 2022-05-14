package com.sirioitalia.api.service;


import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> getImages() throws ResourceException;

    Image getImageById(Long imageId) throws ResourceException;

    Image createImage(Image imageDetails) throws ResourceException;

    List<Image> createImages(List<Image> imagesDetails) throws ResourceException;

    void deleteImage(Long imageId) throws ResourceException;
}
