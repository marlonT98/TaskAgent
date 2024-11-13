<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="Entity.UsuarioEntity"%>
<%@page import="Logic.UsuarioBL"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    UsuarioBL BL = new UsuarioBL();//entidad

    String id = request.getParameter("id");
    String accion = request.getParameter("accion"); //el tipo de accion

    // Acción DELETE: Eliminar un usuario
    if ("DELETE".equalsIgnoreCase(accion)) {

        BL.eliminar(id);

    }

    // Acción CREATE: Crear un nuevo usuario
    if ("CREATE".equalsIgnoreCase(accion)) {

        UsuarioEntity item = new UsuarioEntity();
        item.setNombre(request.getParameter("nombre"));
        item.setEmail(request.getParameter("email"));
        item.setPassword(request.getParameter("password"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String fechaCreacionStr = request.getParameter("fecha_creacion");
        String fechaUltimoAccesoStr = request.getParameter("fecha_ultimo_acceso");

        if (fechaCreacionStr != null && !fechaCreacionStr.isEmpty()) {
            item.setFecha_creacion(LocalDate.parse(fechaCreacionStr, formatter));
        }

        if (fechaUltimoAccesoStr != null && !fechaUltimoAccesoStr.isEmpty()) {
            item.setFecha_ultimo_acceso(LocalDate.parse(fechaUltimoAccesoStr, formatter));
        }

        BL.insertar(item);
    }

    // Acción UPDATE: Actualizar un usuario existente
    if ("UPDATE".equalsIgnoreCase(accion)) {
        UsuarioEntity item = new UsuarioEntity();

        item.setId_usuario(id);
        item.setNombre(request.getParameter("nombre"));
        item.setEmail(request.getParameter("email"));
        item.setPassword(request.getParameter("password"));

        // Define un formato de fecha que coincida con el formato del String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convierte el String de fecha_creacion a LocalDate
        String fechaCreacionStr = request.getParameter("fecha_creacion");
        if (fechaCreacionStr != null && !fechaCreacionStr.isEmpty()) {
            item.setFecha_creacion(LocalDate.parse(fechaCreacionStr, formatter));
        }

        // Convierte el String de fecha_ultimo_acceso a LocalDate
        String fechaUltimoAccesoStr = request.getParameter("fecha_ultimo_acceso");
        if (fechaUltimoAccesoStr != null && !fechaUltimoAccesoStr.isEmpty()) {
            item.setFecha_ultimo_acceso(LocalDate.parse(fechaUltimoAccesoStr, formatter));
        }

        BL.actualizar(item);
    }
    // Listar usuarios
    List<UsuarioEntity> lista = BL.listar();


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includecss.jsp" %>
        <title>Listado de Usuarios</title>
    </head>
    <body>
        <div id="wrapper">
            <%@include file="includenavbar.jsp"%>
            <div id="page-wrapper" class="gray-bg"> 
                <%@include file="includenavbarheader.jsp"%>  
                <%@include file="includepageheading.jsp"%> 

                <div class="wrapper wrapper-content animated fadeInRight">

                    <div class="row"> 
                        <div class="col-lg-12">
                            <div class="ibox ">
                                <div class="ibox-content">
                                    <a class='btn btn-outline-success' href='usuarios-edit.jsp?accion=CREATE'><i class='fa-solid fa-plus'></i>Nuevo<a/>
                                        <a class='btn btn-outline-primary' href='usuarios-list.jsp?accion=REFRESH'><i class='fa-solid fa-refresh'></i>Actualizar<a/>
                                            <button type="button" class="btn btn-outline-warning"><i class="fa-solid fa-print"></i>&nbsp Imprimir</button>

                                            <table class="table table-hover" >

                                                <thead>
                                                    <tr>
                                                        <th>Acciones</th>
                                                        <th>Nombre</th>
                                                        <th>Email</th>
                                                        <th>Password</th>
                                                        <th>Fecha de Creación</th>
                                                        <th>Último Acceso</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%  for (UsuarioEntity item : lista) {

                                                            out.write("<tr>");

                                                            out.write("<td>");
                                                            out.write("<a class='btn btn-info' href='usuarios-edit.jsp?accion=UPDATE&id=" + item.getId_usuario() + "'><i class='fa-solid fa-pencil'></i><a/>");
                                                            out.write(" ");
                                                            out.write("<a class='btn btn-danger' href='usuarios-list.jsp?accion=DELETE&id=" + item.getId_usuario() + "'><i class='fa-solid fa-trash'></i><a/>");
                                                            out.write("</td>");

                                                            out.write("<td>" + item.getNombre() + "</td>");
                                                            out.write("<td>" + item.getEmail() + "</td>");
                                                            out.write("<td>" + item.getPassword() + "</td>");
                                                            out.write("<td>" + item.getFecha_creacion() + " </td>");
                                                            out.write("<td>" + item.getFecha_ultimo_acceso() + "</td>");

                                                            out.write("</tr>");
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                            </div>
                                            </div>
                                            </div>



                                            </div>
                                            </div>
                                                    <%@include file="includefooter.jsp" %>
                                            </div>
                                            </div>

                                                    <%@include file="includejs.jsp" %>
                                            </body>
                                            </html>
