package org.cyrus.util;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayUtils {

    /**
     * Converts a specified array into a String.
     * @param split The devide between every instance
     * @param toString Converts the specified type into String
     * @param array The array to be converted
     * @param <T> The class type of the array
     * @return A string output
     */
    public static <T> String toString(String split, Function<T, String> toString, T... array){
        return toString(split, toString, Arrays.asList(array));
    }

    /**
     * Converts a specified array into a String.
     * @param split The devide between every instance
     * @param toString Converts the specified type into String
     * @param array The array to be converted
     * @param <T> The class type of the array
     * @return A string output
     */
    public static <T> String toString(String split, Function<T, String> toString, Iterable<T> array){
        String ret = null;
        for(T value : array){
            if(ret == null){
                ret = toString.apply(value);
            }else{
                ret = ret + split + toString.apply(value);
            }
        }
        return ret;
    }

    public static String[] filter(int min, int max, String... array){
        String[] arr = new String[(max + 1) - min];
        for(int A = min; A <= max; A++){
            arr[A - min] = array[A];
        }
        return arr;
    }

    public static String[] splitBy(String toSplit, int startWith, boolean combineStartWith, Predicate<Character> splitBy){
        String[] split = new String[0];
        int previousSplit = startWith;
        for(int A = startWith; A < toSplit.length(); A++){
            char character = toSplit.charAt(A);
            if(splitBy.test(character)){
                String[] newSplit = new String[split.length + 1];
                for(int B = 0; B < split.length; B++){
                    newSplit[B] = split[B];
                }
                newSplit[split.length] = toSplit.substring(previousSplit, A);
                previousSplit = A;
                split = newSplit;
            }
        }
        String[] newSplit = new String[split.length + 1];
        for(int B = 0; B < split.length; B++){
            newSplit[B] = split[B];
        }
        newSplit[split.length] = toSplit.substring(previousSplit);
        split = newSplit;
        if(split.length == 0 && combineStartWith){
            return new String[]{toSplit};
        }
        if(split.length == 0){
            return split;
        }
        if(combineStartWith){
            split[0] = toSplit.substring(0, startWith + split[0].length());
        }
        if(startWith == 0){
            return split;
        }
        newSplit = new String[split.length];
        for(int B = 0; B < split.length; B++){
            newSplit[B + 1] = split[B];
        }
        newSplit[0] = toSplit.substring(0, startWith);
        return newSplit;
    }
}
