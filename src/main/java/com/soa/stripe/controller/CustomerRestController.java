package com.soa.stripe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.stripe.dto.ChargeRequest;
import com.soa.stripe.service.IStripeService;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerRestController.class.getName());
	
	@Autowired
	private IStripeService stripeService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) throws Exception {
		HttpStatus status = null;
		Customer customer = null;
		try {
			customer = stripeService.getCustomer(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("Impossible to retrieve the customer with ID: {}", id,e.getMessage());
			throw(e);
		}
		return new ResponseEntity<Customer>(customer,status);
	}
	
}