package ucad.glrs.data.enums;

public enum TypeBourse {
    DEMIBOURSE(20000), BOURSECOMPLET(40000);

    private final int montant;

    TypeBourse(int montant) {
        this.montant = montant;
    }

    public int getMontant() {
        return montant;
    }
}
