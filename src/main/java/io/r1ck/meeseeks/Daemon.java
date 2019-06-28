/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks;

public interface Daemon extends Runnable {
    String name();
    
    void start();
    
    void stop();
}
