package br.com.yuri.cavalcante.tcc.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.yuri.cavalcante.tcc.domain.User;
import br.com.yuri.cavalcante.tcc.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody User user){
		
		user = userService.insert(user);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		
		List<User> userList = new ArrayList<User>();
		userList = userService.findAll();
		
		return ResponseEntity.ok().body(userList);				
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> find(@PathVariable Integer id){
		
		User user = userService.find(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody User user, @PathVariable Integer id){
	
		user.setId(id);
		user = userService.update(user);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		userService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
