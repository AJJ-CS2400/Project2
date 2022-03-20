public class Calculator<T> { // converts infix expression to the eqivalent postfix expression
    static String convertToPostfix(String infix)
     {
        // Sanitize user inputs
        if (infix.isEmpty())
        {
            return infix;
        } // retruns null if the infix expression is empty
        else if (infix == null)
         {
            throw new NullPointerException();
        } else {
            // linked stack method implementation
            LinkedStack<Character> operatorStack = new LinkedStack<>();
            String postfix = "";
            char nextCharacter;
            char topOperator;
            int count = 0;

            while (count < infix.length()) // iterating through the entire infix expression
            {
                nextCharacter = infix.charAt(count); // counts each letter within the infix expression

                switch (nextCharacter) {
                    case '^':
                        operatorStack.push(nextCharacter); // pushes the character into the stack
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!operatorStack.isEmpty()
                                && precedence(nextCharacter) <= precedence(operatorStack.peek()))
                        {
                            postfix += operatorStack.pop(); // pops the character
                        }
                        operatorStack.push(nextCharacter); // pushes the operator '('
                        break;
                    case '(':
                        operatorStack.push(nextCharacter); // pushes the operator ')'
                        break;
                    case ')':
                        topOperator = operatorStack.pop(); // system calls to pop the operator within the '()' into the
                                                           // stack
                        while (topOperator != '(')
                        {
                            postfix += topOperator;
                            topOperator = operatorStack.pop();
                        }
                        break;
                    default: // if variable, append to postfix
                        if (Character.isLetter(nextCharacter))
                        {
                            postfix += nextCharacter;
                        }
                } // end switch

                count++;
            }
            while (!operatorStack.isEmpty())
            {
                topOperator = operatorStack.pop();
                postfix += topOperator;
            } // append the rest of the operators left in the stack

            return postfix;
        }
    } // end convertToPostfix

    static int precedence(char c) // checks for the precedence of each character as it iterates through each case
    {                             // allows the program to interate and print out in correctly
        switch (c)
         {
            case '+': // precedence of += is less than */
            case '-':
                return 1;
            case '*': // precedence of */ is higher than +-
            case '/':
                return 2;
        }
        return -1;
    } // end precedence
      // beginning of evaluating Postfix method

    public static int evaluatePostfix(String postfix) {
        StackInterface<Integer> valueStack = new LinkedStack<Integer>();

        char nextCharacter;
        int characterCount = postfix.length();
        int operandOne;
        int operandTwo;
        int result;

        int i = 0;
        while (i < characterCount) // iterates through the loop and checks each character
        {
            nextCharacter = postfix.charAt(i); // initializes the while loop at 0

            switch (nextCharacter) // each charater aligns with the valua manually inputted
            {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                    int temp = Character.getNumericValue(nextCharacter) - 8; // getNumericValue makes a-z = to 10-35
                    valueStack.push(temp); // subtracting 8 will make the value of a = 2, b = 3, etc.
                    break;

                case '+': // case for the '+'
                    operandTwo = valueStack.pop(); // pops out the values of oprand two
                    operandOne = valueStack.pop(); // pops out the values of oprand one
                    result = operandOne + operandTwo; // adds oprand one and oprand two together to get result
                    valueStack.push(result); // pushes the result into the stack
                    break;

                case '-': // case for '-'
                    operandTwo = valueStack.pop(); // pops out the values of oprand two
                    operandOne = valueStack.pop(); // pops out the values of oprand one
                    result = operandOne - operandTwo; // subtracts oprand one and oprand two together to get result
                    valueStack.push(result); // pushes the result into the stack
                    break;

                case '*': // case for '*'
                    operandTwo = valueStack.pop(); // pops out the values of oprand two
                    operandOne = valueStack.pop(); // pops out the values of operand one
                    result = operandOne * operandTwo; // multiplies operand one and oprand two
                    valueStack.push(result); // pushes the result into the stack
                    break;

                case '/': // case for '/'
                    operandTwo = valueStack.pop(); // pops out operand two
                    operandOne = valueStack.pop(); // pops out oprand one
                    result = operandOne / operandTwo; // divides operand one and operand two
                    valueStack.push(result); // pushes the result into the stack
                    break;

                default:
                    break;
            }
            i++;
        }
        return valueStack.peek(); // retruns the values back to the peek function
    }

    // main method
    public static void main(String[] args)
    {
        //beginning of infix to postfix conversion
        String infix = "a*b/(c-a)+d*e"; // infix expression inputted manually
        System.out.println("Infix Expression: " + infix); // system prints out infix expression
        String postfix = convertToPostfix(infix); // calls for the conversion of infix to postfix method
        System.out.println("Postfix Expression:  " + postfix); // system prints out the infix and postfix translation

        // beginning of evaluation of postfix method
        Calculator test = new Calculator(); // set calculator to equal new object
        int postfixResult = test.evaluatePostfix("ab*ca-de*+/"); // postfix expression inputted manually
        System.out.println("Postfix Result: " + postfixResult); // system evaluates postfix expression and calls to
                                                                // print out the postfix evaluation
    }
}
