package br.com.yuri.cavalcante.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import br.com.yuri.cavalcante.tcc.controllers.exceptions.DataIntegrityException;
import br.com.yuri.cavalcante.tcc.controllers.exceptions.ObjectNotFoundException;
import br.com.yuri.cavalcante.tcc.domain.ApplicationDomain;
import br.com.yuri.cavalcante.tcc.domain.Area;
import br.com.yuri.cavalcante.tcc.repositories.AreaRepository;

@Service
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;

	public Area insert(Area area) {

		area.setId(null);
		if(!existsArea(area))
			return areaRepository.save(area);
		
		throw new DataIntegrityException("Already exists an area with this name - Type " + Area.class.getName());
	}

	public List<Area> findAll(){

		return areaRepository.findAll();		
	}

	public Page<Area> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return areaRepository.findAll(pageRequest);
	}

	public Area find(Integer id){

		Optional<Area> area = areaRepository.findById(id); 
		return area.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + " - Type:" + Area.class.getName())); 
	}
	
	public boolean existsArea(Area area) {
		
		List<Area> listAreas = findAll();
		for(Area areaAux : listAreas) {
			if(areaAux.equals(area))
				return true;
		}
		
		return false;
	}

	public Area update(Area area) {

		Area updatedArea = find(area.getId());
		updatedArea.setName(area.getName());
		updatedArea.setDescription(area.getDescription());
		updatedArea.setExample(area.getExample());

		return areaRepository.save(updatedArea);
	}

	//TODO: LEMBRAR DE FAZER A DOCUMENTAÇÃO DA API? NÃO SEI SE PRECISA

	public void delete(Integer id) {		 

		find(id);
		try {
			areaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible delete an area that has catalogs linked to it.");
		}

	}

}
