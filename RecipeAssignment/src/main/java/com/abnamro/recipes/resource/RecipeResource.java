package com.abnamro.recipes.resource;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.recipes.model.api.RecipeApi;
import com.abnamro.recipes.model.db.RecipeDb;
import com.abnamro.recipes.repository.RecipeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("recipes")
// Generate a constructor having annotation @Autowired.
@AllArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class RecipeResource {

	private final RecipeRepository recipeRepository;

	@GetMapping
	public List<RecipeApi> findAllRecipes() {

		List<RecipeDb> allRecipesFromDb = recipeRepository.findAll();

		List<RecipeApi> allRecipesInResponse = new ArrayList<>();
		for (RecipeDb dbRecipe : allRecipesFromDb) {
			allRecipesInResponse.add(mapDbToApi(dbRecipe));
		}

		return allRecipesInResponse;
	}

	@GetMapping("{id}")
	public RecipeApi findOneRecipe(@PathVariable long id) {
		// Find from DB and return the DB model.
		RecipeDb dbRecipe = recipeRepository.findById(id).orElse(null);

		// If exists, map to API model and return, else return null (empty JSON {}).
		if (dbRecipe != null) {
			return mapDbToApi(dbRecipe);
		} else {
			return null;
		}
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOneRecipe(@PathVariable long id) {
		recipeRepository.deleteById(id);
	}

	@PostMapping
	public RecipeApi createRecipe(@RequestBody RecipeApi recipeToBeCreated) {

		RecipeDb dbRecipeToBeSaved = new RecipeDb() ;
		try {
			dbRecipeToBeSaved = mapApiToDb(recipeToBeCreated);
		} catch (ParseException e) {
			log.error("Unabel to parse");
		}

		RecipeDb savedRecipe = recipeRepository.save(dbRecipeToBeSaved);

		return mapDbToApi(savedRecipe);
	}

	private RecipeApi mapDbToApi(RecipeDb dbRecipe) {
		String[] ingredientsSplit = dbRecipe.getIngredients().split(",");
		List<String> ingredientsList = Arrays.asList(ingredientsSplit);

		String[] instructionsSplit = dbRecipe.getInstructions().split(",");
		List<String> instructionsList = Arrays.asList(instructionsSplit);

		String createdTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dbRecipe.getCreated());
		
		return new RecipeApi(createdTimeStamp, dbRecipe.getVeg(), dbRecipe.getNoOfServings(),
				ingredientsList, instructionsList, dbRecipe.getId());
	}

	private RecipeDb mapApiToDb(RecipeApi apiRecipe) throws ParseException {

		RecipeDb dbRecipe = new RecipeDb();

		List<String> ingredientsList = apiRecipe.getIngredients();
		String ingredientsJoined = String.join(",", ingredientsList);
		dbRecipe.setIngredients(ingredientsJoined);

		List<String> instructionsList = apiRecipe.getInstructions();
		String instructionsJoined = String.join(",", instructionsList);
		dbRecipe.setInstructions(instructionsJoined);

		Date createdTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(apiRecipe.getCreated());
		
		dbRecipe.setCreated(new Timestamp(createdTimeStamp.getTime()));
		dbRecipe.setNoOfServings(apiRecipe.getNoOfServings());
		dbRecipe.setVeg(apiRecipe.getVeg());
		dbRecipe.setId(apiRecipe.getId());

//		RecipeDb dbRecipe = new RecipeDb(apiRecipe.getCreated(), apiRecipe.getVeg(), apiRecipe.getNoOfServings(),
//				ingredientsJoined, instructionsJoined, apiRecipe.getId());

		return dbRecipe;
	}
}
