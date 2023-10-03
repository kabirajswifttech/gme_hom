package com.gme.hom.messaging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gme.hom.messaging.models.Message;

public interface MessagingRepository extends JpaRepository<Message, Long>{
	
	

}
