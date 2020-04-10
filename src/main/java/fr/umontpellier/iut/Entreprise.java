package fr.umontpellier.iut;

import java.util.Collection;

public class Entreprise {
    private double bonusTotal;

    public Collection<Employe> getEmployesOrdonnes() {
        throw new RuntimeException("Méthode à implémenter");
    }

    public void setBonusTotal(double bonusTotal) {
        this.bonusTotal = bonusTotal;
    }
}
