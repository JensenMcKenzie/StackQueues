import java.util.*;

public class CircularArrayQueue<E> extends AbstractQueue{
    //Instance variables for the array, front, rear, total capacity, and current number of elements
    private E[] arr;
    private int front, rear, capacity, numberOfElements;

    //Constructor, declared with size
    public CircularArrayQueue(int size) {
        //Set all instance variables to default values, update capacity to size, and create a new array
        this.capacity = size;
        this.front = 0;
        this.rear = 0;
        this.arr = (E[]) new Object[this.capacity];
        this.numberOfElements = 0;
    }

    //Necessary method for implementation of AbstractQueue, not used
    public Iterator iterator() {
        return null;
    }

    //Returns the size of the current Array Queue
    public int size() {
        return numberOfElements;
    }

    //Adds a new element to the Array Queue. If full, add expands the current Array to allow for the addition of another element
    public boolean add(Object element) {
        //Is the current array full?
        if (numberOfElements == capacity) {
            //Increase the capacity
            capacity++;
            //Create a new array with updated capacity
            E[] newArr = (E[]) new Object[this.capacity];
            //Copy all elements from arr into newArr
            System.arraycopy(arr, 0, newArr, 0, capacity - 1);
            //Set arr to newArr
            arr = newArr;
        }
        //If the array is empty, set the front and rear to 0
        if (isEmpty()) {
            front = rear = 0;
        }
        //If the rear is out of bounds, set it to 0
        if (rear > numberOfElements) {
            rear = 0;
        }
        //Set the element at rear to the new element
        arr[rear] = (E)element;
        //Increment the rear and total number of elements values
        rear++;
        this.numberOfElements++;
        //Return true, as a new element has been successfully added
        return true;
    }

    //Removes the head from the current array queue
    public E remove() {
        //Throw an error if the queue is empty
        if (isEmpty())
            throw new NoSuchElementException();
        //Create a temporary variable for the return value
        E element = arr[front];
        //Set the front to null
        arr[front] = null;
        //Increment the front
        front++;
        //Decrement the total number of elements
        this.numberOfElements--;
        //Return the removed element
        return element;
    }

    //Add o to the array
    public boolean offer(Object o) {
        add(o);
        return true;
    }

    //Removes the head from the array, returning null if the array is empty
    public Object poll() {
        //If array is empty, return null
        if (isEmpty()){
            return null;
        }
        //Create a temporary variable for the return value
        E element = arr[front];
        //Remove the head of the array
        remove();
        //Return the removed element
        return element;
    }

    //Retrieves the head of the array, if the array is empty, throw an error
    public Object element() {
        //If the current array is empty, throw an error
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        //Return the head of the array
        return arr[front];
    }

    //Retrieves the head of the array, if the array is empty, return null
    public Object peek() {
        //If the current array is empty, return null
        if (isEmpty()){
            return null;
        }
        //Return the head of the array
        return arr[front];
    }
}
