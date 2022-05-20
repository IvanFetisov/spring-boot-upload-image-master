package com.fetisov.controller;
import com.fetisov.entities.Image;
import com.fetisov.repositories.ImageRepository;
import com.fetisov.services.ImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Validated
@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
public class ImageController {

   @Autowired
    private  ImageRepository imageRepository;

   @Autowired
    private static ImageServiceImp imageServiceImp;

    Image image = new Image();

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImage(ImageServiceImp.compressImage(file.getBytes()));
        image.setId(image.getId());


        imageRepository.save(image);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

  @GetMapping(path = {"/get/image/info/{name}"})
 public Image getImageDetails(@PathVariable("name") String name) throws IOException {
        Optional<Image> imageInfo = imageRepository.findImageByName(name);
        return imageInfo.get();
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