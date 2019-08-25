package com.soa.stripe.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soa.stripe.dto.ChargeRequest;
import com.soa.stripe.dto.CustomerDto;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
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

	@Override
	public Charge charge(ChargeRequest request) throws AuthenticationException, InvalidRequestException,
	ApiConnectionException, CardException, ApiException {
		Map<String, Object> chargeParams = new HashMap<String,Object>();
		chargeParams.put("amount", request.getAmount());
		chargeParams.put("currency", request.getCurrency());
		chargeParams.put("description", request.getDescription());
		chargeParams.put("source", request.getStripeToken());
		return Charge.create(chargeParams);
	}
}
