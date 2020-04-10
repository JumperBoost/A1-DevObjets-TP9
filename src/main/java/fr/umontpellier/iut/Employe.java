package fr.umontpellier.iut;

import java.time.LocalDate;

public class Employe {

    private String nrINSEE;
    private String nom;
    private double base;
    private double nbHeures;

    private LocalDate dateEmbauche;

    private double bonus; // pour exo3

    private String adresse; // pour exo4

    public Employe(String nrINSEE, String nom, double base, double nbHeures, LocalDate dateEmbauche) {
        this.nrINSEE = nrINSEE;
        this.nom = nom;
        this.base = base;
        this.nbHeures = nbHeures;
        this.dateEmbauche = dateEmbauche;
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
                ", nbHeures=" + nbHeures +
                ", dateEmbauche=" + dateEmbauche +
                "}\n";
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
