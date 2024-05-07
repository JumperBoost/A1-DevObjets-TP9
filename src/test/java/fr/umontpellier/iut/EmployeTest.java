package fr.umontpellier.iut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void test_anciennete_inferieur() {
        Employe e1 = new Employe("1", "Dupont", 1);
        e1.setDateEmbauche(LocalDate.now().minusMonths(3).plusDays(2));
        Employe e2 = new Employe("2", "Moulin", 1);
        e2.setDateEmbauche(LocalDate.now().minusMonths(3).minusDays(1));

        assert e1.getMoisAnciennete() < e2.getMoisAnciennete();
    }

    @Test
    public void test_anciennete_inferieur_un_jour() {
        Employe e1 = new Employe("1", "Dupont", 1);
        e1.setDateEmbauche(LocalDate.now().minusMonths(3).plusDays(1));
        Employe e2 = new Employe("2", "Moulin", 1);
        e2.setDateEmbauche(LocalDate.now().minusMonths(3));

        assert e1.getMoisAnciennete() < e2.getMoisAnciennete();
    }

    @Test
    public void test_anciennete_egale() {
        Employe e1 = new Employe("1", "Dupont", 1);
        e1.setDateEmbauche(LocalDate.now().minusMonths(3));
        Employe e2 = new Employe("2", "Moulin", 1);
        e2.setDateEmbauche(LocalDate.now().minusMonths(3));

        System.out.println(LocalDate.now().minusMonths(2));
        assertEquals(e1.getMoisAnciennete(), e2.getMoisAnciennete());
    }

    @Test
    public void test_anciennete_superieur() {
        Employe e1 = new Employe("1", "Dupont", 1);
        e1.setDateEmbauche(LocalDate.now().minusMonths(3).minusDays(2));
        Employe e2 = new Employe("2", "Moulin", 1);
        e2.setDateEmbauche(LocalDate.now().minusMonths(3).plusDays(1));

        assert e1.getMoisAnciennete() > e2.getMoisAnciennete();
    }

    @Test
    public void test_anciennete_superieur_un_jour() {
        Employe e1 = new Employe("1", "Dupont", 1);
        e1.setDateEmbauche(LocalDate.now().minusMonths(3));
        Employe e2 = new Employe("2", "Moulin", 1);
        e2.setDateEmbauche(LocalDate.now().minusMonths(3).plusDays(1));

        assert e1.getMoisAnciennete() > e2.getMoisAnciennete();
    }

}
