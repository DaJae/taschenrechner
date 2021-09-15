package de.dajae.taschenrechner.parser;

import de.dajae.taschenrechner.parser.parserexceptions.SyntaxError;
import org.springframework.stereotype.Component;

@Component
public class ParserTwoPointO {

    int pos = -1;
    int ch = -1;
    String eq = "";

    private void nextChar(){
        ch = (++pos) < eq.length() ? eq.charAt(pos) : -1;
    }

    private void nextChar(int skip){
        ch = (pos = pos + skip) < eq.length() ? eq.charAt(pos) : -1;
    }

    public double parse(String formula) throws SyntaxError{
        this.eq = formula.trim();

        double x = parseExp();

        pos = -1;
        ch = -1;
        eq = "";
        return x;
    }

    private double parseExp() throws SyntaxError{
        double x = parseBase();

        trimWhitespace();

        while (ch != -1 && ch != ')') {
            if (ch == '*') {
                x *= parseBase();
            }

            if (ch == '/') {
                x /= parseBase();
            }

            if (ch == '+') {
                x += parseBase();
            }

            if (ch == '-') {
                x -= parseBase();
            }
        }

        return x;
    }

    private double parseBase() throws SyntaxError {

        double x;

        nextChar();
        trimWhitespace();

        int startIndex = pos;

        //Parentheses
        if (ch == '('){
            x = parseExp();
            if (ch != ')'){
                throw new SyntaxError("Missing \")\" at column " + pos);
            }
            nextChar();
            return x;
        }

        //Numbers
        if (ch >= '0' && ch <= '9') {
            while (ch >= '0' && ch <= '9') {
                nextChar();
            }

            x = Double.parseDouble(eq.substring(startIndex, pos));

            if (ch == '^'){
                x = Math.pow(x, parseBase());
            }
            return x;
        }

        return trigonometricFunctions();
    }

    private double trigonometricFunctions() throws SyntaxError{
        double x;

        if (eq.substring(pos, pos+3).equals("sin")){
            nextChar(2);
            x = parseExp();
            x = Math.sin(x);

            return x;
        }

        if (eq.substring(pos, pos+3).equals("cos")){
            nextChar(2);
            x = parseExp();
            x = Math.cos(x);

            return x;
        }

        if (eq.substring(pos, pos+3).equals("tan")){
            nextChar(2);
            x = parseExp();
            x = Math.tan(x);

            return x;
        }

        throw new SyntaxError("Unknown symbol starting at column: " + pos);
    }

    private void trimWhitespace(){
        while(ch == ' '){
            nextChar();
        }
    }
}
