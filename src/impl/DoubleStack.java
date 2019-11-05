package impl;

import interfaces.IDoubleStack;
import interfaces.IStack;

/**
* This class implements the IDoubleStack interface.
*/
public class DoubleStack implements IDoubleStack {


  public IStack firstStack;
  public IStack secondStack;

  public DoubleStack(int maxSize) {
    Object[] stackArray = new Object[maxSize];
    AtomicInteger avaliableSpace = new AtomicInteger(maxSize);
    firstStack = new Stack(avaliableSpace, stackArray, true);
    secondStack = new Stack(avaliableSpace, stackArray, false);
  }

  /**
   * Method which returns the first IStack object in the IDoubleStack for subsequent use with {@link IStack} operations.
   * @return the first stack in the double stack
   */
  public IStack getFirstStack() {
    return firstStack;
  }

  /**
   * Method which returns the second IStack in the IDoubleStack object for subsequent use with {@link IStack} operations.
   * @return the second stack in the double stack
   */
  public IStack getSecondStack() {
    return secondStack;
  }
}
