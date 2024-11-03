package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HemarthroseTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Hemarthrose getHemarthroseSample1() {
        return new Hemarthrose().id(1L).siege("siege1").frequencePerAn(1);
    }

    public static Hemarthrose getHemarthroseSample2() {
        return new Hemarthrose().id(2L).siege("siege2").frequencePerAn(2);
    }

    public static Hemarthrose getHemarthroseRandomSampleGenerator() {
        return new Hemarthrose()
            .id(longCount.incrementAndGet())
            .siege(UUID.randomUUID().toString())
            .frequencePerAn(intCount.incrementAndGet());
    }
}
