package com.api.springcrudbook.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String firstName;
	private String lastName;
	
	
	@OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonBackReference
    private Book book;
	
	
}
	

