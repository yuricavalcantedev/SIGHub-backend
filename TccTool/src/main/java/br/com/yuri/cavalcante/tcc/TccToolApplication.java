package br.com.yuri.cavalcante.tcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.yuri.cavalcante.tcc.domain.ApplicationDomain;
import br.com.yuri.cavalcante.tcc.domain.Area;
import br.com.yuri.cavalcante.tcc.domain.Catalog;
import br.com.yuri.cavalcante.tcc.domain.Note;
import br.com.yuri.cavalcante.tcc.domain.Person;
import br.com.yuri.cavalcante.tcc.domain.Softgoal;
import br.com.yuri.cavalcante.tcc.domain.User;
import br.com.yuri.cavalcante.tcc.repositories.ApplicationDomainRepository;
import br.com.yuri.cavalcante.tcc.repositories.AreaRepository;
import br.com.yuri.cavalcante.tcc.repositories.CatalogRepository;
import br.com.yuri.cavalcante.tcc.repositories.NoteRepository;
import br.com.yuri.cavalcante.tcc.repositories.PersonRepository;
import br.com.yuri.cavalcante.tcc.repositories.SoftgoalRepository;
import br.com.yuri.cavalcante.tcc.repositories.UserRepository;

@SpringBootApplication
public class TccToolApplication implements CommandLineRunner{

	@Autowired
	private ApplicationDomainRepository applicationDomainRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SoftgoalRepository softgoalRepository;
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TccToolApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
	
		ApplicationDomain appDomain = new ApplicationDomain();
		appDomain.setDescription("Descrição 1");
		appDomain.setName("Name 1");
		appDomain.setExample("Example 1, Example 2, Example 3");
		
		ApplicationDomain appDomain2 = new ApplicationDomain();
		appDomain2.setDescription("Descrição 2");
		appDomain2.setName("Name 2");
		appDomain2.setExample("Example 1, Example 2, Example 3");
		
		
		applicationDomainRepository.saveAll(Arrays.asList(appDomain, appDomain2));
		
		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Name 1");
		area.setExample("Example 1, Example 2, Example 3");
		
		Area area2 = new Area();
		area2.setDescription("Descrição 2");
		area2.setName("Name 2");
		area2.setExample("Example 1, Example 2, Example 3");
		
		areaRepository.saveAll(Arrays.asList(area,area2));
		
		Note note = new Note();
		note.setText("Note 1");
		note.setCatalog(null);
		
		Note note2 = new Note();
		note2.setText("Note 2");
		note2.setCatalog(null);
		
		Note note3 = new Note();
		note3.setText("Note 3");
		note3.setCatalog(null);
		
		Note note4 = new Note();
		note4.setText("Note 4");
		note4.setCatalog(null);
		
		noteRepository.saveAll(Arrays.asList(note,note2,note3,note4));
		
		Person person = new Person();
		person.setName("person1");
		person.setCatalogList(null);
		person.setEmail("email1@gmail.com");
		
		Person person2 = new Person();
		person2.setName("person2");
		person.setCatalogList(null);
		person2.setEmail("email2@gmail.com");
		
		Person person3 = new Person();
		person3.setName("person3");
		person.setCatalogList(null);
		person3.setEmail("email13@gmail.com");
		
		personRepository.saveAll(Arrays.asList(person,person2,person3));
		
		User user = new User("senha1");
		user.setName("User 1");
		user.setCatalogList(null);
		user.setEmail("yuricavalcante@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);
		
		Catalog catalog = new Catalog();
		catalog.setReference("Reference");
		catalog.setYear(2018);
		catalog.setDescription("Description here");
		catalog.setKeyWords("KeyWord1, KeyWord2 , KeyWord3");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain, appDomain2));
		catalog.getAreasList().addAll(Arrays.asList(area,area2));
		catalog.getNotesList().addAll(Arrays.asList(note,note2,note3,note4));
		catalog.setOwner(user);
	
		
		catalog = catalogRepository.save(catalog);
		
		note.setCatalog(catalog);
		note2.setCatalog(catalog);
		note3.setCatalog(catalog);
		note4.setCatalog(catalog);

		noteRepository.saveAll(Arrays.asList(note,note2,note3,note4));
		
		catalog.getAuthors().addAll(Arrays.asList(person, person2, person3));
		
		Softgoal softgoal = new Softgoal(null,"SOFTGOAL 1", "DESCRIÇÃO 1", true, 1,2,1,1);		
		ArrayList<Softgoal> listSF = new ArrayList<Softgoal>();
		
		Softgoal softgoal2 = new Softgoal();
		softgoal2.setName("CATALOG 2");
		
		softgoal2.setSoftgoalList(Arrays.asList(new Softgoal(),new Softgoal(),new Softgoal()));
		
		listSF.add(softgoal2);
		listSF.add(new Softgoal());
		listSF.add(new Softgoal());
		softgoal.setSoftgoalList(listSF);
		softgoal.setId(catalog.getId());
		
		softgoal.setCatalog(catalog);
		catalog.setSoftgoalMain(softgoal);
		
		softgoalRepository.saveAll(Arrays.asList(softgoal, softgoal2));
		catalogRepository.save(catalog);

	}
	
}
