package com.fetisov.repositories;

import com.fetisov.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer>, JpaSpecificationExecutor<Note> {
    Optional<Note> findById(Integer id);
    Note findNOteById(Integer id);
}
