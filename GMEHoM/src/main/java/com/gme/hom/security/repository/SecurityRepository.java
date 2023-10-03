package com.gme.hom.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.gme.hom.common.models.EntityHash;


public interface SecurityRepository extends JpaRepository<EntityHash, Long> {

	
	@Procedure(value = "public.check_duplicate")
	public List<EntityHash> checkDuplicateData(@Param("_entity") String entity, @Param("_hash") String hash);
}
