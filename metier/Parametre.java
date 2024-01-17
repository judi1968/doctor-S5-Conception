package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Parametre {
    int idParametre;
    String nomParametre;
    public void setIdParametre(int idParametre) {
        this.idParametre = idParametre;
    }
    public int getIdParametre() {
        return idParametre;
    }
    public void setNomParametre(String nomParametre) {
        this.nomParametre = nomParametre;
    }
    public String getNomParametre() {
        return nomParametre;
    }

    public Parametre(){}
    public Parametre(int idParametre,String nomParametre){
        setIdParametre(idParametre);
        setNomParametre(nomParametre);
    }

    public static Parametre[] getAllParametre(Connection connection) throws Exception{
        String query = "select * from parametre";
        Parametre[] parametres;
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
                parametres = new Parametre[0];
            }else{
                parametres = new Parametre[size];
                int i = 0;
                resultset =  statement.executeQuery();
                while(resultset.next()){
                    parametres[i] = new Parametre();
                    parametres[i].setIdParametre(resultset.getInt("id_parametre"));
                    parametres[i].setNomParametre(resultset.getString("designation"));
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

    public static Parametre getParametreById(int id,Connection connection) throws Exception{
        String query = "select * from parametre where id_parametre = ?";
        Parametre parametre = new Parametre();
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
                    parametre.setIdParametre(resultset.getInt("id_parametre"));
                    parametre.setNomParametre(resultset.getString("designation"));
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
        return parametre;
    }
}
