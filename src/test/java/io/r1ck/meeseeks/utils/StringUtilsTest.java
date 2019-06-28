/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void testTitlecase() {
        Assert.assertEquals("Hello", StringUtils.titlecase("hello"));
        Assert.assertEquals("Hello", StringUtils.titlecase("HELLO"));
        Assert.assertEquals("Hello", StringUtils.titlecase("hELLO"));
    }
}