package br.com.yuri.cavalcante.tcc.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.yuri.cavalcante.tcc.domain.Area;
import br.com.yuri.cavalcante.tcc.domain.Catalog;
import br.com.yuri.cavalcante.tcc.services.CatalogService;

@RestController
@RequestMapping(value = "/catalogs")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Catalog catalog){
		try {

			catalog = catalogService.insert(catalog);
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(catalog.getId())
					.toUri();
			return ResponseEntity.created(uri).build();		
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return null;
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Catalog>> findAll(){
		
		List<Catalog> catalogList = catalogService.findAll();
		return ResponseEntity.ok().body(catalogList);
	}
	
	@CrossOrigin
	@GetMapping(value = "/{id}/catalog")
	public ResponseEntity<Catalog> find(@PathVariable Integer id){
		
		Catalog catalog = catalogService.find(id);
		return ResponseEntity.ok().body(catalog);
		
	}

	@CrossOrigin 
	@GetMapping(value = "/{searchText}") 
	public ResponseEntity<List<Catalog>> findByDomainOrArea(@PathVariable String searchText){
		List<Catalog> catalogs = new ArrayList<>();
		
		catalogs = catalogService.findAllByDomainOrArea(searchText);
		
		return ResponseEntity.ok().body(catalogs);
		 
		
	}
}
