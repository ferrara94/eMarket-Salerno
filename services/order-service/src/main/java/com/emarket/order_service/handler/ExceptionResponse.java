package com.emarket.order_service.handler;

import java.util.Map;

public record ExceptionResponse(
        Map<String, String> errors
){
}
