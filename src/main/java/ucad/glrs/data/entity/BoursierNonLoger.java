package ucad.glrs.data.entity;

import ucad.glrs.data.enums.TypeBourse;

public class BoursierNonLoger extends EtudiantBoursier {

    public BoursierNonLoger(String nom, String prenom, String email, String tel, String dateNaiss,
            TypeBourse typeBourse) {
        super(nom, prenom, email, tel, dateNaiss, typeBourse);
    }
    
}
