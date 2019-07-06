/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.absolute;

import io.r1ck.meeseeks.areas.Area;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class AbsoluteAreaImpl extends ClientAccessor implements AbsoluteArea {
    private Tile center;
    private final Collection<Tile> tiles;
    
    AbsoluteAreaImpl(final ClientContext ctx, final Collection<Tile> tiles) {
        super(ctx);
        this.tiles = new HashSet<>(20);
        this.tiles.addAll(tiles);
        
        this.center = null;
    }
    
    AbsoluteAreaImpl(final ClientContext ctx) {
        this(ctx, Collections.emptySet());
    }
    
    AbsoluteAreaImpl add(final Collection<Tile> tiles) {
        this.tiles.addAll(tiles);
        this.center = null;
        return this;
    }
    
    AbsoluteAreaImpl add(final Tile tile) {
        this.tiles.add(tile);
        this.center = null;
        return this;
    }
    
    private Tile calculateCenter() {
        final int avgX = (int) this.tiles.stream().mapToInt(Tile::x).average()
            .orElse(0.0);
        final int avgY = (int) this.tiles.stream().mapToInt(Tile::y).average()
            .orElse(0.0);
        final int floor = this.tiles.stream().mapToInt(Tile::floor).findAny()
            .orElse(0);
        
        final Tile actualCenter = new Tile(avgX, avgY, floor);
        
        if (this.tiles.contains(actualCenter)) {
            return actualCenter;
        }
        
        return this.tiles.stream().min((o1, o2) -> (int) o1.distanceTo(o2))
            .orElse(actualCenter);
    }
    
    @Override
    public Tile center() {
        if (this.center == null) {
            this.center = this.calculateCenter();
        }
        return this.center;
    }
    
    @Override
    public boolean contains(final Locatable coordinate) {
        return this.tiles.contains(coordinate.tile());
    }
    
    @Override
    public void draw(final Graphics graphics, final int alpha) {
        this.tiles.forEach(tile -> tile.matrix(this.ctx).draw(graphics, alpha));
    }
    
    @Override
    public void draw(final Graphics graphics) {
        this.draw(graphics, 255);
    }
    
    @Override
    public Area offset(final int dx, final int dy) {
        final Collection<Tile> offsetTiles = this.tiles.stream()
            .map(tile -> tile.derive(dx, dy))
            .collect(Collectors.toSet());
        return new AbsoluteAreaImpl(this.ctx, offsetTiles);
    }
    
    @Override
    public Stream<Tile> tiles() {
        return this.tiles.stream();
    }
    
    @Override
    public String toString() {
        final Tile center = this.center();
        return String.format("AbsoluteArea{center=(%d, %d), tiles=%d}",
            center.x(), center.y(),
            this.tiles.size()
        );
    }
}
