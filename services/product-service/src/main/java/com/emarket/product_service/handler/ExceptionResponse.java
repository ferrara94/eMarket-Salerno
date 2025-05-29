package com.emarket.product_service.handler;

import java.util.Map;

public record ExceptionResponse(
        Map<String, String> errors
){
}
