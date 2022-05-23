package com.fetisov.services;

import com.fetisov.controller.NoteUploadResponce;
import com.fetisov.entities.Note;
import com.fetisov.repositories.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class NoteServiceImpl {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public static byte[] compressImage(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
        }
        return outputStream.toByteArray();
    }
    public ResponseEntity<byte[]> getNoteById(Integer id){
        Optional<Note> note = noteRepository.findById(id);
        return ResponseEntity
                .ok().header(note.get().getText())
                .contentType(MediaType.valueOf(note.get().getType())).body(NoteServiceImpl.decompressImage(note.get().getNoteImage()));


}
    public ResponseEntity<NoteUploadResponce> uploadImage(MultipartFile file ,String text)
            throws IOException
    {
        Note note = new Note();

        note.setId(note.getId());
        note.setNoteImage(NoteServiceImpl.compressImage(file.getBytes()));
        note.setDateTime(LocalDateTime.now());
        note.setText(text);
        note.setType(file.getContentType());
        noteRepository.save(note);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoteUploadResponce("Image uploaded successfully: "));
    }

    public void deleteNoteById (Integer id){
        Note note = noteRepository.findById(id).get();
        noteRepository.delete(note);
    }

}
