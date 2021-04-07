package fr.umontpellier.iut.bonus;

public abstract class Commercial {
    private double chiffreAffaires;
    private double tauxCommission;

    public void negocierTransaction() {
        System.out.println("Je negocie une transaction avec " + tauxCommission + " " + chiffreAffaires);
    }

    @Override
    public String toString() {
        return "Commercial{" + super.toString() +
                ", chiffreAffaires=" + chiffreAffaires +
                ", tauxCommission=" + tauxCommission +
                '}';
    }
}
