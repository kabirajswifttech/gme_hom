package com.gme.hom.category.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gme.hom.category.models.CategoryDTO;
import com.gme.hom.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<CategoryDTO> getAllCategoryRoots() {
		return (List<CategoryDTO>) categoryRepository.getAllCategoryRoots();
	}

	public List<CategoryDTO> getCategoryByKeyword(String forOne, String by, String value) {
		return categoryRepository.getCategoryByKeyword(forOne, by, value);
	}

	public List<CategoryDTO> getCategoryBySubtype(String forOne, String subtype, String by, String value) {
		return categoryRepository.getCategoryBySubtype(forOne, subtype, by, value);
	}

	public List<CategoryDTO> getCategoryByKeyword(String forOne) {
		return categoryRepository.getCategoryByKeyword(forOne);
	}

	public List<CategoryDTO> getCategoriesByKeyword(List<String> forMany, String by, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
