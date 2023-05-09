package com.tecsup.interfaz;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tecsup.connection.MongoConection;
import com.mongodb.client.model.Filters;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class ProductoDAOImpl implements ProductoDAO {

    private final Connection con;

    public ProductoDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();

        // Conexion MySql
        String sql = "SELECT * FROM producto";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                Producto p = new Producto(codigo, nombre, precio, stock);
                lista.add(p);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Conexion MongoDb
        MongoConection conexion = new MongoConection();
        MongoDatabase baseDatos = conexion.getDatabase();
        MongoCollection<Document> coleccion = baseDatos.getCollection("producto");
        FindIterable<Document> iterable = coleccion.find();
        for (Document doc : iterable) {
            int codigo = doc.getInteger("codigo");
            String nombre = doc.getString("nombre");
            double precio = doc.getDouble("precio");
            int stock = doc.getInteger("stock");
            Producto p = new Producto(codigo, nombre, precio, stock);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public void agregar(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (codigo, nombre, precio, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            // Manejar la excepción
        }
    }

    @Override
    public void agregarNoSql(Producto producto) throws SQLException {
        MongoConection conexion = new MongoConection();
        MongoDatabase baseDatos = conexion.getDatabase();
        MongoCollection<Document> coleccion = baseDatos.getCollection("producto");
        Document doc = new Document("codigo", producto.getCodigo())
                .append("nombre", producto.getNombre())
                .append("precio", producto.getPrecio())
                .append("stock", producto.getStock());
        coleccion.insertOne(doc);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto(
                            rs.getInt("codigo"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                    productos.add(producto);
                }
            }
        } catch (SQLException ex) {
            // Manejar la excepcion
        }

        MongoConection conexion = new MongoConection();
        MongoDatabase baseDatos = conexion.getDatabase();
        MongoCollection<Document> coleccion = baseDatos.getCollection("producto");
        FindIterable<Document> iterable = coleccion.find(Filters.regex("nombre", ".*" + nombre + ".*"));
        for (Document doc : iterable) {
            Producto p = new Producto(
                    doc.getInteger("codigo"),
                    doc.getString("nombre"),
                    doc.getDouble("precio"),
                    doc.getInteger("stock"));
            productos.add(p);
        }

        return productos;
    }

}
