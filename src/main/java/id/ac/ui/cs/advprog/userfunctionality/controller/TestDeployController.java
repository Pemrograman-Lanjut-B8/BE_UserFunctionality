package id.ac.ui.cs.advprog.userfunctionality.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDeployController{
    @GetMapping("/")
    public String index() {
        return "Hello World";
    }
}