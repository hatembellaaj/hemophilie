package org.adhes.hemophilie.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPatientAllPropertiesEquals(Patient expected, Patient actual) {
        assertPatientAutoGeneratedPropertiesEquals(expected, actual);
        assertPatientAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPatientAllUpdatablePropertiesEquals(Patient expected, Patient actual) {
        assertPatientUpdatableFieldsEquals(expected, actual);
        assertPatientUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPatientAutoGeneratedPropertiesEquals(Patient expected, Patient actual) {
        assertThat(expected)
            .as("Verify Patient auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPatientUpdatableFieldsEquals(Patient expected, Patient actual) {
        assertThat(expected)
            .as("Verify Patient relevant properties")
            .satisfies(e -> assertThat(e.getPrenom()).as("check prenom").isEqualTo(actual.getPrenom()))
            .satisfies(e -> assertThat(e.getNom()).as("check nom").isEqualTo(actual.getNom()))
            .satisfies(e -> assertThat(e.getNomJeuneFille()).as("check nomJeuneFille").isEqualTo(actual.getNomJeuneFille()))
            .satisfies(e -> assertThat(e.getProfession()).as("check profession").isEqualTo(actual.getProfession()))
            .satisfies(e -> assertThat(e.getSexe()).as("check sexe").isEqualTo(actual.getSexe()))
            .satisfies(e -> assertThat(e.getDateNaissance()).as("check dateNaissance").isEqualTo(actual.getDateNaissance()))
            .satisfies(e -> assertThat(e.getAgeActuel()).as("check ageActuel").isEqualTo(actual.getAgeActuel()))
            .satisfies(e -> assertThat(e.getOrigine()).as("check origine").isEqualTo(actual.getOrigine()))
            .satisfies(e -> assertThat(e.getAutreOrigine()).as("check autreOrigine").isEqualTo(actual.getAutreOrigine()))
            .satisfies(e -> assertThat(e.getAdresse()).as("check adresse").isEqualTo(actual.getAdresse()))
            .satisfies(e -> assertThat(e.getTelephone()).as("check telephone").isEqualTo(actual.getTelephone()))
            .satisfies(e -> assertThat(e.getEtatCivil()).as("check etatCivil").isEqualTo(actual.getEtatCivil()))
            .satisfies(e -> assertThat(e.getCouvertureSociale()).as("check couvertureSociale").isEqualTo(actual.getCouvertureSociale()))
            .satisfies(e -> assertThat(e.getPrenomPere()).as("check prenomPere").isEqualTo(actual.getPrenomPere()))
            .satisfies(e -> assertThat(e.getProfessionPere()).as("check professionPere").isEqualTo(actual.getProfessionPere()))
            .satisfies(e -> assertThat(e.getNomPrenomMere()).as("check nomPrenomMere").isEqualTo(actual.getNomPrenomMere()))
            .satisfies(e -> assertThat(e.getProfessionMere()).as("check professionMere").isEqualTo(actual.getProfessionMere()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPatientUpdatableRelationshipsEquals(Patient expected, Patient actual) {
        // empty method
    }
}
