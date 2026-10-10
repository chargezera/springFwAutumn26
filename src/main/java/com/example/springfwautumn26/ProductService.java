package com.example.springfwautumn26;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, Product> products = new LinkedHashMap<>();

    private long nextId = 1;

    public synchronized ProductResponse create(ProductRequest request) {
        String name = request.name().trim();

        checkDuplicateName(name, null);

        Product product = new Product(
                nextId++,
                name,
                request.price()
        );

        products.put(product.id(), product);

        return toResponse(product);
    }

    public synchronized List<ProductResponse> findAll() {
        return products.values()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public synchronized ProductResponse findById(Long id) {
        Product product = getProduct(id);

        return toResponse(product);
    }

    public synchronized ProductResponse update(
            Long id,
            ProductRequest request
    ) {
        getProduct(id);

        String name = request.name().trim();

        checkDuplicateName(name, id);

        Product updatedProduct = new Product(
                id,
                name,
                request.price()
        );

        products.put(id, updatedProduct);

        return toResponse(updatedProduct);
    }

    public synchronized void delete(Long id) {
        getProduct(id);

        products.remove(id);
    }

    private Product getProduct(Long id) {
        Product product = products.get(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }

    private void checkDuplicateName(String name, Long excludedId) {
        boolean duplicateExists = products.values()
                .stream()
                .anyMatch(product ->
                        product.name().equalsIgnoreCase(name)
                                && !product.id().equals(excludedId)
                );

        if (duplicateExists) {
            throw new ProductConflictException(name);
        }
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.name(),
                product.price()
        );
    }
}