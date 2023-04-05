package fr.umontpellier.iut.buildershierarchiques;

public class Fabricant extends Employe {
    private int nbUnitesProduites;
    private double tauxCommissionUnite;

    public Fabricant(FabricantBuilder fabricantBuilder) {
        super(fabricantBuilder);
        nbUnitesProduites = fabricantBuilder.getNbUnitesProduites();
        tauxCommissionUnite = fabricantBuilder.getTauxCommissionUnite();
    }

    public void fabriquerProduit() {
        System.out.println("Je fabrique "+nbUnitesProduites + " produits avec le taux de "+tauxCommissionUnite);
    }

    @Override
    public String toString() {
        return "Fabricant{" + super.toString() +
                ", nbUnitesProduites=" + nbUnitesProduites +
                ", tauxCommissionUnite=" + tauxCommissionUnite +
                '}';
    }

}
