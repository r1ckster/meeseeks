/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.context;

import io.r1ck.meeseeks.player.Player;
import org.powerbot.script.rt6.Varpbits;

/**
 * Wrapper around the default client context that facilitates unit testing.
 */
public interface Context<P extends Player> {
    P player();
    
    Varpbits varpbits();
}