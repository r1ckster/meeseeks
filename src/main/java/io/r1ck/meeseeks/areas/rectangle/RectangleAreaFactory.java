/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.rectangle;

import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

public final class RectangleAreaFactory {
    private RectangleAreaFactory() {
    }
    
    public RectangleArea create(final ClientContext ctx, final Tile southWest,
                         final int width, final int height) {
        return new RectangleAreaImpl(ctx, southWest, width, height);
    }
}
