/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.absolute;

import io.r1ck.meeseeks.areas.rectangle.RectangleArea;
import io.r1ck.meeseeks.areas.rectangle.RectangleAreaFactory;
import org.junit.Assert;
import org.junit.Test;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

public class AbsoluteAreaTest {
    @Test
    public void testAddRectangular() {
        final Tile sw = new Tile(1, 2, 5);
        Assert.assertNotNull(sw);
        
        final int width = 10;
        final int height = 10;
        
        final RectangleArea rect = RectangleAreaFactory.create(mock(ClientContext.class),
            sw,
            width,
            height
        );
        Assert.assertNotNull(rect);
        
        final AbsoluteArea area = AbsoluteAreaBuilder.init(mock(ClientContext.class))
            .addRectangular(sw, width, height)
            .build();
        Assert.assertNotNull(area);
        Assert.assertTrue(area.contains(sw));
        
        final Collection<Tile> tiles = area.tiles().collect(Collectors.toSet());
        Assert.assertEquals(width * height, tiles.size());
        rect.tiles().forEach(tile -> Assert.assertTrue(tiles.contains(tile)));
    }
    
    @Test
    public void testTiles() {
        final Tile tile1 = new Tile(1, 2);
        Assert.assertNotNull(tile1);
        final Tile tile2 = new Tile(3, 4);
        Assert.assertNotNull(tile2);
        
        final AbsoluteArea area = AbsoluteAreaBuilder.init(mock(ClientContext.class))
            .add(tile1)
            .add(tile2)
            .build();
        Assert.assertNotNull(area);
        
        final Collection<Tile> tiles = area.tiles().collect(Collectors.toSet());
        Assert.assertNotNull(tiles);
        Assert.assertEquals(2, tiles.size());
    }
}