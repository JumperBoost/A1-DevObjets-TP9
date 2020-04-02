package fr.umontpellier.iut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    public void test_salaire_brut_800() {
        Employe employe = new Employe("19392932", "Toto", 0, 8, 100);
        double valeur = employe.getSalaireBrut();

        assertEquals(800, valeur, 0.0001);
    }


    @Test
    public void test_salaire_net_zero_point_huit_du_brut() {
        Employe employe = new Employe("19392932", "Toto", 0, 7.5, 100);
        double brut = employe.getSalaireBrut();
        double net = employe.getSalaireNet();

        assertEquals(brut * 0.8, net, 0.001);
    }


}