package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.Etudiant;

public class EtudiantService implements Service<Etudiant> {
    List<Etudiant> etudiants = new ArrayList<>();
    @Override
    public boolean save(Etudiant objet) {
        etudiants.add(objet);
        return true;
    }

    @Override
    public List<Etudiant> show() {
        return etudiants;
    }

    @Override
    public int count() {
        return etudiants.size();
    }

    @Override
    public Etudiant getBy(int value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }

    @Override
    public Etudiant getBy(String value) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getMatricule().compareTo(value) == 0) {
                return etudiant;
            }
        }
        return null;
    }
}
