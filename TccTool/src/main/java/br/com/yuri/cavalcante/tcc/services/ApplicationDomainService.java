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
import br.com.yuri.cavalcante.tcc.domain.ApplicationDomain;
import br.com.yuri.cavalcante.tcc.repositories.ApplicationDomainRepository;

@Service
public class ApplicationDomainService {

	@Autowired
	private ApplicationDomainRepository applicationDomainRepository;
	
	public ApplicationDomain insert(ApplicationDomain applicationDomain) {
		applicationDomain.setId(null);
		return applicationDomainRepository.save(applicationDomain);
	}
	
	public List<ApplicationDomain> findAll(){
		
		return applicationDomainRepository.findAll();		
	}
	
	public Page<ApplicationDomain> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return applicationDomainRepository.findAll(pageRequest);
	}
	
	public ApplicationDomain find(Integer id){
		Optional<ApplicationDomain> applicationDomain = applicationDomainRepository.findById(id); 
		return applicationDomain.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: - " + id + "Type:" + ApplicationDomain.class.getName())); 
	}
	
	public ApplicationDomain update(ApplicationDomain applicationDomain) {
		ApplicationDomain updatedAplicationDomain = find(applicationDomain.getId());
		updatedAplicationDomain.setName(applicationDomain.getName());
		updatedAplicationDomain.setDescription(applicationDomain.getDescription());
		updatedAplicationDomain.setExampleList(applicationDomain.getExampleList());
		
		return applicationDomainRepository.save(updatedAplicationDomain);
	}
	
		
	public void delete(Integer id) {		 
		
		find(id); //if didn't work, it's gonna throw a exception already.
		try {
			applicationDomainRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible delete an applicationDomain that has catalogs linked to it.");
		}
		
	}
}
