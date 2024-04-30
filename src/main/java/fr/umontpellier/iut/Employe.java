package fr.umontpellier.iut;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Employe {
    private String nrINSEE;
    private String nom;
    private double base;

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
        long intervalleEnMois = ChronoUnit.MONTHS.between(dateEmbauche, LocalDate.now());
        return (int) intervalleEnMois;
    }

    public double getIndemniteTransport() {
        throw new RuntimeException("Méthode à implémenter");
    }
}
