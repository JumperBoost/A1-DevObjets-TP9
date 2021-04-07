package fr.umontpellier.iut.bonus;

public class Fabricant extends Employe {
    private int nbUnitesProduites;
    private double tauxCommissionUnite;

    public Fabricant(FabricantBuilder fabricantBuilder) {
        super(fabricantBuilder);
        nbUnitesProduites = fabricantBuilder.nbUnitesProduites;
        tauxCommissionUnite = fabricantBuilder.tauxCommissionUnite;
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

    public static class FabricantBuilder extends AbstractEmployeBuilder<FabricantBuilder> {
        private int nbUnitesProduites;
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
}
