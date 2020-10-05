package com.abnamro.recipes.model.api;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeApi {
	
	String created;
	Boolean veg;
	Integer noOfServings;
	List<String> ingredients;
	List<String> instructions;
	
	Long id;
}


