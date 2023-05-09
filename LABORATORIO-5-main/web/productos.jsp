<%--
    Document   : index
    Created on : 22 abr. 2023, 23:14:26
    Author     : NV7547
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body>

        <!-- Cabezera -->
        <header class="container-fluid bg-primary">
            <div class="row py-3 justify-content-between align-items-center">

                <div class="col-auto">
                    <h1 class="text-white">Tienda</h1>
                </div>

                <div class="col-auto">
                    <form class="d-flex" action="Dashboard">
                        <input class="form-control me-2" name="nombre" type="search" placeholder="Buscar por nombre" aria-label="Buscar">
                        <button class="btn btn-danger" type="submit">Buscar</button>
                    </form>
                </div>

            </div>
        </header>

        <main class="container">
            <form id="formClean" method="POST" action="Dashboard">
                <div class="row mt-5 justify-content-center p-0">
                    <div class="col-6">
                        <div class="mb-3 d-flex justify-content-between">
                            <label class="form-label fw-bold">Codigo:</label>
                            <input type="number" name="codigo" class="form-control" style="width: 25rem;" id="inputCodigo">
                        </div>
                        <div class="mb-3 d-flex justify-content-between">
                            <label class="form-label fw-bold">Nombre:</label>
                            <input type="text" name="nombre" class="form-control" style="width: 25rem;" id="inputNombre">
                        </div>
                        <div class="mb-3 d-flex justify-content-between">
                            <label class="form-label fw-bold">Precio:</label>
                            <input type="number" name="precio" class="form-control" style="width: 25rem;" id="inputPrecio">
                        </div>
                        <div class="mb-3 d-flex justify-content-between">
                            <label class="form-label fw-bold">Stock:</label>
                            <input type="number" name="stock" class="form-control" style="width: 25rem;" id="inputStock">
                        </div>
                    </div>
                </div>
                <div class="row mt-2 justify-content-center">
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success" style="width: 10rem;">Insertar</button>
                    </div>
                    <div class="col-auto">
                        <button id="btn-clean" type="button" class="btn btn-secondary" style="width: 10rem;">Limpiar</button>
                    </div>
                </div>
                <div class="row mt-2 justify-content-center">
                    <div class="col-auto">
                        <c:if test="${not empty mensaje}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${mensaje}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </form>
            <div class="row mt-5 justify-content-center">
                <div class="col-6">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Codigo</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Stock</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lista}" var="producto">
                                <tr>
                                    <td>${producto.codigo}</td>
                                    <td>${producto.nombre}</td>
                                    <td>${producto.precio}</td>
                                    <td>${producto.stock}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <script>
            const btnClean = document.getElementById('btn-clean');
            btnClean.addEventListener('click', function () {
                const myForm = document.getElementById('formClean');
                myForm.reset();
            });
        </script>
    </body>
</html>
