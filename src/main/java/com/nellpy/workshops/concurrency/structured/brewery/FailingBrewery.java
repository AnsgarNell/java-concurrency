package com.nellpy.workshops.concurrency.structured.brewery;

public class FailingBrewery extends Brewery {

    @Override
    protected void processInventory() {
        printMessage("This brewery is going to fail!");
        throw new RuntimeException("Brewery failed!");
    }
}
