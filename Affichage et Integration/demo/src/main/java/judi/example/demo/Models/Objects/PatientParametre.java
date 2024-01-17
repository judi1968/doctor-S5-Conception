package judi.example.demo.Models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientParametre extends Patient {
    Parametre parametre;
    int noteParametre;

    public void setNoteParametre(int noteParametre) {
        this.noteParametre = noteParametre;
    }
    public void setParametre(Parametre parametre) {
        this.parametre = parametre;
    }

    public Parametre getParametre() {
        return parametre;
    }
    public int getNoteParametre() {
        return noteParametre;
    }

    public PatientParametre(){}
    public PatientParametre(Parametre parametre,int note){
        setParametre(parametre);
        setNoteParametre(note);
    }
    public PatientParametre(int id_patient,String nomPatient, int age,Parametre parametre , int note){
        super(id_patient, nomPatient, age);
        setNoteParametre(note);
        setParametre(parametre);
    }

    public void addParametrePatient(Connection connection) throws Exception{
        String query = "INSERT INTO \"public\".parametre_patient\r\n" + //
                "\t( id_patient_fk, id_parametre_fk, note_parametre) VALUES ( ?, ?, ? );";
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
            statement.setInt(1,this.getIdPatient());
            statement.setInt(2,this.getParametre().getIdParametre());
            statement.setInt(3, this.getNoteParametre());
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

    
    public static PatientParametre[] getAllPatientParametreByIdPatient(int id,Connection connection) throws Exception{
        String query = "select * from parametre_patient where id_patient_fk= ? ";
        PatientParametre[] parametres;
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
                parametres = new PatientParametre[0];
            }else{
                parametres = new PatientParametre[size];
                int i = 0;
                resultset =  statement.executeQuery();
                Patient patient = Patient.getPatientById(id, connection);
                while(resultset.next()){
                    //id_patient_fk | note_parametre | id_parametre_fk
                    parametres[i] = new PatientParametre();
                    parametres[i].setAge(patient.getAge());
                    parametres[i].setIdPatient(patient.getIdPatient());
                    parametres[i].setNomPatient(patient.getNomPatient());
                    parametres[i].setParametre(Parametre.getParametreById(resultset.getInt("id_parametre_fk"), connection));
                    parametres[i].setNoteParametre(resultset.getInt("note_parametre"));
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
        return parametres;
    }

    public MedicamentPatient getAllMedicamentsByParametrePatient() throws Exception{
        //System.out.println(patientParametre.getParametre().getNomParametre()+" : "+patientParametre.getNoteParametre());
        // System.out.println("Traitement du parametre : "+this.getParametre().getNomParametre()+" : note : "+this.getNoteParametre());
        // alaina ny fanafody rehetra de jerena ny composition fanafody mora indrindra
        MedicamentPatient[] medicamentPatients = MedicamentPatient.getMedicamentParametreByIdParametre(this.getParametre().getIdParametre(), null);
        MedicamentPatient medicamentPatientsResultat = null;
        double prix_max = 0;
        for (MedicamentPatient medicamentPatient : medicamentPatients) {
            int effet_medicament = medicamentPatient.getEffet();
            double prix = medicamentPatient.getPrix();
            int quantite = 1;
                                                            // 7                                4
            System.out.println("    note du patient : " +this.getNoteParametre()+" les medicaments sont : "+medicamentPatient.getNomMedicament()+" : effet "+medicamentPatient.getEffet()+" : prix : "+medicamentPatient.getPrix());
            while (effet_medicament<this.getNoteParametre()) {
                effet_medicament = effet_medicament + medicamentPatient.getEffet();
                prix = prix + medicamentPatient.getPrix();
                quantite++;//1
            }
            medicamentPatient.setEffet(effet_medicament);
            medicamentPatient.setPrix(prix);
            medicamentPatient.setQuantite(quantite);
            if (prix_max<medicamentPatient.getPrix()) {
                prix_max = medicamentPatient.getPrix()+1;
            }
        }
        // System.out.println();
        System.out.println("        Effet du traitement avec leur prix");
        double prix_resultat = prix_max;
        for (MedicamentPatient medicamentPatient : medicamentPatients) {
            if (prix_resultat>medicamentPatient.getPrix()) {
                medicamentPatientsResultat = medicamentPatient;
                prix_resultat = medicamentPatient.getPrix();
            }
                        System.out.println("            les medicaments : "+medicamentPatient.getNomMedicament()+" : effet : "+medicamentPatient.getEffet()+" quatite : "+medicamentPatient.getQuantite()+" : prix : "+medicamentPatient.getPrix());
        }
        return medicamentPatientsResultat;
    }

}
