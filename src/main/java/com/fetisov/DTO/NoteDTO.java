package com.fetisov.DTO;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name="notes")
public class NoteDTO {
        @Id
        @Column(name = "id")
        Integer id;

        @Column(name = "text")
        private String text;

         @Column(name = "type")
          private String type;

        @Column(name = "picture",unique = false, length = 100000)
        private byte[] noteImage;

        @Column (name = "date")
        private LocalDateTime dateTime;


    public byte[] getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(byte[] noteImage) {
        this.noteImage = noteImage;
    }

        public LocalDateTime getDateTime() {
        return dateTime;
    }

        public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

        public Integer getId() {return id;}

        public void setId(Integer id) {
            this.id = id;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }


}


