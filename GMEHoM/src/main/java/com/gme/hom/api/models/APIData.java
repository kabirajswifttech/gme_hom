package com.gme.hom.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gme.hom.auth.models.OtpRequest;
import com.gme.hom.auth.models.PasswordRequest;
import com.gme.hom.documents.models.DocumentRequest;
import com.gme.hom.merchants.bankDetails.model.MerchantsBankDetailsRequest;
import com.gme.hom.merchants.directors.model.MerchantsDirectorsDetailsRequest;
import com.gme.hom.merchants.kyc.model.MerchantRequest;
import com.gme.hom.merchants.owners.model.MerchantsOwnersDetailsRequest;
import com.gme.hom.merchants.preferredServices.model.MerchantsServicePreferenceRequest;
import com.gme.hom.merchants.representatives.model.MerchantsRepresentativeDetailsRequest;
import com.gme.hom.merchants.stockholders.model.MerchantsStockholdersDetailsRequest;
import com.gme.hom.messaging.models.MessageRequest;
import com.gme.hom.security.models.DedupEntityHash;
import com.gme.hom.users.models.UserInfoRequest;
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
	private OtpRequest otpRequest;

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

	// users
	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_signup")
	private UserSignupRequest userSignupRequest;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user")
	private UserRequest userRequest;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("user_info")
	private UserInfoRequest userInfoRequest;

	// merchants

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
	@JsonProperty("merchants_service_preference_details")
	private List<MerchantsServicePreferenceRequest> merchantServicePreferenceRequests;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_directors_details")
	private List<MerchantsDirectorsDetailsRequest> merchantsDirectorsDetailsRequests;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("merchants_representatives_details")
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
	@JsonProperty("merchants_owners_details")
	private List<MerchantsOwnersDetailsRequest> merchantsOwnersDetailsRequests;

	@Valid
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("password")
	private PasswordRequest passwordRequest;
	
}
