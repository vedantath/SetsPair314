/*  Student information for assignment:
 *
 *  On OUR honor, Vedant Athale and Srishruthik Alle,
 *  this programming assignment is OUR own work
 *  and WE have not provided this code to any other student.
 *
 *  Number of slip days used:
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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 * <p>
 * The data type for E must be a type that implements Comparable.
 * <p>
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    // Instance variable
    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<E>();
    }

    /**
     * Create a copy of other that is sorted.<br>
     * Time Complexity: O(N log N)
     *
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        myCon = new ArrayList<E>();
        for (E item : other) {
            this.add(item);
        }
        mergeSort(myCon);
    }

    // Merge sort algorithm --> O(N log N)
    // code for merge sort from class slides
    private void mergeSort(ArrayList<E> data) {
        if (data.size() > 1 && data != null) {
            ArrayList<E> temp = new ArrayList<E>(data.size());
            for (int i = 0; i < data.size(); i++) {
                temp.add(null); // Add null placeholders to match size
            }
            sort(data, temp, 0, data.size() - 1); //kick off the merge sort recursion
        }
    }

    private void sort(ArrayList<E> data, ArrayList<E> temp, int low, int high) {
        if (low < high) {
            int center = (low + high) / 2;
            sort(data, temp, low, center); //sort left half
            sort(data, temp, center + 1, high); //sort right half
            merge(data, temp, low, center + 1, high); //merge the sorted halves
        }
    }

    // Merges the sorted sub-ArrayLists into a single sorted ArrayList --> O(N)
    private void merge(ArrayList<E> data, ArrayList<E> temp, int leftPos, int rightPos,
                       int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        //main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (data.get(leftPos).compareTo(data.get(rightPos)) <= 0) {
                temp.set(tempPos, data.get(leftPos));
                leftPos++;
            } else {
                temp.set(tempPos, data.get(rightPos));
                rightPos++;
            }
            tempPos++;
        }
        //copy rest of left half
        while (leftPos <= leftEnd) {
            temp.set(tempPos, data.get(leftPos));
            tempPos++;
            leftPos++;
        }
        //copy rest of right half
        while (rightPos <= rightEnd) {
            temp.set(tempPos, data.get(rightPos));
            tempPos++;
            rightPos++;
        }
        //copy temp back into data
        for (int i = 0; i < numElements; i++, rightEnd--) {
            data.set(rightEnd, temp.get(rightEnd));
        }
    }

    /**
     * Merge two sub-SortedSets into a single sorted set.
     *
     * @param set1 the first sorted set to be merged
     * @param set2 the second sorted set to be merged
     * @return a new ISet that contains all the elements of set1 and set2 and sorted
     */
    private ISet<E> mergeSets(SortedSet<E> set1, SortedSet<E> set2) {
        ArrayList<E> data = set1.myCon;
        ArrayList<E> temp = set2.myCon;
        ArrayList<E> result = new ArrayList<>();
        int leftPos = 0;
        int rightPos = 0;

        //main loop
        while (leftPos < data.size() && rightPos < temp.size()) {
            // if element in data is less than or equal to element in temp, add to result
            if (data.get(leftPos).compareTo(temp.get(rightPos)) <= 0) {
                result.add(data.get(leftPos));
                leftPos++; //increment left index
            }
            //element in temp is less than element in data, add to result, increment right index
            else {
                result.add(temp.get(rightPos));
                rightPos++;
            }
        }
        // copy remaining elements from data and temp into result
        while (leftPos < data.size()) {
            result.add(data.get(leftPos));
            leftPos++;
        }

        while (rightPos < temp.size()) {
            result.add(temp.get(rightPos));
            rightPos++;
        }
        // copy result into resultSet internal container
        ISet<E> resultSet = new SortedSet<>();
        for (E item : result) {
            resultSet.add(item);
        }
        return resultSet;
    }

    private ArrayList<E> merge(ArrayList<E> listA, ArrayList<E> listB) {
        ArrayList<E> res = new ArrayList<>();
        int left = 0; //left pointer index
        int right = 0; //right pointer index
        while (left < listA.size() && right < listB.size()) {
            if (listA.get(left).compareTo(listB.get(right)) <= 0) {
                res.add(listA.get(left));
                left++;
            } else {
                res.add(listA.get(right));
                right++;
            }
        }
        while (left < listA.size()) {
            res.add(listA.get(left));
            left++;
        }
        while (right < listB.size()) {
            res.add(listB.get(right));
            right++;
        }
        return res;
    }

    /**
     * Create a new set that is the intersection of setA and setB.
     *
     * @param setA the first sorted set to be intersected
     * @param setB the second sorted set to be intersected
     * @return a new ISet that contains all the elements that are in both setA and setB
     */
    private ISet<E> intersectionHelper(SortedSet<E> setA, SortedSet<E> setB) {
        ArrayList<E> res = new ArrayList<>();
        ArrayList<E> aList = setA.myCon;
        ArrayList<E> bList = setB.myCon;
        int left = 0; //left pointer index
        int right = 0; //right pointer index
        //main loop
        while (left < aList.size() && right < bList.size()) {
            // If elements are equal, add to result and increment both pointers
            if (aList.get(left).compareTo(bList.get(right)) == 0) {
                res.add(aList.get(left));
                left++;
                right++;
            } else if (aList.get(left).compareTo(bList.get(right)) < 0) { //
                left++;
            } else {
                right++;
            }
        }
        // copy result into resultSet internal container
        ISet<E> resultSet = new SortedSet<>();
        for (E item : res) {
            resultSet.add(item);
        }
        return resultSet;
    }

    /**
     * Create a new set that is the difference of setA and setB. A-B (elements in A, but not in B)
     *
     * @param setA
     * @param setB
     * @return a new ISet that contains all the elements that are in setA but not in setB
     */
    private ISet<E> differenceHelper(SortedSet<E> setA, SortedSet<E> setB) {
        ArrayList<E> res = new ArrayList<>();
        ArrayList<E> aList = setA.myCon;
        ArrayList<E> bList = setB.myCon;
        int left = 0; //left pointer index
        int right = 0; //right pointer index
        //main loop
        while (left < aList.size() && right < bList.size()) {
            // If elements are equal, skip over and increment both index pointers
            if (aList.get(left).compareTo(bList.get(right)) == 0) {
                left++;
                right++;
            }
            // If element in aList is less than element in bList, add to result and increment left
            else if (aList.get(left).compareTo(bList.get(right)) < 0) {
                res.add(aList.get(left));
                left++;
            }
            // Element in bList smaller, increment right index
            else {
                right++;
            }
        }
        // copy remaining elements from aList into result (elements not in bList)
        while (left < aList.size()) {
            res.add(aList.get(left));
            left++;
        }
        // copy result into resultSet internal container
        ISet<E> resultSet = new SortedSet<>();
        for (E item : res) {
            resultSet.add(item);
        }
        return resultSet;
    }

    // Binary search algorithm --> O(log N)
    // code for binary search from class slides
    private int binarySearch(ArrayList<E> data, E tgt) {
        int result = -1;
        int low = 0;
        int high = data.size() - 1;
        while (result == -1 && low <= high) {
            int mid = low + ((high - low) / 2);
            int compareResult = tgt.compareTo(data.get(mid));
            if (compareResult == 0)
                result = mid;
            else if (compareResult > 0)
                low = mid + 1;
            else
                high = mid - 1; //compareResult < 0
        }
        return result;
    }

    // Override methods that can be done more efficiently

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
        int index = 0;
        while (index < myCon.size() && myCon.get(index).compareTo(item) < 0) {
            index++;
        }
        myCon.add(index, item);
        return true;
    }

    /**
     * Add all items of otherSet that are not already present in this set to this set.
     * Time Complexity: O(N) if the otherSet is a SortedSet, O(N^2) otherwise
     *
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        boolean setChanged = false;
        // If otherSet is a SortedSet, use merge method --> O(N)
        if (otherSet instanceof SortedSet) {
            if (!this.equals(otherSet)) {
                SortedSet<E> other = (SortedSet<E>) otherSet;
                if (otherSet.size() > 0) {
                    this.myCon = merge(this.myCon, other.myCon);
                }
                setChanged = true;
            }
        }
        // If otherSet is not SortedSet, add elements one by one then perform mergeSort --> O(N^2)
        else {
            for (E item : otherSet) {
                if (!myCon.contains(item)) {
                    myCon.add(item);
                    setChanged = true;
                }
            }
            mergeSort(myCon);
        }
        return setChanged;
    }

    /**
     * Determine if item is in this set.
     * Time Complexity: O(log N)
     *
     * @param item element whose presence is being tested. item != null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        return binarySearch(this.myCon, item) != -1; //Perform Binary Search algorithm
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * Time Complexity: O(N) if otherSet is a SortedSet, O(N^2) Worst case this set does contain
     * all the elements of the other set
     *
     * @param otherSet != null
     * @return true if this set contains all the elements in otherSet, false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        if (otherSet instanceof SortedSet<E> o) {
            int index = this.myCon.size() - 1;
            while (index >= 0 && o.size() > 0) {
                if (myCon.get(index).compareTo(o.myCon.get(o.size() - 1)) == 0) {
                    o.myCon.remove(o.size() - 1);
                }
                index--;
            }
            return o.size() == 0;
        }
        return super.containsAll(otherSet);
    }

    /**
     * Determine if this set is equal to other. Two sets are equal if they have exactly the same
     * elements. The order of the elements does not matter.
     * Time Complexity: O(N) if other is a SortedSet, O(N^2) otherwise
     *
     * @param other the object to compare to this set
     */
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ISet<?> otherSet)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (this.size() != otherSet.size()) {
            return false;
        }
        // If otherSet is a SortedSet, compare elements one by one in order --> O(N)
        if (otherSet instanceof SortedSet<?>) {
            SortedSet<E> o = (SortedSet<E>) otherSet;
            for (int i = 0; i < this.myCon.size(); i++) {
                if (!this.myCon.get(i).equals(o.myCon.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(otherSet); // otherSet not SortedSet, use AbstractSet equals -> O(N^2)
    }

    /**
     * Create a new set that is the difference of this set and otherSet.
     * Time Complexity: O(N) if otherSet is a SortedSet
     *
     * @param otherSet != null
     * @return a new set that is the difference of this set and otherSet.
     */
    @Override
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        SortedSet<E> other;
        if (!(otherSet instanceof SortedSet<?>)) {
            other = new SortedSet<>(otherSet);  // O(N log N)
        } else {
            other = (SortedSet<E>) otherSet;
        }

        ISet<E> result = new SortedSet<>();
        result = differenceHelper(this, other);
        return result;
    }

    /**
     * Create a new set that is the intersection of this set and otherSet. (elements in both sets)
     * Time Complexity: O(N) if otherSet is a SortedSet
     *
     * @param otherSet != null
     * @return a new set that is the intersection of this set and otherSet.
     */
    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        SortedSet<E> other;
        if (!(otherSet instanceof SortedSet<?>)) {
            other = new SortedSet<>(otherSet);  // O(N log N)
        } else {
            other = (SortedSet<E>) otherSet;
        }

        ISet<E> result = new SortedSet<>();
        result = intersectionHelper(this, other);
        return result;
    }

    /**
     * Create a new set that is the union of this set and otherSet. (elements in either set)
     * Time Complexity: O(N) if otherSet is a SortedSet, otherwise O(N log N)
     *
     * @param otherSet != null
     * @return a new set that is the union of this set and otherSet.
     */
    @Override
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("otherSet cannot be null");
        }
        SortedSet<E> other;
        if (!(otherSet instanceof SortedSet<?>)) {
            other = new SortedSet<>(otherSet);  // O(N log N)
        } else {
            other = (SortedSet<E>) otherSet;
        }
        ISet<E> result = new SortedSet<>();
        result = mergeSets(this, (SortedSet<E>) otherSet);
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

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     *
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        if (size() == 0) {
            throw new IllegalStateException("size cannot be 0");
        }
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     *
     * @return the largest element in this SortedSet.
     */
    public E max() {
        if (size() == 0) {
            throw new IllegalStateException("size cannot be 0");
        }
        return myCon.get(size() - 1);
    }

}
