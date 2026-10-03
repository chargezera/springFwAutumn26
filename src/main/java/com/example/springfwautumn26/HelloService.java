package com.example.springfwautumn26;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private final AppProperties appProperties;
    private final String university = "KBTU";

    public HelloService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String giveName(String name) {
        return "Your name is " + name
                + ". You study at " + university
                + ". " + appProperties.message();
    }
}