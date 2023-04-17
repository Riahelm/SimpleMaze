package code.util.impl;

import code.util.api.Counter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CounterImplTest {

    Counter testCounter;
    @Before
    public void setUp(){
        testCounter = new CounterImpl(0);
    }

    @Test
    public void testUsage(){

        assertEquals(0, testCounter.getValue());
        testCounter.increment();
        testCounter.increment();
        assertEquals(2, testCounter.getValue());

        testCounter = new CounterImpl(5);
        assertEquals(5, testCounter.getValue());

        testCounter.increment();
        assertEquals(6, testCounter.getValue());
    }

}