package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Medicament {
    int idMedicament;
    String nomMedicament;
    double prix;
    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }
    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getIdMedicament() {
        return idMedicament;
    }
    public String getNomMedicament() {
        return nomMedicament;
    }
    public double getPrix() {
        return prix;
    }

    public Medicament(){}

      
    public static Medicament[] getAllMedicament(Connection connection) throws Exception{
        String query = "select * from medicament";
        Medicament[] medicaments;
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

			statementOpen = true;
			
			resultset =  statement.executeQuery();
			
			while(resultset.next()) {
                size++;
			}
            if(size==0){
                medicaments = new Medicament[0];
            }else{
                medicaments = new Medicament[size];
                int i = 0;
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    medicaments[i] = new Medicament();
                    medicaments[i].setIdMedicament(resultset.getInt("id_medicament"));
                    medicaments[i].setNomMedicament(resultset.getString("designation"));
                    medicaments[i].setPrix(resultset.getDouble("prix"));
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
        return medicaments;
    }

    public static Medicament getMedicamentById(int id,Connection connection) throws Exception{
        String query = "select * from medicament where id_medicament = ?";
        Medicament medicament = new Medicament();
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
                while(resultset.next()){
                    medicament.setIdMedicament(resultset.getInt("id_medicament"));
                    medicament.setNomMedicament(resultset.getString("designation"));
                    medicament.setPrix(resultset.getDouble("prix"));
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
        return medicament;
    }

}
