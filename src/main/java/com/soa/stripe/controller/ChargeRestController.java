package com.soa.stripe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.stripe.dto.ChargeRequest;
import com.soa.stripe.service.IStripeService;
import com.stripe.model.Charge;

@RestController
@RequestMapping("/charge")
public class ChargeRestController {

	private static final Logger logger = LoggerFactory.getLogger(ChargeRestController.class.getName());

	@Autowired
	private IStripeService stripeService;

	@PostMapping(value="/charge")
	public ResponseEntity<Charge> charge(@RequestBody ChargeRequest request) throws Exception {
		HttpStatus status = null;
		Charge charge = null;
		try {
			charge = stripeService.charge(request);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("Impossible to execute the charge",e.getMessage());
			throw(e);
		}
		return new ResponseEntity<Charge>(charge, status);
	}
}

