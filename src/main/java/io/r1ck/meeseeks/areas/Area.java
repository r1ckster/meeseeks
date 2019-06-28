/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas;

import org.powerbot.script.Drawable;
import org.powerbot.script.Filter;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;

import java.util.stream.Stream;

public interface Area extends Drawable {
    static <T extends Locatable> Filter<T> within(final Area area) {
        return area::contains;
    }
    
    Tile center();
    
    boolean contains(Locatable coordinate);
    
    /**
     * Derives a new area by offsetting all coordinates with the given delta.
     *
     * @param dx the x delta
     * @param dy the y delta
     * @return the derived area
     */
    Area offset(int dx, int dy);
    
    Stream<Tile> tiles();
}
