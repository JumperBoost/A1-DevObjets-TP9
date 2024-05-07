package fr.umontpellier.iut;

import java.time.LocalDate;

public class GestionEmployes {

    public static void main(String[] args) {
        Entreprise entreprise = new Entreprise();
        entreprise.setBonusTotal(2);

        Employe employe1 = new Employe("1", "Moulin", 1850);
        Employe employe2 = new Employe("2", "Dupont", 1600);
        Employe employe3 = new Employe("3", "Moulin", 2400);

        System.out.println(entreprise.getEmployesOrdonnes());
        entreprise.embaucher(employe1, LocalDate.of(2023, 8, 15));
        System.out.println(entreprise.getEmployesOrdonnes());
        entreprise.embaucher(employe2, LocalDate.of(2024, 3, 12));
        entreprise.licencier(employe1);
        System.out.println(entreprise.getEmployesOrdonnes());
        entreprise.embaucher(employe1, LocalDate.now());
        entreprise.embaucher(employe3, LocalDate.now());
        System.out.println(entreprise.getEmployesOrdonnes());
    }

}
