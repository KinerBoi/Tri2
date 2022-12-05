package com.nighthawk.spring_portfolio.mvc.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.lang.Math;

/* In mathematics,
    an expression or mathematical expression is a finite combination of symbols that is well-formed
    according to rules that depend on the context.
   In computers,
    expression can be hard to calculate with precedence rules and user input errors
    to handle computer math we often convert strings into reverse polish notation
    to handle errors we perform try / catch or set default conditions to trap errors
     */
public class Calculator {
    // Key instance variables
    private String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result = 0.0;

    // Helper definition for supported operators
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("log", 5);
        OPERATORS.put("exp", 2);
        OPERATORS.put("^", 2);
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
    }

    // Helper definition for supported operators
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }

    // Create a 1 argument constructor expecting a mathematical expression
    public Calculator(String expression) {
        // original input
        this.expression = expression;

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();

        // calculate reverse polish notation
        this.rpnToResult();
    }

    // Test if token is an operator
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an separator
    private boolean isSeparator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0) ;
    }

    // Term Tokenizer takes original expression and converts it to ArrayList of tokens
    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0;  // term split starting index
        // holds a term
        StringBuilder multiCharTerm = new StringBuilder();
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if ( isOperator(c.toString() ) || isSeparator(c.toString())  ) {
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator/parenthesis to the list
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                // preparing for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    private boolean parenthesesCheck() {
        int leftParentheses = 0;
        int rightParentheses = 0;
        for (int i = 0; i < this.expression.length(); i++) {
            if (this.expression.charAt(i) == '(') {
                leftParentheses++;
            } else if (this.expression.charAt(i) == ')') {
                rightParentheses++;
            }
        }

        if (leftParentheses != rightParentheses) {
            return false;
        }
        else {
            return true;
        }
    }

    // Conversion to Reverse Polish Notation (RPN), the operator follows its operands.
    private void tokensToReversePolishNotation () {
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder the elements
        Stack<String> tokenStack = new Stack<String>();
        for (String token : tokens) {
            switch (token) {
                // If it's a left bracket, push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("("))
                    {
                        reverse_polish.add( tokenStack.pop() );
                    }
                    tokenStack.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":
                case "log":
                case "exp":
                    // While stack has stuff and the top of the stack is an operator
                    while (tokenStack.size() > 0 && isOperator(tokenStack.peek()))
                    {
                        if ( isPrecedent(token, tokenStack.peek() )) {
                            reverse_polish.add(tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default: 
                    try
                    {
                        Double.parseDouble(token);
                    }
                    catch(NumberFormatException e)
                    {
                        // variable resolves to 0 for it to work
                        this.reverse_polish.add("0");
                        this.expression = "Error with parsing your expression \'" + this.expression + "\'. Please enter valid inputs and try again.";
                        break;
                    }
                    this.reverse_polish.add(token);
            }
        }
        // Empties remaining tokens
        while (tokenStack.size() > 0) {
            reverse_polish.add(tokenStack.pop());
        }

    }

    private void rpnToResult()
    {
        // stack holds operands and calculations
        Stack<Double> calcStack = new Stack<Double>();

        // RPN processes and calcStack has final result
        for (String token : this.reverse_polish)
        {
            // token operator --> Calculator
            if (isOperator(token))
            {
                              
                // Pop top two entries
                double a = calcStack.pop();
                double b = calcStack.pop();

                // Calculate middle thingies
                switch (token) {
                    // b goes first since its the second one to be popped
                    case "+":
                        result = b + a;
                        break;
                    case "-":
                        result = b - a;
                        break;
                    case "*":
                        result = b * a;
                        break; 
                    case "/":
                        result = b / a;
                        break;
                    case "%":
                        result = b % a;
                        break;
                    case "^":
                    case "log":
                        result = (Math.log(a) / Math.log(b));
                        break;
                    case "exp":
                        // Math.pow() function
                        result = Math.pow(b,a);
                        break;
                    default:
                        break;
                }

                calcStack.push( result );
            }
            // token is a number so push to stack
            else
            {
                calcStack.push(Double.valueOf(token));
            }
        }
        this.result = calcStack.pop();
    }

    public String calcToString(boolean x) {
        if (x) {
        System.out.println("--------");
        System.out.println("Result: " + this.expression + " = " + this.result);
        System.out.println("Tokens: " + this.tokens + " , RPN: " + this.reverse_polish);
        }

        String output = this.expression + " = " + this.result;
        return output;
    }

    public String toString() {
        return ( "{ \"Expression\": \""  + this.expression + "\", \"Tokens\": \"" + this.tokens + "\", \"RPN\": \"" + this.reverse_polish + "\", \"Result\": " + this.result + " }" );
    }
    
}
