package br.com.yuri.cavalcante.tcc.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Catalog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private List<Person> authors;
	private String reference;
	private Integer year;
	
	@NotNull
	private List<ApplicationDomain> applicationDomainList;
	
	private Set<String> keyWords;
	private User owner;
	private String description;
	
	@NotNull
	private Softgoal softgoalMain;
	private List<String> notesList;
	
	@NotNull
	private List<String> areasList;

	public Catalog() {
		
	}
	
	public Catalog(Integer id,  @NotNull List<Person> authors, String reference, Integer year,
			@NotNull List<ApplicationDomain> applicationDomainList, Set<String> keyWords, User owner,
			String description, @NotNull Softgoal softgoalMain, List<String> notesList,
			@NotNull List<String> areasList) {
		super();
		this.id = id;
		this.authors = authors;
		this.reference = reference;
		this.year = year;
		this.applicationDomainList = applicationDomainList;
		this.keyWords = keyWords;
		this.owner = owner;
		this.description = description;
		this.softgoalMain = softgoalMain;
		this.notesList = notesList;
		this.areasList = areasList;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	
	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> authors) {
		this.authors = authors;
	}

	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	
	public List<ApplicationDomain> getApplicationDomainList() {
		return applicationDomainList;
	}

	public void setApplicationDomainList(List<ApplicationDomain> applicationDomainList) {
		this.applicationDomainList = applicationDomainList;
	}

	
	public Set<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(Set<String> keyWords) {
		this.keyWords = keyWords;
	}

	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Softgoal getSoftgoalMain() {
		return softgoalMain;
	}

	public void setSoftgoalMain(Softgoal softgoalMain) {
		this.softgoalMain = softgoalMain;
	}

	
	public List<String> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<String> notesList) {
		this.notesList = notesList;
	}

	
	public List<String> getAreasList() {
		return areasList;
	}

	public void setAreasList(List<String> areasList) {
		this.areasList = areasList;
	}
		

}