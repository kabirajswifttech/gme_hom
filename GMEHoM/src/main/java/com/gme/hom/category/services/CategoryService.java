package com.gme.hom.category.services;

import java.util.List;

import com.gme.hom.category.repository.CategoryDTO;

public interface CategoryService {	
	
	// public List<CategoryDTO> GetCategoryRoots();
	
	public List<CategoryDTO> getAllCategoryRoots();
	
	public List<CategoryDTO> getCategoryByKeyword(String forOne, String by, String value);
	
	public List<CategoryDTO> getCategoryByKeyword(String forOne);
	
	public List<CategoryDTO> getCategoryBySubtype(String forOne, String subType, String by, String value);
	
	public List<CategoryDTO> getCategoriesByKeyword(List<String> forMany, String by, String value);

}
