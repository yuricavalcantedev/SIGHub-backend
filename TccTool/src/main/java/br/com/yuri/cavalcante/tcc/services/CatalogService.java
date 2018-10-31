package br.com.yuri.cavalcante.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.yuri.cavalcante.tcc.controllers.exceptions.ObjectNotFoundException;
import br.com.yuri.cavalcante.tcc.domain.Catalog;
import br.com.yuri.cavalcante.tcc.repositories.CatalogRepository;


@Service
public class CatalogService {
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	public Catalog insert(Catalog catalog) {
		
		catalog.setId(null);
		return catalogRepository.save(catalog);
	}
	
	public List<Catalog> findAll(){
		
		return catalogRepository.findAll();		
	}
	
	public Page<Catalog> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return catalogRepository.findAll(pageRequest);
	}
	
	public Catalog find(Integer id){
		
		Optional<Catalog> catalog = catalogRepository.findById(id); 
		return catalog.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: - " + id + "Type:" + Catalog.class.getName()));
	}
	
	public Catalog update(Catalog catalog) {
		
		Catalog updatedCatalog = find(catalog.getId());
		
		updatedCatalog.setYear(catalog.getYear());
		updatedCatalog.setReference(catalog.getReference());
		updatedCatalog.setApplicationDomainList(catalog.getApplicationDomainList());
		updatedCatalog.setKeyWords(catalog.getKeyWords());
		updatedCatalog.setDescription(catalog.getDescription());
		updatedCatalog.setSoftgoalMain(catalog.getSoftgoalMain());
		updatedCatalog.setNotesList(catalog.getNotesList());
		updatedCatalog.setAreasList(catalog.getAreasList());
		
		return catalogRepository.save(updatedCatalog);
	}
		
	public void delete(Integer id) {		 
		
		find(id);  //if didn't work, it's gonna throw a exception already.
		catalogRepository.deleteById(id);
//		catalogRepository.deleteSoftGoalsByIdFather(id);
		
		
	}

}
