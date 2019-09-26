package com.soa.stripe.service;

import com.soa.stripe.dto.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

public interface IStripeService {

	/**
	 * Retrieve the customer
	 * @param id
	 * @return {@link Customer}
	 * @throws Exception - thrown in case of exception
	 */
	public Customer getCustomer(String id) throws Exception;
	
	/**
	 * Create a charge request
	 * @param request, object with charge information
	 * @return {@link Charge}
	 * @throws StripeException - thrown in case of exception
	 */
	public Charge charge(ChargeRequest request) throws StripeException;
	
	/**
	 * Create a customer into Stripe dashboard
	 * @param email
	 * @param token
	 * @return customer ID
	 */
	public String createCustomer(String email, String token);

}
