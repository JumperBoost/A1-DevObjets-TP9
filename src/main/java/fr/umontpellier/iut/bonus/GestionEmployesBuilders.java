package fr.umontpellier.iut.bonus;

public class GestionEmployesBuilders {
    public static void main(String[] args) {
        // instanciation d'un employe simple
        Employe employe = new Employe.EmployeBuilder()
                .setBase(10)
                .setNom("Toto")
                .setNrINSEE("1982424")
                .setAdresse("99 av. Occitanie")
                .build();

        System.out.println(employe);

        Technicien technicien = new Technicien.TechnicienBuilder()
                .setOutilTechnique("marteau")
                .setNrINSEE("2992727397")
                .setBase(12)
                .setNom("Nono le technicien")
                .build(); // instanciation d'un technicien (pas d'adresse pour lui)

        System.out.println(technicien);
        technicien.effectuerTacheTechnique();

        Employe fabricant = new Fabricant.FabricantBuilder()
                .setBase(12)
                .setNbUnitesProduites(5)
                .setTauxCommissionUnite(36)
                .setNom("Lolo le Fabricant")
                .build(); // instanciation en tant que fabricant (pas de numero INSEE et pas d'adresse pour lui)

        System.out.println(fabricant);
        // par principe de substitution, ici il n'est pas possible d'invoquer la méthode fabriquerProduit() car
        // l'objet `fabricant` a comme type apparent `Employe`


        Vendeur vendeur; // ici votre code d'instanciation en tant que vendeur

        Representant representant; // ici votre code d'instanciation en tant que representant
    }
}
