package com.gme.hom.kyc.bulk;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gme.hom.api.models.APIData;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetails;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.kyc.bankDetails.services.MerchantsBankDetailsService;
import com.gme.hom.kyc.config.MerchantStatusCodes;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetails;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.kyc.directors.services.MerchantsDirectorsDetailsService;
import com.gme.hom.kyc.merchants.model.Merchant;
import com.gme.hom.kyc.merchants.model.MerchantRequest;
import com.gme.hom.kyc.merchants.services.MerchantDTO;
import com.gme.hom.kyc.merchants.services.MerchantService;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreference;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreferenceRequest;
import com.gme.hom.kyc.preferredServices.services.MerchantsServicePreferenceService;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetails;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetailsRequest;
import com.gme.hom.kyc.representatives.services.MerchantsRepresentativeDetailsService;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetails;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetailsRequest;
import com.gme.hom.kyc.stockholders.services.MerchantsStockholdersDetailsService;
import com.gme.hom.users.models.UserRequest;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MerchantOnboardingServiceImpl implements MerchantOnboardingService {
	private MerchantService merchantService;
	private MerchantsBankDetailsService bankDetailsService;
	private MerchantsDirectorsDetailsService directorsService;
	private MerchantsRepresentativeDetailsService representativeService;
	private MerchantsStockholdersDetailsService stockholdersService;
	private MerchantsServicePreferenceService servicePreferenceService;

	@Override
	@Transactional
	public String onBoard(APIData data, Long id) throws Exception {
		// Unpacking data from APIRequest object
		UserRequest userReq = data.getUserRequest();
		MerchantRequest merchantReq = data.getMerchantRequest();
		MerchantsBankDetailsRequest bankDetailsReq = data.getMerchantsBankDetailsRequest();
		List<MerchantsDirectorsDetailsRequest> directorsReqs = data.getMerchantsDirectorsDetailsRequests();
		List<MerchantsRepresentativeDetailsRequest> representativesReqs = data
				.getMerchantsRepresentativeDetailsRequests();
		List<MerchantsStockholdersDetailsRequest> stockholdersReqs = data.getMerchantsStockholdersDetailsRequests();
		List<MerchantsServicePreferenceRequest> makePaymentServicePrefReq = data.getMakePaymentInfoRequests();
		List<MerchantsServicePreferenceRequest> getPaymentServicePrefReq = data.getGetPaymentInfoRequests();
		// update merchant details
		MerchantDTO m = merchantService.getById(id);

		Merchant merchant = new Merchant(merchantReq);

		// check if merchant matches the registration started earlier
		if (m.getEmail_id().equals(merchant.getEmailId())) {
			merchant.setId(id);
			merchant.setStatus(MerchantStatusCodes.PENDING.toString());
			merchant.setActive(true);
			merchantService.update(merchant);
		} else {
			throw new Exception("Merchant email did not match!");
		}

		// update user details
//			Optional<User> u = userService.getUserByMerchantId(id);
//			if(!u.isEmpty()) {
//				User user = new User(userReq);
//				//check if user matches the registration started earlier
//				if(u.get().getEmailId().equals(userReq.getEmailId())) {
//					user.setId(u.get().getId());
//					user.setStatus(MerchantStatusCodes.PENDING.toString());
//					user.setActive(true);
//					user.setMerchantId(m.get().getId());
//					userService.update(user);
//				}else {
//					throw new Exception("User email did not match!");
//				}
//			}else {
//				throw new Exception("No such user found!");
//			}

		// insert merchant bank details request
		bankDetailsReq.setMerchantId(id);
		bankDetailsService.save(new MerchantsBankDetails(bankDetailsReq));
		// insert List of merchantDirectorsDetails
		for (MerchantsDirectorsDetailsRequest directorsReq : directorsReqs) {
			directorsReq.setMerchantId(id);
			directorsService.save(new MerchantsDirectorsDetails(directorsReq));
		}
		// insert List of merchantRepresentativeDetails
		for (MerchantsRepresentativeDetailsRequest representativeReq : representativesReqs) {
			representativeReq.setMerchantId(id);
			representativeService.save(new MerchantsRepresentativeDetails(representativeReq));
		}
		// insert List of merchantStockholdersDetails
		for (MerchantsStockholdersDetailsRequest stockholdersReq : stockholdersReqs) {
			stockholdersReq.setMerchantId(id);
			;
			stockholdersService.save(new MerchantsStockholdersDetails(stockholdersReq));
		}
		// insert List of make payment info to MerchantsServicePreferenceDetailsTable
		for (MerchantsServicePreferenceRequest servicePrefReq : makePaymentServicePrefReq) {
			servicePrefReq.setMerchantId(id);
			servicePreferenceService.save(new MerchantsServicePreference(servicePrefReq));
		}
		// insert List of get payment info to MerchantsServicePreferenceDetailsTable
		for (MerchantsServicePreferenceRequest servicePrefReq : getPaymentServicePrefReq) {
			servicePrefReq.setMerchantId(id);
			servicePreferenceService.save(new MerchantsServicePreference(servicePrefReq));
		}

		return "Data saved to the database";
	}
}
