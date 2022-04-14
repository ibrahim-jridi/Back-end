package com.login.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.jwt.entity.Theme;
@Repository
public interface ThemeDao extends JpaRepository<Theme, Long>{

}
