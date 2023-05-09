package com.tecsup.connection;

import com.mongodb.client.MongoDatabase;
import java.sql.*;

public class TestDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        DatabaseConection dbConnection = new DatabaseConection();
        Connection con = dbConnection.Conectar();
        MongoConection conexion = new MongoConection();
        MongoDatabase database = conexion.getDatabase();

        if (con != null && database != null) {
            System.out.println("Se ha conectado a ambas bases de datos correctamente");
            conexion.close();
            con.close();
        } else {
            System.out.println("La conexion a la base de datos ha fallado");
        }

    }

}
