<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ProyectoFutbol.entidadesdenegocio.BundesLiga" %>
<% BundesLiga bundesLiga = (BundesLiga) request.getAttribute("bundesLiga");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar BundesLiga</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Editar BundesLiga</h5>
            <form action="BundesLiga" method="post">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                <input type="hidden" name="id" value="<%=bundesLiga.getId()%>" />
                <div class="row">
                         <div  class="input-field col 14 s12">
                        <jsp:include page="/Views/Ligas/select.jsp">
                            <jsp:param name="id" value="0"/>
                        </jsp:include>
                         </div>
                  
                     <div class="input-field col 14 s12">
                        <input type="number" disabled id="nbPosicion" name ="posicion" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getPosicion()%>">
                        <label for="nbPosicion">Posicion</label>
                    </div>
                        
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtEquipo" name ="equipo" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getEquipo()%>">
                        <label for="txtEquipo">Equipo</label>
                    </div>
                          <div class="input-field col 14 s12">
                        <input type="number" disabled id="nbPuntos" name ="puntos" required 
                               class="validate" maxlength="30" 
                               value="<%=bundesLiga.getPuntos()%>">
                        <label for="nbPuntos">Puntos</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">save</i>Guardar
                        </button>
                        <a href="Rol" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

