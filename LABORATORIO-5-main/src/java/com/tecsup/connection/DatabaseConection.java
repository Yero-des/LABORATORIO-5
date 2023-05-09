package com.tecsup.connection;

import java.sql.*;

public class DatabaseConection {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/ventas";
    String usuario = "root";
    String clave = "";

    public Connection Conectar() {
        try {
            Class.forName(driver);
            Connection xcon = DriverManager.getConnection(url, usuario, clave);
            return xcon;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
