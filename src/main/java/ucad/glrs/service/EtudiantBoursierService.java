package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.EtudiantBoursier;

public class EtudiantBoursierService implements Service<EtudiantBoursier> {
    List<EtudiantBoursier> etudiantBoursiers = new ArrayList<>();

    @Override
    public boolean save(EtudiantBoursier objet) {
        etudiantBoursiers.add(objet);
        return true;
    }

    @Override
    public List<EtudiantBoursier> show() {
        return etudiantBoursiers;
    }

    @Override
    public int count() {
        return etudiantBoursiers.size();
    }

    @Override
    public EtudiantBoursier getBy(int value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }

    @Override
    public EtudiantBoursier getBy(String value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }
}
