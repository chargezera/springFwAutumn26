package com.example.springfwautumn26;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;


    @GetMapping("/get/{name}")
    public String getHelloName(@PathVariable String name) { return helloService.giveName(name);}
}
