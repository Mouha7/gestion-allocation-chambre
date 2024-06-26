package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.BoursierLoger;

public class BousierLogerService implements Service<BoursierLoger> {
    List<BoursierLoger> boursierLogers = new ArrayList<>();

    @Override
    public boolean save(BoursierLoger objet) {
        boursierLogers.add(objet);
        return true;
    }

    @Override
    public List<BoursierLoger> show() {
        return boursierLogers;
    }

    @Override
    public int count() {
        return boursierLogers.size();
    }

    @Override
    public BoursierLoger getBy(int value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }

    @Override
    public BoursierLoger getBy(String value) {
        throw new UnsupportedOperationException("Unimplemented method 'getBy'");
    }
}
