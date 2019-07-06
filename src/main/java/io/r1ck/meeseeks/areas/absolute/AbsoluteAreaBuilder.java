/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.absolute;

import io.r1ck.meeseeks.areas.rectangle.RectangleAreaFactory;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public final class AbsoluteAreaBuilder {
    private final AbsoluteAreaImpl area;
    
    private AbsoluteAreaBuilder(final ClientContext ctx) {
        this.area = new AbsoluteAreaImpl(ctx);
    }
    
    public AbsoluteAreaBuilder add(final Area area) {
        return this.add(area.tiles());
    }
    
    public AbsoluteAreaBuilder add(final io.r1ck.meeseeks.areas.Area area) {
        return this.add(area.tiles().collect(Collectors.toSet()));
    }
    
    public AbsoluteAreaBuilder add(final Collection<Tile> tiles) {
        this.area.add(tiles);
        return this;
    }
    
    public AbsoluteAreaBuilder add(final Tile[] tiles) {
        return this.add(Arrays.asList(tiles));
    }
    
    public AbsoluteAreaBuilder add(final Tile tile) {
        this.area.add(tile);
        return this;
    }
    
    public AbsoluteAreaBuilder addRectangular(final Tile southWest,
                                              final int width,
                                              final int height) {
        return this.add(
            RectangleAreaFactory.create(this.area.ctx, southWest, width, height)
        );
    }
    
    public AbsoluteArea build() {
        return this.area;
    }
    
    public static AbsoluteAreaBuilder init(final ClientContext ctx) {
        return new AbsoluteAreaBuilder(ctx);
    }
}
