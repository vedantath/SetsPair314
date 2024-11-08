/*  Student information for assignment:
 *
 *  On OUR honor, Vedant Athale and Srishruthik Alle,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: vba252
 *  email address: vedant.athale@gmail.com
 *  Grader name:
 *  Section number:
 *
 *  Student 2
 *  UTEID: SA59576
 *  email address: shruthik.alle@utexas.edu
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    // Instance variable
    private ArrayList<E> myCon;

    // UnsortedSet Constructor: Creates an empty UnsortedSet
    public UnsortedSet() {
        myCon = new ArrayList<E>();
    }

    /**
     * Add an item to this set.
     * Time Complexity: O(N)
     *
     * @param item the item to be added to this set. item != null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        if (myCon.contains(item)) {
            return false;
        }
        myCon.add(item);
        return true;
    }

    /**
     * Add all items of otherSet that are not already present in this set to this set.
     * Time Complexity: O(N^2)
     *
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        boolean setChanged = false;
        for (E item : otherSet) {
            if (!myCon.contains(item)) {
                myCon.add(item);
                setChanged = true;
            }
        }
        return setChanged;
    }

    /**
     * Create a new set that is the difference of this set and otherSet. (In this but not in other)
     * Time Complexity: O(N^2)
     *
     * @param otherSet != null
     * @return a new set that is the difference of this set and otherSet.
     */
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        ISet<E> result = new UnsortedSet<E>();
        for (E val : myCon) {
            if (!otherSet.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /**
     * Create a new set that is the intersection of this set and otherSet. (elements in both sets)
     * Time Complexity: O(N^2)
     * @param otherSet != null
     * @return a new set that is the intersection of this set and otherSet.
     */
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        ISet<E> result = new UnsortedSet<E>();
        for (E val : myCon) {
            if (otherSet.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /* Return an Iterator object for the elements of this set.
       Time Complexity: O(1) */
    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    /* Return the size of this set.
       Time Complexity: O(1) */
    public int size() {
        return myCon.size();
    }
}
