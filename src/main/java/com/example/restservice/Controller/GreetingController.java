package com.example.restservice.Controller;

import com.example.restservice.Model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(defaultValue = "World") String name) {
        String stringTemplate = "Hello, %s!";
        return new Greeting(counter.incrementAndGet(), String.format(stringTemplate, name));
    }
}
