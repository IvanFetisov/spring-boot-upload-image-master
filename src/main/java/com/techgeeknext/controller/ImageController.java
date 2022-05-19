package com.techgeeknext.controller;

import com.techgeeknext.entities.Image;
import com.techgeeknext.repositories.ImageRepository;
import com.techgeeknext.services.ImageService;
import com.techgeeknext.services.ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
@CrossOrigin() // open for all ports
public class ImageController {


    @Autowired
    private static ImageRepository imageRepository;
    @Autowired
    private static ImageServiceImp imageServiceImp;

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageRepository.
                save(
                Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageServiceImp.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }
/*
СМОТРИ, ПОЛУЧАЕТСЯ, ЧТО ULLPOINTEREXCEPTION вылезает из-за того, что репозиторий не может это грамотно сохранить
1) Почему?
2) Как тогда избежать данной проблемы ?
3)
 */
    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageServiceImp.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageServiceImp.decompressImage(dbImage.get().getImage()));
    }
}