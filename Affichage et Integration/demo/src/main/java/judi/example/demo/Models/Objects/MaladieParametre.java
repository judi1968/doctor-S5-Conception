package judi.example.demo.Models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MaladieParametre extends Maladie{
    Parametre parametre;
    int noteMinimal;
    int noteMaximal;
    int ageMinimal;
    int ageMaximal;

    public void setAgeMaximal(int ageMaximal) {
        this.ageMaximal = ageMaximal;
    }
    public void setParametre(Parametre parametre) {
        this.parametre = parametre;
    }
    public void setNoteMaximal(int noteMaximal) {
        this.noteMaximal = noteMaximal;
    }
    public void setNoteMinimal(int noteMinimal) {
        this.noteMinimal = noteMinimal;
    }
    public void setAgeMinimal(int ageMinimal) {
        this.ageMinimal = ageMinimal;
    }
    
    public Parametre getParametre() {
        return parametre;
    }
    public int getNoteMinimal() {
        return noteMinimal;
    }
    public int getNoteMaximal() {
        return noteMaximal;
    }
    public int getAgeMinimal() {
        return ageMinimal;
    }
    public int getAgeMaximal() {
        return ageMaximal;
    }

    public MaladieParametre(){}

        
    public static MaladieParametre[] getAllMaladieParametresInMaladie(int id,Connection connection) throws Exception{
        String query = "select * from parametre_maladie where id_maladie_fk= ?";
        MaladieParametre[] parametres;
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
                parametres = new MaladieParametre[0];
            }else{
                parametres = new MaladieParametre[size];
                int i = 0;
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    parametres[i] = new MaladieParametre();
                    parametres[i].setAgeMaximal(resultset.getInt("an_max"));
                    parametres[i].setAgeMinimal(resultset.getInt("an_min"));
                    parametres[i].setIdMaladie(resultset.getInt("id_maladie_fk"));
                    parametres[i].setNoteMaximal(resultset.getInt("note_max"));
                    parametres[i].setNoteMinimal(resultset.getInt("note_min"));
                    parametres[i].setNomMaladie(Maladie.getMaladieById(id, connection).getNomMaladie());
                    parametres[i].setParametre(Parametre.getParametreById(resultset.getInt("id_parametre_fk"), connection));
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

}
