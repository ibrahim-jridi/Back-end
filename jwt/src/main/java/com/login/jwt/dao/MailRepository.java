package com.login.jwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.jwt.entity.Mail;

public interface MailRepository extends JpaRepository<Mail, Long> {

}
