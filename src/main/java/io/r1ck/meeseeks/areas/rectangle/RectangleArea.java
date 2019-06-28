/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.areas.rectangle;

import io.r1ck.meeseeks.areas.Area;
import org.powerbot.script.Tile;

public interface RectangleArea extends Area {
    Tile northEast();
    
    Tile northWest();
    
    Tile southEast();
    
    Tile southWest();
}
