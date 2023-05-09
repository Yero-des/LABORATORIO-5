package com.tecsup.interfaz;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {

    public List<Producto> listar();

    public void agregar(Producto producto) throws SQLException;

    public List<Producto> buscarPorNombre(String nombre) throws SQLException;

}
