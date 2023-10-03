package com.gme.hom.api.external.swifttech.service;

import com.gme.hom.api.external.swifttech.models.SwiftSMSRequest;

public interface SwiftSMSSender {

	public int sendSwiftSMS(SwiftSMSRequest swiftSMSRequest);
}
