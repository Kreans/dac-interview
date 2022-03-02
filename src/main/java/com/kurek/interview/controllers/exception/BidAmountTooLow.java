package com.kurek.interview.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Bid request amount is too low")
public class BidAmountTooLow extends RuntimeException {
}
