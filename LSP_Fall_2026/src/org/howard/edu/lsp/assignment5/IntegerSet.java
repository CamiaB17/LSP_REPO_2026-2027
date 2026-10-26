package org.howard.edu.lsp.assignment5;

import java.util.TreeSet;

/**
 * Represents a mathematical set of integers. Duplicates are not allowed.
 * Internally backed by a sorted set, which is kept private (encapsulated).
 * Operations that return an IntegerSet always return a new, independent object
 * and never modify either input set.
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
     * Compares this set to another for equality, ignoring insertion order.
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
     * Checks whether a value is in this set.
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
     * Returns the union of this set and another.
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
     * Returns the intersection of this set and another.
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
     * Returns the difference of this set minus another.
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
     * Returns the assignment-specific complement: elements in b but not in this set.
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
     * Checks whether this set and b share no elements.
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
     * Returns the symmetric difference of this set and another.
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
     * Checks whether the set has no elements.
     * @return true if the set is empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns the elements in ascending order, e.g. [-3, 0, 8, 15]; an empty set gives [].
     * @return the string representation of this set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}