package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MerchantLog;


public interface MerchantLogRepository extends JpaRepository<MerchantLog, Long>{

}
