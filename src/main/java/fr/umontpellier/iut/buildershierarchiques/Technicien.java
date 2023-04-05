package fr.umontpellier.iut.buildershierarchiques;

public class Technicien extends Employe {
    private String outilTechnique;

    public Technicien(TechnicienBuilder technicienBuilder) {
        super(technicienBuilder);
        outilTechnique = technicienBuilder.getOutilTechnique();
    }

    public void effectuerTacheTechnique() {
        System.out.println("J'effectue une tâche technique avec " + outilTechnique);
    }

    @Override
    public String toString() {
        return "Technicien{" + super.toString() + "}";
    }


}
