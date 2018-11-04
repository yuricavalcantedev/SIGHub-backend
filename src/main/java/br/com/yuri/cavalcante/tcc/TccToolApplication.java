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
import br.com.yuri.cavalcante.tcc.services.CatalogService;

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
		appDomain.setName("Banking");
		appDomain.setExample("Example 1, Example 2, Example 3");
		
		
		applicationDomainRepository.saveAll(Arrays.asList(appDomain));
		
		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Internet of Things");
		area.setExample("Example 1, Example 2, Example 3");
		
		Area area2 = new Area();
		area2.setDescription("Descrição 2");
		area2.setName("Ubiquos System");
		area2.setExample("Example 1, Example 2, Example 3");
		
		
		areaRepository.saveAll(Arrays.asList(area, area2));
		
		Note note = new Note();
		note.setText("NFR Softgoal");
		note.setCatalog(null);
		
		Note note2 = new Note();
		note2.setText("Sub NFR Softgoals");
		note2.setCatalog(null);
		
		Note note3 = new Note();
		note3.setText("HELP Contribution");
		note3.setCatalog(null);
		
		Note note4 = new Note();
		note4.setText("General Operationalizing");
		note4.setCatalog(null);
		
		Note note5 = new Note();
		note5.setText("AND Contribution");
		note5.setCatalog(null);
		
		Note note6 = new Note();
		note6.setText("OR Contribution");
		note6.setCatalog(null);
		
		Note note7 = new Note();
		note7.setText("Specific Operationalizing Softgoals");
		note7.setCatalog(null);
		
		noteRepository.saveAll(Arrays.asList(note,note2,note3,note4,note5,note6,note7));
		
		Person person = new Person();
		person.setName("Rainara Maia Carvalho");
		person.setCatalogList(null);
		person.setEmail("rainaramaia4@gmail.com");
		
		
		personRepository.saveAll(Arrays.asList(person));
		
		User user = new User();
		user.setName("Yuri");
		user.setCatalogList(null);
		user.setEmail("yuricavalcante@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);
		
		Catalog catalog = new Catalog();
		catalog.setReference("Reference");
		catalog.setYear(2018);
		catalog.setDescription("This is a catalog of Ubiquos System developed to be showed on a HIC Conference");
		catalog.setKeyWords("Ubiquos System, IoT, Security, ");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain));
		catalog.getAreasList().addAll(Arrays.asList(area));
		catalog.getNotesList().addAll(Arrays.asList(note,note2,note3,note4,note5,note6,note7));
		catalog.setOwner(user);
	
		
		catalog = catalogRepository.save(catalog);
		
		catalog.getAuthors().addAll(Arrays.asList(person, user));
		
		//type, and or, break hurt help, denied weakly denied satisficied conflict
		
		//LEVEL 0
		Softgoal SFSecurity = new Softgoal(null, "Security", "Security is ...", false, 1,0,0,0);
		SFSecurity.setId(catalog.getId());
		SFSecurity.setCatalog(catalog);
		
		//LEVEL 1
		Softgoal SFIntegrity = new Softgoal(null, "Integrity", "Integrity is ...", false,1,1,0,0);
		Softgoal SFConfidentiality = new Softgoal(null, "Confidentiality", "Confidentiality is ...", false, 1,1,0,0);
		Softgoal SFAvailability = new Softgoal(null, "Availability", "Availability is ...", false, 1,1,0,0);
		
		SFIntegrity.setCatalog(catalog);
		SFConfidentiality.setCatalog(catalog);
		SFAvailability.setCatalog(catalog);

		//LEVEL 2
		Softgoal SFAuthorizeAccess = new Softgoal(null, "Authorize access", "Authorize access is ...", false, 2,4,0,0);				
		
		SFAuthorizeAccess.setCatalog(catalog);
		
		//LEVEL 3
		Softgoal SFValidateAcces = new Softgoal(null, "Validate access agains eligibility rules", "Validate access agains eligibility rules is ...", false, 2,1,0,0);
		Softgoal SFIdentifyUsers = new Softgoal(null, "Identify users", "Identify users is ...", false, 2,1,0,0);
		Softgoal SFAuthenticateUserAccess = new Softgoal(null, "Authenticate user access", "Authenticate user access is ...", false, 2,1,0,0);
				
		SFValidateAcces.setCatalog(catalog);
		SFIdentifyUsers.setCatalog(catalog);
		SFAuthenticateUserAccess.setCatalog(catalog);
		
		//level 4
		Softgoal SFUsePIN = new Softgoal(null, "Use PIN","Use PIN is ...", false, 2,2,0,0);
		Softgoal SFCompareSignature = new Softgoal(null, "Compare signature", "Compare signature is ...", false, 2,2,0,0);
		Softgoal SFRequireAdditionalID = new Softgoal(null, "Require additional ID", "Require additional ID is ...", false, 2,2,0,0);
		
		SFUsePIN.setCatalog(catalog);
		SFCompareSignature.setCatalog(catalog);
		SFRequireAdditionalID.setCatalog(catalog);
		
		SFSecurity.getSoftgoalList().addAll(Arrays.asList(SFIntegrity, SFConfidentiality, SFAvailability));
		SFConfidentiality.getSoftgoalList().addAll(Arrays.asList(SFAuthorizeAccess));
		SFAuthorizeAccess.getSoftgoalList().addAll(Arrays.asList(SFValidateAcces, SFIdentifyUsers, SFAuthenticateUserAccess));
		SFAuthenticateUserAccess.getSoftgoalList().addAll(Arrays.asList(SFUsePIN, SFCompareSignature, SFRequireAdditionalID));
		
		
		
		
		softgoalRepository.saveAll(Arrays.asList(SFSecurity, SFIntegrity, SFConfidentiality, SFAvailability, SFAuthorizeAccess, SFValidateAcces,
												 SFIdentifyUsers, SFAuthenticateUserAccess, SFUsePIN, SFCompareSignature, SFRequireAdditionalID));
		catalog.setSoftgoalMain(SFSecurity);
		
		catalogRepository.save(catalog);
	
	}
	
	private void auxMethod() {
		
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
