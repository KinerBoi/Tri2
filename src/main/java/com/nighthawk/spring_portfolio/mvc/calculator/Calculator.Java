public class Delimiters
private String openDel
private String closeDel
public Delimiters(String open, String close)
public ArrayList<String> getDelimitersList(String[] tokens)
public boolean isBalanced(ArrayList<String> delimiters)

// Helper definition to define operators, lookup in MAP are fast and easy O(1) versus ArrayList O(n)
private final Map<String, Integer> OPERATORS = new HashMap<>();
{
    // Map<"token", precedence>
    OPERATORS.put("*", 3);
    OPERATORS.put("/", 3);
    OPERATORS.put("%", 3);
    OPERATORS.put("+", 4);
    OPERATORS.put("-", 4);
    OPERATORS.put("^",1);
    OPERATORS.put("ROOT",1);
}

// Helper definition for supported separators
private final Map<String, Integer> SEPARATORS = new HashMap<>();
{
    // Map<"separator", not_used>
    SEPARATORS.put(" ", 0);
    SEPARATORS.put("(", 0);
    SEPARATORS.put(")", 0);
}

// Helper definition for supported separators
private final Map<String, Integer> SEPARATORS = new HashMap<>();
{
    // Map<"separator", not_used>
    SEPARATORS.put(" ", 0);
    SEPARATORS.put("(", 0);
    SEPARATORS.put(")", 0);
}


public class Calculator () {
    
    // Key instance variables
    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;


    // Create a 1 argument constructor providing a mathematical expression
        public Calculator(String expression) {
            // original input
            this.expression = expression;

            // parse expression into terms
            this.termTokenizer();

            this.parenthesisValidation();

            // place terms into reverse polish notation
            this.tokensToReversePolishNotation();

            // calculate reverse polish notation
            this.rpnToResult();
        }

        // Print the expression, terms, and result
        public String toString() {
            return ("Original expression: " + this.expression + "\n" +
                    "Tokenized expression: " + this.tokens.toString() + "\n" +
                    "Reverse Polish Notation: " +this.reverse_polish.toString() + "\n" +
                    "Final result: " + String.format("%.2f", this.result));
        }

        // Term Tokenizer takes original expression and converts it to ArrayList of tokens
    private void termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0;  // term split starting index
        StringBuilder multiCharTerm = new StringBuilder();    // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if ( isOperator(c.toString() ) || isSeperator(c.toString())  ) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator or parenthesis term to list
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
    }

    // Takes tokens and converts to Reverse Polish Notation (RPN).
    private void tokensToReversePolishNotation () {
        // contains final list of tokens in RPN
        List<String> reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack tokenStack = new Stack();
        for (String token : tokens) {
            switch (token) {
                // If left bracket push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("("))
                    {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    tokenStack.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while (tokenStack.peek() != null && isOperator((String) tokenStack.peek()))
                    {
                        if ( isPrecedent(token, (String) tokenStack.peek() )) {
                            reverse_polish.add((String)tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default:    // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
            }
        }
        // Empty remaining tokens
        while (tokenStack.peek() != null) {
            reverse_polish.add((String)tokenStack.pop());
        }

    }

    private void parenthesisValidation {
        int leftParenthesis = 0;
        int rightParenthesis = 0;

        for (int i = 0; i < expression.length(),i++) {
            if (this.expression.charAt() == "(") {
                leftParenthesis++
            }
            else if (this.expression.charAt() == ")") {
                rightParenthesis++;
            }
        }

        if (leftParenthesis != rightParenthesis) {
            System.out.println("Check your Parenthesis, the left and right ones do not match");
        }
    } 

    public double calculation (String operator, double x1, double x2) {

        if (operator == "+") {
            return x1 + x2;
        }
        else if (operator == "-") {
            return x1 - x2;
        }
        else if (operator == "*") {
            return x1 * x2;
        }
        else if (operator == "/") {
            return x1 - x2;
        }
        else if (operator -- "%") {
            return x1 % x2;
        }
        else if (operator == "^") {
            return Math.pow(x1,x2);
        }
        else if (operator == "ROOT") {
            return Math.pow(x1, 1/x2);
        }
        else {
            System.out.println("Unidentified Operator" + operator);
        }

    }

    // Takes RPN and produces a final result
    private void rpnToResult()
    {
        // stack is used to hold operands and each calculation
        Stack<Double> calcStack = new Stack<Double>();

        // RPN is processed, ultimately calcStack has final result
        for (String token : this.reverse_polish)
        {
            // If the token is an operator, calculate
            if (isOperator(token))
            {
                // Pop the two top entries
                double x1 = calcStack.pop(Double.valueOf(token))
                double x2 = calcStack.pop(Double.valueOf(token))

                // Calculate intermediate results
                result = 0.0;
                result = calculation(x1,x2,token);

                // Push intermediate result back onto the stack
                calcStack.push(result);
            }
            // else the token is a number push it onto the stack
            else
            {
                calcStack.push(Double.valueOf(token));
            }
        }
        // Pop final result and set as final result for expression
        this.result = calcStack.pop();
    }

    public static void main (String args[]) {
        Calculator addition = new Calculator(100 + 200 + 300 + 400 + 500);
        System.out.println(addition.toString);

        Calculator subtraction = new Calculator(2000 - 100 - 500 - 300 - 200);
        System.out.println(subtraction.toString);

        Calculator multiplication = new Calculator(2 * 3 * 9 * 0.5);
        System.out.println(multiplication.toString);

        Calculator division = new Calculator(2 / 3 / 9 / 1);
        System.out.println(division.toString);

        Calculator modulus = new Calculator(5 % 2);
        System.out.println(modulus.toString);

        Calculator power = new Calculator(5 ^ 2);
        System.out.println(power.toString);

        Calculator root = new Calculator(5 ROOT 2);
        System.out.println(root.toString);
    }

}



