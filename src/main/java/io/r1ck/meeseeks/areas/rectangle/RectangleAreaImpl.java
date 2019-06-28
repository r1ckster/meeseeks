/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.rectangle;

import io.r1ck.meeseeks.areas.Area;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

final class RectangleAreaImpl extends ClientAccessor implements RectangleArea {
    private final Tile northEast;
    private final Tile northWest;
    private final Tile southEast;
    private final Tile southWest;
    
    private final int height;
    private final int width;
    
    private final Collection<Tile> tiles;
    
    RectangleAreaImpl(final ClientContext ctx, final Tile southWest,
                      final int width, final int height) {
        super(ctx);
        this.height = height;
        this.northEast = southWest.derive(width - 1, height - 1);
        this.northWest = southWest.derive(0, height - 1);
        this.southEast = southWest.derive(width - 1, 0);
        this.southWest = southWest;
        this.tiles = new HashSet<>(width * height);
        this.width = width;
    }
    
    @Override
    public Tile center() {
        return new Tile(
            this.northWest.x() + ((this.northEast.x() - this.northWest.x()) / 2),
            this.southEast.y() + ((this.northEast.y() - this.southEast.y()) / 2)
        );
    }
    
    @Override
    public boolean contains(final Locatable coordinate) {
        final int cx = coordinate.tile().x();
        final int cy = coordinate.tile().y();
        
        return cx <= this.northEast.x() && this.northWest.x() <= cx
            && cy <= this.northEast.y() && this.southEast.y() <= cy;
    }
    
    @Override
    public void draw(final Graphics graphics, final int alpha) {
        this.northEast.matrix(this.ctx).draw(graphics, alpha);
        this.northWest.matrix(this.ctx).draw(graphics, alpha);
        this.southEast.matrix(this.ctx).draw(graphics, alpha);
        this.southWest.matrix(this.ctx).draw(graphics, alpha);
    }
    
    @Override
    public void draw(final Graphics graphics) {
        this.draw(graphics, 255);
    }
    
    @Override
    public Tile northEast() {
        return this.northEast;
    }
    
    @Override
    public Tile northWest() {
        return this.northWest;
    }
    
    @Override
    public Area offset(final int dx, final int dy) {
        return new RectangleAreaImpl(
            this.ctx,
            this.southWest.derive(dx, dy),
            this.width,
            this.height
        );
    }
    
    @Override
    public Tile southEast() {
        return this.southEast;
    }
    
    @Override
    public Tile southWest() {
        return this.southWest;
    }
    
    @Override
    public Stream<Tile> tiles() {
        final int minX = this.southWest.x();
        final int minY = this.southWest.y();
        final int maxX = this.southEast.x();
        final int maxY = this.northWest.y();
        
        if (this.tiles.isEmpty()) {
            for (int x = minX; x <= maxX; ++x) {
                for (int y = minY; y <= maxY; ++y) {
                    this.tiles.add(new Tile(x, y));
                }
            }
        }
        return this.tiles.stream();
    }
    
    @Override
    public String toString() {
        return String.format("RectangleArea{sw=(%d, %d), ne=(%d, %d), width=%d, height=%d}",
            this.southWest.x(), this.southWest.y(),
            this.northEast.x(), this.northEast.y(),
            this.width, this.height
        );
    }
}
