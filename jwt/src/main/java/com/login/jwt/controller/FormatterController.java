package com.login.jwt.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.jwt.dao.FormationDao;
import com.login.jwt.dao.FormatterDao;
import com.login.jwt.dao.RoleDao;
import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.Formation;
import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.Role;
import com.login.jwt.entity.User;
import com.login.jwt.service.MailService;
import com.login.jwt.service.UserService;
import com.login.jwt.util.ResourceNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.ServletContext;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class FormatterController {

	/*
	 * @Autowired private UserDao userDao;
	 * 
	 * 
	 * @Autowired private RoleDao roleDao;
	 */
	@Autowired
	  private UserDao userDao;
	 @Autowired
	  private FormatterDao formatterDao;
	 
	@Autowired
	private UserService userService;
	@Autowired
	private FormationDao formationDao;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	  private MailService  mailService;
	@Autowired  
	ServletContext context;
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
	
	// get all formatter
	@GetMapping("/formatters")
	
		
		//Role formatterRole = new Role();
		public List<Formatter>  getAllFormatters(){
			return formatterDao.findAll();
		
		
	}	
	/*
	 * @GetMapping("/Formatters")
	 * 
	 * 
	 * //Role formatterRole = new Role(); public Iterable<User> getAllFormatterss(){
	 * return userDao.findAll();
	 * }
	 */
	
	// create formatter rest api and save it in formatterDao
	@PostMapping({"/formatterss"}) 
	public Formatter registerNewFormatter(@RequestBody  Formatter formatter)  { 
		formatter.setUserPassword(getEncodedPassword(formatter.getUserPassword()));
		formatter.setUserConfirmPassword(getEncodedPassword(formatter.getUserConfirmPassword()));
		List<Formatter> formatters = formatterDao.findAll();
			for (Formatter formatterExist : formatters) {
				if (formatter.getUserName().equals(formatterExist.getUserName())) {
					System.out.println("il y a un utilisateur avec ce Pseudo svp changer vos");
					return null;
				}}
			String email=formatter.getEmail();
			mailService.sendPass(email,formatter);
			    
			    return formatterDao.save(formatter);
			  }
	
	// create formatter rest api and save it in userDao
	@PostMapping({"/formatters"})
	public User registerNewFormatter(@RequestBody
			  User formatter) { 
		
		return userService.registerNewFormatter(formatter);
		
			  }
	
	// get formatter by id rest api
	@GetMapping("/formatterss/{id}")
	public ResponseEntity<Formatter> getFormatterById(@PathVariable Long id) {
		Formatter formatter = formatterDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		return ResponseEntity.ok(formatter);
	}
	 
	//get formatter by username
	@GetMapping("/findByUsername/{username}")
	 public Formatter findByFormatterName(@PathVariable String username) {
		   Optional<Formatter> formatters = formatterDao.findByUserName(username);
		    if (formatters.isPresent()) {
		     Formatter formatter = formatters.get();
		   return formatter;
		  }
		   return null;
		 }
	//get formation by formatterName
			@GetMapping("/formatterss/{username}")
			public List<Formation> findByFormatterUserName(@PathVariable String username) {
				Formatter formatter = formatterDao.findByUserName(username).get();
				List<Formation> formation;
				 formation = (List<Formation>) formationDao.findAll();
				for (Formation formation2 : formation) {
					if (formatter.getUserName() != ((Formation) formation).getFormatter().getUserName()) {
						return null ;
					} 
				}
				return formation;
				
				
						
			}
	
	// update formatter rest api
	
	@PutMapping("/formatterss/{id}")
	public ResponseEntity<Formatter> updateFormatter(@RequestParam("file") MultipartFile file,@PathVariable Long id, @RequestBody Formatter formatterDetails){
		Formatter formatter = formatterDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		/*
		 * deletUserImage(formatter); String filename = file.getOriginalFilename();
		 * String newFileName =
		 * FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		 * formatter.setFileName(newFileName); addUserImage(file);
		 */
		/*
		 * boolean isExit = new File(context.getRealPath("/Images/")).exists(); if
		 * (!isExit) { new File (context.getRealPath("/Images/")).mkdir();
		 * System.out.println("mk dir............."); } String filename =
		 * file.getOriginalFilename(); String newFileName =
		 * FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		 * File serverFile = new File
		 * (context.getRealPath("/Images/"+File.separator+newFileName)); try {
		 * System.out.println("Image");
		 * FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }
		 */
		
		formatter.setUserName(formatterDetails.getUserName());
		//formatter.setFileName(newFileName);
		formatter.setUserFirstName(formatterDetails.getUserFirstName());
		formatter.setUserLastName(formatterDetails.getUserLastName());
		formatter.setEmail(formatterDetails.getEmail());
		formatter.setAdresse(formatterDetails.getAdresse());
		formatter.setSpecialite(formatterDetails.getSpecialite());
		formatter.setUserPassword(getEncodedPassword(formatter.getUserPassword()));
		formatter.setUserConfirmPassword(getEncodedPassword(formatter.getUserConfirmPassword()));
		
		Formatter updatedFormatter = formatterDao.save(formatter);
		Formatter updateFormatter = userDao.save(formatter);
		return ResponseEntity.ok(updatedFormatter);
		
	}
	private void deletUserImage(Formatter formatter) {
		System.out.println("delete image");
		try {
			File file = new File (context.getRealPath("/ImgUsers/"+formatter.getFileName()));
			System.out.println(formatter.getFileName());
			if (file.delete()) {
				System.out.println(file.getName()+"is deleted");
				
			}else {
				System.out.println("deleting operation is failed");
			}
		} catch (Exception e) {
			System.out.println("failed to delet image");
		}
	}
	private void addUserImage(MultipartFile file) {
		 boolean isExit = new File(context.getRealPath("/ImgUsers/")).exists();
		    if (!isExit)
		    {
		    	new File (context.getRealPath("/ImgUsers/")).mkdir();
		    	System.out.println("mk dir Imagess.............");
		    }
		    
		    
		    String filename = file.getOriginalFilename();
		    String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		    File serverFile = new File (context.getRealPath("/ImgUsers/"+File.separator+newFileName));
		    try
		    {
		    	
		    	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
		    	 
		    }catch(Exception e) {
		    	System.out.println("failed to upload image");
		    }
		    
		
	}
	 @GetMapping(path="/Imgformatter/{id}")
	 public byte[] getPhoto(@PathVariable("id") String id) throws Exception{
		 Formatter formatter   = formatterDao.findByUserName(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+formatter.getFileName()));
	 }
	
	// delete formatter rest api
	@DeleteMapping("/formatterss/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFormatter(@PathVariable Long id){
		Formatter formatter = formatterDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		
		formatterDao.delete(formatter);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}