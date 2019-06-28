/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.player;

import io.r1ck.meeseeks.context.accessor.AbstractAccessor;
import org.powerbot.script.Condition;
import org.powerbot.script.InteractiveEntity;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Npc;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Abstract wrapper over the local player.
 */
public abstract class AbstractPlayer<C extends ClientContext> extends AbstractAccessor<C> implements Player {
    protected AbstractPlayer(final C ctx) {
        super(ctx);
    }
    
    @Override
    public void attack(final Npc target) {
        if (this.idle() || !Objects.equals(this.local().interacting(), target)) {
            this.interact(target, "Attack", () -> !target.valid() || target.healthPercent() <= 0);
        }
    }
    
    @Override
    public boolean at(final Locatable tile) {
        return this.tile().equals(tile);
    }
    
    @Override
    public double distanceTo(final Locatable coordinate) {
        return this.tile().distanceTo(coordinate);
    }
    
    @Override
    public boolean idle() {
        return this.local().idle();
    }
    
    @Override
    public void interact(final InteractiveEntity entity,
                         final String action,
                         final Callable<Boolean> condition) {
        if (!this.moving() && (!entity.inViewport() || this.distanceTo(entity) > 6)) {
            this.walk(entity);
        }
        
        if (!entity.inViewport()) {
            this.ctx.camera.turnTo(entity);
        }
        
        if (entity.interact(false, action)) {
            Condition.wait(condition, 500, 10);
        } else if (this.distanceTo(entity) > 2 && !this.moving()) {
            this.walk(entity);
        }
    }
    
    private org.powerbot.script.rt6.Player local() {
        return this.ctx.players.local();
    }
    
    @Override
    public boolean moving() {
        return this.local().inMotion();
    }
    
    @Override
    public Tile tile() {
        return this.local().tile();
    }
    
    @Override
    public boolean walk(final Locatable target) {
        return this.ctx.movement.step(target);
    }
}
