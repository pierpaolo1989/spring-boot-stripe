package com.soa.stripe.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soa.stripe.dto.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
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
			logger.error("Impossible to retrieve customer with ID: {}",id,e.getMessage());
			throw(e);
		}
	}

	@Override
	public Charge charge(ChargeRequest request) throws StripeException {
		Map<String, Object> chargeParams = new HashMap<String,Object>();
		chargeParams.put("amount", request.getAmount());
		chargeParams.put("currency", request.getCurrency());
		chargeParams.put("description", request.getDescription());
		chargeParams.put("source", request.getStripeToken());
		return Charge.create(chargeParams);
	}
	
	@Override
	public String createCustomer(String email, String token) {
        String id = null;
        try {
            Stripe.apiKey = stripePublicKey;
            Map<String, Object> customerParams = new HashMap<>();
            
            customerParams.put("description", "Customer for " + email);
            customerParams.put("email", email);

            customerParams.put("source", token); // obtainer with Stripe js
            
            //create a new customer
            Customer customer = Customer.create(customerParams);
            id = customer.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
