<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProyectoFutbol.entidadesdenegocio.BundesLiga" %>
<%@page import="ProyectoFutbol.accesoadatos.BundesLigaDAL" %>
<%@page import="java.util.ArrayList" %>
<% ArrayList<BundesLiga> bundesLigas = BundesLigaDAL.obtenerTodos();
   int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slBundesLiga" name="idBundesLiga">
    <option <%=(id == 0) ? "selected" : "" %> value="0">Seleccionar</option>
    <%for(BundesLiga bundesLiga: bundesLigas)
    {
    %>
    <option <%=(id==bundesLiga.getId()) ? "selected" : "" %>
        value="<%=bundesLiga.getId()%>">
         <%=bundesLiga.getIdLigas%>
        <%=bundesLiga.getPosicion()%>
         <%=bundesLiga.getEquipo()%>
          <%=bundesLiga.getPuntos()%>
    </option>
    <%
    }%>
</select>
<label for="slBundesLiga">BundesLiga</label>