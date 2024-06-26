package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.Chambre;

public class ChambreService implements Service<Chambre> {
    List<Chambre> chambres = new ArrayList<>();

    @Override
    public boolean save(Chambre objet) {
        chambres.add(objet);
        return true;
    }

    @Override
    public List<Chambre> show() {
        return chambres;
    }

    @Override
    public int count() {
        return chambres.size();
    }

    @Override
    public Chambre getBy(int value) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumChambre() == value) {
                return chambre;
            }
        }
        return null;
    }

    @Override
    public Chambre getBy(String value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }
    
}