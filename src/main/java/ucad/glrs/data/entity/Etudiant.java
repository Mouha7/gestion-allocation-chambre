package ucad.glrs.data.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Etudiant {
    private int id;
    private static int nbr;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private Date dateNaiss;
    
    public Etudiant() {
        this.id = ++nbr;
        int size=String.valueOf(id).length();
        this.matricule = "MAT"+"O".repeat(size>4?0:4-size)+(id);
    }

    public Etudiant(String nom, String prenom, String email, String tel, Date dateNaiss) {
        this.id = ++nbr;
        int size=String.valueOf(id).length();
        this.matricule = "MAT"+"O".repeat(size>4?0:4-size)+(id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.dateNaiss = dateNaiss;
    }
}
