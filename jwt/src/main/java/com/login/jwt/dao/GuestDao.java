package com.login.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.Guest;

public interface GuestDao extends JpaRepository<Guest, Long> {
	Guest findByUserName(String name);
}
