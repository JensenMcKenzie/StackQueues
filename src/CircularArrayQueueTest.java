import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * CircularArrayQueueTest : a test class for a Queue interface implementation which models a circular array.
 *
 * @author King
 * @version 2.0
 */
public class CircularArrayQueueTest {

    /** Values to be added to the queue. */
    private static final String[] STRING_VALUES = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven" };

    /** The initial capacity of the queue. */
    private static final int INITIAL_CAPACITY = 5;

    /** Object derived from the Queue abstract class. */
    private Queue<String> circularQueue;

    /**
     * Helper function for test operations which utilize methods offer or add.
     *
     * @param numElements the number of elements to add to the queue
     * @param isOffer flag for using either offer (true) or add (false)
     */
    @SuppressWarnings("ManualArrayToCollectionCopy")
    private void populateQueue(int numElements, boolean isOffer) {
        if (isOffer) {
            for (int i = 0; i < numElements; i ++) {
                circularQueue.offer(STRING_VALUES[i]);
            }
        }
        else {
            for (int i = 0; i < numElements; i ++) {
                circularQueue.add(STRING_VALUES[i]);
            }
        }
    }

    /**
     * Creates a circular queue with a given initial capacity. This function is executed every single time before
     * each test run.
     */
    @Before
    public void setup() {
        // Instantiate circular array queue with an initial capacity of 5
        circularQueue = new CircularArrayQueue<String>(INITIAL_CAPACITY);
    }

    // region peek and element tests

    @Test
    public void testPeekEmpty() {
        assertEquals("Test peek failed - Call from an empty queue should return null.", null, circularQueue.peek());
    }

    @Test
    public void testElementEmpty() {
        try {
            circularQueue.element();
            fail("Test element failed - Call from an empty queue should throw a NoSuchElementException.");
        } catch (NoSuchElementException nse) { /* Test Success */ }
    }

    // endregion peek and element tests
    // region offer tests

    @Test
    public void testOfferOne() {
        populateQueue(1, true);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testOfferFew() {
        populateQueue(3, true);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testOfferAfterReallocation() {
        // Populate queue to capacity with offer
        populateQueue(INITIAL_CAPACITY, true);

        // Offer additional elements which prompt reallocation
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY]);
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY + 1]);
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY + 2]);

        // Validate with peek and element
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    // endregion offer tests
    // region add tests

    @Test
    public void testAddOne() {
        populateQueue(1, false);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testAddFew() {
        populateQueue(3, false);
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    @Test
    public void testAddAfterReallocation() {
        // Populate queue to capacity with add
        populateQueue(INITIAL_CAPACITY, false);
        // Add additional elements which prompt reallocation
        circularQueue.add(STRING_VALUES[INITIAL_CAPACITY]);
        circularQueue.add(STRING_VALUES[INITIAL_CAPACITY + 1]);
        circularQueue.add(STRING_VALUES[INITIAL_CAPACITY + 2]);
        // Validate with peek and element
        assertEquals("Test failed - Call to peek should return the head of this queue.", STRING_VALUES[0], circularQueue.peek());
        assertEquals("Test failed - Call to element should return the head of this queue.", STRING_VALUES[0], circularQueue.element());
    }

    // endregion add tests
    // region poll tests

    @Test
    public void testPollEmpty() {
        // Attempt to poll from an empty queue
        assertEquals("Test poll failed - Call from an empty queue should return null.", null, circularQueue.poll());
    }

    @Test
    public void testPollOne() {
        // Offer and poll one element
        circularQueue.offer(STRING_VALUES[0]);
        assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[0], circularQueue.poll());

        // Add and poll another element
        circularQueue.add(STRING_VALUES[1]);
        assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[1], circularQueue.poll());
    }

    @Test
    public void testPollFew() {
        // Populate queue to capacity with offer
        populateQueue(INITIAL_CAPACITY, true);

        // Poll each element from queue
        for (int i = 0; i < INITIAL_CAPACITY; i ++) {
            assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.poll());
        }
    }

    @Test
    public void testPollManyAfterReallocation() {
        // Populate queue to capacity with offer
        populateQueue(INITIAL_CAPACITY, true);

        // Add additional elements which prompt reallocation
        circularQueue.add(STRING_VALUES[INITIAL_CAPACITY]);
        circularQueue.add(STRING_VALUES[INITIAL_CAPACITY + 1]);
        circularQueue.add(STRING_VALUES[INITIAL_CAPACITY + 2]);

        // Poll each element from queue
        for (int i = 0; i < (INITIAL_CAPACITY + 3); i ++) {
            assertEquals("Test poll failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.poll());
        }

        // Queue is empty- validate with null return value
        assertEquals("Test poll failed - Call from an empty queue should return null.", null, circularQueue.poll());
    }

    // endregion poll tests
    // region remove tests

    @Test
    public void testRemoveEmpty() {
        // Attempt remove on an empty queue
        try {
            circularQueue.remove();
            fail("Test remove failed - Call from an empty queue should throw NoSuchElementException.");
        } catch (NoSuchElementException nsee) { /* Test Success */ }
    }

    @Test
    public void testRemoveOne() {
        // Offer and remove one element
        circularQueue.offer(STRING_VALUES[0]);
        assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[0], circularQueue.remove());

        // Add and remove another element
        circularQueue.add(STRING_VALUES[1]);
        assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[1], circularQueue.remove());
    }

    @Test
    public void testRemoveFew() {
        // Populate queue to capacity with offer
        populateQueue(INITIAL_CAPACITY, true);

        // Remove each element from the queue
        for (int i = 0; i < INITIAL_CAPACITY; i ++) {
            assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.remove());
        }
    }

    @Test
    public void testRemoveAfterReallocation() {
        // Populate queue to capacity with add
        populateQueue(INITIAL_CAPACITY, false);

        // Offer additional elements which prompt reallocation
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY]);
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY + 1]);
        circularQueue.offer(STRING_VALUES[INITIAL_CAPACITY + 2]);

        // Remove each element from the queue
        for (int i = 0; i < (INITIAL_CAPACITY + 3); i ++) {
            assertEquals("Test remove failed - Call should return the head of the queue.", STRING_VALUES[i], circularQueue.remove());
        }

        // Queue is empty- validate with remove exception
        try {
            circularQueue.remove();
            fail("Test remove failed - Call from an empty queue should throw NoSuchElementException.");
        } catch (NoSuchElementException nsee) { /* Test Success */ }
    }

    // endregion remove tests

} // End of class CircularArrayQueueTest