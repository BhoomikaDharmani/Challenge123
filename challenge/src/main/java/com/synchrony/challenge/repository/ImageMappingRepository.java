package com.synchrony.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synchrony.challenge.model.ImageMapping;

public interface ImageMappingRepository extends JpaRepository<ImageMapping, Integer> {

	List<ImageMapping> findByUserId(Integer userId);
	
	Integer deleteByImageDeleteHash(String imageDeleteHash);
}
