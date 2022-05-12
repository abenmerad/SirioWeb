package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Color;

import java.util.List;

public interface ColorService {
    List<Color> getColors() throws ResourceException;

    Color getColorById(Long colorId) throws ResourceException;

    Color createColor(Color colorDetails) throws ResourceException;

    Color updateColor(Long colorId, Color colorDetails) throws ResourceException;

    void deleteColor(Long colorId) throws ResourceException;
}
