package common;

/**
 * This class represents objects which can be assigned a given priority when they are instantiated.
 * You can use instances of this class to test whether the priority queue implementation preserves
 * FIFO ordering when different object with the same priority value are enqueued.
 * @author jonl
 *
 */
public class PriorityObject implements Comparable<PriorityObject> {

    private int priority;

    /**
     * Constructor for a Priority Object.
     * @param priority the priority to use for this object
     */
    public PriorityObject(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityObject other) {
        return this.priority - other.priority;
    }

}
