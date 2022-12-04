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

            // Calculate intermediate results
            result = 0.0;

            // Push intermediate result back onto the stack
            calcStack.push( result );
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


