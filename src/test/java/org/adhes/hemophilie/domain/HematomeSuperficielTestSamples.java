package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class HematomeSuperficielTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static HematomeSuperficiel getHematomeSuperficielSample1() {
        return new HematomeSuperficiel().id(1L).siege("siege1");
    }

    public static HematomeSuperficiel getHematomeSuperficielSample2() {
        return new HematomeSuperficiel().id(2L).siege("siege2");
    }

    public static HematomeSuperficiel getHematomeSuperficielRandomSampleGenerator() {
        return new HematomeSuperficiel().id(longCount.incrementAndGet()).siege(UUID.randomUUID().toString());
    }
}
