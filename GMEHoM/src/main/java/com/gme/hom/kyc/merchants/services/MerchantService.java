package com.gme.hom.kyc.merchants.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.gme.hom.kyc.merchants.model.Merchant;

public interface MerchantService {
	
	//public int emailSignup(EmailSignup emailSignup);

	Merchant save(Merchant merchant) throws NoSuchAlgorithmException, IOException;

	MerchantDTO getById(long id);

	MerchantDTO getMerchantByEmailId(String email);
	
	Merchant update(Merchant merchant);

	public List<MerchantDTO> getAllMerchants();

	Page<Merchant> getAllMerchants(PageRequest pageRequest);

	boolean phoneNumberAlreadyRegistered(String phoneNumber);

	boolean emailAlreadyRegistered(String email);

}
