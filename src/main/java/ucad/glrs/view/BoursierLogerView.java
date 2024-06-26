package ucad.glrs.view;

import java.util.List;
import java.util.Scanner;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.BoursierLoger;
import ucad.glrs.data.entity.Chambre;

public class BoursierLogerView extends Implementation<BoursierLoger> {
    private Service<Chambre> chambreService;

    public BoursierLogerView() {
    }

    public BoursierLogerView(Service<Chambre> chambreService) {
        this.chambreService = chambreService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public BoursierLoger saisie() {
        BoursierLoger boursierLoger = new BoursierLoger();
        if (chambreService.count() != 0) {
            int i = 0;
            int choixChambre;
            List<Chambre> chambres = chambreService.show();
            for (Chambre chambre : chambres) {
                System.out.println(i + " <<-----------------------");
                System.out.println("*- Numéro Chambre  : " + chambre.getNumChambre());
                System.out.println("*- Numéro Étage    : " + chambre.getNumEtage());
                System.out.println("*- Type Chambre    : " + chambre.getTypeChambre());
                if (chambre.getPavillon() != null) {
                    System.out.println("*- Numéro Pavillon : " + chambre.getPavillon().getNumero());
                } else {
                    System.out.println("*- Numéro Pavillon : non assigné");
                }
                i++;
            }
            System.out.println(i + " <<- Annuler");
            do {
                System.out.print("Entrez le numéro de la chambre à lui affecter : ");
                choixChambre = scanner.nextInt();
                if (choixChambre == i) {
                    System.out.println("Annulation de la saisie.");
                    return null; // Retourne null si l'utilisateur choisit d'annuler
                }
                if (choixChambre >= 0 && choixChambre < i) {
                    boursierLoger.setChambre(chambres.get(choixChambre));
                } else {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } while (boursierLoger.getChambre() == null);
        } else {
            System.out.println("Aucune chambre n'a encore été enregistrée.");
        }
        return boursierLoger;
    }

    @Override
    public void modify() {
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }
}
