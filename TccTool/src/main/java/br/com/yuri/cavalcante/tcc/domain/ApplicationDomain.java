package br.com.yuri.cavalcante.tcc.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class ApplicationDomain implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "You must type a name")
	@Size(min = 4, max = 40, message = "The name must be between 4 and 40 characters")
	private String name;
	private String description;
	private Set<String> exampleList;
		
	public ApplicationDomain() {

	}
	
	public ApplicationDomain(Integer id, String name, String description, Set<String> exampleList) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.exampleList = exampleList;
	}
	
	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<String> getExampleList() {
		return exampleList;
	}
	

	public void setExampleList(Set<String> exampleList) {
		this.exampleList = exampleList;
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
		ApplicationDomain other = (ApplicationDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
