public class Calculator
{
    public static void main(String[] args)
    {
        System.out.println("Passing string \"a*b/(c-a)+d*e\" to convertToPostFix method");
        String infix = "a*b/(c-a)+d*e";
        String postfix = Calculator.convertToPostfix(infix);
        System.out.println("Outputted postfix string: \"" + postfix + "\"");

        System.out.println("Passing postfix string \"" + postfix + "\" to evaluatePostfix method");
        System.out.println("Assuming: a=2, b=3, c=4, d=5, e=6");
        int result = Calculator.evaluatePostfix("ab*ca-de*+/");
        System.out.println("Outputted result: " + result);
    }

    /** Converts an infix expression to a postfix expression
     * @param infix the infix expression given as a String
     * @return Returns the postfix expression as a String
     */
    public static String convertToPostfix(String infix)
    {
        // Sanitize inputs
        if (infix.isEmpty()) {return infix;}
        else if (infix == null) {throw new NullPointerException();}
        else
        {

            // Method
            StackInterface<Character> operatorStack = new LinkedStack<>();  // LinkedStack implementation
            String postfix = "";
            char nextCharacter;
            char topOperator;
            int count = 0;

            while (count < infix.length()) // iterate through the entire infix expression
            {
                nextCharacter = infix.charAt(count);

                switch (nextCharacter)
                {
                    case '^':
                        operatorStack.push(nextCharacter);
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while (!operatorStack.isEmpty() && precedence(nextCharacter) <= precedence(operatorStack.peek()))
                        {
                            postfix += operatorStack.pop();
                        }
                        operatorStack.push(nextCharacter);
                        break;
                    case '(':
                        operatorStack.push(nextCharacter);
                        break;
                    case ')':
                        topOperator = operatorStack.pop();
                        while (topOperator != '(')
                        {
                            postfix += topOperator;
                            topOperator = operatorStack.pop();
                        }
                        break;
                    default:  // if variable, append to postfix
                        if (Character.isLetter(nextCharacter))
                        {
                            postfix += nextCharacter;
                        }
                        break; // ignore unexpected characters
                } // end switch
                count++;
            }
            while (!operatorStack.isEmpty())
            {
                topOperator = operatorStack.pop();
                postfix += topOperator;
            } // append the rest of the operators left in the stack, if any

            return postfix;
        }
    } // end convertToPostfix

    // method for determining the precedence of operations
    private static int precedence(char c)
    {
        switch (c)
        {
            case '+':       // precedence of += is less than */
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;      // failsafe
    } // end precedence

    /** Evaluates a postfix expression, given a=2,b=3,c=4,d=5,and e=6 (a-z = 2-28)
     * @param postfix the postfix expression given as a String
     * @return Returns the evaluation of the postfix expression as an Integer.
     */
    public static int evaluatePostfix(String postfix)
    {
        // Sanitize inputs
        if (postfix.isEmpty()) {return 0;}
        else if (postfix == null) {throw new NullPointerException();}
        else
        {
            StackInterface<Integer> valueStack = new ResizeableArrayStack<>();  // ResizeableArrayStack implementation

            char nextCharacter;
            int characterCount = postfix.length();
            int operandOne;
            int operandTwo;
            int result;

            int i = 0;
            while (i < characterCount)
            {
                nextCharacter = postfix.charAt(i);

                switch (nextCharacter)
                {
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                        int temp = Character.getNumericValue(nextCharacter) - 8;  // converts a-z -> 10-35
                        valueStack.push(temp);           // subtracting 8 to this value will give:
                        break;                           // a=2,b=3,c=4,d=5,e=6

                    case '+':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = operandOne + operandTwo;
                        valueStack.push(result);
                        break;

                    case '-':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = operandOne - operandTwo;
                        valueStack.push(result);
                        break;

                    case '*':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = operandOne * operandTwo;
                        valueStack.push(result);
                        break;

                    case '/':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = operandOne / operandTwo;
                        valueStack.push(result);
                        break;

                    case '^':
                        operandTwo = valueStack.pop();
                        operandOne = valueStack.pop();
                        result = (int) Math.pow(operandOne, operandTwo);
                        valueStack.push(result);
                        break;

                    default:
                        break; // ignore unexpected characters
                }
                i++;
            }
            return valueStack.peek();
        }
    }
}
