package ucad.glrs.view;

import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.Chambre;
import ucad.glrs.data.entity.Pavillon;
import ucad.glrs.data.enums.TypeChambre;

public class ChambreView extends Implementation<Chambre> {
    private Service<Chambre> chambreService;
    private Service<Pavillon> pavillonService;

    public ChambreView(Service<Chambre> chambreService, Service<Pavillon> pavillonService) {
        this.chambreService = chambreService;
        this.pavillonService = pavillonService;
    }

    public void changeState() {
        if (chambreService.count() != 0) {
            Chambre chambre;
            int numChambre;
            do {
                System.out.print("Entrez le numero de Chambre : ");
                numChambre = scanner.nextInt();
                chambre = chambreService.getBy(numChambre);
                if (numChambre < 0) {
                    System.out.println("Erreur, le numéro de chambre ne peut pas être négatif.");
                } else if (chambre == null) {
                    System.out.println("Erreur, ce numéro de chambre n'existe pas !");
                }
            } while (numChambre < 0 || chambre == null);
            chambre.setActif(false);
            System.out.println("Modifié avec succès.");
        } else {
            System.out.println("Aucune chambre n'a été encore enregistrée.");
        }
    }

    @Override
    public void modify() {
        if (chambreService.count() != 0) {
            Chambre chambre;
            int numChambre;
            do {
                System.out.print("Entrez le numero de chambre à modifier : ");
                numChambre = scanner.nextInt();
                chambre = chambreService.getBy(numChambre);
                if (numChambre < 0) {
                    System.out.println("Erreur, le numéro de chambre ne peut pas être négatif.");
                } else if (chambre == null) {
                    System.out.println("Erreur, ce numéro de chambre n'existe pas !(0 pour sortir)");
                }
            } while (numChambre < 0 || chambre == null);
            changeChambre(chambre);
        } else {
            System.out.println("Aucune chambre n'a été encore enregistrée.");
        }
    }

    @Override
    public Chambre saisie() {
        int numEtage;
        int numChambre;
        Chambre ch = new Chambre();
        do {
            System.out.print("Entrez le numéro de chambre : ");
            numChambre = scanner.nextInt();
            if (numChambre < 0) {
                System.out.println("Erreur, le numéro de chambre ne peut pas être négatif.");
            } else if (chambreService.getBy(numChambre) != null) {
                System.out.println("Erreur, ce numéro de chambre existe déjà !");
            }
        } while (numChambre < 0 || chambreService.getBy(numChambre) != null);
        do {
            System.out.print("Entrez le numéro d'étage : ");
            numEtage = scanner.nextInt();
            if (numEtage < 0) {
                System.out.println("Erreur, le numéro d'étage ne peut pas être négatif.");
            }
        } while (numEtage < 0);
        ch.setNumChambre(numChambre);
        ch.setNumEtage(numEtage);
        ch.setTypeChambre(saisieTypeChambre());
        ch.setPavillon(saisiePavillon());
        System.out.println("Chambre ajoutée avec succès.");
        return ch;
    }

    public TypeChambre saisieTypeChambre() {
        int type;
        do {
            System.out.println("Quel est le type de la chambre ?");
            System.out.println("1- Individuel");
            System.out.println("2- À Deux");
            System.out.print("Faites votre choix : ");
            type = scanner.nextInt();
            if (type < 1 || type > 2) {
                System.out.println("Erreur, choix invalide. Veuillez choisir 1 ou 2.");
            }
        } while (type < 1 || type > 2);
        return TypeChambre.values()[type - 1];
    }

    public Pavillon saisiePavillon() {
        Pavillon pavillon = null;
        char response;
        int numPv;
        do {
            System.out.print("Voulez-vous ajouter un pavillon O/N ? ");
            response = scanner.next().charAt(0);
        } while (response != 'O' && response != 'o' && response != 'N' && response != 'n');
        if (response == 'o' || response == 'O') {
            if (pavillonService.count() != 0) {
                do {
                    System.out.print("Entrez le numéro de pavillon : ");
                    numPv = scanner.nextInt();
                    pavillon = pavillonService.getBy(numPv);
                    if (pavillon == null) {
                        System.out.println("Erreur, le numéro de pavillon saisi n'existe pas !");
                    }
                } while (pavillon == null);
            } else {
                System.out.println("Aucun pavillon n'existe encore.");
            }
        }
        return pavillon;
    }

    public void changeChambre(Chambre chambre) {
        int choix;
        System.out.println("1- Numéro Chambre  : " + chambre.getNumChambre());
        System.out.println("2- Numéro Étage    : " + chambre.getNumEtage());
        System.out.println("3- Type Chambre    : " + chambre.getTypeChambre());
        if (chambre.getPavillon() != null) {
            System.out.println("4- Numéro Pavillon : " + chambre.getPavillon().getNumero());
        } else {
            System.out.println("4- Numéro Pavillon : non assigné");
        }
        System.out.println("5- Annuler");
        System.out.print("Entrez le numéro à modifier : ");
        choix = scanner.nextInt();
        switch (choix) {
            case 1:
                int newNumChambre;
                Chambre ch;
                do {
                    System.out.print("1- Numéro Chambre  : ");
                    newNumChambre = scanner.nextInt();
                    ch = chambreService.getBy(newNumChambre);
                    if (newNumChambre < 0) {
                        System.out.println("Erreur, le numéro de chambre ne peut pas être négatif.");
                    } else if (ch != null) {
                        System.out.println("Erreur, ce numéro de chambre existe déjà !");
                    }
                } while (newNumChambre < 0 || ch != null);
                chambre.setNumChambre(newNumChambre);
                break;
            case 2:
                int newNumEtage;
                do {
                    System.out.print("2- Numéro Étage    : ");
                    newNumEtage = scanner.nextInt();
                    if (newNumEtage < 0) {
                        System.out.println("Erreur, le numéro d'étage ne peut pas être négatif.");
                    }
                } while (newNumEtage < 0);
                chambre.setNumEtage(newNumEtage);
                break;
            case 3:
                chambre.setTypeChambre(saisieTypeChambre());
                break;
            case 4:
                Pavillon pavillon;
                int pv;
                do {
                    System.out.print("4- Numéro Pavillon : ");
                    pv = scanner.nextInt();
                    pavillon = pavillonService.getBy(pv);
                    if (pavillon == null) {
                        System.out.println("Erreur, ce numéro de pavillon n'existe pas !(0 pour sortir)");
                    }
                } while (pavillon == null || pv == 0);
                chambre.setPavillon(pavillon);
                break;
            default:
                break;
        }
    }

    public Chambre listChambre() {
        List<Chambre> chambres = chambreService.show();
        if (chambres.isEmpty()) {
            System.out.println("Aucune chambre n'a été enregistrée.");
            return null;
        }
        
        int choix;
        int i = 0;
        
        // Afficher la liste des chambres
        for (Chambre chambre : chambres) {
            System.out.println(i + " <<-----------------------");
            System.out.println("Numéro Chambre : " + chambre.getNumChambre());
            System.out.println("Numéro Étage : " + chambre.getNumEtage());
            System.out.println("Type Chambre : " + chambre.getTypeChambre());
            if (chambre.getPavillon() != null) {
                System.out.println("Numéro Pavillon : " + chambre.getPavillon().getNumero());
            } else {
                System.out.println("Numéro Pavillon : non assigné");
            }
            i++;
        }
        
        System.out.println(i + " <<- Annuler");
        
        // Gérer le choix de l'utilisateur
        do {
            System.out.print("Entrez le numéro de chambre à affecter au étudiant : ");
            choix = scanner.nextInt();
            if (choix == i) {
                System.out.println("Annulation de la saisie.");
                return null;
            }
            if (choix < 0 || choix >= i) {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix < 0 || choix >= i);
        
        return chambres.get(choix);
    }
    
}
