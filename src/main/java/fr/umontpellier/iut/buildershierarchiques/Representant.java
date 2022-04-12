package fr.umontpellier.iut.buildershierarchiques;

public class Representant extends Commercial{

    public void representerEntreprise() {
        System.out.println("je represente l'entreprise aupres des clients");
    }

    @Override
    public String toString() {
        return "Representant{" + super.toString() + "}";
    }
}
