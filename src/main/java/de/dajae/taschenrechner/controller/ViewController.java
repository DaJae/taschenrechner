package de.dajae.taschenrechner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(value = "/calculator")
    public String getCalculator (){
        return "calculator";
    }
}
