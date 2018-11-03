package br.com.yuri.cavalcante.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.yuri.cavalcante.tcc.controllers.exceptions.DataIntegrityException;
import br.com.yuri.cavalcante.tcc.controllers.exceptions.ObjectNotFoundException;
import br.com.yuri.cavalcante.tcc.domain.User;
import br.com.yuri.cavalcante.tcc.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User insert(User user) {
		user.setId(null);
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		
		return userRepository.findAll();		
	}
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return userRepository.findAll(pageRequest);
	}
	
	public User find(Integer id){
		Optional<User> user = userRepository.findById(id); 
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: - " + id + "Type:" + User.class.getName())); 
	}
	
	public User update(User user) {
		User updatedUser = find(user.getId());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setPassword(user.getPassword());
		
		return userRepository.save(updatedUser);
	}
	
		
	public void delete(Integer id) {		 
		
		find(id); //if didn't work, it's gonna throw a exception already.
		try {
			userRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible delete an user that has catalogs linked to it.");
			// I think that hasn't problem to do that, because the catalog doesn't need a user, just a user linked. I mean, a catalog may has just a owner as author (remeber to set owner as an author in the catalog). 
		}
		
	}
}
