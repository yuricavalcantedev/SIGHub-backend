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

import br.com.yuri.cavalcante.tcc.domain.Area;
import br.com.yuri.cavalcante.tcc.services.AreaService;

@RestController
@RequestMapping(value = "/area")
public class AreaController {
	
	//only admins can CREATE, UPDATE and DELETE the areas, commom user can just get them.
	
	@Autowired
	private AreaService areaService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Area area){
	
		area = areaService.insert(area);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(area.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<Area>> findAll(){
		
		List<Area> areaList = new ArrayList<>();
		areaList = areaService.findAll();
		
		return ResponseEntity.ok().body(areaList);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Area> find(@PathVariable Integer id){
		
		Area area = areaService.find(id);
		return ResponseEntity.ok().body(area);
		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Area area, @PathVariable Integer id){
		
		area.setId(id);
		area = areaService.update(area);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		areaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
