package com.login.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.login.jwt.entity.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	Optional<ImageModel> findByName(String name);
}