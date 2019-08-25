package com.soa.stripe.service;

import com.soa.stripe.dto.ChargeRequest;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

public interface IStripeService {

	public Customer getCustomer(String id) throws Exception;
	public Charge charge(ChargeRequest request) throws AuthenticationException, InvalidRequestException,
													   ApiConnectionException, CardException, ApiException;

}
