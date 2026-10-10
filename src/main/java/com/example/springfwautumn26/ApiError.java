package com.example.springfwautumn26;

import java.util.Map;

public record ApiError(
        int status,
        String message,
        String path,
        Map<String, String> errors
) {
}