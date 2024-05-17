package fr.umontpellier.iut;

import java.time.LocalDate;
import java.util.Date;

public class GestionEmployes {

    public static void main(String[] args) {
        Employe e1 = new Employe("1", "e1", 1);
        Employe e2 = new Employe("2", "e2", 2);
        Employe e3 = new Employe("3", "e3", 3);

        Entreprise inc = new Entreprise();

        inc.embaucher(e1, LocalDate.now());
        System.out.println(inc);
        inc.embaucher(e2, LocalDate.now());
        System.out.println(inc);
        inc.embaucher(e3, LocalDate.now());
        System.out.println(inc);

        Employe e1inf = new Employe("4", "e1", 1);
        Employe e1sup = new Employe("0", "e1", 1);
        Employe e1eq = new Employe("1", "e1", 1);

        inc.embaucher(e1eq, LocalDate.now());
        inc.embaucher(e1sup, LocalDate.now());
        inc.embaucher(e1inf, LocalDate.now());
        System.out.println(inc);
        System.out.println("DESORDRE===================================");
        System.out.println(inc.getEmployesDansDesordre());
        System.out.println("ORDRE======================================");
        System.out.println(inc.getEmployesOrdonnes());

        inc.setBonusTotal(30);
        inc.embaucher(e1, LocalDate.now().minusMonths(1));
        inc.embaucher(e2, LocalDate.now().minusMonths(2));
        inc.embaucher(e3, LocalDate.now().minusMonths(3));
        inc.distribuerBonus();
        System.out.println("BONUS======================================");
        System.out.println(inc);
    }

}
