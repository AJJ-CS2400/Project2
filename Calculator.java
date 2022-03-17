public class Calculator
{
    public String convertToPostfix(String infix)
    {
        // Sanitize inputs
        // stub

        // Method
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";
        char nextCharacter;
        int infixParse = 0;
        char topOperator;

        while (infixParse < infix.length())
        {
            nextCharacter = infix.charAt(infixParse);

            switch (nextCharacter)
            {
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
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
                default: break; // ignore unexpected characters
            }

            infixParse++;
        }

        return null; // stub
    } // end convertToPostfix

    private int precedence(char c)
    {
        switch (c) {
            case '+':
            case '-':
                return 1;
            break;
            case '*':
            case '/':
                return 2;
            break;
            case '^':
                return 3;
            break;
        }
        return -1;
    } // end precedence
}
