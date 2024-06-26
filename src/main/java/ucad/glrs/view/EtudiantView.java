package ucad.glrs.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ucad.glrs.core.Service;
import ucad.glrs.data.entity.BoursierLoger;
import ucad.glrs.data.entity.Chambre;
import ucad.glrs.data.entity.Etudiant;
import ucad.glrs.data.entity.EtudiantBoursier;
import ucad.glrs.data.entity.EtudiantNonBoursier;
import ucad.glrs.data.enums.TypeBourse;

public class EtudiantView extends Implementation<Etudiant> {
    private Service<Etudiant> etudiantService;
    private Service<EtudiantBoursier> etudiantBoursierService;
    private Service<EtudiantNonBoursier> etudiantNonBoursierService;
    private Service<BoursierLoger> boursierLogerService;
    private Service<Chambre> chambreService;

    private EtudiantBoursierView etudiantBoursierView;
    private BoursierLogerView boursierLogerView;
    private ChambreView chambreView;

    public EtudiantView(Service<Etudiant> etudiantService, Service<EtudiantBoursier> etudiantBoursierService,
            Service<EtudiantNonBoursier> etudiantNonBoursierService, Service<BoursierLoger> boursierLogerService,
            Service<Chambre> chambreService) {
        this.etudiantService = etudiantService;
        this.etudiantBoursierService = etudiantBoursierService;
        this.etudiantNonBoursierService = etudiantNonBoursierService;
        this.boursierLogerService = boursierLogerService;
        this.chambreService = chambreService;
        this.etudiantBoursierView = new EtudiantBoursierView();
        this.boursierLogerView = new BoursierLogerView(chambreService);
    }

    @Override
    public Etudiant saisie() {
        boolean test;
        int choix;
        String nom;
        String prenom;
        String email;
        String tel;
        String dateNaiss;
        System.out.print("Entrez le nom de l'étudiant : ");
        nom = scanner.nextLine();
        System.out.print("Entrez le prénom de l'étudiant : ");
        prenom = scanner.nextLine();
        System.out.print("Entrez l'email de l'étudiant : ");
        email = scanner.nextLine();
        System.out.print("Entrez le téléphone de l'étudiant : ");
        tel = scanner.nextLine();
        do {
            System.out.print("Entrez la date de naissance (jj/mm/aaaa) : ");
            dateNaiss = scanner.nextLine();
            test = checkDate(dateNaiss);
        } while (!test);

        Etudiant etudiant;
        do {
            System.out.println("Êtes-vous un étudiant :");
            System.out.println("1- Boursier");
            System.out.println("2- Non-Boursier");
            System.out.print("Faites votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Ajouté pour éviter les problèmes de scanner
            if (choix < 1 || choix > 2) {
                System.out.println("Erreur, choix invalide. Veuillez choisir 1 ou 2.");
            }
        } while (choix < 1 || choix > 2);

        if (choix == 1) {
            etudiant = saisieEtudiantBoursier(nom, prenom, email, tel, dateNaiss);
        } else {
            etudiant = saisieEtudiantNonBoursier(nom, prenom, email, tel, dateNaiss);
        }
        etudiantService.save(etudiant);
        System.out.println("Étudiant ajouté avec succès.");
        return etudiant;
    }

    @Override
    public void modify() {
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    public boolean checkDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(inputDate);
            System.out.println("Date valide : " + sdf.format(date));
            return true;
        } catch (ParseException e) {
            System.out.println("Date invalide : " + inputDate);
            return false;
        }
    }

    public EtudiantBoursier saisieEtudiantBoursier(String nom, String prenom, String email, String tel,
            String dateNaiss) {
        TypeBourse typeBourse = etudiantBoursierView.saisiTypeBourse();
        BoursierLoger boursierLoger = boursierLogerView.saisie();
        EtudiantBoursier etudiantBoursier;
        if (boursierLoger != null) {
            etudiantBoursier = new BoursierLoger(nom, prenom, email, tel, dateNaiss, typeBourse,
                    boursierLoger.getChambre());
            Chambre chambre = boursierLoger.getChambre();
            if (chambre != null) {
                chambre.addChambre((BoursierLoger) etudiantBoursier);
            }
            boursierLogerService.save((BoursierLoger) etudiantBoursier);
        } else {
            etudiantBoursier = new EtudiantBoursier(nom, prenom, email, tel, dateNaiss, typeBourse);
            etudiantBoursierService.save(etudiantBoursier);
        }
        return etudiantBoursier;
    }

    public EtudiantNonBoursier saisieEtudiantNonBoursier(String nom, String prenom, String email, String tel,
            String dateNaiss) {
        System.out.println("Entrez l'adresse de l'étudiant : ");
        String adresse = scanner.nextLine();
        EtudiantNonBoursier etudiantNonBoursier = new EtudiantNonBoursier(nom, prenom, email, tel, dateNaiss, adresse);
        etudiantNonBoursierService.save(etudiantNonBoursier);
        return etudiantNonBoursier;
    }

    public void listerEtudiantsBoursiers() {
        int i = 0;
        int choix;
        List<Etudiant> etudiants = etudiantService.show();
        System.out.println("Liste des étudiants boursiers :");
        for (Etudiant etudiant : etudiants) {
            if (etudiant instanceof EtudiantBoursier) {
                System.out.println(i + " <<-----------------------");
                System.out.println("*- Matricule : " + etudiant.getMatricule());
                System.out.println("*- Nom : " + etudiant.getNom());
                System.out.println("*- Prenom : " + etudiant.getPrenom());
                System.out.println("*- Email : " + etudiant.getEmail());
                System.out.println("*- Tel : " + etudiant.getTel());
                System.out.println("*- Date de naissance : " + etudiant.getDateNaiss());
                if (etudiant instanceof BoursierLoger) {
                    Chambre chambre = ((BoursierLoger) etudiant).getChambre();
                    if (chambre != null) {
                        System.out.println("*- Chambre : " + chambre.getNumChambre());
                    } else {
                        System.out.println("*- Chambre : Non attribuée");
                    }
                }
                i++;
            }
        }
        System.out.println(i + " <<- Annuler");
        do {
            System.out.print("Entrez le numéro de l'étudiant pour attribuer une chambre : ");
            choix = scanner.nextInt();
            if (choix == i) {
                System.out.println("Annulation de la saisie.");
                return;
            }
            if (choix >= 0 && choix < i) {
                Etudiant etudiantSelectionne = etudiants.get(choix);
                if (etudiantSelectionne instanceof BoursierLoger) {
                    Chambre chambre = chambreView.listChambre();
                    if (chambre != null) {
                        ((BoursierLoger) etudiantSelectionne).setChambre(chambre);
                        chambre.addChambre((BoursierLoger) etudiantSelectionne);
                        System.out.println("Chambre attribuée avec succès.");
                    } else {
                        System.out.println("Aucune chambre sélectionnée.");
                    }
                } else {
                    System.out.println("L'étudiant sélectionné n'est pas un boursier logé.");
                }
            } else {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix < 0 || choix > i);
    }

    public void listerEtudiantsDeChambre() {
        System.out.print("Entrez le numéro de la chambre : ");
        int numChambre = scanner.nextInt();
        scanner.nextLine(); // pour éviter les problèmes de scanner
        Chambre chambre = chambreService.getBy(numChambre);
        if (chambre == null) {
            System.out.println("Chambre introuvable.");
            return;
        }
        List<BoursierLoger> boursierLogers = chambre.getBoursierLogers();
        if (boursierLogers.isEmpty()) {
            System.out.println("Aucun étudiant trouvé dans cette chambre.");
        } else {
            System.out.println("Liste des étudiants de la chambre " + numChambre + " :");
            for (BoursierLoger boursierLoger : boursierLogers) {
                System.out.println("*- Matricule : " + boursierLoger.getMatricule());
                System.out.println("*- Nom : " + boursierLoger.getNom());
                System.out.println("*- Prénom : " + boursierLoger.getPrenom());
                System.out.println("*- Email : " + boursierLoger.getEmail());
                System.out.println("*- Téléphone : " + boursierLoger.getTel());
                System.out.println("*- Date de naissance : " + boursierLoger.getDateNaiss());
                System.out.println("-----------------------");
            }
        }
    }
}
