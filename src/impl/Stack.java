package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

/**
* Implements the IStack Interface
*/
public class Stack implements IStack {

  int maxSize;
  int currentSize;
  AtomicInteger avaliableSpace;
  Object[] stack;

  /**
  * Creates a new stack object.
  *
  * @param size The maximum size of the stack
  */
  public Stack (AtomicInteger avaliableSpace, Object[] stack, boolean isFront) {
    this.maxSize = avaliableSpace.get();
    this.avaliableSpace = avaliableSpace;
    this.stack = stack;
    this.isFront = isFront;
  }

  /**
   * Pushes an element onto the stack.
   *
   * @param element the element to be pushed
   * @throws StackOverflowException if there is no room on the stack for the new element
   */
  public void push(Object element) throws StackOverflowException {
    if (avaliableSpace.get() == 0) {
      throw new StackOverflowException();
    }
    else {
      set(element);
      currentSize++;
      avaliableSpace.set(avaliableSpace.get() - 1);
    }
  }

  /**
   * Pops an element from the stack.
   *
   * @return the popped element
   * @throws StackEmptyException if the stack is empty
   */
  public Object pop() throws StackEmptyException {
    if (currentSize == 0) {
      throw new StackEmptyException();
    }
    Object element = get();
    currentSize--;
    avaliableSpace.set(avaliableSpace.get() + 1);
    return element;
  }

  /**
   * Accesses the top element on the stack without removing it.
   *
   * @return the top element
   * @throws StackEmptyException if the stack is empty
   */
  public Object top() throws StackEmptyException {
    if (currentSize == 0) {
      throw new StackEmptyException();
    }
    return get();
  }

  /**
   * Returns the number of elements on the stack.
   * @return the number of elements on the stack
   */
  public int size() {
    return currentSize;
  }

  /**
   * Checks whether the stack is empty.
   * @return true if the stack is empty
   */
  public boolean isEmpty() {
    return (currentSize == 0)? true: false;
  }

  /**
   * Removes all elements from the stack.
   */
  public void clear() {
    avaliableSpace.set(avaliableSpace.get() + currentSize);
    currentSize = 0;
  }

  /**
  * Gets the element on the top of the stack.
  *
  * @return the element on the top
  */
  public Object get() {
    if (isFront) {
      return stack[currentSize - 1];
    }
    else {
      return stack[(maxSize - currentSize) - 1];
    }
  }

  /**
  * Puts the element on the top of the stack.
  *
  * @param element The elmenet being added to the top.
  */
  private void set(Object element) {
    if (isFront) {
      stack[currentSize] = element;
    }
    else {
      stack[maxSize - currentSize] = element;
    }
  }
}
