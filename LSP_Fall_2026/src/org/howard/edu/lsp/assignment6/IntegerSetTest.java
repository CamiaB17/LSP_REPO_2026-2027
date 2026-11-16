package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class IntegerSetTest {

    /** Helper: builds a fresh set containing the given values. */
    private static IntegerSet setOf(int... values) {
        IntegerSet s = new IntegerSet();
        for (int v : values) {
            s.add(v);
        }
        return s;
    }

    /** Helper: asserts set equality via IntegerSet.equals(IntegerSet). */
    private static void assertSetEquals(IntegerSet expected, IntegerSet actual) {
        assertTrue(expected.equals(actual),
                "Expected " + expected + " but was " + actual);
    }

    // ===== clear() =====

    @Test
    public void testClearClearsPopulatedSet() {
        IntegerSet s = setOf(1, 2, 3);
        s.clear();
        assertTrue(s.isEmpty());
        assertEquals(0, s.length());
        assertEquals("[]", s.toString());
        assertFalse(s.contains(1));
    }

    @Test
    public void testClearClearsEmptySet() {
        IntegerSet s = new IntegerSet();
        s.clear();
        assertTrue(s.isEmpty());
        assertEquals(0, s.length());
        assertEquals("[]", s.toString());
    }

    // ===== length() =====

    @Test
    public void testLengthCountsUniqueElements() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(3);
        assertEquals(3, s.length());
    }

    @Test
    public void testLengthEmptySetLength() {
        assertEquals(0, new IntegerSet().length());
    }

    // ===== equals() =====

    @Test
    public void testEqualsIgnoresInsertionOrder() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 1, 2);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertSetEquals(a, b);
    }

    @Test
    public void testEqualsDetectsDifferentContents() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(1, 2, 4);
        assertEquals(a.length(), b.length());
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    public void testEqualsEmptySetsEqual() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void testEqualsSameObjectAndDifferentSizes() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(1, 2);
        assertTrue(a.equals(a));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    // ===== contains() =====

    @Test
    public void testContainsPresentAndAbsent() {
        IntegerSet s = setOf(5, 10, 15);
        assertTrue(s.contains(10));
        assertFalse(s.contains(11));
    }

    @Test
    public void testContainsEmptySetContainsNothing() {
        IntegerSet s = new IntegerSet();
        assertFalse(s.contains(0));
        assertFalse(s.contains(42));
        assertFalse(s.contains(-7));
    }

    // ===== largest() =====

    @Test
    public void testLargestNegativeAndPositiveValues() {
        IntegerSet mixed = setOf(-5, 0, 7, -20, 3);
        assertEquals(7, mixed.largest());
        IntegerSet allNegative = setOf(-9, -3, -7);
        assertEquals(-3, allNegative.largest());
    }

    @Test
    public void testLargestEmptySetThrowsException() {
        IntegerSet s = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> s.largest());
    }

    // ===== smallest() =====

    @Test
    public void testSmallestNegativeAndPositiveValues() {
        IntegerSet mixed = setOf(-5, 0, 7, -20, 3);
        assertEquals(-20, mixed.smallest());
        IntegerSet allPositive = setOf(9, 3, 7);
        assertEquals(3, allPositive.smallest());
    }

    @Test
    public void testSmallestEmptySetThrowsException() {
        IntegerSet s = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> s.smallest());
    }

    // ===== add() =====

    @Test
    public void testAddIgnoresDuplicates() {
        IntegerSet s = new IntegerSet();
        s.add(4);
        s.add(4);
        s.add(4);
        assertEquals(1, s.length());
        assertTrue(s.contains(4));
        assertEquals("[4]", s.toString());
    }

    @Test
    public void testAddHandlesIntegerBoundaries() {
        IntegerSet s = new IntegerSet();
        s.add(Integer.MIN_VALUE);
        s.add(Integer.MAX_VALUE);
        s.add(0);
        assertEquals(3, s.length());
        assertTrue(s.contains(Integer.MIN_VALUE));
        assertTrue(s.contains(Integer.MAX_VALUE));
        assertTrue(s.contains(0));
        assertEquals(Integer.MIN_VALUE, s.smallest());
        assertEquals(Integer.MAX_VALUE, s.largest());
        assertEquals("[-2147483648, 0, 2147483647]", s.toString());
    }

    // ===== remove() =====

    @Test
    public void testRemoveRemovesExistingElement() {
        IntegerSet s = setOf(1, 2, 3);
        s.remove(2);
        assertFalse(s.contains(2));
        assertTrue(s.contains(1));
        assertTrue(s.contains(3));
        assertEquals(2, s.length());
        assertEquals("[1, 3]", s.toString());
    }

    @Test
    public void testRemoveIgnoresAbsentElement() {
        IntegerSet s = setOf(1, 2, 3);
        s.remove(99);
        assertEquals(3, s.length());
        assertEquals("[1, 2, 3]", s.toString());
        assertSetEquals(setOf(1, 2, 3), s);
    }

    // ===== union() =====

    @Test
    public void testUnionOverlappingSets() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4, 5);
        IntegerSet result = a.union(b);
        assertEquals(5, result.length());
        assertEquals("[1, 2, 3, 4, 5]", result.toString());
        assertSetEquals(setOf(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testUnionEmptyAndSameOperand() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet empty = new IntegerSet();
        assertSetEquals(setOf(1, 2, 3), a.union(empty));
        assertSetEquals(setOf(1, 2, 3), empty.union(a));
        assertSetEquals(setOf(1, 2, 3), a.union(a));
        assertEquals(3, a.union(a).length());
        assertTrue(empty.union(new IntegerSet()).isEmpty());
    }

    @Test
    public void testUnionDoesNotMutateInputs() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4, 5);
        IntegerSet result = a.union(b);
        assertSetEquals(setOf(1, 2, 3), a);
        assertSetEquals(setOf(3, 4, 5), b);
        assertNotSame(a, result);
        assertNotSame(b, result);

        result.add(100);
        result.remove(1);
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[3, 4, 5]", b.toString());
        assertFalse(a.contains(100));
        assertFalse(b.contains(100));
    }

    // ===== intersect() =====

    @Test
    public void testIntersectOverlappingSets() {
        IntegerSet a = setOf(1, 2, 3, 4);
        IntegerSet b = setOf(3, 4, 5, 6);
        IntegerSet result = a.intersect(b);
        assertEquals(2, result.length());
        assertEquals("[3, 4]", result.toString());
    }

    @Test
    public void testIntersectDisjointAndSameOperand() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(4, 5, 6);
        assertTrue(a.intersect(b).isEmpty());
        assertEquals("[]", a.intersect(b).toString());
        assertSetEquals(setOf(1, 2, 3), a.intersect(a));
        assertSetEquals(setOf(1, 2, 3), a);
    }

    @Test
    public void testIntersectIndependentResult() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(2, 3, 4);
        IntegerSet result = a.intersect(b);
        assertNotSame(a, result);
        assertNotSame(b, result);

        result.add(99);
        result.remove(2);
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[2, 3, 4]", b.toString());
    }

    // ===== diff() =====

    @Test
    public void testDiffDirectionMatters() {
        IntegerSet a = setOf(1, 2, 3, 4);
        IntegerSet b = setOf(3, 4, 5);
        assertEquals("[1, 2]", a.diff(b).toString());
        assertEquals("[5]", b.diff(a).toString());
        assertFalse(a.diff(b).equals(b.diff(a)));
    }

    @Test
    public void testDiffEmptyAndSameOperand() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet empty = new IntegerSet();
        assertSetEquals(setOf(1, 2, 3), a.diff(empty));
        assertTrue(empty.diff(a).isEmpty());
        assertTrue(a.diff(a).isEmpty());
        assertSetEquals(setOf(1, 2, 3), a);
    }

    @Test
    public void testDiffIndependentResult() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4);
        IntegerSet result = a.diff(b);
        assertEquals("[1, 2]", result.toString());
        assertNotSame(a, result);

        result.add(50);
        result.remove(1);
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[3, 4]", b.toString());
    }

    // ===== complement() =====

    @Test
    public void testComplementReverseDifference() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4, 5);
        assertEquals("[4, 5]", a.complement(b).toString());
        assertEquals("[1, 2]", b.complement(a).toString());
        assertSetEquals(b.diff(a), a.complement(b));
    }

    @Test
    public void testComplementEmptyOperands() {
        IntegerSet empty = new IntegerSet();
        IntegerSet b = setOf(1, 2);
        assertSetEquals(setOf(1, 2), empty.complement(b));
        assertTrue(b.complement(new IntegerSet()).isEmpty());
        assertEquals("[1, 2]", b.toString());
    }

    @Test
    public void testComplementIndependentResult() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4, 5);
        IntegerSet result = a.complement(b);
        assertNotSame(a, result);
        assertNotSame(b, result);

        result.add(77);
        result.remove(4);
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[3, 4, 5]", b.toString());
    }

    // ===== isSubsetOf() =====

    @Test
    public void testIsSubsetOfProperSubset() {
        IntegerSet small = setOf(1, 2);
        IntegerSet big = setOf(1, 2, 3, 4);
        assertTrue(small.isSubsetOf(big));
        assertFalse(big.isSubsetOf(small));
    }

    @Test
    public void testIsSubsetOfNotSubset() {
        IntegerSet a = setOf(1, 2, 9);
        IntegerSet b = setOf(1, 2, 3, 4);
        assertFalse(a.isSubsetOf(b));
    }

    @Test
    public void testIsSubsetOfEqualSets() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 2, 1);
        assertTrue(a.isSubsetOf(b));
        assertTrue(b.isSubsetOf(a));
    }

    @Test
    public void testIsSubsetOfEmptyCases() {
        IntegerSet empty = new IntegerSet();
        IntegerSet nonEmpty = setOf(1, 2);
        assertTrue(empty.isSubsetOf(nonEmpty));
        assertFalse(nonEmpty.isSubsetOf(empty));
        assertTrue(empty.isSubsetOf(new IntegerSet()));
    }

    // ===== isDisjoint() =====

    @Test
    public void testIsDisjointNoOverlap() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(4, 5, 6);
        assertTrue(a.isDisjoint(b));
        assertTrue(b.isDisjoint(a));
    }

    @Test
    public void testIsDisjointOverlap() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4, 5);
        assertFalse(a.isDisjoint(b));
        assertFalse(b.isDisjoint(a));
    }

    @Test
    public void testIsDisjointEmptyAndSameOperand() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet empty = new IntegerSet();
        assertTrue(a.isDisjoint(empty));
        assertTrue(empty.isDisjoint(a));
        assertFalse(a.isDisjoint(a));
    }

    // ===== symmetricDiff() =====

    @Test
    public void testSymmetricDiffPartiallyOverlappingSets() {
        IntegerSet a = setOf(1, 2, 3, 4);
        IntegerSet b = setOf(3, 4, 5, 6);
        IntegerSet result = a.symmetricDiff(b);
        assertEquals(4, result.length());
        assertEquals("[1, 2, 5, 6]", result.toString());
        assertSetEquals(result, b.symmetricDiff(a));
    }

    @Test
    public void testSymmetricDiffIdenticalSets() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 2, 1);
        IntegerSet result = a.symmetricDiff(b);
        assertTrue(result.isEmpty());
        assertEquals("[]", result.toString());
    }

    @Test
    public void testSymmetricDiffEmptyAndDisjointSets() {
        IntegerSet a = setOf(1, 2);
        IntegerSet empty = new IntegerSet();
        assertSetEquals(setOf(1, 2), a.symmetricDiff(empty));
        assertSetEquals(setOf(1, 2), empty.symmetricDiff(a));

        IntegerSet b = setOf(3, 4);
        assertEquals("[1, 2, 3, 4]", a.symmetricDiff(b).toString());
    }

    @Test
    public void testSymmetricDiffIndependentResult() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(3, 4, 5);
        IntegerSet result = a.symmetricDiff(b);
        assertNotSame(a, result);
        assertNotSame(b, result);

        result.add(88);
        result.remove(1);
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[3, 4, 5]", b.toString());
    }

    // ===== isEmpty() =====

    @Test
    public void testIsEmptyNewSet() {
        assertTrue(new IntegerSet().isEmpty());
    }

    @Test
    public void testIsEmptyPopulatedSet() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        assertFalse(s.isEmpty());
    }

    @Test
    public void testIsEmptyAfterClear() {
        IntegerSet s = setOf(1, 2, 3);
        assertFalse(s.isEmpty());
        s.clear();
        assertTrue(s.isEmpty());
    }

    // ===== toString() =====

    @Test
    public void testToStringAscendingOrder() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        s.add(-2);
        s.add(10);
        s.add(0);
        assertEquals("[-2, 0, 5, 10]", s.toString());
    }

    @Test
    public void testToStringEmptyFormatting() {
        assertEquals("[]", new IntegerSet().toString());
    }

    @Test
    public void testToStringExactSpacingAndDuplicates() {
        IntegerSet s = new IntegerSet();
        s.add(3);
        s.add(1);
        s.add(3);
        s.add(2);
        s.add(1);
        assertEquals("[1, 2, 3]", s.toString());
        IntegerSet single = setOf(7);
        assertEquals("[7]", single.toString());
    }

}