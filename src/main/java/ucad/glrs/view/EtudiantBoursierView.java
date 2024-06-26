package ucad.glrs.view;

import ucad.glrs.data.entity.EtudiantBoursier;
import ucad.glrs.data.enums.TypeBourse;

public class EtudiantBoursierView extends Implementation<EtudiantBoursier> {

    @Override
    public EtudiantBoursier saisie() {
        EtudiantBoursier etudiantBoursier = new EtudiantBoursier();
        
        etudiantBoursier.setTypeBourse(saisiTypeBourse());
        return etudiantBoursier;
    }

    @Override
    public void modify() {
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }
    
    public TypeBourse saisiTypeBourse() {
        int type;
        do {
            System.out.println("Quel est son type de bourse");
            System.out.println("1- Demi Bourse("+TypeBourse.DEMIBOURSE.getMontant()+")");
            System.out.println("2- Bourse Complète("+TypeBourse.BOURSECOMPLET.getMontant()+")");
            System.out.print("Faites votre choix : ");
            type = scanner.nextInt();
            if (type < 1 || type > 2) {
                System.out.println("Erreur, choix invalide. Veuillez choisir 1 ou 2.");
            }
        } while (type < 1 || type > 2);
        return TypeBourse.values()[type - 1];
    }
}
