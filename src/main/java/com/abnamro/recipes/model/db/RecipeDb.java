package com.abnamro.recipes.model.db;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotations. Generates getters, setters and constructors.
@Data
@NoArgsConstructor
@AllArgsConstructor
// Enables this entity to be persisted by JPA.
@Entity
public class RecipeDb {
	
	Timestamp created;
	Boolean veg;
	Integer noOfServings;
	String ingredients;
	String instructions;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

}


