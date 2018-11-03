package br.com.yuri.cavalcante.tcc.domain.enums;

public enum ContributionType {

	NONE_CONTRIBUTION(0, "NONE_CONTRIBUTION"),
	AND_CONTRIBUTION(1,"AND_CONTRIBUTION"),
	OR_CONTRIBUTION(2,"OR_CONTRIBUTION");
	
	private Integer code;
	private String description;
	
	private ContributionType(Integer code, String description) {
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
	
	public static ContributionType toEnum(Integer code) {
		
		if(code == null)
			return null;
		for(ContributionType contributionType : ContributionType.values()) {
			if(contributionType.getCode() == code)
				return contributionType;
		}
		
		throw new IllegalArgumentException("Invalid code: " + code);
	}	
	
}
