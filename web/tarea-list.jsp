<%@page import="java.time.LocalDate"%>
<%@page import="Entity.TareaEntity"%>
<%@page import="Logic.TareaBL"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    TareaBL tareaBL = new TareaBL();
    String accion = request.getParameter("accion");
    String id = request.getParameter("id");

    // Acciones de CREATE, UPDATE y DELETE
    if (accion != null) {
        try {
            if ("DELETE".equalsIgnoreCase(accion) && id != null) {
                tareaBL.eliminar(id);
            } else if ("CREATE".equalsIgnoreCase(accion)) {
                TareaEntity tarea = new TareaEntity();
                tarea.setFk_usuario(request.getParameter("fk_usuario"));
                tarea.setTitulo(request.getParameter("titulo"));
                tarea.setDescripcion(request.getParameter("descripcion"));
                tarea.setFecha_creacion(LocalDate.parse(request.getParameter("fecha_creacion")));
                tarea.setFecha_vencimiento(LocalDate.parse(request.getParameter("fecha_vencimiento")));
                tarea.setEstado(TareaEntity.Estado.valueOf(request.getParameter("estado").toUpperCase()));
                tarea.setPrioridad(TareaEntity.Prioridad.valueOf(request.getParameter("prioridad").toUpperCase()));
                tareaBL.insertar(tarea);
            } else if ("UPDATE".equalsIgnoreCase(accion) && id != null) {
                TareaEntity tarea = tareaBL.bucarPorId(id);
                if (tarea != null) {
                    tarea.setFk_usuario(request.getParameter("fk_usuario"));
                    tarea.setTitulo(request.getParameter("titulo"));
                    tarea.setDescripcion(request.getParameter("descripcion"));
                    tarea.setFecha_creacion(LocalDate.parse(request.getParameter("fecha_creacion")));
                    tarea.setFecha_vencimiento(LocalDate.parse(request.getParameter("fecha_vencimiento")));
                    tarea.setEstado(TareaEntity.Estado.valueOf(request.getParameter("estado").toUpperCase()));
                    tarea.setPrioridad(TareaEntity.Prioridad.valueOf(request.getParameter("prioridad").toUpperCase()));
                    tareaBL.actualizar(tarea);
                }
            }
        } catch (Exception e) {
            out.println("Error al procesar la tarea: " + e.getMessage());
        }
    }

    List<TareaEntity> listaTareas = tareaBL.listar();
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includecss.jsp" %>
        <title>Lista de Tareas</title>

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
                                    <a class='btn btn-outline-success' href='tarea-edit.jsp?accion=CREATE'><i class='fa-solid fa-plus'></i>Nuevo<a/>
                                        <a class='btn btn-outline-primary' href='tarea-list.jsp?accion=REFRESH'><i class='fa-solid fa-refresh'></i>Actualizar<a/>
                                            <button type="button" class="btn btn-outline-warning"><i class="fa-solid fa-print"></i>&nbsp Imprimir</button>

                                            <table class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Acciones</th>
                                                        <th>ID</th>
                                                        <th>ID Usuario</th>
                                                        <th>Título</th>
                                                        <th>Descripción</th>
                                                        <th>Fecha de Creación</th>
                                                        <th>Fecha de Vencimiento</th>
                                                        <th>Estado</th>
                                                        <th>Prioridad</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (TareaEntity tarea : listaTareas) {%>
                                                    <tr>
                                                        <td>
                                                            <a class='btn btn-info' href="tarea-edit.jsp?accion=UPDATE&id=<%= tarea.getId_tarea()%>"><i class='fa-solid fa-pencil'></i></a>
                                                            <a class='btn btn-danger' href="tarea-list.jsp?accion=DELETE&id=<%= tarea.getId_tarea()%>" onclick="return confirmarEliminacion();"><i class='fa-solid fa-trash'></i></a>
                                                        </td>
                                                        <td><%= tarea.getId_tarea()%></td>
                                                        <td><%= tarea.getFk_usuario()%></td>
                                                        <td><%= tarea.getTitulo()%></td>
                                                        <td><%= tarea.getDescripcion()%></td>
                                                        <td><%= tarea.getFecha_creacion()%></td>
                                                        <td><%= tarea.getFecha_vencimiento()%></td>
                                                        <td><%= tarea.getEstado()%></td>
                                                        <td><%= tarea.getPrioridad()%></td>
                                                    </tr>
                                                    <% }%>
                                                </tbody>
                                            </table>
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
