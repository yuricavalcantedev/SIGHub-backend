package br.com.yuri.cavalcante.tcc.domain;

import javax.persistence.Entity;

@Entity
public class User extends Person{
	
	private String password;
	
	public User(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
