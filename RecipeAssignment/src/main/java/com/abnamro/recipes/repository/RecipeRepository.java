package com.abnamro.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abnamro.recipes.model.db.RecipeDb;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeDb, Long> {

}
