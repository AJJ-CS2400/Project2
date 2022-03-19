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

    public int evaluatePostfix(String postfix)
    {
        return 0; // stub
    }
}
