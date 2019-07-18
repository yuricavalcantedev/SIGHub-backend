package br.com.yuri.cavalcante.tcc;

import java.util.Arrays;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class TccToolApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	private ApplicationDomainRepository applicationDomainRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SoftgoalRepository softgoalRepository;

	@Autowired
	private CatalogRepository catalogRepository;

	@Override 
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TccToolApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TccToolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		addCatalogUsability();
		addCatalogIoT();
		addCatalogIntegrity();
		addCatalogPerformance();

	}
	
	private void addCatalogUsability() {
		

		ApplicationDomain appDomain = new ApplicationDomain();
		appDomain.setDescription("Descrição 1");
		appDomain.setName("Usability");
		appDomain.setExample("Example 1, Example 2, Example 3");


		applicationDomainRepository.saveAll(Arrays.asList(appDomain));

		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Web");
		area.setExample("Example 1, Example 2, Example 3");

		areaRepository.saveAll(Arrays.asList(area));
		Person person = new Person();
		person.setName("Rainara Maia Carvalho");
		person.setCatalogList(null);
		person.setEmail("rainaramaia4@gmail.com");


		personRepository.saveAll(Arrays.asList(person));

		User user = new User();
		user.setName("Joao");
		user.setCatalogList(null);
		user.setEmail("joao@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);

		Catalog catalog = new Catalog();
		catalog.setReference("CHUNG, L.; NIXON");
		catalog.setYear(2018);
		catalog.setDescription("This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conference");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain));
		catalog.getAreasList().addAll(Arrays.asList(area));
		catalog.setOwner(user);


		catalog = catalogRepository.save(catalog);

		catalog.getAuthors().addAll(Arrays.asList(person, user));


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

	private void addCatalogSecurity() {
		

		ApplicationDomain appDomain = new ApplicationDomain();
		appDomain.setDescription("Descrição 1");
		appDomain.setName("Security");
		appDomain.setExample("Example 1, Example 2, Example 3");


		applicationDomainRepository.saveAll(Arrays.asList(appDomain));

		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Web");
		area.setExample("Example 1, Example 2, Example 3");

		areaRepository.saveAll(Arrays.asList(area));
		Person person = new Person();
		person.setName("Rainara Maia Carvalho");
		person.setCatalogList(null);
		person.setEmail("rainaramaia4@gmail.com");


		personRepository.saveAll(Arrays.asList(person));

		User user = new User();
		user.setName("Joao");
		user.setCatalogList(null);
		user.setEmail("joao@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);

		Catalog catalog = new Catalog();
		catalog.setReference("WIEGERS K. ; BEATTY");
		catalog.setYear(2018);
		catalog.setDescription("This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conference");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain));
		catalog.getAreasList().addAll(Arrays.asList(area));
		catalog.setOwner(user);


		catalog = catalogRepository.save(catalog);

		catalog.getAuthors().addAll(Arrays.asList(person, user));


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
	
	private void addCatalogIoT() {
		

		ApplicationDomain appDomain = new ApplicationDomain();
		appDomain.setDescription("Descrição 1");
		appDomain.setName("Internet of Things");
		appDomain.setExample("Example 1, Example 2, Example 3");


		applicationDomainRepository.saveAll(Arrays.asList(appDomain));

		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Mobile");
		area.setExample("Example 1, Example 2, Example 3");

		areaRepository.saveAll(Arrays.asList(area));
		Person person = new Person();
		person.setName("Rainara Maia Carvalho");
		person.setCatalogList(null);
		person.setEmail("rainaramaia4@gmail.com");


		personRepository.saveAll(Arrays.asList(person));

		User user = new User();
		user.setName("Joao");
		user.setCatalogList(null);
		user.setEmail("joao@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);

		Catalog catalog = new Catalog();
		catalog.setReference("CHUNG, L.; NIXON, B. A.;");
		catalog.setYear(2018);
		catalog.setDescription("This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conference");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain));
		catalog.getAreasList().addAll(Arrays.asList(area));
		catalog.setOwner(user);


		catalog = catalogRepository.save(catalog);

		catalog.getAuthors().addAll(Arrays.asList(person, user));


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

	private void addCatalogIntegrity() {
		

		ApplicationDomain appDomain = new ApplicationDomain();
		appDomain.setDescription("Descrição 1");
		appDomain.setName("Integrity");
		appDomain.setExample("Example 1, Example 2, Example 3");


		applicationDomainRepository.saveAll(Arrays.asList(appDomain));

		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Desktop");
		area.setExample("Example 1, Example 2, Example 3");

		areaRepository.saveAll(Arrays.asList(area));
		Person person = new Person();
		person.setName("Rainara Maia Carvalho");
		person.setCatalogList(null);
		person.setEmail("rainaramaia4@gmail.com");


		personRepository.saveAll(Arrays.asList(person));

		User user = new User();
		user.setName("Joao");
		user.setCatalogList(null);
		user.setEmail("joao@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);

		Catalog catalog = new Catalog();
		catalog.setReference("CARVALHO, R. M.; ANDRADE, R. M. C.; OLIVEIRA, K. M.; KOLSKI, C.");
		catalog.setYear(2018);
		catalog.setDescription("This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC ConferenceThis is a catalog of Ubiquos System developed to be showed on a HIC Conference");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain));
		catalog.getAreasList().addAll(Arrays.asList(area));
		catalog.setOwner(user);


		catalog = catalogRepository.save(catalog);

		catalog.getAuthors().addAll(Arrays.asList(person, user));


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

	private void addCatalogPerformance() {
		

		ApplicationDomain appDomain = new ApplicationDomain();
		appDomain.setDescription("Descrição 1");
		appDomain.setName("Performance");
		appDomain.setExample("Example 1, Example 2, Example 3");


		applicationDomainRepository.saveAll(Arrays.asList(appDomain));

		Area area = new Area();
		area.setDescription("Descrição 1");
		area.setName("Web");
		area.setExample("Example 1, Example 2, Example 3");

		areaRepository.saveAll(Arrays.asList(area));
		Person person = new Person();
		person.setName("Rainara Maia Carvalho");
		person.setCatalogList(null);
		person.setEmail("rainaramaia4@gmail.com");


		personRepository.saveAll(Arrays.asList(person));

		User user = new User();
		user.setName("Joao");
		user.setCatalogList(null);
		user.setEmail("joao@gmail.com");
		user.setPassword("1234");
		userRepository.save(user);

		Catalog catalog = new Catalog();
		catalog.setReference("BERANDER, P.; DAMM, L.-O.");
		catalog.setYear(2018);
		catalog.setDescription("This is a catalog of Ubiquos System developed to be showed on a HIC Conference This is a catalog of Ubiquos System developed to be showed on a HIC Conferencev");
		catalog.getApplicationDomainList().addAll(Arrays.asList(appDomain));
		catalog.getAreasList().addAll(Arrays.asList(area));
		catalog.setOwner(user);


		catalog = catalogRepository.save(catalog);

		catalog.getAuthors().addAll(Arrays.asList(person, user));


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




}