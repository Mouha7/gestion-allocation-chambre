package ucad.glrs.service;

import java.util.ArrayList;
import java.util.List;

import ucad.glrs.core.Repository;
import ucad.glrs.core.Service;
import ucad.glrs.data.entity.Chambre;

public class ChambreService implements Service<Chambre> {
    private Repository<Chambre> repository;
    

    public ChambreService(Repository<Chambre> repository) {
        this.repository = repository;
    }

    public ChambreService() {
    }

    @Override
    public boolean save(Chambre objet) {
        return repository.insert(objet);
    }

    @Override
    public List<Chambre> show() {
        return repository.selectAll();
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public Chambre getBy(int value) {
        return repository.selectBy(value);
    }

    @Override
    public Chambre getBy(String value) {
        return repository.selectBy(value);
    }
    
}