package judi.example.demo.Models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MedicamentPatient extends Medicament {
    Parametre parametreConcerne;
    int effet;
    int quantite;

    public void setParametreConcerne(Parametre parametreConcerne) {
        this.parametreConcerne = parametreConcerne;
    }
    public void setEffet(int effet) {
        this.effet = effet;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }
    public Parametre getParametreConcerne() {
        return parametreConcerne;
    }
    public int getEffet() {
        return effet;
    }

    public MedicamentPatient() {}

    
    public static MedicamentPatient[] getMedicamentParametreByIdParametre(int id,Connection connection) throws Exception{
        String query = "select * from parametre_medicament where id_parametre_fk= ?";
        MedicamentPatient[] medicamentPatients;
        int size = 0;
        PreparedStatement statement = null;
		ResultSet resultset= null;
		boolean statementOpen = false;
		boolean resultsetOpen = false;
		boolean closeable = false;
		try {
            if(connection==null) {
                connection = ConnectionPostgres.connect("localhost",5432,"dokotera","postgres","mdpprom15");
				connection.setAutoCommit(false);
                closeable = true;
			}
			
			statement = connection.prepareStatement(query);
            statement.setInt(1, id);
			statementOpen = true;
			
			resultset =  statement.executeQuery();
			
			while(resultset.next()) {
                size++;
			}
            if(size==0){
                medicamentPatients = new MedicamentPatient[0];
            }else{
                medicamentPatients = new MedicamentPatient[size];
                int i = 0;
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    medicamentPatients[i] = new MedicamentPatient();
                    medicamentPatients[i].setIdMedicament(resultset.getInt("id_medicament_fk"));
                    medicamentPatients[i].setNomMedicament(Medicament.getMedicamentById(resultset.getInt("id_medicament_fk"), connection).getNomMedicament());
                    medicamentPatients[i].setParametreConcerne(Parametre.getParametreById(resultset.getInt("id_parametre_fk"), connection));
                    medicamentPatients[i].setPrix(Medicament.getMedicamentById(resultset.getInt("id_medicament_fk"), connection).getPrix());
                    medicamentPatients[i].setEffet(resultset.getInt("note_effet"));
                    medicamentPatients[i].setQuantite(0);
                    i++;
                }
            }
			statement.close();
			
		}catch (Exception e) {
			throw e;
		}finally {
			if(statementOpen) {
				statement.close();
			}
			if(resultsetOpen) {
				resultset.close();
			}
			if(closeable) {
				connection.commit();
				connection.close();
			}
		}
        return medicamentPatients;
    }
}
