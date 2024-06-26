package ucad.glrs.view;

import ucad.glrs.data.entity.EtudiantNonBoursier;

public class EtudiantNonBoursierView extends Implementation<EtudiantNonBoursier> {

    @Override
    public EtudiantNonBoursier saisie() {
        EtudiantNonBoursier etudiantNonBoursier = new EtudiantNonBoursier();
        System.out.print("Entrez l'adresse de l'étudiant : ");
        etudiantNonBoursier.setAdresse(scanner.nextLine());
        return etudiantNonBoursier;
    }

    @Override
    public void modify() {
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }
    
}
