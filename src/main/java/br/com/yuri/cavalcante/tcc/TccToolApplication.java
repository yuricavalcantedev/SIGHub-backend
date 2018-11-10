package br.com.yuri.cavalcante.tcc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

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
public class TccToolApplication extends SpringBootServletInitializer{

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
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TccToolApplication.class);
	}
	
	/*
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
	
	}*/
}
