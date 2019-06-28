/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks;

import io.r1ck.meeseeks.tasks.TreeTask;
import org.powerbot.script.ClientContext;
import org.powerbot.script.PollingScript;

public abstract class TreeScript<C extends ClientContext> extends PollingScript<C> {
    private TreeTask root;
    
    protected String task = TreeTask.NIL.name();
    
    @Override
    public final void poll() {
        if (this.root == null) {
            this.root = this.rootTask();
        }
        
        TreeTask cursor = this.root;
        while (!cursor.leaf()) {
            if (cursor.valid()) {
                cursor = cursor.success();
            } else {
                cursor = cursor.failure();
            }
        }
        
        this.task = cursor.name();
        
        this.ctx.controller.script().log.info(cursor.name());
        
        cursor.execute();
    }
    
    protected abstract TreeTask rootTask();
}