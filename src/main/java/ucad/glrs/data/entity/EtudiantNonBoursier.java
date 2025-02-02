package ucad.glrs.data.entity;

import java.util.Date;

import lombok.Data;

@Data
public class EtudiantNonBoursier extends Etudiant {
    private int id;
    private static int nbr;
    private String adresse;

    public EtudiantNonBoursier() {
        this.id = ++nbr;
    }

    public EtudiantNonBoursier(String nom, String prenom, String email, String tel, Date dateNaiss, String adresse) {
        super(nom, prenom, email, tel, dateNaiss);
        this.id = ++nbr;
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EtudiantNonBoursier other = (EtudiantNonBoursier) obj;
        if (adresse == null) {
            if (other.adresse != null)
                return false;
        } else if (!adresse.equals(other.adresse))
            return false;
        return true;
    }
}
