package com.soa.stripe.service;

import com.soa.stripe.dto.CustomerDto;
import com.stripe.model.Customer;

public interface IStripeService {
	
	public Customer getCustomer(String id) throws Exception;

}
