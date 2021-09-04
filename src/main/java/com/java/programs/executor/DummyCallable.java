package com.java.programs.executor;

import java.util.concurrent.Callable;

public class DummyCallable implements Callable<String> {

    private final int serialNumber;

    public DummyCallable(final int i) {
        serialNumber = i;
    }

    @Override
    public String call() throws Exception {
        return "Hello World" + serialNumber;
    }
}
