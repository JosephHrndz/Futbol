<%-- 
    Document   : headerBody
    Created on : 27 ago 2023, 14:58:08
    Author     : JOSEPH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProyectoFutbol.Web.Utils.*"%>
<nav>
    <div class="#000000 negro">
    <div class=>
        <a href="Home" class="brand-logo">THe FOotBaLL Net </a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down">  
           <% if (SessionUser.isAuth(request)) {  %>
    <li><a href="Home">Inicio</a></li>
    <li><a href="Ligas">Ligas</a></li>
    <li><a href="Carrusel?accion=index">Mas</a></li>
    <li><a href="Usuario?accion=cambiarpass">Usuario</a></li>
    </div>
            <%}
             else
                {%>
                <div class="#000000 negro">
         <li><a href="Home">Inicio</a></li>
            <li><a href="Ligas">Ligas</a></li>
            <li><a href="Rol">Usuario</a></li>
          
            </div>
              <%}%>
        </ul>
        
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
    <% if (SessionUser.isAuth(request)) {  %>
    <div class="#000000 negro">
    <li><a href="Home">Inicio</a></li>
    <li><a href="Usuario">Ligas</a></li>
    <li><a href="Carrusel?accion=index">Mas</a></li>
    <li><a href="Usuario?accion=cambiarpass">Usuario</a></li>
 </div>
    <%}%>
</ul>

