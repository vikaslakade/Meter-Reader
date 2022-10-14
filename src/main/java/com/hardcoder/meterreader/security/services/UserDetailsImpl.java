package com.hardcoder.meterreader.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.hardcoder.meterreader.models.RegisterEM;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDetailsImpl extends RegisterEM implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String EMSN;

	private String EMname;

	@JsonIgnore
	private String password;

	private String customername;



	public UserDetailsImpl(Long id, String username, String EMSN,String EMname, String password,
						   String customername) {
		this.id = id;
		this.username = username;
		this.EMSN = EMSN;
		this.EMname=EMname;
		this.password = password;
		this.customername = customername;
	}

	public static UserDetailsImpl build(RegisterEM registerEM) {


		return new UserDetailsImpl(
				registerEM.getId(),
				registerEM.getUsername(),
				registerEM.getEMSN(),
				registerEM.getEMname(),
				registerEM.getPassword(),
				registerEM.getCustomername());
	}



	public Long getId() {
		return id;
	}

	public String getEMSN() {
		return EMSN;
	}

	public String getCustomername() {
		return customername;
	}

	public String getEMname() {
		return EMname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
