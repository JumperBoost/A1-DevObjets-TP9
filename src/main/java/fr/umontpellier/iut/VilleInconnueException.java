package fr.umontpellier.iut;

public class VilleInconnueException extends Exception {
    public VilleInconnueException(String nomVille) {
        super("La ville " + nomVille + " n'existe pas");
    }
}
