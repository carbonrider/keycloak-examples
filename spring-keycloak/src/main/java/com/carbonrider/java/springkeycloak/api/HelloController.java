package com.carbonrider.java.springkeycloak.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path = {"/hello"})
    public HelloMessage sayHello() {
        HelloMessage message = new HelloMessage();
        message.setMessage("You got it!!!");
        return message;
    }
}
