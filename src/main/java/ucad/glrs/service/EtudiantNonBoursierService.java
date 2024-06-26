package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.EtudiantNonBoursier;

public class EtudiantNonBoursierService implements Service<EtudiantNonBoursier> {
    List<EtudiantNonBoursier> etudiantNonBoursiers = new ArrayList<>();

    @Override
    public boolean save(EtudiantNonBoursier objet) {
        etudiantNonBoursiers.add(objet);
        return true;
    }

    @Override
    public List<EtudiantNonBoursier> show() {
        return etudiantNonBoursiers;
    }

    @Override
    public int count() {
        return etudiantNonBoursiers.size();
    }

    @Override
    public EtudiantNonBoursier getBy(int value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }

    @Override
    public EtudiantNonBoursier getBy(String value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }
}
