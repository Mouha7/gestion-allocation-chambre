package ucad.glrs.data.repository;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Repository;
import ucad.glrs.data.entity.Chambre;

public class ChambreList implements Repository<Chambre> {
    List<Chambre> chambres = new ArrayList<>();

    @Override
    public boolean insert(Chambre objet) {
        chambres.add(objet);
        return true;
    }

    @Override
    public List<Chambre> selectAll() {
        return chambres;
    }

    @Override
    public int count() {
        return chambres.size();
    }

    @Override
    public Chambre selectBy(int value) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumChambre() == value) {
                return chambre;
            }
        }
        return null;
    }

    @Override
    public Chambre selectBy(String value) {
        return null;
    }

    @Override
    public boolean update(Chambre object) {
        return true;
    }
    
}