<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProyectoFutbol.entidadesdenegocio.BundesLiga"%>
<%@page import="java.util.ArrayList" %>
<%ArrayList<BundesLiga> bundesLigas = (ArrayList<BundesLiga>) request.getAttribute("bundesLigas");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if(bundesLigas == null)
    {
        bundesLigas = new ArrayList();
    }
    else
        if(bundesLigas.size() > numReg)
        {
            double divNumPage = (double) bundesLigas.size() / (double) numReg;
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
        <title>Buscar BundesLiga</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
        <main class="container">
           <h5>Buscar BundesLiga</h5>
           <form action="BundesLiga" method="post">
               <input type="hidden" name="accion" value="<%request.getAttribute("accion");%>">
               <div class="row">
                 
                   <div class="input-field col 16 s12">
                        <input type="number" id="nbIdLigas " name ="idLigas">
                        <label for="nbIdLigas">IdLigas</label>
                    </div>
                    <div class="input-field col 16 s12">
                        <input type="text" id="txtEquipo" name ="equipo">
                        <label for="txtEquipo">Equipo</label>
                    </div>
                   
                  <div class="input-field col 16 s12">
                      <input type="number" id="nbPosicion" name ="posicion">
                        <label for="nbPosicion">Posicion</label>
                    </div>
                    <div class="input-field col 16 s12">
                        <input type="number" id="nbPuntos " name ="puntos">
                        <label for="nbPuntos">Puntos</label>
                    </div>
                   <div class="input-field col 13 s12">
                       <jsp:include page="/Views/Shared/selectTop.jsp">
                           <jsp:param name="top_aux" value="<%=top_aux%>"/>
                       </jsp:include>
                   </div>
               </div>
               <div class="row">
                   <div class="input-field col 16 s12">
                       <button type="submit" class="waves-effect waves-ligth btn blue">Buscar</button>
                       <a href="Rol?accion=create" class="waves-effect waves-ligth btn blue">Nuevo</a>
                   </div>
               </div>
           </form>
               
            <div class="row">
                <div class="col 112 s12">
                    <div style="overflow: auto;">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>IdLigas</th>
                                    <th>Posicion</th>
                                     <th>Puntos</th>
                                      <th>Equipo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for(BundesLiga bundesLiga:bundesLigas)
                                {
                                   int tempNumPage = numPage;
                                   if(numPage > 1)
                                   {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                   }
                                %>
                                    <tr data-page="<%=tempNumPage%>">
                                        <td><%=bundesLiga.getIdLigas()%></td>
                                        <td><%=bundesLiga.getPosicion()%></td>
                                        <td><%=undesLiga.getPuntos()%></td>
                                        <td><%=bundesLiga.getEquipo()%></td>
                                       
                                        <td>
                                            <div style="display: flex">
                                                <a href="BundesLiga?accion=edit&id=<%=bundesLiga.getId()%>" 
                                                   title="Mofificar" class="waves-effect waves-light btn green">
                                                    <i class="material-icons">edit</i>
                                                </a>
                                                <a href="BundesLiga?accion=details&id=<%=bundesLiga.getId()%>" 
                                                   title="Ver" class="waves-effect waves-light btn blue">
                                                    <i class="material-icons">description</i>
                                                </a>
                                                <a href="BundesLiga?accion=delete&id=<%=bundesLiga.getId()%>" 
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

---------