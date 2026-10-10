package com.example.springfwautumn26;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        BigDecimal price
) {
}