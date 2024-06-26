package ucad.glrs.data.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RP {
    private List<Etudiant> etudiants = new ArrayList<>();
    private List<Chambre> chambres = new ArrayList<>();
    private List<Pavillon> pavillons = new ArrayList<>();

    public void addEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
    }

    public void addChambre(Chambre chambre) {
        chambres.add(chambre);
    }

    public void addPavillon(Pavillon pavillon) {
        pavillons.add(pavillon);
    }
}
