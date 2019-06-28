/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.player;

import org.powerbot.script.InteractiveEntity;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.Npc;

import java.util.concurrent.Callable;

public interface Player extends Locatable {
    void attack(Npc target);
    
    boolean at(Locatable tile);
    
    double distanceTo(Locatable coordinate);
    
    boolean idle();
    
    void interact(InteractiveEntity entity, String action, Callable<Boolean> condition);
    
    boolean moving();
    
    Tile tile();
    
    boolean walk(Locatable target);
}
