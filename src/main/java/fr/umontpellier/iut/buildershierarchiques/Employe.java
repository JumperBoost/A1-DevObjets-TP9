package fr.umontpellier.iut.buildershierarchiques;

public class Employe {
    private String nrINSEE;
    private String nom;
    private double base;

    private String adresse;

    public Employe(AbstractEmployeBuilder<?> employeBuilder) {
        nrINSEE = employeBuilder.getNrINSEE();
        nom = employeBuilder.getNom();
        base = employeBuilder.getBase();
        adresse = employeBuilder.getAdresse();
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nrINSEE='" + nrINSEE + '\'' +
                ", nom='" + nom + '\'' +
                ", base=" + base +
                "}\n";
    }

}
