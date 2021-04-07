package fr.umontpellier.iut;

import java.time.LocalDate;
import java.util.Collection;

public class Entreprise {
    private double bonusTotal;
    private Collection<Employe> lePersonnel;

    public void embaucher(Employe e, LocalDate dateEmbauche) {
        throw new RuntimeException("Méthode à implémenter");
    }

    public void licencier(Employe e) {
        throw new RuntimeException("Méthode à implémenter");
    }

    public Collection<Employe> getEmployesOrdonnes() {
        throw new RuntimeException("Méthode à implémenter");
    }

    public Collection<Employe> getEmployesDansDesordre() {
        throw new RuntimeException("Méthode à implémenter");
    }

    public double getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(double bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public void distribuerBonus() {
        throw new RuntimeException("Méthode à implémenter");
    }

    public void remercier(int n) {
        throw new RuntimeException("Méthode à implémenter");
    }
}
