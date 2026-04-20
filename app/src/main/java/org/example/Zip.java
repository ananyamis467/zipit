package org.example;

import java.util.ArrayList;
import java.util.List;

//driver
public class Zip { 
    public static <T> List<T> zip(List<T> list1, List<T> list2) { //for generic lists 
        List<T> result = new ArrayList<>();
        int minSize = Math.min(list1.size(), list2.size());

        for (int i = 0; i < minSize; i++) {
            result.add(list1.get(i));
            result.add(list2.get(i));
        }

        //append any leftover elements from the longer list
        result.addAll(list1.subList(minSize, list1.size()));
        result.addAll(list2.subList(minSize, list2.size()));

        return result;
    }

    public static void main(String[] args) {
        //ex. 1: Equal-length integer lists
        List<Integer> nums1 = List.of(1, 3, 5, 7);
        List<Integer> nums2 = List.of(2, 4, 6, 8);
        List<Integer> mergedNumbers = zip(nums1, nums2);
        System.out.println(mergedNumbers); // [1, 2, 3, 4, 5, 6, 7, 8]

        //ex. 2: Unequal-length string lists
        List<String> colors1 = List.of("Red", "Green", "Blue");
        List<String> colors2 = List.of("White", "Black", "Orange", "Pink", "Fuschia");
        List<String> mergedWords = zip(colors1, colors2);
        System.out.println(mergedWords); // [Red, White, Green, Black, Blue, Orange, Pink, Fuschia]
    }
}
