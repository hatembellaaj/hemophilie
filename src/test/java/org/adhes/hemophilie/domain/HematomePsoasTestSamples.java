package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class HematomePsoasTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static HematomePsoas getHematomePsoasSample1() {
        return new HematomePsoas().id(1L);
    }

    public static HematomePsoas getHematomePsoasSample2() {
        return new HematomePsoas().id(2L);
    }

    public static HematomePsoas getHematomePsoasRandomSampleGenerator() {
        return new HematomePsoas().id(longCount.incrementAndGet());
    }
}
