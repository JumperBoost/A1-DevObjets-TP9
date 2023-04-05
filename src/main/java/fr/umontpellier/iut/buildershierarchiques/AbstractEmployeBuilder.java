package fr.umontpellier.iut.buildershierarchiques;

public abstract class AbstractEmployeBuilder<T extends AbstractEmployeBuilder<T>> {
    private String nrINSEE;
    private String nom;
    private double base;
    private String adresse;

    protected abstract T self();

    public abstract Employe build();

    public T setNom(String nom) {
        this.nom = nom;
        return self();
    }

    public T setBase(double base) {
        this.base = base;
        return self();
    }

    public T setNrINSEE(String nrINSEE) {
        this.nrINSEE = nrINSEE;
        return self();
    }

    public T setAdresse(String adresse) {
        this.adresse = adresse;
        return self();
    }

    public String getNrINSEE() {
        return nrINSEE;
    }

    public String getNom() {
        return nom;
    }

    public double getBase() {
        return base;
    }

    public String getAdresse() {
        return adresse;
    }
}
