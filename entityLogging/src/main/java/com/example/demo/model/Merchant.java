package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;




@Entity(name = "Merchant")
@Table(name = "merchants")
@NoArgsConstructor
public class Merchant extends SuperMerchant {

	public Merchant(MerchantRequest merchantRequest) {
		super(merchantRequest);
	}



}
    

    



	



	
	



	
	

	
	
	
	

