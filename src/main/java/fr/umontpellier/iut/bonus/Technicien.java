package fr.umontpellier.iut.bonus;

public class Technicien extends Employe {
    private String outilTechnique;

    public Technicien(TechnicienBuilder technicienBuilder) {
        super(technicienBuilder);
        outilTechnique = technicienBuilder.outilTechnique;
    }

    public void effectuerTacheTechnique() {
        System.out.println("J'effectue une tâche technique avec " + outilTechnique);
    }

    @Override
    public String toString() {
        return "Technicien{" + super.toString() + "}";
    }


    public static class TechnicienBuilder extends AbstractEmployeBuilder<TechnicienBuilder>{

        private String outilTechnique;

        public TechnicienBuilder setOutilTechnique(String outilTechnique){
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

    }
}
