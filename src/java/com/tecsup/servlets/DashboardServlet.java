/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tecsup.servlets;

import com.tecsup.connection.DatabaseConection;
import com.tecsup.interfaz.Producto;
import com.tecsup.interfaz.ProductoDAO;
import com.tecsup.interfaz.ProductoDAOImpl;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NV7547
 */
public class DashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreBuscado = request.getParameter("nombre");
        List<Producto> lista = null;

        try {
            DatabaseConection db = new DatabaseConection();
            Connection con = db.Conectar();
            ProductoDAO productoDAO = new ProductoDAOImpl(con);

            if (nombreBuscado != null && !nombreBuscado.isEmpty()) {
                lista = productoDAO.buscarPorNombre(nombreBuscado);
            } else {
                lista = productoDAO.listar();
            }

            request.setAttribute("lista", lista);
            RequestDispatcher rd = request.getRequestDispatcher("productos.jsp");
            rd.forward(request, response);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Producto producto = new Producto(codigo, nombre, precio, stock);

        try {
            DatabaseConection db = new DatabaseConection();
            Connection con = db.Conectar();
            ProductoDAO productoDAO = new ProductoDAOImpl(con);
            productoDAO.agregar(producto);

            String mensaje = "El producto se ha añadido correctamente";
            request.setAttribute("mensaje", mensaje);
            response.sendRedirect(request.getContextPath() + "/Dashboard");
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
