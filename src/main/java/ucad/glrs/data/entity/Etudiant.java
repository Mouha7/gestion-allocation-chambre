package ucad.glrs.data.entity;

import lombok.Data;

@Data
public class Etudiant {
    private static int nbr;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String dateNaiss;
    
    public Etudiant() {
        int size=String.valueOf(++nbr).length();
        matricule = "MAT"+"O".repeat(size>4?0:4-size)+(++nbr);
    }

    public Etudiant(String nom, String prenom, String email, String tel, String dateNaiss) {
        int size=String.valueOf(++nbr).length();
        matricule = "MAT"+"O".repeat(size>4?0:4-size)+(++nbr);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.dateNaiss = dateNaiss;
    }
}
