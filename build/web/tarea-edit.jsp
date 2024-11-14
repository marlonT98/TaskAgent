<%@page import="java.time.LocalDate"%>
<%@page import="Entity.TareaEntity"%>
<%@page import="Logic.TareaBL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    TareaBL tareaBL = new TareaBL();
    TareaEntity tarea = new TareaEntity();

    String id = request.getParameter("id");
    String accion = request.getParameter("accion");

    // Acción UPDATE: buscamos la tarea en la base de datos
    if ("UPDATE".equalsIgnoreCase(accion)) {
        tarea = tareaBL.bucarPorId(id);
    } // Acción CREATE: Inicializar una tarea vacía
    else if ("CREATE".equalsIgnoreCase(accion)) {
        tarea.setTitulo("");
        tarea.setDescripcion("");
        tarea.setFecha_creacion(null);
        tarea.setFecha_vencimiento(null);
        tarea.setEstado(TareaEntity.Estado.POR_HACER); // Valor predeterminado
        tarea.setPrioridad(TareaEntity.Prioridad.Media); // Valor predeterminado
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includecss.jsp"%>
        <title><%= "Editar Tarea"%></title>
    </head>
    <body>
        <div id="wrapper">
            <%@include file="includenavbar.jsp"%>
            <div id="page-wrapper" class="gray-bg"> 
                <%@include file="includenavbarheader.jsp"%>  
                <%@include file="includepageheading.jsp"%> 

                <div class="wrapper wrapper-content animated fadeInRight">

                    <div class="row"> 
                        <div class="col-lg-6 offset-lg-3">
                            <div class="ibox ">
                                <div class="ibox-content">

                                    <form action="tarea-list.jsp" method="post">
                                        <input type="hidden" name="accion" value="<%= accion%>">
                                        <input type="hidden" name="id" value="<%= tarea.getId_tarea() != null ? tarea.getId_tarea() : ""%>">

                                        <label>ID Usuario:</label>
                                        <input type="text" name="fk_usuario" value="<%= tarea.getFk_usuario() != null ? tarea.getFk_usuario() : ""%>" required><br>

                                        <label>Título:</label>
                                        <input type="text" name="titulo" value="<%= tarea.getTitulo() != null ? tarea.getTitulo() : ""%>" required><br>

                                        <label>Descripción:</label>
                                        <textarea name="descripcion" required><%= tarea.getDescripcion() != null ? tarea.getDescripcion() : ""%></textarea><br>

                                        <label>Fecha de Creación:</label>
                                        <input type="date" name="fecha_creacion" value="<%= tarea.getFecha_creacion() != null ? tarea.getFecha_creacion().toString() : ""%>" required><br>

                                        <label>Fecha de Vencimiento:</label>
                                        <input type="date" name="fecha_vencimiento" value="<%= tarea.getFecha_vencimiento() != null ? tarea.getFecha_vencimiento().toString() : ""%>" required><br>

                                        <label>Estado:</label>
                                        <select name="estado" required>
                                            <option value="POR_HACER" <%= tarea.getEstado() == TareaEntity.Estado.POR_HACER ? "selected" : ""%>>Por hacer</option>
                                            <option value="EN_PROGRESO" <%= tarea.getEstado() == TareaEntity.Estado.EN_PROGRESO ? "selected" : ""%>>En progreso</option>
                                            <option value="COMPLETADO" <%= tarea.getEstado() == TareaEntity.Estado.COMPLETADO ? "selected" : ""%>>Completado</option>
                                        </select><br>

                                        <label>Prioridad:</label>
                                        <select name="prioridad" required>
                                            <option value="Alta" <%= tarea.getPrioridad() == TareaEntity.Prioridad.Alta ? "selected" : ""%>>Alta</option>
                                            <option value="Media" <%= tarea.getPrioridad() == TareaEntity.Prioridad.Media ? "selected" : ""%>>Media</option>
                                            <option value="Baja" <%= tarea.getPrioridad() == TareaEntity.Prioridad.Baja ? "selected" : ""%>>Baja</option>
                                        </select><br>

                                        <button type="submit" value="guardar" class="btn btn-primary">
                                            <i class="fa-solid fa-save"></i> Guardar
                                        </button>
                                         <button type="reset" value="limpiar" class="btn btn-danger">
                                            <i class="fa-regular fa-circle-check"></i> Limpiar
                                        </button>
                                    </form>
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
