package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HemorragiesCutaneoMuqueusesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static HemorragiesCutaneoMuqueuses getHemorragiesCutaneoMuqueusesSample1() {
        return new HemorragiesCutaneoMuqueuses().id(1L).frequencePerAn(1);
    }

    public static HemorragiesCutaneoMuqueuses getHemorragiesCutaneoMuqueusesSample2() {
        return new HemorragiesCutaneoMuqueuses().id(2L).frequencePerAn(2);
    }

    public static HemorragiesCutaneoMuqueuses getHemorragiesCutaneoMuqueusesRandomSampleGenerator() {
        return new HemorragiesCutaneoMuqueuses().id(longCount.incrementAndGet()).frequencePerAn(intCount.incrementAndGet());
    }
}
