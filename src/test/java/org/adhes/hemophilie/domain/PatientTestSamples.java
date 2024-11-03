package org.adhes.hemophilie.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PatientTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Patient getPatientSample1() {
        return new Patient()
            .id(1L)
            .prenom("prenom1")
            .nom("nom1")
            .nomJeuneFille("nomJeuneFille1")
            .profession("profession1")
            .ageActuel(1)
            .autreOrigine("autreOrigine1")
            .adresse("adresse1")
            .telephone("telephone1")
            .prenomPere("prenomPere1")
            .professionPere("professionPere1")
            .nomPrenomMere("nomPrenomMere1")
            .professionMere("professionMere1");
    }

    public static Patient getPatientSample2() {
        return new Patient()
            .id(2L)
            .prenom("prenom2")
            .nom("nom2")
            .nomJeuneFille("nomJeuneFille2")
            .profession("profession2")
            .ageActuel(2)
            .autreOrigine("autreOrigine2")
            .adresse("adresse2")
            .telephone("telephone2")
            .prenomPere("prenomPere2")
            .professionPere("professionPere2")
            .nomPrenomMere("nomPrenomMere2")
            .professionMere("professionMere2");
    }

    public static Patient getPatientRandomSampleGenerator() {
        return new Patient()
            .id(longCount.incrementAndGet())
            .prenom(UUID.randomUUID().toString())
            .nom(UUID.randomUUID().toString())
            .nomJeuneFille(UUID.randomUUID().toString())
            .profession(UUID.randomUUID().toString())
            .ageActuel(intCount.incrementAndGet())
            .autreOrigine(UUID.randomUUID().toString())
            .adresse(UUID.randomUUID().toString())
            .telephone(UUID.randomUUID().toString())
            .prenomPere(UUID.randomUUID().toString())
            .professionPere(UUID.randomUUID().toString())
            .nomPrenomMere(UUID.randomUUID().toString())
            .professionMere(UUID.randomUUID().toString());
    }
}
