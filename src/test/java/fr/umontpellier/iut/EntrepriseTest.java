package fr.umontpellier.iut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static fr.umontpellier.iut.EmployeTest.*;

public class EntrepriseTest extends BaseTest {

    private Entreprise inc;
    private ArrayList<Employe> employes;


    @BeforeEach
    void init() {
        inc = new Entreprise();
        employes = getAttribute(inc, "lePersonnel");
        ajouterEmployes(
            creerEmployeTest("1"),
            creerEmployeTest("2"),
            creerEmployeTest("3")
        );
    }

    public void ajouterEmployes(Employe... e) {
        try {
            employes.addAll(new LinkedList<>(List.of(e)));
        } catch (NullPointerException ex) {
            System.out.println("Ne peut pas insérer de valeurs : "+ex.getMessage());
        }
    }

    // @Disabled
    @Test
    void test_embaucher() {
        Employe e = creerEmployeTest("0");
        inc.embaucher(e, LocalDate.now());

        assertSame(e, employes.get(3));
        assertEquals(4, employes.size());
        assertEquals(LocalDate.now(), getAttribute(e, "dateEmbauche"));
    }

    // @Disabled
    @Test
    void test_embaucher_null() {
        inc.embaucher(null, LocalDate.now());

        assertFalse(containsReference(employes, null));
        assertEquals(3, employes.size());
    }

    // @Disabled
    @Test
    void test_embaucher_meme_employe() {
        Employe e = employes.get(0);
        inc.embaucher(e, LocalDate.now());

        assertSame(e, employes.get(3));
        assertEquals(4, employes.size());
        assertEquals(LocalDate.now(), getAttribute(e, "dateEmbauche"));
    }

    // @Disabled
    @Test
    void test_licencier() {
        Employe e = employes.get(0);
        inc.licencier(e);

        assertFalse(containsReference(employes, e));
        assertEquals(2, employes.size());
        assertNull(getAttribute(e, "dateEmbauche"));
    }

    @Disabled
    @Test
    void test_licencierV2() {
        Employe e = employes.get(0);
        Employe e_bis = EmployeTest.creerEmployeTest("1");
        employes.add(e_bis);

        inc.licencier(e_bis);

        assertTrue(containsReferences(employes, e, e_bis));
        assertNull(getAttribute(e_bis, "dateEmbauche"));
        assertNotNull(getAttribute(e, "dateEmbauche"));
    }

    // @Disabled
    @Test
    void test_licencier_meme_employe() {
        Employe e = employes.get(0);
        inc.embaucher(e, LocalDate.now());
        inc.licencier(e);

        assertTrue(containsReference(employes, e));
        assertNotSame(e, employes.get(0));
        assertSame(e, employes.get(2));
        assertEquals(3, employes.size());
        assertNull(getAttribute(e, "dateEmbauche"));
    }

    // @Disabled
    @Test
    void test_getEmployesDansDesordre_no_doublon_ref_diff() {
        Employe e = new Employe("1", "test1", 0);
        ajouterEmployes(e);

        Collection<Employe> res = inc.getEmployesDansDesordre();
        Collection<Employe> expected = new HashSet<>(employes);

        assertFalse(containsReference(res, e));
        assertTrue(containsReference(res, employes.get(0)));
        assertTrue(Objects.deepEquals(expected, res));
        assertEquals(3, res.size());
    }

    // @Disabled
    @Test
    void test_getEmployesDansDesordre_no_doublon_meme_ref() {
        Employe e = employes.get(0);
        ajouterEmployes(e);

        Collection<Employe> res = inc.getEmployesDansDesordre();
        Collection<Employe> expected = new HashSet<>(employes);

        assertTrue(containsReference(res, e));
        assertTrue(Objects.deepEquals(expected, res));
        assertIterableEquals(expected, res);
        assertEquals(3, res.size());
    }

    // @Disabled
    @Test
    void test_getEmployesOrdonnees() {
        Employe e1 = employes.get(0);
        Employe e2 = employes.get(1);
        Employe e3 = employes.get(2);
        Employe e4 = creerEmployeTest("1");
        Employe e5 = new Employe("4", "test1", 0);
        Employe e6 = creerEmployeTest("5");
        ajouterEmployes(e4, e5, e6);

        Collection<Employe> res = inc.getEmployesOrdonnes();
        Collection<Employe> expected = new TreeSet<>(Employe.getComparatorNomInsee());
        expected.addAll(employes);

        assertTrue(containsReferences(res, e1, e2, e3, e5, e6));
        assertFalse(containsReferences(res, e4));
        assertTrue(containsExactlyReferencesInOrder(res.stream().toList(), e5, e1, e2, e3, e6));
        assertTrue(Objects.deepEquals(expected, res));
        assertIterableEquals(expected, res);
        assertEquals(5, res.size());
    }

    // @Disabled
    @Test
    void test_getEmployesOrdre_et_Desordre() {
        Employe e4 = creerEmployeTest("1");
        Employe e5 = new Employe("4", "test1", 0);
        Employe e6 = creerEmployeTest("5");
        ajouterEmployes(e4, e5, e6);

        Collection<Employe> resDesordre = inc.getEmployesDansDesordre();
        Collection<Employe> resOrdre = inc.getEmployesOrdonnes();

        assertEquals(5, resDesordre.size());
        assertEquals(resOrdre.size(), resDesordre.size());
        assertTrue(containsReferences(resDesordre, resOrdre));
    }



    // @Disabled
    @Test
    void test_distribuerBonus_montant() {
        inc.setBonusTotal(60);
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e2 = creerEmployeTest("2");
        Employe e3 = creerEmployeTest("3");
        Employe e4 = creerEmployeTest("4");
        inc.embaucher(e1, LocalDate.now());
        inc.embaucher(e2, LocalDate.now().minusMonths(1));
        inc.embaucher(e3, LocalDate.now().minusMonths(10));
        inc.embaucher(e4, LocalDate.now().minusMonths(2));
        inc.embaucher(e4, LocalDate.now().minusMonths(2));

        inc.distribuerBonus();

        assertEquals(0, e1.getBonus());
        assertEquals(3.5, e2.getBonus());
        assertEquals(42.5, e3.getBonus());
        assertEquals(14, e4.getBonus());
        assertEquals(0, inc.getBonusTotal());
    }

    // @Disabled
    @Test
    void test_distribuerBonus_ordre_et_bonus_restant_inferieur_bonus_du() {
        inc.setBonusTotal(80);
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e2 = creerEmployeTest("2");
        Employe e3 = creerEmployeTest("3");
        inc.embaucher(e1, LocalDate.now().minusMonths(1));
        inc.embaucher(e2, LocalDate.now().minusMonths(20));
        inc.embaucher(e3, LocalDate.now().minusMonths(10));

        inc.distribuerBonus();

        assertEquals(0, e1.getBonus());
        assertEquals(70, e2.getBonus());
        assertEquals(10, e3.getBonus());
        assertEquals(0, inc.getBonusTotal());
    }


    // VERSION BIDOUILLE
    // @Disabled
    @Test
    void test_distribuerBonus_ordre_apparition() {
        inc.setBonusTotal(8);
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e2 = creerEmployeTest("2");
        Employe e3 = creerEmployeTest("3");
        inc.embaucher(e2, LocalDate.now().minusMonths(1));
        inc.embaucher(e3, LocalDate.now().minusMonths(1));
        inc.embaucher(e1, LocalDate.now().minusMonths(1));
        inc.embaucher(e3, LocalDate.now().minusMonths(1));

        inc.distribuerBonus();

        assertEquals(1, e1.getBonus());
        assertEquals(3.5, e2.getBonus());
        assertEquals(3.5, e3.getBonus());
        assertEquals(0, inc.getBonusTotal());
    }

    // @Disabled
    @Test
    void test_distribuerBonus_critere_comparaison() {
        inc.setBonusTotal(5);
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e2 = creerEmployeTest("2");
        Employe e3 = creerEmployeTest("3");
        inc.embaucher(e1, LocalDate.now().minusMonths(1).minusDays(1));
        inc.embaucher(e2, LocalDate.now().minusMonths(1).minusDays(3));
        inc.embaucher(e3, LocalDate.now().minusMonths(1).minusDays(2));

        inc.distribuerBonus();

        assertEquals(0, e1.getBonus());
        assertEquals(3.5, e2.getBonus());
        assertEquals(1.5, e3.getBonus());
        assertEquals(0, inc.getBonusTotal());
    }

    // @Disabled
    @Test
    void test_remercier_simple() {
        employes.clear();
        Employe fifi = new Employe("1", "fifi", 1);
        Employe loulou = new Employe("1", "fifi", 2);
        Employe toto = creerEmployeTest("3");

        inc.embaucher(toto, LocalDate.of(2000, 1, 1));
        inc.embaucher(fifi, LocalDate.of(2024, 5, 2));
        inc.embaucher(loulou, LocalDate.of(2024, 5, 30));

        inc.remercier(1);

        assertEquals(2, employes.size());
        assertTrue(containsExactlyReferencesInOrder(employes, toto, loulou));
        // loulou plus récent
        // or loulou = fifi au sens de equals et comme licencier supprime la premiere occurence qlors fifi est supp
        assertFalse(containsReference(employes, fifi));
    }

    // @Disabled
    @Test
    void test_remercier_critere_comparaison() {
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e2 = creerEmployeTest("2");
        Employe e3 = creerEmployeTest("3");
        inc.embaucher(e1, LocalDate.now().minusMonths(1).minusDays(1));
        inc.embaucher(e2, LocalDate.now().minusMonths(1).minusDays(3));
        inc.embaucher(e3, LocalDate.now().minusMonths(1).minusDays(2));

        inc.remercier(2);

        assertEquals(1, employes.size());
        assertTrue(containsExactlyReferencesInOrder(employes, e2));
        assertFalse(containsReferences(employes, e1, e3));
    }

    // VERSION BIDOUILLE
    // @Disabled
    @Test
    void test_remercier_ordre_apparition() {
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e2 = creerEmployeTest("2");
        Employe e3 = creerEmployeTest("3");
        inc.embaucher(e2, LocalDate.now().minusMonths(1));
        inc.embaucher(e3, LocalDate.now().minusMonths(1));
        inc.embaucher(e1, LocalDate.now().minusMonths(1));
        inc.embaucher(e3, LocalDate.now().minusMonths(1));

        inc.remercier(2);

        assertEquals(2, employes.size());
        assertTrue(containsExactlyReferencesInOrder(employes, e1, e3));
        assertFalse(containsReferences(employes, e2));
    }

    // @Disabled
    @Test
    void test_remercier_premiere_occurence_doublon_ref_diff() {
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        Employe e1_bis = creerEmployeTest("1");
        inc.embaucher(e1, LocalDate.now().minusMonths(2));
        inc.embaucher(e1_bis, LocalDate.now().minusMonths(1));

        inc.remercier(1);

        assertEquals(1, employes.size());
        assertTrue(containsReference(employes, e1_bis));
        assertFalse(containsReferences(employes, e1));
    }

    // @Disabled
    @Test
    void test_remercier_employes_vide() {
        employes.clear();
        inc.remercier(2);
        assertTrue(employes.isEmpty());
    }

    // @Disabled
    @Test
    void test_remercier_employes_inferieur_a_n() {
        employes.clear();
        Employe e1 = creerEmployeTest("1");
        inc.embaucher(e1, LocalDate.now());
        inc.remercier(2);
        assertTrue(employes.isEmpty());
    }
}
