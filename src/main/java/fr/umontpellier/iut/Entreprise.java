package fr.umontpellier.iut;

import java.time.LocalDate;
import java.util.*;

public class Entreprise {
    private double bonusTotal;
    private Collection<Employe> lePersonnel;

    public Entreprise() {
        lePersonnel = new ArrayList<>();
    }

    public void embaucher(Employe e, LocalDate dateEmbauche) {
        if(e != null) {
            e.setDateEmbauche(dateEmbauche);
            lePersonnel.add(e);
        }
    }

    public void licencier(Employe e) {
        e.setDateEmbauche(null);
        lePersonnel.remove(e);
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
        PriorityQueue<Employe> employes_trie = new PriorityQueue<>(Comparator.comparing(Employe::getDateEmbauche));

        double bonus;
        while (bonusTotal > 0) {
            employes_trie.addAll(lePersonnel);
            while (!employes_trie.isEmpty() && bonusTotal > 0) {
                Employe employe = employes_trie.poll();
                if(employe.getDateEmbauche() != null) {
                    bonus = Math.min(3.5 * employe.getMoisAnciennete(), bonusTotal);
                    employe.setBonus(employe.getBonus() + bonus);
                    bonusTotal -= bonus;
                }
            }
        }
    }

    public void remercier(int n) {
        PriorityQueue<Employe> employes_trie = new PriorityQueue<>(Comparator.comparing(Employe::getDateEmbauche).reversed());
        employes_trie.addAll(lePersonnel);

        Employe e;
        int i = 0;
        while (i++ < n && (e = employes_trie.poll()) != null)
            licencier(e);
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "bonusTotal=" + bonusTotal +
                ", lePersonnel=" + lePersonnel +
                '}';
    }
}
