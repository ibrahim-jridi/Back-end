package com.login.jwt.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.ThemeDao;
import com.login.jwt.entity.Theme;
import com.login.jwt.util.ResourceNotFoundException;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class themeController {
	@Autowired
	private ThemeDao  ThemeDao;
	
	// get all themes
	@GetMapping("/themes")
	public List<Theme> getAllthemes(){
		return ThemeDao.findAll();
	}		
	
	// create theme rest api
	@PostMapping("/themes")
	public Theme createtheme(@RequestBody Theme theme) {
		return ThemeDao.save(theme);
	}
	
	// get theme by id rest api
	@GetMapping("/themes/{id}")
	public ResponseEntity<Theme> getthemeById(@PathVariable Long id) {
		Theme theme = ThemeDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("theme n'est pas trouvé :" + id));
		return ResponseEntity.ok(theme);
	}
	
	// update theme rest api
	
	@PutMapping("/themes/{id}")
	public ResponseEntity<Theme> updatetheme(@PathVariable Long id, @RequestBody Theme themeDetails){
		Theme theme = ThemeDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("theme n'est pas trouvé :" + id));
		
		theme.setName(themeDetails.getName());
		theme.setDescription(themeDetails.getDescription());
		
		
		Theme updatedtheme = ThemeDao.save(theme);
		return ResponseEntity.ok(updatedtheme);
	}
	
	// delete theme rest api
	@DeleteMapping("/themes/{id}")
	public ResponseEntity<Map<String, Boolean>> deletetheme(@PathVariable Long id){
		Theme theme = ThemeDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("theme n'est pas trouvé :" + id));
		
		ThemeDao.delete(theme);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
