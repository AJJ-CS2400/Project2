
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

        // Assert
        assertEquals("ab*ca-/de*+", postfix1);
        assertEquals("", postfix2);
        assertTrue(caught);
    }

    @Test
    void testEvaluatePostfix()
    {
        // Arrange
        String expression1 = "ab*ca-/de*+";
        String expression2 = "";         // empty
        String expression3 = null;       // null
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

        // Assert
        assertEquals(33, result1);
        assertEquals(0, result2);
        assertTrue(caught);
    }
}