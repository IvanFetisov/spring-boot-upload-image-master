package com.techgeeknext.repositories;

import java.io.File;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techgeeknext.entities.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);

	@Query(value = "INSERT INTO images (name,type,images) values (name=:name,type=:type, images=images.images)",nativeQuery = true)
	void saveImage  (Image image);

}
