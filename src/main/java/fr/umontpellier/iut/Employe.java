package fr.umontpellier.iut;

public class Employe {
    private String nrINSEE;
    private String nom;
    private int echelon;
    private double base;
    private double nbHeures;

    public Employe(String nrINSEE, String nom, int echelon, double base, double nbHeures) {
        this.nrINSEE = nrINSEE;
        this.nom = nom;
        this.echelon = echelon;
        this.base = base;
        this.nbHeures = nbHeures;
    }

    public double getSalaireBrut() {
        return base * nbHeures;
    }

    public double getSalaireNet() {
        return getSalaireBrut() * 0.8; // pour éviter la duplication du code
    }


    @Override
    public String toString() {
        return "Employe{" +
                "addNrINSEE=" + nrINSEE +
                ", nom='" + nom + '\'' +
                ", echelon=" + echelon +
                ", base=" + base +
                ", nbHeures=" + nbHeures +
                '}';
    }
}
