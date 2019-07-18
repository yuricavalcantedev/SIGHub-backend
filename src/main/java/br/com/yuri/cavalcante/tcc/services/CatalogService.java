package br.com.yuri.cavalcante.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.yuri.cavalcante.tcc.controllers.exceptions.ObjectNotFoundException;
import br.com.yuri.cavalcante.tcc.domain.ApplicationDomain;
import br.com.yuri.cavalcante.tcc.domain.Area;
import br.com.yuri.cavalcante.tcc.domain.Catalog;
import br.com.yuri.cavalcante.tcc.domain.Person;
import br.com.yuri.cavalcante.tcc.domain.Softgoal;
import br.com.yuri.cavalcante.tcc.repositories.ApplicationDomainRepository;
import br.com.yuri.cavalcante.tcc.repositories.AreaRepository;
import br.com.yuri.cavalcante.tcc.repositories.CatalogRepository;
import br.com.yuri.cavalcante.tcc.repositories.PersonRepository;
import br.com.yuri.cavalcante.tcc.repositories.SoftgoalRepository;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ApplicationDomainRepository applicationDomainRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private SoftgoalRepository softgoalRepository;

	public Catalog insert(Catalog catalog) {

		catalog.setId(null);

		List<Person> authorsList = new ArrayList<>();
		Softgoal softgoalMain = catalog.getSoftgoalMain();

//		for (int i = 0; i <= catalog.getAuthors().size() - 1; i++)
	//		authorsList.add(personRepository.save(catalog.getAuthors().get(i)));

		for (int i = 0; i < catalog.getApplicationDomainList().size(); i++)
			applicationDomainRepository.save(catalog.getApplicationDomainList().get(i));

		for (int i = 0; i < catalog.getAreasList().size(); i++)
			areaRepository.save(catalog.getAreasList().get(i));

		catalog.setSoftgoalMain(null);
		catalog.setAuthors(null);

		catalog = catalogRepository.save(catalog);

		softgoalMain.setCatalog(catalog);
		softgoalMain = softgoalRepository.save(softgoalMain);

		catalog.setAuthors(authorsList);
		catalog.setSoftgoalMain(softgoalMain);

		return catalogRepository.save(catalog);

	}

	public List<Catalog> findAll() {

		return catalogRepository.findAll();
	}

	public Page<Catalog> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return catalogRepository.findAll(pageRequest);
	}

	public Catalog find(Integer id) {

		Optional<Catalog> catalog = catalogRepository.findById(id);
		return catalog.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: - " + id + "Type:" + Catalog.class.getName()));
	}

	public List<Catalog> findAllByDomainOrArea(String searchText) {

		List<Catalog> catalogs = new ArrayList<>();
		String searchWords[] = searchText.split("OR");

		for (int i = 0; i < searchWords.length; i++) {
			catalogs.addAll(findByDomainOrArea(searchWords[i].trim()));
		}

		return catalogs;
	}

	public List<Catalog> findByDomainOrArea(String searchText) {

 		List<Catalog> catalogsSearched = new ArrayList<Catalog>();
		List<Catalog> catalogsList = catalogRepository.findAll();
		Catalog catalog;
		for (int i = 0; i < catalogsList.size(); i++) {
			catalog = catalogsList.get(i);
			for (int j = 0; j < catalog.getApplicationDomainList().size(); j++) {
				ApplicationDomain applicationDomain = catalog.getApplicationDomainList().get(j);
				if (applicationDomain.getName().equalsIgnoreCase(searchText)) {
					catalogsSearched.add(catalog);
				}
			}
			for (int j = 0; j < catalog.getAreasList().size(); j++) {
				Area area = catalog.getAreasList().get(j);
				if (area.getName().equalsIgnoreCase(searchText)) {
					catalogsSearched.add(catalog);
				}
			}
		}

		return catalogsSearched;
	}

	public Catalog update(Catalog catalog) {

		Catalog updatedCatalog = find(catalog.getId());

		updatedCatalog.setYear(catalog.getYear());
		updatedCatalog.setReference(catalog.getReference());
		updatedCatalog.setApplicationDomainList(catalog.getApplicationDomainList());
		updatedCatalog.setDescription(catalog.getDescription());
		updatedCatalog.setSoftgoalMain(catalog.getSoftgoalMain());
		updatedCatalog.setNotesList(catalog.getNotesList());
		updatedCatalog.setAreasList(catalog.getAreasList());

		return catalogRepository.save(updatedCatalog);
	}

	public void delete(Integer id) {

		find(id); // if didn't work, it's gonna throw a exception already.
		catalogRepository.deleteById(id);
		// catalogRepository.deleteSoftGoalsByIdFather(id);

	}
	
}
