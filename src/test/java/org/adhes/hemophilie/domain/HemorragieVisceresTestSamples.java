package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class HemorragieVisceresTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static HemorragieVisceres getHemorragieVisceresSample1() {
        return new HemorragieVisceres().id(1L).examenLesion("examenLesion1");
    }

    public static HemorragieVisceres getHemorragieVisceresSample2() {
        return new HemorragieVisceres().id(2L).examenLesion("examenLesion2");
    }

    public static HemorragieVisceres getHemorragieVisceresRandomSampleGenerator() {
        return new HemorragieVisceres().id(longCount.incrementAndGet()).examenLesion(UUID.randomUUID().toString());
    }
}
