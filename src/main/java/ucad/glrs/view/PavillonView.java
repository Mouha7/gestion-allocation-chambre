package ucad.glrs.view;

import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.Chambre;
import ucad.glrs.data.entity.Pavillon;
import ucad.glrs.data.enums.TypeChambre;
import ucad.glrs.service.PavillonService;

public class PavillonView extends Implementation<Pavillon> {
    private Service<Chambre> chambreService;
    private Service<Pavillon> pavillonService;
    // Ajouter, modifier et lister des pavillons

    public PavillonView(Service<Chambre> chambreService, Service<Pavillon> pavillonService) {
        this.chambreService = chambreService;
        this.pavillonService = pavillonService;
    }

    @Override
    public void modify() {
        if (pavillonService.count() != 0) {
            Pavillon pavillon;
            int numero;
            do {
                System.out.print("Entrez le numero de pavillon à modifier : ");
                numero = scanner.nextInt();
                pavillon = pavillonService.getBy(numero);
                if (numero < 0) {
                    System.out.println("Erreur, le numéro de pavillon ne peut pas être négatif.");
                } else if (pavillon == null) {
                    System.out.println("Erreur, ce numéro de pavillon n'existe pas !");
                }
            } while (numero < 0 || pavillon == null);
            changePavillon(pavillon);
            System.out.println("Modifier avec succès.");
        } else {
            System.out.println("Aucune pavillon n'a été encore enregistrée.");
        }
    }

    public void changePavillon(Pavillon pavillon) {
        int choix;
        System.out.println("1- Numéro Pavillon : " + pavillon.getNumero());
        System.out.println("2- Nombre Étage    : " + pavillon.getNbrEtage());
        System.out.println("3- Chambres");
        System.out.println("4- Annuler");
        System.out.print("Entrez le numéro à modifier : ");
        choix = scanner.nextInt();
        switch (choix) {
            case 1:
                int newNum;
                Pavillon pv;
                do {
                    System.out.print("1- Numéro Pavillon : ");
                    newNum = scanner.nextInt();
                    pv = pavillonService.getBy(newNum);
                    if (newNum < 0) {
                        System.out.println("Erreur, le numéro de pavillon ne peut pas être négatif.");
                    } else if (pv != null) {
                        System.out.println("Erreur, ce numéro de pavillon existe déjà !");
                    }
                } while (newNum < 0 || pv != null);
                pavillon.setNumero(newNum);
                break;
            case 2:
                int newNbrEtage;
                do {
                    System.out.print("2- Nombre Étage    : ");
                    newNbrEtage = scanner.nextInt();
                    if (newNbrEtage < 0) {
                        System.out.println("Erreur, le nombre d'étage ne peut pas être négatif.");
                    }
                } while (newNbrEtage < 0);
                pavillon.setNbrEtage(newNbrEtage);
                break;
            case 3:
                List<Chambre> chambres = pavillon.getChambres();
                if (chambres == null || chambres.isEmpty()) {
                    System.out.println("Aucune chambre n'est affectée à ce pavillon.");
                    return;
                }
                int i = 0;
                int choixChambre;
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
                System.out.println(" <<-----------------------");
                System.out.println(i + " <<- Annuler");
                System.out.print("Entrez le numéro de la chambre à modifier : ");
                choixChambre = scanner.nextInt();
                if (choixChambre != i) {
                    ChambreView chView = new ChambreView(chambreService, pavillonService);
                    chView.changeChambre(chambres.get(choixChambre));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public Pavillon saisie() {
        Pavillon pv = new Pavillon();
        pv.setNumero(saisieNumero());
        pv.setNbrEtage(saisieNbrEtage());
        System.out.print("Voulez-vous ajouter une chambre O/N: ");
        char test = scanner.next().charAt(0);
        if (test == 'o' || test == 'O') {
            char response;
            do {
                Chambre ch = saisieChambre(pv);
                if (ch != null) {
                    chambreService.save(ch);
                    pv.addChambre(ch);
                }
                System.out.print("Voulez ajouter un autre chambre O/N : ");
                response = scanner.next().charAt(0);
            } while (response == 'O' || response == 'o');
        }
        System.out.println("Pavillon ajouté avec succès.");
        return pv;
    }

    public int saisieNumero() {
        Pavillon pavillon;
        int numero;
        do {
            System.out.print("Donnez le numéro du Pavillon : ");
            numero = scanner.nextInt();
            pavillon = pavillonService.getBy(numero);
            if (numero < 0) {
                System.out.println("Erreur, le numéro de pavillon ne peux pas être négatif.");
            } else if (pavillon != null) {
                System.out.println("Erreur, ce numéro de pavillon existe déjà !");
            }
        } while (numero < 0 || pavillon != null);
        return numero;
    }

    public int saisieNbrEtage() {
        int nbrEtage;
        do {
            System.out.print("Donnez le nombre d'étage du Pavillon : ");
            nbrEtage = scanner.nextInt();
            if (nbrEtage < 0) {
                System.out.println("Erreur, le nombre d'étage ne peux pas être négatif.");
            }
        } while (nbrEtage < 0);
        return nbrEtage;
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

    public Chambre saisieChambre(Pavillon pv) {
        int numEtage;
        int numChambre;
        Chambre ch;
        do {
            System.out.print("Donnez le numéro de chambre : ");
            numChambre = scanner.nextInt();
            if (numChambre < 0) {
                System.out.println("Erreur, le numéro de chambre ne peux pas être négatif.");
            }
        } while (numChambre < 0);
        ch = chambreService.getBy(numChambre);
        boolean test = PavillonService.checkChambre(numChambre, pv);
        if (ch == null) {
            ch = new Chambre();
            do {
                System.out.print("Donnez le numéro d'étage : ");
                numEtage = scanner.nextInt();
                if (numEtage < 0) {
                    System.out.println("Erreur, le numéro d'étage ne peux pas être négatif.");
                }
            } while (numEtage < 0);
            ch.setNumChambre(numChambre);
            ch.setNumEtage(numEtage);
            ch.setTypeChambre(saisieTypeChambre());
            ch.setPavillon(pv);
        } else if (test) {
            System.out.println("La chambre est déjà enregistré dans un pavillon.");
            return null;
        } else {
            System.out.println("La chambre existe déjà et elle sera enregistré.");
        }
        return ch;
    }

    public void listerChambresDuPavillon() {
        System.out.print("Entrez l'numéro du pavillon : ");
        int pavillonId = scanner.nextInt();
        scanner.nextLine(); // pour éviter les problèmes de scanner
        Pavillon pavillon = pavillonService.getBy(pavillonId);
        if (pavillon == null) {
            System.out.println("Pavillon introuvable.");
            return;
        }

        List<Chambre> chambres = pavillon.getChambres();
        if (chambres.isEmpty()) {
            System.out.println("Aucune chambre trouvée dans ce pavillon.");
        } else {
            System.out.println("Liste des chambres du pavillon " + pavillon.getNumero() + " :");
            for (Chambre chambre : chambres) {
                System.out.println("*- Numéro de chambre : " + chambre.getNumChambre());
                System.out.println("*- Nombre d'étage    : " + chambre.getNumEtage());
                System.out.println("*- Type de chambre   : " + chambre.getTypeChambre().name());
                System.out.println("-----------------------");
            }
        }
    }
}
