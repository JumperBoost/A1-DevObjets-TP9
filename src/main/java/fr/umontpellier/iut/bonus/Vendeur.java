package fr.umontpellier.iut.bonus;

public class Vendeur extends Commercial {
    private int nbUnitesVendues;

    public void vendreProduit() {
        System.out.println("je vends " + nbUnitesVendues+ " produits");
    }


    @Override
    public String toString() {
        return "Vendeur{" + super.toString() + "}";
    }
}
