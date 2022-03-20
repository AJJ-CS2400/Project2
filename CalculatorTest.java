
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{
    @Test
    void testConvertToPostfix()
    {
        // Arrange
        String expression1 = "a*b/(c-a)+d*e";
        String expression2 = "";        // empty
        String expression3 = null;      // null
        String expression4 = "a^a^a";
        boolean caught = false;

        // Act
        String postfix1 = Calculator.convertToPostfix(expression1);
        String postfix2 = Calculator.convertToPostfix(expression2);
        try
        {   // Catch NullPointerException error
            Calculator.convertToPostfix(expression3);
        }
        catch(NullPointerException e)
        {
            caught = true;
        }
        String postfix4 = Calculator.convertToPostfix(expression4);

        // Assert
        assertEquals("ab*ca-/de*+", postfix1);
        assertEquals("", postfix2);
        assertTrue(caught);
        assertEquals("aaa^^", postfix4);
    }

    @Test
    void testEvaluatePostfix()
    {
        // Arrange
        String expression1 = "ab*ca-/de*+";
        String expression2 = "";         // empty
        String expression3 = null;       // null
        String expression4 = "aaa^^";
        boolean caught = false;

        // Act
        int result1 = Calculator.evaluatePostfix(expression1);
        int result2 = Calculator.evaluatePostfix(expression2);
        try
        {   // Catch NullPointerException error
            Calculator.convertToPostfix(expression3);
        }
        catch(NullPointerException e)
        {
            caught = true;
        }
        int result4 = Calculator.evaluatePostfix(expression4);

        // Assert
        assertEquals(33, result1);
        assertEquals(0, result2);
        assertTrue(caught);
        assertEquals(16, result4);
    }
}