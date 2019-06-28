/*
 * Copyright (c) 2019. All rights reserved.
 *
 * @author r1ck
 */
package io.r1ck.meeseeks.tasks;

public final class NilTask implements TreeTask {
    @Override
    public void execute() {
    
    }
    
    @Override
    public TreeTask failure() {
        return this;
    }
    
    @Override
    public boolean leaf() {
        return true;
    }
    
    @Override
    public String name() {
        return "No task";
    }
    
    @Override
    public TreeTask success() {
        return this;
    }
    
    @Override
    public boolean valid() {
        return true;
    }
}