package fr.umontpellier.iut;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;

public class Employe {
    private String nrINSEE;
    private String nom;
    private double base;

    private LocalDate dateEmbauche;

    private double bonus; // pour exo3

    private String villeDeResidence; // pour exo4

    private final static Comparator<Employe> comparatorNomInsee = (e1, e2) -> {
        if (e1.nom.equals(e2.nom)) {
            return e2.nrINSEE.compareTo(e1.nrINSEE);
        } else {
            return e1.nom.compareTo(e2.nom);
        }
    };

    public Employe(String nrINSEE, String nom, double base) {
        this.nrINSEE = nrINSEE;
        this.nom = nom;
        this.base = base;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nrINSEE='" + nrINSEE + '\'' +
                ", nom='" + nom + '\'' +
                ", base=" + base +
                ", dateEmbauche=" + dateEmbauche +
                ", bonus=" + bonus +
                "}\n";
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getVilleDeResidence() {
        return villeDeResidence;
    }

    public void setVilleDeResidence(String villeDeResidence) {
        this.villeDeResidence = villeDeResidence;
    }

    public int getMoisAnciennete() {
        long intervalleEnMois;
        if (dateEmbauche==null) {
            intervalleEnMois = 0;
        } else {
            intervalleEnMois = ChronoUnit.MONTHS.between(dateEmbauche, LocalDate.now());
        }
        return (int) intervalleEnMois;
    }

    public double getIndemniteTransport() {
        try {
            return GestionDistances.getDistance(getVilleDeResidence()) * base;
        } catch (VilleInconnueException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employe employe = (Employe) o;

        if (!Objects.equals(nrINSEE, employe.nrINSEE)) return false;
        return Objects.equals(nom, employe.nom);
    }

    @Override
    public int hashCode() {
        int result = nrINSEE != null ? nrINSEE.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }

    public static Comparator<Employe> getComparatorNomInsee() {
        return comparatorNomInsee;
    }
}
