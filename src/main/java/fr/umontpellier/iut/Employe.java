package fr.umontpellier.iut;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Employe implements Comparable<Employe> {
    private final String nrINSEE;
    private final String nom;
    private final double base;

    private LocalDate dateEmbauche;

    private double bonus; // pour exo3

    private String villeDeResidence; // pour exo4

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
                "}";
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
        long intervalleEnMois = ChronoUnit.MONTHS.between(dateEmbauche, LocalDate.now());
        return (int) intervalleEnMois;
    }

    public double getIndemniteTransport() {
        try {
            return GestionDistances.getDistance(villeDeResidence) * base;
        } catch (VilleInconnueException exception) {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(nrINSEE, employe.nrINSEE) && Objects.equals(nom, employe.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrINSEE, nom);
    }


    @Override
    public int compareTo(Employe e) {
        int i;
        return (i = nom.compareTo(e.nom)) != 0 ? i : -nrINSEE.compareTo(e.nrINSEE);
    }
}
