package code.util.impl;

import code.util.api.Counter;

public class CounterImpl implements Counter {

    private int counterValue;

    public CounterImpl(int initialValue){
        this.counterValue = initialValue;
    }
    public CounterImpl(){
        this(0);
    }

    @Override
    public void increment() {
        this.counterValue += 1;
    }

    @Override
    public int getValue() {
        return counterValue;
    }
}
