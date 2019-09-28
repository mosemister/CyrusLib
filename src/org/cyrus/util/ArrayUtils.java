package org.cyrus.util;

import java.util.Arrays;
import java.util.function.Function;

public class ArrayUtils {

    public static <T> String toString(String split, Function<T, String> toString, T... array){
        return toString(split, toString, Arrays.asList(array));
    }

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
