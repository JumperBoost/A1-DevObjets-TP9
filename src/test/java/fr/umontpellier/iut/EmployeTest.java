package fr.umontpellier.iut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeTest extends BaseTest {
    private Employe e;
    private static Map<String, Integer> distances;

    @BeforeEach
    void init() {
        e = creerEmployeTest("");
        distances = getAttribute(new GestionDistances(), "distances");
    }

    public static Employe creerEmployeTest(String nb) {
        return new Employe(nb, "test"+nb, 0);
    }

    // @Disabled
    @Test
    void test_equals_meme_nom_et_insee() {
        Employe e2 = new Employe("", "test", 0);
        assertEquals(e, e2);
    }

    // @Disabled
    @Test
    void test_equals_meme_nom_only() {
        Employe e2 = new Employe("2", "test", 0);
        assertNotEquals(e, e2);
    }

    //@Disabled
    @Test
    void test_equals_meme_insee_only() {
        Employe e2 = new Employe("1", "test2", 0);
        assertNotEquals(e, e2);
    }

    // @Disabled
    @Test
    void test_equals_all_diff() {
        Employe e2 = new Employe("2", "test2", 2);
        assertNotEquals(e, e2);
    }

    // @Disabled
    @Test
    void test_equals_meme_ref() {
        Employe e2 =  e;
        assertEquals(e, e2);
    }

    // @Disabled
    @Test
    void test_comparator_nom_insee_meme_nom_et_insee() {
        Employe e2 = new Employe("", "test", 1);
        assertEquals(0, Employe.getComparatorNomInsee().compare(e, e2));
    }

    // @Disabled
    @Test
    void test_comparator_nom_e1_sup_e2_meme_insee() {
        Employe e2 = new Employe("1", "aest", 1);
        assertTrue(Employe.getComparatorNomInsee().compare(e, e2) > 0);
    }

    // @Disabled
    @Test
    void test_comparator_meme_nom_e1_insee_inf_e2() {
        Employe e2 = new Employe("2", "test", 1);
        assertTrue(Employe.getComparatorNomInsee().compare(e, e2) > 0);
    }

    // @Disabled
    @Test
    void test_getMoisAnciennete_1_mois_exact() {
        Employe e = creerEmployeTest("");
        e.setDateEmbauche(LocalDate.now().minusMonths(1));

        assertEquals(1, e.getMoisAnciennete());
    }

    // @Disabled
    @Test
    void test_getMoisAnciennete_2_mois_exact() {
        Employe e = creerEmployeTest("");
        e.setDateEmbauche(LocalDate.now().minusMonths(2));

        assertEquals(2, e.getMoisAnciennete());
    }

    // @Disabled
    @Test
    void test_getMoisAnciennete_1_an_exact() {
        Employe e = creerEmployeTest("");
        e.setDateEmbauche(LocalDate.now().minusYears(1));

        assertEquals(12, e.getMoisAnciennete());
    }

    // @Disabled
    @Test
    void test_mois_entre_31_mars_et_31_mars_moins_1_mois() {
        LocalDate finMars = LocalDate.of(2023, 3, 31);
        LocalDate finFevrier = LocalDate.of(2023, 2, 28);

        assertEquals(finFevrier, finMars.minusMonths(1));
        assertEquals(0, ChronoUnit.DAYS.between(finFevrier, finMars.minusMonths(1)));
    }

    // @Disabled
    @Test
    void test_mois_entre_1_mars_et_1_mars_moins_1_mois() {
        LocalDate debutMars = LocalDate.of(2023, 3, 1);
        LocalDate debutFevrier = LocalDate.of(2023, 2, 1);

        assertTrue(Math.abs(ChronoUnit.DAYS.between(debutMars, debutFevrier)) < 30);
        assertEquals(debutFevrier, debutMars.minusMonths(1));
        assertEquals(0, ChronoUnit.DAYS.between(debutFevrier, debutMars.minusMonths(1)));
        assertEquals(1, ChronoUnit.MONTHS.between(debutFevrier, debutMars));
    }

    // @Disabled
    @Test
    void test_deux_employes_date_differente_meme_mois_anciennete() {
        Employe e1 = creerEmployeTest("");
        Employe e2 = creerEmployeTest("");

        e1.setDateEmbauche(LocalDate.now().minusMonths(1));
        e2.setDateEmbauche(LocalDate.now().minusMonths(1).minusDays(27));

        assertEquals(1, e1.getMoisAnciennete());
        assertEquals(1, e2.getMoisAnciennete());
    }


    // @Disabled
    @Test
    void test_getIndeminiteTransport_montant_simple() {
        Employe e1 = new Employe("", "", 2);
        Employe e2 = new Employe("", "", 0.5);

        e1.setVilleDeResidence("Sommières");
        e2.setVilleDeResidence("Lunel");

        assertEquals(60, e1.getIndemniteTransport());
        assertEquals(15, e2.getIndemniteTransport());
    }

    // @Disabled
    @Test
    void test_getIndemniteTransport_montant() {
        double distance;
        double base = 0;
        for (String key : distances.keySet()) {
            distance = distances.get(key);
            e.setVilleDeResidence(key);

            base+=0.678;
            setAttribute(e, "base", base);
            double expected = distance * base;

            assertEquals(expected, e.getIndemniteTransport());
        }
    }

    // @Disabled
    @Test
    void test_getIndemniteTransport_exception_ville() {
        Employe e1 = new Employe("1", "1", 1);
        e1.setVilleDeResidence("Yggdrasil");

        double res = e1.getIndemniteTransport();
        String msg = "";

        try {
            GestionDistances.getDistance(e1.getVilleDeResidence());
        } catch (VilleInconnueException ex) {
            msg = ex.getMessage();
        }
        
        assertDoesNotThrow(e1::getIndemniteTransport);
        assertEquals(msg, "La ville " + e1.getVilleDeResidence() + " n'existe pas");
        assertEquals(0, res);
    }
}
