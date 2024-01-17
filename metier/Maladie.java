package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Maladie {
    int idMaladie;
    String nomMaladie;

    public void setIdMaladie(int idMaladie) {
        this.idMaladie = idMaladie;
    }
    public void setNomMaladie(String nomMaladie) {
        this.nomMaladie = nomMaladie;
    }

    public int getIdMaladie() {
        return idMaladie;
    }
    public String getNomMaladie() {
        return nomMaladie;
    }

    public Maladie(){}

    public Maladie(int id,String nom){
        setIdMaladie(id);
        setNomMaladie(nom);
    }

    
    public static Maladie[] getAllMaladie(Connection connection) throws Exception{
        String query = "select * from maladie";
        Maladie[] maladies;
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
                maladies = new Maladie[0];
            }else{
                maladies = new Maladie[size];
                int i = 0;
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    maladies[i] = new Maladie();
                    maladies[i].setIdMaladie(resultset.getInt("id_maladie"));
                    maladies[i].setNomMaladie(resultset.getString("designation"));
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
        return maladies;
    }

    public static Maladie getMaladieById(int id,Connection connection) throws Exception{
        String query = "select * from maladie where id_maladie = ?";
        Maladie maladie = new Maladie();
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
                    maladie.setIdMaladie(resultset.getInt("id_maladie"));
                    maladie.setNomMaladie(resultset.getString("designation"));
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
        return maladie;
    }

    public static Maladie[] getAllMaladieExistInAge(int age,Connection connection) throws Exception{
        String query = "select distinct(id_maladie_fk) from parametre_maladie where an_min <= ? and an_max >= ?";
        Maladie[] maladies;
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
            statement.setInt(1, age);
            statement.setInt(2, age);
			statementOpen = true;
			
			resultset =  statement.executeQuery();
			
			while(resultset.next()) {
                size++;
			}
            if(size==0){
                maladies = new Maladie[0];
            }else{
                maladies = new Maladie[size];
                int i = 0;
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    maladies[i] = Maladie.getMaladieById(resultset.getInt("id_maladie_fk"), connection);                 
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
        return maladies;
    }

}
