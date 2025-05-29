package com.emarket.product_service.product;

import com.emarket.product_service.exception.ProductNotFoundException;
import com.emarket.product_service.exception.ProductPurchaseException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public List<ProductResponse> findAllProducts() {
        return repository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findProductById(Integer productId) {
       return repository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("NO PRODUCT FOUND WITH THE PROVIDED ID:: %S",productId)
                ));
    }

    public Integer createProduct(@Valid ProductRequest request) {
        var product = productMapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(@Valid List<ProductPurchaseRequest> request) {
        // Extract the list of Products ID
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        // Check if the all products to purchase exist in the Database
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("ONE OR MORE PRODUCTS DOESN'T EXIST");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            // Check if the quantity of the target product to purchase is available
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("INSUFFICIENT STOCK QUANTITY FOR PRODUCT WITH ID:: "+ productRequest.productId());
            }
            // Update the quantity
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }
}
