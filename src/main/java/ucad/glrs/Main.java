package ucad.glrs;

import java.util.Scanner;

import ucad.glrs.core.Repository;
import ucad.glrs.core.Service;
import ucad.glrs.data.entity.BoursierLoger;
import ucad.glrs.data.entity.Chambre;
import ucad.glrs.data.entity.Etudiant;
import ucad.glrs.data.entity.EtudiantBoursier;
import ucad.glrs.data.entity.EtudiantNonBoursier;
import ucad.glrs.data.entity.Pavillon;
import ucad.glrs.data.repository.ChambreDB;
import ucad.glrs.data.repository.ChambreList;
import ucad.glrs.service.BousierLogerService;
import ucad.glrs.service.ChambreService;
import ucad.glrs.service.EtudiantBoursierService;
import ucad.glrs.service.EtudiantNonBoursierService;
import ucad.glrs.service.EtudiantService;
import ucad.glrs.service.PavillonService;
import ucad.glrs.view.BoursierLogerView;
import ucad.glrs.view.ChambreView;
import ucad.glrs.view.EtudiantView;
import ucad.glrs.view.PavillonView;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Repository<Chambre> chambreRepository = new ChambreList();
        Repository<Chambre> chambreRepository = new ChambreDB();

        Service<Pavillon> pavillonService = new PavillonService();
        Service<Chambre> chambreService = new ChambreService(chambreRepository);
        Service<Etudiant> etudiantService = new EtudiantService();
        Service<EtudiantBoursier> etudiantBoursierService = new EtudiantBoursierService();
        Service<EtudiantNonBoursier> etudiantNonBoursierService = new EtudiantNonBoursierService();
        Service<BoursierLoger> boursierLogerService = new BousierLogerService();

        PavillonView pavillonView = new PavillonView(chambreService, pavillonService);
        ChambreView chambreView = new ChambreView(chambreService, pavillonService);
        EtudiantView etudiantView = new EtudiantView(etudiantService, etudiantBoursierService,
                etudiantNonBoursierService, boursierLogerService, chambreService);
        BoursierLogerView boursierLogerView = new BoursierLogerView();
        int choix;
        do {
            choix = menu();
            switch (choix) {
                case 1:
                    pavillonService.save(pavillonView.saisie());
                    break;
                case 2:
                    pavillonView.affiche(pavillonService.show());
                    pavillonView.modify();
                    break;
                case 3:
                    pavillonView.affiche(pavillonService.show());
                    break;
                case 4:
                    chambreService.save(chambreView.saisie());
                    break;
                case 5:
                    chambreView.affiche(chambreService.show());
                    chambreView.modify();
                    break;
                case 6:
                    chambreView.affiche(chambreService.show());
                    break;
                case 7:
                    chambreView.affiche(chambreService.show());
                    chambreView.changeState();
                    break;
                case 8:
                    etudiantView.saisie();
                    break;
                case 9:
                    etudiantView.listerEtudiantsBoursiers();
                    break;
                case 10:
                    pavillonView.listerChambresDuPavillon();
                    break;
                case 11:
                    chambreView.affiche(chambreService.show());
                    etudiantView.listerEtudiantsDeChambre();
                    break;
                case 12:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Erreur, choix invalide.");
                    break;
            }
        } while (choix != 12);
    }

    public static int menu() {
        System.out.println("------Menu principal------");
        System.out.println("1-  Ajouter des pavillons.");
        System.out.println("2-  Modifier un pavillon.");
        System.out.println("3-  Lister les pavillons.");
        System.out.println("4-  Ajouter des chambres.");
        System.out.println("5-  Modifier un chambre.");
        System.out.println("6-  Lister les chambres.");
        System.out.println("7-  Archiver un chambre.");
        System.out.println("8-  Ajouter un étudiant");
        System.out.println("9-  Affecter une chambre à un boursier logé.");
        System.out.println("10- Lister les chambres d’un pavillon.");
        System.out.println("11- Lister les étudiants d’une chambre.");
        System.out.println("12- Quitter");
        System.out.print("Faites votre choix: ");
        return scanner.nextInt();
    }
}