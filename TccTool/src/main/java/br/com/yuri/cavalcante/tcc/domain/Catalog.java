package br.com.yuri.cavalcante.tcc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Catalog implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String reference;
	private Integer year;
	private String keyWords;
	private String description;

	@ManyToOne
	@JoinColumn(name="owner_id")
	private User owner;

	@OneToOne(mappedBy = "catalog", cascade = CascadeType.ALL)
	private Softgoal softgoalMain;

	@OneToMany(mappedBy = "catalog")
	private List<Note> notesList = new ArrayList<Note>(); 


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="catalogs_authors",
	joinColumns = @JoinColumn(name = "catalog_id"),
	inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Person> authors = new ArrayList<Person>(); ;
	
	@ManyToMany
	@JoinTable(name="catalogs_area",
	joinColumns = @JoinColumn(name = "catalog_id"),
	inverseJoinColumns = @JoinColumn(name = "area_id"))
	private List<Area> areasList = new ArrayList<Area>(); 

	@ManyToMany
	@JoinTable(name="catalogs_applicationDomain",
	joinColumns = @JoinColumn(name = "catalog_id"),
	inverseJoinColumns = @JoinColumn(name = "aplicationDomain_id"))
	private List<ApplicationDomain> applicationDomainsList = new ArrayList<ApplicationDomain>();


	public Catalog() {

	}

	public Catalog(Integer id,  @NotNull List<Person> authors, String reference, Integer year,
			String keyWords, User owner, String description, @NotNull Softgoal softgoalMain) {
		super();
		this.id = id;
		this.authors = authors;
		this.reference = reference;
		this.year = year;
		this.keyWords = keyWords;
		this.owner = owner;
		this.description = description;
		this.softgoalMain = softgoalMain;
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
		return applicationDomainsList;
	}

	public void setApplicationDomainList(List<ApplicationDomain> applicationDomainList) {
		this.applicationDomainsList = applicationDomainList;
	}


	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
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


	public List<Note> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<Note> notesList) {
		this.notesList = notesList;
	}


	public List<Area> getAreasList() {
		return areasList;
	}

	public void setAreasList(List<Area> areasList) {
		this.areasList = areasList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catalog other = (Catalog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}




}
