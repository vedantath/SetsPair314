/*  Student information for assignment:
 *
 *  On OUR honor, Vedant Athale and Shruthik Alle,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used:
 *
 *  Student 1 (Student whose Canvas account is being used)
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

/**
 * Students are to complete this class.
 * Students should implement as many methods
 * as they can using the Iterator from the iterator
 * method and the other methods.
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* DELETE THIS COMMENT FROM YOUR SUBMISSION.
     *
     * RECALL:
     *
     * NO INSTANCE VARIABLES ALLOWED.
     *
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     *
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     *
     * NO METHODS ADDED other than those in ISet and Object.
     */


    /**
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     *
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }

    /**
     * Make this set empty.
     * Time Complexity: O(N)
     */
    @Override
    public void clear() {
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Determine if item is contained in this set.
     * Time Complexity: O(N)
     *
     * @param item element whose presence is being tested. item != null
     * @return true if this set contains the specified item, false otherwise.
     */
    @Override
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        for (E val : this) {
            if (val.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * Time Complexity: O(N^2)
     *
     * @param otherSet != null
     * @return true if this set contains all the elements in otherSet, false otherwise.
     */
    @Override
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        for (E val : otherSet) {
            if (!this.contains(val)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if this set is equal to other. Two sets are equal if they have exactly the same
     * elements. The order of the elements does not matter.
     * Time Complexity: O(N^2) Worst Case: if the sets are equal
     *
     * @param other the object to compare to this set
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ISet<?> otherSet)) {
            return false;
        }
        if (this.size() != otherSet.size()) {
            return false;
        }
        if (other == this) {
            return true;
        }
        return this.containsAll((ISet<E>) otherSet);
    }

    /**
     * Remove the specified item from this set if it is present.
     * Time Complexity: O(N)
     *
     * @param item the item to remove from the set. item != null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    @Override
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(item)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Return the amount of elements of this set.
     * Time Complexity: O(N)
     */
    @Override
    public int size() {
        int count = 0;
        for (E val : this) {
            count++;
        }
        return count;
    }

//    /**
//     * Create a new set that is the intersection of this set and otherSet. (elements in both sets)
//     * Time Complexity: O(N^2)
//     *
//     * @param otherSet != null
//     * @return a new set that is the intersection of this set and otherSet.
//     */
//    @Override
//    public ISet<E> intersection(ISet<E> otherSet) {
//        if (otherSet == null) {
//            throw new IllegalArgumentException("otherSet cannot be null");
//        }
//        for (E val : this) {
//            if (!otherSet.contains(val)) {
//                this.remove(val);
//            }
//        }
//        return this;
//    }
    public ISet<E> union(ISet<E> other) {
        if (other == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        for (E val : this) {
            if (!other.contains(val)) {
                other.add(val);
            }
        }
        return other;
    }
}
