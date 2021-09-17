package de.dajae.taschenrechner.controller;

import de.dajae.taschenrechner.parser.ParserTwoPointO;
import de.dajae.taschenrechner.parser.parserexceptions.SyntaxError;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorRestController {

    private ParserTwoPointO parser;

    @PostMapping(value = "/api/calculate")
    public Map<String, String> apiCalculator (@RequestParam("formula") String formula){
        Map<String, String> resultMap = new HashMap<>();
        try {
            double result = parser.parse(formula);
            resultMap.put("pastFormula", formula);
            resultMap.put("result", String.valueOf(result));
        } catch (SyntaxError error){
            resultMap.put("pastFormula", formula);
            resultMap.put("result", error.getMessage());
        }

        return resultMap;
    }

    @Autowired
    public void setParser(ParserTwoPointO parser){
        this.parser = parser;
    }
}
