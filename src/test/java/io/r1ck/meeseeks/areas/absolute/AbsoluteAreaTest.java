/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.absolute;

import org.junit.Assert;
import org.junit.Test;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

public class AbsoluteAreaTest {
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