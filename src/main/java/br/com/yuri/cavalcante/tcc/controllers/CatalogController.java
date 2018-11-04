package br.com.yuri.cavalcante.tcc.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.yuri.cavalcante.tcc.domain.Catalog;
import br.com.yuri.cavalcante.tcc.services.CatalogService;

@RestController
@RequestMapping(value = "/catalogs")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Catalog catalog){
		
		
		//aqui dentro é que vão ser inseridos no banco todos os objetos que estão dentro de catalog
		//authors, applicationDomains and areas don't because they're already in the database,
		//but notes, softgoal and users will be added.
		
		catalog = catalogService.insert(catalog);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(catalog.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
		
	}

	@GetMapping
	public ResponseEntity<List<Catalog>> findAll(){
		
		List<Catalog> catalogList = catalogService.findAll();
		return ResponseEntity.ok().body(catalogList);
	}
	
}
