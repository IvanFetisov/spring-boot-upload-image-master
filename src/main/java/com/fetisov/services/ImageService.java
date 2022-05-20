package com.fetisov.services;


import com.fetisov.DTO.ImageDTO;
import com.fetisov.controller.ImageUploadResponse;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {

    ImageUploadResponse compressImage (ImageDTO imageDTO);
    ImageUploadResponse decompressImage (ImageDTO imageDTO);
}
