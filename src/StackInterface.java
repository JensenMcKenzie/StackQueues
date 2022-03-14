public interface StackInterface<E> {

    /**
     * Returns true if the stack is empty; otherwise, returns false
     *
     * @return true if empty, false otherwise
     */
    public boolean empty();

    /**
     * Returns the object at the top of the stack without removing it
     *
     * @return reference (shallow copy) of object at top of stack
     */
    public E peek();

    /**
     * Returns the object at the top of the stack and removes it
     *
     * @return reference of removed object from top of stack
     */
    public E pop();

    /**
     * Pushes an item onto the top of the stack and returns the item pushed.
     *
     * @param obj object to push onto top of stack
     * @return item that was pushed
     */
    public E push(E obj);
}
