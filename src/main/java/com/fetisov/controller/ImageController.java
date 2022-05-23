//package com.fetisov.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Validated
//@RestController
////@CrossOrigin(origins = "http://localhost:8082") open for specific port
//public class ImageController {
//
//   @Autowired
//    private  ImageRepository imageRepository;
//
//   @Autowired
//    private static ImageServiceImp imageServiceImp;
//
//    Image image = new Image();
//
//    @PostMapping("/upload")
//    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file)
//            throws IOException {
//
//        image.setName(file.getOriginalFilename());
//        image.setImage(ImageServiceImp.compressImage(file.getBytes()));
//        image.setId(image.getId());
//        imageRepository.save(image);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ImageUploadResponse("Image uploaded successfully: " +
//                        file.getOriginalFilename()));
//    }
//
//  @GetMapping(path = {"/get/image/info/{name}"})
// public Image getImageDetails(@PathVariable("name") String name) throws IOException {
//        Optional<Image> imageInfo = imageRepository.findImageByName(name);
//        return imageInfo.get();
// }
//
//   @GetMapping(path = {"/get/image/{name}"})
// public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
//     final Optional<Image> dbImage = imageRepository.findByName(name);
//       return ResponseEntity
//               .ok()
//               .contentType(MediaType.valueOf(dbImage.get().getName()))
//               .body(ImageServiceImp.decompressImage(dbImage.get().getImage()));
//
// }
// @GetMapping(path = {"/mainPage"})
//    public ResponseEntity<byte[]> getAllImages(@PathVariable("name") String name) throws  IOException{
//        List<Image> allImages =  imageRepository.findAll();
//        return (ResponseEntity<byte[]>) allImages;
// }
//
//}