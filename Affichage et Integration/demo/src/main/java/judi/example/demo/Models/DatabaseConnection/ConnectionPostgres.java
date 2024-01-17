package judi.example.demo.Models.DatabaseConnection;

import java.sql.*;

public class ConnectionPostgres {
    public static Connection connect(String ip,int port,String databaseName,String userName,String password) throws Exception{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+databaseName+"", ""+userName+"", ""+password+"");
    }
}