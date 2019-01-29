package com.pawlinski.recipeproject.repositories;

import com.pawlinski.recipeproject.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
