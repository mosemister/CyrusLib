package org.cyrus.util;

import java.util.Arrays;
import java.util.function.Function;

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
}
