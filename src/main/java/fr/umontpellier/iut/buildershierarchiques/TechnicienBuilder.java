package fr.umontpellier.iut.buildershierarchiques;

public class TechnicienBuilder extends AbstractEmployeBuilder<TechnicienBuilder> {

    private String outilTechnique;

    public TechnicienBuilder setOutilTechnique(String outilTechnique) {
        this.outilTechnique = outilTechnique;
        return this;
    }

    @Override
    protected TechnicienBuilder self() {
        return this;
    }

    @Override
    public Technicien build() {
        return new Technicien(this);
    }

    public String getOutilTechnique() {
        return outilTechnique;
    }
}
