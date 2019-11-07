package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.StackOverflowException;
import common.StackEmptyException;
import interfaces.IDoubleStack;
import interfaces.IStack;

/**
 * Tests array collection implementation.
 */
public class ArrayDoubleStackTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1, "Failure: IFactory.makeDoubleStack returns null, expected non-null IDoubleStack object");
    }

    /**
    * Tests that the Double Stack constructs a non-null IStack.
    */
    @Test
    public void doubleStackFirstStackIsNonNull() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack;
      assertNotNull(firstStack, "Failure: IDoubleStack.getFirstStack returns null, expected non-null IStack object");
    }

    /**
    * Tests that the Double Stack constructs a non-null IStack.
    */
    @Test
    public void doubleStackSecondStackIsNonNull() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      assertNotNull(secondStack, "Failure: IDoubleStack.getSecondStack returns null, expected non-null IStack object");
    }

    /**
    * Tests that the Double Stack first Stack allows the adding of objects without throwing an exceptions
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleStackAddToFirstStack() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      firstStack.push("element");
    }

    /**
    * Tests that the Double Stack second Stack allows the adding of objects without throwing an exceptions
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleStackAddToSecondStack() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      secondStack.push("element");
    }

    /**
    * Tests the pushing element to the first stack increases the stack size by 1.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleFirstStackPushIncreasesSize() throws StackOverflowException {
      final int size = 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      firstStack.push("element");
      assertEquals(size, firstStack.size());
    }

    /**
    * Tests the pushing element to the second stack increases the stack size by 1.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleSecondStackPushIncreasesSize() throws StackOverflowException {
      final int size = 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      firstStack.push("element");
      assertEquals(size, secondStack.size());
    }

    /**
    * Tests the first stack is empty on creation.
    */
    @Test
    public void doubleStackFirstStackInitialEmpty() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      assertTrue(firstStack.isEmpty());
    }

    /**
    * Tests the second stack is empty on creation.
    */
    @Test
    public void doubleStackSecondStackInitialEmpty() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      assertTrue(secondStack.isEmpty());
    }

    /**
    * Tests the first stack is not empty after an element has been pushed to it.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleStackFirstStackNotEmptyAfterPush() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      firstStack.push("elemenet");
      assertFalse(firstStack.isEmpty());
    }

    /**
    * Tests the second stack is not empty after an element has been pushed to it.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleStackSecondStackNotEmptyAfterPush() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      secondStack.push("element");
      assertFalse(secondStack.isEmpty());
    }

    /**
    * Tests that if there is no space left in first stack an attempt is make to push exception is thrown.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleStackFirstStackPushingWhenFullForbidden() throws StackOverflowException {
      final int maxSize = 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(maxSize);
      IStack firstStack = doubleStack.getFirstStack();
      //This throws checked exception so method must still throw StackOverflowException
      firstStack.push("First Element");
      assertThrows(StackOverflowException.class, () -> {
        firstStack.push("Second Element");
      });
    }

    /**
    * Tests that if there is no space left in second stack an attempt is make to push exception is thrown.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    @Test
    public void doubleStackSecondStackPushingWhenFullForbidden() throws StackOverflowException {
      final int maxSize = 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(maxSize);
      IStack secondStack = doubleStack.getSecondStack();
      //This throws checked exception so method must still throw StackOverflowException
      secondStack.push("First Element");
      assertThrows(StackOverflowException.class, () -> {
        secondStack.push("Second Element");
      });
    }

    /**
    * Tests that you are able to pop elements from a non-empty first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstStackPopping() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      firstStack.push("element");
      firstStack.pop();
    }

    /**
    * Tests that you are able to pop elements from a non-empty second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSecondStackPopping() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      secondStack.push("element");
      secondStack.pop();
    }

    /**
    * Tests that size decrease by 1 after pushing elements to first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstStackPush() throws StackEmptyException, StackOverflowException {
      final int oneLessThanMax = DEFAULT_MAX_SIZE - 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        firstStack.push(new Integer(i));
      }
      firstStack.pop();
      assertEquals(oneLessThanMax, firstStack.size());
    }

    /**
    * Tests that size decrease by 1 after pushing elements to second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSecondStackPush() throws StackEmptyException, StackOverflowException {
      final int oneLessThanMax = DEFAULT_MAX_SIZE - 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        secondStack.push(new Integer(i));
      }
      secondStack.pop();
      assertEquals(oneLessThanMax, secondStack.size());
    }

    /**
    * Tests that element popped is the most recent element pushed in first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstStackPopReturnsLastPush() throws StackEmptyException, StackOverflowException {
      final int oneLessThanMax = DEFAULT_MAX_SIZE - 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < oneLessThanMax; i++) {
        firstStack.push(new Integer(i));
      }
      Integer LastPush = new Integer(oneLessThanMax);
      firstStack.push(LastPush);
      assertEquals(LastPush, firstStack.pop());
    }

    /**
    * Tests that element popped is the most recent element pushed in second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSecondStackPopReturnsLastPush() throws StackEmptyException, StackOverflowException {
      final int oneLessThanMax = DEFAULT_MAX_SIZE - 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < oneLessThanMax; i++) {
        secondStack.push(new Integer(i));
      }
      Integer LastPush = new Integer(oneLessThanMax);
      secondStack.push(LastPush);
      assertEquals(LastPush, secondStack.pop());
    }

    /**
    * Tests that the element popped is the element on the top of the first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstStackPopReturnsTop() throws StackEmptyException, StackOverflowException {
      final int oneLessThanMax = DEFAULT_MAX_SIZE - 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        firstStack.push(new Integer(i));
      }
      Object top = firstStack.top();
      assertEquals(top, firstStack.pop());
    }

    /**
    * Tests that the element popped is the element on the top of the second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSecondStackPopReturnsTop() throws StackEmptyException, StackOverflowException {
      final int oneLessThanMax = DEFAULT_MAX_SIZE - 1;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        secondStack.push(new Integer(i));
      }
      Object top = secondStack.top();
      assertEquals(top, secondStack.pop());
    }


    /**
    * Tests that popping when empty causing exception for first stack.
    */
    @Test
    public void doubleStackFirstPoppingEmptyException() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      assertThrows(StackEmptyException.class, () -> {
        firstStack.pop();
      });

    }

    /**
    * Tests that popping when empty causing exception for second stack.
    */
    @Test
    public void doubleStackSecondPoppingEmptyException() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      assertThrows(StackEmptyException.class, () -> {
        secondStack.pop();
      });

    }

    /**
    * Checks size remains constant after top obtained from first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstStackSizeConstantAfterGettingTop() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        firstStack.push(new Integer(i));
      }
      int sizeBeforeGettingTop = firstStack.size();
      firstStack.top();
      assertEquals(sizeBeforeGettingTop, firstStack.size());
    }

    /**
    * Checks size remains constant after top obtained from second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSecondStackSizeConstantAfterGettingTop() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        secondStack.push(new Integer(i));
      }
      int sizeBeforeGettingTop = secondStack.size();
      secondStack.top();
      assertEquals(sizeBeforeGettingTop, secondStack.size());
    }

    /**
    * Trying to get top from empty first stack causes exception thrown.
    */
    @Test
    public void doubleStackFirstTopThrowsExceptionWhenEmpty() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      assertThrows(StackEmptyException.class, () -> {
        firstStack.top();
      });
    }

    /**
    * Trying to get top from empty second stack causes exception thrown.
    */
    @Test
    public void doubleStackSecondTopThrowsExceptionWhenEmpty() {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      assertThrows(StackEmptyException.class, () -> {
        secondStack.top();
      });
    }

    /**
    * Checks the most recent items pushed is top in first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    public void doubleStackFirstStackTopIsMostRecent() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      firstStack.push("first element");
      String mostRecenet = "most recent push";
      firstStack.push(mostRecenet);
      assertEquals(mostRecenet, firstStack.top());
    }

    /**
    * Checks the most recent items pushed is top in second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    public void doubleStackSecondStackTopIsMostRecent() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      secondStack.push("first element");
      String mostRecenet = "most recent push";
      secondStack.push(mostRecenet);
      assertEquals(mostRecenet, secondStack.top());
    }

    /**
    * checks clearing the first stack makes size zero.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    public void doubleStackFirstStackClearingMakesSizeZero() throws StackEmptyException, StackOverflowException {
      final int sizeZero = 0;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        firstStack.push(new Integer(i));
      }
      firstStack.clear();
      assertEquals(sizeZero, firstStack.size());
    }

    /**
    * checks clearing the second stack makes size zero.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    public void doubleStackSecondStackClearingMakesSizeZero() throws StackEmptyException, StackOverflowException {
      final int sizeZero = 0;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        secondStack.push(new Integer(i));
      }
      secondStack.clear();
      assertEquals(sizeZero, secondStack.size());
    }

    /**
    * checks clearing the first stack makes it empty.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    public void doubleStackFirstStackClearingMakesEmpty() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        firstStack.push(new Integer(i));
      }
      firstStack.clear();
      assertTrue(firstStack.isEmpty());
    }

    /**
    * checks clearing the second stack makes it empty.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    public void doubleStackSecondStackClearingMakesEmpty() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        secondStack.push(new Integer(i));
      }
      secondStack.clear();
      assertTrue(secondStack.isEmpty());
    }

    /**
    * Getting Top from cleared first stack will cause exception.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    public void doubleStackFirstStackClearingMakesTopForbidden() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        firstStack.push(new Integer(i));
      }
      firstStack.clear();
      assertThrows(StackEmptyException.class, () -> {
        firstStack.top();
      });
    }

    /**
    * Getting Top from cleared second stack will cause exception.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    */
    public void doubleStackSecondStackClearingMakesTopForbidden() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
        secondStack.push(new Integer(i));
      }
      secondStack.clear();
      assertThrows(StackEmptyException.class, () -> {
        secondStack.top();
      });
    }

    /**
    * Popping from a first stack with only 1 element will cause stakc to become empty.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstPoppingMakesEmpty() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      firstStack.push("element");
      firstStack.pop();
      assertTrue(firstStack.isEmpty());
    }

    /**
    * Popping from a second stack with only 1 element will cause stakc to become empty.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSecondPoppingMakesEmpty() throws StackEmptyException, StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack secondStack = doubleStack.getSecondStack();
      secondStack.push("element");
      secondStack.pop();
      assertTrue(secondStack.isEmpty());
    }

    /**
    * Pushing to first stack second stack remains empty.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.#
    */
    @Test
    public void doubleStackFirstStackPushDoesntEffectSecondStack() throws StackOverflowException {
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      firstStack.push("element");
      assertTrue(secondStack.isEmpty());
    }

    /**
    * Pushing to Second stack first stack remains empty.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.#
    */
    @Test
    public void doubleStackSecondStackPushDoesntEffectFirstStack() throws StackOverflowException{
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      secondStack.push("element");
      assertTrue(firstStack.isEmpty());
    }

    /**
    * Pushing to Second Stack does not effect Size of First Stack
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.#
    */
    @Test
    public void doubleStackPushingToFirstStackdoesntEffectSecondStackSize() throws StackOverflowException {
      final int elements = 5;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < elements; i++) {
        firstStack.push(new Integer(i));
      }
      secondStack.push(new Integer(elements));
      assertequals(elements, firstStack.size());
    }

    /**
    * Pushing to First Stack does not effect Size of Second Stack
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.#
    */
    @Test
    public void doubleStackPushingToSecondStackdoesntEffectFirstStackSize() throws StackOverflowException {
      final int elements = 5;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < elements; i++) {
        secondStack.push(new Integer(i));
      }
      firstStack.push(new Integer(elements));
      assertequals(elements, SecondStack.size());
    }

    /**
    * When Popping from first stack second Stack size remains constant
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackPoppingFromFirstStackSecondStackRemainsConstant() throws StackEmptyException, StackOverflowException {
      final int elements = 5;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      firstStack.pop();
      assertEquals(elements, secondStack.size());
    }

    /**
    * When Popping from Second stack First Stack size remains constant
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackPoppingFromSecondStackFirstStackRemainsConstant() throws StackEmptyException, StackOverflowException {
      final int elements = 5;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i < elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      secondStack.pop();
      assertEquals(elements, firstStack.size());
    }

    /**
    * Top remains constant in Second Stack after pushing to First stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackPushingToFirstStackSecondStackTopConstant() throws StackEmptyException, StackOverflowException {
      final int elements = 4;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i <= elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      firstStack.push("element");
      assertEquals(new Integer(elements), secondStack.top());
    }

    /**
    * Top remains constant in First Stack after pushing to Second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackPushingToSecondStackFirstStackTopConstant() throws StackEmptyException, StackOverflowException {
      final int elements = 4;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i <= elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      secondStack.push("element");
      assertEquals(new Integer(elements), firstStack.top());
    }

    /**
    * Top remains constant in Second Stack after popping from First stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackPoppingFromFirstStackSecondStackTopConstant() throws StackEmptyException, StackOverflowException {
      final int elements = 4;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i <= elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      firstStack.pop();
      assertEquals(new Integer(elements), secondStack.top());
    }

    /**
    * Top remains constant in First Stack after popping from Second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackPoppingFromSecondStackFirstStackTopConstant() throws StackEmptyException, StackOverflowException {
      final int elements = 4;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i <= elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      secondStack.pop();
      assertEquals(new Integer(elements), firstStack.top());
    }

    /**
    * Exception called when pushing a value above double stack limit to first stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackFirstStackCauseExceptionAboveMax() throws StackOverflowException {
      final int elements = 5;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i <= elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      assertThrows(StackOverflowException.class, () -> {
        firstStack.push("element");
      });
    }

    /**
    * Exception called when pushing a value above double stack limit to Second stack.
    *
    * @throws StackOverflowException in event an element in pushed to stack when there is no free space.
    * @throws StackEmptyException in event attempt is made to pop from empty stack.
    */
    @Test
    public void doubleStackSeconStackCauseExceptionAboveMax() throws StackOverflowException {
      final int elements = 5;
      IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
      IStack firstStack = doubleStack.getFirstStack();
      IStack secondStack = doubleStack.getSecondStack();
      for (int i = 0; i <= elements; i++) {
        firstStack.push(new Integer(i));
        secondStack.push(new Integer(i));
      }
      assertThrows(StackOverflowException.class, () -> {
        secondStack.push("element");
      });
    }
}
