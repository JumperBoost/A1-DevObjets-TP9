package fr.umontpellier.iut.bonus;

import java.time.LocalDate;

public class Employe {
    private String nrINSEE;
    private String nom;
    private double base;

    private String adresse;

    public Employe(AbstractEmployeBuilder<?> employeBuilder) {
        nrINSEE = employeBuilder.nrINSEE;
        nom = employeBuilder.nom;
        base = employeBuilder.base;
        adresse = employeBuilder.adresse;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nrINSEE='" + nrINSEE + '\'' +
                ", nom='" + nom + '\'' +
                ", base=" + base +
                "}\n";
    }

    public static class EmployeBuilder extends AbstractEmployeBuilder<EmployeBuilder> {

        @Override
        protected EmployeBuilder self() {
            return this;
        }

        public Employe build() {
            return new Employe(this);
        }
    }

    public abstract static class AbstractEmployeBuilder<T extends AbstractEmployeBuilder<T>> {
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
    }
}
