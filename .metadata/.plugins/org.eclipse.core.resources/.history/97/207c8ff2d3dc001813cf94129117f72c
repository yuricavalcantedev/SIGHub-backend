package br.com.yuri.cavalcante.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.yuri.cavalcante.tcc.domain.Catalog;
import br.com.yuri.cavalcante.tcc.repositories.CatalogRepository;

//fazer 1 e depois aplicar para os outros.

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

		return null; 
	}
		
	public void delete(Integer id) {		 
		
		find(id); //se não der certo, ele já vai lançar uma exceção
		try {
			catalogRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			//throw new DataIntegrityException("Não é possível excluir uma catalog que possui produtos.");
		}
		
	}
	
	

}
