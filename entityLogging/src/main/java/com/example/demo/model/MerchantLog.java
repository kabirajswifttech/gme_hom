package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity(name = "MerchantLog")
@Table(name = "merchants_log")
@NoArgsConstructor
public class MerchantLog extends SuperMerchant{
	


	public MerchantLog(Merchant merchant) {
		super(merchant);
	}
	
	
}









