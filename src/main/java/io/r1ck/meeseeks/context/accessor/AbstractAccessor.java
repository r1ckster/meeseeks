/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.context.accessor;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

/**
 * Wrapper around the default client accessor that facilitates unit testing.
 */
public abstract class AbstractAccessor<C extends ClientContext> extends ClientAccessor<C> {
    public final C ctx;
    
    protected AbstractAccessor(final C ctx) {
        super(ctx);
        this.ctx = ctx;
    }
}