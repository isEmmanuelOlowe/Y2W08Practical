package impl;

import interfaces.IDoubleStack;
import interfaces.IFactory;
import interfaces.IPriorityQueue;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    /**
    * Creates a new IDoubleStack object
    *
    * @return the object.
    */
    @Override
    public IDoubleStack makeDoubleStack(int maxSize) {
        return new DoubleStack(maxSize);
    }

    @Override
    public IPriorityQueue makePriorityQueue(int maxSize) {
        // TODO need to implement this
        return null;
    }

}
