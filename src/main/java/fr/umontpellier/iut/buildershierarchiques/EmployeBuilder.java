package fr.umontpellier.iut.buildershierarchiques;

public class EmployeBuilder extends AbstractEmployeBuilder<EmployeBuilder> {

    @Override
    protected EmployeBuilder self() {
        return this;
    }

    public Employe build() {
        return new Employe(this);
    }
}
