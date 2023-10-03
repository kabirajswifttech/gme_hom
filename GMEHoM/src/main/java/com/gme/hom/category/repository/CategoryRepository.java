package com.gme.hom.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gme.hom.category.models.Category;
import com.gme.hom.category.models.CategoryDTO;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/*
	 * EntityManagerFactory entityManagerFactory =
	 * Persistence.createEntityManagerFactory("postgresql-42.5.4.jar");
	 * 
	 * EntityManager entityManager = entityManagerFactory.createEntityManager();
	 */


	@Query(value = "select c.category_type, c.sub_type, c.category_code, c.description, c.parent_type, c.parent_code, c.ref_col_1, c.ref_col_2, c.ref_col_3, c.ref_col_4, c.ref_col_5 from category_roots c WHERE c.is_active = TRUE", nativeQuery = true)
	List<CategoryDTO> getAllCategoryRoots();

	@Query(value = "SELECT cr.category_code, cr.description FROM category_roots cr " + "WHERE cr.category_type = ?1 "
			+ "AND cr.parent_type = ?2 " + "AND cr.parent_code = ?3 AND cr.is_active = TRUE ", nativeQuery = true)
	List<CategoryDTO> getCategoryByKeyword(String forOne, String by, String value);
	
	@Query(value = "SELECT cr.category_code, cr.description FROM category_roots cr " + "WHERE cr.category_type = ?1 "
			+ "AND cr.is_active = TRUE", nativeQuery = true)
	List<CategoryDTO> getCategoryByKeyword(String forOne);

	@Query(value = "SELECT cr.category_code, cr.description FROM category_roots cr " + "WHERE cr.category_type = ?1 "
			+ "AND cr.sub_type = ?2 " + "AND cr.parent_type = ?3 " + "AND cr.parent_code = ?4 "
			+ "AND cr.is_active = TRUE", nativeQuery = true)
	List<CategoryDTO> getCategoryBySubtype(String forOne, String subtype, String by, String value);

	/*
	 * public static List<String> getCategoryByKeyword(String forOne, String by,
	 * String value){
	 * 
	 * 
	 * String sql = "SELECT cr.category_code FROM category_roots cr " +
	 * "WHERE cr.category_type = :forone " + "AND cr.parent_type = :by " +
	 * "AND cr.parent_code = :value ";
	 * 
	 * jakarta.persistence.Query q = entityManager.createQuery(sql);
	 * q.setParameter("forone", forOne); q.setParameter("by", by);
	 * q.setParameter("value", value);
	 * 
	 * return DataTypeConverter.castList(String.class, q.getResultList());
	 * 
	 * }
	 */

	public static List<CategoryDTO> getCategoriesByKeyword(List<String> forMany, String by, String value) {
		return null;
	}
}
