package ucad.glrs.data.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Pavillon {
    private int numero;
    private int nbrEtage;
    private List<Chambre> chambres = new ArrayList<>();

    public Pavillon(int numero, int nbrEtage) {
        this.numero = numero;
        this.nbrEtage = nbrEtage;
    }

    public Pavillon() {
    }

    public void addChambre(Chambre chambre) {
        chambres.add(chambre);
    }

    @Override
    public String toString() {
        return "Pavillon{" +
                "numero=" + numero +
                ", nbrEtage=" + nbrEtage +
                ", nombre de chambres=" + chambres.size() + // Éviter d'appeler toString sur les chambres
                '}';
    }
}
