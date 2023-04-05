package fr.umontpellier.iut.buildershierarchiques;

public class FabricantBuilder extends AbstractEmployeBuilder<FabricantBuilder> {
    private int nbUnitesProduites;

    public int getNbUnitesProduites() {
        return nbUnitesProduites;
    }

    public double getTauxCommissionUnite() {
        return tauxCommissionUnite;
    }

    private double tauxCommissionUnite;

    public FabricantBuilder setNbUnitesProduites(int nbUnitesProduites) {
        this.nbUnitesProduites = nbUnitesProduites;
        return this;
    }

    public FabricantBuilder setTauxCommissionUnite(double tauxCommissionUnite) {
        this.tauxCommissionUnite = tauxCommissionUnite;
        return this;
    }

    @Override
    protected FabricantBuilder self() {
        return this;
    }

    @Override
    public Fabricant build() {
        return new Fabricant(this);
    }
}
