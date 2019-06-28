/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.context;

import io.r1ck.meeseeks.player.Player;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Varpbits;

/**
 * Wrapper around the default client context that facilitates unit testing.
 */
public abstract class AbstractContext<P extends Player> extends ClientContext implements Context<P> {
    private final P player;
    
    protected AbstractContext(final ClientContext ctx) {
        super(ctx);
        this.player = this.getPlayer();
    }
    
    protected abstract P getPlayer();
    
    @Override
    public P player() {
        return this.player;
    }
    
    public Varpbits varpbits() {
        return this.varpbits;
    }
}