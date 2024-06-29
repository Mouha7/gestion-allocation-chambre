package ucad.glrs.data.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ucad.glrs.data.entity.Chambre;
import ucad.glrs.data.entity.Pavillon;
import ucad.glrs.data.enums.TypeChambre;

public class ChambreDB extends RepositoryDB<Chambre> {
    public ChambreDB() {
        this.ouvrir();
    }

    @Override
    public boolean insert(Chambre object) {
        String req;
        int isActif = object.getIsActif() ? 1 : 0;
        int typeChambreId = (object.getTypeChambre().ordinal() + 1);
        if (object.getPavillon() == null) {
            req = String.format(
                    "INSERT INTO `chambre` (`numero_chambre`, `numero_etage`, `is_actif`, `type_chambre_id`) VALUES ('%d', '%d', '%d', '%s');",
                    object.getNumChambre(), object.getNumEtage(), isActif, typeChambreId);
        } else {
            req = String.format(
                    "INSERT INTO `chambre` (`numero_chambre`, `numero_etage`, `is_actif`, `pavillon_id`, `type_chambre_id`) VALUES ('%d', '%d', '%d', '%d', '%s');",
                    object.getNumChambre(), object.getNumEtage(), isActif, object.getPavillon().getId(),
                    typeChambreId);
        }
        return executeUpdate(req);
    }

    @Override
    public List<Chambre> selectAll() {
        List<Chambre> chambres = new ArrayList<>();
        try {
            ResultSet rs = executeSelect(
                    "SELECT * FROM `chambre` c, `type_chambre` t WHERE c.id_chambre = t.id_type_chambre;");
            while (rs.next()) {
                chambres.add(this.convert(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erreur de requête");
        }
        return chambres;
    }

    public Chambre convert(ResultSet rs) {
        Chambre chambre = null;
        try {
            chambre = new Chambre();
            chambre.setId(rs.getInt("id_chambre"));
            chambre.setNumChambre(rs.getInt("numero_chambre"));
            chambre.setNumEtage(rs.getInt("numero_etage"));
            chambre.setActif(rs.getBoolean("is_actif"));
            int idPavillon = rs.getInt("pavillon_id");
            if (rs.wasNull()) {
                chambre.setPavillon(null);
            } else {
                Pavillon pavillon = new Pavillon();
                pavillon.setId(idPavillon);
                chambre.setPavillon(pavillon);
            }
            chambre.setTypeChambre(TypeChambre.get(rs.getString("nom_type_chambre")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chambre;
    }

    @Override
    public boolean update(Chambre object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Chambre selectBy(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectBy'");
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public Chambre selectBy(int value) {
        String sql = String.format(
                "SELECT * FROM `chambre` c, `type_chambre` t  WHERE id_chambre = %s and c.id_chambre = t.id_type_chambre;",
                value);
        ResultSet rs = super.executeSelect(sql);
        Chambre chambre = null;
        try {
            while (rs.next()) {
                chambre = this.convert(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chambre;

    }
}
