package br.com.yuri.cavalcante.tcc.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Softgoal implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Softgoal parent;
	
	@NotNull
	@OneToMany(mappedBy="parent")
	private Collection<Softgoal> softgoalList;
	
	private String name;
	private String description;

	@OneToOne
	@JoinColumn(name="catalog_id")
	@MapsId
	@JsonIgnore
	private Catalog catalog;

	private boolean priority;
	private Integer nfrType;
	private Integer contributionType;
	private Integer contributionTypeCatalog;
	private Integer evaluationProcedure;

	public Softgoal() {

	}

	public Softgoal(Integer id, Softgoal parent, String name, String description, boolean priority,
			Integer nfrType, Integer contributionType, Integer contributionTypeCatalog, Integer evaluationProcedure,
			Collection<Softgoal> softgoalList) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.nfrType = nfrType;
		this.contributionType = contributionType;
		this.contributionTypeCatalog = contributionTypeCatalog;
		this.evaluationProcedure = evaluationProcedure;
		this.softgoalList = softgoalList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Softgoal getParent() {
		return parent;
	}

	public void setParent(Softgoal parent) {
		this.parent = parent;
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

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public Integer getNfrType() {
		return nfrType;
	}

	public void setNfrType(Integer nfrType) {
		this.nfrType = nfrType;
	}

	public Integer getContributionType() {
		return contributionType;
	}

	public void setContributionType(Integer contributionType) {
		this.contributionType = contributionType;
	}

	public Integer getContributionTypeCatalog() {
		return contributionTypeCatalog;
	}

	public void setContributionTypeCatalog(Integer contributionTypeCatalog) {
		this.contributionTypeCatalog = contributionTypeCatalog;
	}

	public Integer getEvaluationProcedure() {
		return evaluationProcedure;
	}

	public void setEvaluationProcedure(Integer evaluationProcedure) {
		this.evaluationProcedure = evaluationProcedure;
	}

	public Collection<Softgoal> getSoftgoalList() {
		return softgoalList;
	}

	public void setSoftgoalList(Collection<Softgoal> softgoalList) {
		this.softgoalList = softgoalList;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
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
		Softgoal other = (Softgoal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
