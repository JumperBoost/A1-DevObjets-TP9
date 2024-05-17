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
        if (e!=null) {
            e.setDateEmbauche(dateEmbauche);
            lePersonnel.add(e);
        }
    }

    public void licencier(Employe e) {
        e.setDateEmbauche(null);
        lePersonnel.remove(e);
    }


    public Collection<Employe> getEmployesOrdonnes() {
        Set<Employe> employes = new TreeSet<>(Employe.getComparatorNomInsee());
        employes.addAll(lePersonnel);
        return employes;
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
        // Version Bidouille
        ArrayList<Employe> em = new ArrayList<>(lePersonnel);
        PriorityQueue<Employe> employes = new PriorityQueue<>((Employe e1, Employe e2) ->
                e1.getDateEmbauche().equals(e2.getDateEmbauche()) ?
                         em.indexOf(e1)-em.indexOf(e2) : e1.getDateEmbauche().compareTo(e2.getDateEmbauche())
        );

        Employe e;
        double bonus;
        while ((bonusTotal > 0)) {
            employes.addAll(em);
            while ((e=employes.poll())!=null && (bonusTotal > 0)) {
                bonus = Math.min(3.5 * e.getMoisAnciennete(), bonusTotal);
                e.setBonus(e.getBonus() + bonus);
                bonusTotal -= bonus;
            }
        }

        // Version Classique
//        PriorityQueue<Employe> employes = new PriorityQueue<>(Comparator.comparing(Employe::getDateEmbauche));
//
//        Employe e;
//        double bonus;
//
//        while ((bonusTotal > 0)) {
//            employes.addAll(lePersonnel);
//            while ((e=employes.poll())!=null && (bonusTotal > 0)) {
//                bonus = Math.min(3.5 * e.getMoisAnciennete(), bonusTotal);
//                e.setBonus(e.getBonus() + bonus);
//                bonusTotal -= bonus;
//            }
//        }
    }

    public void remercier(int n) {
        // Version bidouille
        ArrayList<Employe> em = new ArrayList<>(lePersonnel);
        PriorityQueue<Employe> employes = new PriorityQueue<>((Employe e1, Employe e2) ->
                e1.getDateEmbauche().equals(e2.getDateEmbauche()) ?
                        em.indexOf(e1)-em.indexOf(e2) : e2.getDateEmbauche().compareTo(e1.getDateEmbauche())
        );
        employes.addAll(em);

        Employe e;
        while((e=employes.poll())!=null && n-- > 0) {
            licencier(e);
        }

        // Version Classique
//        PriorityQueue<Employe> employes = new PriorityQueue<>((Employe e1, Employe e2) -> e2.getDateEmbauche().compareTo(e1.getDateEmbauche()));
//        employes.addAll(lePersonnel);
//
//        Employe e;
//        while((e=employes.poll())!=null && n-- > 0) {
//            licencier(e);
//        }
    }

    @Override
    public String toString() {
        return lePersonnel.toString();
    }

}
