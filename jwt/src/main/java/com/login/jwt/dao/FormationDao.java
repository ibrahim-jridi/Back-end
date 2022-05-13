package com.login.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.jwt.entity.Formation;
import com.login.jwt.entity.Formatter;

@Repository
public interface FormationDao extends JpaRepository <Formation, Long> {
	
}
