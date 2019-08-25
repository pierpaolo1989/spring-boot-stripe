package com.soa.stripe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soa.stripe.dto.CustomerDto;
import com.stripe.model.Customer;

@Service
public class StripeService implements IStripeService{
	
	private static final Logger logger = LoggerFactory.getLogger(StripeService.class.getName());
	
	@Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

	@Override
	public Customer getCustomer(String id) throws Exception {
		try {
			return Customer.retrieve(id);
		} catch (Exception e) {
			logger.error("impossible to retrieve customer with ID: {}",id,e.getMessage());
			throw(e);
		}
	}

}
