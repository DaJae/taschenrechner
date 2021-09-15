package de.dajae.taschenrechner.controller;

import de.dajae.taschenrechner.database.RepoHelper;
import de.dajae.taschenrechner.parser.ParserTwoPointO;
import de.dajae.taschenrechner.parser.parserexceptions.SyntaxError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Calculator {

    private RepoHelper repoHelper;
    private ParserTwoPointO parser;

    @PostMapping("/calculate")
    public String calculate(@RequestParam("formula") String formula, @RequestParam("userAgent") String userAgent, Model model){

        model.addAttribute("pastFormula", formula);

        try{
            double result = parser.parse(formula.trim());
            model.addAttribute("result", result);

            if(repoHelper.checkHistoryEntries(userAgent)){
                repoHelper.createUser(userAgent);
            }
            repoHelper.saveHistory(userAgent, formula);
        } catch (SyntaxError error){
            model.addAttribute("result", error.getMessage());
        }

        return "calculator";
    }

    @GetMapping("/calculator")
    public String calculate(){

        return "calculator";
    }

    @Autowired
    public void setRepoHelper(RepoHelper repoHelper){
        this.repoHelper = repoHelper;
    }

    @Autowired
    public void setParser(ParserTwoPointO parser){
        this.parser = parser;
    }


}
