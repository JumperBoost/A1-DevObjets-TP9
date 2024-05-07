package fr.umontpellier.iut;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class Entreprise {
    private double bonusTotal;
    private Collection<Employe> lePersonnel;

    public Entreprise() {
        lePersonnel = new ArrayList<>();
    }

    public void embaucher(Employe e, LocalDate dateEmbauche) {
        Employe employe = lePersonnel.stream().filter(f -> e.hashCode() == f.hashCode()
                        && f.getDateEmbauche() == null).findFirst().orElse(null);
        if(employe == null) {
            e.setDateEmbauche(dateEmbauche);
            lePersonnel.add(e);
        } else employe.setDateEmbauche(dateEmbauche);
    }

    public void licencier(Employe e) {
        if(lePersonnel.contains(e))
            e.setDateEmbauche(null);
    }

    public Collection<Employe> getEmployesOrdonnes() {
        return new TreeSet<>(getEmployesDansDesordre());
    }

    public Collection<Employe> getEmployesDansDesordre() {
       return new HashSet<>(lePersonnel);
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

    @Override
    public String toString() {
        return "Entreprise{" +
                "bonusTotal=" + bonusTotal +
                ", lePersonnel=" + lePersonnel +
                '}';
    }
}
