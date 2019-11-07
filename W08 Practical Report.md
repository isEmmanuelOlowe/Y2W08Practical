# W08 Practical Report

## Overview

The specification has required that a program which implements a Double stack. A data structure which is two stacks which share a single array. Each stack in the Double stack array much be accessible individually and complementary stack much not interfere with the contents of the other. So there must be some way to actually share the array between the two stacks. Also a way to store both of the stack elements in the single array must be developed also away to determine how much space there is left to add elements to the stack array. To determine if the double stack has been correctly implement it must be tested that the first and second stack which are obtained are fully functional as individual stack classes.

## Design

### Double Stack

To ensure the two stacks share the same array, the most efficient way decided to store it was to give it to the constructor and store it as a private variable of both stacks so when the individual objects are obtained from the double stack they have access to the stack array.

To store both stacks in the array it was decided one stack would be stored at the being of the array and one stack at the end. So another parameter in the stack is where the stack will store at the front of back. Since only two options a Boolean is used. This was viewed as the most efficient implementation as both stacks will fulfil the functionality of a stack but just from different sides so methods which determine where the array will add to top and get elements to the top are from which. To do this a `get` and `set` method where determine using the Boolean `isFront` to determine which side which the top is for the given stack.

To determine how much space is left and to let both stacks know how much space is left `atomicInteger` was used. Essential allows the integer value of how much space is available to be accessible and editable by both stacks. The maximum value of the atom integer is the max size of the double stack. Adding an element to either stack decreases the value atom integer by 1. Popping an element to either stack increases the value of atom integer by 1. Clearing a stack adds the size of the stack onto the array as all the space is now available. 

Now that stack overflow is throw when an attempt to push to either of the stacks when there is free space in array. (`AtomInteger` when variable equals 0).

## Testing

### Double Stack

As mentioned before to determine if the Double Stack works correctly it must be determined if the Stacks they return function as stacks individual stacks. So this aspect shall be focused on heavily on in testing. Tests must be written to test for these attributes in both `IStack` implementations in Double Stack to verify they work as intended.

#### `IDoubleStack`

To determine if the double stack works correctly:

* `getFirstStack` must return a non-null implementation of `IStack`
* `getSecondStack` must return a non-null implementation of `IStack`

##### `IStack`

###### `push`

To determine if the following method functions correctly. It must be determined that:

* The object pushed to the stack can be popped from the stack.
* Pushing elements to the stack increases the size by one.
* When Item is pushed to the stack is is no longer empty.
* If there is no space left in array then you are unable to push to the stack and exception is called.

###### `pop`

To determine if the following method functions correctly. It must be determined that:

* The object popped to the stack decrease size of stack by 1
* If there are no items in stack and  `pop` function is called then Stack Empty Exception.
* Checks the Last element pushed is the last element returned.

###### `top`

To determine if the following method functions correctly. It must be determined that:

* When the top element is attempted to be obtained from empty stack empty exception is thrown.
* The most recent element pushed to the stack is returned from top.
* Size remains constant when top is obtained.
* Top remains constant when pop is obtained. (e.g. is not pushed from stack).

###### `size`

To determine if this method functions correctly. It must be determined that:

* The initial size of stack is zero
* Pushing an element increase stack size by 1.
* Popping an element decreases stack size by 1.
* Clearing the stack sets stack size to zero.
* When stack size is zero that the stack is empty.
* When stack size is not zero the stack is not empty.

###### `isEmpty`

To determine if this method functions correctly. It must be determined that:

* The initial result is that the stack is empty.
* When an element is pushed to the stack it is no longer empty.
* When stack size is zero that the stack is empty.
* When stack size is not zero the stack is not empty.
* Popping from a stack with 1 element makes the stack empty.

###### `clear`

To determine if this method functions correctly. It must be determined that:

* Stack Size is set to zero.
* Stack is now empty.
* Trying to obtain top will cause exception to be thrown.

###### Independence

It must also be shown that:

* these two stacks do not interfere with each other
  * Stack still Values are not altered by other stack. 
  * When pushing to one stack other remains empty
  * When pushing to one stack other size remains constant
  * When popping from one stack the others size remains constant
  * The top when pushing/popping remains constant in the other stack.
* Maximum space available to push to first stack is the maximum size of stack minus the size of the second size and minus the size of first stack.