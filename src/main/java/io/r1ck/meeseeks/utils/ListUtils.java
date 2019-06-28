/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ListUtils {
    private ListUtils() {
    }
    
    public static <T> List<T> concat(final List<T> a, final List<T> b) {
        return Stream.of(a, b)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }
    
    /**
     * Reverses the given list (not in place).
     *
     * @param input the list to reverse
     * @param <T>   type of the list
     * @return the reversed list, the input list remains unchanged
     */
    public static <T> List<T> reverse(final List<T> input) {
        final List<T> ret = new ArrayList<>(input);
        Collections.reverse(ret);
        return ret;
    }
}
