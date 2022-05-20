package com.fetisov.repositories;

import com.fetisov.entities.UploadImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadImageRepository extends JpaRepository<UploadImageEntity, String> {
}
