package com.emarket.customer.service.handler;

import java.util.Map;

public record ExceptionResponse(
        Map<String, String> errors
){
}
