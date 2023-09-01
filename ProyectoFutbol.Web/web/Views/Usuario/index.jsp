<<%-- 
    Document   : index
    Created on : 27 ago 2023, 21:35:41
    Author     : JOSEPH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="proyectofutbol.entidaddenegocio.Usuario" %>
<%@page import="java.util.ArrayList" %>
<%ArrayList<Usuario> usuarios = 
                (ArrayList<Usuario>) request.getAttribute("usuarios");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if(usuarios == null)
    {
        usuarios = new ArrayList();
    }
    else
        if(usuarios.size() > numReg)
        {
            double divNumPage = (double) usuarios.size() / (double) numReg;
            numPage = (int) Math.ceil(divNumPage);
        }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if(strTop_aux != null && strTop_aux.trim().length() > 0)
    {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Usuario</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
        <main class="container">
           <h5>Buscar Usuario</h5>
           <form action="Usuario" method="post">
               <input type="hidden" name="accion" value="<%request.getAttribute("accion");%>">
               <div class="row">
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtNombre" name="nombre">
                       <label for="txtNombre">Nombre</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtApellido" name="apellido">
                       <label for="txtApellido">Apellido</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtLogin" name="login">
                       <label for="txtLogin">Login</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <select id="slEstado" name="estatus">
                           <option value="0">Seleccionar</option>
                           <option value="1">ACTIVO</option>
                           <option value="2">INACTIVO</option>
                       </select>
                       <label for="slEstado">Estatus</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Rol/select.jsp">
                           <jsp:param name="id" value="0"/>
                       </jsp:include>
                   </div>
                   <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Shared/selectTop.jsp">
                           <jsp:param name="top_aux" value="<%=top_aux%>"/>
                       </jsp:include>
                   </div>
               </div>
               <div class="row">
                   <div class="input-field col 16 s12">
                       <button type="submit" class="waves-effect waves-ligth btn blue">Buscar</button>
                       <a href="Usuario?accion=create" class="waves-effect waves-ligth btn blue">Nuevo</a>
                   </div>
               </div>
           </form>
               
            <div class="row">
                <div class="col 112 s12">
                    <div style="overflow: auto;">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Apellido</th>
                                    <th>Login</th>
                                    <th>Estatus</th>
                                    <th>Rol</th>
                                    <th>Fecha Registro</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for(Usuario usuario:usuarios)
                                {
                                   int tempNumPage = numPage;
                                   if(numPage > 1)
                                   {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                   }
                                   
                                   String estatus = "";
                                   switch(usuario.getEstatus())
                                   {
                                        case 1:
                                            estatus = "ACTIVO";
                                            break;
                                        case 2:
                                            estatus = "INACTIVO";
                                            break;
                                        default:
                                            estatus = "";
                                   }
                                %>
                                    <tr data-page="<%=tempNumPage%>">
                                        <td><%=usuario.getNombre()%></td>
                                        <td><%=usuario.getApellido()%></td>
                                        <td><%=usuario.getLogin()%></td>
                                        <td><%=estatus%></td>
                                        <td><%=usuario.getRol().getNombre()%></td>
                                        <td><%=usuario.getFechaRegistro()%></td>
                                        <td>
                                            <div style="display: flex">
                                                <a href="Usuario?accion=edit&id=<%=usuario.getId()%>" 
                                                   title="Mofificar" class="waves-effect waves-light btn green">
                                                    <i class="material-icons">edit</i>
                                                </a>
                                                <a href="Usuario?accion=details&id=<%=usuario.getId()%>" 
                                                   title="Ver" class="waves-effect waves-light btn blue">
                                                    <i class="material-icons">description</i>
                                                </a>
                                                <a href="Usuario?accion=delete&id=<%=usuario.getId()%>" 
                                                   title="Eliminar" class="waves-effect waves-light btn red">
                                                    <i class="material-icons">delete</i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr> 
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col 112 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%=numPage%>"/>
                    </jsp:include> 
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

