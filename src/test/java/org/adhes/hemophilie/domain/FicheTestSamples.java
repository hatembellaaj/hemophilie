package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FicheTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Fiche getFicheSample1() {
        return new Fiche()
            .id(1L)
            .dossierNumber("dossierNumber1")
            .ordreNumber("ordreNumber1")
            .indexNumber("indexNumber1")
            .anneeDiagnostic(1)
            .autreDiagnostic("autreDiagnostic1")
            .degreParenteConsanguins("degreParenteConsanguins1")
            .nbreCasSimilaires(1)
            .degreParenteCasSimilaires("degreParenteCasSimilaires1")
            .nbreCasDeces(1)
            .nbreFreres(1)
            .nbreSoeurs(1)
            .plaquettes(1)
            .ageDiagnostic(1)
            .causePriseEnCharge("causePriseEnCharge1")
            .doseProphylaxie("doseProphylaxie1")
            .frequenceProphylaxie(1)
            .age1ereSubstitution(1)
            .typeHandicap("typeHandicap1")
            .typeActiviteSportive("typeActiviteSportive1")
            .causeDateDeces("causeDateDeces1");
    }

    public static Fiche getFicheSample2() {
        return new Fiche()
            .id(2L)
            .dossierNumber("dossierNumber2")
            .ordreNumber("ordreNumber2")
            .indexNumber("indexNumber2")
            .anneeDiagnostic(2)
            .autreDiagnostic("autreDiagnostic2")
            .degreParenteConsanguins("degreParenteConsanguins2")
            .nbreCasSimilaires(2)
            .degreParenteCasSimilaires("degreParenteCasSimilaires2")
            .nbreCasDeces(2)
            .nbreFreres(2)
            .nbreSoeurs(2)
            .plaquettes(2)
            .ageDiagnostic(2)
            .causePriseEnCharge("causePriseEnCharge2")
            .doseProphylaxie("doseProphylaxie2")
            .frequenceProphylaxie(2)
            .age1ereSubstitution(2)
            .typeHandicap("typeHandicap2")
            .typeActiviteSportive("typeActiviteSportive2")
            .causeDateDeces("causeDateDeces2");
    }

    public static Fiche getFicheRandomSampleGenerator() {
        return new Fiche()
            .id(longCount.incrementAndGet())
            .dossierNumber(UUID.randomUUID().toString())
            .ordreNumber(UUID.randomUUID().toString())
            .indexNumber(UUID.randomUUID().toString())
            .anneeDiagnostic(intCount.incrementAndGet())
            .autreDiagnostic(UUID.randomUUID().toString())
            .degreParenteConsanguins(UUID.randomUUID().toString())
            .nbreCasSimilaires(intCount.incrementAndGet())
            .degreParenteCasSimilaires(UUID.randomUUID().toString())
            .nbreCasDeces(intCount.incrementAndGet())
            .nbreFreres(intCount.incrementAndGet())
            .nbreSoeurs(intCount.incrementAndGet())
            .plaquettes(intCount.incrementAndGet())
            .ageDiagnostic(intCount.incrementAndGet())
            .causePriseEnCharge(UUID.randomUUID().toString())
            .doseProphylaxie(UUID.randomUUID().toString())
            .frequenceProphylaxie(intCount.incrementAndGet())
            .age1ereSubstitution(intCount.incrementAndGet())
            .typeHandicap(UUID.randomUUID().toString())
            .typeActiviteSportive(UUID.randomUUID().toString())
            .causeDateDeces(UUID.randomUUID().toString());
    }
}
