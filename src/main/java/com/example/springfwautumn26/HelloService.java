package com.example.springfwautumn26;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class HelloService {

    public String university = "KBTU";

    public String giveName(String name){
        return "Your name is " + name  + ". You study at " + university;
    }
}
