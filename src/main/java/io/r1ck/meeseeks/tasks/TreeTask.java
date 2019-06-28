/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.tasks;

import org.powerbot.script.Nameable;
import org.powerbot.script.Nillable;
import org.powerbot.script.Validatable;

public interface TreeTask extends Nameable, Nillable<TreeTask>, Validatable {
    TreeTask NIL = new NilTask();
    
    void execute();
    
    /**
     * Gets the task to execute on validation failure.
     *
     * @return failure task
     */
    TreeTask failure();
    
    boolean leaf();
    
    @Override
    default TreeTask nil() {
        return NIL;
    }
    
    /**
     * Gets the task to execute on validation success.
     *
     * @return the success task
     */
    TreeTask success();
}