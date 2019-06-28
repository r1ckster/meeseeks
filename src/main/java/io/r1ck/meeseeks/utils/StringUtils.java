/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.utils;

public final class StringUtils {
    private StringUtils() {
    }
    
    /**
     * Titlecases the given string.
     * Example: myString becomes Mystring
     *
     * @param input input string
     * @return titlecased string
     */
    public static String titlecase(final String input) {
        return input.toUpperCase().charAt(0) + input.substring(1).toLowerCase();
    }
}
