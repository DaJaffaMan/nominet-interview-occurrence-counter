package com.nominet.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LongCounterTest {

    private Producer producer = null;
    private final Counter counter = new LongCounter();

    @Test
    public void CountTopSampleFromSingleProducer() {
        producer = new Producer(10, 1);
        final String topSample = counter.getTop(1, producer);

        assertEquals("[-4964420948893066024=10]", topSample);
    }

    @Test
    public void CountTop5SamplesFromSingleProducer() {
        producer = new Producer(1000, 1);
        final String top5Samples = counter.getTop(5, producer);

        assertEquals("[-4964420948893066024=170, 7564655870752979346=109, 3831662765844904176=84, 6137546356583794141=72, -594798593157429144=61]", top5Samples);
    }

    @Test
    public void countTopSampleFromMultipleProducers() {
        producer = new Producer(10, 1);
        Producer producer1 = new Producer(10, 2);
        final String topSamples = counter.getTop(10, producer, producer1);

        assertTrue(topSamples.contains("-4964420948893066024=10"));
        assertTrue(topSamples.contains("-4959463499243013640=10"));
    }

    @Test
    public void countTop5SamplesFromMultipleProducers() {
        // should produce a single sample with 10 occurrences of a number meaning it won't be in top 5
        producer = new Producer(10, 1);
        Producer producer1 = new Producer(1000, 2);
        final String top5Samples = counter.getTop(5, producer, producer1);

        assertEquals("[-4959463499243013640=165, -1817970389778669801=114, 9164759175887871693=96, -260524486875061426=74, -2635596377894034055=56]", top5Samples);
    }
}
