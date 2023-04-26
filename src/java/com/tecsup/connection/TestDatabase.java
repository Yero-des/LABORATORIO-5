package com.tecsup.connection;

import java.sql.*;

public class TestDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DatabaseConection dbConnection = new DatabaseConection();
        Connection con = dbConnection.Conectar();

        if (con != null) {
            System.out.println("Se ha conectado a la base de datos correctamente");
        } else {
            System.out.println("La conexion a la base de datos ha fallado");
        }

    }

}
