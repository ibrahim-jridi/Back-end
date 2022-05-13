package com.login.jwt.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.FormatterDao;
import com.login.jwt.entity.Formatter;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class imageController {
	@Autowired
	  private FormatterDao formatterDao;
	@Autowired  ServletContext context;
	@GetMapping(path="/Imgformatter/{id}")
	 public byte[] getPhoto(@PathVariable("id") String id) throws Exception{
		 Formatter formatter   = formatterDao.findByUserName(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+formatter.getFileName()));
	 }
}
