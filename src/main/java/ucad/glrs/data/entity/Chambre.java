package ucad.glrs.data.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ucad.glrs.data.enums.TypeChambre;

@Data
public class Chambre {
    private int numChambre;
    private int numEtage;
    private TypeChambre typeChambre;
    private Pavillon pavillon; // Mettre ce champ à null dans la BD
    private boolean isActif;
    private List<BoursierLoger> boursierLogers = new ArrayList<>();

    public Chambre(int numChambre, int numEtage, TypeChambre typeChambre, Pavillon pavillon) {
        this.numChambre = numChambre;
        this.numEtage = numEtage;
        this.typeChambre = typeChambre;
        this.pavillon = pavillon;
        isActif = true;
    }

    public Chambre() {
        isActif = true;
    }
    

    public void addChambre(BoursierLoger boursierLoger) {
        boursierLogers.add(boursierLoger);
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "numChambre=" + numChambre +
                ", numEtage=" + numEtage +
                ", typeChambre=" + typeChambre +
                ", état chambre=" + isActif +
                ", pavillon=" + (pavillon != null ? pavillon.getNumero() : "null") + // Afficher seulement le numéro du pavillon
                '}';
    }
}
