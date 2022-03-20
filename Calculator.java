public class Calculator
{
    public String convertToPostfix(String infix)
    {
        // Sanitize inputs
        if (infix.isEmpty()) {return infix;}
        else if (infix == null) {throw new NullPointerException();}
        else
        {

            // Method
            LinkedStack<Character> operatorStack = new LinkedStack<>();
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

    private int precedence(char c)
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
        return -1;
    } // end precedence

    public static void main(String[] args)
    {
        Calculator test = new Calculator();
        int temp = test.evaluatePostfix("ab*ca-de*+/");
        System.out.println(temp);
    }

    public static int evaluatePostfix(String postfix)
    {
        // Sanitize inputs
        if (postfix.isEmpty()) {return 0;}
        else if (postfix == null) {throw new NullPointerException();}
        else
        {
            StackInterface<Integer> valueStack = new LinkedStack<Integer>();

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
                        int temp = Character.getNumericValue(nextCharacter) - 8;
                        valueStack.push(temp);
                        break;

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

                    default:
                        break;
                }
                i++;
            }
            return valueStack.peek();
        }
    }
}
