package com.gme.hom.merchants.bankDetails.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantsBankDetailsRequest {
    

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("merchant_id")
    private Long merchantId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("bank_id")
    private String bankId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("account_name")
    private String accountName;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("swift_code")
    private String swiftCode;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("bic_code")
    private String bicCode;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("ifsc_code")
    private String ifscCode;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("iban_cbu_card_number")
    private String ibanCbuCardNumber;


}
