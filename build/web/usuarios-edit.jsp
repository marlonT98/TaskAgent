<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Entity.UsuarioEntity"%>
<%@page import="Logic.UsuarioBL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UsuarioBL BL = new UsuarioBL();
    UsuarioEntity usuario = new UsuarioEntity();

    String id = request.getParameter("id");
    String miAccion = request.getParameter("accion");

    // Acción UPDATE: buscamos el usuario en la base de datos
    if ("UPDATE".equalsIgnoreCase(miAccion)) {
        usuario = BL.bucarPorId(id); // Buscar al usuario en la base de datos
    }

    // Acción CREATE: Inicializar un usuario vacío
    if ("CREATE".equalsIgnoreCase(miAccion)) {

        usuario.setNombre("");
        usuario.setEmail("");
        usuario.setPassword("");
        usuario.setFecha_creacion(null); // Reseteamos como null
        usuario.setFecha_ultimo_acceso(null); // Reseteamos como null
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includecss.jsp"%>
        <title><%= "Editar Usuario"%></title>
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
                                    <form method="POST" action="usuarios-list.jsp">

                                        <input type="hidden"  name="accion" value="<%=miAccion%>" >
                                        <input type="hidden" name="id" value="<%=usuario.getId_usuario()%>">


                                        <label> Nombre:</label>
                                        <input type="text" class="form-control" name="nombre" value="<%=usuario.getNombre()%>">

                                        <label> Email:</label>
                                        <input type="email" class="form-control" name="email" value="<%=usuario.getEmail()%>">

                                        <label> Password:</label>
                                        <input type="text" class="form-control" name="password" value="<%=usuario.getPassword()%>">

                                        <label>Fecha de creación:</label>
                                        <input type="date" class="form-control" name="fecha_creacion"
                                               value="<%=usuario.getFecha_creacion()%>">

                                        <label>Último acceso:</label>
                                        <input type="date" class="form-control" name="fecha_ultimo_acceso"
                                               value="<%=usuario.getFecha_ultimo_acceso()%>">

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
