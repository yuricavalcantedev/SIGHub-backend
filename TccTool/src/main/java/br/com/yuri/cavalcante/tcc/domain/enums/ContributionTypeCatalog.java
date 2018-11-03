package br.com.yuri.cavalcante.tcc.domain.enums;

public enum ContributionTypeCatalog {

	NONE_CONTRIBUTION(0, "NONE_CONTRIBUTION"),
	BREAK_CONTRIBUTION(1,"BREAK_CONTRIBUTION"),
	HURT_CONTRIBUTION(2,"HURT_CONTRIBUTION"),
	UNKNOWN_CONTRIBUTION(3,"UNKNOWN_CONTRIBUTION"),
	HELP_CONTRIBUTION(4,"HELP_CONTRIBUTION"),
	SOME_MINUS_CONTRIBUTION(5,"SOME_MINUS_CONTRIBUTION"),
	SOME_PLUS_CONTRIBUTION(6,"SOME_PLUS_CONTRIBUTION");
	
	private Integer code;
	private String description;
	
	private ContributionTypeCatalog(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static ContributionTypeCatalog toEnum(Integer code) {
		
		if(code == null)
			return null;
		for(ContributionTypeCatalog contributionTypeCatalog : ContributionTypeCatalog.values()) {
			if(contributionTypeCatalog.getCode() == code)
				return contributionTypeCatalog;
		}
		
		throw new IllegalArgumentException("Invalid code: " + code);
	}	
	
	
}
