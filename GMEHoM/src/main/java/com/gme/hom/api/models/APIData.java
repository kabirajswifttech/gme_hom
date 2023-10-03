package com.gme.hom.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.auth.models.OTP;
import com.gme.hom.documents.models.DocumentRequest;
import com.gme.hom.kyc.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.kyc.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.kyc.merchants.model.MerchantRequest;
import com.gme.hom.kyc.owners.model.MerchantsOwnersDetailsRequest;
import com.gme.hom.kyc.preferredServices.model.MerchantsServicePreferenceRequest;
import com.gme.hom.kyc.representatives.model.MerchantsRepresentativeDetailsRequest;
import com.gme.hom.kyc.stockholders.model.MerchantsStockholdersDetailsRequest;
import com.gme.hom.messaging.models.MessageRequest;
import com.gme.hom.security.models.DedupEntityHash;
import com.gme.hom.users.models.UserRequest;
import com.gme.hom.users.models.UserSignupRequest;
import com.gme.hom.usersecurity.models.UserAuthRequest;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIData {

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("query_params")
	private APIRequestQueryParams query;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("body")
	private String body;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("otp")
	private OTP otp;

	/*
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchant")
	private Merchant merchant;
	*/
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("entity_hash")
	private DedupEntityHash entityHash;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("message")
	private MessageRequest messageRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_auth")
	private UserAuthRequest userAuthRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("documents")
	private List<DocumentRequest> documentRequests;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_signup_request")
	private UserSignupRequest userSignupRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user")
	private UserRequest userRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchant")
	private MerchantRequest merchantRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_bank_detail")
	private MerchantsBankDetailsRequest merchantsBankDetailsRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_directors_detail")
	private MerchantsDirectorsDetailsRequest merchantsDirectorsDetailsRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_representative_detail")
	private MerchantsRepresentativeDetailsRequest merchantsRepresentativeDetailsRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_stockholders_detail")
	private MerchantsStockholdersDetailsRequest merchantsStockholdersDetailsRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("make_payment_info")
	private MerchantsServicePreferenceRequest merchantServicePreferenceRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_directors_details")
	private List<MerchantsDirectorsDetailsRequest> merchantsDirectorsDetailsRequests;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_representative_details")
	private List<MerchantsRepresentativeDetailsRequest> merchantsRepresentativeDetailsRequests;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_stockholders_details")
	private List<MerchantsStockholdersDetailsRequest> merchantsStockholdersDetailsRequests;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("make_payment_infos")
	private List<MerchantsServicePreferenceRequest> makePaymentInfoRequests;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("get_payment_infos")
	private List<MerchantsServicePreferenceRequest> getPaymentInfoRequests;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_owners_detail")
	private MerchantsOwnersDetailsRequest merchantsOwnersDetailsRequest;
	
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_owners_details")
	private List<MerchantsOwnersDetailsRequest> merchantsOwnersDetailsRequests;
	

}