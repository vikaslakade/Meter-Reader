package com.hardcoder.meterreader.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "register_em ",
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "EMSN")
		})
public class RegisterEM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;


	@NotBlank
	@Size(max = 20)
	private String EMname;

	@NotBlank
	@Size(max = 50)
	private String EMSN;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 50)
	private String customername;


	


	public RegisterEM() {
	}

	public RegisterEM( String username,String EMname, String EMSN, String password, String customername) {

		this.username = username;
		this.EMname=EMname;
		this.EMSN = EMSN;
		this.password = password;
		this.customername = customername;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEMSN() {
		return EMSN;
	}

	public void setEMSN(String EMSN) {
		this.EMSN = EMSN;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getEMname() {
		return EMname;
	}

	public void setEMname(String EMname) {
		this.EMname = EMname;
	}
}
