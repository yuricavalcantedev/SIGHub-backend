package br.com.yuri.cavalcante.tcc.domain.enums;

public enum NFRType {

	NFR_SOFTGOAL(1,"NFR_SOFTGOAL"),
	OPERATIONALIZATION_SOFTGOAL(2,"OPERATIONALIZATION_SOFTGOAL"),
	CLAIM_SOFTGOAL(3,"CLAIM_SOFTGOAL");
	
	private Integer code;
	private String description;
	
	private NFRType(Integer code, String description) {
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
	
	public static NFRType toEnum(Integer code) {
		
		if(code == null)
			return null;
		for(NFRType nfrType : NFRType.values()) {
			if(nfrType.getCode() == code)
				return nfrType;
		}
		
		throw new IllegalArgumentException("Invalid code: " + code);
	}	
}
