package com.login.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.jwt.entity.Formation;

@Repository
public interface FormationDao extends JpaRepository <Formation, Long> {

}
