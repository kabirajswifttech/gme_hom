package com.example.demo.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;




@Entity(name = "Merchant")
@Table(name = "merchants")
@NoArgsConstructor
public class Merchant extends SuperMerchant {

	public Merchant(MerchantRequest merchantRequest) {
		super(merchantRequest);
	}
	
	@PrePersist
    protected void onCreate() {
        setMerchantId(UUID.randomUUID());
    }


}
    

    



	



	
	



	
	

	
	
	
	


