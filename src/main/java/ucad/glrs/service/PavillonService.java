package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.Chambre;
import ucad.glrs.data.entity.Pavillon;

public class PavillonService implements Service<Pavillon> {
    List<Pavillon> pavillons = new ArrayList<>();

    @Override
    public boolean save(Pavillon objet) {
        pavillons.add(objet);
        return true;
    }

    @Override
    public List<Pavillon> show() {
        return pavillons;
    }

    @Override
    public int count() {
        return pavillons.size();
    }

    @Override
    public Pavillon getBy(int value) {
        for (Pavillon pavillon : pavillons) {
            if (pavillon.getNumero() == value) {
                return pavillon;
            }
        }
        return null;
    }

    public static boolean checkChambre(int value, Pavillon pavillon) {
        for (Chambre chambre : pavillon.getChambres()) {
            if (chambre.getNumChambre() == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Pavillon getBy(String value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }
}
