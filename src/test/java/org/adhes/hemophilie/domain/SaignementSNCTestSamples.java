package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class SaignementSNCTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SaignementSNC getSaignementSNCSample1() {
        return new SaignementSNC().id(1L);
    }

    public static SaignementSNC getSaignementSNCSample2() {
        return new SaignementSNC().id(2L);
    }

    public static SaignementSNC getSaignementSNCRandomSampleGenerator() {
        return new SaignementSNC().id(longCount.incrementAndGet());
    }
}
