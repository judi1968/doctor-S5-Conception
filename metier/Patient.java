/**
 * Patient
 */
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Patient {

    int idPatient;
    String nomPatient;
    int age;

    public void setAge(int age) {
        this.age = age;
    }
    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }
    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public int getAge() {
        return age;
    }
    public int getIdPatient() {
        return idPatient;
    }
    public String getNomPatient() {
        return nomPatient;
    }

    public Patient(){}
    public Patient(int idPatient,String nomPatient,int age){
        setAge(age);
        setIdPatient(idPatient);
        setNomPatient(nomPatient);
    }
    public Patient(String nomPatient,int age){
        setAge(age);
        setNomPatient(nomPatient);
    }

    // ajouter patient 
    public void createNewPatient(Connection connection) throws Exception{
        String query = "INSERT INTO \"public\".patient\r\n" + //
                "\t(  nom, ans) VALUES ( ?, ? );";
        PreparedStatement statement = null;
		ResultSet resultset= null;
		boolean statementOpen = false;
		boolean resultsetOpen = false;
		boolean closeable = false;
        double prix = 0;
		try {
            if(connection==null) {
                connection = ConnectionPostgres.connect("localhost",5432,"dokotera","postgres","mdpprom15");
				connection.setAutoCommit(false);
                closeable = true;
			}
			
			statement = connection.prepareStatement(query);
            statement.setString(1,this.getNomPatient());
            statement.setInt(2,this.getAge());
			statementOpen = true;
			
			statement.executeUpdate();
		
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
    }

    // prendre le dernier id du patient 
    public static int getLastIdPatient(Connection connection) throws Exception{
        String query = "select max(id_patient) id_patient from patient";
        PreparedStatement statement = null;
		ResultSet resultset= null;
		boolean statementOpen = false;
		boolean resultsetOpen = false;
		boolean closeable = false;
        int id = 0;
		try {
            if(connection==null) {
                connection = ConnectionPostgres.connect("localhost",5432,"dokotera","postgres","mdpprom15");
				connection.setAutoCommit(false);
                closeable = true;
			}
			
			statement = connection.prepareStatement(query);
			statementOpen = true;
			
			resultset =  statement.executeQuery();
			
		
            while(resultset.next()){
                id = resultset.getInt("id_patient");
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
        return id;
    }

    // prendre le patient par Id
    public static Patient getPatientById(int id,Connection connection) throws Exception{
        String query = "select * from patient where id_patient = ?";
        Patient patient = new Patient();
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
                    patient.setIdPatient(resultset.getInt("id_patient"));
                    patient.setNomPatient(resultset.getString("nom"));
                    patient.setAge(resultset.getInt("ans"));
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
        return patient;
    }
}