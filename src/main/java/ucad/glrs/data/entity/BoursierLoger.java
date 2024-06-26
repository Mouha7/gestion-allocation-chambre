package ucad.glrs.data.entity;

import lombok.Data;
import ucad.glrs.data.enums.TypeBourse;

@Data
public class BoursierLoger extends EtudiantBoursier {
    private Chambre chambre;
    
    public BoursierLoger() {
    }

    public BoursierLoger(String nom, String prenom, String email, String tel, String dateNaiss,
            TypeBourse typeBourse, Chambre chambre) {
        super(nom, prenom, email, tel, dateNaiss, typeBourse);
        this.chambre = chambre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((chambre == null) ? 0 : chambre.hashCode());
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
        BoursierLoger other = (BoursierLoger) obj;
        if (chambre == null) {
            if (other.chambre != null)
                return false;
        } else if (!chambre.equals(other.chambre))
            return false;
        return true;
    }
    
}
