package com.gme.hom.category.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gme.hom.api.config.APIRequestFunctionCode;
import com.gme.hom.api.config.APIRequestScopeCode;
import com.gme.hom.api.config.APIResponseCode;
import com.gme.hom.api.config.APIResponseScopeCode;
import com.gme.hom.api.models.APIRequest;
import com.gme.hom.api.models.APIRequestQueryParams;
import com.gme.hom.api.models.APIResponse;
import com.gme.hom.category.repository.CategoryDTO;
import com.gme.hom.category.services.CategoryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/common/category_roots")
public class CategoryController {

	private final CategoryService categoryService;

	CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("")
	public ResponseEntity<APIResponse> GetCategoryRoots(@RequestBody APIRequest apiRequest, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// public List<CategoryDTO> GetCategoryRoots(@RequestBody APIRequest apiRequest)
		// {

		APIResponse ar = new APIResponse();

		if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.ALL.toString())) {

			List<CategoryDTO> categories = categoryService.getAllCategoryRoots();

			ar.setStatus(APIResponseCode.SUCCESS);

			ar.setScope(APIResponseScopeCode.MULTIPLE);

			ar.setDataType("Category");

			ar.setData(categories);

			ar.setDescription("Categories list");

			return ResponseEntity.ok(ar);

		} else if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYKEYWORD.toString())) {

			ar.setDataType("Category");

			ar.setScope(APIResponseScopeCode.MULTIPLE);

			APIRequestQueryParams arqs = apiRequest.getData().getQuery();

			// iF by and value are empty, search for all data in a given category
			if (arqs.getBy() == null || arqs.getValue() == null) {

				List<CategoryDTO> result = categoryService.getCategoryByKeyword(arqs.getForOne());

				ar.setStatus(APIResponseCode.SUCCESS);
				
				ar.setData(result);
			}
			// if by and value are given, search for specific values only
			else {

				List<CategoryDTO> result = categoryService.getCategoryByKeyword(arqs.getForOne(), arqs.getBy(),
						arqs.getValue());

				
				ar.setStatus(APIResponseCode.SUCCESS);
				
				ar.setData(result);

			}

			ar.setDataType("Category");

			ar.setDescription("Categories list");

			return ResponseEntity.ok(ar);

		} else if (apiRequest.getFunction().equals(APIRequestFunctionCode.SEARCH.toString())
				&& apiRequest.getScope().equals(APIRequestScopeCode.BYSUBTYPE.toString())) {

			ar.setDataType("Category");

			ar.setScope(APIResponseScopeCode.MULTIPLE);

			APIRequestQueryParams arqs = apiRequest.getData().getQuery();

			List<CategoryDTO> result = categoryService.getCategoryBySubtype(arqs.getForOne(), arqs.getSubtype(),
					arqs.getBy(), arqs.getValue());
			
			ar.setStatus(APIResponseCode.SUCCESS);

			ar.setData(result);

			ar.setDataType("Category");

			ar.setDescription("Categories list");

			return ResponseEntity.ok(ar);

		} else {
			ar.setStatus(APIResponseCode.FAILURE);

			return ResponseEntity.ok(ar);
		}

	}

}
