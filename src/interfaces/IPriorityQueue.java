package interfaces;

import common.QueueEmptyException;
import common.QueueFullException;

/**
 * Simple priority queue interface.
 *
 */
public interface IPriorityQueue {

    /**
     * Enqueues (adds) an element to the queue.
     *
     * @param element the element to be queued
     * @throws QueueFullException if there is no room in the queue for the new element
     */
    @SuppressWarnings("rawtypes")
    void enqueue(Comparable element) throws QueueFullException;

    /**
     * Dequeues the highest priority element (largest) in FIFO ordering in relation to other elements of equal priority.
     *
     * @return the element removed
     * @throws QueueEmptyException if the queue is empty
     */
    @SuppressWarnings("rawtypes")
    Comparable dequeue() throws QueueEmptyException;

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Checks whether the queue is empty.
     * @return true if the queue is empty
     */
    boolean isEmpty();

    /**
     * Removes all elements from the queue.
     */
    void clear();
}
