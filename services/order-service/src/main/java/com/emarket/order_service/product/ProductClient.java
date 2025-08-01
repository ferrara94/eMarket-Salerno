package com.emarket.order_service.product;

import com.emarket.order_service.exception.BusinessException;
import com.emarket.order_service.purchase.PurchaseRequest;
import com.emarket.order_service.purchase.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> request){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(request, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<List<PurchaseResponse>>(){};
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()){
            throw new BusinessException("AN ERROR OCCURRED WHILE PROCESSING THE PRODUCT PURCHASE: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }

}
