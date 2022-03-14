import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * StackTest : a test class for the given StackInterface implementation using an ArrayList.
 */
public class StackTest {

    /** An object which implements StackInterface. */
    private StackInterface<Integer> stack;

    /** This function is executed every single time before each test runs. */
    @Before
    public void setup() {
        stack = new ArrayListStack<Integer>();
    }

    @Test
    public void testEmpty() {
        // Verify that a call to empty() on an empty stack returns true
        assertTrue("Test Failed - Should start as empty", stack.empty());
    }

    @Test
    public void testPushAndNotEmpty() {
        // Push one item into the stack and verify that it isn't empty with empty()
        stack.push(5);
        assertFalse("Should not be empty", stack.empty());
    }

    @Test
    public void testPushAndPeekOnce() {
        // Push one item into the stack and verify that peek returns that item
        stack.push(5);
        assertEquals("Expected and actual don't match, only one value was pushed!",
                5L, ((long) stack.peek())); // 5L is a 5 but created as a long type literal
    }

    @Test
    public void testPushAndPeekMany() {
        // Push four integers, 0 through 4, onto the top of the stack and verify that peek() returns the current
        // integer following its insertion through each iteration
        for (int i = 0; i < 5; i ++) {
            stack.push(i);
            assertEquals("Expected and actual don't match, somethings wrong with pushing multiple values?",
                    ((long) i), ((long) stack.peek())); // Cast expected and actual values as longs
        }
    }

    @Test
    public void testPeekError() {
        // Attempts to call peak() on an empty stack should throw an EmptyStackException
        try {
            stack.peek();
            fail("Test Failed - Peek should have thrown EmptyStackException!");
        } catch (EmptyStackException ese) { /* Test Passed! */ }
    }

    @Test
    public void testPopOnce() {
        // Push one integer into the stack
        stack.push(71);
        assertEquals("Expected and actual don't match- something's wrong with popping or pushing one value?",
                71L, ((long) stack.pop())); // 71L is a 71 but created as a long type literal
    }

    @Test
    public void testPopMany() {
        // Push 13 integers, 50 through 63, onto the stack
        for (int i = 50; i <= 63; i ++) {
            stack.push(i);
        }

        // Values come out in reverse order!
        for (int i = 63; i >= 50; i --) {
            assertEquals("Expected and actual don't match- something's wrong with popping or pushing multiple values?",
                    ((long) i), ((long) stack.pop())); // Cast expected and actual values as longs
        }
    }

    @Test
    public void testPopError() {
        // Attempts to call pop() on an empty stack should throw an EmptyStackException
        try {
            stack.pop();
            fail("Pop should have thrown EmptyStackException!");
        } catch (EmptyStackException ese) { /* Test Passed! */ }
    }

} // End of class StackTest