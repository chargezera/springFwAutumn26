package com.example.springfwautumn26;

public class ProductConflictException extends RuntimeException {

    public ProductConflictException(String name) {
        super("Product with name '" + name + "' already exists");
    }
}