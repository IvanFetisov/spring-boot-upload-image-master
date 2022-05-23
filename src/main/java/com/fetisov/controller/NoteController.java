package com.fetisov.controller;

import com.fetisov.entities.Note;
import com.fetisov.repositories.NoteRepository;
import com.fetisov.services.NoteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Optional;

@Validated
@RestController
public class NoteController {
    private final String GETALLNOTES ="/notes/getAll";
    private final String GETNOTEBYID ="/get/note/{id}";
    private final String updateNoteById="/update/note/{id}";
    private final String DELETENOTEBYID="/delete/note/{id}";
    private final String CREATENOTE="/create/note";

    private NoteRepository noteRepository;
    private NoteServiceImpl noteServiceImpl ;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        this.noteServiceImpl = noteServiceImpl;
    }
    @PostMapping(CREATENOTE)
    public ResponseEntity<NoteUploadResponce> uploadImage(@RequestParam("image") MultipartFile file , @RequestParam("text") String text)
        throws IOException
    {
       noteServiceImpl.uploadImage(file,text);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoteUploadResponce("Image uploaded successfully: "));
    }

    @GetMapping(GETNOTEBYID)
    public ResponseEntity<byte[]> getNoteById(@PathVariable("id") Integer id){
    return noteServiceImpl.getNoteById(id);
    }
    @DeleteMapping(DELETENOTEBYID)
    public void deleteNoteById (@PathVariable("id") Integer id){
       noteServiceImpl.deleteNoteById(id);
    }
    @GetMapping("/main")
    public String main (){

        return "";
    }

}




