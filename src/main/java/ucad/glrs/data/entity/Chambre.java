package ucad.glrs.data.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ucad.glrs.data.enums.TypeChambre;

@Data
public class Chambre {
    private int id;
    private static int nbr;
    private int numChambre;
    private int numEtage;
    private TypeChambre typeChambre;
    private Pavillon pavillon; // Mettre ce champ à null dans la BD
    private boolean isActif;
    private List<BoursierLoger> boursierLogers = new ArrayList<>();

    public Chambre(int numChambre, int numEtage, TypeChambre typeChambre, Pavillon pavillon) {
        this.id = ++nbr;
        this.numChambre = numChambre;
        this.numEtage = numEtage;
        this.typeChambre = typeChambre;
        this.pavillon = pavillon;
        this.isActif = true;
    }

    public Chambre() {
        this.id = ++nbr;
        this.isActif = true;
    }
    
    public boolean getIsActif() {
        return isActif;
    }

    public void addChambre(BoursierLoger boursierLoger) {
        boursierLogers.add(boursierLoger);
    }

    @Override
    public String toString() {
        return "Chambre{" + "ID: " + id +
                ", numChambre=" + numChambre +
                ", numEtage=" + numEtage +
                ", typeChambre=" + typeChambre +
                ", état chambre=" + isActif +
                ", pavillon=" + (pavillon != null ? pavillon.getNumero() : "null") + // Afficher seulement le numéro du pavillon
                '}';
    }
}
