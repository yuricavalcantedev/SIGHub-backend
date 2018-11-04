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
import br.com.yuri.cavalcante.tcc.domain.Softgoal;
import br.com.yuri.cavalcante.tcc.repositories.SoftgoalRepository;

@Service
public class SoftgoalService {

	@Autowired
	private SoftgoalRepository softgoalRepository;
	
public Softgoal insert(Softgoal softgoal) {
	
		return softgoalRepository.save(softgoal);
	}
	
	public List<Softgoal> findAll(){
		
		return softgoalRepository.findAll();		
	}
	
	public Page<Softgoal> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		return softgoalRepository.findAll(pageRequest);
	}
	
	public Softgoal find(Integer id){
		
		Optional<Softgoal> softgoal = softgoalRepository.findById(id); 
		return softgoal.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: - " + id + "Type:" + Softgoal.class.getName()));
	}
	
	public Softgoal update(Softgoal softgoal) {
		
		Softgoal updatedSoftgoal = find(softgoal.getId());
		
		updatedSoftgoal.setName(softgoal.getName());
		updatedSoftgoal.setParent(softgoal.getParent());
		updatedSoftgoal.setDescription(softgoal.getDescription());
		updatedSoftgoal.setPriority(softgoal.isPriority());
		updatedSoftgoal.setNfrType(softgoal.getNfrType());
		updatedSoftgoal.setContributionType(softgoal.getContributionType());
		updatedSoftgoal.setContributionTypeCatalog(softgoal.getContributionTypeCatalog());
		updatedSoftgoal.setEvaluationProcedure(softgoal.getEvaluationProcedure());
		updatedSoftgoal.setSoftgoalList(softgoal.getSoftgoalList());
		
		return softgoalRepository.save(updatedSoftgoal);
	}
		
	public void delete(Integer id) {		 
		
		find(id);
		softgoalRepository.deleteById(id);
				
	}
}
