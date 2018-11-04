package br.com.yuri.cavalcante.tcc.domain.enums;

public enum EvaluationProcedure {

	NONE_CONTRIBUTION(0, "NONE_EVALUATION"),
	DENIED_EVALUATION(1,"DENIED_EVALUATION"),
	WEAKLY_DENIED_EVALUATION(2,"WEAKLY_DENIED_EVALUATION"),
	UNDECIDED_EVALUATION(3,"UNDECIDED_EVALUATION"),
	WEAKLY_SATISFICED_EVALUATION(4,"WEAKLY_SATISFICED_EVALUATION"),
	SATISFICED_EVALUATION(5,"SATISFICED_EVALUATION"),
	CONFLICT_EVALUATION(6,"CONFLICT_EVALUATION");
	
	private Integer code;
	private String description;
	
	private EvaluationProcedure(Integer code, String description) {
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
	
	public static EvaluationProcedure toEnum(Integer code) {
		
		if(code == null)
			return null;
		for(EvaluationProcedure evaluationProcedure : EvaluationProcedure.values()) {
			if(evaluationProcedure .getCode() == code)
				return evaluationProcedure;
		}
		
		throw new IllegalArgumentException("Invalid code: " + code);
	}	
	
	
}
