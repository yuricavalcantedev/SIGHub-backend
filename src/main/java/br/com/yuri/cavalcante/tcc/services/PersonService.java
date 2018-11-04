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
import br.com.yuri.cavalcante.tcc.domain.Person;
import br.com.yuri.cavalcante.tcc.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person insert(Person person) {
		
		person.setId(null);
		return personRepository.save(person);
	}
	
	public List<Person> findAll(){
		
		return personRepository.findAll();		
	}
	
	public Page<Person> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return personRepository.findAll(pageRequest);
	}
	
	public Person find(Integer id){
		
		Optional<Person> person = personRepository.findById(id); 
		return person.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: - " + id + "Type:" + Person.class.getName())); 
	}
	
	public Person update(Person person) {
		
		Person updatedPerson = find(person.getId());
		updatedPerson.setEmail(person.getEmail());
		
		return personRepository.save(updatedPerson);
	}
	
		
	public void delete(Integer id) {		 
		
		find(id); //if didn't work, it's gonna throw a exception already.
		try {
			personRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible delete an person that has catalogs linked to it.");
			// I think that hasn't problem to do that, because the catalog doesn't need a person, just a user linked. I mean, a catalog may has just a owner as author (remember to set owner as an author in the catalog). 
		}
		
	}
}
