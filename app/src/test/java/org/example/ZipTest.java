package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//tests
public class ZipTest {

    //equal-length lists
    @Test
    void testZipEqualLengthIntegers() {
        List<Integer> list1 = List.of(1, 3, 5, 7);
        List<Integer> list2 = List.of(2, 4, 6, 8);
        List<Integer> result = Zip.zip(list1, list2);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8), result);
    }

    @Test
    void testZipEqualLengthStrings() {
        List<String> list1 = List.of("A", "C", "E");
        List<String> list2 = List.of("B", "D", "F");
        List<String> result = Zip.zip(list1, list2);
        assertEquals(List.of("A", "B", "C", "D", "E", "F"), result);
    }

    @Test
    void testZipEqualLengthDoubles() {
        List<Double> list1 = List.of(1.1, 3.3);
        List<Double> list2 = List.of(2.2, 4.4);
        List<Double> result = Zip.zip(list1, list2);
        assertEquals(List.of(1.1, 2.2, 3.3, 4.4), result);
    }

    //unequal-length lists — 1st list longer
    @Test
    void testZipFirstListLongerStrings() {
        List<String> colors1 = List.of("Red", "Green", "Blue");
        List<String> colors2 = List.of("White", "Black", "Orange", "Pink", "Fuschia");
        List<String> result = Zip.zip(colors1, colors2);
        assertEquals(
            List.of("Red", "White", "Green", "Black", "Blue", "Orange", "Pink", "Fuschia"),
            result
        );
    }

    @Test
    void testZipFirstListLongerIntegers() {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        List<Integer> list2 = List.of(10, 20);
        List<Integer> result = Zip.zip(list1, list2);
        assertEquals(List.of(1, 10, 2, 20, 3, 4, 5), result);
    }

    //unequal-length lists — 2nd list longer
    @Test
    void testZipSecondListLonger() {
        List<Integer> list1 = List.of(10, 20);
        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        List<Integer> result = Zip.zip(list1, list2);
        assertEquals(List.of(10, 1, 20, 2, 3, 4, 5), result);
    }

    //edge cases
    @Test
    void testZipFirstListEmpty() {
        List<String> list1 = List.of();
        List<String> list2 = List.of("X", "Y", "Z");
        List<String> result = Zip.zip(list1, list2);
        assertEquals(List.of("X", "Y", "Z"), result);
    }

    @Test
    void testZipSecondListEmpty() {
        List<String> list1 = List.of("X", "Y", "Z");
        List<String> list2 = List.of();
        List<String> result = Zip.zip(list1, list2);
        assertEquals(List.of("X", "Y", "Z"), result);
    }

    @Test
    void testZipBothEmpty() {
        List<Integer> list1 = List.of();
        List<Integer> list2 = List.of();
        List<Integer> result = Zip.zip(list1, list2);
        assertTrue(result.isEmpty());
    }

    @Test
    void testZipSingleElementEach() {
        List<Integer> list1 = List.of(42);
        List<Integer> list2 = List.of(99);
        List<Integer> result = Zip.zip(list1, list2);
        assertEquals(List.of(42, 99), result);
    }

    @Test
    void testZipSingleElementFirstOnly() {
        List<String> list1 = List.of("only");
        List<String> list2 = List.of();
        List<String> result = Zip.zip(list1, list2);
        assertEquals(List.of("only"), result);
    }


    //custom object type — verifies generics work with non-primitive types
    //(no int, String, etc.)
   record Point(int x, int y) {}

    @Test
    void testZipCustomObjects() {
        List<Point> list1 = List.of(new Point(0, 0), new Point(2, 2));
        List<Point> list2 = List.of(new Point(1, 1), new Point(3, 3));
        List<Point> result = Zip.zip(list1, list2);
        assertEquals(
            List.of(new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3)),
            result
        );
    }

    //output list must be independent of the inputs (no aliasing)
    @Test
    void testZipReturnsNewList() {
        List<Integer> list1 = List.of(1, 3);
        List<Integer> list2 = List.of(2, 4);
        List<Integer> result = Zip.zip(list1, list2);
        assertNotSame(list1, result);
        assertNotSame(list2, result);
    }
}
