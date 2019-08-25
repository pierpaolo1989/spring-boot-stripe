package com.soa.stripe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User implements Serializable{
	
	private static final long serialVersionUID = -5236608329002390774L;

	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String name;
	

}
