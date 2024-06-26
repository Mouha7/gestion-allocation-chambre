package ucad.glrs.data.entity;

import lombok.Data;
import ucad.glrs.data.enums.TypeBourse;

@Data
public class EtudiantBoursier extends Etudiant {
    private TypeBourse typeBourse;


    public EtudiantBoursier() {
    }


    public EtudiantBoursier(String nom, String prenom, String email, String tel, String dateNaiss, TypeBourse typeBourse) {
        super(nom, prenom, email, tel, dateNaiss);
        this.typeBourse = typeBourse;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((typeBourse == null) ? 0 : typeBourse.hashCode());
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
        EtudiantBoursier other = (EtudiantBoursier) obj;
        if (typeBourse != other.typeBourse)
            return false;
        return true;
    }
}
