package org.howard.edu.lsp.assignment5;

import java.util.TreeSet;

/**
 * Represents a set of integers with no duplicate values.
 */
public class IntegerSet {
    private final TreeSet<Integer> set = new TreeSet<>();

    /** Removes all elements from this set. */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     * @return the count of unique elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Checks if this set contains the same elements as another set.
     * @param b the set to compare with
     * @return true if both sets contain exactly the same elements
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        return set.equals(b.set);
    }

    /**
     * Checks if the set contains a value.
     * @param value the value to look for
     * @return true if the value is present
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element.
     * @return the largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return set.last();
    }

    /**
     * Returns the smallest element.
     * @return the smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return set.first();
    }

    /**
     * Adds an item to the set; duplicates are ignored.
     * @param item the value to add
     */
    public void add(int item) {
        set.add(item);
    }

    /**
     * Removes an item from the set; has no effect if the item is absent.
     * @param item the value to remove
     */
    public void remove(int item) {
        set.remove(item);
    }

    /**
     * Returns a new set containing the elements from both sets.
     * @param b the other set
     * @return a new set containing elements in either set
     */
    public IntegerSet union(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        result.set.addAll(b.set);
        return result;
    }

    /**
     * Returns a new set containing elements found in both sets.
     * @param b the other set
     * @return a new set containing elements found in both sets
     */
    public IntegerSet intersect(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        result.set.retainAll(b.set);
        return result;
    }

    /**
     * Returns the elements in this set that are not in b.
     * @param b the other set
     * @return a new set containing elements in this set but not in b
     */
    public IntegerSet diff(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        result.set.removeAll(b.set);
        return result;
    }

    /**
     * Returns the elements in b that are not in this set.
     * @param b the other set
     * @return a new set containing elements in b but not in this set
     */
    public IntegerSet complement(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(b.set);
        result.set.removeAll(set);
        return result;
    }

    /**
     * Checks whether every element of this set is also in b.
     * An empty set is a subset of every set.
     * @param b the possible superset
     * @return true if this set is a subset of b
     */
    public boolean isSubsetOf(IntegerSet b) {
        return b.set.containsAll(set);
    }

    /**
     * Checks if the two sets have no elements in common.
     * @param b the other set
     * @return true if the sets are disjoint
     */
    public boolean isDisjoint(IntegerSet b) {
        for (int value : set) {
            if (b.set.contains(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a new set containing elements that are in only one of the sets.
     * @param b the other set
     * @return a new set containing elements that occur in exactly one of the two sets
     */
    public IntegerSet symmetricDiff(IntegerSet b) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(set);
        result.set.addAll(b.set);
        TreeSet<Integer> common = new TreeSet<>(set);
        common.retainAll(b.set);
        result.set.removeAll(common);
        return result;
    }

    /**
     * Checks if the set is empty.
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns the elements in ascending order
     * @return the string representation of this set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}